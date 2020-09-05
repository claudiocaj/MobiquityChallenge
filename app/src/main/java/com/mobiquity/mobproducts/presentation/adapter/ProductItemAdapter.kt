package com.mobiquity.mobproducts.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobiquity.mobproducts.BuildConfig
import com.mobiquity.mobproducts.R
import com.mobiquity.mobproducts.databinding.ItemProductBinding
import com.mobiquity.mobproducts.domain.entities.Product
import com.mobiquity.mobproducts.extensions.getImageRequestFormat
import com.mobiquity.mobproducts.extensions.load

class ProductItemAdapter(val onItemClick: (product: Product, binding: ItemProductBinding) -> Unit) :
    RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {
    var products = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setProductsItems(products: List<Product>) {
        this.products.apply {
            clear()
            addAll(products)
        }.run {
            notifyDataSetChanged()
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        holder.binding.productName.text = product.name

        holder.binding.productImg.load(
            product.imageUrl.getImageRequestFormat()
        )

        holder.binding.root.setOnClickListener {
            onItemClick.invoke(product, holder.binding)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemProductBinding.bind(view)
    }
}