package ca.qc.cgodin.miniprojet3mobilecegep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.miniprojet3mobilecegep.models.Favoris
import ca.qc.cgodin.miniprojet3mobilecegep.models.Succursale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuccursaleAdapter(private val favorisViewModel: FavorisViewModel) :
    RecyclerView.Adapter<SuccursaleAdapter.SuccursaleViewHolder>() {
    private var succursales: List<Succursale> = emptyList()



    inner class SuccursaleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvVille: TextView = itemView.findViewById(R.id.tvListeVille)
        val tvBudget: TextView = itemView.findViewById(R.id.tvListeBudget)
        val button1: Button = itemView.findViewById(R.id.btnFavoris)

        init {
            button1.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val succursale = succursales[position]
                    val favoris = Favoris(nom = succursale.ville, budget = succursale.budget)
                    favorisViewModel.insert(favoris)
                    button1.isEnabled = false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuccursaleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.succursale_item, parent, false)
        return SuccursaleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuccursaleViewHolder, position: Int) {
        val succursale = succursales[position]
        holder.tvVille.text = succursale.ville
        holder.tvBudget.text = succursale.budget.toString()

        // Vérifier si la succursale est déjà dans la liste des favoris
        val favorisLiveData: LiveData<List<Favoris>> = favorisViewModel.allFavoris
        favorisLiveData.observeForever(object : Observer<List<Favoris>> {
            override fun onChanged(favorisList: List<Favoris>) {
                if (favorisList != null) {
                    val isFavoris = favorisList.any { it.nom == succursale.ville && it.budget == succursale.budget }
                    holder.button1.isEnabled = !isFavoris
                }
                favorisLiveData.removeObserver(this)
            }
        })
    }
    override fun getItemCount(): Int = succursales.size



    fun setListSuccursales(succursales: List<Succursale>) {
        this.succursales = succursales
        notifyDataSetChanged()
    }
}