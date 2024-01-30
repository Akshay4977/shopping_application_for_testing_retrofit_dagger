package com.example.shoppingapplicationfortestingretrofitdagger.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shoppingapplicationfortestingretrofitdagger.FakeShoppingRepository
import com.example.shoppingapplicationfortestingretrofitdagger.MainCoroutineRule
import com.example.shoppingapplicationfortestingretrofitdagger.getOrAwaitValueTest
import com.example.shoppingapplicationfortestingretrofitdagger.other.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ShoppingViewModelTest {

    @get:Rule
    var instance = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setUp() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun insertShoppingItemWithEmptyField_returnFalse() {

        viewModel.insertShoppingItem("name", "", "12")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertShoppingItemWithToLongName_returnFalse() {

        viewModel.insertShoppingItem(
            "insertShoppingItemWithToLongNameInsertShoppingItemWithToLongName",
            "5",
            "12"
        )

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun searchShoppingItem_returnTrue() {

        viewModel.searchForImage("name")

        val value = viewModel.images.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}