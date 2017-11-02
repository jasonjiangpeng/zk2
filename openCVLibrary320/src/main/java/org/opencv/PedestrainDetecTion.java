package org.opencv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;



import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/10/31.
 */

public class PedestrainDetecTion  {
    String dir = Environment.getExternalStorageDirectory().toString()+"/test"  ;
    public int pedestrainDetection(){
        System.out.println(System.currentTimeMillis());
        Mat mat1=Imgcodecs.imread(dir+"/a.jpg");
        System.out.println(mat1.cols());
      //  mat1.convertTo(mat1,16);
        MatOfRect matOfRect =new MatOfRect();
        MatOfDouble matOfDouble=new MatOfDouble();
        HOGDescriptor  hogDescriptor =new HOGDescriptor();
        hogDescriptor.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
        System.out.println("tpye"+mat1.type());
       //int  d=mat1.type();
        hogDescriptor.detectMultiScale(mat1,matOfRect,matOfDouble,0,new Size(8,8),new Size(32,32),1.05,2.0,false);
        int  c=matOfRect.toArray().length;
        System.out.println(System.currentTimeMillis());
        return c;
    }
    public int pedestrainDetection(String fileName){
        Mat mat1=Imgcodecs.imread(fileName);
        MatOfRect matOfRect =new MatOfRect();
        MatOfDouble matOfDouble=new MatOfDouble();
        HOGDescriptor  hogDescriptor =new HOGDescriptor();
        hogDescriptor.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
        hogDescriptor.detectMultiScale(mat1,matOfRect,matOfDouble,0,new Size(8,8),new Size(32,32),1.05,2.0,false);
        return matOfRect.toArray().length;
    }
    public int pedestrainDetection(Context context,int  bitmap){
        Mat mat1=null;
        try {
            mat1  =     Utils.loadResource(context,bitmap,-1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MatOfRect matOfRect =new MatOfRect();
        MatOfDouble matOfDouble=new MatOfDouble();
        HOGDescriptor  hogDescriptor =new HOGDescriptor();
        hogDescriptor.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
        hogDescriptor.detectMultiScale(mat1,matOfRect,matOfDouble,0,new Size(8,8),new Size(32,32),1.05,2.0,false);
        return matOfRect.toArray().length;
    }
    public int pedestrainDetection2(byte[] bytes){
        Mat mat1=bytes2Mat(bytes);
        MatOfRect matOfRect =new MatOfRect();
        MatOfDouble matOfDouble=new MatOfDouble();
        HOGDescriptor  hogDescriptor =new HOGDescriptor();
        hogDescriptor.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
        hogDescriptor.detectMultiScale(mat1,matOfRect,matOfDouble,0,new Size(8,8),new Size(32,32),1.05,2.0,false);
        return matOfRect.toArray().length;
    }
    public Mat bytes2Mat(byte[] bytes){
        Mat encoded = new Mat(1, bytes.length, CvType.CV_8U);
        encoded.put(0, 0, bytes);
        Mat decoded = Imgcodecs.imdecode(encoded, -1);
        encoded.release();
        return decoded;
    }
    public int pedestrainDetection(byte[]  bytes){
        writeData(bytes);
        Mat mat1=Imgcodecs.imread(dir+"/a.jpg");
        MatOfRect matOfRect =new MatOfRect();
        MatOfDouble matOfDouble=new MatOfDouble();
        HOGDescriptor  hogDescriptor =new HOGDescriptor();
        hogDescriptor.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
        hogDescriptor.detectMultiScale(mat1,matOfRect,matOfDouble,0,new Size(8,8),new Size(32,32),1.05,2.0,false);
        return matOfRect.toArray().length;
    }
    public void writeData(Bitmap bitmap){
        try {
            File file = new File(dir);
            if (!file.exists()){
                System.out.println("createNewFile");
               file.mkdirs();
            }
            String fname = "a.jpg";
            File file2 = new File (file ,fname);
            if (file2.exists ()) file2.delete ();
            file2.createNewFile();
            FileOutputStream out = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeData(byte[] bytes){
        try {
            File file = new File(dir);
            if (!file.exists()){
                System.out.println("createNewFile");
                file.mkdirs();
            }
            String fname = "a.jpg";
            File file2 = new File (file ,fname);
            if (file2.exists ()) file2.delete ();
            file2.createNewFile();
            FileOutputStream out = new FileOutputStream(file2);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   /* public Mat getPedestrainMat(String name){
        Mat mat1=Imgcodecs.imread(name)
        Utils.bitmapToMat(bitmap,mat1);
        System.out.println(mat1.cols());
        System.out.println(mat1.rows());
        return mat1;
    }*/
    public Bitmap bytes2Bitmap(byte[] bytes){
        BitmapFactory.Options options =new BitmapFactory.Options();
        options.inPreferredConfig= Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        return bitmap;
    }
}
