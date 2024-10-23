package ca.qc.cgodin.miniprojet3mobilecegep

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.miniprojet3mobilecegep.models.Favoris

class FavorisListAdapter  constructor(
    context: Context,
    private val favorisViewModel: FavorisViewModel
) : RecyclerView.Adapter<FavorisListAdapter.FavorisViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var favoris = emptyList<Favoris>()


    inner class FavorisViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val NomItemView: TextView =
            itemView.findViewById(R.id.NomView)
        val BudgetItemView: TextView =
            itemView.findViewById(R.id.BudgetView)

        private val button1: Button = itemView.findViewById(R.id.btn_Suppr)

        init {
            button1.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val favoris = favoris[position]
                    favorisViewModel.delete(favoris)
                }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            FavorisViewHolder {
        val itemView = inflater.inflate(R.layout.favoris_item, parent,
            false)
        return FavorisViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: FavorisViewHolder, position: Int) {
        val current = favoris[position]
        holder.NomItemView.text = current.nom
        holder.BudgetItemView.text = "${current.budget}"
    }
    fun setFavoris(favoris: List<Favoris>) {
        this.favoris = favoris
        notifyDataSetChanged()
    }
    override fun getItemCount() = favoris.size
}