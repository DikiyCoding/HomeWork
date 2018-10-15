package example.homework.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import example.homework.R;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private TextView tvName, tvLocation, tvFoundingDate, tvKeyFigures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        viewsInit();
        valuesInit();
    }

    private void viewsInit() {
        ivLogo = findViewById(R.id.iv_logo);
        tvName = findViewById(R.id.tv_name);
        tvLocation = findViewById(R.id.tv_location);
        tvFoundingDate = findViewById(R.id.tv_founding_date);
        tvKeyFigures = findViewById(R.id.tv_key_figures);
    }

    private void valuesInit() {
        Intent intent = getIntent();
        ivLogo.setImageResource(intent.getIntExtra("imageId", 0));
        tvName.setText(intent.getStringExtra("name"));
        tvLocation.setText(intent.getStringExtra("location"));
        tvFoundingDate.setText(intent.getIntExtra("foundingDate", 0) + "");
        tvKeyFigures.setText(intent.getStringExtra("keyFigures"));
    }
}
