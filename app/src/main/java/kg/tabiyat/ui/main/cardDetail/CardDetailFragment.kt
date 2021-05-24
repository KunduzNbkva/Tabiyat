package kg.tabiyat.ui.main.cardDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.loadImage
import kg.tabiyat.base.showToastShort
import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.FavoriteModel
import kg.tabiyat.databinding.CardDetailFragmentBinding
import kg.tabiyat.ui.main.cardFavoriteDetail.CardFavDetailViewModel
import org.koin.android.ext.android.inject

class CardDetailFragment : BaseFragment<CardDetailFragmentBinding>(CardDetailFragmentBinding::inflate), View.OnClickListener {
    private val viewModel by inject<CardFavDetailViewModel>()
    private lateinit var buttonAddObservation: Button
    private lateinit var redBookRecycler: RecyclerView
    private lateinit var fenofazeRecycler: RecyclerView
    private lateinit var model: Datum
    private lateinit var type: String
    private var clickCounter: Int = 0
    private var message = String()
    private lateinit var favoriteModel:FavoriteModel
    private var favoritableId: Int = 0


    companion object {
        const val FAV_MODEL_KEY = "model"
    }

    @SuppressLint("UseCompatLoadingForDrawables", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        redBookRecycler = view.findViewById(R.id.redBook_list)
        fenofazeRecycler = view.findViewById(R.id.fenofaze_list)
        buttonAddObservation = view.findViewById(R.id.add_observation_btn)
    }

    override fun setUpViews() {
        super.setUpViews()
        getDataOfModel()
        // checkForFavorite()
        setViewData()
        openObservations()
        setList()
        buttonAddObservation.setOnClickListener(this)
        binding.detailSaveCard.setOnClickListener { onFavoriteClick() }
    }

    override fun observeData() {
        super.observeData()
        observe()
    }

    private fun getDataOfModel() {
        model = arguments?.getSerializable("model") as Datum
        type = arguments?.getString("type").toString()
       // favoritableId = arguments?.getInt("favoritable_id")!!
    }

    private fun setList() {
        val redBookList = ArrayList<String>()
        when (model.redBook!!.toInt()) {
            0 -> redBookList.add("Не входит")
            1 -> redBookList.add("Входит")
        }
        when (model.iucn!!.toInt()) {
            1 -> redBookList.add("Collapsed")
            2 -> redBookList.add("Critically Endangered")
            3 -> redBookList.add("Endangered")
            4 -> redBookList.add("Vulnerable")
            5 -> redBookList.add("Near Endangered ")
            6 -> redBookList.add("Least Concern")
            7 -> redBookList.add("Data Deficient")
            8 -> redBookList.add("Not Evaluated")
        }


        val fenofazeList = ArrayList<String>()
        fenofazeList.add(model.phenophaseId.toString())

        val redBookAdapter = CardDetailAdapter(redBookList)
        redBookRecycler.layoutManager = LinearLayoutManager(requireContext())
        redBookRecycler.adapter = redBookAdapter

        val fenofazeAdapter = CardDetailAdapter(fenofazeList)
        fenofazeRecycler.layoutManager = LinearLayoutManager(requireContext())
        fenofazeRecycler.adapter = fenofazeAdapter
    }

    private fun openObservations() {
        binding.observationLayout.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_plantsDetailFragment_to_observationsFragment)
        }
    }

    override fun onClick(v: View?) {
        Navigation.findNavController(v!!)
            .navigate(R.id.action_plantsDetailFragment_to_addObservationFragment)
    }


    private fun observe() {
        viewModel.favoriteResponse.observe(viewLifecycleOwner, {
            if (it!=null) Log.e("Favorite","favorite is created ${it.id}")
        })
    }

    private fun checkForFavorite(){
//        if (viewModel.isFavorite())
//            binding.detailSaveCard.setImageResource(R.drawable.ic_save)
//        else if(!viewModel.isFavorite()) {
//            binding.detailSaveCard.setImageResource(R.drawable.ic_saved)
//        }

        if(favoritableId!=null){
            binding.detailSaveCard.setImageResource(R.drawable.ic_saved)
        } else{
            binding.detailSaveCard.setImageResource(R.drawable.ic_save)
        }
    }

    private fun onFavoriteClick() {
        clickCounter++
        if (clickCounter % 2 == 0) {
            binding.detailSaveCard.setImageResource(R.drawable.ic_saved)
            favoriteModel = FavoriteModel(type, model.id!!)
            viewModel.createFavorite(favoriteModel)
        }  else if (clickCounter % 2 != 0) {
            binding.detailSaveCard.setImageResource(R.drawable.ic_save)
            viewModel.deleteFavorite(favoritableId)
            requireContext().showToastShort(getString(R.string.fav_deleted))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setViewData() {
        binding.detailCardTitle.text = model.name!!.ru.toString()
        binding.detailCardImg.loadImage(model.urlPick.toString())
        binding.detailCardLang.text =
            "(лат. ${model.name!!.la}, русс. ${model.name!!.ru}, кырг. ${model.name!!.ky})"
        binding.detailCardDescription.text = model.description!!.ru.toString()
        binding.detailCardClass.text = model.classId.toString()
        binding.detailCardDepartment.text = model.divisionId.toString()
        binding.detailCardFamily.text = model.familyId.toString()
        binding.detailCardGenus.text = model.genusId.toString()
        //binding.detailCardPalatability.text = model.
        // binding.observationCount.text = model.
    }

}

enum class IUCN {
    CO,
    CR,
    EN,
    VU,
    NE,
    LC,
    DD,
    NOE

    //            "CO"->redBookList.add("Collapsed")
//            "CR"->redBookList.add("Critically Endangered")
//            "EN"->redBookList.add("Endangered")
//            "VU"->redBookList.add("Vulnerable")
//            "NE"->redBookList.add("Near Endangered ")
//            "LC"->redBookList.add("Least Concern")
//            "DD"->redBookList.add("Data Deficient")
//            "NOE"->redBookList.add("Not Evaluated")

}

