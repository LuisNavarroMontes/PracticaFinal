package dadm.lnavmon.practicafinal.ui.newquotation

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
import dadm.lnavmon.practicafinal.R
import dadm.lnavmon.practicafinal.databinding.FragmentNewQuotationBinding

class NewQuotationFragment : Fragment(R.layout.fragment_new_quotation), MenuProvider {
    private var _binding: FragmentNewQuotationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewQuotationViewModel by viewModels()
    private var userName: String = ""
    private var isFavoriteButtonVisible: Boolean = false
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