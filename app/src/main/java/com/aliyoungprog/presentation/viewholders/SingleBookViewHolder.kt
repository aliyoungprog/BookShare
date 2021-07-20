package com.aliyoungprog.presentation.viewholders

import android.graphics.BitmapFactory
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.aliyoungprog.data.database.MyFirebaseStorage
import com.aliyoungprog.domain.entity.Book
import com.aliyoungprog.presentation.adapters.ItemClickListener
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.list_item_book.view.*
import kotlinx.coroutines.*

class SingleBookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    lateinit var book: Book


    fun bind(book: Book, clickListener: ItemClickListener){
        this.book = book
        itemView.book_name.text = book.name
        getImg()
        itemView.setOnClickListener{
            clickListener.onItemClicked(book)
        }
    }

    private fun downloadImg(book_name: String){
            val storage = MyFirebaseStorage.db_storage.getReference(book_name)
            storage.getBytes(1024 * 1024).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                Glide.with(itemView)
                    .load(bitmap)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(itemView.book_image)
            }.addOnFailureListener {

            }
    }

//    .diskCacheStrategy(DiskCacheStrategy.DATA)


    private fun getImg() {
        CoroutineScope(Dispatchers.IO).launch {
            downloadImg(book_name = book.name.toString())
        }
    }
}
