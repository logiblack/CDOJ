package cn.edu.uestc.acm.cdoj.resources.utils;

import android.content.res.Resources;
import android.support.annotation.ColorRes;

/**
 * Created by Great on 2016/10/8.
 */

public class RGBAColor {
    public int R = 0;
    public int G = 0;
    public int B = 0;
    public int A = 0;

    public RGBAColor(int R, int G, int B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public RGBAColor(int R, int G, int B, int A) {
        this.R = R;
        this.G = G;
        this.B = B;
        this.A = A;
    }

    public float[] getColorMatrix(boolean asSkew) {
        if (asSkew) {
            return new float[]{
                    1, 0, 0, 0, R,
                    0, 1, 0, 0, G,
                    0, 0, 1, 0, B,
                    0, 0, 0, 1, A};

        }
        return new float[]{
                0, 0, 0, 0, R,
                0, 0, 0, 0, G,
                0, 0, 0, 0, B,
                0, 0, 0, 0, A};
    }

    public static float[] getColorMatrix(int R, int G, int B) {
        return getColorMatrix(R, G, B, false);
    }

    public static float[] getColorMatrix(int R, int G, int B, boolean asSkew) {
        return getColorMatrix(R, G, B, 0, asSkew);
    }

    public static float[] getColorMatrix(int R, int G, int B, int A) {
        return getColorMatrix(R, G, B, A, false);
    }

    public static float[] getColorMatrix(int R, int G, int B, int A, boolean asSkew) {
        if (asSkew) {
            return new float[]{
                    1, 0, 0, 0, R,
                    0, 1, 0, 0, G,
                    0, 0, 1, 0, B,
                    0, 0, 0, 1, A};
        }
        return new float[]{
                0, 0, 0, 0, R,
                0, 0, 0, 0, G,
                0, 0, 0, 0, B,
                0, 0, 0, 0, A};
    }

    public static float[] getColorMatrix(Resources resources, @ColorRes int colorResource) {
        return getColorMatrix(resources, colorResource, false);
    }

    public static float[] getColorMatrix(Resources resources, @ColorRes int colorResource, boolean asSkew) {
        int[] RGBA = getRGBAValues(resources, colorResource);
        if (asSkew) {
            return new float[]{
                    1, 0, 0, 0, RGBA[0],
                    0, 1, 0, 0, RGBA[1],
                    0, 0, 1, 0, RGBA[2],
                    0, 0, 0, 0, RGBA[3]};
        }
        return new float[]{
                0, 0, 0, 0, RGBA[0],
                0, 0, 0, 0, RGBA[1],
                0, 0, 0, 0, RGBA[2],
                0, 0, 0, 0, RGBA[3]};
    }

    public static float[] getColorMatrix(Resources resources, @ColorRes int colorResource, float transparent) {
        return getColorMatrix(resources, colorResource, transparent, false);
    }

    public static float[] getColorMatrix(Resources resources, @ColorRes int colorResource, float transparent, boolean asSkew) {
        int[] RGBA = getRGBAValues(resources, colorResource);
        return getColorMatrix(RGBA[0], RGBA[1], RGBA[2], transparent, asSkew);
    }

    public static float[] getColorMatrix(int R, int G, int B, float transparent) {
        return getColorMatrix(R, G, B, transparent, false);
    }

    public static float[] getColorMatrix(int R, int G, int B, float transparent, boolean asSkew) {
        if (asSkew) {
            return new float[]{
                    1, 0, 0, 0, R,
                    0, 1, 0, 0, G,
                    0, 0, 1, 0, B,
                    0, 0, 0, transparent, 0};
        }
        return new float[]{
                0, 0, 0, 0, R,
                0, 0, 0, 0, G,
                0, 0, 0, 0, B,
                0, 0, 0, transparent, 0};
    }

    public static int[] getRGBAValues(Resources resources, @ColorRes int colorResource) {
        int color = resources.getColor(colorResource);
        int A = (color & 0xff000000) >>> 24;
        int R = (color & 0x00ff0000) >>> 16;
        int G = (color & 0x0000ff00) >>> 8;
        int B = (color & 0x000000ff);
        return new int[]{R, G, B, A};
    }
}
