package ca.qc.cgodin.miniprojet3mobilecegep

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.FavorisItemBinding
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.FragmentFavorisBinding
import ca.qc.cgodin.miniprojet3mobilecegep.models.Favoris
import ca.qc.cgodin.miniprojet3mobilecegep.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class FavorisAdapter(
    private val favorisList: List<Favoris>) :
    RecyclerView.Adapter<FavorisAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavorisItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favorisList[position]
        holder.idView.text = item.succursaleVille
        holder.contentView.text = item.succursaleBudget.toString()
    }

    override fun getItemCount(): Int = favorisList.size

    inner class ViewHolder(binding: FavorisItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.textView12
        val contentView: TextView = binding.textView11


        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}