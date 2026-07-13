package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.DessertUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uistate = MutableStateFlow(DessertUIState())
    val uiState: StateFlow<DessertUIState> = _uistate.asStateFlow()

    public fun onDessertClicked() {
        _uistate.update { dessertUIState ->
            // Update the revenue
            val newRevenue = dessertUIState.revenue + dessertUIState.currentDessertPrice
            val newDessertsSold = dessertUIState.dessertsSold + 1

            // Show the next dessert
            val dessertToShow = determineDessertToShow(dessertUIState.dessertsSold)
            val newImageId = dessertList[dessertToShow].imageId
            val newPrice = dessertList[dessertToShow].price

            dessertUIState.copy(
                currentDessertIndex = dessertToShow,
                revenue = newRevenue,
                dessertsSold = newDessertsSold,
                currentDessertImageId = newImageId,
                currentDessertPrice = newPrice
            )
        }

    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(
        dessertsSold: Int
    ): Int {
        var dessertToShow = 0
        for (dessertIndex in dessertList.indices) {
            if (dessertsSold >= dessertList[dessertIndex].startProductionAmount) {
                dessertToShow = dessertIndex
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}