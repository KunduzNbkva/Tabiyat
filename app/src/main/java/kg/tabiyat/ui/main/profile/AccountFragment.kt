package kg.tabiyat.ui.main.profile

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kg.tabiyat.R
import kg.tabiyat.base.URIPathHelper
import kg.tabiyat.base.loadImage
import kg.tabiyat.base.showToastShort
import kg.tabiyat.data.model.Customer
import kg.tabiyat.databinding.AccountFragmentBinding
import kg.tabiyat.ui.main.map.MapsFragment
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
    private var galleryPermissionGranted = false



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
            customer.avatar = body
            customer.fullName = name
            val bundle = Bundle()
            bundle.putSerializable("edited_customer",customer)
            Navigation.findNavController(it).navigate(R.id.action_accountFragment_to_navigation_profile)
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
            getGalleryPermission()
            if(!galleryPermissionGranted){
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                resultLauncher.launch(gallery)
            } else if(galleryPermissionGranted){
                requireContext().showToastShort(getString(R.string.grant_permission))
            }
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            100 -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                 Log.e("Permission","Permission is granted")
                } else requireContext().showToastShort(getString(R.string.grant_permission))
            }
        }
    }

    private fun getGalleryPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                galleryPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
               100
            )
        }
    }


    }









