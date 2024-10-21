package ca.qc.cgodin.miniprojet3mobilecegep

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgument
import androidx.navigation.ui.setupWithNavController
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.ActivityMainBinding
import ca.qc.cgodin.miniprojet3mobilecegep.databinding.ActivitySuccursaleBinding
import ca.qc.cgodin.miniprojet3mobilecegep.repository.SuccursalesRepository

class SuccursaleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuccursaleBinding
    lateinit var succursaleViewModel: SuccursaleViewModel
    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySuccursaleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val succursalesRepository = SuccursalesRepository()
        val viewModelProviderFactory = SuccursaleViewModelProviderFactory(succursalesRepository)
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.bottomNavigationMenu.setupWithNavController(navController)
        try{
            val viewModelProvider = ViewModelProvider(navController.getViewModelStoreOwner(R.id.succursale_nav_graph), viewModelProviderFactory)
            succursaleViewModel = viewModelProvider.get(SuccursaleViewModel::class.java)
        }catch (e: IllegalArgumentException){
            e.printStackTrace()
        }
        val bundle = Bundle().apply{
            putSerializable("aut", intent.getStringExtra("aut"))
        }
        navController.navigate(R.id.listSuccursaleFragment, bundle)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deconnexionItem -> {
                //ajouter une activité pour contenir la page de connexion, ensuite la remplacer
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }
}