package dadm.lnavmon.practicafinal.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import dadm.lnavmon.practicafinal.R
import dadm.lnavmon.practicafinal.databinding.FragmentNewQuotationBinding
import dadm.lnavmon.practicafinal.databinding.FragmentSettingsBinding

class SettingsFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }
}