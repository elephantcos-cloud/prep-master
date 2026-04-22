package com.prepmaster.app.notification

object ReminderMessages {
    private val morning = listOf(
        Pair("সুপ্রভাত! Preposition শিখতে শুরু করো!","আজ ৫টি নতুন preposition শিখলে তুমি এগিয়ে যাবে।"),
        Pair("ভোরের শিক্ষার্থী!","Preposition এর ভেতরের রহস্য আজ উদ্ঘাটন করো!"),
        Pair("নতুন দিন, নতুন শেখা!","আজ 'in', 'on', 'at' এর পার্থক্য ভালো করে শিখো।"),
    )
    private val afternoon = listOf(
        Pair("দুপুরের বিরতিতে Preposition!","মাত্র ১০ মিনিট Practice করো — তোমার English উন্নত হবে।"),
        Pair("Streak বাঁচাও!","আজকের Study বাকি আছে। একটি Quiz দাও!"),
        Pair("এক কাপ চা আর Preposition!","'Before' এবং 'After' কখন ব্যবহার হয় জানো?"),
    )
    private val evening = listOf(
        Pair("সন্ধ্যায় Preposition Practice!","Flashcard দিয়ে আজকের শেখা review করো।"),
        Pair("আজকের লক্ষ্য পূরণ করো!","৫টি Practice sentence করলেই আজকের কোটা পূর্ণ।"),
        Pair("'In', 'On', 'At' তুমি কি জানো?","এখনই Quiz দিয়ে দেখো কতটা মনে আছে!"),
    )
    private val night = listOf(
        Pair("রাতের পেঁচা Preposition শিখছে!","ঘুমানোর আগে একটি Story পড়ো।"),
        Pair("আজকের শেষ সুযোগ!","মাত্র একটি Flashcard দেখো — ঘুম ভালো হবে!"),
        Pair("Streak মিস করো না!","মধ্যরাতের আগে একটি Preposition শিখো।"),
    )

    fun get(hour: Int): Pair<String,String> = when (hour) {
        in 5..11  -> morning.random()
        in 12..16 -> afternoon.random()
        in 17..20 -> evening.random()
        else      -> night.random()
    }
}
