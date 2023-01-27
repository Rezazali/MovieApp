package com.rezazali.movieapp.presentation.ui.component

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatTextView


class CustomPersianTextViewLight : AppCompatTextView {

    fun setFont(context: Context) {
        val typeface = Typeface.createFromAsset(context.assets, "font/IRANSansMobile_Light.ttf")
        setTypeface(typeface)
    }

    constructor(context: Context) : super(context) {
        setFont(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setFont(context)
    }



    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        setFont(context)
    }
}