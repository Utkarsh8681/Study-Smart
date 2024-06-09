package com.example.studytime.ui.theme.Dashboard

import androidx.lifecycle.ViewModel
import com.example.studytime.ui.theme.Domain.model.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class SessionViewModel @Inject constructor(

    val sessionRepository : SessionRepository
):ViewModel() {
}