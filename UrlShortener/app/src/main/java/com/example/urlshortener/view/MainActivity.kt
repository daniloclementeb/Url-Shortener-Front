package com.example.urlshortener.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.urlshortener.R
import com.example.urlshortener.data.api.ApiAdapter
import com.example.urlshortener.ui.main.MainViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnEncurta.setOnClickListener {
            launch(Dispatchers.Main) {
                val map = HashMap<String, String>()
                map.put("url", txtUrl.text.toString())
                val response = ApiAdapter().apiClient.encurtaUrl(map)
                text_input_layout.error = null
                if (response!!.isSuccessful && response.body() != null) {
                    txtUrlShort.setText(response.body()!!.url?.get(0)?.get("shortUrl").toString())
                    textView4.visibility = View.VISIBLE
                    txtUrlShort.setOnClickListener {
                        val clipboard: ClipboardManager =
                            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("label", txtUrlShort.text.toString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(it.context, "Link Copiado", Toast.LENGTH_SHORT).show()

                    }
                    textView4.setOnClickListener {
                        val clipboard: ClipboardManager =
                            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("label", txtUrlShort.text.toString())
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(it.context, "Link Copiado", Toast.LENGTH_SHORT).show()

                    }
                } else {
                    text_input_layout.error = "Url Inválida"
                }
            }

        }
    }
}