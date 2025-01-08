package kmp.project.projectcodetest.presentation.doctors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kmp.project.projectcodetest.databinding.FragmentDoctorListBinding
import kmp.project.projectcodetest.presentation.doctors.adapter.DoctorAdapter

@AndroidEntryPoint
class DoctorListFragment : Fragment() {

    private var _binding: FragmentDoctorListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DoctorListViewModel by viewModels()

    private val args: DoctorListFragmentArgs by navArgs() // Safe Args


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoctorListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val doctorAdapter = DoctorAdapter {id,specialization ->
            viewModel.bookAppointment(id.toInt(),specialization)
        }
        binding.doctorRecyclerView.adapter = doctorAdapter
        binding.doctorRecyclerView.layoutManager = LinearLayoutManager(context)

        val specialization = args.specialization

        viewModel.loadDoctors(specialization)

        viewModel.doctorsList.observe(viewLifecycleOwner) { doctors ->
            doctorAdapter.submitList(doctors)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}