package drop.swift.sale.module.helper;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageHelper {
    public static void loadFixImage(String imageUrl, ImageView imageView) {
        Picasso.get()
                .load(imageUrl)
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
