package com.example.mini7thumcapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mini7thumcapplication.Alarm.AlarmFragment
import com.example.mini7thumcapplication.Detail.DetailFragment
import com.example.mini7thumcapplication.MyPage.MyPageFragment
import com.example.mini7thumcapplication.Review.ReviewFragment
import com.example.mini7thumcapplication.databinding.ActivityMainBinding
import com.example.mini7thumcapplication.main.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // ViewBinding 변수 선언
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BottomNavigationView 참조 및 동작 설정
        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation

        // 처음 앱 실행 시 HomeFragment 표시
        if (savedInstanceState == null) {
            loadFragment(MainFragment())
        }

        // 바텀 네비게이션
        bottomNavigationView.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.navigation_home -> MainFragment()
                R.id.navigation_detail -> DetailFragment()
                R.id.navigation_review -> ReviewFragment()
                R.id.navigation_alarm -> AlarmFragment()
                R.id.navigation_mypage -> MyPageFragment()
                else -> MainFragment()
            }
            loadFragment(selectedFragment)
            true
        }
    }

    // 프래그먼트 로드
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
