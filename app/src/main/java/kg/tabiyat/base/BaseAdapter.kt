package kg.tabiyat.base


//abstract class BaseAdapter<Data : Any, T : ViewBinding>(
//    private var dataList: List<Data>,
//    private val inflate: (LayoutInflater) -> T,
//) : RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>() {
//
//    abstract val layoutItemId: Int
//    private var _binding: T? =null
//    val binding get()= _binding!!
//
//    override fun getItemCount(): Int = dataList.size
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
//        _binding = inflate.invoke(LayoutInflater.from(parent.context))
//        return BaseViewHolder(binding)
//    }
//
//    inner class BaseViewHolder<binding : T>(binding: T) : RecyclerView.ViewHolder(this.binding?.root!!)
//}
//}
//open class BindingViewHolder<T : ViewBinding> private constructor(
//    val binding: T
//) : RecyclerView.ViewHolder(binding.root) {
//    constructor(
//        parent: ViewGroup,
//        creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
//    ) : this(
//        creator(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//
//    )
//}

//fun <T : ViewBinding> ViewGroup.viewHolderFrom(
//    creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
//): BindingViewHolder<T> = BindingViewHolder(this, creator)