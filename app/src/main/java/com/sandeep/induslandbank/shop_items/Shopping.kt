package com.sandeep.induslandbank.shop_items





import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.SupportActivity
import android.support.v7.view.SupportActionModeWrapper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import com.sandeep.induslandbank.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_shopping.*


class Shopping : AppCompatActivity() {



//    var shopnew: GridView? = null
//    var shopnewList: ArrayList<ShopList> = ArrayList()
//    var adapter: ShopListAdapter? = null


//    private val shopList = arrayOf(
//    ShopList(R.string.abc1, R.string.abc2, R.drawable.shop, "https://static4.cilory.com/273124-large_default/nologo-navy-casual-shirt.jpg"),
//                                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "https://images-na.ssl-images-amazon.com/images/I/41J60RhwRhL.jpg"),
//                                    ShopList(R.string.jeans, R.string.abc4, R.drawable.shop,"https://5.imimg.com/data5/EJ/AG/MY-44534711/ladies-party-wear-gown-500x500.jpg"),
//                                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://d38jde2cfwaolo.cloudfront.net/modules/homepageadvertise/slides/kurti-08012016.jpg"),
//    ShopList(R.string.abc1, R.string.abc2, R.drawable.shop, "https://static4.cilory.com/273124-large_default/nologo-navy-casual-shirt.jpg"),
//    ShopList(R.string.trouser, R.string.trouser, R.drawable.shop, "https://rukminim1.flixcart.com/image/880/1056/trouser/a/f/7/tomcatmtrolv-uber-urban-34-original-imaechtuqgdnv2pa.jpeg?q=50")
//
//    )
    private var shopList = arrayOf(ShopList(0, 0, 0, ""))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val bundle: Bundle? = intent.extras
       val shopitem = bundle?.getString("shop")

        val adhar = bundle?.getString("adhar")
        val accNum = bundle?.getString("acc")
        Log.d("adhhr", "ad:$adhar anum:$accNum")




