package com.example.quotes

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.random.Random

class MainViewModel(val context: Context):ViewModel(){
    private var quoteList :Array<Quotes>  = emptyArray()
    private var index= Random.nextInt(0,1000)
    init {
       quoteList=loadQuotesFromAssets()
    }
    private fun loadQuotesFromAssets():Array<Quotes>{
        val inputStream=context.assets.open("quotes.json")
        val size:Int=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer, UTF_8)
        val gson= Gson()
        return gson.fromJson(json,Array<Quotes>::class.java)
    }

    fun getQuote(): Quotes =quoteList[index]
    fun nextQuote()=quoteList[++index]
    fun previousQuote()=quoteList[index--]
}