package br.com.nicolas.brasilconsulta.common

import android.view.View

interface GenericSimpleRecyclerBindingInterface<T : Any> {
    fun bindData(item: T, view: View)
}