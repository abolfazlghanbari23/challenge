package com.example.challenge.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.challenge.response.GitHubRepo
import com.example.challenge.response.User

@Dao
interface AppDao {

    @Insert
    fun insert(repo: GitHubRepo)

    @Insert
    fun insert(user: User)

    @Update
    fun update(repo: GitHubRepo)

    @Update
    fun update(user: User)

    @Delete
    fun delete(repo: GitHubRepo)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM repo_table ORDER BY name DESC")
    fun getAllRepos(): LiveData<List<GitHubRepo>>

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<User>>
}