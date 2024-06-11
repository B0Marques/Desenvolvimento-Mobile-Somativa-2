package com.example.myquiz

class DicionarioTemas {
    companion object {
        private val map: Map<Int, String> = mapOf(
            9 to "General Knowledge",
            10 to "Entertainment: Books",
            11 to "Entertainment: Film",
            12 to "Entertainment: Music",
            13 to "Entertainment: Musicals & Theatres",
            14 to "Entertainment: Television",
            15 to "Entertainment: Video Games",
            16 to "Entertainment: Board Games",
            17 to "Science & Nature",
            18 to "Science: Computers",
            19 to "Science: Mathematics",
            20 to "Mythology",
            21 to "Sports",
            22 to "Geography",
            23 to "History",
            24 to "Politics",
            25 to "Art",
            26 to "Celebrities",
            27 to "Animals",
            28 to "Vehicles",
            29 to "Entertainment: Comics",
            30 to "Science: Gadgets",
            31 to "Entertainment: Japanese Anime & Manga",
            32 to "Entertainment: Cartoon & Animations"

        )

        val icons:Map<Int,Int> = mapOf(
            9 to R.drawable.generalknowledge,
            10 to R.drawable.book,
            11 to R.drawable.movie,
            12 to R.drawable.music,
            13 to R.drawable.musical_theatre,
            14 to R.drawable.television,
            15 to R.drawable.videogame,
            16 to R.drawable.boardgame,
            17 to R.drawable.nature,
            18 to R.drawable.computer,
            19 to R.drawable.mathematics,
            20 to R.drawable.mythology,
            21 to R.drawable.sports,
            22 to R.drawable.geography,
            23 to R.drawable.history,
            24 to R.drawable.politics,
            25 to R.drawable.art,
            26 to R.drawable.celebrities,
            27 to R.drawable.animals,
            28 to R.drawable.vehicles,
            29 to R.drawable.comics,
            30 to R.drawable.gadgets,
            31 to R.drawable.anime,
            32 to R.drawable.cartoon
        )

        fun GetThemes():List<Int>{
            return map.keys.toList()
        }

        fun GetThemeById(themeID:Int):String?{
            return map.get(themeID)
        }
        fun GetThemeIcon(themeID:Int):Int?{
            return icons.get(themeID)
        }
    }


}