package com.prepmaster.app.data.model

data class PrepCategory(
    val id       : String,
    val title    : String,
    val titleBn  : String,
    val description : String,
    val color    : Long,
    val iconRes  : String,
    val prepositions: List<PrepItem>
)

data class PrepItem(
    val id        : String,
    val word      : String,
    val meaning   : String,      // Bengali meaning
    val definition: String,      // Bengali definition
    val structure : String,      // usage structure
    val examples  : List<PrepExample>,
    val notes     : String = "",
    val imageType : String = "",  // for SVG illustration key
    val categoryId: String = ""
)

data class PrepExample(
    val sentence   : String,
    val translation: String,
    val highlight  : String = ""  // which word to highlight
)

data class FlashCard(
    val id        : String,
    val prep      : String,
    val meaning   : String,
    val example   : String,
    val imageType : String,
    val categoryId: String,
    val color     : Long
)

data class QuizQuestion(
    val id         : String,
    val question   : String,
    val options    : List<String>,
    val correctIdx : Int,
    val explanation: String,
    val categoryId : String
)

data class PracticeItem(
    val id         : String,
    val sentence   : String,   // "The cat is ___ the table."
    val answer     : String,   // "on"
    val options    : List<String>,
    val translation: String,
    val explanation: String,
    val categoryId : String
)

data class StoryItem(
    val id         : String,
    val title      : String,
    val content    : String,   // story with [prep] markers
    val prepositions: List<String>,
    val questions  : List<StoryQuestion>
)

data class StoryQuestion(
    val question   : String,
    val answer     : String,
    val options    : List<String>
)

data class UserStats(
    val xp             : Int,
    val level          : Int,
    val streak         : Int,
    val totalStudied   : Int,
    val quizCorrect    : Int,
    val quizTotal      : Int,
    val practiceCorrect: Int,
    val practiceTotal  : Int,
    val flashcardsViewed: Int,
    val name           : String,
    val studyGoalMins  : Int,
    val todayMins      : Int
)
