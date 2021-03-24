package com.wisnitech.googlebooks.ui.books

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wisnitech.googlebooks.R

class ListBooksFragment : Fragment() {


    private lateinit var viewModel: ListBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_books_fragment, container, false)
    }


}