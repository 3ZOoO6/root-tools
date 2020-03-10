package com.rarnu.tools.neo.comp

import android.content.Context
import android.preference.Preference
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rarnu.tools.neo.R
import kotlinx.android.synthetic.main.comp_preference.view.*

class PreferenceEx : Preference {

    private lateinit var innerView: View
    private var showSwitch = false
    private var showIcon = true
    private var exTitle = ""
    private var isOn = false

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttr(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttr(attrs)
    }

    constructor(context: Context) : super(context) {
        initAttr(null)
    }

    private fun initAttr(attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.PreferenceEx, 0, 0)
            showSwitch = a.getBoolean(R.styleable.PreferenceEx_showSwitch, false)
            showIcon = a.getBoolean(R.styleable.PreferenceEx_showIcon, true)
            if (a.hasValue(R.styleable.PreferenceEx_extitle)) {
                exTitle = a.getString(R.styleable.PreferenceEx_extitle)!!
            }
            a.recycle()
        }
    }

    override fun onBindView(view: View) {
        try {
            super.onBindView(view)
        } catch (e: Exception) {

        }
        with(innerView) {
            prefTitle.text = title
            prefExTitle.text = exTitle
            prefSummary.text = summary
            if (summary == null || summary == "") {
                prefSummary.visibility = View.GONE
            }
            prefStatus.isChecked = isOn
            prefIcon.setImageDrawable(icon)
        }

    }

    override fun onCreateView(parent: ViewGroup): View {
        super.onCreateView(parent)
        innerView = LayoutInflater.from(context).inflate(R.layout.comp_preference, parent, false).apply {
            prefStatus.visibility = if (showSwitch) View.VISIBLE else View.GONE
            prefIcon.visibility = if (showIcon) View.VISIBLE else View.GONE
        }
        return innerView
    }

    override fun setTitle(titleResId: Int) {
        super.setTitle(titleResId)
        innerView.prefTitle.setText(titleResId)
    }

    override fun setSummary(summaryResId: Int) {
        super.setSummary(summaryResId)
        if (summary == null || summary == "") {
            innerView.prefSummary?.visibility = View.GONE
        }
    }

    override fun setIcon(iconResId: Int) {
        super.setIcon(iconResId)
        innerView.prefIcon?.setImageDrawable(icon)
    }

    fun setShowSwitch(on: Boolean) {
        showSwitch = on
        innerView.prefStatus?.visibility = if (on) View.VISIBLE else View.GONE
    }

    var status: Boolean
        get() = innerView.prefStatus.isChecked
        set(on) {
            isOn = on
            innerView.prefStatus.isChecked = on
        }
}
