package com.prepmaster.app.data.content

import com.prepmaster.app.data.model.*

val categoryManner = PrepCategory(
    id = "manner", title = "Manner", titleBn = "ধরন ও পদ্ধতি",
    description = "কীভাবে করা হয় তা বোঝাতে",
    color = 0xFFFFD700, iconRes = "ic_prep_manner",
    prepositions = listOf(
        PrepItem("with","with","সহ / দিয়ে",
            "কোনো কিছু ব্যবহার করে বা কারো সাথে।",
            "Subject + verb + with + noun",
            listOf(
                PrepExample("She wrote with a pen.","সে কলম দিয়ে লিখল।","with"),
                PrepExample("He came with his friend.","সে তার বন্ধুর সাথে এল।","with"),
                PrepExample("She spoke with confidence.","সে আত্মবিশ্বাসের সাথে কথা বলল।","with"),
            ), "instrument বা accompaniment।", "img_with", "manner"),

        PrepItem("by_manner","by","দ্বারা / মাধ্যমে",
            "কোনো উপায় বা মাধ্যমে কাজ করা।",
            "Subject + verb + by + noun",
            listOf(
                PrepExample("She traveled by train.","সে ট্রেনে ভ্রমণ করল।","by"),
                PrepExample("He learned by practicing.","সে অনুশীলনের মাধ্যমে শিখল।","by"),
            ), "পরিবহন/পদ্ধতি।", "img_by_manner", "manner"),

        PrepItem("like","like","মতো / সদৃশ",
            "তুলনা করতে বা মিল বোঝাতে।",
            "Subject + verb + like + noun",
            listOf(
                PrepExample("She sings like a nightingale.","সে বুলবুলির মতো গায়।","like"),
                PrepExample("He runs like the wind.","সে বাতাসের মতো দৌড়ায়।","like"),
            ), "তুলনা বোঝাতে।", "img_like", "manner"),

        PrepItem("as","as","হিসেবে / রূপে",
            "ভূমিকা বা পরিচয় বোঝাতে।",
            "Subject + works + as + noun",
            listOf(
                PrepExample("She works as a doctor.","সে ডাক্তার হিসেবে কাজ করে।","as"),
                PrepExample("He acted as a leader.","সে নেতা হিসেবে কাজ করল।","as"),
            ), "পরিচয় বা ভূমিকা।", "img_as", "manner"),

        PrepItem("without","without","ছাড়া",
            "কোনো কিছুর অভাবে বা ছাড়া।",
            "Subject + verb + without + noun/gerund",
            listOf(
                PrepExample("She left without saying goodbye.","সে বিদায় না নিয়ে চলে গেল।","without"),
                PrepExample("Don't go without permission.","অনুমতি ছাড়া যেও না।","without"),
            ), "ছাড়া / ব্যতিরেকে।", "img_without", "manner"),
    )
)

val categoryCause = PrepCategory(
    id = "cause", title = "Cause & Reason", titleBn = "কারণ ও উদ্দেশ্য",
    description = "কেন হয়েছে তা বোঝাতে",
    color = 0xFFFF9800, iconRes = "ic_prep_cause",
    prepositions = listOf(
        PrepItem("because_of","because of","কারণে",
            "কোনো কারণের ফলে ঘটা।",
            "Subject + verb + because of + noun",
            listOf(
                PrepExample("The match was canceled because of rain.","বৃষ্টির কারণে খেলা বাতিল হলো।","because of"),
                PrepExample("She cried because of the pain.","ব্যথার কারণে সে কাঁদল।","because of"),
            ), "noun এর আগে কারণে।", "img_because_of", "cause"),

        PrepItem("due_to","due to","কারণে (formal)",
            "কোনো কারণের ফলে — formal ব্যবহারে।",
            "Subject + verb + due to + noun",
            listOf(
                PrepExample("The flight was delayed due to fog.","কুয়াশার কারণে ফ্লাইট বিলম্বিত হলো।","due to"),
                PrepExample("He failed due to laziness.","অলসতার কারণে সে ফেল করল।","due to"),
            ), "formal writing এ।", "img_due_to", "cause"),

        PrepItem("for_reason","for","জন্য / কারণে",
            "কারণ বা উদ্দেশ্য বোঝাতে।",
            "Subject + verb + for + noun",
            listOf(
                PrepExample("She is famous for her singing.","সে তার গানের জন্য বিখ্যাত।","for"),
                PrepExample("He was punished for lying.","মিথ্যার জন্য শাস্তি পেল।","for"),
                PrepExample("Thank you for your help.","সাহায্যের জন্য ধন্যবাদ।","for"),
            ), "কারণ বা উদ্দেশ্য।", "img_for_reason", "cause"),

        PrepItem("out_of_reason","out of","থেকে (অনুভূতি)",
            "আবেগ বা অনুভূতি থেকে সৃষ্ট কারণ।",
            "Subject + verb + out of + emotion",
            listOf(
                PrepExample("She did it out of kindness.","সে দয়া থেকে এটি করল।","out of"),
                PrepExample("He acted out of fear.","সে ভয় থেকে কাজটি করল।","out of"),
            ), "আবেগ থেকে উৎপন্ন।", "img_out_of_reason", "cause"),

        PrepItem("from_cause","from","থেকে (কারণ)",
            "কারণ বা উৎস থেকে ঘটা।",
            "Subject + suffers/dies + from + noun",
            listOf(
                PrepExample("She is suffering from fever.","সে জ্বরে ভুগছে।","from"),
                PrepExample("He died from a snake bite.","সে সাপের কামড়ে মারা গেল।","from"),
            ), "suffer from, die from।", "img_from_cause", "cause"),
    )
)

