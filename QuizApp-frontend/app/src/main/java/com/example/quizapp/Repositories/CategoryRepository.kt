package com.example.quizapp

data class Category(
    val name: String,
    val description: String,
    val imageResId: Int
)

object CategoryRepository {
    val categories = listOf(
        Category(
            name = "Science",
            description = "Explore the wonders of the universe! Science uncovers the secrets of the natural world, from microscopic organisms to distant galaxies.",
            imageResId = R.drawable.cat1
        ),
        Category(
            name = "History",
            description = "Travel back in time to relive significant events, remarkable figures, and civilizations that shaped our world.",
            imageResId = R.drawable.cat2
        ),
        Category(
            name = "Sport",
            description = "Dive into the world of athleticism and competition. Test your knowledge of iconic athletes, teams, and thrilling games.",
            imageResId = R.drawable.cat3
        ),
        Category(
            name = "Art",
            description = "Celebrate creativity and imagination. Discover the stories behind masterpieces, artists, and art movements across the ages.",
            imageResId = R.drawable.cat4
        ),
        Category(
            name = "Programming",
            description = "Unravel the logic behind coding and software. Learn about languages, algorithms, and the technology that powers our digital world.",
            imageResId = R.drawable.cat5
        ),
        Category(
            name = "Math",
            description = "Challenge your problem-solving skills! Delve into numbers, patterns, and equations that define the language of the universe.",
            imageResId = R.drawable.cat6
        )
    )
}

