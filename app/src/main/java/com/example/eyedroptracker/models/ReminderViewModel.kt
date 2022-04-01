package com.example.eyedroptracker.models

import androidx.lifecycle.*
import com.example.eyedroptracker.repos.ReminderRepository
import kotlinx.coroutines.launch

class ReminderViewModel(private val repository: ReminderRepository) : ViewModel() {

    val allReminders: LiveData<List<Reminder>> = repository.allReminders.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(reminder: Reminder) = viewModelScope.launch {
        repository.addReminder(reminder)
    }
    class ReminderViewModelFactory(private val repository: ReminderRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ReminderViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

