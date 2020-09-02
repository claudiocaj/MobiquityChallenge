package com.mobiquity.mobproducts.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobiquity.mobproducts.R
import com.mobiquity.mobproducts.presentation.viewmodel.ProductsViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}