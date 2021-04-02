package com.example.bookshare.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshare.domain.entity.Book
import kotlinx.android.synthetic.main.list_item_book.view.*

class SingleBookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    lateinit var book: Book

    fun bind(book: Book, clickListener: ItemClickListener){
        this.book = book
        itemView.book_author.text = book.author
        itemView.book_name.text = book.name
        itemView.title_name.text = book.genre
        itemView.setOnClickListener{
            clickListener.onItemClicked(book)
        }
    }
}