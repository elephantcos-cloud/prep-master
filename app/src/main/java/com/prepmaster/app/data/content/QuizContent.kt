package com.prepmaster.app.data.content

import com.prepmaster.app.data.model.QuizQuestion
import com.prepmaster.app.data.model.PracticeItem
import com.prepmaster.app.data.model.StoryItem
import com.prepmaster.app.data.model.StoryQuestion

val allQuizQuestions = listOf(
    // PLACE
    QuizQuestion("q1","The ball is ___ the box.",listOf("in","on","at","under"),0,"'In' মানে ভেতরে (enclosed space)।","place"),
    QuizQuestion("q2","The book is ___ the table.",listOf("in","over","on","at"),2,"'On' মানে উপরে (surface এ স্পর্শ করে)।","place"),
    QuizQuestion("q3","She is waiting ___ the bus stop.",listOf("in","at","on","by"),1,"নির্দিষ্ট বিন্দু = at।","place"),
    QuizQuestion("q4","The cat is hiding ___ the sofa.",listOf("over","above","behind","in front of"),2,"'Behind' = পেছনে।","place"),
    QuizQuestion("q5","The park is ___ the school and the hospital.",listOf("among","between","beside","near"),1,"দুটির মাঝে = between।","place"),
    QuizQuestion("q6","She sat ___ the window.",listOf("in","on","by","at"),2,"'By' = পাশে/কাছে।","place"),
    QuizQuestion("q7","The temperature is ___ 40 degrees.",listOf("on","over","above","up"),2,"স্পর্শ না করে উপরে = above।","place"),
    QuizQuestion("q8","He stood ___ the door.",listOf("in","at","on","by"),1,"নির্দিষ্ট বিন্দু = at।","place"),
    QuizQuestion("q9","The fish swim ___ the surface.",listOf("under","below","beneath","down"),1,"স্পর্শ না করে নিচে = below।","place"),
    QuizQuestion("q10","She lives ___ the building opposite ours.",listOf("in","at","inside","opposite"),3,"বিপরীত দিকে = opposite।","place"),

    // TIME
    QuizQuestion("q11","The class starts ___ 9 AM.",listOf("in","on","at","by"),2,"নির্দিষ্ট সময় = at।","time"),
    QuizQuestion("q12","She was born ___ March.",listOf("at","on","in","by"),2,"মাস = in।","time"),
    QuizQuestion("q13","We met ___ Monday.",listOf("in","at","on","by"),2,"দিন = on।","time"),
    QuizQuestion("q14","He has lived here ___ 2015.",listOf("for","since","from","until"),1,"নির্দিষ্ট সময় থেকে এখন = since।","time"),
    QuizQuestion("q15","I studied ___ two hours.",listOf("since","for","during","within"),1,"সময়ের পরিমাণ = for।","time"),
    QuizQuestion("q16","Submit the form ___ Friday.",listOf("until","by","before","on"),1,"deadline = by।","time"),
    QuizQuestion("q17","Don't talk ___ the exam.",listOf("during","for","while","in"),0,"সময়কালের মধ্যে = during।","time"),
    QuizQuestion("q18","The office is open ___ 9 ___ 5.",listOf("from...to","since...till","at...at","in...in"),0,"শুরু থেকে শেষ = from...to।","time"),
    QuizQuestion("q19","I'll call you ___ an hour.",listOf("within","in","for","by"),0,"সময়ের ভেতরে = within।","time"),
    QuizQuestion("q20","Wash your hands ___ eating.",listOf("after","before","during","while"),1,"আগে = before।","time"),

    // MOVEMENT
    QuizQuestion("q21","She went ___ school.",listOf("to","at","in","into"),0,"গন্তব্য = to।","movement"),
    QuizQuestion("q22","The cat jumped ___ the table.",listOf("on","onto","over","above"),1,"উঠে যাওয়া = onto।","movement"),
    QuizQuestion("q23","She walked ___ the park.",listOf("across","through","along","over"),1,"মধ্য দিয়ে = through।","movement"),
    QuizQuestion("q24","He ran ___ the road.",listOf("through","along","across","over"),2,"পার হয়ে = across।","movement"),
    QuizQuestion("q25","She climbed ___ the mountain.",listOf("up","on","over","onto"),0,"উপরের দিকে = up।","movement"),
    QuizQuestion("q26","The ball rolled ___ the hill.",listOf("up","down","along","across"),1,"নিচের দিকে = down।","movement"),
    QuizQuestion("q27","She moved ___ the door.",listOf("toward","to","into","at"),0,"দিকে = toward।","movement"),
    QuizQuestion("q28","Get ___ the bus here.",listOf("off","out","down","from"),0,"নামা = off।","movement"),
    QuizQuestion("q29","She came ___ Chittagong.",listOf("to","from","out of","in"),1,"উৎস = from।","movement"),
    QuizQuestion("q30","She walked ___ the beach.",listOf("along","across","through","over"),0,"বরাবর = along।","movement"),

    // MANNER
    QuizQuestion("q31","She wrote ___ a pen.",listOf("by","with","using","through"),1,"যন্ত্র = with।","manner"),
    QuizQuestion("q32","She traveled ___ train.",listOf("with","by","on","in"),1,"পরিবহন = by।","manner"),
    QuizQuestion("q33","She sings ___ a nightingale.",listOf("as","like","with","by"),1,"তুলনা = like।","manner"),
    QuizQuestion("q34","She works ___ a doctor.",listOf("like","as","by","with"),1,"ভূমিকা = as।","manner"),
    QuizQuestion("q35","Don't go ___ permission.",listOf("with","by","without","from"),2,"ছাড়া = without।","manner"),

    // CAUSE
    QuizQuestion("q36","The match was canceled ___ rain.",listOf("due to","because","for","by"),0,"noun এর আগে কারণ = due to / because of।","cause"),
    QuizQuestion("q37","She is famous ___ her singing.",listOf("by","with","for","of"),2,"কারণ/উদ্দেশ্য = for।","cause"),
    QuizQuestion("q38","She did it ___ kindness.",listOf("from","with","out of","by"),2,"আবেগ থেকে = out of।","cause"),
    QuizQuestion("q39","He is suffering ___ fever.",listOf("of","with","from","by"),2,"suffer from।","cause"),
    QuizQuestion("q40","He failed ___ laziness.",listOf("because","due to","for","by"),1,"formal কারণ = due to।","cause"),

    // AGENT
    QuizQuestion("q41","The book was written ___ Tagore.",listOf("with","by","from","of"),1,"Passive: কর্তা = by।","agent"),
    QuizQuestion("q42","The letter was written ___ a pen.",listOf("by","with","using","on"),1,"যন্ত্র = with।","agent"),
    QuizQuestion("q43","She talked ___ the phone.",listOf("in","by","on","with"),2,"ডিভাইস = on।","agent"),

    // POSSESSION
    QuizQuestion("q44","The capital ___ Bangladesh is Dhaka.",listOf("of","for","in","at"),0,"মালিকানা/সম্পর্ক = of।","possession"),
    QuizQuestion("q45","He is kind ___ everyone.",listOf("of","for","to","with"),2,"সম্পর্ক = to।","possession"),

    // COMPOUND
    QuizQuestion("q46","She succeeded ___ difficulties.",listOf("in spite of","despite of","because of","in case of"),0,"সত্ত্বেও = in spite of।","compound"),
    QuizQuestion("q47","She drank juice ___ tea.",listOf("instead of","in place","rather","without"),0,"পরিবর্তে = instead of।","compound"),
    QuizQuestion("q48","___ the report, it will rain.",listOf("According to","Due to","In case of","By means of"),0,"অনুযায়ী = according to।","compound"),
    QuizQuestion("q49","___ fire, use the stairs.",listOf("In spite of","In case of","Instead of","On top of"),1,"পরিস্থিতিতে = in case of।","compound"),
    QuizQuestion("q50","He spoke ___ the team.",listOf("on behalf of","instead of","in front of","according to"),0,"পক্ষ থেকে = on behalf of।","compound"),
)

