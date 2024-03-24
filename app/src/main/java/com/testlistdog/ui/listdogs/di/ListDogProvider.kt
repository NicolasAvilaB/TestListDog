package com.testlistdog.ui.listdogs.di

import androidx.fragment.app.FragmentActivity
import com.testlistdog.data.ListDogRepository
import com.testlistdog.data.remote.ListDogRemoteImpl
import com.testlistdog.data.remote.retrofit.ListDogWebService
import com.testlistdog.data.remote.retrofit.Retrofit
import com.testlistdog.data.source.ListDogSourceRemote
import com.testlistdog.presentation.listdogs.ListDogViewModel

internal class ListDogProvider() {

    internal fun getViewModel(
        activity: FragmentActivity
    ): ListDogViewModel = getFactoryViewModel(
        activity = activity,
        repository = getListDogRepository()
    )

    private fun getListDogRepository():
            ListDogRepository = ListDogRepository(remote = getRemoteImpl())

    private fun getRemoteImpl():
            ListDogSourceRemote = ListDogRemoteImpl(webService = listDogWebService)

    private val listDogWebService: ListDogWebService
    init {
        val retrofit = Retrofit.provideRetrofit()
        listDogWebService = retrofit.create(ListDogWebService::class.java)
    }
}