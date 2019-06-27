package com.sandeep.induslandbank.shop_items

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.sandeep.induslandbank.R
import kotlinx.android.synthetic.main.activity_compare_item.*

class CompareItem : AppCompatActivity() {
    var otherShopType: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_item)

        val adapter = ArrayAdapter.createFromResource(this, R.array.shop_type, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        shoptype.adapter = adapter
        shoptype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                otherShopType = parent?.getItemAtPosition(position).toString()
                Log.d("shop", "shop: $otherShopType")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        btncmp.setOnClickListener {
            Log.d("shop", "shop: $otherShopType")
            //  disaplayCompareRes()

            if ((kiranVal.text.toString().isNotEmpty() && wrr2.text.toString().isEmpty()) && (flipVal.text.toString().isNotEmpty() && wrr1.text.toString().isEmpty())) {
                disaplayCompareRes()
            }  else if ((kiranVal.text.toString().isEmpty() && wrr2.text.toString().isNotEmpty()) && (flipVal.text.toString().isEmpty() && wrr1.text.toString().isNotEmpty())) {
                dispWarr()
            } else if ((kiranVal.text.toString().isEmpty() && wrr2.text.toString().isEmpty()) && (flipVal.text.toString().isEmpty() && wrr1.text.toString().isEmpty())) {
                errorEmpty()
            } else if ((kiranVal.text.toString().isNotEmpty() && wrr2.text.toString().isNotEmpty()) && (flipVal.text.toString().isNotEmpty() && wrr1.text.toString().isNotEmpty())) {
                val x = kiranVal.text.toString().toBigDecimal() / wrr2.text.toString().toInt().toBigDecimal()
                val y = flipVal.text.toString().toBigDecimal() / wrr1.text.toString().toInt().toBigDecimal()
                val res = AlertDialog.Builder(this)
                if (x < y) {
                    res.setMessage("Kirana Store Product Price and Warranty Services is more Beneficial")
                        .setCancelable(false)
                        .setIcon(R.drawable.kirana)
                        .setPositiveButton("done") { _, _ ->
                            finish()
                        }
                        .setNegativeButton("cancel") { dialog, _ ->
                            dialog.cancel()
                        }
                } else {
                    res.setMessage("$otherShopType Product is a better in terms of Price and Warranty Services")
                        .setCancelable(false)
                        .setIcon(R.drawable.online_shop)
                        .setPositiveButton("done") { _, _ ->
                            finish()
                        }
                        .setNegativeButton("cancel") { dialog, _ ->
                            dialog.cancel()
                        }
                }
                val alert = res.create()
                alert.setTitle("Better Choice")
                alert.show()

            }
        }
    }
    private fun disaplayCompareRes(){
        val mess = AlertDialog.Builder(this)
        if (kiranVal.text.toString().toBigDecimal() < flipVal.text.toString().toBigDecimal()){
            mess.setMessage("Kirana Store Products is more Profitatble to You")
                .setCancelable(false)
                .setIcon(R.drawable.kirana)
                .setPositiveButton("done") { _,_ -> finish()
                }
                .setNegativeButton("cancel") { dialog, _ -> dialog.cancel()
                }
        }else{
            mess.setMessage("$otherShopType Product Price is Better, But If you Purchase From Kirana Store You get additional discounts on Products than $otherShopType")
                .setCancelable(false)
                .setIcon(R.drawable.online_shop)
                .setPositiveButton("done") { _,_ -> finish()
                }
                .setNegativeButton("cancel") { dialog, _ -> dialog.cancel()
                }
        }
        val alert = mess.create()
        alert.setTitle("More Beneficial to You")
        alert.show()
    }
    private fun dispWarr(){
        val mess = AlertDialog.Builder(this)
        if (wrr2.text.toString().toInt() > wrr1.text.toString().toInt()){
            mess.setMessage("Better Warranty Service Provided by Kirana Store")
                .setCancelable(false)
                .setIcon(R.drawable.kirana)
                .setPositiveButton("done") { _,_ -> finish()
                }
                .setNegativeButton("cancel") { dialog, _ -> dialog.cancel()
                }
        }else{
            mess.setMessage("Warranty Period of $otherShopType is better so You can prefer $otherShopType")
                .setCancelable(false)
                .setIcon(R.drawable.online_shop)
                .setPositiveButton("done") { _,_ -> finish()
                }
                .setNegativeButton("cancel") { dialog, _ -> dialog.cancel()
                }
        }
        val alert = mess.create()
        alert.setTitle("Better Service in Warranty")
        alert.show()
    }
    private fun errorEmpty(){
        val dialogbuilder = AlertDialog.Builder(this)
        dialogbuilder.setMessage("Please enter atleast one deatils to compare")
            .setCancelable(false)
            .setPositiveButton("done") { _, _ -> finish()
            }
            .setNegativeButton("cancel") { dialog, _ ->  dialog.cancel()
            }
        val alert = dialogbuilder.create()
        alert.setIcon(R.drawable.error)
        alert.setTitle("Invalid Entry Comparison")
        alert.show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.goback, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.back -> {
                val next = Intent(this@CompareItem, Category::class.java)
                val bundle:Bundle? = intent.extras
                val adhar = bundle?.getString("adhar")
                val accNum = bundle?.getString("acc")
                next.putExtra("addar",adhar)
                next.putExtra("useracc", accNum)
                startActivity(next)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
