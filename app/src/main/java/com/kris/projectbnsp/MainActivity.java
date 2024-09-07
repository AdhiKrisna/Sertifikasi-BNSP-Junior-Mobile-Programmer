package com.kris.projectbnsp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int[][] IMAGES = {
            {R.drawable.indo, R.drawable.malaysia},
            {R.drawable.laos, R.drawable.kamboja},
            {R.drawable.myanmar, R.drawable.filipina},
            {R.drawable.singapura, R.drawable.vietnam},
    };

    private static final String[][] JAWABAN = {
            {"indonesia", "malaysia"},
            {"laos", "kamboja"},
            {"myanmar", "filipina"},
            {"singapura", "vietnam"},
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout mainLayout = findViewById(R.id.main_tebak_gambar);

        for (int i = 0; i < IMAGES.length; i++) {
            addImageRow(mainLayout, IMAGES[i][0], IMAGES[i][1], JAWABAN[i][0], JAWABAN[i][1]);
        }

        Button button = findViewById(R.id.button_to_tdl);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TdlActicity.class);
            startActivity(intent);
        });
    }

    private void addImageRow(LinearLayout parent, int leftImageRes, int rightImageRes, String jawabanLeft, String jawabanRight) {
        View imageRow = getLayoutInflater().inflate(R.layout.row_image, parent, false);

        ImageView leftImageView = imageRow.findViewById(R.id.image_view_left);
        ImageView rightImageView = imageRow.findViewById(R.id.image_view_right);

        leftImageView.setImageResource(leftImageRes);
        rightImageView.setImageResource(rightImageRes);


        // tiap image view nya kita klik, buka activity tebak gambar
        leftImageView.setOnClickListener(v -> openTebakActivity(leftImageRes, jawabanLeft));
        rightImageView.setOnClickListener(v -> openTebakActivity(rightImageRes, jawabanRight));

        parent.addView(imageRow);
    }
    private void openTebakActivity(int imageRes, String jawaban) {
        Intent intent = new Intent(MainActivity.this, TebakActivity.class);
        intent.putExtra("imageResource", imageRes); // Mengirimkan resource gambar
        intent.putExtra("jawaban", jawaban); // Mengirimkan jawaban
        startActivity(intent);
    }
}


