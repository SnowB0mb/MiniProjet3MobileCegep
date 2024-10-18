package ca.qc.cgodin.miniprojet3mobilecegep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.FragmentConnexionBinding
import ca.qc.cgodin.miniprojet3mobilecegep.repository.SuccursalesRepository

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ConnexionFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: SuccursaleViewModel by viewModels{SuccursaleViewModelProviderFactory(
        SuccursalesRepository()
    )}
    private lateinit var binding: FragmentConnexionBinding

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
        binding = FragmentConnexionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConnexionBinding.bind(view)

        binding.btnConnexion.setOnClickListener() {
            viewModel.connectUser(
                binding.editMatricule.text.toString(),
                binding.editMDP.text.toString()
            )
        }
        viewModel.responseConnexion.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.statut == "OK") {
                    // Connection successful, update UI accordingly
                    binding.tvStatus.text = "Connection successful"
                } else {
                    // Connection failed, update UI accordingly
                    binding.tvStatus.text = "Connection failed: ${responseBody?.error ?: "Unknown error"}"
                }
            } else {
                // Handle the error response
                binding.tvStatus.text = "Connection failed: ${response.message()}"
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConnexionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}