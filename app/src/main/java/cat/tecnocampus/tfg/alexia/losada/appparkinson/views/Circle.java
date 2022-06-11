package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.Touch;


public class Circle extends AppCompatActivity {

    private View elipse;
    private List<String> touchs;
    FirebaseFirestore firebaseFirestore;
    private TextView counter;
    private String startTime;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        elipse = findViewById(R.id.ellipse_2);
        counter = findViewById(R.id.counter);

        touchs = new ArrayList<String>();

        firebaseFirestore = FirebaseFirestore.getInstance();

        sdf = new SimpleDateFormat("HH:mm:ss.SSS");

        startTime = sdf.format(new Date());

        elipse.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                String currentTime = sdf.format(new Date());
                System.out.println(startTime);
                System.out.println(currentTime);
                String c = e.getX() + "  "+ e.getY();
                System.out.println(c);
                if(touchs.size()>=1){
                    String d1 = touchs.get(touchs.size()-1).toString();
                    String[] s1 = d1.split("\\s");
                    String[] s2 = c.split("\\s");
                    if(!(s1[0].equals(s2[0]) && s1[1].equals(s2[1]))){
                        touchs.add(c+ "  "+ currentTime);
                    }
                } else{
                    touchs.add(c+ "  " + currentTime);
                }
                return false;
            }
        });


        CountDownTimer countDownTimer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long l) {
                counter.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                addTouchToBbdd();
            }
        }.start();

    }


    private void addTouchToBbdd(){
        Touch t = new Touch(touchs);
        firebaseFirestore.collection("touchs").add(t)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intentC = new Intent(Circle.this, Text.class);
                        intentC.putExtra("touchId", documentReference.getId());
                        intentC.putExtra("type", "feelings");
                        startActivity(intentC);
                        finish();
                    }
                });
    }

}