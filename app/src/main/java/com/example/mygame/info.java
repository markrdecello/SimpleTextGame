package com.example.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class info extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.mygame.EXTRA_TEXT";
    EditText name;
    Button submit;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        name = findViewById(R.id.enterName);
        submit = findViewById(R.id.nameBtn);

        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)){
                    String checkEmpty = name.getText().toString();
                    if(checkEmpty.equals("")){
                        Toast.makeText(info.this, "Enter a name you fool!", Toast.LENGTH_SHORT).show();
                    } else{
                        openInstruct();
                    }
                }
                return false;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEmpty = name.getText().toString();
                if(checkEmpty.equals("")){
                    Toast.makeText(info.this, "Enter a name you fool!", Toast.LENGTH_SHORT).show();
                } else{
                    openInstruct();
                }
            }
        });

    }

    private void openInstruct() {
        String input = name.getText().toString();
        userName = input;
        Intent intent = new Intent(this, instructions.class);
        intent.putExtra(EXTRA_TEXT, userName);
        startActivity(intent);
    }
}
