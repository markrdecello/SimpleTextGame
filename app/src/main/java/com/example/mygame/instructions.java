package com.example.mygame;

import android.os.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class instructions extends AppCompatActivity {

    TextView userName, greeting;
    Button north, south, east, west;
    String location = "Lobby";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        String text = intent.getStringExtra(info.EXTRA_TEXT);

        userName = findViewById(R.id.user);
        greeting = findViewById(R.id.greeting);
        north = findViewById(R.id.north);
        south = findViewById(R.id.south);
        west = findViewById(R.id.west);
        east = findViewById(R.id.east);

        userName.setText("WELCOME " + text.toUpperCase() + "!");

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move north
                userName.setText(R.string.choose);
                String direction = "north";
                location = pickRoom(direction, location);
                showRoom(location);
            }
        });

        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move south
                userName.setText(R.string.choose);
                showRoom(location);
                String direction = "south";
                location = pickRoom(direction, location);
                showRoom(location);
                if (location.equals("Exit")){
                    Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            return;
                        }

                    }, 9000);
                }
            }
        });

        west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move west
                userName.setText(R.string.choose);
                showRoom(location);
                String direction = "west";
                location = pickRoom(direction, location);
            }
        });

        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move east
                userName.setText(R.string.choose);
                showRoom(location);
                String direction = "east";
                location = pickRoom(direction, location);
            }
        });
    }

    private void showRoom(String room) {
        if (room.equals("Exit")){
            show_exit();
        }
        if (room.equals("Lobby")){
            show_lobby();
        }
        if (room.equals("Basement")){
            show_basement();
        }
    }

    private void show_exit() {
        greeting.setText(R.string.exit);
    }

    private void show_basement() {
        greeting.setText(R.string.basement);
    }

    private void show_lobby() {
        greeting.setText(R.string.lobby);
    }

    private String pickRoom(String direction, String room) {
        //north.setText("north");
        //south.setText("south");
        //west.setText("west");

        if (room.equals("Lobby")){
            if(direction.equals("north")){
                return "Basement";
            }else if (direction.equals("south")){
                return "Exit";
            }else{
                greeting.setText(R.string.wrong_direction);
                return "Lobby";
            }
        }
        if (room.equals("Basement")){
            if(direction.equals("south")){
                return "Lobby";
            }else{
                greeting.setText(R.string.wrong_direction);
                return "Basement";
            }
        }
        return room;
    }
}
