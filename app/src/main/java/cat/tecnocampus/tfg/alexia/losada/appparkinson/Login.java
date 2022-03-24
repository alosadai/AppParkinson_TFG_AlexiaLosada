package cat.tecnocampus.tfg.alexia.losada.appparkinson;

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

public class Login extends AppCompatActivity {

    CheckBox showPassword;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showPassword = findViewById(R.id.show_password);
        password = findViewById(R.id.password);

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

    private void login(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
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