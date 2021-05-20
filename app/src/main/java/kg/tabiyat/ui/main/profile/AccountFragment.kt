package kg.tabiyat.ui.main.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kg.tabiyat.base.loadImage
import kg.tabiyat.base.showToastShort
import kg.tabiyat.data.model.Customer
import kg.tabiyat.databinding.AccountFragmentBinding
import kg.tabiyat.ui.main.profile.viewModels.AccountViewModel
import org.koin.android.ext.android.inject

class AccountFragment : Fragment() {
    private lateinit var binding: AccountFragmentBinding
    private val viewModel by inject<AccountViewModel>()
    private val pickImage = 100
    private var imageUri: Uri? = null
    private lateinit var customer: Customer
    private var name: String = String()


    companion object {
        const val USER_KEY = "user"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AccountFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStatus()
        getUser()
        setDataToView()
        changeAvatar()
        saveChangedData()

    }

    private fun saveChangedData() {
        binding.updateAccountInfo.setOnClickListener {
            name = binding.accountName.text.toString()
            viewModel.updateUserAvatar(imageUri.toString())
            viewModel.updateUserName(name)
            requireContext().showToastShort("Save click")
        }
    }

    private fun observeStatus(){
        viewModel.status.observe(viewLifecycleOwner,{
            if (it!=null) requireContext().showToastShort("Successfully")
        })
    }


    private fun setDataToView() {
        binding.accountAvatar.loadImage(customer.avatar.toString())
        binding.accountName.setText(customer.fullName.toString())
        binding.accountEmail.setText(customer.identification.toString())
        // binding.mobileNumber.setText()
        // binding.address.setText()
    }

    private fun getUser() {
        customer = arguments?.getSerializable(USER_KEY) as Customer
    }

    private fun changeAvatar() {
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