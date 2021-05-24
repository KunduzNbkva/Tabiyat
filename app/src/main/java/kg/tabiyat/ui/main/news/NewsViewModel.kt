package kg.tabiyat.ui.main.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.NewsData
import kg.tabiyat.data.model.NewsModel
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel (var repository: NewsRepository): ViewModel() {
    var newsList = MutableLiveData<List<NewsData>>()
    private var page = 0
    private var hasNext = true

    fun getNewsList() {
        if (!hasNext) return
        viewModelScope.launch {
            repository.getNewsList(page).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        val list = it.data!!.news!!.data!!
                        newsList.postValue(list)
                        page++
                        if (list.size < 20) hasNext = false
                    }
                    Status.ERROR -> {
                        error(it.message.toString())
                    }
                    Status.LOADING -> null
                }
            }
        }
    }


    fun resetPage() {
        page = 0
    }

}