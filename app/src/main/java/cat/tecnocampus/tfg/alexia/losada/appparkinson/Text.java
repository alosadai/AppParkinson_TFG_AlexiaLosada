package cat.tecnocampus.tfg.alexia.losada.appparkinson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Text extends AppCompatActivity {

    TextView text;
    Button nextButton;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        text = findViewById(R.id.text);
        nextButton = findViewById(R.id.next);
        layout = findViewById(R.id.back_layout);

        if(type.equals("diagnosis")){
            text.setText(getResources().getString(R.string.explaining));
        }
        else{
            text.setText(getResources().getString(R.string.thanks));
            layout.setVisibility(View.INVISIBLE);

        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circle();
            }
        });
    }

    private void circle(){
        Intent intentC = new Intent(this, Circle.class);
        startActivity(intentC);
        finish();
    }
}