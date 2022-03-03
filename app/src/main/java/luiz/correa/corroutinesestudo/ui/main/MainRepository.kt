package luiz.correa.corroutinesestudo.ui.main

class MainRepository {

    fun getMovies(
        callback:(movies: List<Movie>) -> Unit // passar a lista de filmes para este callback
    ){

        // simulando uma chamada assincrona utilizando uma thread do java

        Thread(Runnable { // thread criada

            // simulação de delay de 3 segundos
            Thread.sleep(3000)

            // retorna lista de filmes para o callback
            callback.invoke(
                listOf(
                    Movie(1,"Movie title 1"),
                    Movie(2,"Movie title 2"),
                    Movie(3,"Movie title 3")
                )
            )
        }).start()
    }
}