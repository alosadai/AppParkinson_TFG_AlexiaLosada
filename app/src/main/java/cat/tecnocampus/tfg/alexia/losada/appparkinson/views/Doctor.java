package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.utils.Utils;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.User;

public class
Doctor extends AppCompatActivity {

    private LinearLayout layout;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userId;
    private Button validar, goBack;
    private EditText email;
    private TextView textEmail, emailAct;
    private boolean firstDoctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        layout = findViewById(R.id.back_layout);
        validar = findViewById(R.id.validate);
        email = findViewById(R.id.email);
        textEmail = findViewById(R.id.textEmail);
        emailAct = findViewById(R.id.emailAct);
        goBack = findViewById(R.id.back_button);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();

        firstDoctor = false;

        infoEmail();

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doctorEmail();
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void doctorEmail(){
        String currentEmail = "";
        String semail = email.getText().toString();

        if(semail.matches("")||!semail.matches(Utils.EMAILPATTERN)){
            Toast.makeText(this, "You did not enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        modifyEmail(semail);
    }

    private void modifyEmail(String semail){
        DocumentReference docRef = firebaseFirestore.collection("user").document(userId);
        docRef.update("doctor", semail);
        infoEmail();
    }

    private void infoEmail(){
        DocumentReference docRef = firebaseFirestore.collection("user").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                if(user.getDoctor() == ""){
                    textEmail.setText(R.string.textNoEmail);
                    emailAct.setText(" ");
                } else {
                    textEmail.setText(R.string.textEmail);
                    emailAct.setText(user.getDoctor());
                }

            }
        });

    }


}