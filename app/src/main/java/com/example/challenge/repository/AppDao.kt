package com.example.challenge.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.challenge.response.GitHubRepo

@Dao
interface AppDao {

    @Insert
    fun insert(repo: GitHubRepo)

    @Update
    fun update(repo: GitHubRepo)

    @Delete
    fun delete(repo: GitHubRepo)

    @Query("SELECT * FROM repo_table ORDER BY name DESC")
    fun getAllRepos(): LiveData<List<GitHubRepo>>
}