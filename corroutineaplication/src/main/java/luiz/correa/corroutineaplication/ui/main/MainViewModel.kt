package luiz.correa.corroutineaplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    /*  Recebe via injeção de dependencia o repositório
     construtor do MainViewModel, código desaclopado*/
    private val repository:MainRepository) : ViewModel(){

    // LiveData criado
    val moviesLiveData = MutableLiveData<List<Movie>>()

     // após obter a lista de filmes, irá acionar o LiveData, para que tiver escuta (MainFragment)
    fun getMoviesCoroutines(){

       /*  criando contexto de coroutine
         será executado na mainThead  e dentro deste contexto será chamado o repositório,
         que será executado numa thead separada*/
        CoroutineScope(Dispatchers.Main).launch {

          /*  executado em outra thread
            dentro de um scope de coroutine só aceita função suspensa*/
            val movies = withContext(Dispatchers.Default) {
                repository.getMoviesCorroutines()
            } // retorna a variável de filmes por outra thread

            // obs:  value é aplicavel, por está no contexto da thread principal
            moviesLiveData.value =  movies  // passando a lista de filmes para liveDate


        } // executado dentro da thread principal

    }

    // este recebe o reposítório
    class MainViewModelFactory(private val repository:MainRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            // criando o viewModel para ser iniciado corretamente
            return MainViewModel(repository) as T
        }

    }
}