package example.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        ivLogo = findViewById(R.id.ivLogo);
        tvName = findViewById(R.id.tvName);
        tvLocation = findViewById(R.id.tvLocation);
        tvFoundingDate = findViewById(R.id.tvFoundingDate);
        tvKeyFigures = findViewById(R.id.tvKeyFigures);
    }

    private void valuesInit() {
        Intent intent = getIntent();
        ivLogo.setImageResource(intent.getIntExtra("imageId", 0));
        tvName.setText(intent.getStringExtra("name"));
        tvLocation.setText(intent.getStringExtra("location"));
        tvFoundingDate.setText(intent.getStringExtra("foundingDate"));
        tvKeyFigures.setText(intent.getStringExtra("keyFigures"));
    }
}