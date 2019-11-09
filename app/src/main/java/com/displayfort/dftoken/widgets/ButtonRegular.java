package com.displayfort.dftoken.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by Husain on 17/09/2019 16:21.
 * DFFeedbackCode
 */
public class ButtonRegular extends AppCompatButton {

    public ButtonRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public ButtonRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public ButtonRegular(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        // Just Change your font name
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/FuturaBookfont.ttf");
        setTypeface(myTypeface);
    }
}
