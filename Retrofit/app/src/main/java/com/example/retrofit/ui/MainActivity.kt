package com.example.retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.CompletionListener
import com.example.retrofit.Data
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.repository.BaeminRepository

class MainActivity : AppCompatActivity(), CompletionListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noticeAdapter: NoticeAdapter
    private var baeminRepository =  BaeminRepository()
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvBaeminNotice.apply {
            binding.rvBaeminNotice.layoutManager = LinearLayoutManager(context)
            noticeAdapter = NoticeAdapter()
            binding.rvBaeminNotice.adapter = noticeAdapter
        }

        baeminRepository.loadBaeminNotice(page, this) // 첫 페이지

        binding.btnLoadNextPage.setOnClickListener {
            baeminRepository.loadBaeminNotice(++page,this)
        }
    }

    // 성공적으로 로드한 경우
    override fun loadComplete(data: Data) {
        noticeAdapter.setList(data.content)
        noticeAdapter.notifyDataSetChanged()
    }

    // 응답에 문제가 있는 경우
    override fun responseIsNotSuccesful(code: Int) {
        Toast.makeText(this, "사이트가 응답하지 않습니다", Toast.LENGTH_SHORT).show()
        Log.v("로그", code.toString())
    }

    // 로드에 실패한 경우
    override fun loadFail() {
        Toast.makeText(this, "인터넷 연결을 확인하세요", Toast.LENGTH_SHORT).show()
    }
}