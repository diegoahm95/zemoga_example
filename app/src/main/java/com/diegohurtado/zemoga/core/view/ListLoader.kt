package com.diegohurtado.zemoga.core.view

interface ListLoader<T> {
    fun setItems(items: List<T>)
    fun setLoading(isLoading: Boolean)
}