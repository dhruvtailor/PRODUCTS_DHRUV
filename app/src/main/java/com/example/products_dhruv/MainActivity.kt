package com.example.products_dhruv

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.products_dhruv.adapters.ProductDataAdapter
import com.example.products_dhruv.api.ApiInterface
import com.example.products_dhruv.api.RetrofitInstance
import com.example.products_dhruv.databinding.ActivityMainBinding
import com.example.products_dhruv.models.Product
import com.example.products_dhruv.models.ProductsResponseObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    // binding
    lateinit var binding: ActivityMainBinding

    // adapter and data source
    lateinit var adapter: ProductDataAdapter
    var productsList:MutableList<Product> = mutableListOf()

    // retrofit instance
    var api: ApiInterface = RetrofitInstance.retrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // add the toolbar
        setSupportActionBar(binding.myToolbar)

        // initialize the adapter
        adapter = ProductDataAdapter(productsList, goToProductDetail)

        // recyclerview configuration
        binding.rvProductList.adapter = adapter
        binding.rvProductList.layoutManager = LinearLayoutManager(this)
        binding.rvProductList.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    // options menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_smartphones -> {
                getProductsByCategory("smartphones")
                true
            }
            R.id.mi_laptops -> {
                getProductsByCategory("laptops")
                true
            }
            R.id.mi_skincare -> {
                getProductsByCategory("skincare")
                true
            }
            R.id.mi_groceries -> {
                getProductsByCategory("groceries")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getProductsByCategory(category: String) {
        lifecycleScope.launch {
            // Find a product by category
            var responseFromAPI: ProductsResponseObject = api.getProductsByCategory(category)
            var resultsFromAPI: List<Product> = responseFromAPI.products

            // update the adapter
            productsList.clear()
            productsList.addAll(resultsFromAPI.toMutableList())
            adapter.notifyDataSetChanged()

        }
    }

    val goToProductDetail = {
            rowNumber:Int ->
        val intent = Intent(this@MainActivity, ProductDetailActivity::class.java)
        intent.putExtra("EXTRA_PRODUCT_ID", productsList.get(rowNumber).id)
        startActivity(intent)
    }
}