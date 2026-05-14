package com.example.siridhanyahub

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siridhanyahub.adapter.RecipeLabAdapter
import com.example.siridhanyahub.model.Recipe

class RecipeLabActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var adapter: RecipeLabAdapter
    private val savedRecipes = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_lab)

        // Set status bar color
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = androidx.core.content.ContextCompat.getColor(this, R.color.green_primary)
        }

        sharedPreferences = getSharedPreferences("saved_recipes", Context.MODE_PRIVATE)
        loadSavedRecipes()

        // Back button
        val btnBack: ImageView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        // Search functionality
        val searchView: SearchView = findViewById(R.id.searchView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRecipes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = getAllRecipes()
        adapter = RecipeLabAdapter(recipes, savedRecipes) { recipe, isSaved ->
            if (isSaved) {
                savedRecipes.add(recipe.title)
                Toast.makeText(this, "✨ ${recipe.title} saved to favorites!", Toast.LENGTH_SHORT).show()
            } else {
                savedRecipes.remove(recipe.title)
                Toast.makeText(this, "❌ ${recipe.title} removed from favorites", Toast.LENGTH_SHORT).show()
            }
            saveSavedRecipes()
        }
        recyclerView.adapter = adapter

        // Search filter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty())
                return true
            }
        })
    }

    private fun loadSavedRecipes() {
        val saved = sharedPreferences.getStringSet("saved", emptySet())
        savedRecipes.clear()
        savedRecipes.addAll(saved ?: emptySet())
    }

    private fun saveSavedRecipes() {
        sharedPreferences.edit().putStringSet("saved", savedRecipes).apply()
    }

    private fun getAllRecipes(): List<Recipe> {
        return listOf(
            Recipe(
                id = 1,
                title = "Ragi Mudde (ರಾಗಿ ಮುದ್ದೆ)",
                milletType = "Ragi",
                prepTime = "25 mins",
                difficulty = "Easy",
                ingredients = listOf(
                    "1 cup Ragi flour",
                    "2 cups water",
                    "1/2 tsp salt",
                    "1 tsp ghee"
                ),
                instructions = listOf(
                    "Boil water in a deep pan with salt",
                    "Add ragi flour slowly while stirring continuously",
                    "Cook until mixture thickens and forms a dough",
                    "Shape into balls while hot",
                    "Serve with sambar or curry"
                ),
                imageRes = R.drawable.ic_ragi_recipe,
                calories = "150 kcal per serving",
                kannadaInstructions = listOf(
                    "ದಪ್ಪ ಪಾತ್ರೆಯಲ್ಲಿ ನೀರು ಮತ್ತು ಉಪ್ಪು ಹಾಕಿ ಕುದಿಸಿ",
                    "ರಾಗಿ ಹಿಟ್ಟನ್ನು ನಿಧಾನವಾಗಿ ಸೇರಿಸಿ ನಿರಂತರವಾಗಿ ಕಲಸಿ",
                    "ಮಿಶ್ರಣ ದಪ್ಪವಾಗುವವರೆಗೆ ಬೇಯಿಸಿ",
                    "ಬಿಸಿಯಾಗಿದ್ದಾಗಲೇ ಉಂಡೆಗಳಾಗಿ ಮಾಡಿ",
                    "ಸಾಂಬಾರ್ ಅಥವಾ ಮಾಂಸದ ಕರಿಯೊಂದಿಗೆ ಸರ್ವ್ ಮಾಡಿ"
                )
            ),
            Recipe(
                id = 2,
                title = "Navane Upma (ನವಣೆ ಉಪ್ಪಿಟ್ಟು)",
                milletType = "Navane",
                prepTime = "20 mins",
                difficulty = "Easy",
                ingredients = listOf(
                    "1 cup Navane (Foxtail millet)",
                    "2 cups water",
                    "1 onion chopped",
                    "1 tsp mustard seeds",
                    "Curry leaves",
                    "Vegetables of your choice"
                ),
                instructions = listOf(
                    "Dry roast navane until aromatic",
                    "Heat oil, add mustard seeds and curry leaves",
                    "Add onions and vegetables, sauté well",
                    "Add water and salt, bring to boil",
                    "Add roasted navane, cook until done"
                ),
                imageRes = R.drawable.ic_navane_recipe,
                calories = "180 kcal per serving",
                kannadaInstructions = listOf(
                    "ನವಣೆಯನ್ನು ಸುವಾಸನೆ ಬರುವವರೆಗೆ ಒಣ ಹುರಿಯಿರಿ",
                    "ಎಣ್ಣೆ ಹಾಕಿ ಸಾಸಿವೆ ಮತ್ತು ಕರಿಬೇವು ಸೇರಿಸಿ",
                    "ಈರುಳ್ಳಿ ಮತ್ತು ತರಕಾರಿಗಳನ್ನು ಸೇರಿಸಿ ಹುರಿಯಿರಿ",
                    "ನೀರು ಮತ್ತು ಉಪ್ಪು ಸೇರಿಸಿ ಕುದಿಸಿ",
                    "ಹುರಿದ ನವಣೆ ಸೇರಿಸಿ ಅನ್ನದವರೆಗೆ ಬೇಯಿಸಿ"
                )
            ),
            Recipe(
                id = 3,
                title = "Sajje Roti (ಸಜ್ಜೆ ರೊಟ್ಟಿ)",
                milletType = "Sajje",
                prepTime = "30 mins",
                difficulty = "Medium",
                ingredients = listOf(
                    "2 cups Sajje flour",
                    "1 onion finely chopped",
                    "1 tsp cumin seeds",
                    "Salt to taste",
                    "Warm water as needed"
                ),
                instructions = listOf(
                    "Mix flour with chopped onion, cumin, and salt",
                    "Add warm water gradually to form soft dough",
                    "Let it rest for 15 minutes",
                    "Roll into rotis using wet hands",
                    "Cook on hot tawa until golden brown"
                ),
                imageRes = R.drawable.ic_sajje_recipe,
                calories = "210 kcal per roti",
                kannadaInstructions = listOf(
                    "ಹಿಟ್ಟಿಗೆ ಈರುಳ್ಳಿ, ಜೀರಿಗೆ, ಉಪ್ಪು ಸೇರಿಸಿ ಮಿಶ್ರಣ ಮಾಡಿ",
                    "ಬೆಚ್ಚಗಿನ ನೀರು ಸೇರಿಸಿ ಮೆತ್ತಗಿನ ಹಿಟ್ಟನ್ನು ತಯಾರಿಸಿ",
                    "15 ನಿಮಿಷಗಳ ಕಾಲ ಅದನ್ನು ವಿಶ್ರಾಂತಿ ಮಾಡಿ",
                    "ಒದ್ದೆಯಾದ ಕೈಗಳಿಂದ ರೊಟ್ಟಿಗಳನ್ನು ಹರಡಿ",
                    "ಬಿಸಿ ತವಾದಲ್ಲಿ ಗೋಲ್ಡನ್ ಬ್ರೌನ್ ಆಗುವವರೆಗೆ ಬೇಯಿಸಿ"
                )
            ),
            Recipe(
                id = 4,
                title = "Baragu Khichdi (ಬರಗು ಖಿಚಡಿ)",
                milletType = "Baragu",
                prepTime = "25 mins",
                difficulty = "Easy",
                ingredients = listOf(
                    "1 cup Baragu millet",
                    "1/2 cup moong dal",
                    "1 tsp ghee",
                    "1 tsp cumin seeds",
                    "Vegetables: carrot, beans, peas"
                ),
                instructions = listOf(
                    "Wash and soak baragu and moong dal",
                    "Heat ghee, add cumin seeds",
                    "Add vegetables and sauté",
                    "Add millet, dal, water and salt",
                    "Pressure cook for 3 whistles"
                ),
                imageRes = R.drawable.ic_recipe_default,
                calories = "220 kcal per serving",
                kannadaInstructions = listOf(
                    "ಬರಗು ಮತ್ತು ತೊಗರಿ ಬೇಳೆಯನ್ನು ತೊಳೆದು ನೆನೆಸಿ",
                    "ತುಪ್ಪ ಬಿಸಿ ಮಾಡಿ ಜೀರಿಗೆ ಸೇರಿಸಿ",
                    "ತರಕಾರಿಗಳನ್ನು ಸೇರಿಸಿ ಹುರಿಯಿರಿ",
                    "ಬರಗು, ಬೇಳೆ, ನೀರು ಮತ್ತು ಉಪ್ಪು ಸೇರಿಸಿ",
                    "3 ವಿಸಲ್ ಬರುವವರೆಗೆ ಪ್ರೆಶರ್ ಕುಕ್ ಮಾಡಿ"
                )
            ),
            Recipe(
                id = 5,
                title = "Oodalu Pongal (ಊದಲು ಪೊಂಗಲ್)",
                milletType = "Oodalu",
                prepTime = "30 mins",
                difficulty = "Medium",
                ingredients = listOf(
                    "1 cup Oodalu millet",
                    "1/2 cup moong dal",
                    "1 tsp pepper",
                    "1 tsp cumin seeds",
                    "Curry leaves, ginger, ghee"
                ),
                instructions = listOf(
                    "Roast millet and dal lightly",
                    "Pressure cook with 4 cups water",
                    "In pan, heat ghee and add pepper, cumin, ginger",
                    "Add cooked millet-dal mixture",
                    "Mix well and simmer for 5 minutes"
                ),
                imageRes = R.drawable.ic_recipe_default,
                calories = "195 kcal per serving",
                kannadaInstructions = listOf(
                    "ಸ್ವಲ್ಪವಾಗಿ ಊದಲು ಮತ್ತು ಬೇಳೆಯನ್ನು ಹುರಿಯಿರಿ",
                    "4 ಕಪ್ ನೀರಿನೊಂದಿಗೆ ಪ್ರೆಶರ್ ಕುಕ್ ಮಾಡಿ",
                    "ಪ್ಯಾನ್ ನಲ್ಲಿ ತುಪ್ಪ ಬಿಸಿ ಮಾಡಿ ಮೆಣಸು, ಜೀರಿಗೆ, ಶುಂಠಿ ಸೇರಿಸಿ",
                    "ಬೇಯಿಸಿದ ಊದಲು-ಬೇಳೆ ಮಿಶ್ರಣ ಸೇರಿಸಿ",
                    "ಚೆನ್ನಾಗಿ ಮಿಕ್ಸ್ ಮಾಡಿ 5 ನಿಮಿಷ ಕುದಿಸಿ"
                )
            )
        )
    }
}