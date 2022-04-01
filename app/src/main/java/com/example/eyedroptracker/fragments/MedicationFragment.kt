package com.example.eyedroptracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.eyedroptracker.EyedropTrackerApp
import com.example.eyedroptracker.R
import com.example.eyedroptracker.dao.UserDao
import com.example.eyedroptracker.models.User
import com.example.eyedroptracker.models.UserViewModel
import com.example.eyedroptracker.repos.UserRepository
import com.example.eyedroptracker.utils.AppDatabase
import kotlinx.android.synthetic.main.fragment_medicine.*
import kotlinx.coroutines.runBlocking


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

        val userDao = AppDatabase.getDatabase(requireActivity().applicationContext, lifecycleScope).userDao()
        val userRepository = UserRepository(userDao)

        val userViewModel: UserViewModel by viewModels {
            UserViewModel.UserViewModelFactory(userRepository)
        }

        save_user.setOnClickListener {
            runBlocking {
                val tempUser = User("Asad", "Kothawala")
                context?.let {
                    userViewModel.insert(tempUser)
                }
            }
        }

        delete_user.setOnClickListener {
            runBlocking {
                val tempUser = User("Asad", "Kothawala")
                context?.let {
                    userViewModel.delete(tempUser)
                }
            }
        }

        show_user.setOnClickListener {
            runBlocking {
                context?.let {
                    userViewModel.allUsers.observe(viewLifecycleOwner) { users ->
                        if (users.isNotEmpty()) {
                            blank_text.text = users[0].firstName
                        } else {
                            blank_text.text = getString(R.string.hello_blank_fragment)
                        }
                    }

                }
            }
        }
    }
}