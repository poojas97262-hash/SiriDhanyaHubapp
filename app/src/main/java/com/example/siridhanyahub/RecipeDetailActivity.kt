package com.example.siridhanyahub

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.siridhanyahub.model.Recipe

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        // Set status bar color
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = androidx.core.content.ContextCompat.getColor(this, R.color.green_primary)
        }

        // Get recipe from intent
        val recipe = intent.getSerializableExtra("recipe") as? Recipe

        recipe?.let {
            displayRecipeDetails(it)
        }

        // Set back button
        val btnBack: ImageView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun displayRecipeDetails(recipe: Recipe) {
        val tvRecipeTitle: TextView = findViewById(R.id.tvRecipeTitle)
        val tvMilletType: TextView = findViewById(R.id.tvMilletType)
        val tvPrepTime: TextView = findViewById(R.id.tvPrepTime)
        val tvDifficulty: TextView = findViewById(R.id.tvDifficulty)
        val tvCalories: TextView = findViewById(R.id.tvCalories)
        val tvIngredients: TextView = findViewById(R.id.tvIngredients)
        val tvInstructions: TextView = findViewById(R.id.tvInstructions)

        tvRecipeTitle.text = recipe.title
        tvMilletType.text = "🌾 Millet: ${recipe.milletType}"
        tvPrepTime.text = "⏱️ Preparation Time: ${recipe.prepTime}"
        tvDifficulty.text = "📊 Difficulty Level: ${recipe.difficulty}"
        tvCalories.text = "🔥 Calories: ${recipe.calories}"

        // Display ingredients
        val ingredientsText = recipe.ingredients.joinToString("\n• ") { "• $it" }
        tvIngredients.text = "📝 Ingredients:\n$ingredientsText"

        // Display instructions
        val instructionsText = recipe.instructions.mapIndexed { index, instruction ->
            "${index + 1}. $instruction"
        }.joinToString("\n\n")
        tvInstructions.text = "👨‍🍳 Instructions:\n\n$instructionsText"
    }
}