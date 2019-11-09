package com.displayfort.dftoken.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Husain on 17/09/2019 16:21.
 * DFFeedbackCode
 */
public class EditextRegular extends EditText {

    public EditextRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public EditextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public EditextRegular(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        // Just Change your font name
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/FuturaBookfont.ttf");
        setTypeface(myTypeface);
    }
}
