package ca.qc.cgodin.miniprojet3mobilecegep

import android.content.Intent
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
        binding.tvNomPrenom.text = getString(R.string.compte_nom_prenom, intent.getStringExtra("prenom"), intent.getStringExtra("nom"))

        //Ajout de listeners aux items du menu pour pouvoir envoyer le matricule aux fragments
        binding.bottomNavigationMenu.menu.findItem(R.id.operationSuccursaleFragment).setOnMenuItemClickListener {
            navController.navigate(R.id.operationSuccursaleFragment, bundle)
            true
        }
        binding.bottomNavigationMenu.menu.findItem(R.id.listSuccursaleFragment).setOnMenuItemClickListener {
            navController.navigate(R.id.listSuccursaleFragment, bundle)
            true
        }
        //Décommenter quand le fragment sera ajouté
//        binding.bottomNavigationMenu.menu.findItem(R.id.savedSuccursaleFragment).setOnMenuItemClickListener {
//            navController.navigate(R.id.savedSuccursaleFragment, bundle)
//            true
//        }
        //Le premier fragment à afficher (le start destination définit dans le nav_graph) avec le matricule de l'utilisateur
        navController.navigate(navController.graph.startDestinationId, bundle)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deconnexionItem -> {
                val intent = Intent(this@SuccursaleActivity, MainActivity::class.java)
                startActivity(intent)
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }
}