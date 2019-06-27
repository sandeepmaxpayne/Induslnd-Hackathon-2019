package com.sandeep.induslandbank.shop_items

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DialogTitle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.webkit.*
import com.sandeep.induslandbank.R
import kotlinx.android.synthetic.main.activity_other_online_stores.*
import okhttp3.Request

@Suppress("DEPRECATION")
class OtherOnlineStores : AppCompatActivity() {
    private var url: String? = null
    private var isAlreadyCreated = false
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_online_stores)
        val bundle: Bundle? = intent.extras
        url = bundle?.getString("flip")


         val adhar = bundle?.getString("adhar")
         val accNum = bundle?.getString("acc")
        Log.d("addd", "ad:$adhar ano:$accNum")

        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(false)
        webView.webViewClient = object : WebViewClient(){

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                showErrorDialog("Error", "No Internet Connection Found", this@OtherOnlineStores)
            }
        }
        Log.d("url", "url:$url")
        webView.loadUrl(url)
    }

    override fun onResume() {
        super.onResume()
        if (isAlreadyCreated && !isNetworkAvailable()){
            isAlreadyCreated = false
            showErrorDialog("Error", "No Internet Connectivity", this@OtherOnlineStores)
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
    private fun isNetworkAvailable():Boolean{
        val connectivityManager= this@OtherOnlineStores.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkinfo = connectivityManager.activeNetworkInfo
        return networkinfo != null && networkinfo.isConnectedOrConnecting
    }
    private fun showErrorDialog(title: String, message: String, context: Context){
        val diag = AlertDialog.Builder(context)
        diag.setTitle(title)
        diag.setMessage(message)
        //diag.setNegativeButton("cancel") { _, _ -> this@OtherOnlineStores.finish()}
        //diag.setNeutralButton("Settings") {_, _ -> startActivity(Intent(Settings.ACTION_SETTINGS))}
        diag.setPositiveButton("Retry") {_, _ -> this@OtherOnlineStores.recreate()}
        diag.setIcon(android.R.drawable.ic_dialog_alert)
        diag.create().show()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.goback, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.back -> {
                val goBack = Intent(this@OtherOnlineStores, Category::class.java)
                val bundle:Bundle? = intent.extras
                val adhar = bundle?.getString("adhar")
                val accNum = bundle?.getString("acc")
                goBack.putExtra("addar",adhar)
                goBack.putExtra("useracc", accNum)
                startActivity(goBack)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
