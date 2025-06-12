package ru.fefu.homework2

sealed class ActivityItem {
    data class DateHeader(val date: String) : ActivityItem()
    data class Activity(
        val id: Long,
        val title: String,
        val distance: String,
        val duration: String,
        val timeAgo: String,
        val type: ActivityType
    ) : ActivityItem()
}

enum class ActivityType {
    SURFING, BIKING, SWING, OTHER
}