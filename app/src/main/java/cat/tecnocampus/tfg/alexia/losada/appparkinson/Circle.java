package cat.tecnocampus.tfg.alexia.losada.appparkinson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Circle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                feelings();
            }
        }, 15000);
    }

    private void feelings(){
        Intent intentC = new Intent(this, Feelings.class);
        startActivity(intentC);
        finish();
    }
}