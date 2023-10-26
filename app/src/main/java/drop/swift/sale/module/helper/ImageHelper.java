package drop.swift.sale.module.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import drop.swift.sale.module.PicassoRoundedTransformation;

public class ImageHelper {
    public static void loadFixImage(String imageUrl, ImageView imageView) {
        Transformation transformation = new PicassoRoundedTransformation(20, 0);
        Picasso.get()
                .load(imageUrl)
                .transform(transformation)
                .resize(500, 400)
                .centerCrop() // or .fit() depending on your preference
                .into(imageView);
    }
    public static void loadResizeImage(String imageUrl, ImageView imageView, int targetWidth, int targetHeight) {
        Picasso.get()
                .load(imageUrl)
                .resize(targetWidth, targetHeight)
                .centerCrop() // or .fit() depending on your preference
                .into(imageView);
    }
}