val categoryAgent = PrepCategory(
    id = "agent", title = "Agent & Instrument", titleBn = "কর্তা ও যন্ত্র",
    description = "কে বা কী দিয়ে করা হয়েছে",
    color = 0xFFB06AFF, iconRes = "ic_prep_agent",
    prepositions = listOf(
        PrepItem("by_agent","by","দ্বারা (passive)",
            "Passive voice এ কর্তা বোঝাতে।",
            "Object + was + verb + by + agent",
            listOf(
                PrepExample("The book was written by Tagore.","বইটি রবীন্দ্রনাথ রচনা করেছিলেন।","by"),
                PrepExample("The house was built by workers.","বাড়িটি শ্রমিকরা নির্মাণ করেছিল।","by"),
            ), "Passive: কর্তা = by।", "img_by_agent", "agent"),

        PrepItem("with_instrument","with","দিয়ে (যন্ত্র)",
            "বস্তু বা যন্ত্র ব্যবহার করে কাজ করা।",
            "Object + was + verb + with + instrument",
            listOf(
                PrepExample("It was written with a pen.","এটি কলম দিয়ে লেখা হয়েছিল।","with"),
                PrepExample("She cut it with a knife.","সে ছুরি দিয়ে কাটল।","with"),
            ), "বস্তু/যন্ত্র = with।", "img_with_instrument", "agent"),

        PrepItem("on_device","on","দিয়ে (ডিভাইস)",
            "প্রযুক্তি বা ডিভাইস ব্যবহার বোঝাতে।",
            "Subject + verb + on + device",
            listOf(
                PrepExample("She talked on the phone.","সে ফোনে কথা বলল।","on"),
                PrepExample("He watched a movie on TV.","সে টিভিতে সিনেমা দেখল।","on"),
            ), "ডিভাইস = on।", "img_on_device", "agent"),
    )
)

val categoryPossession = PrepCategory(
    id = "possession", title = "Possession", titleBn = "মালিকানা",
    description = "কার সেটা বোঝাতে",
    color = 0xFF7C5CFC, iconRes = "ic_prep_poss",
    prepositions = listOf(
        PrepItem("of","of","এর (মালিকানা)",
            "মালিকানা, অংশ বা সম্পর্ক বোঝাতে।",
            "Noun + of + noun",
            listOf(
                PrepExample("The cover of the book is red.","বইয়ের কভার লাল।","of"),
                PrepExample("She is the sister of Ali.","সে আলীর বোন।","of"),
                PrepExample("The capital of Bangladesh is Dhaka.","বাংলাদেশের রাজধানী ঢাকা।","of"),
                PrepExample("A cup of tea.","এক কাপ চা।","of"),
            ), "'Of' = মালিকানা বা সম্পর্ক।", "img_of", "possession"),

        PrepItem("to_possession","to","প্রতি / সাথে সম্পর্ক",
            "সম্পর্ক বা সংযোগ বোঝাতে।",
            "Noun/adj + to + noun",
            listOf(
                PrepExample("She is important to me.","সে আমার কাছে গুরুত্বপূর্ণ।","to"),
                PrepExample("He is kind to everyone.","সে সবার প্রতি সদয়।","to"),
            ), "সম্পর্ক বোঝাতে।", "img_to_poss", "possession"),
    )
)

