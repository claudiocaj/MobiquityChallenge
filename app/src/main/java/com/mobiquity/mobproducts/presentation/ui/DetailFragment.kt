package com.mobiquity.mobproducts.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mobiquity.mobproducts.BuildConfig
import com.mobiquity.mobproducts.ProductsApplicaton
import com.mobiquity.mobproducts.R
import com.mobiquity.mobproducts.databinding.FragmentDetailBinding
import com.mobiquity.mobproducts.domain.entities.Product
import com.mobiquity.mobproducts.extensions.currencyFormat

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity().application as ProductsApplicaton).appComponent.inject(this)
        getProductData()
    }

    private fun getProductData() {
        arguments?.let {
            DetailFragmentArgs.fromBundle(it).product
        }.also {
            binding.productName.text = it?.name
            binding.price.text =
                it?.saleDescription?.amount?.currencyFormat(it.saleDescription.currency)
            Glide.with(requireContext())
                .load(
                    BuildConfig.API_URL + it?.imageUrl?.subSequence(
                        1,
                        it.imageUrl.length
                    )
                ).placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground).into(binding.productImg)
        }
    }
}