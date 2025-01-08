package kmp.project.projectcodetest.presentation.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kmp.project.projectcodetest.R
import kmp.project.projectcodetest.databinding.FragmentServiceListBinding
import kmp.project.projectcodetest.presentation.services.adapter.CategoryAdapter
import kmp.project.projectcodetest.presentation.services.adapter.ServiceAdapter

@AndroidEntryPoint
class ServiceListFragment : Fragment() {

    private var _binding: FragmentServiceListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ServiceListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(
            requireContext(),
            onCategoryClick = { category ->
                // You can handle category click if needed (e.g., for tracking)
            },
            onServiceClick = { service ->
                val action =
                    ServiceListFragmentDirections.actionServiceListFragmentToDoctorListFragment(
                        service
                    )
                findNavController().navigate(action)
            },
            onBookClick = { serviceId ->
                viewModel.bookAppointment(-1, serviceId)
            }
        )
        binding.categoryRecyclerView.adapter = categoryAdapter
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the data for categories with services
        viewModel.categoryWithServices.observe(viewLifecycleOwner) { categoryWithServices ->
            categoryAdapter.submitList(categoryWithServices)
        }

        binding.btnAppointment.setOnClickListener {
            val action = ServiceListFragmentDirections.actionServiceListFragmentToAppointmentListFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
