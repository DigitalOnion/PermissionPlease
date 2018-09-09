package com.outerspace.permissionplease;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    private Presenter presenter = new Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickRequestPermission(View view) {
        if(! presenter.openCamera(this)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(this)
                        .setMessage(R.string.retionale_permission)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                requestCameraPermission();
                            }
                        })
                        .create().show();
            } else {
                requestCameraPermission();
            }
        }
    }

    private void requestCameraPermission() {
        String[] arrayPermissions = new String[] {Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions(this,
                arrayPermissions,
                CAMERA_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            Log.d("LUIS", "onRequestPermission");
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
