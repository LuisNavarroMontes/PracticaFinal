package dadm.lnavmon.practicafinal.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.lnavmon.practicafinal.databinding.QuotationItemBinding
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation

interface ItemClicked {
    fun onClick(author: String)
}


class QuotationListAdapter(private val callback: FavouritesFragment) : ListAdapter<Quotation, QuotationListAdapter.ViewHolder>(QuotationDiff)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = QuotationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: QuotationItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback.onClick(binding.textViewAuthor.text.toString())
            }
        }

        fun bind(quotation: Quotation) {
            binding.textViewQuotation.text = quotation.quote
            binding.textViewAuthor.text = quotation.author
        }
    }

    object QuotationDiff : DiffUtil.ItemCallback<Quotation>() {
        override fun areItemsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem == newItem
        }
    }
}