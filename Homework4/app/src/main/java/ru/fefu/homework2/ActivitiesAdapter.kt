package ru.fefu.homework2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActivitiesAdapter(
    private val onItemClick: (ActivityItem.Activity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ActivityItem>()

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ACTIVITY = 1
    }

    fun submitList(newItems: List<ActivityItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ActivityItem.DateHeader -> TYPE_HEADER
            is ActivityItem.Activity -> TYPE_ACTIVITY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> DateHeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_date_header, parent, false)
            )
            TYPE_ACTIVITY -> ActivityViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_activity, parent, false),
                onItemClick
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ActivityItem.DateHeader -> (holder as DateHeaderViewHolder).bind(item)
            is ActivityItem.Activity -> (holder as ActivityViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class DateHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateText: TextView = view.findViewById(R.id.dateText)

        fun bind(item: ActivityItem.DateHeader) {
            dateText.text = item.date
        }
    }

    class ActivityViewHolder(
        view: View,
        private val onItemClick: (ActivityItem.Activity) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val titleText: TextView = view.findViewById(R.id.titleText)
        private val distanceText: TextView = view.findViewById(R.id.distanceText)
        private val durationText: TextView = view.findViewById(R.id.durationText)
        private val timeAgoText: TextView = view.findViewById(R.id.timeAgoText)

        fun bind(item: ActivityItem.Activity) {
            titleText.text = item.title
            distanceText.text = item.distance
            durationText.text = item.duration
            timeAgoText.text = item.timeAgo

            itemView.setOnClickListener { onItemClick(item) }
        }
    }
}