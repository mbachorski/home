package pl.bachorski.home.ui.main.recycler

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.bachorski.domain.DeviceBinarySwitch

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DeviceBinarySwitch>?) {
    val adapter = recyclerView.adapter as DevicesAdapter
    adapter.submitList(data)
}