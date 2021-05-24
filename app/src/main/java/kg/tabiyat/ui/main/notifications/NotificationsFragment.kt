package kg.tabiyat.ui.main.notifications


import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.databinding.NotificationsFragmentBinding
import kg.tabiyat.ui.main.notifications.adapter.NotificationsAdapter
import org.koin.android.ext.android.inject

class NotificationsFragment : BaseFragment<NotificationsFragmentBinding>(NotificationsFragmentBinding::inflate) {
    private lateinit var adapter: NotificationsAdapter
    private var listOfNotifications: ArrayList<String>? = null
    private val viewModel by inject<NotificationsViewModel>()

    override fun setUpViews() {
        super.setUpViews()
        // checkForNotifications()
        setUpNotificationsRecycler()
    }

    override fun observeData() {
        super.observeData()
    }

    private fun setUpNotificationsRecycler() {
        val list = arrayListOf("Уведомления", "Уведомления", "Уведомления", "Уведомления")
        binding.notificationsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.HORIZONTAL
            )
        )
        binding.notificationsList.layoutManager = LinearLayoutManager(requireContext())
        adapter = NotificationsAdapter(list)
        binding.notificationsList.adapter = adapter
    }

    private fun checkForNotifications() {
        if (listOfNotifications != null) {
            setUpNotificationsRecycler()
        } else {
            binding.notificationsList.visibility = View.INVISIBLE
            binding.noNotificationsLayout.visibility = View.VISIBLE
        }
    }

}