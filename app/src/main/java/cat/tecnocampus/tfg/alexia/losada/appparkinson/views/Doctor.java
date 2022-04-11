package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.User;

public class
Doctor extends AppCompatActivity {

    private LinearLayout layout;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userId;
    private Button validar;
    private EditText email;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        layout = findViewById(R.id.back_layout);
        validar = findViewById(R.id.validate);
        email = findViewById(R.id.email);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();

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
    }

    private void doctorEmail(){
        String currentEmail = "";
        String semail = email.getText().toString();

        if(semail.matches("")||!semail.matches(emailPattern)){
            Toast.makeText(this, "You did not enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        DocumentReference docRef = firebaseFirestore.collection("user").document(firebaseAuth.getCurrentUser().getUid());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                modifyEmail(user.getDoctor(), semail);
            }
        });


    }

    private void modifyEmail(String currentEmail, String semail){


        if(currentEmail  == ""){
            //TODO: Avis canvi email
            //openDialog(); no funciona
        }
        DocumentReference docRef = firebaseFirestore.collection("user").document(firebaseAuth.getCurrentUser().getUid());
        docRef.update("doctor", semail);
    }

    /* void openDialog() {
        DialogClassDoctor dialogClassDoctor = new DialogClassDoctor();
        dialogClassDoctor.show(getSupportFragmentManager(), "exemple dialog");
    }*/


}