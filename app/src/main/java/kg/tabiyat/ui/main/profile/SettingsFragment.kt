package kg.tabiyat.ui.main.profile

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.civitasv.ioslike.dialog.DialogNormal
import com.civitasv.ioslike.model.DialogTextStyle
import kg.tabiyat.App
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.databinding.SettingsFragmentBinding
import kg.tabiyat.ui.main.profile.viewModels.SettingsViewModel
import org.koin.android.ext.android.inject
import java.util.*


class SettingsFragment : BaseFragment<SettingsFragmentBinding>(SettingsFragmentBinding::inflate), View.OnClickListener {
    private var lang: String = String()
    private val viewModel by inject<SettingsViewModel>()

    override fun setUpViews() {
        super.setUpViews()
        settingsClickListener(
            binding.settingsProject,
            R.id.action_menuSettings_to_projectInfoFragment
        )
        binding.settingsExit.setOnClickListener(this)
        languageSwitch()
    }


    private fun languageSwitch() {
        binding.settingsLangSwitch.setOnCheckedChangeListener { _, checkedId ->
          when (checkedId) {
                R.id.ru_lang -> {
                    lang = "ru"
                }
                R.id.kg_lang ->{

                    lang = "kg"
                }
                else -> {
                    lang = "ru"
                }
            }
        }
    }



    private fun settingsClickListener(textView: TextView, action: Int) {
        textView.setOnClickListener {
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onClick(v: View?) {
        DialogNormal(requireContext())
            .setTitle(getString(R.string.you_want_exict))
            .setContent(getString(R.string.you_always_can_return))
            .setConfirm(
                getString(R.string.exit), { _ ->
                    activity?.moveTaskToBack(true)
                    activity?.finish()
                },
                true
            )
            .setCancel(
                getString(R.string.cancel),
                true
            )
            .setCancelStyle(
                DialogTextStyle.Builder(requireContext()).color(R.color.blue).build()
            )
            .setConfirmStyle(
                DialogTextStyle.Builder(requireContext()).color(R.color.blue).build()
            )
            .setCanceledOnTouchOutside(true)
            .show()


    }


}


