package cn.edu.uestc.acm.cdoj.ui.widget.GestureList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import cn.edu.uestc.acm.cdoj.R;


/**
 * Created by leigu on 2017/4/10.
 */

public class Icons {
    private static Icons instance;

    private Paint paint;
    private float[] defaultColorMatrix;
    private float[] colorMatrix;

    private Drawable loadCompleteIcon;
    private Drawable loadProblemIcon;
    private Drawable noDataIcon;
    private Drawable netProblemIcon;
    private Drawable backTopIcon;
    private Resources resources;



    private Icons(Resources resources) {
        this.resources = resources;
        defaultColorMatrix = new float[]{
                0, 0, 0, 0, 255,
                0, 0, 0, 0, 166,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0};
        paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(defaultColorMatrix));
        refreshIcons();
    }

    public static synchronized Icons createInstance(Resources resources) {
        if (instance == null) {
            instance = new Icons(resources);
        }
        return instance;
    }

    public static Icons getInstance() {
        if (instance == null) {
            throw new IllegalStateException(" need  create first !");
        }
        return instance;
    }

    public void setColorMatrix(float[] colorMatrix) {
        if (colorMatrix == null) return;
        if (colorMatrix.length != 20) return;
        this.colorMatrix = colorMatrix;
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        refreshIcons();
    }

    public void restoreDefaultColorMatrix(){
        paint.setColorFilter(new ColorMatrixColorFilter(defaultColorMatrix));
        refreshIcons();
    }

    public float[] getColorMatrix() {
        if (colorMatrix != null) return colorMatrix;
        return defaultColorMatrix;
    }

    private void refreshIcons() {
        loadCompleteIcon = draw(resources, R.drawable.ic_list_footer_load_complete);
        loadProblemIcon = draw(resources, R.drawable.ic_list_footer_load_problem);
        noDataIcon = draw(resources, R.drawable.ic_list_footer_no_data);
        netProblemIcon = draw(resources, R.drawable.ic_list_footer_net_problem);
        backTopIcon = draw(resources, R.drawable.ic_list_back_top);
    }


    BitmapDrawable draw(Resources resources, @DrawableRes int imageResource) {
        return draw(resources, BitmapFactory.decodeResource(resources, imageResource));
    }

    BitmapDrawable draw(Resources resources, Bitmap bitmap) {
        Bitmap afterBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        canvas.drawBitmap(bitmap, new Matrix(), paint);
        return new BitmapDrawable(resources, afterBitmap);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }

    public Drawable getLoadCompleteIcon() {
        return loadCompleteIcon;
    }

    public Drawable getLoadProblemIcon() {
        return loadProblemIcon;
    }

    public Drawable getNoDataIcon() {
        return noDataIcon;
    }

    public Drawable getNetProblemIcon() {
        return netProblemIcon;
    }

    public Drawable getBackTopIcon() {
        return backTopIcon;
    }
}