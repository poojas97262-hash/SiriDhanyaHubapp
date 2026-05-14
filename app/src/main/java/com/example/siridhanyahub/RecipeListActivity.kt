package com.example.siridhanyahub

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siridhanyahub.adapter.RecipeAdapter
import com.example.siridhanyahub.model.Recipe

class RecipeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        // Set status bar color
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = androidx.core.content.ContextCompat.getColor(this, R.color.green_primary)
        }

        setupRecyclerView()

        // Back button
        val btnBack: ImageView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRecipes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = getRecipes()
        val adapter = RecipeAdapter(recipes) { recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("recipe", recipe)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }

    private fun getRecipes(): List<Recipe> {
        return listOf(
            Recipe(
                id = 1,
                title = "Ragi Mudde (Finger Millet Balls)",
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
                calories = "150 kcal per serving"
            ),
            Recipe(
                id = 2,
                title = "Navane Upma (Foxtail Millet Upma)",
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
                calories = "180 kcal per serving"
            ),
            Recipe(
                id = 3,
                title = "Sajje Roti (Pearl Millet Flatbread)",
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
                calories = "210 kcal per roti"
            ),
            Recipe(
                id = 4,
                title = "Baragu Khichdi (Proso Millet Khichdi)",
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
                calories = "220 kcal per serving"
            ),
            Recipe(
                id = 5,
                title = "Oodalu Pongal (Barnyard Millet Pongal)",
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
                calories = "195 kcal per serving"
            )
        )
    }
}