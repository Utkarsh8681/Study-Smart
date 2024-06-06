//package com.example.studytime.ui.theme.data.local.repository
//
//import com.example.studytime.ui.theme.Domain.model.Task
//import com.example.studytime.ui.theme.Domain.model.repository.TaskRepository
//import com.example.studytime.ui.theme.data.local.local.TaskDao
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class TaskRepositoryImplementation @Inject constructor(
//    private val taskDao: TaskDao
//) :TaskRepository {
//    override suspend fun upsertTask(task: Task) {
//        taskDao.upsertTask(task)
//    }
//
//    override suspend fun deleteTask(taskId: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteTaskBySubjectId(subjectId: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getTaskById(taskId: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getTaskForSubjects(subjectId: Int): Flow<List<Task>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAllTasks(): Flow<List<Task>> {
//        TODO("Not yet implemented")
//    }
//}