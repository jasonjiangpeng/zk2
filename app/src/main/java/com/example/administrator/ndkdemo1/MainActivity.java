package com.example.administrator.ndkdemo1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DebugUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.ndkdemo1.ndk.NdkFunction;

import org.opencv.PedestrainDetecTion;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
  /*  static {
        System.loadLibrary("native-lib");

    }*/
  private String  tag="testData";
    static {
        if(!OpenCVLoader.initDebug()){
            LogShow.logShow("OpenCV not loaded");
        } else {
            LogShow.logShow("OpenCV loaded");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(tag,"test====================");
        PedestrainDetecTion pedestrainDetecTion =new PedestrainDetecTion();
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.a1);

        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        Log.e(tag,"startTime===================="+System.currentTimeMillis());
        int i1 = pedestrainDetecTion.pedestrainDetection2(bytes);
        Log.e(tag,"endTime===================="+System.currentTimeMillis());
        System.out.println(i1+"=====xxx=====");

   //     int i = pedestrainDetecTion.pedestrainDetection(getApplicationContext(),R.drawable.a1);
        System.out.println(System.currentTimeMillis());



    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native int getAdd(int a,int b);
}
