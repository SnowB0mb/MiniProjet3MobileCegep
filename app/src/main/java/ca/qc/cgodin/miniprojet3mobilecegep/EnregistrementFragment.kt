package ca.qc.cgodin.miniprojet3mobilecegep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.FragmentConnexionBinding
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.FragmentEnregistrementBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EnregistrementFragment : Fragment() {
    private lateinit var binding: FragmentEnregistrementBinding
    private val viewModel: SuccursaleViewModel by navGraphViewModels(R.id.user_nav_graph)
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
        binding = FragmentEnregistrementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnregistrementBinding.bind(view)

        binding.btnEnregistrer.setOnClickListener() {
            btnEnregistrerOnClick()
        }
        binding.btnAnnuler.setOnClickListener() {
            findNavController().navigate(R.id.action_enregistrementFragment_to_connexionFragment)
        }

    }

    private fun btnEnregistrerOnClick() {
        val strMdp = binding.editMdp.text.toString()
        val strMdp2 = binding.editMdp2.text.toString()
        val strNom = binding.editNom.text.toString()
        val strPrenom = binding.editPrenom.text.toString()
        val strMatricule = binding.editAut.text.toString()
        var boolValide = true

        if (strMatricule.isEmpty()) {
            binding.tvEnregistrementErreurMatricule.text = getString(R.string.erreur_champ_vide)
            boolValide = false
        }
        if (strMdp.isEmpty()) {
            boolValide = false
            binding.tvEnregistrementMdpErreur.text = getString(R.string.erreur_champ_vide)
        }
        if (strMdp2.isEmpty()) {
            boolValide = false
            binding.tvEnregistrementMdp2Erreur.text = getString(R.string.erreur_champ_vide)
        }
        if (strMdp != strMdp2) {
            boolValide = false
            binding.tvEnregistrementStatut.text = "Les mots de passe ne correspondent pas"
        }
        if (strNom.isEmpty()) {
            boolValide = false
            binding.tvEnregistrementNomErreur.text = getString(R.string.erreur_champ_vide)
        }
        if (strPrenom.isEmpty()) {
            boolValide = false
            binding.tvEnregistrementPrenomErreur.text = getString(R.string.erreur_champ_vide)
        }

        if (boolValide) {
            viewModel.registerUser(
                binding.editAut.text.toString(),
                binding.editMdp.text.toString(),
                binding.editNom.text.toString(),
                binding.editPrenom.text.toString(),
            )
        }
        viewModel.responseEnregistrement.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.statut == "OK") {
                    // Connection successful, update UI accordingly
                    binding.tvEnregistrementStatut.text = "Connection successful"
                    Toast.makeText(context, "Inscription réussie", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_enregistrementFragment_to_connexionFragment)
                }
                else{
                    // Connection failed, update UI accordingly
                    binding.tvEnregistrementStatut.text =
                        "${responseBody?.error ?: "Unknown error"}"
                }
            } else {
                // Handle the error response
                binding.tvEnregistrementStatut.text = "${response.message()}"
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EnregistrementFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}