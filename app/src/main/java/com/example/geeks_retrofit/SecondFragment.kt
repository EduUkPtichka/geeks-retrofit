package com.example.geeks_retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.geeks_retrofit.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load("https://elbrus-decor.ru/wp-content/uploads/2022/06/serdechko-iz-ruk-jpg.webp")
            .into(binding.imgSf)

        val result = arguments?.getString("result")
        val fname = arguments?.getString("fname")
        val sname = arguments?.getString("sname")
        val persantage = arguments?.getString("persantage")

        with(binding) {
            tvFirstName.text = fname.toString()
            tvSecondName.text = sname.toString()
            tvPersantage.text = persantage.toString()
            tvResult.text = result.toString()

            btnTryAgain.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, FierstFragment()).commit()
            }
        }
    }
}