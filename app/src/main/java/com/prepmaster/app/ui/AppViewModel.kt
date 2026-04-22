package com.prepmaster.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.prepmaster.app.data.db.entity.BadgeEntity
import com.prepmaster.app.data.db.entity.ProgressEntity
import com.prepmaster.app.data.model.*
import com.prepmaster.app.data.repository.AppRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AppViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = AppRepository.get(app)

    val stats    : StateFlow<UserStats> = repo.statsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserStats(0,1,0,0,0,0,0,0,0,"শিক্ষার্থী",20,0))
    val progress : StateFlow<List<ProgressEntity>> = repo.progressFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val badges   : StateFlow<List<BadgeEntity>> = repo.badgesFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val categories  = repo.getCategories()
    val allFlashCards = repo.getAllFlashCards()
    val quizQuestions = repo.getQuizQuestions()
    val practiceItems = repo.getPracticeItems()
    val stories       = repo.getStories()

    fun markLearned(prep: PrepItem)          = viewModelScope.launch { repo.markLearned(prep) }
    fun recordQuiz(c: Int, t: Int)           = viewModelScope.launch { repo.recordQuiz(c, t) }
    fun recordPractice(c: Int, t: Int)       = viewModelScope.launch { repo.recordPractice(c, t) }
    fun recordFlashcard()                    = viewModelScope.launch { repo.recordFlashcard() }
    fun updateUser(u: com.prepmaster.app.data.db.entity.UserEntity) =
        viewModelScope.launch { repo.updateUser(u) }

    fun getCategory(id: String) = repo.getCategory(id)
    fun xpProgress(xp: Int)    = repo.xpProgress(xp)
}
