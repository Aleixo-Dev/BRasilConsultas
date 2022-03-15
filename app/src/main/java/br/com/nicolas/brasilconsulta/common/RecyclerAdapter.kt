package br.com.nicolas.brasilconsulta.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter<T : Any>(
    private val data: List<T>,
    @LayoutRes val layoutId: Int,
    private val bindingInterface: GenericSimpleRecyclerBindingInterface<T>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun <T : Any> bind(
            item: T,
            bindingInterface: GenericSimpleRecyclerBindingInterface<T>
        ) = bindingInterface.bindData(item, view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, bindingInterface)
    }

    override fun getItemCount() = data.size
}
