package com.example.siridhanyahub.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.siridhanyahub.R

data class MarketPrice(
    val milletName: String,
    val city: String,
    val currentPrice: String,
    val change: String,
    val trend: String,
    val isPositive: Boolean,
    val last7Days: List<Int>
)

class MandiAdapter(private val data: List<MarketPrice>) :
    RecyclerView.Adapter<MandiAdapter.MandiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MandiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mandi_price, parent, false)
        return MandiViewHolder(view)
    }

    override fun onBindViewHolder(holder: MandiViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class MandiViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val cardView: CardView = itemView.findViewById(R.id.cardView)
        private val tvMilletName: TextView = itemView.findViewById(R.id.tvMilletName)
        private val tvCity: TextView = itemView.findViewById(R.id.tvCity)
        private val tvCurrentPrice: TextView = itemView.findViewById(R.id.tvCurrentPrice)
        private val tvChange: TextView = itemView.findViewById(R.id.tvChange)
        private val tvTrend: TextView = itemView.findViewById(R.id.tvTrend)
        private val tvLast7Days: TextView = itemView.findViewById(R.id.tvLast7Days)

        fun bind(price: MarketPrice) {
            tvMilletName.text = "🌾 ${price.milletName}"
            tvCity.text = "📍 ${price.city}"
            tvCurrentPrice.text = price.currentPrice
            tvChange.text = price.change
            tvTrend.text = price.trend

            if (price.isPositive) {
                tvChange.setTextColor(Color.parseColor("#4CAF50"))
                tvTrend.setTextColor(Color.parseColor("#4CAF50"))
            } else {
                tvChange.setTextColor(Color.parseColor("#F44336"))
                tvTrend.setTextColor(Color.parseColor("#F44336"))
            }

            // Show last 7 days trend
            val days = listOf("D1", "D2", "D3", "D4", "D5", "D6", "D7")
            val trendText = days.zip(price.last7Days).joinToString(" → ") { "${it.first}: ₹${it.second}" }
            tvLast7Days.text = "📈 Last 7 Days: $trendText"
        }
    }
}