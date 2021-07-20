package com.aliyoungprog.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.aliyoungprog.R
import com.aliyoungprog.domain.entity.Book
import com.aliyoungprog.presentation.viewholders.SingleBookProfileViewHolder
import com.aliyoungprog.presentation.viewholders.SingleBookViewHolder
import java.util.*
import kotlin.collections.ArrayList

class NewBookAdapter(var books: ArrayList<Book> = listOf<Book>() as ArrayList<Book>, private val clickListener: ItemClickListener, private val someConst: String): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val VIEW_TYPE_HOME_FRAGMENT = 0
    private val VIEW_TYPE_PROFILE_FRAGMENT = 1

    val initialFilteredList : ArrayList<Book>
        get() = ArrayList<Book>().apply { addAll(books) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        if (viewType == VIEW_TYPE_HOME_FRAGMENT) {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false)
            return SingleBookViewHolder(itemView)
        }else{
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_book_to_profile, parent, false)
            return SingleBookProfileViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_HOME_FRAGMENT){
            (holder as SingleBookViewHolder).bind(books[position], clickListener)
        }else{
            (holder as SingleBookProfileViewHolder).bind(books[position], clickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (someConst == "HOME_FRAGMENT"){
            return VIEW_TYPE_HOME_FRAGMENT
        }else{
            return VIEW_TYPE_PROFILE_FRAGMENT
        }
    }

    override fun getItemCount(): Int = books.size

    private val bookFilter = object : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<Book> = ArrayList()
            if (constraint == null || constraint.isEmpty()){
                initialFilteredList.let { filteredList.addAll(it) }
            }else{
                val query = constraint.toString().toLowerCase(Locale.ROOT)
                initialFilteredList.forEach{
                    if (it.name?.toLowerCase(Locale.ROOT)!!.contains(query)){
                        filteredList.add(it)
                    }
                }
            }
            val res = FilterResults()
            res.values = filteredList
            return res
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>){
                books.clear()
                books.addAll(results.values as ArrayList<Book>)
                notifyDataSetChanged()
            }
        }

    }

    fun getFilter(): Filter{
        return bookFilter
    }


}

