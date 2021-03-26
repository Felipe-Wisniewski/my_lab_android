package com.wisnitech.googlebooks.ui.books

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ApiException
import com.wisnitech.googlebooks.auth.AuthManager
import com.wisnitech.googlebooks.databinding.ListBooksFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListBooksFragment : Fragment() {

    private lateinit var binding: ListBooksFragmentBinding
    private val authManager by inject<AuthManager>()
    private val viewModel by viewModel<ListBooksViewModel>()

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) handleSignInResult(it.data)
            else checkGooglePlayServices()

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListBooksFragmentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener { signIn() }
    }

    private fun signIn() {
        val signInIntent = authManager.getSignInIntent()
        startForResult.launch(signInIntent)
    }

    private fun handleSignInResult(intent: Intent?) {
        try {
            GoogleSignIn.getSignedInAccountFromIntent(intent).getResult(ApiException::class.java)
            // continue flow
        } catch (e: ApiException) {
            Toast.makeText(requireContext(), "falha", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkGooglePlayServices() {
        val api = GoogleApiAvailability.getInstance()
        val resultCode = api.isGooglePlayServicesAvailable(requireContext())

        if (resultCode != ConnectionResult.SUCCESS) {
            if (api.isUserResolvableError(resultCode)) {
                val dialog =
                    api.getErrorDialog(requireActivity(), resultCode, REQUEST_PLAY_SERVICES)
                dialog.setOnCancelListener { Log.d("FLMWG", "FINISH DIALOG") }
                dialog.show()
            } else {
                Toast.makeText(requireContext(), "error play services", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val REQUEST_SIGN_IN = 1000
        private const val REQUEST_PLAY_SERVICES = 2000
    }
}