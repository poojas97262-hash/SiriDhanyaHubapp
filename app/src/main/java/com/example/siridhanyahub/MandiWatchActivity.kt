package com.example.siridhanyahub

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siridhanyahub.adapter.MandiAdapter
import com.example.siridhanyahub.adapter.MarketPrice

class MandiWatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mandi_watch)

        // Set status bar color
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = androidx.core.content.ContextCompat.getColor(this, R.color.green_primary)
        }

        // Back button
        val btnBack: ImageView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        setupRecyclerView()
        setupMarketSummary()
    }

    private fun setupMarketSummary() {
        val tvMarketSummary: TextView = findViewById(R.id.tvMarketSummary)
        tvMarketSummary.text = """
            📊 Market Summary (Last 7 Days)
            
            📍 Bengaluru: Ragi ↑12% | Navane ↑5% | Sajje ↓2%
            📍 Davangere: Ragi ↑8% | Navane ↑15% | Sajje ↑3%
            📍 Mysore: Ragi ↑6% | Navane ↑10% | Sajje ↑1%
            📍 Hassan: Ragi ↑9% | Navane ↑7% | Sajje ↓1%
            
            💡 Tip: Navane prices rising due to increased demand!
        """.trimIndent()
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewMandi)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val marketData = listOf(
            MarketPrice("Ragi", "Bengaluru", "₹48/kg", "+12%", "↑", true, getLast7DaysPrices(45, 48, 46, 49, 47, 50, 48)),
            MarketPrice("Ragi", "Davangere", "₹52/kg", "+8%", "↑", true, getLast7DaysPrices(48, 49, 50, 51, 50, 52, 52)),
            MarketPrice("Ragi", "Mysore", "₹50/kg", "+6%", "↑", true, getLast7DaysPrices(47, 48, 49, 48, 50, 49, 50)),
            MarketPrice("Navane", "Bengaluru", "₹65/kg", "+5%", "↑", true, getLast7DaysPrices(62, 63, 64, 63, 65, 64, 65)),
            MarketPrice("Navane", "Davangere", "₹72/kg", "+15%", "↑", true, getLast7DaysPrices(62, 64, 66, 68, 70, 71, 72)),
            MarketPrice("Navane", "Mysore", "₹68/kg", "+10%", "↑", true, getLast7DaysPrices(62, 63, 65, 66, 67, 68, 68)),
            MarketPrice("Sajje", "Bengaluru", "₹53/kg", "-2%", "↓", false, getLast7DaysPrices(55, 54, 54, 53, 53, 52, 53)),
            MarketPrice("Sajje", "Davangere", "₹58/kg", "+3%", "↑", true, getLast7DaysPrices(56, 57, 57, 58, 57, 58, 58)),
            MarketPrice("Sajje", "Mysore", "₹56/kg", "+1%", "↑", true, getLast7DaysPrices(55, 55, 56, 56, 55, 56, 56))
        )

        recyclerView.adapter = MandiAdapter(marketData)
    }

    private fun getLast7DaysPrices(vararg prices: Int): List<Int> {
        return prices.toList()
    }
}