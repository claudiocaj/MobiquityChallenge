package com.mobiquity.mobproducts.helper

import com.mobiquity.mobproducts.domain.entities.Category
import com.mobiquity.mobproducts.domain.entities.Price
import com.mobiquity.mobproducts.domain.entities.Product

object DummyConstants {
    val DUMMY_SALE_DESCRIPTION = Price("2.5", "EUR")
    val DUMMY_PRODUCTS_1 = listOf<Product>(
        Product("1", "Product1", "product1Url",
            DUMMY_SALE_DESCRIPTION
        ),
        Product("2", "Product2", "product1Url2",
            DUMMY_SALE_DESCRIPTION
        )
    )

    val DUMMY_PRODUCTS_2 = listOf<Product>(
        Product("3", "Product3", "product1Url3",
            DUMMY_SALE_DESCRIPTION
        ),
        Product("4", "Product4", "product1Url4",
            DUMMY_SALE_DESCRIPTION
        )
    )


    val DUMMY_CATEGORIES = listOf<Category>(
        Category("1", "category1",
            DUMMY_PRODUCTS_1
        ),
        Category("2", "category2",
            DUMMY_PRODUCTS_2
        )
    )
}