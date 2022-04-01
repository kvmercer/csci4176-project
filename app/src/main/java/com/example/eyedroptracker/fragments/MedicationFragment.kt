package com.example.eyedroptracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.eyedroptracker.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_medicine.*


class MedicationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val list = view.findViewById<TextView>(R.id.simpleListView) as ListView
//        val users: Array<String> = arrayOf<String>("tom", "jerry", "donald")
//
//        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(it, R.layout.fragment_medicine, users)
//        list.adapter = arrayAdapter

        editText1.setVisibility(GONE)
        submitBtn.setVisibility(GONE)

        fab3.setOnClickListener{
            editText1.setVisibility(VISIBLE);
            submitBtn.setVisibility(VISIBLE)
            fab3.setVisibility(GONE);
            submitBtn.setOnClickListener{
                val name = view.findViewById<TextView>(R.id.editText1) as EditText
                val medname : String =  name.text.toString()
                fab3.setVisibility(VISIBLE);
                editText1.setVisibility(GONE);
                submitBtn.setVisibility(GONE)
                view?.let { Snackbar.make(it, medname + " added.", Snackbar.LENGTH_SHORT) }?.show()
            }

        }
    }
}