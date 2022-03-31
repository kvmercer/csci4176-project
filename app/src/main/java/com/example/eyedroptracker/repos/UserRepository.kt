package com.example.eyedroptracker.repos

import androidx.annotation.WorkerThread
import com.example.eyedroptracker.dao.UserDao
import com.example.eyedroptracker.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}