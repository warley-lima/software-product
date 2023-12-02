package com.gs.h2oapp.Action;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gs.h2oapp.R;
import com.squareup.picasso.Picasso;

public class TesteImageActivity extends AppCompatActivity {

    ImageView img;
    Button btn;
    String url ="https://images.netstore.com.br/lojadopapel/images/p/scrapbook-19759.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_image);

        img = (ImageView)findViewById(R.id.img);
        btn = (Button)findViewById(R.id.btn);




        Resources res = getResources();
        String mDrawableName = "logo_ibira";
        int resourceId = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resourceId);
        img.setImageDrawable(drawable );




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Picasso.with(TesteImageActivity.this).load(url).into(img);
                Picasso.get().load(url).into(img);
            }
        });
    }
}
