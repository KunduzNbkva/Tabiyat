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
import kg.tabiyat.databinding.SettingsFragmentBinding
import kg.tabiyat.ui.main.profile.viewModels.SettingsViewModel
import org.koin.android.ext.android.inject
import java.util.*


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
        languageSwitch()
    }


    private fun languageSwitch() {
        binding.settingsLangSwitch.setOnCheckedChangeListener { _, checkedId ->
          when (checkedId) {
                R.id.ru_lang -> {
                    setLocale("ru")
                    lang = "ru"
                }
                R.id.kg_lang ->{
                    setLocale("kg")
                    lang = "kg"
                }
                else -> {
                    setLocale("ru")
                    lang = "ru"
                }
            }
        }
    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        requireContext().resources.updateConfiguration(
            config,
            requireContext().resources.displayMetrics
        )
        App.prefs!!.saveLang(lang)
    }

    override fun onResume() {
        loadLocaleLang()
        super.onResume()
    }

    private fun loadLocaleLang() {
        val language: String = App.prefs!!.lanquage!!
            setLocale(language)
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
                getString(R.string.exit), { v2 ->
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


