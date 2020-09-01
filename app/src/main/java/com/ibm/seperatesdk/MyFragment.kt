package com.ibm.seperatesdk

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.Exception


class MyFragment : Fragment() {
    private var statusBarColor = -1
    private var actionBarColor = -1
    private var actionBarTitleColor=-1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            if (statusBarColor != -1)
                activity?.window?.statusBarColor = resources.getColor(statusBarColor)
            if (actionBarColor != -1) {
                val colorDrawable = ColorDrawable(resources.getColor(actionBarColor));
                activity?.actionBar?.setBackgroundDrawable(colorDrawable)
                if (activity is AppCompatActivity) {
                    (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                        colorDrawable
                    )
                }
            }
            if(actionBarTitleColor!=-1) {
                var titleTxt = activity?.actionBar?.title
                if (titleTxt == null) {
                    titleTxt = (activity as AppCompatActivity).supportActionBar?.title
                }
                val text: Spannable = SpannableString(titleTxt)
                text.setSpan(
                    ForegroundColorSpan(resources.getColor(actionBarTitleColor)),
                    0,
                    text.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                activity?.actionBar?.title = text
                (activity as AppCompatActivity).supportActionBar?.title = text
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        return inflater.inflate(R.layout.fragment_mygragment, container, false)
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        val a = activity!!.obtainStyledAttributes(attrs, R.styleable.watsonFragment)
        statusBarColor = a.getResourceId(R.styleable.watsonFragment_status_bar_color, -1)
        actionBarColor = a.getResourceId(R.styleable.watsonFragment_action_bar_color, -1)
        actionBarTitleColor = a.getResourceId(R.styleable.watsonFragment_action_bar_title_color, -1)
        a.recycle();

    }
}