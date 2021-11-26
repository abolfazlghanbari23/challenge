package com.example.challenge.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
class GitHubRepo(@PrimaryKey val id: Long, val name: String)