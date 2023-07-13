package com.example.suitmedia.presentation.screen3


import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.suitmedia.data.repository.UserPagingSource
import com.example.suitmedia.data.retrofit.ApiService
import com.example.suitmedia.di.Injection


class ListViewModel (
    private val apiService: ApiService
) : ViewModel(){
    val loading = MutableLiveData<Boolean>()
    val users = Pager(PagingConfig(pageSize = 1, initialLoadSize = 1)) {
        UserPagingSource(apiService)

    }.flow.cachedIn(viewModelScope)

}

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(Injection.provideApiService()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
