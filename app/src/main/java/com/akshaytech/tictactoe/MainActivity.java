package com.akshaytech.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public int i,j;
    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    String firstname,secondname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.tv1);
        textViewPlayer2 = findViewById(R.id.tv2);
        Bundle bn = getIntent().getExtras();
        firstname = bn.getString("first");
        secondname = bn.getString("second");
        textViewPlayer1.setText(firstname+":");
        textViewPlayer2.setText(secondname+":");


        for ( i = 0; i < 3; i++) {
            for ( j = 0; j < 3; j++) {
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (i = 0; i < 3; i++) {
            for ( j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for ( i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Result");
        alertDialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Well done, "+firstname,Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setMessage(firstname+" won!");
        alertDialogBuilder.show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Result");
        alertDialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Well done, "+secondname,Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setMessage(secondname+" won!");
        alertDialogBuilder.show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Result");
        alertDialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Well done",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setMessage("Draw!!!");
        alertDialogBuilder.show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText(firstname+": " + player1Points);
        textViewPlayer2.setText(secondname+": " + player2Points);
    }

    private void resetBoard() {
        for ( i = 0; i < 3; i++) {
            for ( j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar,menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void exitApp(MenuItem item) {
        Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void home(MenuItem item) {
        Intent intent = new Intent(MainActivity.this,StartActivity.class);
        startActivity(intent);
    }

    public void resetGame(MenuItem item) {
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
    }
}
