package com.aliyoungprog.presentation.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.aliyoungprog.data.database.MyFirebaseStorage
import com.aliyoungprog.databinding.FragmentBookDescriptionBinding
import com.aliyoungprog.domain.entity.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class BookDescriptionFragment : Fragment() {

    lateinit var binding: FragmentBookDescriptionBinding
    lateinit var bookTitle: TextView
    lateinit var bookName: TextView
    lateinit var bookSender: TextView
    lateinit var bookDesc: TextView
    lateinit var bookImage: ImageView
    lateinit var book: Book

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBookDescriptionBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() = BookDescriptionFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVars()
        receiveDataListener()
        setUpActionBar(book.name.toString())
    }

    private fun setUpActionBar(bookName: String) {
        bookTitle.text = bookName
    }

    private fun receiveDataListener(){
        val bundle: Bundle? = this.arguments
        book = bundle!!.getParcelable("book")!!
        bookName.text = book.name
        bookSender.text = book.sender
        bookDesc.text = book.description
        downloadImg(book.name.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initVars(){
        bookName = binding.bookDescriptionName
        bookSender = binding.bookDescriptionAdd
        bookDesc = binding.bookDescriptionDesc
        bookTitle = binding.title
        bookImage = binding.bookDescriptionImg
        bookTitle.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun downloadImg(book_name: String) {
        if(activity == null) return
        CoroutineScope(Dispatchers.IO).launch {
            val storage = MyFirebaseStorage.db_storage.getReference(book_name)
            Timber.tag("data").i(storage.getBytes(1024 * 1024).toString())
            storage.getBytes(1024 * 1024).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                bookImage.setImageBitmap(bitmap)
                context?.let { it1 ->
                    Glide.with(it1)
                        .load(bitmap)
                        .centerCrop()
                        .into(bookImage)
                }
            }.addOnFailureListener {

            }
        }
    }

}
