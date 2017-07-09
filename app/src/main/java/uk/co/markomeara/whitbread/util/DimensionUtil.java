package uk.co.markomeara.whitbread.util;

import android.content.res.Resources;
import android.support.annotation.DimenRes;
import android.util.TypedValue;

public class DimensionUtil {

    private DimensionUtil() {
        throw new AssertionError();
    }

    public static float convertDimensionDpToPx(@DimenRes int dimen, Resources resources) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                resources.getDimension(dimen),
                resources.getDisplayMetrics());
    }
}
