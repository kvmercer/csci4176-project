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

        /*
        // This is to show to set an alarm.
        var alarmService = AlarmService(requireContext())
        fab.setOnClickListener{
            setAlarm {
                alarmService.setExactAlarm(it, "title", "message", 1)
            }
        }

        // This is to show how you can delete an alarm.
        fab2.setOnClickListener{
            setAlarm {
                alarmService.cancelExactAlarm(it, "title", "message", 1)
            }
        }
        */


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

    /*
    // This was taken from the youtube tutorial referenced in the README file.
    private fun setAlarm(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            DatePickerDialog(
                requireContext(),
                0,
                { _, year, month, day ->
                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, day)
                    TimePickerDialog(
                        requireContext(),
                        0,
                        { _, hour, minute ->
                            this.set(Calendar.HOUR_OF_DAY, hour)
                            this.set(Calendar.MINUTE, minute)
                            callback(this.timeInMillis)
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        false
                    ).show()
                },
                this.get(Calendar.YEAR),
                this.get(Calendar.MONTH),
                this.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
     */


}