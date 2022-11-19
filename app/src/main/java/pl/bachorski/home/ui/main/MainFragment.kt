package pl.bachorski.home.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import pl.bachorski.home.databinding.FragmentMainBinding
import pl.bachorski.home.ui.main.recycler.DevicesAdapter
import pl.bachorski.home.ui.main.recycler.DevicesAdapterCallback

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.getDevicesFromRepository()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        binding.devicesList.adapter = DevicesAdapter(object : DevicesAdapterCallback {
            override fun turnOn(deviceId: Int) {
                viewModel.turnOn(deviceId)
            }

            override fun turnOff(deviceId: Int) {
                viewModel.turnOff(deviceId)
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.testApiButton.setOnClickListener {
            viewModel.getDevicesFromRepository()
        }

        binding.testApiButton1.setOnClickListener {
            viewModel.turnOn(84)
        }

        binding.testApiButton2.setOnClickListener {
            viewModel.turnOff(84)
        }
    }
}