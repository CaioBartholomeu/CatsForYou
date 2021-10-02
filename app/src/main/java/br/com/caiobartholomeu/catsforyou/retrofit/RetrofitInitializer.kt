package br.com.caiobartholomeu.catsforyou.retrofit

import br.com.caiobartholomeu.catsforyou.retrofit.service.NoteService
import retrofit.GsonConverterFactory
import retrofit.Retrofit

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/gallery/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun noteService() = retrofit.create(NoteService::class.java)
}