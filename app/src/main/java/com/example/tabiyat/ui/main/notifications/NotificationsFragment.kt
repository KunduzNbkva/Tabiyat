package com.example.tabiyat.ui.main.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.databinding.NotificationsFragmentBinding
import com.example.tabiyat.ui.main.notifications.adapter.NotificationsAdapter
import org.koin.android.ext.android.inject

class NotificationsFragment : Fragment() {
    private lateinit var binding: NotificationsFragmentBinding
    private lateinit var adapter: NotificationsAdapter
    private var listOfNotifications: ArrayList<String>? = null
    private val viewModel by inject<NotificationsViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NotificationsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // checkForNotifications()
        setUpNotificationsRecycler()
    }

    private fun setUpNotificationsRecycler() {
        val list = arrayListOf("Уведомления","Уведомления","Уведомления","Уведомления")
        binding.notificationsList.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.HORIZONTAL))
        binding.notificationsList.layoutManager = LinearLayoutManager(requireContext())
        adapter = NotificationsAdapter(list)
        binding.notificationsList.adapter = adapter
    }

    private fun checkForNotifications(){
        if(listOfNotifications!=null){
            setUpNotificationsRecycler()
        } else {
            binding.notificationsList.visibility = View.INVISIBLE
            binding.noNotificationsLayout.visibility = View.VISIBLE
        }
    }

}