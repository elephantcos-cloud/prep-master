package com.prepmaster.app.data.content

import com.prepmaster.app.data.model.*

val categoryMovement = PrepCategory(
    id = "movement", title = "Movement", titleBn = "দিক ও গতি",
    description = "কোন দিকে যাচ্ছে তা বোঝাতে",
    color = 0xFF00E676, iconRes = "ic_prep_move",
    prepositions = listOf(
        PrepItem("to","to","দিকে / প্রতি",
            "কোনো গন্তব্যের দিকে যাওয়া।",
            "go/come/travel + to + destination",
            listOf(
                PrepExample("She went to school.","সে স্কুলে গেল।","to"),
                PrepExample("He is going to Dhaka.","সে ঢাকায় যাচ্ছে।","to"),
                PrepExample("Send this letter to him.","এই চিঠিটি তাকে পাঠাও।","to"),
            ), "Destination = to। Home, here, there এর আগে to লাগে না।", "img_to", "movement"),

        PrepItem("from","from","থেকে",
            "কোথা থেকে আসছে বা শুরু হচ্ছে।",
            "come/leave + from + origin",
            listOf(
                PrepExample("She came from Chittagong.","সে চট্টগ্রাম থেকে এল।","from"),
                PrepExample("The train leaves from platform 3.","ট্রেন ৩ নম্বর প্লাটফর্ম থেকে ছাড়ে।","from"),
            ), "'From' = উৎস বা যাত্রার শুরুর স্থান।", "img_from", "movement"),

        PrepItem("into","into","ভেতরে (প্রবেশ করে)",
            "কোনো স্থানের ভেতরে প্রবেশ করা।",
            "go/enter/walk + into + noun",
            listOf(
                PrepExample("She walked into the room.","সে ঘরে ঢুকল।","into"),
                PrepExample("He dived into the pool.","সে সুইমিং পুলে ঝাঁপ দিল।","into"),
                PrepExample("Pour the water into the glass.","পানি গ্লাসে ঢালো।","into"),
            ), "'Into' = ভেতরে প্রবেশ (movement); 'in' = ভেতরে অবস্থান।", "img_into", "movement"),

        PrepItem("out of","out of","থেকে বাইরে",
            "কোনো স্থানের ভেতর থেকে বাইরে বের হওয়া।",
            "come/go/get + out of + noun",
            listOf(
                PrepExample("She walked out of the room.","সে ঘর থেকে বের হলো।","out of"),
                PrepExample("He jumped out of the car.","সে গাড়ি থেকে লাফিয়ে নামল।","out of"),
            ), "'Out of' = ভেতর থেকে বাইরে।", "img_out_of", "movement"),

        PrepItem("through","through","ভেতর দিয়ে",
            "কোনো কিছুর মধ্য দিয়ে যাওয়া।",
            "pass/go/walk + through + noun",
            listOf(
                PrepExample("She walked through the park.","সে পার্কের মধ্য দিয়ে হাঁটল।","through"),
                PrepExample("The light shines through the window.","আলো জানালার মধ্য দিয়ে আসে।","through"),
                PrepExample("We drove through the tunnel.","আমরা টানেলের মধ্য দিয়ে গাড়ি চালালাম।","through"),
            ), "'Through' = মধ্য দিয়ে / ভেদ করে।", "img_through", "movement"),

        PrepItem("across","across","পার হয়ে / ওপারে",
            "কোনো কিছুর একদিক থেকে অন্যদিকে যাওয়া।",
            "go/swim/walk + across + noun",
            listOf(
                PrepExample("She swam across the river.","সে নদী পার হয়ে সাঁতার দিল।","across"),
                PrepExample("He ran across the road.","সে রাস্তা পার হয়ে দৌড়াল।","across"),
                PrepExample("There is a bridge across the river.","নদীর উপর একটি সেতু আছে।","across"),
            ), "'Across' = একপাশ থেকে অন্যপাশে।", "img_across", "movement"),

        PrepItem("along","along","বরাবর / ধরে",
            "কোনো কিছুর পাশ দিয়ে বা বরাবর যাওয়া।",
            "walk/drive/run + along + noun",
            listOf(
                PrepExample("We walked along the beach.","আমরা সমুদ্র সৈকত ধরে হাঁটলাম।","along"),
                PrepExample("Trees are planted along the road.","রাস্তার ধারে গাছ লাগানো।","along"),
            ), "'Along' = লম্বালম্বিভাবে পাশ দিয়ে।", "img_along", "movement"),

        PrepItem("around","around","চারপাশে / ঘিরে",
            "কোনো কিছুকে ঘিরে বা চারদিক দিয়ে।",
            "go/walk/run + around + noun",
            listOf(
                PrepExample("She ran around the track.","সে ট্র্যাকের চারপাশে দৌড়াল।","around"),
                PrepExample("We traveled around the world.","আমরা সারা বিশ্ব ঘুরলাম।","around"),
            ), "'Around' = চারদিক ঘিরে।", "img_around", "movement"),

        PrepItem("toward/towards","toward","দিকে (লক্ষ্য করে)",
            "কোনো দিকের দিকে যাওয়া।",
            "move/walk/run + toward + noun",
            listOf(
                PrepExample("She walked toward the door.","সে দরজার দিকে হাঁটল।","toward"),
                PrepExample("The train moved toward the station.","ট্রেন স্টেশনের দিকে এগোল।","toward"),
            ), "'Toward' = দিকে (গন্তব্য অনির্দিষ্ট)।", "img_toward", "movement"),

        PrepItem("up","up","উপরে (গতি)",
            "নিচ থেকে উপরের দিকে যাওয়া।",
            "go/climb/walk + up + noun",
            listOf(
                PrepExample("She climbed up the mountain.","সে পাহাড়ে উঠল।","up"),
                PrepExample("Walk up the stairs.","সিঁড়ি দিয়ে উপরে উঠো।","up"),
            ), "'Up' = উপরের দিকে।", "img_up", "movement"),

        PrepItem("down","down","নিচে (গতি)",
            "উপর থেকে নিচের দিকে যাওয়া।",
            "go/run/fall + down + noun",
            listOf(
                PrepExample("The ball rolled down the hill.","বলটি পাহাড় থেকে গড়িয়ে নামল।","down"),
                PrepExample("She walked down the stairs.","সে সিঁড়ি দিয়ে নামল।","down"),
            ), "'Down' = নিচের দিকে।", "img_down", "movement"),

        PrepItem("past","past","পার হয়ে / অতিক্রম করে",
            "কোনো স্থানের পাশ দিয়ে না থেমে চলে যাওয়া।",
            "go/walk/drive + past + noun",
            listOf(
                PrepExample("He drove past the school.","সে স্কুলের পাশ দিয়ে গাড়ি চালাল।","past"),
                PrepExample("She walked past me without speaking.","সে কথা না বলে আমার পাশ দিয়ে চলে গেল।","past"),
            ), "'Past' = পাশ দিয়ে অতিক্রম করা।", "img_past", "movement"),

        PrepItem("onto","onto","উপরে (উঠে)",
            "কোনো কিছুর উপরে উঠে যাওয়া।",
            "jump/climb/get + onto + noun",
            listOf(
                PrepExample("The cat jumped onto the table.","বিড়ালটি টেবিলে লাফিয়ে উঠল।","onto"),
                PrepExample("She got onto the bus.","সে বাসে উঠল।","onto"),
            ), "'Onto' = উপরে উঠে (movement); 'on' = উপরে অবস্থান।", "img_onto", "movement"),

        PrepItem("off","off","থেকে নামা",
            "কোনো কিছু থেকে সরে যাওয়া বা নামা।",
            "fall/get/jump + off + noun",
            listOf(
                PrepExample("She jumped off the wall.","সে দেয়াল থেকে লাফিয়ে নামল।","off"),
                PrepExample("Get off the bus here.","এখানে বাস থেকে নামো।","off"),
            ), "'Off' = উপর থেকে নামা/সরা।", "img_off", "movement"),
    )
)
