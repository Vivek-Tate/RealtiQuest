package com.team2.todo.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.team2.todo.data.entities.Images
import com.team2.todo.data.entities.SubTodo
import kotlinx.coroutines.flow.Flow

@Dao
interface SubTodoDao {
    @Upsert
    suspend fun upsertSubTodo(subTodoEntity: SubTodo)

    @Query("SELECT * FROM subtodos where todoId = :todoId")
    fun getSubTodosBasedOnTodo(todoId: Int): Flow<List<SubTodo>>

    @Query("SELECT  * FROM subtodos where subTodoId = :subTodoId ORDER BY priority ASC")
    fun getSubTodosBasedOnSubTodoIdOrderedByPriority(subTodoId: Int): Flow<SubTodo>

    @Transaction
    @Query("UPDATE subtodos SET status = :status WHERE subTodoId = :subTodoId")
    suspend fun updateSubTodoStatus(subTodoId: Int, status: Boolean)

}