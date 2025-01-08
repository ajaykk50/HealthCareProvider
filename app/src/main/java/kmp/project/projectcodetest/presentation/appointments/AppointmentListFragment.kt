package kmp.project.projectcodetest.presentation.appointments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kmp.project.projectcodetest.databinding.FragmentAppointmentListBinding
import kmp.project.projectcodetest.presentation.appointments.adapter.AppointmentAdapter

@AndroidEntryPoint
class AppointmentListFragment : Fragment() {

    private var _binding: FragmentAppointmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AppointmentListViewModel by viewModels()
    private lateinit var adapter: AppointmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppointmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AppointmentAdapter {
            viewModel.deleteAppointment(it)
        }
        binding.appointmentRecyclerView.adapter = adapter
        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.appointments.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.totalServicePrice.observe(viewLifecycleOwner) { totalPrice ->
            binding.totalPrice.text = "Total Price: $totalPrice"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}