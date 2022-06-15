package com.diegohurtado.zemoga.core.exceptions

import android.content.Context
import com.diegohurtado.zemoga.R

class MissingLayoutIdException(private val context: Context): RuntimeException() {
    override val message: String
        get() = context.getString(R.string.missing_layout)
}