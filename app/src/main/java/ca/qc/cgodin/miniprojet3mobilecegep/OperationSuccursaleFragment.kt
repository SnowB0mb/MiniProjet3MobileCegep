package ca.qc.cgodin.miniprojet3mobilecegep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.FragmentOperationSuccursaleBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OperationSuccursaleFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentOperationSuccursaleBinding
    private val viewModel: SuccursaleViewModel by navGraphViewModels(R.id.succursale_nav_graph)
    private lateinit var succursaleAdapter: SuccursaleAdapter
    private lateinit var favorisViewModel: FavorisViewModel
    val args: OperationSuccursaleFragmentArgs by navArgs()

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
        binding = FragmentOperationSuccursaleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOperationSuccursaleBinding.bind(view)
        favorisViewModel = ViewModelProvider(this).get(FavorisViewModel::class.java)
        succursaleAdapter = SuccursaleAdapter(favorisViewModel)

        binding.btnEffacerAjoutModif.setOnClickListener() {
            binding.tvStatutAjoutModif.text = ""
            binding.editVilleAjoutModif.text.clear()
            binding.editBudgetAjoutModif.text.clear()
        }
        binding.btnEffacerRetrait.setOnClickListener() {
            binding.tvStatutRetrait.text = ""
            binding.editVilleRetrait.text.clear()
        }

        binding.btnSoumettreAjoutModif.setOnClickListener() {
            ajouterModifierSuccursale()
        }
        binding.btnSoumettreRetrait.setOnClickListener() {
            retirerSuccursale()
        }
    }

    private fun ajouterModifierSuccursale() {
        var ville = binding.editVilleAjoutModif.text.toString()
        var budget = binding.editBudgetAjoutModif.text.toString()
        var boolVille = true
        var boolBudget = true

        if (ville.isBlank()) {
            binding.tvStatutAjoutModif.text = getString(R.string.ville_invalide)
            boolVille = false
        }
        if (ville.length > 20) {
            binding.tvStatutAjoutModif.text = getString(R.string.ville_invalide)
            boolVille = false
        }
        if (budget.isBlank()) {
            binding.tvStatutAjoutModif.text = getString(R.string.budget_invalide)
            boolBudget = false
        } else {
            if (!budget.isDigitsOnly()) {
                binding.tvStatutAjoutModif.text = getString(R.string.budget_invalide)
                boolBudget = false
            } else if (budget.isDigitsOnly() && budget.toInt() !in 500..9999999) {
                binding.tvStatutAjoutModif.text = getString(R.string.budget_invalide)
                boolBudget = false
            }
        }
        if (!boolVille && !boolBudget) {
            binding.tvStatutAjoutModif.text = getString(R.string.ville_budget_invalide)
        }

        if (boolVille && boolBudget) {
            viewModel.ajouterModifierSuccursale(args.aut.toString(), ville, budget)

            viewModel.responseAjoutSuccursale.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.statut == "OKI") {
                        binding.tvStatutAjoutModif.text = getString(R.string.operation_ajouter_ok)
                    }
                    if (responseBody != null && responseBody.statut == "OKM") {
                        binding.tvStatutAjoutModif.text = getString(R.string.operation_retrait_ok)
                    }
                    if(responseBody != null && responseBody.statut == "PASOK"){
                        binding.tvStatutAjoutModif.text = getString(R.string.operation_succursale_existe)
                    }
                }
            })
        }
    }

    private fun retirerSuccursale() {
        var ville = binding.editVilleRetrait.text.toString()
        var boolVille = true

        if (ville.isBlank()) {
            binding.tvStatutRetrait.text = getString(R.string.ville_invalide)
            boolVille = false
        }
        if (ville.length > 20) {
            binding.tvStatutRetrait.text = getString(R.string.ville_invalide)
            boolVille = false
        }

        if (boolVille) {
            viewModel.retirerSuccursale(args.aut.toString(), ville)

            viewModel.responseRetraitSuccursale.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.statut == "OK") {
                        binding.tvStatutRetrait.text = getString(R.string.operation_retrait_ok)
                    }
                    if(responseBody != null && responseBody.statut == "PASOK"){
                        binding.tvStatutRetrait.text = getString(R.string.succursale_inexistante)
                    }
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OperationSuccursaleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}