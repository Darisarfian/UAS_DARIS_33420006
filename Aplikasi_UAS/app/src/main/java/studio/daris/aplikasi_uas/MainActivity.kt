package studio.daris.aplikasi_uas

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import studio.daris.aplikasi_uas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnPindahFoods: CardView = findViewById(R.id.Makananminuman)
        btnPindahFoods.setOnClickListener(this)

        val btnPindahRumah: CardView = findViewById(R.id.Rumah)
        btnPindahRumah.setOnClickListener(this)

        val btnPindahBayi: CardView = findViewById(R.id.Bayi)
        btnPindahBayi.setOnClickListener(this)

        val btnPindahKesehatan: CardView = findViewById(R.id.Kesehatan)
        btnPindahKesehatan.setOnClickListener(this)

        list.add(
            ImageData(
                "https://blog-oss.investree.id/wp-content/uploads/2021/09/Gambar-Utama-Cushmanwakefield.com_.jpg"
            )
        )
        list.add(
            ImageData(
                "https://indonesiabaik.id/public/uploads/post/3800/3800-1607161156-200717_IPP_Terapkan-Protokol-Kesehatan,-Ciptakan-Sekolah-Aman_DV1.jpg"
            )
        )
        list.add(
            ImageData(
                "https://indonesiantoday.id/wp-content/uploads/2017/05/tips_kosmetik_yang_cocok.jpg"
            )
        )
        adapter = ImageSliderAdapter(list)
        binding.ViewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.ViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun selectedDot(position: Int) {
        for(i in 0 until list.size){
            if(i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.grey))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    private fun setIndicator() {
        for(i in 0 until list.size){
            dots.add(TextView(this))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            }else{
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.Makananminuman -> {
                val PindahFoods = Intent(this, MakanMinumActivity::class.java)
                startActivity(PindahFoods)
            }

            R.id.Rumah-> {
                val PindahRumah = Intent(this, RumahActivity::class.java)
                startActivity(PindahRumah)
            }

            R.id.Bayi-> {
                val PindahBayi = Intent(this, BabyActivity::class.java)
                startActivity(PindahBayi)
            }

            R.id.Kesehatan-> {
                val PindahKesehatan = Intent(this, KesehatanActivity::class.java)
                startActivity(PindahKesehatan)
            }
        }
    }
}