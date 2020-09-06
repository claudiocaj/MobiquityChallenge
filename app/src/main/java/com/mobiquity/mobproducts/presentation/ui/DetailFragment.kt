package com.mobiquity.mobproducts.presentation.ui

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobiquity.mobproducts.databinding.FragmentDetailBinding
import com.mobiquity.mobproducts.domain.entities.Product
import com.mobiquity.mobproducts.extensions.currencyFormat
import com.mobiquity.mobproducts.extensions.getImageRequestFormat
import com.mobiquity.mobproducts.extensions.loadWithoutPlaceHolder
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
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

        viewModel = ViewModelProvider(requireActivity(), factory).get(ProductsViewModel::class.java)
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.getChosenProduct().observe(viewLifecycleOwner, Observer { product ->
            bindProductData(product)
        })
    }

    private fun bindProductData(product: Product) {
        binding.productDetailName.apply {
            transitionName = product.name
            text = product.name
        }

        binding.price.text =
            product.saleDescription.amount.currencyFormat(product.saleDescription.currency)

        binding.productImg.apply {
            transitionName = product.imageUrl
        }.loadWithoutPlaceHolder(
            product.imageUrl.getImageRequestFormat()
        )
    }

}

