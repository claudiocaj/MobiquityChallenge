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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.mobiquity.mobproducts.R
import com.mobiquity.mobproducts.databinding.FragmentProductsBinding
import com.mobiquity.mobproducts.domain.entities.Category
import com.mobiquity.mobproducts.domain.entities.Product
import com.mobiquity.mobproducts.extensions.visible
import com.mobiquity.mobproducts.presentation.adapter.ProductItemAdapter
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ProductsFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: ProductsViewModel
    private lateinit var binding: FragmentProductsBinding
    private lateinit var productAdapter: ProductItemAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders
            .of(requireActivity(), factory)
            .get(ProductsViewModel::class.java)

        setUpProductList()
        subscribeUi()
        viewModel.fetchProducts()
    }

    private fun subscribeUi() {
        with(viewModel) {
            getProducts().observe(viewLifecycleOwner, Observer {
                it.onSuccess { categories ->
                    bindCategoriesTabs(categories)
                }

                it.onFailure {
                    Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_SHORT)
                        .show()
                }
            })

            isLoadingMutable.observe(viewLifecycleOwner, Observer {
                toggleLoadingProgress(it)
            })

        }
    }

    private fun bindCategoriesTabs(categories: List<Category>) {
        val tabs = binding.categoriesTab

        tabs.removeAllTabs()

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

    private fun setUpProductList() {
        productAdapter = ProductItemAdapter { product, binding ->
            viewModel.setChosenProduct(product)
            goToProductsDetail()
        }

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

            adapter = productAdapter
        }
    }


    private fun bindCategoryProductList(products: List<Product>) {
        productAdapter.setProductsItems(products)
    }

    private fun toggleLoadingProgress(isLoading: Boolean) {
        binding.progressLoading.visible(isLoading)
    }

    private fun goToProductsDetail() {
        /* val extras = FragmentNavigatorExtras(
             binding.productImg to "product transition"
         )*/
        val action = ProductsFragmentDirections.actionProductsFragmentToDetailFragment()
        findNavController().navigate(action)
    }
}