package kmp.project.projectcodetest.presentation.services.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kmp.project.projectcodetest.R
import kmp.project.projectcodetest.databinding.ItemCategoryBinding
import kmp.project.projectcodetest.domain.model.CategoryWithServices
import kmp.project.projectcodetest.domain.model.Service

class CategoryAdapter(private val context: Context, private val onCategoryClick: (String) -> Unit,
                      private val onServiceClick: (String) -> Unit,
                      private val onBookClick: (String) -> Unit) : ListAdapter<CategoryWithServices, CategoryAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    private var expandedCategory: String? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))

        if (expandedCategory == getItem(position).category) {
            holder.binding.serviceRecyclerView.visibility = View.VISIBLE
            holder.binding.ivImage.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
        } else {
            holder.binding.serviceRecyclerView.visibility = View.GONE
            holder.binding.ivImage.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
        }
    }

    inner class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryWithServices) {
            binding.categoryName.text = category.category

            val serviceAdapter = ServiceAdapter(category.category,onServiceClick,onBookClick)
            binding.serviceRecyclerView.adapter = serviceAdapter
            binding.serviceRecyclerView.layoutManager = LinearLayoutManager(context)
            serviceAdapter.submitList(category.services)

            binding.root.setOnClickListener {

                if (expandedCategory == category.category) {
                    expandedCategory = null // Collapse
                } else {
                    expandedCategory = category.category // Expand the clicked category
                }
                notifyDataSetChanged()
                onCategoryClick(category.category)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoryWithServices>() {
            override fun areItemsTheSame(oldItem: CategoryWithServices, newItem: CategoryWithServices): Boolean = oldItem.category == newItem.category
            override fun areContentsTheSame(oldItem: CategoryWithServices, newItem: CategoryWithServices): Boolean = oldItem == newItem
        }
    }
}
