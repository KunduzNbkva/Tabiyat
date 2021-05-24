package kg.tabiyat.ui.main.addObservation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import kg.tabiyat.R
import kg.tabiyat.base.*
import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.MapObservationModel
import kg.tabiyat.data.model.PostObserve
import kg.tabiyat.databinding.AddObservationFragmentBinding
import kg.tabiyat.ui.main.addObservation.viewModel.AddObservationViewModel
import kg.tabiyat.ui.main.addObservation.adapter.ImagesAdapter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.android.ext.android.inject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class AddObservationFragment : BaseFragment<AddObservationFragmentBinding>(AddObservationFragmentBinding::inflate), OnDeleteListener {
    private val viewModel by inject<AddObservationViewModel>()
    private var calendar: Calendar = Calendar.getInstance()
    private lateinit var imagesAdapter: ImagesAdapter
    private var alertDialog: AlertDialog? = null
    private var type: String = "plants"
    private var txt: String? = null
    private var abundance: String? = null
    private var galleryPermissionGranted = false
    private var bodyList = arrayListOf<MultipartBody.Part>()
    private lateinit var file: File
    private var  observationableId :Int? = null
    private var body:MultipartBody.Part? =null
    private var mapData:MapObservationModel? =null
    private var abundanceId: Int? = null


    override fun setUpViews() {
        super.setUpViews()
        addImage()
        setPlantChooseClick()
        setDataClick()
        getTime(binding.addObsrvTime, requireContext())
        getDate(binding.addObsrvDate, requireContext())
        postObservation()
        createImagesRecycler()
        setMapFragmentListener()
        setPlantFragmentListener()
        getGalleryPermission()
    }

    override fun observeData() {
        super.observeData()
        observeViewModel()
    }


    private fun setMapFragmentListener() {
        setFragmentResultListener("mapData_key") { requestKey, bundle ->
            mapData = bundle.getSerializable("mapBundle_key") as MapObservationModel
            Log.e(
                "Map", "mapData's accuracy is ${mapData!!.accuracy}," +
                        "latLng is ${mapData!!.latLng}," +
                        "altitude is ${mapData!!.altitude}"
            )
        }
    }

    private fun setPlantFragmentListener(){
        setFragmentResultListener("plantData_key") { requestKey, bundle ->
            val plantData = bundle.getSerializable("model_name") as Datum
            observationableId = plantData.id!!
            binding.addObsrvPlantTitle.text = plantData.name!!.ru
            Log.e(
                "Plant", "plantData's  is ${plantData.id},"
            )
        }
    }


    private fun observeViewModel() {
        viewModel.message.observe(viewLifecycleOwner, {
            requireContext().showToastShort(it)
        })
    }

    private fun postObservation() {
        binding.postObservation.setOnClickListener {
            val postObserve = PostObserve(
                type,
                observationableId,
                binding.addObsrvComments.text.toString() ,
                "42.8746",
                "74.5698",
//                mapData!!.altitude,
                800.0,
               // mapData!!.accuracy,
                80f,
                abundanceId,
//                bodyList as List<MultipartBody.Part>
            null
            )
            viewModel.postObservation(postObserve)
        }
    }

    private fun setDataClick() {
        binding.abundanceExList.setOnClickListener {
            createAlertDialogWithRadioButtonGroup(binding.addObsrvAbundanceTitle)
        }
        binding.addObsrvLocation.setOnClickListener {
            getLocationData()
        }
    }

    private fun getLocationData() {
        view.let {
            Navigation.findNavController(it!!)
                .navigate(R.id.action_addObservationFragment_to_locationMapFragment)
        }
    }

    private fun createAlertDialogWithRadioButtonGroup(view: TextView) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.select))
        val values = arrayOf<CharSequence>(
            getString(R.string.seldom_abundance),
            getString(R.string.normal_abundance),
            getString(R.string.often_abundance))

        builder.setSingleChoiceItems(values, -1) { _, item ->
            when (item) {
                0 -> {
                    txt = values[0].toString()
                    abundanceId = 0
                }
                1 -> {
                    txt = values[1].toString()
                    abundanceId = 0
                }
                2 -> {
                    txt = values[2].toString()
                    abundanceId = 0
                }
            }
            view.text = txt
            abundance = txt
            alertDialog!!.dismiss()
        }
        alertDialog = builder.create()
        alertDialog!!.show()
    }

    private fun setPlantChooseClick() {
        binding.addObsrvPlant.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_addObservationFragment_to_choosePlantFragment)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(textView: TextView, context: Context) {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            textView.text = SimpleDateFormat("HH:mm").format(calendar.time)
        }
        textView.setOnClickListener {
            TimePickerDialog(
                context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(
                    Calendar.MINUTE
                ), true
            ).show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(textView: TextView, context: Context) {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                textView.text = SimpleDateFormat("dd.MM.yyyy").format(calendar.time)
            }
        textView.setOnClickListener {
            DatePickerDialog(
                context, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun addImage() {
        binding.addObsrvAddImgBtn.setOnClickListener {
            if(!galleryPermissionGranted){
                val intent = Intent()
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.action = Intent.ACTION_PICK
                resultLauncher.launch(Intent.createChooser(intent, getString(R.string.select_picture)))
            } else if(galleryPermissionGranted){
                requireContext().showToastShort(getString(R.string.grant_permission))
            }
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val uriPathHelper = URIPathHelper()
            if (result.data!!.clipData != null) {
                val mClipData = result.data!!.clipData
                val cout = mClipData!!.itemCount
                for (i in 0 until cout) {
                    val imageUrl = mClipData.getItemAt(i).uri
                    imagesAdapter.addImage(imageUrl)
                    bodyList.add(getFilePath(imageUrl))
                }
            } else {
                imagesAdapter.addImage(result.data!!.data!!)
//                val filePath = uriPathHelper.getPath(requireContext(), result.data!!.data!!) // create RequestBody instance from file
//                if (filePath != null) {
//                    file = File(filePath)
//                }
//                val fileBody: RequestBody =
//                    file.asRequestBody(
//                        requireContext().contentResolver.getType(result.data!!.data!!)?.toMediaTypeOrNull()
//                    )
//                body= MultipartBody.Part.createFormData("picture", file.name, fileBody)
                bodyList.add(getFilePath(result.data!!.data!!))
            }
        } else {
            requireContext().showToastShort(getString(R.string.you_did_not_choose_pick))
        }
    }

    private fun getFilePath(uri: Uri):MultipartBody.Part{
        val uriPathHelper = URIPathHelper()
        imagesAdapter.addImage(uri)
        val filePath = uriPathHelper.getPath(requireContext(), uri) // create RequestBody instance from file
        if (filePath != null) {
            file = File(filePath)
        }
        val fileBody: RequestBody =
            file.asRequestBody(
                requireContext().contentResolver.getType(uri)?.toMediaTypeOrNull()
            )
        body= MultipartBody.Part.createFormData("picture", file.name, fileBody)
        return body!!
    }



    private fun createImagesRecycler() {
        imagesAdapter = ImagesAdapter(this)
        binding.addObsrvImages.adapter = imagesAdapter
    }

    override fun onItemClicked(position: Uri) {
        imagesAdapter.removeImage(position)
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

}
