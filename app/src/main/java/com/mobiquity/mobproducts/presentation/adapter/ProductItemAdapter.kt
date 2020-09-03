package com.mobiquity.mobproducts.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobiquity.mobproducts.BuildConfig
import com.mobiquity.mobproducts.R
import com.mobiquity.mobproducts.domain.entities.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductItemAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.item_product,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.name


        Glide.with(holder.context)
            .load(BuildConfig.API_URL + product.imageUrl.subSequence(1, product.imageUrl.length))
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.image)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.product_img
        val title: TextView = view.product_name
        val context: Context = view.context
    }
}