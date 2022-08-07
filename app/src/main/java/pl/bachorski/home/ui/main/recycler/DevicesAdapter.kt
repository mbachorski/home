package pl.bachorski.home.ui.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.bachorski.domain.DeviceBinarySwitch
import pl.bachorski.home.databinding.DeviceItemBinding

class DevicesAdapter : ListAdapter<DeviceBinarySwitch,
        DevicesAdapter.DeviceViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DevicesAdapter.DeviceViewHolder {
        return DeviceViewHolder(
            DeviceItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: DevicesAdapter.DeviceViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }

    class DeviceViewHolder(private var binding: DeviceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(device: DeviceBinarySwitch) {
            binding.name.text = device.name
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DeviceBinarySwitch>() {
        override fun areItemsTheSame(
            oldItem: DeviceBinarySwitch,
            newItem: DeviceBinarySwitch
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DeviceBinarySwitch,
            newItem: DeviceBinarySwitch
        ): Boolean {
            return oldItem.value == newItem.value
        }
    }
}