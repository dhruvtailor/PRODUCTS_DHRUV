package com.example.products_dhruv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.products_dhruv.api.ApiInterface
import com.example.products_dhruv.api.RetrofitInstance
import com.example.products_dhruv.databinding.ActivityProductDetailBinding
import com.example.products_dhruv.models.Product
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    // binding
    lateinit var binding: ActivityProductDetailBinding

    // retrofit instance
    var api: ApiInterface = RetrofitInstance.retrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // add the toolbar
        setSupportActionBar(binding.myToolbar)

        var product_id = intent.getIntExtra("EXTRA_PRODUCT_ID", 0)

        lifecycleScope.launch {
            // Find a product by ID
            var resultsFromAPI: Product = api.getProductsByID(product_id)

            // update the layout
            Glide.with(this@ProductDetailActivity)
                .load(resultsFromAPI.thumbnail)
                .into(binding.imageView)
            binding.tvProductTitle.text = resultsFromAPI.title
            binding.tvProductDescription.text = resultsFromAPI.description
            binding.tvProductBrand.text = resultsFromAPI.brand
            binding.tvProductPrice.text = "$${resultsFromAPI.price}"
        }
    }
}