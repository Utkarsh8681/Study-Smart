package com.example.studytime.ui.theme.data.local.repository

import com.example.studytime.ui.theme.Domain.model.Session
import com.example.studytime.ui.theme.Domain.model.repository.SessionRepository
import com.example.studytime.ui.theme.data.local.local.SessionDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class SessionRepositoryImplementation @Inject constructor(
    private val sessionDao: SessionDao
) : SessionRepository {
    override suspend fun insertSession(session: Session) {
        sessionDao.insertSession(session)
    }

    override suspend fun deleteSession(session: Session) {
        TODO("Not yet implemented")
    }

    override fun getAllSessions(): Flow<List<Session>> {
        TODO("Not yet implemented")
    }

    override fun getRecentFiveSessions() : Flow<List<Session>> {
        return sessionDao.getAllSessions().take(count = 5)
    }

    override fun getRecentSessionForSubject(subjectId: Int): Flow<List<Session>> {
        TODO("Not yet implemented")
    }

    override fun getTotalSessionDuration(): Flow<Long> {
       return sessionDao.getTotalSessionDuration()
    }

    override fun getTotalSessionDurationBySubjectId(subjectId: Int): Flow<Long> {
        return sessionDao.getTotalSessionDuration()
    }

    override fun deleteSessionBySubjectId(subjectId: Int) {
        TODO("Not yet implemented")
    }
}