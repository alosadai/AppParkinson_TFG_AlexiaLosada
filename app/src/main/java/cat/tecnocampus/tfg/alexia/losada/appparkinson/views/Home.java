package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.User;

public class Home extends AppCompatActivity {

    TextView welcomeBack;
    Button diagnosisButton, doctorButton, logoutButton, exitButton;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeBack = findViewById(R.id.welcome_back);
        diagnosisButton = findViewById(R.id.diagnosis_button);
        doctorButton = findViewById(R.id.doctor_button);
        logoutButton = findViewById(R.id.logout);
        exitButton = findViewById(R.id.exit);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        setWelcome();

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
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });


    }


    private void setWelcome() {
        String id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("user").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                welcomeBack.setText(getResources().getString(R.string.welcome_back)+ ", "+user.getName());
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
        Intent intentD = new Intent(this, MainActivity.class);
        startActivity(intentD);
        finish();
    }
}