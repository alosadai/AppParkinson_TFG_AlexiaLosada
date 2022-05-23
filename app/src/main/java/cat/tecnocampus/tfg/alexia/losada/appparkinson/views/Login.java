package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;

public class Login extends AppCompatActivity {

    private CheckBox showPassword;
    private EditText email, password;
    private Button loginButton;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        showPassword = findViewById(R.id.show_password);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        preferences = this.getPreferences(Context.MODE_PRIVATE);
        editor = preferences.edit();

        firebaseAuth = firebaseAuth.getInstance();

        revisarCredencials();


        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                showPassword(isChecked);
            }
        });

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });
    }

    private void revisarCredencials() {
        String semail = this.preferences.getString("Email", " ");
        String spassword = this.preferences.getString("Pswd", " ");
        if(!semail.equals(" ")){
            email.setText(semail);
            password.setText(spassword);
        }
    }

    private void guardarCredencials(String email, String password) {
        editor.putString("Email", email);
        editor.putString("Pswd", password);
        editor.apply();
    }

    private void login(){
        String semial = email.getText().toString();
        String spassword = password.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(semial, spassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                            guardarCredencials(semial, spassword);
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Login.this, R.string.AuthFail,
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }


    private void showPassword(Boolean isChecked){
        if(isChecked){
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private void updateUI(FirebaseUser user) {

    }
}