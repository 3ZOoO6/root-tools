package com.rarnu.tools.neo.comp

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.rarnu.android.dip2px
import com.rarnu.tools.neo.R

class LoadingView : LinearLayout {

    private var pb: ProgressBar? = null
    private var tv: TextView? = null

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context) : super(context) {
        initAttrs(null)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        orientation = LinearLayout.HORIZONTAL
        background = context.resources.getDrawable(R.drawable.background_layout, context.theme)

        // inner component
        pb = ProgressBar(context)

        val rllpPb = LinearLayout.LayoutParams(36.dip2px(), 36.dip2px())
        rllpPb.marginStart = 12.dip2px()
        rllpPb.gravity = Gravity.CENTER_VERTICAL
        pb?.layoutParams = rllpPb

        tv = TextView(context)
        val rllpTv = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 36.dip2px())
        rllpTv.marginStart = 12.dip2px()
        rllpTv.marginEnd = 12.dip2px()
        rllpTv.gravity = Gravity.CENTER_VERTICAL
        tv?.layoutParams = rllpTv
        tv?.gravity = Gravity.CENTER_VERTICAL
        tv?.textSize = 18f
        addView(pb)
        addView(tv)

        val a = context.obtainStyledAttributes(attrs, R.styleable.LoadingView, 0, 0)
        tv?.text = a.getString(R.styleable.LoadingView_text)
        tv?.setTextColor(a.getColor(R.styleable.LoadingView_textColor, Color.BLACK))
        a.recycle()
    }
}