val categorySource = PrepCategory(
    id = "source", title = "Source & Origin", titleBn = "উৎস ও উৎপত্তি",
    description = "কোথা থেকে এসেছে বোঝাতে",
    color = 0xFFE91E63, iconRes = "ic_prep_source",
    prepositions = listOf(
        PrepItem("from_origin","from","থেকে (উৎস)",
            "কোথা থেকে এসেছে বা উৎপত্তি।",
            "Subject + comes/is + from + origin",
            listOf(
                PrepExample("She is from Bangladesh.","সে বাংলাদেশ থেকে।","from"),
                PrepExample("This honey is from the hills.","এই মধু পাহাড় থেকে।","from"),
                PrepExample("He learned it from his teacher.","সে তার শিক্ষকের কাছ থেকে শিখল।","from"),
            ), "উৎস বোঝাতে।", "img_from_origin", "source"),

        PrepItem("out_of_origin","out of","থেকে বের হয়ে (উৎস)",
            "কোনো উৎস থেকে বের হওয়া।",
            "Subject + comes + out of + noun",
            listOf(
                PrepExample("Water comes out of the tap.","কল থেকে পানি আসে।","out of"),
                PrepExample("She grew out of her shyness.","সে লজ্জা কাটিয়ে উঠল।","out of"),
            ), "উৎস থেকে বের হওয়া।", "img_out_of_origin", "source"),

        PrepItem("of_origin","of","এর (উৎস/তৈরি)",
            "তৈরির উপাদান বা উৎপত্তি।",
            "Made/built + of + material",
            listOf(
                PrepExample("The table is made of wood.","টেবিলটি কাঠ দিয়ে তৈরি।","of"),
                PrepExample("The ring is made of gold.","আংটিটি সোনা দিয়ে তৈরি।","of"),
            ), "উপাদান/উৎস।", "img_of_origin", "source"),
    )
)

