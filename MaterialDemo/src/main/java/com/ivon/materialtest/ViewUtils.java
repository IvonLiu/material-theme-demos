package com.ivon.materialtest;

import android.os.Build;
import android.view.View;

/**
 * Created by ivon on 29/06/14.
 */
public class ViewUtils {

    /**
     * Safely sets elevation of a view by checking to
     * make sure View.setElevation() is supported
     *
     * @param view to set the elevation for
     * @param elevation to set the view to
     */
    public static void setElevation(View view, float elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.L) {
            view.setElevation(elevation);
        }
    }

    /**
     * Safely gets elevation of a view by checking to
     * make sure View.getElevation() is supported
     *
     * @param view to get elevation for
     * @return view.getElevation() if supported, otherwise returns -1
     */
    public static float getElevation(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.L) {
            return view.getElevation();
        } else {
            return -1;
        }
    }
}
