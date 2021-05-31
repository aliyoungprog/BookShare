package com.aliyoungprog.presentation.adapters

import android.graphics.BitmapFactory
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.aliyoungprog.R
import com.aliyoungprog.data.database.MyFirebaseStorage
import com.aliyoungprog.domain.entity.Book
import kotlinx.android.synthetic.main.list_item_book.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SingleBookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    lateinit var book: Book

    fun bind(book: Book, clickListener: ItemClickListener){
        this.book = book
        itemView.book_author.text = book.author
        itemView.book_name.text = book.name
        itemView.title_name.text = book.genre
        itemView.book_sender.text = book.sender
        downloadImg(book.name.toString())
        itemView.setOnClickListener{
            clickListener.onItemClicked(book)
        }
    }
    private fun downloadImg(book_name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val storage = MyFirebaseStorage.db_storage.getReference(book_name)
            Timber.tag("data").i(storage.getBytes(1024 * 1024).toString())
            storage.getBytes(1024 * 1024).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                itemView.book_image.setImageBitmap(bitmap)
                Glide.with(itemView)
                    .load(bitmap)
                    .centerCrop()
                    .placeholder(R.drawable.add_book_fragment_book_img)
                    .into(itemView.book_image)
            }.addOnFailureListener {

            }
        }
    }
}