val categoryCompound = PrepCategory(
    id = "compound", title = "Compound Prepositions", titleBn = "যৌগিক Preposition",
    description = "দুই বা তিন শব্দের preposition",
    color = 0xFF00BCD4, iconRes = "ic_prep_compound",
    prepositions = listOf(
        PrepItem("in_front_of","in front of","সামনে",
            "কোনো কিছুর সামনের দিকে।",
            "Noun + is + in front of + noun",
            listOf(
                PrepExample("She stood in front of the class.","সে ক্লাসের সামনে দাঁড়াল।","in front of"),
                PrepExample("Park in front of the building.","বিল্ডিংয়ের সামনে পার্ক করো।","in front of"),
            ), "সামনে।", "img_in_front_of", "compound"),

        PrepItem("in_spite_of","in spite of","সত্ত্বেও",
            "বাধা থাকা সত্ত্বেও কাজ করা।",
            "In spite of + noun/gerund, Subject + verb",
            listOf(
                PrepExample("She succeeded in spite of difficulties.","কষ্ট সত্ত্বেও সে সফল হলো।","in spite of"),
                PrepExample("He went out in spite of the rain.","বৃষ্টি সত্ত্বেও সে বাইরে গেল।","in spite of"),
            ), "সত্ত্বেও (= despite)।", "img_in_spite_of", "compound"),

        PrepItem("instead_of","instead of","পরিবর্তে",
            "কোনো কিছুর বদলে বা পরিবর্তে।",
            "Instead of + noun/gerund, Subject + verb",
            listOf(
                PrepExample("She drank juice instead of tea.","সে চায়ের পরিবর্তে জুস পান করল।","instead of"),
                PrepExample("Go by bus instead of walking.","হাঁটার পরিবর্তে বাসে যাও।","instead of"),
            ), "পরিবর্তে।", "img_instead_of", "compound"),

        PrepItem("on_behalf_of","on behalf of","পক্ষ থেকে",
            "কারো হয়ে বা প্রতিনিধিত্ব করে।",
            "Subject + speaks/acts + on behalf of + noun",
            listOf(
                PrepExample("He spoke on behalf of the team.","সে দলের পক্ষে কথা বলল।","on behalf of"),
                PrepExample("I accept the award on behalf of our company.","আমি কোম্পানির পক্ষ থেকে পুরস্কার গ্রহণ করছি।","on behalf of"),
            ), "কারো হয়ে।", "img_on_behalf_of", "compound"),

        PrepItem("in_addition_to","in addition to","অতিরিক্ত / ছাড়াও",
            "কোনো কিছুর সাথে আরো যোগ করে।",
            "In addition to + noun, Subject + verb",
            listOf(
                PrepExample("In addition to English, she knows French.","ইংরেজি ছাড়াও সে ফরাসি জানে।","in addition to"),
                PrepExample("He gave advice in addition to money.","টাকার পাশাপাশি সে পরামর্শও দিল।","in addition to"),
            ), "ছাড়াও / অতিরিক্ত।", "img_in_addition_to", "compound"),

        PrepItem("according_to","according to","অনুযায়ী",
            "কোনো তথ্য বা নিয়ম অনুসরণ করে।",
            "According to + noun, Subject + verb",
            listOf(
                PrepExample("According to the report, it will rain.","রিপোর্ট অনুযায়ী, বৃষ্টি হবে।","according to"),
                PrepExample("Act according to the rules.","নিয়ম অনুযায়ী কাজ করো।","according to"),
            ), "অনুযায়ী।", "img_according_to", "compound"),

        PrepItem("with_regard_to","with regard to","সম্পর্কে",
            "কোনো বিষয়ে বা সম্পর্কে — formal।",
            "With regard to + noun, Subject + verb",
            listOf(
                PrepExample("With regard to your application, we need more documents.","আপনার আবেদন সম্পর্কে, আমাদের আরো নথি দরকার।","with regard to"),
            ), "formal: সম্পর্কে।", "img_with_regard_to", "compound"),

        PrepItem("by_means_of","by means of","মাধ্যমে",
            "কোনো উপায় বা পদ্ধতির মাধ্যমে।",
            "Subject + verb + by means of + noun",
            listOf(
                PrepExample("She communicated by means of sign language.","সে সাংকেতিক ভাষার মাধ্যমে যোগাযোগ করল।","by means of"),
                PrepExample("He escaped by means of a rope.","সে দড়ির মাধ্যমে পালাল।","by means of"),
            ), "মাধ্যমে।", "img_by_means_of", "compound"),

        PrepItem("in_case_of","in case of","ক্ষেত্রে / হলে",
            "কোনো পরিস্থিতিতে বা ক্ষেত্রে।",
            "In case of + noun, Subject + verb",
            listOf(
                PrepExample("In case of fire, use the stairs.","আগুনের ক্ষেত্রে সিঁড়ি ব্যবহার করো।","in case of"),
                PrepExample("In case of emergency, call 999.","জরুরি অবস্থায় ৯৯৯ এ কল করো।","in case of"),
            ), "পরিস্থিতিতে।", "img_in_case_of", "compound"),

        PrepItem("on_top_of","on top of","উপরে",
            "কোনো কিছুর একদম উপরে।",
            "Noun + is + on top of + noun",
            listOf(
                PrepExample("The book is on top of the box.","বইটি বাক্সের উপরে।","on top of"),
                PrepExample("She put the vase on top of the shelf.","সে ফুলদানিটি তাকের উপরে রাখল।","on top of"),
            ), "একদম উপরে।", "img_on_top_of", "compound"),

        PrepItem("in_comparison_with","in comparison with","তুলনায়",
            "তুলনামূলক আলোচনায়।",
            "In comparison with + noun, Subject + verb",
            listOf(
                PrepExample("In comparison with last year, sales improved.","গত বছরের তুলনায় বিক্রি বাড়ল।","in comparison with"),
            ), "তুলনায়।", "img_in_comparison_with", "compound"),

        PrepItem("in_spite_despite","despite","সত্ত্বেও",
            "বাধা থাকা সত্ত্বেও — 'in spite of' এর প্রতিশব্দ।",
            "Despite + noun/gerund, Subject + verb",
            listOf(
                PrepExample("Despite being tired, she kept working.","ক্লান্ত থাকা সত্ত্বেও সে কাজ করতে থাকল।","despite"),
                PrepExample("Despite the rain, they played cricket.","বৃষ্টি সত্ত্বেও তারা ক্রিকেট খেলল।","despite"),
            ), "= in spite of।", "img_despite", "compound"),
    )
)

val allCategories = listOf(
    categoryPlace, categoryTime, categoryMovement,
    categoryManner, categoryCause, categoryAgent,
    categoryPossession, categorySource, categoryCompound
)