val allPracticeItems = listOf(
    PracticeItem("p1","The cat is ___ the box.","in",listOf("in","on","at","under"),"বিড়ালটি বাক্সের ভেতরে আছে।","বন্ধ স্থান = in।","place"),
    PracticeItem("p2","The book is ___ the table.","on",listOf("in","on","at","over"),"বইটি টেবিলের উপরে আছে।","surface = on।","place"),
    PracticeItem("p3","She is ___ the bus stop.","at",listOf("in","at","on","by"),"সে বাস স্টপে আছে।","নির্দিষ্ট বিন্দু = at।","place"),
    PracticeItem("p4","He sat ___ her.","beside",listOf("beside","behind","near","next"),"সে তার পাশে বসল।","পাশে = beside।","place"),
    PracticeItem("p5","The park is ___ the school and the hospital.","between",listOf("between","among","beside","near"),"পার্কটি মাঝে।","দুটির মাঝে = between।","place"),
    PracticeItem("p6","The class starts ___ 9 AM.","at",listOf("at","in","on","by"),"ক্লাস ৯টায় শুরু।","নির্দিষ্ট সময় = at।","time"),
    PracticeItem("p7","She was born ___ March.","in",listOf("at","on","in","by"),"সে মার্চে জন্মেছিল।","মাস = in।","time"),
    PracticeItem("p8","We met ___ Monday.","on",listOf("in","at","on","by"),"সোমবারে দেখা।","দিন = on।","time"),
    PracticeItem("p9","He has lived here ___ 2015.","since",listOf("for","since","from","until"),"২০১৫ থেকে এখন পর্যন্ত।","নির্দিষ্ট সময় থেকে = since।","time"),
    PracticeItem("p10","I studied ___ two hours.","for",listOf("since","for","during","within"),"দুই ঘণ্টা পড়াশোনা।","পরিমাণ = for।","time"),
    PracticeItem("p11","She went ___ school.","to",listOf("to","at","in","into"),"স্কুলে গেল।","গন্তব্য = to।","movement"),
    PracticeItem("p12","She walked ___ the park.","through",listOf("across","through","along","over"),"পার্কের মধ্য দিয়ে।","মধ্য দিয়ে = through।","movement"),
    PracticeItem("p13","He ran ___ the road.","across",listOf("through","along","across","over"),"রাস্তা পার হলো।","পার হয়ে = across।","movement"),
    PracticeItem("p14","She wrote ___ a pen.","with",listOf("by","with","using","through"),"কলম দিয়ে লিখল।","যন্ত্র = with।","manner"),
    PracticeItem("p15","She traveled ___ train.","by",listOf("with","by","on","in"),"ট্রেনে ভ্রমণ।","পরিবহন = by।","manner"),
    PracticeItem("p16","The match was canceled ___ rain.","because of",listOf("due to","because of","for","by"),"বৃষ্টির কারণে বাতিল।","কারণ = because of।","cause"),
    PracticeItem("p17","The book was written ___ Tagore.","by",listOf("with","by","from","of"),"রবীন্দ্রনাথ লিখেছিলেন।","Passive কর্তা = by।","agent"),
    PracticeItem("p18","The capital ___ Bangladesh is Dhaka.","of",listOf("of","for","in","at"),"বাংলাদেশের রাজধানী।","মালিকানা = of।","possession"),
    PracticeItem("p19","She succeeded ___ difficulties.","in spite of",listOf("in spite of","despite of","because of","in case of"),"কষ্ট সত্ত্বেও সফল।","সত্ত্বেও = in spite of।","compound"),
    PracticeItem("p20","She drank juice ___ tea.","instead of",listOf("instead of","in place","rather than","without"),"চায়ের পরিবর্তে জুস।","পরিবর্তে = instead of।","compound"),
    PracticeItem("p21","Don't talk ___ the exam.","during",listOf("during","for","while","in"),"পরীক্ষার সময়।","সময়কালে = during।","time"),
    PracticeItem("p22","She climbed ___ the mountain.","up",listOf("up","on","over","onto"),"পাহাড়ে উঠল।","উপরের দিকে = up।","movement"),
    PracticeItem("p23","She sings ___ a nightingale.","like",listOf("as","like","with","by"),"বুলবুলির মতো।","তুলনা = like।","manner"),
    PracticeItem("p24","He is suffering ___ fever.","from",listOf("of","with","from","by"),"জ্বরে ভুগছে।","suffer from।","cause"),
    PracticeItem("p25","___ the report, it will rain.","according to",listOf("According to","Due to","In case of","By means of"),"রিপোর্ট অনুযায়ী।","অনুযায়ী = according to।","compound"),
)

