package com.example.siridhanyahub

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siridhanyahub.adapter.MilletAdapter
import com.example.siridhanyahub.model.Millet

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.green_primary)
        }

        // Navigation Buttons
        val btnMandiWatch: Button = findViewById(R.id.btnMandiWatch)
        val btnRecipeLab: Button = findViewById(R.id.btnRecipeLab)
        val btnHealthBenefits: Button = findViewById(R.id.btnHealthBenefits)
        val btnDirectBuy: Button = findViewById(R.id.btnDirectBuy)

        btnMandiWatch.setOnClickListener {
            startActivity(Intent(this, MandiWatchActivity::class.java))
        }

        btnRecipeLab.setOnClickListener {
            startActivity(Intent(this, RecipeLabActivity::class.java))
        }

        btnHealthBenefits.setOnClickListener {
            startActivity(Intent(this, HealthBenefitsActivity::class.java))
        }

        btnDirectBuy.setOnClickListener {
            startActivity(Intent(this, DirectBuyActivity::class.java))
        }

        // Setup RecyclerView for Millet Products
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val milletList = listOf(
            Millet(
                name = "Ragi (Finger Millet)",
                origin = "Bengaluru, Karnataka",
                price = "₹45/kg",
                benefit = "💪 Good for Calcium & Bone Health",
                nutrition = "Calcium: 344mg | Iron: 3.9mg"
            ),
            Millet(
                name = "Navane (Foxtail Millet)",
                origin = "Davangere, Karnataka",
                price = "₹60/kg",
                benefit = "🩺 Good for Diabetes Control",
                nutrition = "Fiber: 6.7g | Protein: 12.3g"
            ),
            Millet(
                name = "Sajje (Pearl Millet)",
                origin = "Mysore, Karnataka",
                price = "₹55/kg",
                benefit = "🌾 Rich in Fiber & Iron",
                nutrition = "Iron: 8mg | Fiber: 11g"
            ),
            Millet(
                name = "Baragu (Proso Millet)",
                origin = "Hassan, Karnataka",
                price = "₹50/kg",
                benefit = "⚡ Boosts Energy",
                nutrition = "Protein: 12.5g | B-Complex Vitamins"
            ),
            Millet(
                name = "Oodalu (Barnyard Millet)",
                origin = "Shimoga, Karnataka",
                price = "₹65/kg",
                benefit = "❤️ Good for Heart Health",
                nutrition = "Magnesium: 114mg | Phosphorus: 280mg"
            )
        )

        recyclerView.adapter = MilletAdapter(milletList)
    }
}