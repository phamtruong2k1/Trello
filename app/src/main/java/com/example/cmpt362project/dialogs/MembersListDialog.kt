package com.example.cmpt362project.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmpt362project.R
import com.example.cmpt362project.adapters.MemberItemAdapter
import kotlinx.android.synthetic.main.dialog_list.view.*
import com.example.cmpt362project.models.User

abstract class MembersListDialog  (context : Context,
                                   private var list : ArrayList<User>,
                                   private val title : String = ""
) : Dialog(context){

    private var adapter : MemberItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_list, null)
        setContentView(view)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setUpRecyclerView(view)
    }

    private fun setUpRecyclerView(view : View){
        view.dialog_title_tv.text = title

        if(list.size > 0 ){
            view.dialog_list_rv.layoutManager = LinearLayoutManager(context)
            adapter = MemberItemAdapter(context, list)
            view.dialog_list_rv.adapter = adapter
            adapter!!.setOnClickListener(object  : MemberItemAdapter.OnClickListener{
                override fun onClick(position: Int, user: User, action: String) {
                    dismiss()
                    onItemSelected(user,action)
                }
            })
        }
    }

    protected abstract fun onItemSelected(user : User, color : String)
}