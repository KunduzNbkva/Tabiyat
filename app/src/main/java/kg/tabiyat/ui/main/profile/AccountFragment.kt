package kg.tabiyat.ui.main.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import kg.tabiyat.R
import kg.tabiyat.base.URIPathHelper
import kg.tabiyat.base.loadImage
import kg.tabiyat.base.showToastShort
import kg.tabiyat.data.model.Customer
import kg.tabiyat.databinding.AccountFragmentBinding
import kg.tabiyat.ui.main.profile.viewModels.AccountViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.android.ext.android.inject
import java.io.File


class AccountFragment : Fragment() {
    private lateinit var binding: AccountFragmentBinding
    private val viewModel by inject<AccountViewModel>()
    private var imageUri: Uri? = null
    private lateinit var customer: Customer
    private var name: String = String()
    private lateinit var file: File
    private lateinit var body: MultipartBody.Part


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
        customer = arguments?.getSerializable(USER_KEY) as Customer
        setDataToView()
        observeStatus()
        changeAvatar()
        saveChangedData()
    }

    private fun saveChangedData() {
        binding.updateAccountInfo.setOnClickListener {
            name = binding.accountName.text.toString()
            viewModel.updateUserAvatar(body)
            viewModel.updateUserName(name)
        }
    }

    private fun observeStatus(){
        viewModel.status.observe(viewLifecycleOwner, {
            if (it != null) requireContext().showToastShort(getString(R.string.successfully))
        })
    }


    private fun setDataToView() {
        binding.accountAvatar.loadImage(customer.avatar.toString())
        binding.accountName.setText(customer.fullName.toString())
        binding.accountEmail.setText(customer.identification.toString())
        // binding.mobileNumber.setText()
        // binding.address.setText()
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            imageUri = data?.data
            binding.accountAvatar.loadImage(imageUri.toString())

            val uriPathHelper = URIPathHelper()
            val filePath = uriPathHelper.getPath(requireContext(), imageUri!!) // create RequestBody instance from file

            if (filePath != null) {
                file = File(filePath)
            }
            val fileBody: RequestBody =
                file.asRequestBody(
                    requireContext().contentResolver.getType(imageUri!!)?.toMediaTypeOrNull()
                )
            body= MultipartBody.Part.createFormData("picture", file.name, fileBody)
        }
    }



    private fun changeAvatar() {
        binding.accountChangeAvatar.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher.launch(gallery)
        }
    }






}