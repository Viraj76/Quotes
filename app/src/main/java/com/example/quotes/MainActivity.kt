package com.example.quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private val quoteText:TextView
    get() = findViewById(R.id.tvText)
    private val quoteAuthor:TextView
    get() = findViewById(R.id.tvAuthor)
    private lateinit var fabPrev:FloatingActionButton
    private lateinit var fabNext:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel= ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        GlobalScope.launch {
            setQuote(mainViewModel.getQuote())
        }


        fabPrev=findViewById(R.id.fabPrev)
        fabNext=findViewById(R.id.fabNext)

        fabPrev.setOnClickListener{
            onPrev()
        }

        fabNext.setOnClickListener{
            onNext()
        }

    }
    private fun setQuote(quotes: Quotes){
        quoteText.text=quotes.text
        quoteAuthor.text=quotes.author
    }
    private fun onPrev() {
        setQuote(mainViewModel.previousQuote())
    }
    private fun onNext() {
        setQuote(mainViewModel.nextQuote())
    }
    fun onShare(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.setType(("text/plain"))
        GlobalScope.launch {
            intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
        }

        startActivity(intent)
    }
}
