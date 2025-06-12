package ru.fefu.homework2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        view.findViewById<Button>(R.id.changePasswordButton).setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()

            transaction.replace(R.id.fragment_container, ChangePasswordFragment())
            transaction.addToBackStack("profile_to_password")
            transaction.commit()
        }

        return view
    }
}