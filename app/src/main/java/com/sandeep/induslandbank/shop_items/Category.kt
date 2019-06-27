package com.sandeep.induslandbank.shop_items


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.sandeep.induslandbank.Account
import com.sandeep.induslandbank.R
import kotlinx.android.synthetic.main.activity_category.*


class Category : AppCompatActivity() {
    var shopStore: String? = null
    var aad:String? = null
    var acno:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val bundle:Bundle? = intent.extras
         aad = bundle?.getString("addar")
         acno = bundle?.getString("useracc")
      //  Log.d("adhhr", "ad:$aad anum:$acno")


        val adapter = ArrayAdapter.createFromResource(this, R.array.shop_type, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnBrowse.adapter = adapter
        spnBrowse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                shopStore = parent?.getItemAtPosition(position).toString()
                //Log.d("shop", "shopStore: $shopStore")
                when(shopStore){
                    "FLIPKART" -> {
                        Log.d("shop", "shopname:$shopStore")
                        val shopNext = Intent(this@Category, OtherOnlineStores::class.java)

                        shopNext.putExtra("adhar",aad)
                        shopNext.putExtra("acc", acno)
                        Log.d("addd", "ad:$aad anum:$acno")

                        shopNext.putExtra("flip", "https://www.flipkart.com/")
                        startActivity(shopNext)
                    }
                    "SNAP DEAL" -> {
                        val shopNext = Intent(this@Category, OtherOnlineStores::class.java)

                        shopNext.putExtra("adhar",aad)
                        shopNext.putExtra("acc", acno)

                        shopNext.putExtra("flip", "https://www.snapdeal.com/")
                        startActivity(shopNext)
                    }
                    "AMAZON" -> {
                        val shopNext = Intent(this@Category, OtherOnlineStores::class.java)

                        shopNext.putExtra("adhar",aad)
                        shopNext.putExtra("acc", acno)

                        shopNext.putExtra("flip", "https://www.amazon.in")
                        startActivity(shopNext)
                    }
                    "MYNTRA" -> {
                        val shopNext = Intent(this@Category, OtherOnlineStores::class.java)

                        shopNext.putExtra("adhar",aad)
                        shopNext.putExtra("acc", acno)

                        shopNext.putExtra("flip", "https://www.myntra.com/")
                        startActivity(shopNext)
                    }
                    "GOIBIBO" -> {
                        val shopNext = Intent(this@Category, OtherOnlineStores::class.java)

                        shopNext.putExtra("adhar",aad)
                        shopNext.putExtra("acc", acno)

                        shopNext.putExtra("flip", "https://www.goibibo.com/")
                        startActivity(shopNext)
                    }
                }
//                val shopNext = Intent(this@Category, OtherOnlineStores::class.java)
//                startActivity(shopNext)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        books.setOnClickListener {
            val items = "book"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)
            Log.d("adhhr", "ad:$aad anum:$acno")

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        cloths.setOnClickListener {
            val items = "cloth"
            val intent = Intent(this@Category, Shopping::class.java)
            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)
            intent.putExtra("shop", items)
            startActivity(intent)
        }
        toys.setOnClickListener {
            val items = "toy"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        food.setOnClickListener {
            val items = "food"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        bags.setOnClickListener {
            val items = "bag"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        sunglass.setOnClickListener {
            val items = "sunglass"
            val intent = Intent(this@Category, Shopping::class.java)
            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)
            intent.putExtra("shop", items)
            startActivity(intent)
        }
        shoe.setOnClickListener {
            val items = "shoe"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        movie.setOnClickListener {
            val items = "movie"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        home.setOnClickListener {
            val items = "home"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        phone.setOnClickListener {
            val items = "phone"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        computer.setOnClickListener {
            val items = "computer"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        music.setOnClickListener {
            val items = "music"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        games.setOnClickListener {
            val items = "games"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }
        hotel.setOnClickListener {
            val items = "hotel"
            val intent = Intent(this@Category, Shopping::class.java)

            intent.putExtra("adhar",aad)
            intent.putExtra("acc", acno)

            intent.putExtra("shop", items)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.compare, menu)
        menuInflater.inflate(R.menu.delivery, menu)
        menuInflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.cmpr -> {
                val next = Intent(this@Category, CompareItem::class.java)
                val bundle:Bundle? = intent.extras
                val adhar = bundle?.getString("addar")
                val accNum = bundle?.getString("useracc")
                next.putExtra("adhar",adhar)
                next.putExtra("acc", accNum)
                startActivity(next)
            }
            R.id.calcDel -> {
                val next = Intent(this@Category, DeliveryCalculator::class.java)
                val bundle:Bundle? = intent.extras
                val adhar = bundle?.getString("addar")
                val accNum = bundle?.getString("useracc")
                Log.d("adhhr", "ad:$adhar anum:$accNum")
                next.putExtra("adhar",adhar)
                next.putExtra("acc", accNum)
                startActivity(next)
            }
            R.id.home -> {
                val bundle:Bundle? = intent.extras
                val adhar = bundle?.getString("addar")
                val accNum = bundle?.getString("useracc")
                val intent = Intent(this@Category, Account::class.java)
                intent.putExtra("adhar",adhar)
                intent.putExtra("acc", accNum)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }





}
