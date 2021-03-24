package com.wisnitech.googlebooks.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wisnitech.googlebooks.R

class FavoriteBooksFragment : Fragment() {


    private lateinit var viewModel: FavoriteBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_books_fragment, container, false)
    }


}