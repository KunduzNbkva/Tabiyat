package kg.tabiyat.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.civitasv.ioslike.dialog.DialogNormal
import com.civitasv.ioslike.model.DialogTextStyle
import kg.tabiyat.R
import kg.tabiyat.databinding.SettingsFragmentBinding
import kg.tabiyat.ui.main.profile.viewModels.SettingsViewModel
import org.koin.android.ext.android.inject


class SettingsFragment : Fragment(), View.OnClickListener {
    private var lang: String = String()
    private lateinit var binding: SettingsFragmentBinding
    private val viewModel by inject<SettingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsClickListener(
            binding.settingsProject,
            R.id.action_menuSettings_to_projectInfoFragment
        )
        binding.settingsExit.setOnClickListener(this)
    }


    private fun languageSwitch(): String {
        binding.settingsLangSwitch.setOnCheckedChangeListener { _, checkedId ->
            lang = when (checkedId) {
                R.id.ru_lang -> "ru"
                R.id.kg_lang -> "kg"
                else -> "ru"
            }
        }
        return lang
    }

    private fun settingsClickListener(textView: TextView, action: Int) {
        textView.setOnClickListener {
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onClick(v: View?) {
        DialogNormal(requireContext())
            .setTitle("Вы хотите выйти?")
            .setContent("вы всегда можете вернуться")
            .setConfirm(
                "Выйти", { v2 ->
                    activity?.moveTaskToBack(true)
                    activity?.finish()
                },
                true
            )
            .setCancel(
                "Отмена",
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


