package com.mobiquity.mobproducts.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.mobiquity.mobproducts.ProductsApplicaton
import com.mobiquity.mobproducts.R
import com.mobiquity.mobproducts.domain.entities.Category
import com.mobiquity.mobproducts.presentation.adapter.ProductItemAdapter
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import java.util.*
import javax.inject.Inject

class ProductsFragment : Fragment() {
    @Inject
    lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
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
        product_list.apply {
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
        for (category in categories) {
            categories_tab.addTab(categories_tab.newTab().apply {
                text = category.name
            })
        }

        categories_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                product_list.adapter = ProductItemAdapter(
                    categories[tab!!.position].products
                )
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
                //do Nothing
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                //do Nothing
            }
        })

        product_list.adapter = ProductItemAdapter(categories[0].products)

    }
}