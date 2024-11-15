package com.dhontiveros.idealistatechtest.core.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStreamReader


object JsonFileLoader {

    private var jsonStr: String?=null

    fun loadJsonString(file: String) : String? {
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        jsonStr = loader.readText()
        loader.close()
        return jsonStr
    }


    inline fun<reified T> loadEntity(file:String) : T?{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val jsonAdapter: JsonAdapter<T> = moshi.adapter<T>(T::class.java)
        val jsonStr = loadJsonString(file)
        return jsonAdapter.fromJson( jsonStr!! )
    }

}