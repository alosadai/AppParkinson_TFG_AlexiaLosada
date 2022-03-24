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
import android.widget.Toast;

public class Register extends AppCompatActivity {

    CheckBox showPassword;
    EditText name, surname, email, password;
    Button registerButton;

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
            Intent intent = new Intent(this, Home.class);
            intent.putExtra("name", sname);
            startActivity(intent);
        }
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