package com.diegohurtado.zemoga.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.diegohurtado.zemoga.core.exceptions.MissingLayoutIdException

abstract class BaseFragment: Fragment() {

    @get:LayoutRes
    abstract val layoutId: Int
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return tryToInflate(inflater, container)
    }

    private fun tryToInflate(inflater: LayoutInflater, container: ViewGroup?): View {
        if (layoutId == 0){
            throw MissingLayoutIdException(requireContext())
        } else {
            return inflater.inflate(layoutId, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        bindObservers()
        bindViews()
    }

    internal fun<T> observe(data: LiveData<T>, run: (T) -> Unit) =
        data.observe(viewLifecycleOwner, run)

    abstract fun bindViewModel()
    abstract fun bindObservers()
    abstract fun bindViews()

}