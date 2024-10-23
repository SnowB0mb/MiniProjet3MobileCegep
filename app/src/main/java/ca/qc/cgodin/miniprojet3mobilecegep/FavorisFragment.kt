package ca.qc.cgodin.miniprojet3mobilecegep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.FragmentFavorisBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavorisFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavorisFragment : Fragment() {
    private lateinit var binding: FragmentFavorisBinding
    private lateinit var favorisViewModel: FavorisViewModel
    private lateinit var favorisAdapter: FavorisListAdapter
    //val args: FavorisFragmentArgs by navArgs()


    // TODO: Rename and change types of parameters
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
        binding = FragmentFavorisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavorisBinding.bind(view)

        // Initialize RecyclerView
        binding.rvFavoris.layoutManager = LinearLayoutManager(requireContext())
        favorisViewModel = ViewModelProvider(this).get(FavorisViewModel::class.java)
        favorisAdapter = FavorisListAdapter(requireContext(), favorisViewModel)
        binding.rvFavoris.adapter = favorisAdapter

        // Initialize ViewModel
        favorisViewModel = ViewModelProvider(this).get(FavorisViewModel::class.java)

        // Observe LiveData from ViewModel
        favorisViewModel.allFavoris.observe(viewLifecycleOwner, Observer { favoris ->
            favoris?.let { favorisAdapter.setFavoris(it) }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavorisFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavorisFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}