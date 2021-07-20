package com.aliyoungprog.presentation.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aliyoungprog.domain.entity.Book
import com.aliyoungprog.presentation.adapters.ItemClickListener
import kotlinx.android.synthetic.main.list_item_book.view.*
import kotlinx.android.synthetic.main.list_item_book.view.book_name
import kotlinx.android.synthetic.main.list_item_book_to_profile.view.*

class SingleBookProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(book: Book, clickListener: ItemClickListener){
        itemView.book_name.text = book.name
        itemView.book_author.text = book.author
        itemView.title_name.text = book.genre
        itemView.book_sender.text = book.sender
        itemView.setOnClickListener{
            clickListener.onItemClicked(book)
        }
    }
}
