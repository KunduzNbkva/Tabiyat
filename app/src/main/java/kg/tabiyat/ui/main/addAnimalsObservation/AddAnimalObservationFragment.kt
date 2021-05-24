package kg.tabiyat.ui.main.addAnimalsObservation

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.OnDeleteListener
import kg.tabiyat.base.showToastShort
import kg.tabiyat.data.model.MapObservationModel
import kg.tabiyat.databinding.FragmentAddAnimalsObservationBinding
import kg.tabiyat.ui.main.addObservation.adapter.ImagesAdapter
import kg.tabiyat.ui.main.addObservation.viewModel.AddObservationViewModel
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*


class AddAnimalObservationFragment : BaseFragment<FragmentAddAnimalsObservationBinding>(FragmentAddAnimalsObservationBinding::inflate), OnDeleteListener {
    private val viewModel by inject<AddObservationViewModel>()
    private var calendar: Calendar = Calendar.getInstance()
    private lateinit var imagesAdapter: ImagesAdapter
    private var alertDialog: AlertDialog? = null
    private var type: String = "animals"
    private var txt: String? = null
    private var abundance: String? = null

    override fun setUpViews() {
        super.setUpViews()
        addImage()
        setPlantChooseClick()
        getPlantData(binding.addObsrvPlantTitle)
        setDataClick()
        getTime(binding.addObsrvTime, requireContext())
        getDate(binding.addObsrvDate, requireContext())
        postObservation()
        createImagesRecycler()
        setFragmentListener()
    }

    override fun observeData() {
        super.observeData()
        observeViewModel()
    }

    private fun setFragmentListener() {
        setFragmentResultListener("mapData_key") { requestKey, bundle ->
            val mapData = bundle.getSerializable("mapBundle_key") as MapObservationModel
            Log.e(
                "Map", "mapData's accuracy is ${mapData.accuracy}," +
                        "latLng is ${mapData.latLng}," +
                        "altitude is ${mapData.altitude}"
            )
        }
    }

    private fun observeViewModel() {
        viewModel.message.observe(viewLifecycleOwner, {
            requireContext().showToastShort(it)
        })
    }

    private fun postObservation() {
        binding.postAnimalObservation.setOnClickListener {
//            val postObserve =
//                PostObserve(type, 1, "Комментарий", "42.8746", "74.5698", imagesAdapter.getList())
//            viewModel.postObservation(postObserve)
        }
    }

    private fun setDataClick() {
        binding.worryExList.setOnClickListener {
            createAlertDialogWithRadioButtonGroup(binding.addObsrvWorryTitle)
        }
        binding.addObsrvLocation.setOnClickListener {
            getLocationData()
        }
    }

    private fun getLocationData() {
        view.let {
            Navigation.findNavController(it!!)
                .navigate(R.id.action_addAnimalObsrvFragment_to_locationMapFragment)
        }
    }

    private fun createAlertDialogWithRadioButtonGroup(view: TextView) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.select))
        val values = arrayOf<CharSequence>(
            getString(R.string.running),
            getString(R.string.standing),
            getString(R.string.sleeping)
        )
        builder.setSingleChoiceItems(values, -1) { _, item ->
            when (item) {
                0 -> txt = values[0].toString()
                1 -> txt = values[1].toString()
                2 -> txt = values[2].toString()
            }
            view.text = txt
            abundance = txt
            alertDialog!!.dismiss()
        }
        alertDialog = builder.create()
        alertDialog!!.show()
    }

    private fun setPlantChooseClick() {
        binding.addObsrvAnimal.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_addAnimalObsrvFragment_to_chooseAnimalFragment)
        }
    }

    private fun getPlantData(view: TextView) {
        val name: String = arguments?.getString("model_name") ?: getString(R.string.choose_animal)
        view.text = name
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(textView: TextView, context: Context) {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
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
        binding.addAnimalObsrvAddImgBtn.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_PICK
            resultLauncher.launch(Intent.createChooser(intent, getString(R.string.select_picture)))
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                if (result.data!!.clipData != null) {
                    val mClipData = result.data!!.clipData
                    val cout = mClipData!!.itemCount
                    for (i in 0 until cout) {
                        val imageurl = mClipData.getItemAt(i).uri
                        imagesAdapter.addImage(imageurl)
                    }
                } else {
                    imagesAdapter.addImage(result.data!!.data!!)
                }
            } else {
                requireContext().showToastShort(getString(R.string.you_did_not_choose_pick))
            }
        }


    private fun createImagesRecycler() {
        imagesAdapter = ImagesAdapter(this)
        binding.addAnimalObsrvImages.adapter = imagesAdapter
    }

    override fun onItemClicked(position: Uri) {
        imagesAdapter.removeImage(position)
    }
}