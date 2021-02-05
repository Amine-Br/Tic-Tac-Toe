package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Game3x3 extends AppCompatActivity {
    private int current_player = 1, childCount;
    private int[][] boxes;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3x3);
        init();
        onclickgridlayout();
    }

    private void init() {
        boxes = new int[3][3];
        remplirmatrice(boxes);
        gridLayout = findViewById(R.id.activity_game3x3_gridlayout);
        childCount = gridLayout.getChildCount();
    }

    private void remplirmatrice(int[][] matrice) {
        int val = 15;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrice[i][j] = val;
                val++;
            }
        }
    }

    private void onclickgridlayout() {
        for (int i = 0; i < childCount; i++) {
            int position = i;
            ImageView container = (ImageView) gridLayout.getChildAt(i);
            container.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    switch (current_player) {
                        case 1:
                            if (position < 3) {
                                boxes[0][position] = current_player;
                                Log.d("boxes", "boxes[" + 0 + "] [" + position + "]=" + current_player);
                            } else {
                                if (position < 6) {
                                    boxes[1][position % 3] = current_player;
                                    Log.d("boxes", "boxes[" + 1 + "] [" + position % 3 + "]=" + current_player);
                                } else {
                                    boxes[2][position % 6] = current_player;
                                    Log.d("boxes", "boxes[" + 2 + "] [" + position % 6 + "]=" + current_player);
                                }
                            }
                            container.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.xbox, getTheme()));
                            if (checkwin()) {
                                Toast toast = Toast.makeText(getApplicationContext(), "player " + current_player + " win!", Toast.LENGTH_SHORT);
                                toast.show();
                                finish();
                            }
                            current_player = 2;
                            container.setEnabled(false);
                            break;
                        case 2:
                            if (position < 3) {
                                boxes[0][position] = current_player;
                                Log.d("boxes", "boxes[" + 0 + "] [" + position + "]=" + current_player);
                            } else if (position < 6) {
                                boxes[1][position % 3] = current_player;
                                Log.d("boxes", "boxes[" + 1 + "] [" + position % 3 + "]=" + current_player);
                            } else {
                                boxes[2][position % 6] = current_player;
                                Log.d("boxes", "boxes[" + 2 + "] [" + position % 6 + "]=" + current_player);
                            }
                            container.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.obox, getTheme()));
                            if (checkwin()) {
                                Toast toast = Toast.makeText(getApplicationContext(), "player " + current_player + " win!", Toast.LENGTH_SHORT);
                                toast.show();
                                finish();
                            }
                            current_player = 1;
                            container.setEnabled(false);
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

    private boolean checkwin() {
        if (boxes[0][0] == boxes[1][0] && boxes[0][0] == boxes[2][0]) {
            return true;
        }
        if (boxes[0][1] == boxes[1][1] && boxes[0][1] == boxes[2][1]) {
            return true;
        }
        if (boxes[0][2] == boxes[1][2] && boxes[0][2] == boxes[2][2]) {
            return true;
        }
        if (boxes[0][0] == boxes[0][1] && boxes[0][0] == boxes[0][2]) {
            return true;
        }
        if (boxes[1][0] == boxes[1][1] && boxes[1][0] == boxes[1][2]) {
            return true;
        }
        if (boxes[2][0] == boxes[2][1] && boxes[2][0] == boxes[2][2]) {
            return true;
        }
        if (boxes[0][0] == boxes[1][1] && boxes[0][0] == boxes[2][2]) {
            return true;
        }
        if (boxes[2][0] == boxes[1][1] && boxes[2][0] == boxes[0][2]) {
            return true;
        }
        return false;
    }
}