package com.mobiquity.mobproducts.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mobiquity.mobproducts.ProductsApplicaton
import com.mobiquity.mobproducts.R
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}