package ru.fefu.homework2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ActivitiesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_activity, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)

        val adapter = ActivitiesPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Моя"
                1 -> "Пользователей"
                else -> null
            }
        }.attach()

        return view
    }
}

class ActivitiesPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MyActivitiesFragment()
            1 -> UsersActivitiesFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }

}

class MyActivitiesFragment : Fragment() {
    private lateinit var adapter: ActivitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_activities_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        adapter = ActivitiesAdapter { activity ->
            val intent = Intent(requireContext(), ActivityDetailActivity::class.java).apply {
                putExtra("ACTIVITY_ID", activity.id)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadMyActivities()

        return view
    }

    private fun loadMyActivities() {
        val items = mutableListOf<ActivityItem>()

        items.add(ActivityItem.DateHeader("Вчера"))
        items.add(ActivityItem.Activity(
            1, "14.32 км", "2 часа 46 минут", "Серфинг", "14 часов назад", ActivityType.SURFING
        ))

        items.add(ActivityItem.DateHeader("Май 2022 года"))
        items.add(ActivityItem.Activity(
            2, "1000 м", "60 минут", "Велосипед", "29.05.2022", ActivityType.BIKING
        ))

        adapter.submitList(items)
    }
}

class UsersActivitiesFragment : Fragment() {
    private lateinit var adapter: ActivitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_activities_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        adapter = ActivitiesAdapter { activity ->
            val intent = Intent(requireContext(), ActivityDetailActivity::class.java).apply {
                putExtra("ACTIVITY_ID", activity.id)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadUsersActivities()

        return view
    }

    private fun loadUsersActivities() {
        val items = mutableListOf<ActivityItem>()

        items.add(ActivityItem.DateHeader("Вчера"))
        items.add(ActivityItem.Activity(
            3, "14.32 км", "2 часа 46 минут", "Серфинг", "14 часов назад", ActivityType.SURFING
        ))
        items.add(ActivityItem.Activity(
            4, "228 м", "14 часов 48 минут", "Качели", "14 часов назад", ActivityType.SWING
        ))
        items.add(ActivityItem.Activity(
            5, "10 км", "1 час 10 минут", "Езда на кадилак", "14 часов назад", ActivityType.OTHER
        ))

        adapter.submitList(items)
    }
}