package com.example.leon.snct;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ActGaleria extends AppCompatActivity {
    ImageView Imagem;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_galeria);
        Imagem = (ImageView) findViewById(R.id.ImCamera);
        bt=(Button)findViewById(R.id.comp);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent it = new Intent(Intent.ACTION_SEND);
                it.setType("plain/text");
                String shareBody = "RELATE O PROBLEMA";
                String shareSub = "INSIRA SEU NOME E SUA CIDADE";
                it.putExtra(Intent.EXTRA_STREAM, Uri.parse("file///BarrieBraker.jpg"));
                it.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                it.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(it, "Compartilhar em que app"));
            }
        });
        }
    public void onclick(View view) {
        carregarImagem();
    }
    private void carregarImagem() {
        Intent intent=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Selecione uma aplicação "),10);
    }

    @Override
   protected
    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path=data.getData();
            Imagem.setImageURI(path);
        }
    }
}
