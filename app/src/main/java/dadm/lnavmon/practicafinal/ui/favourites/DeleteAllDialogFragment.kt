package dadm.lnavmon.practicafinal.ui.favourites

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dadm.lnavmon.practicafinal.R

open class DeleteAllDialogFragment() : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Delete all favourite quotations")
            .setMessage("Do you really want to delete all quotations?")
            .setPositiveButton("Delete All") { _, _ ->
                val viewModel: FavouritesViewModel by activityViewModels()
                viewModel.deleteAllQuotations()
                Toast.makeText(
                    requireContext(),
                    "Quotations has been deleted",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

}