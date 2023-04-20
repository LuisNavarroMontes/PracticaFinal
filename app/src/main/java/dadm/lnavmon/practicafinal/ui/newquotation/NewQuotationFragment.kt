package dadm.lnavmon.practicafinal.ui.newquotation

import android.accounts.NetworkErrorException
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.google.android.material.snackbar.Snackbar
import dadm.lnavmon.practicafinal.R
import dadm.lnavmon.practicafinal.databinding.FragmentNewQuotationBinding
import dadm.lnavmon.practicafinal.utils.NoInternetException
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import java.net.UnknownHostException

@AndroidEntryPoint
class NewQuotationFragment : Fragment(R.layout.fragment_new_quotation), MenuProvider {
    private var _binding: FragmentNewQuotationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewQuotationViewModel by viewModels()
    private var userName: String = ""
    private var isFavoriteButtonVisible: Boolean = false
    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewQuotationBinding.bind(view)

        viewModel.userName.observe(viewLifecycleOwner) { name ->
            userName = name
            binding.tvGreetings.text = getString(R.string.bienvenida, userName)
        }

        viewModel.quotation.observe(viewLifecycleOwner) { quotation ->
            binding.tvQuotation.text = quotation?.quote ?: ""
            binding.tvAuthor.text = if (quotation.author.isEmpty()) "Anonymous" else quotation.author
            binding.swipeToRefresh.isRefreshing = viewModel.isGettingNewQuotation.value ?: false
            binding.tvGreetings.isVisible = viewModel.isGreetingsVisible.value ?: false
        }
        viewModel.isGettingNewQuotation.observe(viewLifecycleOwner) { isRefreshing ->
            binding.swipeToRefresh.isRefreshing = isRefreshing == true
        }

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getNewQuotation()
        }
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        viewModel.getNewQuotation()

        viewModel.isFavoriteButtonVisible.observe(viewLifecycleOwner) { isVisible ->
            binding.btnAddToFavourites.visibility = if (isVisible == true) View.VISIBLE else View.GONE
        }
        binding.btnAddToFavourites.setOnClickListener {
            viewModel.addToFavourites()
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            val errorMessage = when (error) {
                is NetworkErrorException -> getString(R.string.network_error_message)
                is HttpException -> getString(R.string.http_error_message, error.code())
                is NoInternetException -> getString(R.string.no_internet_error_message)
                is UnknownHostException -> getString(R.string.unknown_host_error_message)
                else -> getString(R.string.unexpected_error_message)
            }
            Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_LONG).show()
            viewModel.resetError()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_new_quotation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_refresh -> {
                viewModel.getNewQuotation()
                true
            }
            else -> false
        }
    }
}