package com.example.tabiyat.ui.main.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabiyat.databinding.AccountFragmentBinding
import com.example.tabiyat.ui.main.profile.viewModels.AccountViewModel
import org.koin.android.ext.android.inject

class AccountFragment : Fragment() {
    private lateinit var binding: AccountFragmentBinding
    private val viewModel by inject <AccountViewModel>()
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AccountFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeAvatar()
    }

    private fun changeAvatar(){
        binding.accountChangeAvatar.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            binding.accountAvatar.setImageURI(imageUri)
        }
    }



}