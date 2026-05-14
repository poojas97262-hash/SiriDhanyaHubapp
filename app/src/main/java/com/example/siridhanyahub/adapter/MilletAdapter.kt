package com.example.siridhanyahub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.siridhanyahub.R
import com.example.siridhanyahub.model.Millet

class MilletAdapter(private val milletList: List<Millet>) :
    RecyclerView.Adapter<MilletAdapter.MilletViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilletViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_millet, parent, false)
        return MilletViewHolder(view)
    }

    override fun onBindViewHolder(holder: MilletViewHolder, position: Int) {
        holder.bind(milletList[position])
    }

    override fun getItemCount(): Int = milletList.size

    inner class MilletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.cardView)
        private val ivMillet: ImageView = itemView.findViewById(R.id.ivMillet)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvOrigin: TextView = itemView.findViewById(R.id.tvOrigin)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val tvBenefit: TextView = itemView.findViewById(R.id.tvBenefit)
        private val tvNutrition: TextView = itemView.findViewById(R.id.tvNutrition)
        private val btnAddToCart: Button = itemView.findViewById(R.id.btnAddToCart)
        private val btnViewDetails: Button = itemView.findViewById(R.id.btnViewDetails)

        fun bind(millet: Millet) {
            tvName.text = millet.name
            tvOrigin.text = "📍 ${millet.origin}"
            tvPrice.text = millet.price
            tvBenefit.text = millet.benefit
            tvNutrition.text = millet.nutrition

            // Set different icons based on millet type
            when {
                millet.name.contains("Ragi", ignoreCase = true) ->
                    ivMillet.setImageResource(R.drawable.ic_millet_ragi)
                millet.name.contains("Navane", ignoreCase = true) ->
                    ivMillet.setImageResource(R.drawable.ic_millet_navane)
                millet.name.contains("Sajje", ignoreCase = true) ->
                    ivMillet.setImageResource(R.drawable.ic_millet_sajje)
                else ->
                    ivMillet.setImageResource(R.drawable.ic_millet_default)
            }

            // Add to cart button click
            btnAddToCart.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "✓ ${millet.name} added to cart!\nPrice: ${millet.price}",
                    Toast.LENGTH_LONG
                ).show()
            }

            // View details button click - Show detailed dialog
            btnViewDetails.setOnClickListener {
                showDetailDialog(millet)
            }

            // Card click animation
            cardView.setOnClickListener {
                cardView.animate().apply {
                    duration = 100
                    scaleXBy(0.02f)
                    scaleYBy(0.02f)
                    withEndAction {
                        cardView.animate().apply {
                            duration = 100
                            scaleXBy(-0.02f)
                            scaleYBy(-0.02f)
                        }.start()
                    }
                }.start()
            }
        }

        private fun showDetailDialog(millet: Millet) {
            AlertDialog.Builder(itemView.context)
                .setTitle("🌾 ${millet.name}")
                .setMessage(
                    """
                    📍 Origin: ${millet.origin}
                    💰 Price: ${millet.price}
                    
                    ✨ Benefits:
                    ${millet.benefit}
                    
                    📊 Nutrition:
                    ${millet.nutrition}
                    
                    🌟 Premium quality millet directly from farmers.
                    """.trimIndent()
                )
                .setPositiveButton("Add to Cart") { _, _ ->
                    Toast.makeText(
                        itemView.context,
                        "${millet.name} added to cart!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton("Close", null)
                .show()
        }
    }
}