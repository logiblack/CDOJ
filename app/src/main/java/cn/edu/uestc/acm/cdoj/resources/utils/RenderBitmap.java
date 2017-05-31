package cn.edu.uestc.acm.cdoj.resources.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.DrawableRes;

/**
 * Created by Great on 2016/10/8.
 */

public class RenderBitmap {
    private static RenderBitmap instance;
    private final Resources resources;
    private final float[] defaultColorMatrix;
    private final Paint defaultPaint;
    private float[] colorMatrix;

    private RenderBitmap(Resources resources) {
        this.resources = resources;
        defaultColorMatrix = new float[]{
                0, 0, 0, 0, 255,
                0, 0, 0, 0, 166,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0};
        defaultPaint = new Paint();
        defaultPaint.setColorFilter(new ColorMatrixColorFilter(defaultColorMatrix));
    }

    public static RenderBitmap instance(Resources resources) {
        if (instance == null) {
            instance = new RenderBitmap(resources);
        }
        return instance;
    }

    public void setColorMatrix(float[] colorMatrix) {
        if (colorMatrix == null) return;
        if (colorMatrix.length != 20) return;
        this.colorMatrix = colorMatrix;
        defaultPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    public void restoreColorMatrix() {
        defaultPaint.setColorFilter(new ColorMatrixColorFilter(defaultColorMatrix));
    }

    public float[] getColorMatrix() {
        if (colorMatrix != null) return colorMatrix;
        return defaultColorMatrix;
    }

    public BitmapDrawable render(@DrawableRes int imageResource) {
        return render(BitmapFactory.decodeResource(resources, imageResource));
    }

    public BitmapDrawable render(@DrawableRes int imageResource, float[] colorMatrix) {
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        return render(BitmapFactory.decodeResource(resources, imageResource), paint);
    }

    public BitmapDrawable render(Bitmap bitmap) {
        return render(bitmap, defaultPaint);
    }

    public BitmapDrawable render(Bitmap bitmap, float[] colorMatrix) {
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        return render(bitmap, paint);
    }

    public BitmapDrawable render(Bitmap bitmap, Paint paint) {
        Bitmap afterBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        canvas.drawBitmap(bitmap, new Matrix(), paint);
        return new BitmapDrawable(resources, afterBitmap);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }
}
