package br.com.caiobartholomeu.catsforyou.retrofit.service

import android.provider.ContactsContract
import retrofit.Call
import retrofit.http.GET

interface NoteService {
    @GET("?q=cats")
    fun list(): Call<List<ContactsContract.CommonDataKinds.Note>>
}