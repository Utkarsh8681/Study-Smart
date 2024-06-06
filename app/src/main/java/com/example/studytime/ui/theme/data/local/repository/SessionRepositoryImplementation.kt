//package com.example.studytime.ui.theme.data.local.repository
//
//import com.example.studytime.ui.theme.Domain.model.Session
//import com.example.studytime.ui.theme.Domain.model.repository.SessionRepository
//import com.example.studytime.ui.theme.data.local.local.SessionDao
//import kotlinx.coroutines.flow.Flow
//
//class SessionRepositoryImplementation(
//    private val sessionDao: SessionDao
//) : SessionRepository {
//    override suspend fun insertSession(session: Session) {
//        sessionDao.insertSession(session)
//    }
//
//    override suspend fun deleteSession(session: Session) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAllSessions(): Flow<List<Session>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getRecentSessionForSubject(subjectId: Int): Flow<List<Session>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun grtTotalSessionDuration(): Flow<Long> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getTotalSessionDurationBySubjectId(subjectId: Int): Flow<Long> {
//        TODO("Not yet implemented")
//    }
//
//    override fun deleteSessionBySubjectId(subjectId: Int) {
//        TODO("Not yet implemented")
//    }
//}