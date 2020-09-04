package com.mobiquity.mobproducts.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.mobiquity.mobproducts.ProductsApplicaton
import com.mobiquity.mobproducts.R
import com.mobiquity.mobproducts.databinding.FragmentProductsBinding
import com.mobiquity.mobproducts.domain.entities.Category
import com.mobiquity.mobproducts.domain.entities.Product
import com.mobiquity.mobproducts.presentation.adapter.ProductItemAdapter
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import javax.inject.Inject

class ProductsFragment : Fragment() {
    @Inject
    lateinit var viewModel: ProductsViewModel

    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity().application as ProductsApplicaton).appComponent.inject(this)
        bindProductList()
        subscribeUi()
        viewModel.fetchProducts()
    }

    private fun subscribeUi() {
        viewModel.getProducts().observe(viewLifecycleOwner, Observer {
            it.onSuccess { categories ->
                handleCategories(categories)
            }
        })
    }

    private fun bindProductList() {
        binding.productList.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun handleCategories(categories: List<Category>) {
        val tabs = binding.categoriesTab
        for (category in categories) {
            tabs.addTab(tabs.newTab().apply {
                text = category.name
            })
        }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                bindCategoryProductList(categories[tab!!.position].products)
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
                //do Nothing
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                //do Nothing
            }
        })
        bindCategoryProductList(categories[0].products)
    }

    private fun bindCategoryProductList(products: List<Product>) {
        binding.productList.adapter = ProductItemAdapter(products).also {
            it.itemClick.subscribe { product ->
                viewModel.setChosenProduct(product)
                goToProductsDetail(product)

            }
        }
    }

    private fun goToProductsDetail(product: Product) {
        val action = ProductsFragmentDirections.actionProductsFragmentToDetailFragment(product)
        findNavController().navigate(action)
    }
}