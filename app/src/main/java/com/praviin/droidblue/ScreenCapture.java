package com.praviin.droidblue;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenCapture {

    private static final String TAG = ScreenCapture.class.getSimpleName();

    private Context context;
    private int layout;
    private ScrollView scroll; // Even NestedScrollView also work

    ScreenCapture(Context ctx, ScrollView scrollView,  int ref){
        this.context = ctx;
        this.scroll = scrollView;
        this.layout = ref;
    }


    public void onScrollCaptureImg() throws IOException {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Change to any layout you want
        RelativeLayout root = (RelativeLayout) inflater.inflate(layout,null); //Relative layout is my rootView
        root.setDrawingCacheEnabled(true);
        Bitmap bitmap = getBitmapFromView(scroll, scroll.getChildAt(0).getHeight(), scroll.getChildAt(0).getWidth());
        if (bitmap != null) StoreImage(bitmap);

    }

    //Capture your scroll View content
    private Bitmap getBitmapFromView(ScrollView scroll, int height, int width) {

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = scroll.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        scroll.draw(canvas);


        return bitmap;
    }

    //Store Scrollview as PNG Format
    private void StoreImage(Bitmap bitmap) throws IOException {
        //Need to add Permission
        String filename = "APJ.png"; // You can Change to Any Image Format
        File source = Environment.getExternalStorageDirectory();
        File Destination = new File(source, filename);

        FileOutputStream out = new FileOutputStream(Destination);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // You can Change to Any Image Format
        out.flush();
        out.close();
        Log.e(TAG,"Image Stored :)");
        Toast.makeText(context, "Stored @ "+Destination, Toast.LENGTH_SHORT).show();
        //It will work in your android device
        //not work in emulator
        //Thanks

    }


}
