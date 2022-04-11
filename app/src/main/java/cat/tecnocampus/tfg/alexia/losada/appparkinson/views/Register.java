package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.User;

public class Register extends AppCompatActivity {

    private CheckBox showPassword;
    private EditText name, surname, email, password;
    private Button registerButton;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        showPassword = findViewById(R.id.show_password);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.register_button);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                showPassword(isChecked);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register();
            }
        });


    }

    private void register(){
        String sname = name.getText().toString();
        String ssurname = surname.getText().toString();
        String spassword = password.getText().toString();
        String semail = email.getText().toString();

        if (sname.matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
            return;
        } else if(ssurname.matches("")){
            Toast.makeText(this, "You did not enter a surname", Toast.LENGTH_SHORT).show();
            return;
        } else if(spassword.matches("")){
            Toast.makeText(this, "You did not enter a password", Toast.LENGTH_SHORT).show();
            return;
        } else if(semail.matches("")||!semail.matches(emailPattern)){
            Toast.makeText(this, "You did not enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            auth.createUserWithEmailAndPassword(semail,spassword)
                    .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, "ERROR",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(Register.this, "Usuari creat amb UID ="+auth.getCurrentUser().getUid(),Toast.LENGTH_LONG).show();
                                createUser(semail, sname, ssurname, auth.getCurrentUser().getUid());
                            }
                        }
                    });}

        }


    private void createUser(String semail, String sname, String ssurname, String uid) {
        System.out.println("Creant usuari");
        User user = new User(semail, sname, ssurname);
        firebaseFirestore.collection("user").document(uid).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        System.out.println("Success");
                        Intent intent = new Intent(Register.this, Home.class);
                        intent.putExtra("name", sname);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void showPassword(Boolean isChecked){
        if(isChecked){
            //Show password
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            //Hide password
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}