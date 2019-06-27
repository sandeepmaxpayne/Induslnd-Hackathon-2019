package com.sandeep.induslandbank.shop_items

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.common.data.DataHolder
import com.sandeep.induslandbank.R
import com.squareup.picasso.Picasso

//class ShopList{
//    var name: String = ""
//    var img_icon = 0
//}
//
//data class ShopListAdapter(var shopitem:List<ShopList>, var activity: Activity): BaseAdapter(){
//
//    override fun getItem(position: Int): Any {
//        return shopitem[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getCount(): Int {
//        return shopitem.size
//    }
//
//
//    @SuppressLint("ViewHolder", "SetTextI18n")
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view = View.inflate(activity, R.layout.layout_adapter, null)
//        val itdesc = view.findViewById(R.id.itesmdesc) as TextView
//        val itqlty = view.findViewById(R.id.itemqlty) as TextView
//        val img_item=view.findViewById<ImageView>(R.id.cover)
//        itdesc.text= (position+1).toString()+"."
//        itqlty.text= shopitem.get(position).name
//        val lang_pic=shopitem.get(position).img_icon
////img_lang.drawable =langaugeList.get(position).img_icon
//        img_item.setImageResource(lang_pic)
//        return view
//
//    }
//
//}


class ShopList(val itemname: Int, val itemcategory: Int, val imageResouce: Int, val imageurl: String){
    var isFav = false
    fun toggleFavourite(){
        isFav = true
    }
}

class ShopListAdapter(private val mcontext: Context, private val shoplist: Array<ShopList>): BaseAdapter(){
    val count1: Int
        get() = shoplist.size
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val sList = shoplist[position]
        if (convertView == null){
            val layoutInflater = LayoutInflater.from(mcontext)
            convertView = layoutInflater.inflate(R.layout.layout_adapter, null)

            val imageViewCover = convertView.findViewById<ImageView>(R.id.cover)
            val descTextView = convertView.findViewById<TextView>(R.id.itesmdesc)
            val productqlty = convertView.findViewById<TextView>(R.id.itemqlty)
            val imgViewFav = convertView.findViewById<ImageView>(R.id.fav)
            val viewHolder = ViewHolder(descTextView, productqlty, imageViewCover, imgViewFav)
            convertView.setTag(viewHolder)
        }
        val viewHolder = convertView!!.tag as ViewHolder
        viewHolder.nametext.text = mcontext.getString(sList.itemname)
        viewHolder.quality.text = mcontext.getString(sList.itemcategory)
        viewHolder.Favourite.setImageResource(if (sList.isFav) R.drawable.star_enabled else R.drawable.star_disabled)

        Picasso.get().load(sList.imageurl).into(viewHolder.coverView)
        return convertView

    }

    override fun getCount(): Int {
        return shoplist.size
    }

    override fun getItem(position: Int): Any {
        return shoplist[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
    private inner class ViewHolder(val nametext: TextView, val quality: TextView, val coverView: ImageView, val Favourite: ImageView)

}
