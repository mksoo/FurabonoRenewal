package com.example.mks.furabonorenewal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.text.Charsets.UTF_8

class PageOneFragment : Fragment, View.OnClickListener{


    var aData: ArrayList<ItemData>? = null
    var adrlist : ListView? = null

    var address = ArrayList<ItemData>()
    
    private var items : ItemData? = null
    private var adapter : ListAdapter? = null
    var editText : EditText? = null

    //Floating Action Button//
    private var fabOpen : Animation? = null
    private var fabClose : Animation? = null
    var isFabOpen = false
    private var fab : FloatingActionButton? = null
    private var fabSub1 : FloatingActionButton? = null

    var isKeyboardShow = false


    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)

    fun anim(){
        if (isFabOpen) {
            fabSub1?.startAnimation(fabClose)
            fabSub1?.setClickable(false)
        } else {
            fabSub1?.startAnimation(fabOpen)
            fabSub1?.setClickable(true)
        }
        isFabOpen != isFabOpen
    }

    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.fab -> anim()
            R.id.fab_sub1 -> {
                anim()

                val intent = Intent(activity, ContactAdd::class.java)//TODO

                intent.putParcelableArrayListExtra("list", aData)

                startActivity(intent)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_page_one, container, false)

        fabOpen =  AnimationUtils.loadAnimation(activity, R.anim.fab_open)
        fabClose =  AnimationUtils.loadAnimation(activity, R.anim.fab_close)

        fab = layout.findViewById(R.id.fab)
        fabSub1 = layout.findViewById(R.id.fab_sub1)

        fab?.setOnClickListener(this)
        fabSub1?.setOnClickListener(this)

        adrlist = layout.findViewById(R.id.listView)
        editText = layout.findViewById(R.id.list_search_name)

        aData = ArrayList<ItemData>()
        aData = arguments?.getParcelableArrayList("list")

        adapter = com.example.mks.furabonorenewal.ListAdapter(aData)
        adrlist?.adapter = adapter

        loadJSON("");

        //Init List//
        //aData = getArguments().getParcelableArrayList("list");
        val adrAdapter = com.example.mks.furabonorenewal.ListAdapter(address)
        adrlist?.adapter = adrAdapter

        //폰으로 어플을 실행시키면 처음에 키보드가 안보였다가, editText를 누르면 키보드가 보여지고 그런 부분//
        val mgr = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        editText?.setOnClickListener(View.OnClickListener {
            if (isKeyboardShow) {
                //InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.showSoftInput(editText, 0)
                isKeyboardShow = false
            } else {
                mgr.hideSoftInputFromWindow(editText?.getWindowToken(), 0)
                isKeyboardShow = true
            }
        })

        //TODO search
        editText?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //loadJSON("");
                if(s.toString() == ""){
                    loadJSON("")
                    val adrAdapter = com.example.mks.furabonorenewal.ListAdapter(address)
                    adrlist?.adapter = adrAdapter
                }
                else {
                    //keyword에 무엇인가 있는경우
                    loadJSON(s.toString())

                    val adrAdapter = com.example.mks.furabonorenewal.ListAdapter(address)
                    adrlist?.adapter = adrAdapter
                    adrAdapter.notifyDataSetChanged()//TODO 왜이럴까
                }
            }
        })

        adrlist?.onItemClickListener = (AdapterView.OnItemClickListener{ _, _, position, _ ->
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + address.get(position).strNum))
            val bundle = Bundle()
            bundle.putString(ContactsContract.Intents.Insert.PHONE, address.get(position).strNum)
            intent.putExtras(bundle)

            startActivity(intent)
        })
        return layout
    }

    fun loadJSON(keyword : String) : String{
        var json = ""

        address.clear()

        try{
            val iS = activity!!.assets.open("Adr_data.json")
            val size = iS.available()
            val buffer = ByteArray(size) //TODO
            iS.read(buffer)
            iS.close()
            json = String(buffer, UTF_8)

            val obj = JSONObject(json)
            val addr = obj.getJSONArray("data")

            for (i in 0 until addr.length()) {
                if(keyword == ""){
                    val strname = addr.getJSONObject(i).getString("Name")
                    val strnum = addr.getJSONObject(i).getString("PhoneNum")
                    val intAge = addr.getJSONObject(i).getInt("age")
                    val strCountry = addr.getJSONObject(i).getString("Country")

                    address.add(ItemData(strname, strnum, strCountry, intAge))
                }
                else {
                    if(addr.getJSONObject(i).getString("Name").contains(keyword)){
                        val strname = addr.getJSONObject(i).getString("Name")
                        val strnum = addr.getJSONObject(i).getString("PhoneNum")
                        val intAge = addr.getJSONObject(i).getInt("age")
                        val strCountry = addr.getJSONObject(i).getString("Country")

                        address.add(ItemData(strname, strnum, strCountry, intAge))
                    }
                }
            }
        }catch (ex : IOException){
            ex.printStackTrace()
        }catch(e : JSONException){
            e.printStackTrace()
        }

        return json
    }

}