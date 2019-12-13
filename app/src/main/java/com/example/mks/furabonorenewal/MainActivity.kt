package com.example.mks.furabonorenewal

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private val address = ArrayList<ItemData>()

    private lateinit var BasePath: String

    internal lateinit var inputName: String
    internal lateinit var inputAge: String
    internal lateinit var inputPhone: String
    internal lateinit var inputCountry: String

    //permissions...
    // TODO Q. 그럼 이런거는 어떻게 설정하냐
    private val EXTERNAL_PERMS = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)

    private val EXTERNAL_REQUEST = 138


    val TAG = "MainActivityDebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "BasePath : ??")


        requestForPermission()

        loadJSON()

        BasePath = Environment.getExternalStorageDirectory().toString()

        Log.d(TAG, "BasePath : $BasePath")


        //TODO 왜 set이 싹 다 사라졌을까
        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.addTab(tabLayout.newTab().setText("Tab One"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab Two"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab Three"))
        //tabLayout.tabGravity(TabLayout.GRAVITY_FILL)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        viewPager = findViewById(R.id.pager)


        val pagerAdapter = TabPagerAdapter(supportFragmentManager, tabLayout.tabCount, address, BasePath)
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
        })

        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.agenda)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.photoalbum)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.player)
    }

    private fun loadJSON(): String? {
        val json: String? = null
        try {
            val iS: InputStream = assets.open("Adr_data.json");
            val size = iS.available()
            val buffer = ByteArray(size)
            iS.read(buffer)
            iS.close()
//            TODO json = String(buffer, "UTF-8")

            val obj = JSONObject(json)
            val addr = obj.getJSONArray("data")

            for(i in 0 until addr.length() ){
                val strName = addr.getJSONObject(i).getString("Name")
                val strNum = addr.getJSONObject(i).getString("PhoneNum")
                val intAge = addr.getJSONObject(i).getInt("age")
                val strCountry = addr.getJSONObject(i).getString("Country")

                address.add(ItemData(strName, strNum, strCountry, intAge))
            }
        } catch (ex: IOException){
            ex.printStackTrace()
        }
        catch(e: JSONException){
            e.printStackTrace()
        }

        return json
    }

    private fun requestForPermission(): Boolean{
        var isPermissionOn = true
        val version = Build.VERSION.SDK_INT
        if (version >= 23){
            if(!canAccessExternalSd()){
                isPermissionOn = false
                requestPermissions(EXTERNAL_PERMS, EXTERNAL_REQUEST)
            }
        }

        return isPermissionOn
    }

    private fun canAccessExternalSd(): Boolean{
        return (hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }

    private fun hasPermission(perm: String): Boolean{
        return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, perm))
    }
}
