package com.example.leon.snct;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback
{
    Camera camera;
    SurfaceHolder holder;
    public ShowCamera(Context context,Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);


    }
    @Override
    public void  surfaceChanged(SurfaceHolder holder , int f, int w,int h){

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        Camera.Parameters params = camera.getParameters();
        //pega as orientações da camera
        if (this.getResources().getConfiguration().orientation!= Configuration.ORIENTATION_LANDSCAPE) {
            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        } else{
                params.set("orientation","landscape");
                camera.setDisplayOrientation(0);
                params.setRotation(0);
            }
            camera.setParameters(params);
        try{
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}