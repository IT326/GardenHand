package com.example.gardenhand;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchView extends AppCompatActivity {

    Button searchButton;
    EditText textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        textBox = (EditText) findViewById(R.id.vendor_text);
        searchButton = (Button) findViewById(R.id.vendor_button);
        
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = textBox.getText().toString();
                if (item.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No item listed", Toast.LENGTH_SHORT).show();
                } else {
                    String searchURL = "https://www.amazon.com/s?url=search-alias%3Daps&field-keywords=";

                    String[] s = item.split("\\s+");
                    searchURL.concat(TextUtils.join("+", s));

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(searchURL));
                    startActivity(browserIntent);
                }
            }
        });
    }
}