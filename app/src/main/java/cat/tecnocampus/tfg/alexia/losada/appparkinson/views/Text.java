package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;

public class Text extends AppCompatActivity {

    TextView text;
    Button nextButton;
    LinearLayout layout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        text = findViewById(R.id.text);
        nextButton = findViewById(R.id.next);
        layout = findViewById(R.id.back_layout);

        if(type.equals("diagnosis")){
            layout.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
            text.setText(getResources().getString(R.string.explaining));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                text.setTextAppearance(R.style.text);
            }
        }
        else if(type.equals("dades")){
            layout.setVisibility(View.GONE);
            nextButton.setVisibility(View.VISIBLE);
            text.setText(getResources().getString(R.string.permisos));
            text.setTextSize(18);
            text.setTextColor(R.color.black);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                text.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            }
            text.setTypeface(null, Typeface.NORMAL);
            text.setAllCaps(false);
        }
        else{
            layout.setVisibility(View.GONE);
            nextButton.setVisibility(View.GONE);
            text.setText(getResources().getString(R.string.thanks));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                text.setTextAppearance(R.style.text);
            }

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String touchId = intent.getStringExtra("touchId");
                    System.out.println("TOUCH ID" + touchId);
                    Intent intentC = new Intent(Text.this, Feelings.class);
                    intentC.putExtra("touchId", touchId);
                    startActivity(intentC);
                    finish();
                }
            }, 3500);

        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("diagnosis")){
                    Intent intentC = new Intent(Text.this, Circle.class);
                    startActivity(intentC);
                    finish();
                }
                else if(type.equals("dades")){
                    Intent intentC = new Intent(Text.this, Home.class);
                    startActivity(intentC);
                    finish();
                }
            }
        });
    }
}