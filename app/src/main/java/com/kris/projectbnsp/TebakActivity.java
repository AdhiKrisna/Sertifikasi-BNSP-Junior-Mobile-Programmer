package com.kris.projectbnsp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TebakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tebak);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.white, getTheme()));
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(TebakActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        setInit();
        checkAnswer();
    }

    private void setInit(){
        // Menerima resource gambar dan jawaban dari Intent
        int imageRes = getIntent().getIntExtra("imageResource", R.drawable.indo);
        ImageView imageDitebak = findViewById(R.id.image_ditebak);
        imageDitebak.setImageResource(imageRes);
    }

    private void checkAnswer(){
        // nyobain liat jawaban
//        EditText jawabanEditText = findViewById(R.id.edit_text_jawaban);
//        jawabanEditText.setHint(jawaban);
        String jawaban = getIntent().getStringExtra("jawaban");
//
        Button submitButton = findViewById(R.id.button_cek);
        TextView resultTextView = findViewById(R.id.result_text_view);
        submitButton.setOnClickListener(v -> {
            EditText jawabanEditText = findViewById(R.id.edit_text_jawaban);
            String inputJawaban = jawabanEditText.getText().toString().toLowerCase();
            if (inputJawaban.isEmpty()) {
                resultTextView.setText("Jawaban tidak boleh kosong");
                resultTextView.setVisibility(View.VISIBLE);
            } else {
                if (inputJawaban.equals(jawaban)) {
                    resultTextView.setText("Jawaban benar!");
                } else {
                    resultTextView.setText("Jawaban salah!");
                }
                resultTextView.setVisibility(View.VISIBLE); // nampilin hasil jawaban nyabner atau slah
            }
        });

    }
}