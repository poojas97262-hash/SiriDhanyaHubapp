package com.example.siridhanyahub.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.siridhanyahub.RecipeDetailActivity
import com.example.siridhanyahub.R
import com.example.siridhanyahub.model.Recipe

class RecipeLabAdapter(
    private var recipes: List<Recipe>,
    private val savedRecipes: Set<String>,
    private val onSaveToggle: (Recipe, Boolean) -> Unit
) : RecyclerView.Adapter<RecipeLabAdapter.RecipeViewHolder>() {

    private var filteredRecipes: List<Recipe> = recipes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_lab, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(filteredRecipes[position])
    }

    override fun getItemCount(): Int = filteredRecipes.size

    fun filter(query: String) {
        filteredRecipes = if (query.isEmpty()) {
            recipes
        } else {
            recipes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.milletType.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(itemView: android.view.View) :
        RecyclerView.ViewHolder(itemView) {

        private val cardView: CardView = itemView.findViewById(R.id.cardView)
        private val ivRecipeIcon: ImageView = itemView.findViewById(R.id.ivRecipeIcon)
        private val tvRecipeTitle: TextView = itemView.findViewById(R.id.tvRecipeTitle)
        private val tvMilletType: TextView = itemView.findViewById(R.id.tvMilletType)
        private val tvPrepTime: TextView = itemView.findViewById(R.id.tvPrepTime)
        private val tvDifficulty: TextView = itemView.findViewById(R.id.tvDifficulty)
        private val tvCalories: TextView = itemView.findViewById(R.id.tvCalories)
        private val btnSave: Button = itemView.findViewById(R.id.btnSave)

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

            // Set icon
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

            // Save button state
            val isSaved = savedRecipes.contains(recipe.title)
            btnSave.text = if (isSaved) "❤️ Saved" else "🤍 Save Recipe"
            btnSave.setBackgroundColor(
                if (isSaved) itemView.context.getColor(R.color.green_primary)
                else itemView.context.getColor(R.color.orange_accent)
            )

            btnSave.setOnClickListener {
                val newSavedState = !savedRecipes.contains(recipe.title)
                onSaveToggle(recipe, newSavedState)
                btnSave.text = if (newSavedState) "❤️ Saved" else "🤍 Save Recipe"
                btnSave.setBackgroundColor(
                    if (newSavedState) itemView.context.getColor(R.color.green_primary)
                    else itemView.context.getColor(R.color.orange_accent)
                )
            }

            cardView.setOnClickListener {
                val intent = Intent(itemView.context, RecipeDetailActivity::class.java)
                intent.putExtra("recipe", recipe)
                itemView.context.startActivity(intent)
            }
        }
    }
}