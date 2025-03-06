package com.flauschcode.broccoli.shopping;

import android.graphics.Paint;
import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {
    @BindingAdapter("strikethrough")
    public static void setStrikethrough(TextView textView, boolean strikethrough) {
        Log.d("BindingAdapters", "Setting strikethrough to " + strikethrough);
        if (strikethrough) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
        textView.invalidate();
    }
}
