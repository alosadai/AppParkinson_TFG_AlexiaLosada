package cat.tecnocampus.tfg.alexia.losada.appparkinson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView welcomeBack;
    Button diagnosisButton, doctorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        welcomeBack = findViewById(R.id.welcome_back);
        diagnosisButton = findViewById(R.id.diagnosis_button);
        doctorButton = findViewById(R.id.doctor_button);

        if(name == null){
            welcomeBack.setText(getResources().getString(R.string.welcome_back)+ "!");
        }
        else{
            welcomeBack.setText(getResources().getString(R.string.welcome_back)+ ", "+name);
        }

        diagnosisButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diagnosis();
            }
        });
        doctorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doctor();
            }
        });

    }


    private void diagnosis(){
        Intent intentD = new Intent(this, Text.class);
        intentD.putExtra("type", "diagnosis");
        startActivity(intentD);
    }

    private void doctor(){
        Intent intentD = new Intent(this, Doctor.class);
        startActivity(intentD);
    }
}