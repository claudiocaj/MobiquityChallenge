package com.mobiquity.mobproducts.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.mobiquity.mobproducts.*
import com.mobiquity.mobproducts.databinding.FragmentDetailBinding
import com.mobiquity.mobproducts.domain.entities.Product
import com.mobiquity.mobproducts.extensions.currencyFormat
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    
    lateinit var viewModel: ProductsViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders
            .of(requireActivity(), factory)
            .get(ProductsViewModel::class.java)

        viewModel.getChosenProduct().observe(viewLifecycleOwner, Observer { product ->
            bindProductData(product)
        })
    }

    private fun bindProductData(product: Product) {
        binding.productName.text = product.name
        binding.price.text =
            product.saleDescription.amount.currencyFormat(product.saleDescription.currency)

        Glide.with(requireContext())
            .load(
                BuildConfig.API_URL + product.imageUrl.subSequence(
                    1,
                    product.imageUrl.length
                )
            ).placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground).into(binding.productImg)
    }

}