package dadm.lnavmon.practicafinal.ui.favourites

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dadm.lnavmon.practicafinal.R
import dadm.lnavmon.practicafinal.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites), MenuProvider {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouritesViewModel by activityViewModels()
    private lateinit var adapter: QuotationListAdapter

    private val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun isLongPressDragEnabled(): Boolean {
            return false
        }

        override fun isItemViewSwipeEnabled(): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewModel.deleteQuotationAtPosition(viewHolder.adapterPosition)
        }
    })
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        adapter = QuotationListAdapter(this)

        binding.recyclerViewFavourites.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFavourites.adapter = adapter

        viewModel.favoriteQuotationsLiveData.observe(viewLifecycleOwner) { quotations ->
            adapter.submitList(quotations)
        }
        viewModel.isDeleteAllVisible.observe(viewLifecycleOwner) {
            requireActivity().invalidateOptionsMenu()
        }
        touchHelper.attachToRecyclerView(binding.recyclerViewFavourites)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onClick(author: String) {
        if (author == "Anonymous") {
            Snackbar.make(binding.root, "No se puede mostrar información sobre autores anónimos", Snackbar.LENGTH_SHORT).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://en.wikipedia.org/wiki/Special:Search?search=$author")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Snackbar.make(binding.root, "No es posible gestionar la acción solicitada", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_favourites, menu)
    }

    override fun onMenuItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuFavouritesDelete -> {
                DeleteAllDialogFragment().show(parentFragmentManager, "delete_all_dialog")
                true
            }
            else -> false
        }
    }

    override fun onPrepareMenu(menu: Menu) {
        menu.findItem(R.id.menuFavouritesDelete)?.isVisible = viewModel.isDeleteAllVisible.value == true
    }
}