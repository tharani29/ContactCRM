package com.tharani;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.tharani.Post.PostData;

public class MainPage extends AppCompatActivity {

    Button sendData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        sendData=(Button)findViewById(R.id.insertUser);

        sendData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PostData.class);
                    startActivity(intent);
            }
        });
    };

}