val allStories = listOf(
    StoryItem(
        id = "story1",
        title = "A Day at the Park",
        content = """
Yesterday, I went [to] the park [near] my house. I arrived [at] 9 AM and stayed [until] noon. 

I sat [on] a bench [beside] the fountain. Children were playing [around] the park. Some were running [across] the grass. Others were climbing [up] the trees. A little girl was hiding [behind] a big tree.

I had my lunch [at] 12 PM. I ate a sandwich made [of] vegetables. I drank juice [from] a bottle I had brought [with] me.

[After] lunch, I walked [along] the river bank. The river flows [through] the city. I could see fish swimming [below] the surface of the water.

I left the park [at] 1 PM and went home. I slept [for] two hours [during] the afternoon. It was a wonderful day!
        """.trimIndent(),
        prepositions = listOf("to","near","at","until","on","beside","around","across","up","behind","at","of","from","with","after","along","through","below","at","for","during"),
        questions = listOf(
            StoryQuestion("When did the narrator arrive at the park?","at 9 AM",listOf("at 8 AM","at 9 AM","in the morning","at noon")),
            StoryQuestion("Where was the little girl hiding?","behind a big tree",listOf("under a tree","behind a big tree","beside the fountain","in the park")),
            StoryQuestion("How long did the narrator sleep?","for two hours",listOf("for one hour","for two hours","during an hour","until evening")),
        )
    ),
    StoryItem(
        id = "story2",
        title = "The Lost Key",
        content = """
Maria looked [for] her key everywhere. She searched [under] the pillow and [behind] the curtains. She looked [inside] every drawer and [on] every shelf.

[After] ten minutes, she found it [in] her jacket pocket. The jacket was hanging [on] a hook [beside] the door. She had left it there [before] going [to] bed.

[With] the key [in] her hand, she locked the door [from] inside and went [out of] the house [through] the back door. She walked [along] the street [toward] the bus station.

The bus arrived [at] 8:30 AM. She got [on] the bus and sat [next to] the window. She reached the office [within] fifteen minutes.

Her boss was standing [in front of] the reception desk [with] some files [in] his hand. Maria said, "Good morning!" [according to] her usual habit, and went [to] her seat.
        """.trimIndent(),
        prepositions = listOf("for","under","behind","inside","on","after","in","on","beside","before","to","with","in","from","out of","through","along","toward","at","on","next to","within","in front of","with","in","according to","to"),
        questions = listOf(
            StoryQuestion("Where did Maria find her key?","in her jacket pocket",listOf("under the pillow","behind the curtains","in her jacket pocket","on the shelf")),
            StoryQuestion("How did Maria reach the office?","within fifteen minutes",listOf("within ten minutes","within fifteen minutes","in thirty minutes","by noon")),
            StoryQuestion("Where was the boss standing?","in front of the reception desk",listOf("beside the door","behind the desk","in front of the reception desk","at the office")),
        )
    ),
)
