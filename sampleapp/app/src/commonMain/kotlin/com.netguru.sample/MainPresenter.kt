package com.netguru.sample
import com.netguru.multiplatformstorage.MultiplatformStorage

class MainPresenter {

    private var view: MainView? = null

    private val toDoList = mutableListOf<String>()

    private val storage by lazy {
        MultiplatformStorage()
    }

    fun attachView(view: MainView) {
        this.view = view
        getToDoList()
    }

    fun detachView() {
        this.view = null
    }

    fun addNewToDoElement(item: String) {
        toDoList.add(item)
        storage.putString(KEY_LIST, toDoList.joinToString(separator = STRING_LIST_SEPARATOR))
        storage.getAll()

        with(view!!) {
            showElementAddedInfo()
            showToDoList(toDoList)
        }
    }

    private fun getToDoList() {
        if (storage.contains(KEY_LIST)) {
            toDoList.addAll(storage.getString(KEY_LIST, "")!!.split(STRING_LIST_SEPARATOR))
        }
        view!!.showToDoList(toDoList)
    }

    companion object {
        private const val KEY_LIST = "key:list"
        private const val STRING_LIST_SEPARATOR = ","
    }
}
