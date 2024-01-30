package com.example.shoppingapplicationfortestingretrofitdagger

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.shoppingapplicationfortestingretrofitdagger.data.local.ShoppingDao
import com.example.shoppingapplicationfortestingretrofitdagger.data.local.ShoppingItem
import com.example.shoppingapplicationfortestingretrofitdagger.data.local.ShoppingItemDatabase
import com.example.shoppingapplicationfortestingretrofitdagger.ui.ShoppingFragment
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ShoppingDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: ShoppingItemDatabase
    lateinit var shoppingDao: ShoppingDao


    @Before
    fun setup() {
        hiltRule.inject()
        shoppingDao = database.shoppingDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun launchFragment(){
        launchFragmentInHiltContainer<ShoppingFragment> {

        }
    }


    @Test
    fun insertShoppingItem() = runBlockingTest {
        var shoppingItem = ShoppingItem("name", 10, 2f, "")
        shoppingDao.insertShoppingItem(shoppingItem)


        val allData = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allData).doesNotContain(shoppingItem)

        var shoppingItem1 = ShoppingItem("name", 10, 2f, "", 1)


        shoppingDao.deleteShoppingItem(shoppingItem1)

        val allData2 = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allData2).doesNotContain(shoppingItem1)
    }
}