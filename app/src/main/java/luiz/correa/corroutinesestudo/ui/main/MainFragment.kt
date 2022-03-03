package luiz.correa.corroutinesestudo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import luiz.correa.corroutinesestudo.R
import luiz.correa.corroutinesestudo.databinding.MainActivityBinding
import luiz.correa.corroutinesestudo.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* viewModelProvider passando o ciclo de vida "owner" que é o fragmento, como segundo parâmetro
         passando um factory que permite que crie um viewModel, e passando para este ViewModel o
         repositório como injeção de dependencia, poruqe o ViewModel recebe no seu contrutor o repositório*/
        viewModel = ViewModelProvider(
            this,
            //criando uma instancia do repositório e injetando no viewModel
            MainViewModel.MainViewModelFactory(MainRepository())
        )[MainViewModel::class.java]

        // escutar o LiveData que está no ViewModel pelo callback
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { movies ->
            // temos a lista de filmes retornada

            binding.tvMovies.text = movies[0].title
        })

        // instanciando o ViewModel, fazendo toda a chamada e chegar ao repositório
        viewModel.getMovies()


    }


}