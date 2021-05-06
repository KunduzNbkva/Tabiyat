package com.example.tabiyat.ui.main.tabiyat.adapters

//class MainDataAdapter(
//    private var list: ArrayList<Any>,
//    private var clickListener: OnMainCardClickListener
//) :
//    RecyclerView.Adapter<BaseViewHolder<*>>() {
//
//    companion object {
//        private const val TYPE_PLANTS = 0
//        private const val TYPE_ANIMALS = 1
//        private const val TYPE_INFO = 2
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
//       return when(viewType) {
//           TYPE_PLANTS->{
//               PlantsViewHolder(PlantsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//           }
//           TYPE_ANIMALS->{
//               AnimalsViewHolder(PlantsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//           }
//           TYPE_INFO->{
//               InfoViewHolder(InfoListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//           }
//           else -> throw IllegalArgumentException("Invalid view type")
//       }
//    }
//
//    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
//       return
//    }
//
//    override fun getItemCount(): Int {
//        return  list.size
//    }
//}

//class PlantsViewHolder( var binding: PlantsListBinding) : BaseViewHolder<com.example.tabiyat.data.model.PlantsModel>(binding.root) {
//    private var plantTitle = binding.plantTitle
//    private var plantDep = binding.plantDepartment
//    private var plantImg = binding.plantImg
//
//    override fun bind(item: com.example.tabiyat.data.model.PlantsModel, clickListener: OnItemClickListener) {
//        plantTitle.text = item.title
//        plantDep.text = item.department
//        Glide
//            .with(itemView)
//            .load(item.img)
//            .centerCrop()
//            .into(plantImg)
//    }
//}
//
//class AnimalsViewHolder(binding: PlantsListBinding) : BaseViewHolder<com.example.tabiyat.data.model.PlantsModel>(binding.root) {
//    private var animalTitle = binding.plantTitle
//    private var animalDep = binding.plantDepartment
//    private var animalImg = binding.plantImg
//
//    override fun bind(item: com.example.tabiyat.data.model.PlantsModel, clickListener: OnItemClickListener) {
//        animalTitle.text = item.title
//        animalDep.text = item.department

//        Glide
//            .with(itemView)
//            .load(item.img)
//            .centerCrop()
//            .into(animalImg)
//    }
//}
//
//class InfoViewHolder(binding: InfoListBinding) : BaseViewHolder<InfoModel>(binding.root) {
//    private var infoTitle = binding.infoTitle
//    private var infoImg = binding.infoImg
//
//    override fun bind(item: InfoModel, clickListener: OnItemClickListener) {
//        infoTitle.text = item.nameTxt
//        Glide
//            .with(itemView)
//            .load(item.srcImage)
//            .centerCrop()
//            .into(infoImg)
//    }
//}