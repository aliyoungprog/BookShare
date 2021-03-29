package com.example.bookshare.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshare.R
import com.example.bookshare.domain.entity.Book
import kotlinx.android.synthetic.main.list_item_book.view.*

class NewBookAdapter(var books: List<Book> = listOf(), private val clickListener: ItemClickListener): RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(books[position], clickListener)
    }

    override fun getItemCount(): Int = books.size
}

class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
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
