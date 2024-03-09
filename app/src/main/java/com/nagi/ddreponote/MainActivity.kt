package com.nagi.ddreponote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nagi.ddreponote.adapter.ViewPagerAdapter
import com.nagi.ddreponote.databinding.ActivityMainBinding
import com.nagi.ddreponote.utils.DataUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        clearToolbarTitle()
        setupScrollViewOnScrollListener()
        setupViewPagerAndAdapter()
        setupTabLayoutWithViewPager()
        setupOnPageChangeCallback()
        setupTabSelectedListener()
    }

    private fun clearToolbarTitle() {
        binding.homeToolbar.title = ""
    }

    private fun setupScrollViewOnScrollListener() {
        binding.homeScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            if (binding.homeToolbar.height + binding.homeMainTitle.bottom <= scrollY + binding.homeToolbar.height) {
                binding.homeToolbar.title = binding.homeMainTitle.text
            } else {
                clearToolbarTitle()
            }
        })
    }

    private fun setupViewPagerAndAdapter() {
        binding.homeViewPager.adapter = ViewPagerAdapter(this)
    }

    private fun setupTabLayoutWithViewPager() {
        TabLayoutMediator(binding.homeTabLayout, binding.homeViewPager) { tab, position ->
            tab.text = getText(DataUtils.homePageTabText[position])
            tab.setIcon(DataUtils.homePageTabIcon[position])
        }.attach()
    }

    private fun setupOnPageChangeCallback() {
        binding.homeViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    binding.noteAdd.hide()
                }
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    binding.noteAdd.show()
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.homeTabLayout.selectTab(binding.homeTabLayout.getTabAt(position))
            }
        })
    }

    private fun setupTabSelectedListener() {
        binding.homeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.homeViewPager.currentItem = tab.position
                binding.noteAdd.hide()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}