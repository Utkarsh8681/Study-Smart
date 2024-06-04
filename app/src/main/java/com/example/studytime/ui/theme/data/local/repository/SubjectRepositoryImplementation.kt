package com.example.studytime.ui.theme.data.local.repository

import com.example.studytime.ui.theme.Domain.model.Subject
import com.example.studytime.ui.theme.Domain.model.repository.SubjectRepository
import com.example.studytime.ui.theme.data.local.local.SubjectDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectRepositoryImplementation @Inject constructor(
    private val subjectDao: SubjectDao
) : SubjectRepository {
    override suspend fun upsertSubject(subject: Subject) {
       subjectDao.upsertSubject(subject)
    }

    override fun getTotalSubjectCount(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getTotalHours(): Flow<Float> {
        TODO("Not yet implemented")
    }

    override suspend fun getSubjectById(subjectId: Int): Subject? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSubject(subjectId: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllSubjects(): Flow<List<Subject>> {
        TODO("Not yet implemented")
    }

}