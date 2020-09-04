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
import io.reactivex.subjects.PublishSubject

class ProductItemAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

    val itemClick: PublishSubject<Product> = PublishSubject.create<Product>()
    val transitionClickItem: PublishSubject<ItemProductBinding> =
        PublishSubject.create<ItemProductBinding>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        holder.binding.productName.text = product.name

        Glide.with(holder.context)
            .load(BuildConfig.API_URL + product.imageUrl.subSequence(1, product.imageUrl.length))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_no_photo)
            .into(holder.binding.productImg)

        holder.binding.root.setOnClickListener {
            itemClick.onNext(product)
            transitionClickItem.onNext(holder.binding)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemProductBinding.bind(view)
        val context: Context = view.context
    }
}