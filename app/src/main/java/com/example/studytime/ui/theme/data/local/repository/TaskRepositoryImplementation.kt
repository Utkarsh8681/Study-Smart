package com.example.studytime.ui.theme.data.local.repository

import com.example.studytime.tasks
import com.example.studytime.ui.theme.Domain.model.Task
import com.example.studytime.ui.theme.Domain.model.repository.TaskRepository
import com.example.studytime.ui.theme.data.local.local.TaskDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImplementation @Inject constructor(
    private val taskDao: TaskDao
) :TaskRepository {
    override suspend fun upsertTask(task: Task) {
        taskDao.upsertTask(task)
    }

    override suspend fun deleteTask(taskId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTaskBySubjectId(subjectId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(taskId: Int) {
        TODO("Not yet implemented")
    }

    override fun getTaskForSubjects(subjectId: Int): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
            .map { tasks -> tasks.filter { it.isComplete.not() } }
            .map { tasks -> sortTasks(tasks) }
    }

    private fun sortTasks(tasks : List<Task>) : List<Task>{
        return tasks.sortedWith(compareBy<Task>{it.dueDate}.thenByDescending { it.priority })
    }
}