package com.example.quizapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.Activity.CategoryDetailActivity
import com.example.quizapp.Category
import com.example.quizapp.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val categories: List<Category>,
    private val context: Context
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.categoryName.text = category.name
            binding.categoryImage.setImageResource(category.imageResId)

            itemView.setOnClickListener {
                val intent = Intent(context, CategoryDetailActivity::class.java).apply {
                    putExtra("CATEGORY_NAME", category.name)
                    putExtra("CATEGORY_DESCRIPTION", category.description)
                    putExtra("CATEGORY_IMAGE", category.imageResId)
                }
                context.startActivity(intent)
            }
        }
    }
}
