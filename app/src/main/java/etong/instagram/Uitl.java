package etong.instagram;

import android.content.Context;

/**
 * Created by Administrator on 2014/9/18.
 */
public class Uitl {

    private static float density;

    public static float getDensity(Context context){
        if(density==0) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }
}
