package com.example.reservationtable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.BarcodeFormat;

import java.io.IOException;

public class SkanerActivity extends AppCompatActivity {
    SurfaceView surfaceKamera;
    TextView textKodQR;

    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;

    public static final int CAMERA_PERMISSION_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skaner);

        surfaceKamera=findViewById(R.id.camera);
        textKodQR=findViewById(R.id.textskaner);


        checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE);

        barcodeDetector=new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource=new CameraSource.Builder(getApplicationContext(),barcodeDetector).setRequestedPreviewSize(640,480).build();

        surfaceKamera.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                try{
                    cameraSource.start(surfaceHolder);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {

            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> grcode=detections.getDetectedItems();
                if(grcode.size() !=0){
                    textKodQR.post(new Runnable() {
                        @Override
                        public void run() {
                            textKodQR.setText(grcode.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });
    }

    public void checkPermission(String permission,int requestCode){
        if(ContextCompat.checkSelfPermission(SkanerActivity.this,permission)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(SkanerActivity.this,new String[] {permission},requestCode);
        }else{
            Toast.makeText(SkanerActivity.this,"Permission already granted",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantsResult){
        super.onRequestPermissionsResult(requestCode,permissions,grantsResult);
        if(requestCode==CAMERA_PERMISSION_CODE){
            if(grantsResult.length>0 && grantsResult[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(SkanerActivity.this,"Permission Granted",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(SkanerActivity.this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
