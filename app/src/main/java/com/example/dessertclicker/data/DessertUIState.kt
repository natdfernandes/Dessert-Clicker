package com.example.dessertclicker.data

import com.example.dessertclicker.data.Datasource.dessertList

data class DessertUIState(
  var revenue: Int = 0,
    var dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    var currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    var currentDessertImageId: Int = dessertList[currentDessertIndex].imageId
)
