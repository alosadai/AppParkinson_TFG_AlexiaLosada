package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.DailyLog;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.Touch;

public class Circle extends AppCompatActivity {

    private View elipse;
    private List<String> touchs;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        elipse = findViewById(R.id.ellipse_1);

        touchs = new ArrayList<String>();

        firebaseFirestore = FirebaseFirestore.getInstance();

        elipse.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                String c = "x: " + e.getX() + " y: "+ e.getY();
                System.out.println(c);
                touchs.add(c);
                return true;
            }
        });

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                addTouchToBbdd();
            }
        }, 15000);
    }

    private void addTouchToBbdd(){
        Touch t = new Touch(touchs);
        firebaseFirestore.collection("touchs").add(t)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intentC = new Intent(Circle.this, Feelings.class);
                        intentC.putExtra("touchId", documentReference.getId());
                        startActivity(intentC);
                        finish();
                    }
                });
    }

}