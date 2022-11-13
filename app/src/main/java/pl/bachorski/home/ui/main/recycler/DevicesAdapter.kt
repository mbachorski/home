package pl.bachorski.home.ui.main.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.bachorski.domain.DeviceBinarySwitch
import pl.bachorski.home.databinding.DeviceItemBinding


interface DevicesAdapterCallback {
    fun turnOn(deviceId: Int)
    fun turnOff(deviceId: Int)
}

class DevicesAdapter(
    private val devicesAdapterCallback: DevicesAdapterCallback
) : ListAdapter<DeviceBinarySwitch,
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

    inner class DeviceViewHolder(private var binding: DeviceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(device: DeviceBinarySwitch) {
            binding.name.text = device.name
            binding.onButton.setOnClickListener {
                Log.i("ADAPTER", "Device: ${device.name} ON ")
                devicesAdapterCallback.turnOn(device.id)
            }
            binding.offButton.setOnClickListener {
                Log.i("ADAPTER", "Device: ${device.name} OFF ")
                devicesAdapterCallback.turnOff(device.id)
            }
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