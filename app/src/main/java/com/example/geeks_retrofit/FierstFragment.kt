package com.example.geeks_retrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.geeks_retrofit.databinding.FragmentFierstBinding
import com.example.geeks_retrofit.databinding.FragmentSecondBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class FierstFragment : Fragment() {

    private lateinit var binding: FragmentFierstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFierstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage()
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            btnGetLove.setOnClickListener {
                RetrofitService.api.getLove(
                    etFirstName.text.toString(),
                    etSecondName.text.toString()
                )
                    .enqueue(object : Callback<LoveModel> {
                        override fun onResponse(
                            call: Call<LoveModel>,
                            response: Response<LoveModel>
                        ) {
                            if (response.isSuccessful) {
                                val fname = response.body()?.fname
                                val sname = response.body()?.sname
                                val persantage = response.body()?.percentage
                                val result = response.body()?.result
                                val secondFragment = SecondFragment()
                                val bundle = Bundle()

                                bundle.putString("fname", fname.toString())
                                bundle.putString("sname", sname.toString())
                                bundle.putString("persantage", persantage.toString())
                                bundle.putString("result", result.toString())

                                secondFragment.arguments = bundle
                                requireActivity().supportFragmentManager.beginTransaction()
                                    .replace(R.id.container, secondFragment).commit()
                            }
                        }

                        override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                            Log.e("ololo", "onFailure:${t.message}")
                        }

                    })
            }
        }
    }

    private fun loadImage() {
        Glide.with(this)
            .load("https://elbrus-decor.ru/wp-content/uploads/2022/06/serdechko-iz-ruk-jpg.webp")
            .into(binding.img)
    }


}