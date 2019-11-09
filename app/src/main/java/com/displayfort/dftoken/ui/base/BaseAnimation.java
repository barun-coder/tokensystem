package com.displayfort.dftoken.ui.base;

import android.app.Activity;

import com.displayfort.dftoken.R;


public class BaseAnimation {

    public enum EFFECT_TYPE {
        TAB_DEAFULT, TAB_SLIDE, TAB_FADE, TAB_SLIDE_RIGHT, TAB_SLIDE_RIGHT_FINISH, TAB_SLIDE_FINISH, TAB_SLIDE_DOWN_TO_UP, TAB_ZOOM;
    }

    public static void setAnimationTransition(Activity context, EFFECT_TYPE type) {
        switch (type) {
            case TAB_SLIDE:
                context.overridePendingTransition(R.anim.slide_in_right,
                        R.anim.fade_out);
                break;
            case TAB_FADE:
                context.overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                break;

            case TAB_SLIDE_DOWN_TO_UP:
                context.overridePendingTransition(R.anim.slide_in_up,
                        R.anim.slide_out_up);
                break;
            case TAB_ZOOM:
                context.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                break;

            default:
                context.overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                break;
        }

    }

}
