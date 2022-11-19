package pl.bachorski.home.ui.main.recycler

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.bachorski.domain.DeviceBinarySwitch
import pl.bachorski.home.ui.main.ApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DeviceBinarySwitch>?) {
    val adapter = recyclerView.adapter as DevicesAdapter
    adapter.submitList(data)
}

@BindingAdapter("progressApiStatus")
fun bindProgressView(progressBar: ProgressBar, status: ApiStatus) {
    progressBar.isVisible = status == ApiStatus.IN_PROGRESS
}