package com.outerspace.permissionplease;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static android.hardware.camera2.CameraMetadata.LENS_FACING_FRONT;

public class Presenter {

    public boolean openCamera(Activity activity) {
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            CameraManager manager =
                    (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
            try {
                String[] cameraIdList = manager.getCameraIdList();
                for(String cameraId: cameraIdList) {
                    CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                    Integer lensFacing = characteristics.get(CameraCharacteristics.LENS_FACING);
                    if(lensFacing.equals(LENS_FACING_FRONT)) {
                        manager.openCamera(cameraId, getCallback(), null);
                    }
                }
                if(cameraIdList.length > 0) {
                    String cameraId = cameraIdList[0];
                    manager.openCamera(cameraId, getCallback(), null);
                }
            } catch (CameraAccessException | NullPointerException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
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
