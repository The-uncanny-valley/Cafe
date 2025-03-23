package com.example.cafe;

import android.app.Activity;
import android.content.Context; // for hiding keyboard
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent; // for hiding keyboard
import android.view.View;
import android.view.inputmethod.InputMethodManager; // for hiding keyboard
import android.widget.Button;
import android.widget.EditText; // chatGPT
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    launchNextScreen(username, password);
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            getString(R.string.error_fields_empty),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

    }

    private void launchNextScreen(String username, String password) {
        Intent intent = new Intent(MainActivity.this, MakeOrderActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    private void initViews() { // created to make code clear
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view instanceof EditText) {
            hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

