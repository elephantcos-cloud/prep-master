package com.prepmaster.app.data.content

import com.prepmaster.app.data.model.*

val categoryTime = PrepCategory(
    id = "time", title = "Time", titleBn = "সময়",
    description = "কখন হয়েছে তা বোঝাতে",
    color = 0xFFFF6B6B,
    iconRes = "ic_prep_time",
    prepositions = listOf(
        PrepItem("at (time)","at","নির্দিষ্ট সময়ে",
            "নির্দিষ্ট ঘড়ির সময়, সন্ধ্যা, রাত, মধ্যরাত, দুপুর ইত্যাদি।",
            "at + specific time / at noon / at night",
            listOf(
                PrepExample("The class starts at 9 AM.","ক্লাস সকাল ৯টায় শুরু হয়।","at"),
                PrepExample("We eat at noon.","আমরা দুপুরে খাই।","at"),
                PrepExample("She called at midnight.","সে মধ্যরাতে ফোন করল।","at"),
                PrepExample("The shop closes at 9 PM.","দোকান রাত ৯টায় বন্ধ হয়।","at"),
            ), "নির্দিষ্ট ঘড়ির সময়ে = at। at noon, at night, at midnight।", "img_at_time", "time"),

        PrepItem("on (time)","on","নির্দিষ্ট দিনে",
            "সপ্তাহের দিন, তারিখ, বিশেষ দিন বোঝাতে।",
            "on + day / on + date / on + special day",
            listOf(
                PrepExample("She came on Monday.","সে সোমবারে এল।","on"),
                PrepExample("My birthday is on 15 April.","আমার জন্মদিন ১৫ এপ্রিল।","on"),
                PrepExample("We met on New Year's Day.","আমরা নববর্ষের দিন দেখা করলাম।","on"),
                PrepExample("The event is on Saturday.","অনুষ্ঠানটি শনিবারে।","on"),
            ), "দিন ও তারিখে = on।", "img_on_time", "time"),

        PrepItem("in (time)","in","সময়কালে",
            "মাস, বছর, শতাব্দী, দশক, ঋতু, দিনের অংশ বোঝাতে।",
            "in + month / year / season / part of day",
            listOf(
                PrepExample("She was born in March.","সে মার্চ মাসে জন্মেছিল।","in"),
                PrepExample("The war ended in 1971.","যুদ্ধ ১৯৭১ সালে শেষ হয়েছিল।","in"),
                PrepExample("We go for walks in the morning.","আমরা সকালে হাঁটতে যাই।","in"),
                PrepExample("Flowers bloom in spring.","বসন্তে ফুল ফোটে।","in"),
            ), "মাস, বছর, ঋতু, দিনের অংশে = in। (except: at night)।", "img_in_time", "time"),

        PrepItem("before","before","আগে",
            "কোনো ঘটনার পূর্বে বা আগে।",
            "before + noun / before + verb-ing",
            listOf(
                PrepExample("Wash your hands before eating.","খাওয়ার আগে হাত ধুও।","before"),
                PrepExample("She left before noon.","সে দুপুরের আগে চলে গেল।","before"),
                PrepExample("He arrived before me.","সে আমার আগে পৌঁছাল।","before"),
            ), "'Before' = আগে; 'after' = পরে।", "img_before", "time"),

        PrepItem("after","after","পরে",
            "কোনো ঘটনার পরে।",
            "after + noun / after + verb-ing",
            listOf(
                PrepExample("She went home after school.","স্কুলের পরে সে বাড়ি গেল।","after"),
                PrepExample("We'll meet after the meeting.","মিটিংয়ের পরে আমরা দেখা করব।","after"),
                PrepExample("He felt better after resting.","বিশ্রামের পরে সে ভালো অনুভব করল।","after"),
            ), "'After' = পরে।", "img_after", "time"),

        PrepItem("during","during","সময়কালে / চলাকালীন",
            "কোনো ঘটনা বা সময়কালের মধ্যে।",
            "during + noun (period of time)",
            listOf(
                PrepExample("We slept during the journey.","যাত্রার সময় আমরা ঘুমিয়েছিলাম।","during"),
                PrepExample("He visited us during the holidays.","ছুটির সময় সে আমাদের সাথে দেখা করল।","during"),
                PrepExample("Don't talk during the exam.","পরীক্ষার সময় কথা বলো না।","during"),
            ), "'During' = কোনো সময়কালের মধ্যে।", "img_during", "time"),

        PrepItem("since","since","থেকে (অতীত থেকে এখন পর্যন্ত)",
            "অতীতের একটি নির্দিষ্ট সময় থেকে এখন পর্যন্ত চলমান।",
            "since + specific point in time (used with perfect tense)",
            listOf(
                PrepExample("I have lived here since 2015.","আমি ২০১৫ সাল থেকে এখানে বাস করছি।","since"),
                PrepExample("She has been crying since morning.","সে সকাল থেকে কাঁদছে।","since"),
                PrepExample("He hasn't eaten since yesterday.","সে গতকাল থেকে খায়নি।","since"),
            ), "'Since' = নির্দিষ্ট সময় থেকে এখন পর্যন্ত (perfect tense এ)।", "img_since", "time"),

        PrepItem("for (time)","for","কতক্ষণ / সময়ের পরিমাণ",
            "সময়ের পরিমাণ বা সময়কাল বোঝাতে।",
            "for + duration (minutes/hours/days/years)",
            listOf(
                PrepExample("I studied for two hours.","আমি দুই ঘণ্টা পড়াশোনা করলাম।","for"),
                PrepExample("She waited for a long time.","সে অনেকক্ষণ অপেক্ষা করল।","for"),
                PrepExample("He lived in Paris for three years.","সে তিন বছর প্যারিসে থাকল।","for"),
            ), "'For' = সময়ের পরিমাণ; 'since' = শুরুর বিন্দু।", "img_for_time", "time"),

        PrepItem("by (time)","by","এর মধ্যে / এর আগে",
            "নির্দিষ্ট সময়ের আগেই কাজ শেষ করতে হবে।",
            "by + time / date",
            listOf(
                PrepExample("Submit the form by Friday.","শুক্রবারের মধ্যে ফর্ম জমা দাও।","by"),
                PrepExample("I'll be done by 5 PM.","বিকেল ৫টার মধ্যে আমি শেষ করব।","by"),
                PrepExample("Finish it by tomorrow.","কালের মধ্যে শেষ করো।","by"),
            ), "'By' = নির্দিষ্ট সময়ের আগেই / মধ্যে।", "img_by_time", "time"),

        PrepItem("until/till","until / till","পর্যন্ত",
            "কোনো সময় পর্যন্ত কিছু চলতে থাকা।",
            "until/till + time",
            listOf(
                PrepExample("Wait until I come back.","আমি ফিরে না আসা পর্যন্ত অপেক্ষা করো।","until"),
                PrepExample("He worked till midnight.","সে মধ্যরাত পর্যন্ত কাজ করল।","till"),
                PrepExample("The shop is open until 10 PM.","দোকান রাত ১০টা পর্যন্ত খোলা।","until"),
            ), "'Until/till' = পর্যন্ত। 'Till' আরো casual।", "img_until", "time"),

        PrepItem("within","within","এর মধ্যে",
            "নির্দিষ্ট সময়ের ভেতরে কাজ সম্পন্ন হওয়া।",
            "within + duration",
            listOf(
                PrepExample("I'll call you within an hour.","এক ঘণ্টার মধ্যে ফোন করব।","within"),
                PrepExample("The package arrives within 3 days.","প্যাকেজ ৩ দিনের মধ্যে আসবে।","within"),
            ), "'Within' = নির্দিষ্ট সময়ের ভেতরে।", "img_within", "time"),

        PrepItem("throughout","throughout","সর্বত্র / পুরো সময়ব্যাপী",
            "কোনো সময়কালের পুরোটা জুড়ে।",
            "throughout + period",
            listOf(
                PrepExample("It rained throughout the day.","সারাদিন বৃষ্টি হলো।","throughout"),
                PrepExample("He was quiet throughout the meeting.","পুরো মিটিং জুড়ে সে চুপ থাকল।","throughout"),
            ), "'Throughout' = পুরো সময় জুড়ে।", "img_throughout", "time"),

        PrepItem("from...to","from...to","থেকে...পর্যন্ত",
            "কোনো সময়ের শুরু থেকে শেষ পর্যন্ত।",
            "from + time + to + time",
            listOf(
                PrepExample("The office is open from 9 to 5.","অফিস ৯টা থেকে ৫টা পর্যন্ত খোলা।","from...to"),
                PrepExample("He studied from morning to night.","সে সকাল থেকে রাত পর্যন্ত পড়ল।","from...to"),
            ), "'From...to' = শুরু থেকে শেষ পর্যন্ত।", "img_from_to", "time"),

        PrepItem("around (time)","around","প্রায় / আনুমানিক",
            "নির্দিষ্ট নয়, আনুমানিক সময় বোঝাতে।",
            "around + time",
            listOf(
                PrepExample("Come around 3 PM.","বিকেল ৩টার দিকে এসো।","around"),
                PrepExample("She arrived around midnight.","সে মধ্যরাতের দিকে পৌঁছাল।","around"),
            ), "'Around' = আনুমানিক সময়।", "img_around_time", "time"),
    )
)
