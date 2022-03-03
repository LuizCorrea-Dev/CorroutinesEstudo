package luiz.correa.corroutinesestudo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(
   /*  Recebe via injeção de dependencia o repositório
    construtor do MainViewModel, código desaclopado*/
    private val repository:MainRepository) : ViewModel(){

    // LiveData criado
        val moviesLiveData = MutableLiveData<List<Movie>>()

    // após obter a lista de filmes, irá acionar o LiveData, para que tiver escuta (MainFragment)
        fun getMovies(){
            repository.getMovies { movies ->
                moviesLiveData.postValue(movies) // passando a lista de filmes para liveDate
            }  /* obs: o postValue entrega os dados na Thread principal,
                  em quanto o value entrega na Thread que veio*/
        }

    // este recebe o reposítório
    class MainViewModelFactory(private val repository:MainRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            // criando o viewModel para ser iniciado corretamente
                return MainViewModel(repository) as T
        }

    }
}