package com.example.bookshare.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshare.R
import com.example.bookshare.domain.entity.Book
import kotlinx.android.synthetic.main.list_item_book.view.*

class NewBookAdapter(var books: List<Book> = listOf(), private val clickListener: ItemClickListener): RecyclerView.Adapter<SingleBookViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleBookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false)
        return SingleBookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SingleBookViewHolder, position: Int) {
        holder.bind(books[position], clickListener)
    }

    override fun getItemCount(): Int = books.size
}

