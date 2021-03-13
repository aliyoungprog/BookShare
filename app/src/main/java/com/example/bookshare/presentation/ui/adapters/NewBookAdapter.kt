package com.example.bookshare.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshare.R
import com.example.bookshare.data.disk_data_source.Book
import kotlinx.android.synthetic.main.new_book.*
import kotlinx.android.synthetic.main.new_book.view.*

class NewBookAdapter(var books: List<Book>): RecyclerView.Adapter<NewBookAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.new_book, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(books[position])

    override fun getItemCount(): Int = books.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var book: Book

        fun bind(book: Book){
            this.book = book
            itemView.book_author.text = book.book_author
            itemView.book_name.text = book.book_name
        }
    }
}
