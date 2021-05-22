package kg.tabiyat.ui.main.addObservatrion

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kg.tabiyat.R
import kg.tabiyat.base.showToastShort
import kg.tabiyat.data.model.PostObserve
import kg.tabiyat.databinding.AddObservationFragmentBinding
import kg.tabiyat.ui.main.addObservatrion.viewModel.AddObservationViewModel
import kg.tabiyat.ui.main.addObservatrion.adapter.ImagesAdapter
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*


class AddObservationFragment : Fragment() {
    private lateinit var binding: AddObservationFragmentBinding
    private val viewModel by inject<AddObservationViewModel>()
    private var calendar: Calendar = Calendar.getInstance()
    private lateinit var imagesAdapter: ImagesAdapter
    private var alertDialog: AlertDialog? = null
    private var type: String = "plants"
    private val pickObsrvImage = 200
    private var txt: String? = null

    var values = arrayOf<CharSequence>(" First Item ", " Second Item ", " Third Item ")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddObservationFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        addImage()
        setPlantChooseClick()
        getPlantData(binding.addObsrvPlantTitle)
        setDataClick()
        getTime(binding.addObsrvTime, requireContext())
        getDate(binding.addObsrvDate, requireContext())
        postObservation()
        createImagesRecycler()

    }

    private fun observeViewModel() {
        viewModel.message.observe(viewLifecycleOwner, {
            requireContext().showToastShort(it)
        })
    }

    private fun postObservation() {
        binding.postObservation.setOnClickListener {
            val postObserve =
                PostObserve(type, 1, "Комментарий", "42.8746", "74.5698", imagesAdapter.getList())
            viewModel.postObservation(postObserve)
        }
    }

    private fun setDataClick() {
        binding.expansionCategory.setOnClickListener {
            createAlertDialogWithRadioButtonGroup(binding.addObsrvCategoryTitle)
        }
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
                .navigate(R.id.action_addObservationFragment_to_mapsActivityCurrentPlace)
        }
    }

    private fun createAlertDialogWithRadioButtonGroup(view: TextView) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select Your Choice")

        builder.setSingleChoiceItems(values, -1,
            DialogInterface.OnClickListener { _, item ->
                when (item) {
                    0 -> txt = values[0].toString()
                    1 -> txt = values[1].toString()
                    2 -> txt = values[2].toString()
                }
                view.text = txt
                alertDialog!!.dismiss()
            })
        alertDialog = builder.create()
        alertDialog!!.show()
    }

    private fun setPlantChooseClick() {
        binding.addObsrvPlant.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_addObservationFragment_to_choosePlantFragment)
        }
    }

    private fun getPlantData(view: TextView) {
        val name: String = arguments?.getString("model_name") ?: getString(R.string.choose_plant)
        view.text = name
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
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), pickObsrvImage)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickObsrvImage && resultCode == RESULT_OK && null != data) {
            if (data.clipData != null) {
                val mClipData = data.clipData
                val cout = mClipData!!.itemCount
                for (i in 0 until cout) {
                    val imageurl = mClipData.getItemAt(i).uri
                    imagesAdapter.addImage(imageurl)
                }
            } else {
                imagesAdapter.addImage(data.data!!)
            }
        } else {
            requireContext().showToastShort(getString(R.string.you_did_not_choose_pick))
        }
    }


    private fun createImagesRecycler() {
        imagesAdapter = ImagesAdapter()
        binding.addObsrvImages.adapter = imagesAdapter
    }
}