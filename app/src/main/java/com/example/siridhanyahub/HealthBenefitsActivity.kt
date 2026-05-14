package com.example.siridhanyahub

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HealthBenefitsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_benefits)

        // Set status bar color
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = androidx.core.content.ContextCompat.getColor(this, R.color.green_primary)
        }

        // Back button
        val btnBack: ImageView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        val tvBenefits: TextView = findViewById(R.id.tvBenefits)
        tvBenefits.text = """
            🌿 Health Benefits of Millets 🌿
            
            🔬 Ragi (Finger Millet)
            • Highest calcium content among all millets
            • 344mg calcium per 100g - 10x more than rice
            • Great for bone health and preventing osteoporosis
            • Helps in managing diabetes due to low glycemic index
            
            🩺 Navane (Foxtail Millet)
            • ★ WHY GOOD FOR DIABETICS ★
            • Low glycemic index (50-55) - releases sugar slowly
            • Rich in dietary fiber (6.7g/100g)
            • Improves insulin sensitivity
            • Reduces cholesterol levels
            • Contains healthy carbs that don't spike blood sugar
            
            ❤️ Sajje (Pearl Millet)
            • Rich in iron (8mg/100g) - prevents anemia
            • High in magnesium - good for heart health
            • Contains antioxidants that fight free radicals
            • Gluten-free - great for celiac patients
            
            ⚡ Baragu (Proso Millet)
            • Boosts energy levels
            • Rich in B-complex vitamins
            • Good for nervous system
            
            🌾 Oodalu (Barnyard Millet)
            • Lowest calorie content among millets
            • Good for weight management
            • Rich in fiber - keeps you full longer
            
            🌍 Climate Action Impact:
            • Requires 70% less water than paddy rice
            • Grows in dry conditions with minimal irrigation
            • Drought-resistant crop
            • Maturing in just 60-90 days
            
            💰 Farmer Prosperity:
            • Farmers get 40-50% better prices than paddy
            • Multiple market options through mandi integration
            • Direct connection with FPOs eliminates middlemen
            • Sustainable income throughout the year
            
            🌱 Nutrition Security:
            • Fights malnutrition with essential nutrients
            • Prevents lifestyle diseases
            • Complete protein profile
            • Rich in essential amino acids
        """.trimIndent()
    }
}