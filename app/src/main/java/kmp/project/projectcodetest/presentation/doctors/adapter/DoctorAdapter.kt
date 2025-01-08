package kmp.project.projectcodetest.presentation.doctors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kmp.project.projectcodetest.databinding.ItemDoctorBinding
import kmp.project.projectcodetest.domain.model.Doctor

class DoctorAdapter (private val onBookClick: (String,String) -> Unit) : ListAdapter<Doctor, DoctorAdapter.DoctorViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DoctorViewHolder(private val binding: ItemDoctorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(doctor: Doctor) {
            binding.doctorName.text = doctor.name
            if (doctor.availability) {
                binding.doctorAvailability.text = "Available"
            } else {
                binding.doctorAvailability.text = "Not Available"
            }
            binding.doctorExperience.text = "Exp : " + doctor.experience.toString()
            binding.doctorSpecialization.text = doctor.specialization

            binding.bookDoctorButton.setOnClickListener {
                onBookClick(doctor.id.toString(),doctor.specialization)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Doctor>() {
            override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
                return oldItem == newItem
            }
        }
    }
}
