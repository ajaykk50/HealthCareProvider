package kmp.project.projectcodetest.presentation.services.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kmp.project.projectcodetest.databinding.ItemServiceBinding
import kmp.project.projectcodetest.domain.model.Service

class ServiceAdapter(
    private val category: String, private val onServiceClick: (String) -> Unit,
    private val onBookClick: (String) -> Unit
) :
    ListAdapter<Service, ServiceAdapter.ServiceViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ServiceViewHolder(private val binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(service: Service) {
            binding.serviceName.text = service.name
            binding.servicePrice.text = "RS ${service.price}"

            if (category != "DOCTOR_CONSULTATION") {
                binding.bookButton.visibility = View.VISIBLE
                binding.ivNext.visibility = View.GONE

                binding.bookButton.setOnClickListener {
                    onBookClick.invoke(service.id.toString())
                }
            } else {
                binding.bookButton.visibility = View.GONE
                binding.ivNext.visibility = View.VISIBLE
            }

            binding.root.setOnClickListener {
                if (category == "DOCTOR_CONSULTATION") {
                    onServiceClick.invoke(service.name)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Service>() {
            override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
                return oldItem == newItem
            }
        }
    }
}
