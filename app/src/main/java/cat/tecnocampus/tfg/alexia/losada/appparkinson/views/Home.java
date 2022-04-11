package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;

public class Home extends AppCompatActivity {

    TextView welcomeBack;
    Button diagnosisButton, doctorButton, logoutButton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        welcomeBack = findViewById(R.id.welcome_back);
        diagnosisButton = findViewById(R.id.diagnosis_button);
        doctorButton = findViewById(R.id.doctor_button);
        logoutButton = findViewById(R.id.logout);

        firebaseAuth = FirebaseAuth.getInstance();

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
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
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

    private void logout(){
        firebaseAuth.signOut();
        finish();
    }
}