package com.example.products_dhruv.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.products_dhruv.R
import com.example.products_dhruv.models.Product

class ProductDataAdapter(var productListData:List<Product>, var goToProductDetail: (Int)->Unit) : RecyclerView.Adapter<ProductDataAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
    }

    // tell the function which layout file to use for each row of the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.product_row_layout, parent, false)
        return MyViewHolder(view)
    }

    // how many items are in the list
    override fun getItemCount(): Int {
        return productListData.size
    }

    // In a single row, what data goes in the layout
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currProduct: Product = productListData.get(position)

        val clMain = holder.itemView.findViewById<ConstraintLayout>(R.id.main)
        val tvRow1 = holder.itemView.findViewById<TextView>(R.id.tvRow1)
        val tvRow2 = holder.itemView.findViewById<TextView>(R.id.tvRow2)

        tvRow1.text = "${currProduct.title}"
        tvRow2.text = "Price: $${currProduct.price}"

        clMain.setOnClickListener {
            goToProductDetail(position)
        }
    }
}