package ca.qc.cgodin.miniprojet3mobilecegep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.miniprojet3mobilecegep.models.Succursale

class SuccursaleAdapter() :
    RecyclerView.Adapter<SuccursaleAdapter.SuccursaleViewHolder>() {
    private var succursales: List<Succursale> = emptyList()

    inner class SuccursaleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvVille: TextView = itemView.findViewById(R.id.tvListeVille)
        val tvBudget: TextView = itemView.findViewById(R.id.tvListeBudget)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuccursaleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.succursale_item, parent, false)
        return SuccursaleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuccursaleViewHolder, position: Int) {
        val succursale = succursales[position]
        holder.tvVille.text = succursale.ville
        holder.tvBudget.text = succursale.budget.toString()

    }

    override fun getItemCount(): Int = succursales.size

    fun setListSuccursales(articles: List<Succursale>) {
        this.succursales = articles
        notifyDataSetChanged()
    }
}