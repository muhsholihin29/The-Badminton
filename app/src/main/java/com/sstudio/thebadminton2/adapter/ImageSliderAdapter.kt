package com.sstudio.thebadminton2.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.github.chrisbanes.photoview.PhotoView

class ImageSliderAdapter(private val sDrawables: IntArray): PagerAdapter() {
        override fun getCount(): Int {
            return sDrawables.size
        }

        override fun instantiateItem(
            container: ViewGroup,
            position: Int
        ): View {
            val photoView = PhotoView(container.context)
            photoView.setImageResource(sDrawables[position])
            // Now just add PhotoView to ViewPager and return it
            container.addView(
                photoView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            return photoView
        }

        override fun destroyItem(
            container: ViewGroup,
            position: Int,
            `object`: Any
        ) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(
            view: View,
            `object`: Any
        ): Boolean {
            return view === `object`
        }
}