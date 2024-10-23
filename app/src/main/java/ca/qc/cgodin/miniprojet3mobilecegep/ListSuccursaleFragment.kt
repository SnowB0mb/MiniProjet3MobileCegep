package ca.qc.cgodin.miniprojet3mobilecegep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.FragmentListSuccursaleBinding
import ca.qc.cgodin.miniprojet3mobilecegep.models.Succursale

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListSuccursaleFragment : Fragment() {
    private lateinit var binding: FragmentListSuccursaleBinding
    private val viewModel: SuccursaleViewModel by navGraphViewModels(R.id.succursale_nav_graph)
    private lateinit var succursaleAdapter: SuccursaleAdapter
    val args: ListSuccursaleFragmentArgs by navArgs()

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListSuccursaleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListSuccursaleBinding.bind(view)
        binding.recyclerViewSuccursale.layoutManager = LinearLayoutManager(requireContext())
        succursaleAdapter = SuccursaleAdapter()
        binding.recyclerViewSuccursale.adapter = succursaleAdapter
        binding.recyclerViewSuccursale.visibility = View.VISIBLE

        nbSuccursales()
        listSuccursales()
        binding.editSearchSuccursale.setOnClickListener() {
            var strEditSearch = binding.editSearchSuccursale.text.toString()
            if (strEditSearch.isNotBlank()) {
                budgetSuccursale(strEditSearch)
            } else {
                binding.recyclerViewSuccursale.visibility = View.VISIBLE
                listSuccursales()
            }
        }
    }

    private fun budgetSuccursale(strEditSearch: String) {
        viewModel.getBudgetSuccursale(args.aut.toString(), strEditSearch)

        viewModel.responseBudgetSuccursale.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.statut == "OK") {
                    var listSuccursale: List<Succursale> = listOf(responseBody.succursale)
                    binding.tvRienAfficher.visibility = View.GONE
                    binding.recyclerViewSuccursale.visibility = View.VISIBLE
                    succursaleAdapter.setListSuccursales(listSuccursale)
                    binding.recyclerViewSuccursale.adapter = succursaleAdapter
                } else if (responseBody != null && responseBody.statut == "PASOK") {
                    binding.tvRienAfficher.text = getString(R.string.succursale_inexistante)
                    binding.tvRienAfficher.visibility = View.VISIBLE
                    binding.recyclerViewSuccursale.visibility = View.GONE

                }
            }
        })
    }

    private fun listSuccursales() {
        viewModel.getListSuccursale(args.aut.toString())
        //Observer pour la liste des succursales
        viewModel.responseListSuccursale.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.statut == "OK") {
                    binding.tvRienAfficher.visibility = View.GONE
                    succursaleAdapter.setListSuccursales(responseBody.succursales)
                    binding.recyclerViewSuccursale.adapter = succursaleAdapter
                } else if (responseBody != null && responseBody.statut == "AUCUNE") {
                    binding.tvRienAfficher.text = getString(R.string.rien_a_afficher)
                    binding.tvRienAfficher.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun nbSuccursales(){
        viewModel.getNbSuccursales(args.aut.toString())
        //Observer pour la liste des succursales
        viewModel.responseNbSuccursale.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.statut == "OK") {
                    binding.tvRienAfficher.visibility = View.GONE
                    binding.tvNbSuccursales.text = responseBody.nbSuccursales
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListSuccursaleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}