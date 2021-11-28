package com.example.challenge.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(@PrimaryKey val id: Long,
                val avatarUrl: String,
                val name: String,
                val email: String,
                val followers: Follow,
                val following: Follow)
