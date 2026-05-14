package com.example.siridhanyahub

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DirectBuyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct_buy)

        // Set status bar color
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = androidx.core.content.ContextCompat.getColor(this, R.color.green_primary)
        }

        // Back button
        val btnBack: ImageView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        val spinnerMillet: Spinner = findViewById(R.id.spinnerMillet)
        val spinnerQuantity: Spinner = findViewById(R.id.spinnerQuantity)
        val tvTotalPrice: TextView = findViewById(R.id.tvTotalPrice)
        val btnPlaceOrder: Button = findViewById(R.id.btnPlaceOrder)
        val tvFPOInfo: TextView = findViewById(R.id.tvFPOInfo)

        val millets = arrayOf("Select Millet", "Ragi (Finger Millet)", "Navane (Foxtail Millet)", "Sajje (Pearl Millet)", "Baragu (Proso Millet)", "Oodalu (Barnyard Millet)")
        val prices = mapOf(
            "Ragi (Finger Millet)" to 45,
            "Navane (Foxtail Millet)" to 60,
            "Sajje (Pearl Millet)" to 55,
            "Baragu (Proso Millet)" to 50,
            "Oodalu (Barnyard Millet)" to 65
        )
        val quantities = arrayOf("1 kg", "2 kg", "3 kg", "4 kg", "5 kg", "10 kg")
        val quantityMultiplier = mapOf("1 kg" to 1, "2 kg" to 2, "3 kg" to 3, "4 kg" to 4, "5 kg" to 5, "10 kg" to 10)

        val milletAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, millets)
        milletAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMillet.adapter = milletAdapter

        val quantityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, quantities)
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerQuantity.adapter = quantityAdapter

        // FPO Information
        tvFPOInfo.text = """
            🤝 Connect with Local Farmer Producer Organization (FPO)
            
            📍 Karnataka Millets FPO - Davangere
            • Direct from farmers, no middlemen
            • Fair trade certified
            • Organic farming practices
            • Support local farming communities
            
            📞 Contact: 1800-XXX-XXXX
            📧 Email: fpo@karnatakamillets.com
            
            💚 Your purchase directly supports 500+ farmers!
        """.trimIndent()

        var selectedMillet = ""
        var selectedQuantity = 1

        spinnerMillet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                selectedMillet = millets[position]
                updatePrice(selectedMillet, selectedQuantity, prices, tvTotalPrice)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerQuantity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                selectedQuantity = quantityMultiplier[quantities[position]] ?: 1
                updatePrice(selectedMillet, selectedQuantity, prices, tvTotalPrice)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        btnPlaceOrder.setOnClickListener {
            if (selectedMillet == "Select Millet") {
                Toast.makeText(this, "Please select a millet", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "✅ Order placed! FPO will contact you within 24 hours.\nThank you for supporting farmers!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updatePrice(millet: String, quantity: Int, prices: Map<String, Int>, tvTotalPrice: TextView) {
        val price = prices[millet] ?: 0
        val total = price * quantity
        if (millet != "Select Millet") {
            tvTotalPrice.text = "💰 Total Price: ₹$total (${quantity}kg @ ₹$price/kg)"
        } else {
            tvTotalPrice.text = "💰 Total Price: ₹0"
        }
    }
}