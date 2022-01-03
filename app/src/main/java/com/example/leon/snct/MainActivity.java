package com.example.leon.snct;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
public  class MainActivity extends AppCompatActivity {
    String Myfile;
    ImageView Imagem;
    private Button button;
    private final int capturarfoto = 104;
    Button bt;
    Camera camera;
    Bitmap resizeImagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.BGaleria);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActGaleria();
            }
        });
        initializedUI();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},1);
                    }
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);
                }else{
                    tirarfoto();
                }
            }
        });
    }
    public void tirarfoto(){
      Intent cameraIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
      startActivityForResult(cameraIntent, capturarfoto);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnIntent){
        super.onActivityResult(requestCode,resultCode,returnIntent);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case capturarfoto:
                    Bitmap fototirada = (Bitmap) returnIntent.getExtras().get("data");
                    Imagem.setImageBitmap(fototirada);
                    salvarfoto(fototirada);
                    break;
                    default:
                        break;
            }
        }
    }
    private void salvarfoto(Bitmap finalBitmap){
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File (root + "/SNCT");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n=generator.nextInt(n);
        String Nome = "BarrieBraker-"+n+".jpg";
        Myfile=Nome;
        File file = new File (myDir,Nome);
        if (file.exists())file.delete();
        try{
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            String resizeImg = file.getAbsolutePath();
            out.flush();
            out.close();
            Toast.makeText(MainActivity.this, "Sua foto foi salva com sucesso",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Exception lançada",Toast.LENGTH_SHORT).show();
        }
        refreshGaleria(file);
    }
    public void refreshGaleria(File file){
        Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }

    public void initializedUI(){
        bt = (Button)findViewById(R.id.TirarFoto);
        Imagem = (ImageView)findViewById(R.id.imgv);
    }
    public void openActGaleria(){
        Intent intent = new Intent(this, ActGaleria.class);
        startActivity(intent);
    }

    }

