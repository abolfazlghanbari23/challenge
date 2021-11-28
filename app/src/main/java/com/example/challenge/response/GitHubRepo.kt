package com.example.challenge.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
class GitHubRepo(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val primaryLanguage: String
)