        when(shopitem){
            "book" -> {

                supportActionBar?.title = "Books Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.light_red))
                shopList = arrayOf(
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "data:image/jpeg;base64,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"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoupZQlJ_1g4tImGGVHZ2l5xwknTyVY1k4jNAv7WfPsSqeDRpT8g"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxjPc14MMcLNGJ9wlZgLMvZJwTPyMziL47Sh2c3-Kyu4qkCeYX"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "http://adeleabbott.com/adele/wp-content/uploads/2015/12/AudioBook-3-wheniswheneverythingwhencrazy-240-x-240.jpg"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPPlo_rI9FOyjia8awwzwY_ufDu88r1V96pK8cFK5bo1J3ylIG")

                )
            }
            "toy" -> {

                supportActionBar?.title = "Toys Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.crimson))
                shopList = arrayOf(
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGo-SeFT7xLyc377fG4dIIzcz6gAu7-okpEDRKen9cxB1B0L7k"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOSgohfZzVY4W0wEh2HUpJ1S8XKYt5CLcoVb09TEIBJ9LoB0vO"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTAchHgTQUdL64O4DH2SKKPuWFO8UVwHLsIDfHkpkCUpraS6jluhA"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpd0A_B0_ruk70A4nQaCE6XkfrztirMqNEm40sVm7pc_330mVd5Q"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFmIYhJLu9Bj_yC9CtozaGMzo2Fyef4ZTtCVnOzSX__rkGygzg_A")

                )
            }
            "food" -> {

                supportActionBar?.title = "Fooods Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.lightSalmon))
                shopList = arrayOf(
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Food-pizza-fastfood_%2824300304566%29.jpg/240px-Food-pizza-fastfood_%2824300304566%29.jpg"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSftWriOfqkhx4SjqWysF9KLiLronjD3lyBsecHgB9LadQKh9iP"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlWImwdgcCOdvTHxvjgTap9KCj5eJ2zhidgFbX8smybW6YqdLO"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://challengesworldwideicsentrepreneur.files.wordpress.com/2016/04/typical-ugandan-food-served-with-a-dashing-of-matoke.jpg?w=240"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://cdn.shopify.com/s/files/1/1531/6957/files/Soup_Salad_Dressings_2_240x240.jpg?v=1551455412"),

                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://cdn.shopify.com/s/files/1/1531/6957/files/All-Recipes2_240x240.jpg?v=1551455566"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"http://www.zingermanscommunity.com/wp-content/uploads/2017/08/GTB_FoodTours_TAble-240x240.png"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://t3.ftcdn.net/jpg/02/18/18/22/240_F_218182275_YsS6YpnfWNyxAD2h8AoxsSfp0OJnuguI.jpg"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRta2KH4kSvSKRsgYhlrS5gpOegwuoZXsTTeCLTnzpKqL3GecaP"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXHRgotWEIGXTMeO_98MxiOx-LkMsEu0qZT-bwkxWb5OK4rRfJ")

                )
            }
            "bag" -> {

                supportActionBar?.title = "Bags Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.light_back))
                shopList = arrayOf(
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://www.thomann.de/pics/bdb/296968/13159236_800.jpg"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTFnyRqvyFeiKybO7sHC0HccXLVaZh2IM-XCXIFAw5uL6inBRU"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQa-R96M-cF3hj2X5A1aFjUZgy3lGh70-_lONsQWIS08_671xT0"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9SLgvezgQB6hZlxst91J7SJIcP4DGTOG5BYUb9gWUdP6bg3rk"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTk9Ys2rkoKomUGCoyov5fZ3IKUEy-0vwG9XirDvbWkdBQfyu_t"),

                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSc1jg2F689orFcI4OsjpybqrNTNcyJk-YE1mVacWDsKbvffeTvIw"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEysP16dEJDJl_5xCOl4Erp4X-v75-C5MlZKk5a9qTO738x39F"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://cdn.shopify.com/s/files/1/0019/1411/0010/products/2_32cdb0f6-3021-4685-b5e4-ebf0bc0c78ff_medium.jpg?v=1530591131"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMwulDNUFTNDpfk44oJG2cA2Gfwc1Bkl1q9_vOj3t3vrZnmtDx"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRr1v26-C2rHqieT7HOHJIqiUtgvpiEdSelre2XImd5lClppkKMcQ")

                )
            }
            "sunglass" -> {

                supportActionBar?.title = "Sunglasses Section"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.pink))
                shopList = arrayOf(
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://media.kohlsimg.com/is/image/kohls/3530765?wid=240&hei=240&op_sharpen=1"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://a.1stdibscdn.com/chanel-brown-beige-bi-colour-square-oversized-sunglasses-for-sale/1121189/v_67918521559892641478/6791852_master.jpg?width=240"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsWz6D7wKQ1KTd083kn4LhlQt9OBp8GfeEaa5xjoFV90Xl1ty6bg"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9bynX7gzroovIssfOvbbZFhYl7qMm7TbnHdT4_rP_D3tZMbz2cg"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_Op07e1PA3piBdKVzXhSrYWYlZNwsw-lUigy0wdtZPb30zctl"),
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaisYKBKfJ_XFk53NFaFd3zl0Ul7Dol6u0FJKwfrNdnNzf-bfl"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaXXck775cBLbUdahxFqiqgoFJ-LGNcF8fLCAE7FLyYeRfqkGgPg"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://cdn.shopify.com/s/files/1/0590/6049/products/Flat_Lens_240x.jpg"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTumf5bJBk0VEAn-4e5SvwGfC98HqsAeRtYGL5kcNHDdrLscGkcXw"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZMwZ6uEWWinBSbaiwcBvPihy4lTLC7tjx8D0SMjaCvRpGLVq0")

                )
            }
            "shoe" -> {

                supportActionBar?.title = "Shoes Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.blue))
                shopList = arrayOf(
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYw8WjUe7sIetfv5tVAlZaHn-y-KD-Niy-icpM5_9Db2rFIEvf"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://gamepedia.cursecdn.com/battlegrounds_gamepedia_en/1/14/Icon_equipment_Feet_1.0-99_Shoes.png?version=e5eb127149df78f5a5ce988c5a0e10fe"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://gamepedia.cursecdn.com/battlegrounds_gamepedia_en/d/d6/Icon_equipment_Legs_BR_School_Shoes.png"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://images-na.ssl-images-amazon.com/images/I/81hlDHwwJ+L._AC._SR240,240.jpg"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://us.ecco.com/dw/image/v2/BCNL_PRD/on/demandware.static/-/Sites-ecco/default/dwb4065115/productimages/285623-01007-outside.jpg?sw=240&sh=240&sm=fit&q=100"),
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeeP36hmgbA56sx1WHvG0marayscgJT20iFlFYnW2swVu02Htv"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://dz2563isv5m4b4oy42huiqwm-wpengine.netdna-ssl.com/wp-content/uploads/2019/06/adidas-spikeless-shoes-240x240.jpg"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCtpRJuhEcyvoAWetFg8N35yDGi_neIdAyMKITCrK__kfuc1j-"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTO6Gqo_i7kcn3MZVyXs9NrcSwp844nIjdbUuXmzc624FMcRlbb"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUhCXtvkUfn4rIK2H612ra_CIzh8e2q75tWeu0ze3rGZmCFzRhag")


                )
            }
            "movie" -> {

                supportActionBar?.title = "Movies Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.hotPink))
                shopList = arrayOf(
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://cdn-2.cinemaparadiso.co.uk/1904170335368_l.jpg"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://vignette.wikia.nocookie.net/marvelmovies/images/6/61/Movie_toad.jpg/revision/latest/zoom-crop/width/240/height/240?cb=20180422184150"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8bja1ddNK8g89u3gJkKVRcCNsKoA1OZXGIvwu2vm8wzjcBEwcNQ"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFBJzCibbTYUvU5F03hI0x8OjJXuM-TfcoTyiBC5YSn2q2DA7sBA"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://s2-ssl.dmcdn.net/v/HzADa1SgsYWgJ7UjO/x240"),
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://s3.zerochan.net/Pok%C3%A9mon.the.Movie%3A.I.Choose.You%21.240.2126355.jpg"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZ60WdMFO1PBeoQ9HR0_aQWnAOt_ZlFdooRVYn9IfEY1L5aLxevw"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlm1Jg8w68i_9eah-TqAkK__I0gKcOIVummn5iTdUySgPYKVBq"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://s3-us-west-2.amazonaws.com/flx-editorial-wordpress/wp-content/uploads/2018/12/27134027/spiderman_into_the_spiderverse_xlg-240x240.jpg"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLnCyBB5HK_rzQhUx9uCBIKlshRhjwwWzCgAs68CTh6tmgnMzneg")

                )
            }
            "home" -> {

                supportActionBar?.title = "Home Appliances"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.pink))
                shopList = arrayOf(
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://www.vancouverrealestatepodcast.com/wp-content/uploads/2018/06/luxury-home-in-west-vancouver-240x240.jpg"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUSqIjBK-EbrH44PvsDRVCzooNboSFWJyXM9rtoixwSHgNx1vFGw"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://store.playstation.com/store/api/chihiro/00_09_000/container/CA/en/999/UP4040-CUSA06266_00-PORTALKNIGHTS000/1559539499000/image?w=240&h=240&bg_color=000000&opacity=100&_version=00_09_000"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr9mWBqmFMZCT1Hn5LCbprz9oZn9We-DhcmRxL5b_BqIoP2dNl"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHE6-MAST7FhDcvlUHR3lH73BhIKu1CTZNgcD654492FEZcIi6Ww"),
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaZhgFtxv_NG9scljJlK6E_reY7dsIgN55oKFtJ3s0dGIqRXIz"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://www.google.com/search?client=firefox-b-d&biw=1536&bih=750&tbm=isch&sa=1&ei=UU4HXcXeBYS5rQH_m5bICg&q=home+appliance+mages+240+x+240&oq=home+appliance+mages+240+x+240&gs_l=img.3...9369.12465..12754...0.0..0.239.1944.0j7j3......0....1..gws-wiz-img.-OQXFJjeC6c#imgdii=-JPEtmT6-XagqM:&imgrc=Aq57j65p7Qs7QM:"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://www.google.com/search?client=firefox-b-d&biw=1536&bih=750&tbm=isch&sa=1&ei=UU4HXcXeBYS5rQH_m5bICg&q=home+appliance+mages+240+x+240&oq=home+appliance+mages+240+x+240&gs_l=img.3...9369.12465..12754...0.0..0.239.1944.0j7j3......0....1..gws-wiz-img.-OQXFJjeC6c#imgdii=-JPEtmT6-XagqM:&imgrc=Aq57j65p7Qs7QM:"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://www.google.com/search?client=firefox-b-d&biw=1536&bih=750&tbm=isch&sa=1&ei=UU4HXcXeBYS5rQH_m5bICg&q=home+appliance+mages+240+x+240&oq=home+appliance+mages+240+x+240&gs_l=img.3...9369.12465..12754...0.0..0.239.1944.0j7j3......0....1..gws-wiz-img.-OQXFJjeC6c#imgdii=-dfpdy1MQxt_9M:&imgrc=wqvEcyeCsIWQ3M:"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvuv31iFgUyZwJuGGhJd8YNiheG8jy8FTn7XfVQFJH5DzaAIdw")
                )
            }

            "phone" -> {

                supportActionBar?.title = "Latest Phone Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.pink))
                shopList = arrayOf(
                    ShopList(R.string.phone2, R.string.phone2, R.drawable.circle_backgreen, "https://images-na.ssl-images-amazon.com/images/I/41SKN0OeWrL.jpg"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://upload.wikimedia.org/wikipedia/commons/thumb/6/69/Tango_Phone.svg/240px-Tango_Phone.svg.png"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://thumbor.forbes.com/thumbor/240x0/https%3A%2F%2Fblogs-images.forbes.com%2Fgreatspeculations%2Ffiles%2F2012%2F07%2F7048801715_565030b4df_m.jpg"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeFm8pLHr6Avd_LsIx7ktpTZPumarjInJGzp3ipU4RFNUWAMYU"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJyPcQi-Cm2cBGhPadqtPBfCKwuYBl9SN9mUYmtEpUmJx6QIG4Lw"),
                    ShopList(R.string.phone3, R.string.phone3, R.drawable.jio,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbXqjnEl4xO9YvXiPwt3DiPqtbRIvq1oBfRmHRFwyrjHTW8J02"),
                    ShopList(R.string.phone4, R.string.phone4, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_FofHRU0_UrOlYCZLG8eoRKtz0B-eaXXz07x19DqSRrwNSeoUig"),
                    ShopList(R.string.phone5, R.string.phone5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoU22RJ6bZq9eUQFH2pE8xWl-bhy6wTs-y6h-LefbrRmcQ_YSl"),
                    ShopList(R.string.phone6, R.string.phone6, R.drawable.circle_backgreen, "http://www.najevtino.mk/images/m/17/408ee5ae54ca0ee717cf7bbf252880da.jpeg")

                )
            }
            "cloth" -> {

                supportActionBar?.title = "Cloths Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.yellow))
                shopList = arrayOf(
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://static4.cilory.com/273124-large_default/nologo-navy-casual-shirt.jpg"),
                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "https://images-na.ssl-images-amazon.com/images/I/41J60RhwRhL.jpg"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSULpW0zp-Br1ytgzNTLqZki4GnpKYtaxU-ZhmdBJFYpEnxJu886Q"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://images-na.ssl-images-amazon.com/images/I/91lLu1Lr7oL._AC._SR240,240.jpg"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://cdn.shopify.com/s/files/1/1644/6687/products/007_cap_-_silver_logo_F_medium.jpg?v=1560263943"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa7cI1axSv-6QpKMnKe4gxuLIepM7KRpN8jaYeOhmuPNkkj5D1zg"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVUu6m-3MgubGBxRS41BGLcAKMeIylnzo73mo8HrhYSaYZ7EDoFg"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyKQkFUIgNstajc2xxnAt4TdMnwPe35adlrr0NOLAQznbbXK3g"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpPC7HbNofWk8Cq_4i-uzduH3uNBxtYL17Q9Rt9Ai3ZYffIk2m8g"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrJUm33lJa2ORIA-ZAw97dKHCHgiztOyQZD-KWGq5JPuC-JEMQNw")
                )

            }

            "computer" -> {

                supportActionBar?.title = "Latest Computer Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.light_back))
                shopList = arrayOf(
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://marketplace.magento.com/media/catalog/product/cache/e4d64343b1bc593f1c5348fe05efa4a6/m/a/mage2-form-builder-240x240.png"),
                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "https://wantitbuyit.scdn5.secure.raxcdn.com/pub/media/catalog/product/cache/small_image/240x240/beff4985b56e3afdbeabfc89641a4582/e/4/e480-5-win-10-pro.jpg"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbQv5jPK51YBI-tRz3AhMWGXWokQzSxGmCkcvvHMHsiESQEKOH"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvkg2NZQeQK_W17bgYtelW1Ge9_vcH_G_ATon--LPz09_L4-CF"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0-H4O2ztZfwXVbcFC_J2e8iMTp88tS2o_MQscg8hBuKEc3KsSSQ"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://i.ebayimg.com/images/g/6UUAAOSwRxJb6mrw/s-l225.jpg"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://www.pcbyte.com.au/media/catalog/product/cache/1/small_image/240x240/9df78eab33525d08d6e5fb8d27136e95/h/1/h18_16.jpg"),
                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWYD1jBu0rr5gHCmyQmtdCy3gKsgtlNc2aFLoo8fBdQ4hopgvRkg"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNrEcaMSTxAXqhfCryr5iByWNUeinNu_mUJqQKgqtiH6Ms2Opqlw"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlbk7MZrY_ZvPxtBJY70LP8sELpQYzryOCVVd_XMxkary06wKa"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmXf02_KLNKKZbl0MSGQlfoaECLAW_HRmZAn6R5JTzTdbbkd_x"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://warwick.ac.uk/insite/news/intnews2/new_work_area_computers/new-work-area-computers-240.jpg")
                )

            }
            "games" -> {

                supportActionBar?.title = "Latest Games"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.buttonDark))
                shopList = arrayOf(
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQe3nuz2daaz3JMx7aYJdNMMaDIZEnznSY2fwJr-7CK31s8eNouDw"),
                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "http://cdn.24.co.za/files/Cms/General/d/5203/811bf8ec8dcb48c4b30511a46362e60b.jpg"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSha9NFU8tcXCVyUYTCrTzTAOfknwOgx8vUOkqlZzgywSOcJl32"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXDyNfe1MWS0bJlpSJlUH_J7ums8ZXDrcLmFTKYxgw8HX8D0ZH"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://images-na.ssl-images-amazon.com/images/I/71YSvFcuK7L._AC._SR240,240.jpg"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlYXqgOMsQJ5dZlsFEkwVldQ6Usyl8XesWVz_J78pp7HRfBq66"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSi-1drw4W42T_FHjSOIdCL2WY0lcNcoxiowlZCpQKB7WpI-QUrGQ"),
                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPIUk6eck_xmE8zJtgbsacSLhKTLyhexjPGfbZQOznZ2ZtJSRc"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxsR38gyUirCQQR32HGMmzcVqTXAFXyhzYrJkbpX24h-ce2QnA"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://images-na.ssl-images-amazon.com/images/I/91AFCdHkvoL._AC._SR240,240.jpg"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5tEnyGw8RSyPtbKmecH1UtU-Az877ldIuGPogKDotX4pqFwTf-Q"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvgOGTrwd3v-ep2k-_ympyJCTxqOqZkFvHsDoo0AZ3AByv0LHQUQ")
                )

            }
            "music" -> {

                supportActionBar?.title = "Latest Music Corner"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.deep_purple))
                shopList = arrayOf(
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpxKc5IO7FaaNyzB0Duff1d1XsgajLbYkSFjbtXYER5aEXAnI31Q"),
                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "https://farm2.staticflickr.com/1485/25819282900_4a5b7d0ac1_m.jpg"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://is2-ssl.mzstatic.com/image/thumb/Music3/v4/af/d2/fe/afd2fe7a-3b38-58b2-0731-2e5e480c65cd/886445051315.jpg/320x0w.jpg"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://charts-static.billboard.com/img/2011/02/adele-hdy-240x240.jpg"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjgAHmClvOpFQdy0NxPXT-pDQEPF5iGzmQx8Eu1ZpxW_Xfp19GKw"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJEqFuAOdPH46dlE9wNVPpYtFrmU9uczBhpmUkDTcui78ogvqc"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPA4sOhqVm4EVlKFz83-E_i2UrkLXw88ZsM_UeCU-dpFBiV3g8"),
                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "https://i.iheart.com/v3/url/JTJGJTJGaW1hZ2UuaWhlYXJ0LmNvbSUyRmJlbGwtaW5nZXN0aW9uLXBpcGVsaW5lLXByb2R1Y3Rpb24tdW1nJTJGZnVsbCUyRjAwODQzOTMwMDM2MTY1XzIwMTgxMjE0MDAyNzE1NTA0JTJGMDA4NDM5MzAwMzYxNjVfVDFfY3ZyYXJ0LmpwZw==?ops=fit(240%2C240)"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://m.media-amazon.com/images/I/41C8ZkgXaJL._SY240_.jpg"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyOPJOpvKM5lcbuT091deZ51clcp5TxEwEWUE-DpCItk4wA2Fv"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScK3eJxeIfvVoQQokQlJ_AlSJ3T9cvqFPgw03OppoZKbXgwPP5"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyejK7allB1olKR70VAO5PSoeOdgfxUF1y0I4H-tWVbbVstRbM")
                )

            }
            "hotel" -> {


                supportActionBar?.title = "Hotels Service"
                supportActionBar?.setBackgroundDrawable(getDrawable(R.color.mediumPink))
                shopList = arrayOf(
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://www.welcome-hotels.com/site/assets/files/6409/welcome_hotel_darmstadt_tagungsraum_kopernikus.240x240.jpg"),
                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "https://www.welcome-hotels.com/site/assets/files/6568/welcome_hotel_residenzschloss_bamberg_k1.240x240.jpg"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://media-cdn.tripadvisor.com/media/photo-s/05/aa/4f/08/novotel-barcelona-sant.jpg"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://cdn.ostrovok.ru/t/240x240/content/ec/58/ec58d98311718f250ff996b049d5e51396836b5c.jpeg"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSunB8GnHoX6ihPbDK9_AkhEngCwwbtrh-3wv9AAmqJyyJnnZdREw"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuOeWUM7QRT47AfoeIjqUIgtKUBRN9T-RIZrLd_ulHnx7Ie3fN"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwoBiEPOuSHxCvFxE8qmdjmqZNN_fc0doXaEnOu9g6uTDaYb2sQQ"),
                    ShopList(R.string.blazzer, R.string.abc3, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGnaI4370DeYmuvkEOpQWm4tSDP0zi8oy6EHd2dL_r57F8oz2_Tg"),
                    ShopList(R.string.jeans, R.string.abc4, R.drawable.circle_backgreen,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSW6nkEDhrN_Fu0dc3QeI1kMN_-OdZqcNoaa_we46WHY7DzY4w9Gw"),
                    ShopList(R.string.pant, R.string.abc5, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUcM2UCTWFCFMEipJGXTKleS2QnRqbcX4Wy0p4HffkMqxmNe_mJg"),
                    ShopList(R.string.abc1, R.string.abc2, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFwkzp5PXzOg2LGAag5_1QWtKuuBKqycVHKopddpjd_yYgPx2W"),
                    ShopList(R.string.trouser, R.string.trouser, R.drawable.circle_backgreen, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSc3sJ5P6_GFO54Rpmego-jReP9OPZ0kJIKgd_czvYfGEL9L77v")
                )
            }


        }


        val shopView = findViewById<GridView>(R.id.gridView)
        val shopadapter = ShopListAdapter(this, shopList)
        shopView.adapter = shopadapter

        shopView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val item = shopList[position]
            item.toggleFavourite()
            shopadapter.notifyDataSetChanged()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val favouriteItem = ArrayList<ShopList>()
        for (item in shopList){
            if (item.isFav){
                favouriteItem.add(item.itemname as ShopList)
            }
        }
        outState?.putIntegerArrayList(favouriteItemNameKey, favouriteItem as java.util.ArrayList<Int>)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val favouriteItem = savedInstanceState?.getIntegerArrayList(favouriteItemNameKey)
        for (itemName in favouriteItem!!){
            for (item in shopList){
                if (item.itemname == itemName){
                    item.isFav = true
                    break
                }
            }
        }
    }

//    private fun addData(){
//        val item1 = ShopList()
//            item1.name = "Android"
//        item1.img_icon = R.drawable.common_google_signin_btn_icon_dark_focused
//        shopnewList.add(item1)
//
//        val item2 = ShopList()
//        item2.name = "Android"
//        item2.img_icon = R.drawable.common_google_signin_btn_icon_dark_focused
//        shopnewList.add(item2)
//
//        val item3 = ShopList()
//        item3.name = "Android"
//        item3.img_icon = R.drawable.common_google_signin_btn_icon_dark_focused
//        shopnewList.add(item2)
//
//        val item4 = ShopList()
//        item4.name = "Android"
//        item4.img_icon = R.drawable.common_google_signin_btn_icon_dark_focused
//        shopnewList.add(item2)
//
//    }
companion object{
    private val favouriteItemNameKey = "favouriteItemNameKey"
}
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.goback, menu)
        menuInflater.inflate(R.menu.kart, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.back -> {
                val next = Intent(this@Shopping, Category::class.java)
                val bundle:Bundle? = intent.extras
                val adhar = bundle?.getString("adhar")
                val accNum = bundle?.getString("acc")
                Log.d("adhhr", "ad:$adhar anum:$accNum")
                next.putExtra("addar",adhar)
                next.putExtra("useracc", accNum)
                startActivity(next)
            }
            R.id.bkart -> {
                val bitem = Intent(this@Shopping, Kart::class.java)
                val bundle:Bundle? = intent.extras
                 val adhar = bundle?.getString("adhar")
                 val accNum = bundle?.getString("acc")
                bitem.putExtra("addh",adhar)
                bitem.putExtra("accno", accNum)
                Log.d("adhhr", "ad:$adhar anum:$accNum")
                startActivity(bitem)
            }
        }
        return super.onOptionsItemSelected(item)
    }



}



