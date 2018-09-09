package com.outerspace.permissionplease;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

public class Presenter {

    public void openCamera(Context context) {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            CameraManager manager = (CameraManager) context
                    .getSystemService(Context.CAMERA_SERVICE);
            try {
                String[] cameraIdList = manager.getCameraIdList();
                if(cameraIdList.length > 0) {
                    String cameraId = cameraIdList[0];
                    manager.openCamera(cameraId, getCallback(), null);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private CameraDevice.StateCallback getCallback() {
        return new CameraDevice.StateCallback() {

            @Override
            public void onOpened(@NonNull CameraDevice cameraDevice) {

            }

            @Override
            public void onDisconnected(@NonNull CameraDevice cameraDevice) {

            }

            @Override
            public void onError(@NonNull CameraDevice cameraDevice, int i) {

            }
        };
    }

}
