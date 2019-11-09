package com.displayfort.dftoken.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Husain on 17/09/2019 16:21.
 * DFFeedbackCode
 */
public class TextviewBold extends TextView {

    public TextviewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public TextviewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public TextviewBold(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        // Just Change your font name
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/futuraheavyfont.ttf");
        setTypeface(myTypeface);
    }
}
