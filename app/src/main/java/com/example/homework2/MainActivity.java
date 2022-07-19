package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private Button clearButton;
    private Button[] buttonBoxes = new Button[9];
    private int[] board = new int[9];  // 0 == Empty, 1 == O, 2 == X
    private int turn = 0; // 0 for O, 1 for X

    private void updateBoard() {
        for (int i = 0; i < 9; ++i) {
            if (board[i] != 0) {
                String text = "X";
                if (board[i] == 1) {
                    text = "O";
                }
                buttonBoxes[i].setText(text);
            }
        }
    }
    
    private void clearBoard() {
        for (int i = 0; i < 9; ++i) {
            board[i] = 0;
            buttonBoxes[i].setText("");
        }
    }

    private Boolean checkForWin() {
        if (board[0] != 0 && board[0] == board[1] && board[1] == board[2])
            return true;
        if (board[3] != 0 && board[3] == board[4] && board[4] == board[5])
            return true;
        if (board[6] != 0 && board[6] == board[7] && board[7] == board[8])
            return true;
        if (board[0] != 0 && board[0] == board[3] && board[3] == board[6])
            return true;
        if (board[1] != 0 && board[1] == board[4] && board[4] == board[7])
            return true;
        if (board[2] != 0 && board[2] == board[5] && board[5] == board[8])
            return true;
        if (board[0] != 0 && board[0] == board[4] && board[4] == board[8])
            return true;
        if (board[2] != 0 && board[2] == board[4] && board[4] == board[6])
            return true;
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        clearButton = (Button) findViewById(R.id.clear_button);
        buttonBoxes[0] = (Button) findViewById(R.id.box_1);
        buttonBoxes[1] = (Button) findViewById(R.id.box_2);
        buttonBoxes[2] = (Button) findViewById(R.id.box_3);
        buttonBoxes[3] = (Button) findViewById(R.id.box_4);
        buttonBoxes[4] = (Button) findViewById(R.id.box_5);
        buttonBoxes[5] = (Button) findViewById(R.id.box_6);
        buttonBoxes[6] = (Button) findViewById(R.id.box_7);
        buttonBoxes[7] = (Button) findViewById(R.id.box_8);
        buttonBoxes[8] = (Button) findViewById(R.id.box_9);


        for (int i = 0; i < 9; ++i) {
            final int finalI = i;
            buttonBoxes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (board[finalI] != 0) {
                        Toast.makeText(getApplicationContext(), "Tile has been filled",
                                Toast.LENGTH_LONG).show();
                        // System.out.println("Tile has been filled");
                    } else {
                        board[finalI] = turn + 1;
                        turn ^= 1;

                        updateBoard();

                        if (checkForWin()) {
                            Toast.makeText(MainActivity.this, "Game Won",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                }
            });
        }

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBoard();
            }
        });


    }
}