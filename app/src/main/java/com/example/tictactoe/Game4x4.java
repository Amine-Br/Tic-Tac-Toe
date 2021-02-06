package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Game4x4 extends AppCompatActivity {
    private int current_player = 1, childCount;
    private int[][] boxes;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4x4);
        init();
        onclickgridlayout();
    }

    private void init() {
        boxes = new int[4][4];
        fill_matrix(boxes);
        gridLayout = findViewById(R.id.activity_game4x4_gridlayout);
        childCount = gridLayout.getChildCount();
    }

    private void fill_matrix(int[][] matrice) {
        int val = 15;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
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
                            play(position);
                            container.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.xbox, getTheme()));
                            checkgame();
                            current_player = 2;
                            container.setEnabled(false);
                            break;
                        case 2:
                            play(position);
                            container.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.obox, getTheme()));
                            checkgame();
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

    private void play(int position) {
        if (position < 4) {
            boxes[0][position] = current_player;
            Log.d("boxes", "boxes[" + 0 + "] [" + position + "]=" + current_player);
        } else {
            if (position < 8) {
                boxes[1][position % 4] = current_player;
                Log.d("boxes", "boxes[" + 1 + "] [" + position % 4 + "]=" + current_player);
            } else {
                if (position < 12) {
                    boxes[2][position % 8] = current_player;
                    Log.d("boxes", "boxes[" + 2 + "] [" + position % 8 + "]=" + current_player);
                } else {
                    boxes[3][position % 12] = current_player;
                    Log.d("boxes", "boxes[" + 3 + "] [" + position % 12 + "]=" + current_player);
                }
            }
        }
    }

    private void checkgame() {
        if (checkwin()) {
            Toast toast = Toast.makeText(getApplicationContext(), "player " + current_player + " win!", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        } else {
            if (checkdraw()) {
                Toast toast = Toast.makeText(getApplicationContext(), "draw", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        }
    }

    private boolean checkwin() {
        //vertical
        if (boxes[0][0] == boxes[1][0] && boxes[0][0] == boxes[2][0] && boxes[0][0] == boxes[3][0]) {
            return true;
        }
        if (boxes[0][1] == boxes[1][1] && boxes[0][1] == boxes[2][1] && boxes[0][1] == boxes[3][1]) {
            return true;
        }
        if (boxes[0][2] == boxes[1][2] && boxes[0][2] == boxes[2][2] & boxes[0][2] == boxes[3][2]) {
            return true;
        }
        if (boxes[0][3] == boxes[1][3] && boxes[0][3] == boxes[2][3] & boxes[0][3] == boxes[3][3]) {
            return true;
        }
        //horizontal
        if (boxes[0][0] == boxes[0][1] && boxes[0][0] == boxes[0][2] && boxes[0][0] == boxes[0][3]) {
            return true;
        }
        if (boxes[1][0] == boxes[1][1] && boxes[1][0] == boxes[1][2] && boxes[1][0] == boxes[1][3]) {
            return true;
        }
        if (boxes[2][0] == boxes[2][1] && boxes[2][0] == boxes[2][2] && boxes[2][0] == boxes[2][3]) {
            return true;
        }
        if (boxes[3][0] == boxes[3][1] && boxes[3][0] == boxes[3][2] && boxes[3][0] == boxes[3][3]) {
            return true;
        }
        //diagonal
        if (boxes[0][0] == boxes[1][1] && boxes[0][0] == boxes[2][2] && boxes[0][0] == boxes[3][3]) {
            return true;
        }
        if (boxes[3][0] == boxes[2][1] && boxes[3][0] == boxes[1][2] && boxes[3][0] == boxes[0][3]) {
            return true;
        }
        return false;
    }

    private boolean checkdraw() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (boxes[i][j] != 1 && boxes[i][j] != 2) {
                    return false;
                }
            }
        }
        return true;
    }
}