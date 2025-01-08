package kmp.project.projectcodetest.presentation.appointments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kmp.project.projectcodetest.databinding.ItemAppointmentBinding
import kmp.project.projectcodetest.databinding.ItemAppointmentDetailsBinding
import kmp.project.projectcodetest.domain.model.Appointment
import kmp.project.projectcodetest.domain.model.AppointmentDetails


class AppointmentAdapter(private val onCancelClick: (Int) -> Unit) : ListAdapter<AppointmentDetails, AppointmentAdapter.AppointmentViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = ItemAppointmentDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AppointmentViewHolder(private val binding: ItemAppointmentDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appointment: AppointmentDetails) {
            binding.serviceName.text = appointment.serviceName
            binding.servicePrice.text = "${appointment.servicePrice}"

            if (appointment.doctorName != null) {
                binding.doctorDetails.text = "Doctor: ${appointment.doctorName}, ${appointment.doctorSpecialization}, Exp : ${appointment.doctorExperience} years"
                binding.doctorDetails.visibility = View.VISIBLE
            } else {
                binding.doctorDetails.visibility = View.GONE
            }

            binding.removeButton.setOnClickListener {
                onCancelClick(appointment.appointmentId)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AppointmentDetails>() {
            override fun areItemsTheSame(oldItem: AppointmentDetails, newItem: AppointmentDetails): Boolean {
                return oldItem.appointmentId == newItem.appointmentId
            }

            override fun areContentsTheSame(oldItem: AppointmentDetails, newItem: AppointmentDetails): Boolean {
                return oldItem == newItem
            }
        }
    }
}
