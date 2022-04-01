package com.example.eyedroptracker.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.eyedroptracker.R
import com.example.eyedroptracker.models.User
import com.example.eyedroptracker.utils.AppDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.eyedroptracker.service.AlarmService

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        save_user.setOnClickListener {
//            runBlocking {
//                val tempUser = User("Asad", "Kothawala")
//                context?.let {
//                    AppDatabase(it).userDao().addUser(tempUser)
//                }
//            }
//        }

//        delete_user.setOnClickListener {
//            runBlocking {
//                val tempUser = User("Asad", "Kothawala")
//                context?.let {
//                    AppDatabase(it).userDao().delete(tempUser)
//                }
//            }
//        }
//
//        show_user.setOnClickListener {
//            runBlocking {
//                context?.let {
//                    val usersList = AppDatabase(it).userDao().getAllUsers()
//                    if (usersList.isNotEmpty()) {
//                        blank_text.text = usersList[0].firstName
//                    } else {
//                        blank_text.text = getString(R.string.hello_blank_fragment)
//                    }
//                }
//            }
//        }
    }



}