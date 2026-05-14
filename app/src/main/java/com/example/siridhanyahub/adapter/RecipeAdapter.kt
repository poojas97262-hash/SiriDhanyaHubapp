package com.example.siridhanyahub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siridhanyahub.R
import com.example.siridhanyahub.model.Recipe

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipeViewHolder(itemView: android.view.View) :
        RecyclerView.ViewHolder(itemView) {

        private val ivRecipeIcon = itemView.findViewById<android.widget.ImageView>(R.id.ivRecipeIcon)
        private val tvRecipeTitle = itemView.findViewById<android.widget.TextView>(R.id.tvRecipeTitle)
        private val tvMilletType = itemView.findViewById<android.widget.TextView>(R.id.tvMilletType)
        private val tvPrepTime = itemView.findViewById<android.widget.TextView>(R.id.tvPrepTime)
        private val tvDifficulty = itemView.findViewById<android.widget.TextView>(R.id.tvDifficulty)
        private val tvCalories = itemView.findViewById<android.widget.TextView>(R.id.tvCalories)

        fun bind(recipe: Recipe) {
            tvRecipeTitle.text = recipe.title
            tvMilletType.text = "🌾 ${recipe.milletType}"
            tvPrepTime.text = "⏱️ ${recipe.prepTime}"
            tvDifficulty.text = when(recipe.difficulty) {
                "Easy" -> "🌟 Easy"
                "Medium" -> "⭐⭐ Medium"
                "Hard" -> "⭐⭐⭐ Hard"
                else -> recipe.difficulty
            }
            tvCalories.text = "🔥 ${recipe.calories}"

            // Set icon based on millet type
            when {
                recipe.milletType.contains("Ragi", ignoreCase = true) ->
                    ivRecipeIcon.setImageResource(R.drawable.ic_ragi_recipe)
                recipe.milletType.contains("Navane", ignoreCase = true) ->
                    ivRecipeIcon.setImageResource(R.drawable.ic_navane_recipe)
                recipe.milletType.contains("Sajje", ignoreCase = true) ->
                    ivRecipeIcon.setImageResource(R.drawable.ic_sajje_recipe)
                else ->
                    ivRecipeIcon.setImageResource(R.drawable.ic_recipe_default)
            }

            // Card click listener
            itemView.setOnClickListener {
                onRecipeClick(recipe)
            }
        }
    }
}