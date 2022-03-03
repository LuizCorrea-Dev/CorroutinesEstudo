package luiz.correa.corroutineaplication.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepository {

    suspend fun getMoviesCorroutines() : List<Movie>{

        // criando Thread do corroutine, passando o contexto do corroutine como  retorno

        return withContext(Dispatchers.Default) {
            // dentro do contexto de uma corroutine

            // simulação de delay de 3 segundos
            delay(3000)

            // retorna lista de filmes para o callback
            listOf(
                Movie(1,"Movie title 01"),
                Movie(2,"Movie title 02"),
                Movie(3,"Movie title 03")
            )


        }
    }
}