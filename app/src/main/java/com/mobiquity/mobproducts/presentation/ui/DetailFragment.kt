package com.mobiquity.mobproducts.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mobiquity.mobproducts.ProductsApplicaton
import com.mobiquity.mobproducts.R
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity().application as ProductsApplicaton).appComponent.inject(this)
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.getChosenProduct().observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        })
    }
}