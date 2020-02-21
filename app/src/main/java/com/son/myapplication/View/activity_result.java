package com.son.myapplication.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.son.myapplication.R;

public class activity_result extends AppCompatActivity {
    Button Back,Next;
    TextView KQ;
    int highScore=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        KQ = (TextView) findViewById(R.id.TxtKQ);
        Back = (Button)findViewById(R.id.BtnBack);
        Intent callerintent=getIntent();
        Bundle packagefromcaller= callerintent.getBundleExtra("MyPackage");
        int grade=packagefromcaller.getInt("score");
        String result=packagefromcaller.getInt("score")+"/"+
                packagefromcaller.getInt("position");
        KQ.setText(result);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}
