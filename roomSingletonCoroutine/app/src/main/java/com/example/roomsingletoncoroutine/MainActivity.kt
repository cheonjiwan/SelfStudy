package com.example.roomsingletoncoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import com.example.roomsingletoncoroutine.database.User
import com.example.roomsingletoncoroutine.database.UserDatabase
import com.example.roomsingletoncoroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabase.getInstance(applicationContext)!!

        binding.btnSave.setOnClickListener {
            addUser()
            refreshUserList()
        }

    }

    private fun addUser() {
        var name = binding.editName.text.toString()
        var age = binding.editAge.text.toString()
        var phone = binding.editPhone.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            db.userDao().insert(User(name, age, phone))
        }
    }

    private fun refreshUserList() {
        var userList = ""

        CoroutineScope(Dispatchers.Main).launch {
            val users = CoroutineScope(Dispatchers.IO).async {
                db.userDao().getAll()
            }.await()

            for (user in users){
                userList += "이름: ${user.name}, 나이: ${user.age}, 번호: ${user.phone}\n"
            }
            binding.textPerson.text = userList
        }
    }
}