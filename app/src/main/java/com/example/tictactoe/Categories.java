package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class Categories extends AppCompatActivity implements View.OnClickListener {
    private Button btn3x3,btn4x4,btn5x5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        init();
    }
    public void init(){
        btn3x3=findViewById(R.id.activity_categories_button3x3);
        btn3x3.setOnClickListener(this);
        btn4x4=findViewById(R.id.activity_categories_button4x4);
        btn4x4.setOnClickListener(this);
        btn5x5=findViewById(R.id.activity_categories_button5x5);
        btn5x5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_categories_button3x3:
                startActivity(new Intent(this,Game3x3.class));
                break;
            case R.id.activity_categories_button4x4:
                startActivity(new Intent(this,Game4x4.class));
                break;
            case R.id.activity_categories_button5x5:
                startActivity(new Intent(this,Game5x5.class));
                break;
            default:
                break;
        }
    }
}