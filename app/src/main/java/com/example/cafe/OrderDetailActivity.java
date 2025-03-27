package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USERNAME = "username";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_ADDITIVES = "additives";
    private static final String EXTRA_DRINK_TYPE = "drinktype";

    private TextView textViewWhatName;
    private TextView textViewWhatDrink;
    private TextView textViewWhatDrinkType;
    private TextView textViewWhatAdditives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        Intent intent = getIntent();
        textViewWhatName.setText(intent.getStringExtra(EXTRA_USERNAME));
        textViewWhatDrink.setText(intent.getStringExtra(EXTRA_DRINK));
        textViewWhatAdditives.setText(intent.getStringExtra(EXTRA_ADDITIVES));
        textViewWhatDrinkType.setText(intent.getStringExtra(EXTRA_DRINK_TYPE));

    }

    public static Intent newIntent(
            Context context,
            String username,
            String drink,
            String additives,
            String drinkType)
    {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_ADDITIVES, additives);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        return intent;
    }

    private void initViews() {
        textViewWhatName = findViewById(R.id.textViewWhatName);
        textViewWhatDrink = findViewById(R.id.textViewWhatDrink);
        textViewWhatDrinkType = findViewById(R.id.textViewWhatDrinkType);
        textViewWhatAdditives = findViewById(R.id.textViewWhatAdditives);
    }
}