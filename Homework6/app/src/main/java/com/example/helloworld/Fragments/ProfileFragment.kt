package com.example.helloworld.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helloworld.WelcomeScreenActivity
import com.example.helloworld.R
import com.example.helloworld.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changePassword.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                val profileFragment = requireActivity().supportFragmentManager.findFragmentByTag("profile_fr")

                if (profileFragment != null)
                    hide(profileFragment)

                add(
                    R.id.fragmentContainer,
                    ProfileEditPasswordFragment(),
                    "edit_password_fr"
                )

                commit()
            }
        }

        binding.buttonLogout.setOnClickListener {
            val intent = Intent(requireActivity(), WelcomeScreenActivity::class.java)
            startActivity(intent)
        }
    }
}