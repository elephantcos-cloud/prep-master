package com.prepmaster.app.data.content

import com.prepmaster.app.data.model.*

val categoryPlace = PrepCategory(
    id = "place", title = "Place", titleBn = "অবস্থান",
    description = "কোথায় আছে তা বোঝাতে",
    color = 0xFF00C6FF,
    iconRes = "ic_prep_place",
    prepositions = listOf(
        PrepItem("in","in","এর মধ্যে / ভেতরে",
            "বন্ধ বা আবদ্ধ স্থানের ভেতরে থাকা বোঝায়।",
            "Subject + verb + in + noun",
            listOf(
                PrepExample("The cat is in the box.","বিড়ালটি বাক্সের ভেতরে আছে।","in"),
                PrepExample("She lives in Dhaka.","সে ঢাকায় বাস করে।","in"),
                PrepExample("The keys are in my pocket.","চাবিগুলো আমার পকেটে আছে।","in"),
                PrepExample("He is in the room.","সে ঘরের ভেতরে আছে।","in"),
            ), "শহর, দেশ, ঘর, গাড়ির ভেতরে = in ব্যবহার হয়।", "img_in", "place"),

        PrepItem("on","on","এর উপরে (স্পর্শ করে)",
            "কোনো কিছুর উপরিভাগে স্পর্শ করে থাকা বোঝায়।",
            "Subject + verb + on + noun",
            listOf(
                PrepExample("The book is on the table.","বইটি টেবিলের উপরে আছে।","on"),
                PrepExample("There is a fly on the wall.","দেয়ালে একটি মাছি আছে।","on"),
                PrepExample("She sat on the chair.","সে চেয়ারে বসল।","on"),
                PrepExample("The picture is on the wall.","ছবিটি দেয়ালে আছে।","on"),
            ), "কোনো surface এর সাথে স্পর্শ থাকলে on।", "img_on", "place"),

        PrepItem("at","at","এ / তে (নির্দিষ্ট বিন্দু)",
            "নির্দিষ্ট স্থান বা বিন্দু বোঝাতে ব্যবহার হয়।",
            "Subject + verb + at + noun",
            listOf(
                PrepExample("She is at the door.","সে দরজায় আছে।","at"),
                PrepExample("We met at the corner.","আমরা কোণায় দেখা করলাম।","at"),
                PrepExample("He is at school.","সে স্কুলে আছে।","at"),
                PrepExample("Wait at the bus stop.","বাস স্টপে অপেক্ষা করো।","at"),
            ), "নির্দিষ্ট বিন্দু, ঠিকানা, কার্যক্রমের স্থানে = at।", "img_at", "place"),

        PrepItem("under","under","নিচে",
            "কিছুর নিচে বা নিম্নে থাকা বোঝায়।",
            "Subject + verb + under + noun",
            listOf(
                PrepExample("The dog is under the table.","কুকুরটি টেবিলের নিচে আছে।","under"),
                PrepExample("She hid under the bed.","সে বিছানার নিচে লুকাল।","under"),
                PrepExample("The bridge is under repair.","সেতুটি মেরামতাধীন।","under"),
            ), "নিচে ও ঢাকা থাকলে 'under', শুধু নিচে থাকলে 'below'।", "img_under", "place"),

        PrepItem("over","over","উপর দিয়ে / উপরে",
            "কোনো কিছুর উপর দিয়ে বা উপরে (সরাসরি) থাকা বোঝায়।",
            "Subject + verb + over + noun",
            listOf(
                PrepExample("The plane flew over the city.","বিমানটি শহরের উপর দিয়ে উড়ল।","over"),
                PrepExample("The lamp hangs over the table.","বাতিটি টেবিলের উপরে ঝুলছে।","over"),
                PrepExample("She spread the blanket over him.","সে তার উপর কম্বল বিছাল।","over"),
            ), "'Over' = উপরে (ঢেকে দেওয়া বা সরাসরি উপরে)।", "img_over", "place"),

        PrepItem("above","above","উপরে (স্পর্শ ছাড়া)",
            "কিছুর উপরে কিন্তু স্পর্শ না করে।",
            "Subject + verb + above + noun",
            listOf(
                PrepExample("The birds fly above the clouds.","পাখিগুলো মেঘের উপরে উড়ে।","above"),
                PrepExample("The temperature is above 40°C.","তাপমাত্রা ৪০°C এর উপরে।","above"),
                PrepExample("Hang the picture above the fireplace.","ছবিটি চুল্লির উপরে টাঙাও।","above"),
            ), "'Above' = স্পর্শ না করে উপরে।", "img_above", "place"),

        PrepItem("below","below","নিচে (স্পর্শ ছাড়া)",
            "কিছুর নিচে কিন্তু স্পর্শ না করে।",
            "Subject + verb + below + noun",
            listOf(
                PrepExample("The fish swim below the surface.","মাছগুলো পানির নিচে সাঁতার কাটে।","below"),
                PrepExample("The temperature fell below zero.","তাপমাত্রা শূন্যের নিচে নামল।","below"),
            ), "'Below' = স্পর্শ না করে নিচে।", "img_below", "place"),

        PrepItem("beside","beside","পাশে",
            "কারো বা কোনো কিছুর পাশে থাকা বোঝায়।",
            "Subject + verb + beside + noun",
            listOf(
                PrepExample("She sat beside her friend.","সে তার বন্ধুর পাশে বসল।","beside"),
                PrepExample("The school is beside the park.","স্কুলটি পার্কের পাশে।","beside"),
            ), "'Beside' ও 'next to' একই অর্থে ব্যবহার হয়।", "img_beside", "place"),

        PrepItem("between","between","মাঝখানে (দুটির)",
            "দুটি বস্তু বা ব্যক্তির মধ্যে।",
            "Subject + verb + between + noun + and + noun",
            listOf(
                PrepExample("The park is between the school and the hospital.","পার্কটি স্কুল ও হাসপাতালের মাঝে।","between"),
                PrepExample("She sat between Ali and Rina.","সে আলী ও রিনার মাঝে বসল।","between"),
                PrepExample("Choose between tea and coffee.","চা ও কফির মধ্যে বেছে নাও।","between"),
            ), "দুটির মাঝে = between; তিন বা তার বেশির মাঝে = among।", "img_between", "place"),

        PrepItem("among","among","মাঝে (তিন বা বেশি)",
            "তিন বা তার বেশি বস্তু বা ব্যক্তির মধ্যে।",
            "Subject + verb + among + plural noun",
            listOf(
                PrepExample("She was among the best students.","সে সেরা ছাত্রদের মধ্যে ছিল।","among"),
                PrepExample("Distribute the gifts among the children.","শিশুদের মধ্যে উপহার বিতরণ করো।","among"),
            ), "তিন বা বেশির মাঝে = among।", "img_among", "place"),

        PrepItem("near","near","কাছে",
            "কারো বা কোনো কিছুর কাছাকাছি।",
            "Subject + verb + near + noun",
            listOf(
                PrepExample("The shop is near the station.","দোকানটি স্টেশনের কাছে।","near"),
                PrepExample("Don't stand near the edge.","কিনারার কাছে দাঁড়িও না।","near"),
            ), "'Near' = কাছে, 'far from' = দূরে।", "img_near", "place"),

        PrepItem("behind","behind","পেছনে",
            "কোনো কিছুর পিছনে বা পেছন দিকে।",
            "Subject + verb + behind + noun",
            listOf(
                PrepExample("The cat is hiding behind the sofa.","বিড়ালটি সোফার পেছনে লুকাচ্ছে।","behind"),
                PrepExample("She walked behind him.","সে তার পেছনে হাঁটল।","behind"),
            ), "'Behind' = পেছনে; 'in front of' = সামনে।", "img_behind", "place"),

        PrepItem("in front of","in front of","সামনে",
            "কোনো কিছুর সামনের দিকে।",
            "Subject + verb + in front of + noun",
            listOf(
                PrepExample("He stood in front of the class.","সে ক্লাসের সামনে দাঁড়াল।","in front of"),
                PrepExample("Park your car in front of the house.","গাড়িটি বাড়ির সামনে পার্ক করো।","in front of"),
            ), "'In front of' এর বিপরীত 'behind'।", "img_in_front_of", "place"),

        PrepItem("opposite","opposite","বিপরীত দিকে",
            "একদম মুখোমুখি বা বিপরীত দিকে।",
            "Noun + is + opposite + noun",
            listOf(
                PrepExample("The bank is opposite the post office.","ব্যাংকটি পোস্ট অফিসের বিপরীতে।","opposite"),
                PrepExample("She lives opposite our house.","সে আমাদের বাড়ির বিপরীতে থাকে।","opposite"),
            ), "'Opposite' = একদম মুখোমুখি।", "img_opposite", "place"),

        PrepItem("inside","inside","ভেতরে",
            "'In' এর জোরালো রূপ। ভেতরের দিক বোঝায়।",
            "Subject + verb + inside + noun",
            listOf(
                PrepExample("Come inside the house.","বাড়ির ভেতরে এসো।","inside"),
                PrepExample("There's nothing inside the bag.","ব্যাগের ভেতরে কিছু নেই।","inside"),
            ), "'Inside' = ভেতরের দিকে (জোর দিয়ে)।", "img_inside", "place"),

        PrepItem("outside","outside","বাইরে",
            "কোনো কিছুর বাইরের দিকে।",
            "Subject + verb + outside + noun",
            listOf(
                PrepExample("The children are playing outside the house.","শিশুরা বাড়ির বাইরে খেলছে।","outside"),
                PrepExample("Wait outside the room.","ঘরের বাইরে অপেক্ষা করো।","outside"),
            ), "'Outside' = বাইরে।", "img_outside", "place"),

        PrepItem("next to","next to","পাশে (একদম সংলগ্ন)",
            "'Beside' এর সমার্থক, একদম পাশে।",
            "Subject + verb + next to + noun",
            listOf(
                PrepExample("Sit next to me.","আমার পাশে বসো।","next to"),
                PrepExample("The library is next to the park.","লাইব্রেরি পার্কের পাশে।","next to"),
            ), "'Next to' = একদম পাশে।", "img_next_to", "place"),

        PrepItem("by","by","পাশে / কাছে",
            "কোনো কিছুর পাশে বা কাছাকাছি।",
            "Subject + verb + by + noun",
            listOf(
                PrepExample("She was sitting by the window.","সে জানালার পাশে বসেছিল।","by"),
                PrepExample("The house is by the river.","বাড়িটি নদীর পাশে।","by"),
            ), "Place এ 'by' = কাছে/পাশে।", "img_by", "place"),
    )
)
