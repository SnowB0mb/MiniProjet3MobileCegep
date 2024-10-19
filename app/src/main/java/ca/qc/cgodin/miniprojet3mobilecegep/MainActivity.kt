package ca.qc.cgodin.miniprojet3mobilecegep

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.ActivityMainBinding
import ca.qc.cgodin.miniprojet3mobilecegep.repository.SuccursalesRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var succursaleViewModel: SuccursaleViewModel
    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostFragment.navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val succursalesRepository = SuccursalesRepository()
        val viewModelProviderFactory = SuccursaleViewModelProviderFactory(succursalesRepository)

        try{
            val viewModelProvider = ViewModelProvider(navController.getViewModelStoreOwner(R.id.succursale_nav_graph), viewModelProviderFactory)
            succursaleViewModel = viewModelProvider.get(SuccursaleViewModel::class.java)
        }catch (e: IllegalArgumentException){
            e.printStackTrace()
        }
    }
}