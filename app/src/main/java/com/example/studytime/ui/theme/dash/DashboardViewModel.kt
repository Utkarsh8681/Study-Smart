package com.example.studytime.ui.theme.dash

import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studytime.ui.theme.Domain.model.Session
import com.example.studytime.ui.theme.Domain.model.Subject
import com.example.studytime.ui.theme.Domain.model.Task
import com.example.studytime.ui.theme.Domain.model.repository.SessionRepository
import com.example.studytime.ui.theme.Domain.model.repository.SubjectRepository
import com.example.studytime.ui.theme.Domain.model.repository.TaskRepository
import com.example.studytime.ui.theme.Util.SnackbarEvent
import com.example.studytime.ui.theme.Util.toHours
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class DashboardViewModel @Inject constructor(
   private val subjectRepository : SubjectRepository,
    private val sessionRepository : SessionRepository,
    private val taskRepository :TaskRepository
):ViewModel() {

    private val _state = MutableStateFlow(DashboardState())

    val state = combine(
        _state,
        subjectRepository.getTotalSubjectCount(),
        subjectRepository.getTotalGoalHours(),
        subjectRepository.getAllSubjects(),
        sessionRepository.getTotalSessionDuration()
    ){ state, subjectCount, goalHours, subjects, totalSessionDuration ->
        state.copy(
            totalSubjectCount = subjectCount,
            totalGoalStudiedHours = goalHours,
            subjects =  subjects,
            totalStudiedHours = totalSessionDuration.toHours()
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardState()
    )
    val tasks :StateFlow<List<Task>> = taskRepository.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val recentSessions :StateFlow<List<Session>> = sessionRepository.getRecentFiveSessions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    private val _snackbarEventFlow = MutableSharedFlow<SnackbarEvent>()
    val snackbarEventFlow = _snackbarEventFlow.asSharedFlow()

    fun event(event : DashboardEvent){
        when(event){
            is DashboardEvent.OnSubjectNameChange -> {
                _state.update {
                    it.copy(subjectName = event.name)
                }
            }
            is DashboardEvent.OnGoalStudyHoursChange -> {
                _state.update {
                    it.copy(goalStudyHours = event.hours)
                }
            }
            is DashboardEvent.OnTaskIsCompleteButtonClick -> {
                _state.update {
                    it.copy()
                }
            }
            is DashboardEvent.OnDeleteSessionButtonClick -> {
                _state.update {
                    it.copy(session = event.session)
                }
            }
            is DashboardEvent.OnSubjectCardColorChange -> {
                _state.update {
                    it.copy(subjectCardColors = event.colors)
                }
            }
            DashboardEvent.SaveSubject -> saveSubject()
            DashboardEvent.DeleteSession -> TODO()
        }
    }

    private fun saveSubject() {

        viewModelScope.launch {
            try {


                subjectRepository.upsertSubject(
                    subject = Subject(
                        name = state.value.subjectName,
                        goalHours = state.value.goalStudyHours.toFloatOrNull() ?: 1f,
                        colors = state.value.subjectCardColors.map { it.toArgb() },
                    )
                )
                _state.update {
                    it.copy(
                        subjectName = "",
                        goalStudyHours = "",
                        subjectCardColors = Subject.subjectCardColors.random()
                    )
                }
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Subject added sucessfully")
                    )
            }catch (e:Exception){
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                     "Couldn't save subject . ${e.message}",
                    SnackbarDuration.Long
                    )
                )
            }
        }
    }
}