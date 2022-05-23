package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;

public class MainActivity extends AppCompatActivity {

    private Button registerButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registrarse();
            }
        });
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });
    }



    private void registrarse(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }

    private void login(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}