package cat.tecnocampus.tfg.alexia.losada.appparkinson.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.R;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.DailyLog;

import java.util.Calendar;

public class Feelings extends AppCompatActivity {

    private Button answer1, answer2, answer3, answer4, answer5;
    private TextView question;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userId, touchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings);

        Intent intent = getIntent();
        touchId = intent.getStringExtra("touchId");

        answer1 = findViewById(R.id.answer_1);
        answer2 = findViewById(R.id.answer_2);
        answer3 = findViewById(R.id.answer_3);
        answer4 = findViewById(R.id.answer_4);
        answer5 = findViewById(R.id.answer_5);

        question = findViewById(R.id.com_et_sent);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu(answer1);
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu(answer2);
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu(answer3);
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu(answer4);
            }
        });
        answer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu(answer5);
            }
        });

    }

    private void menu(Button answer){
        DailyLog dailyLog = new DailyLog(userId, question.getText().toString() , answer.getText().toString(), Calendar.getInstance().getTime());
        firebaseFirestore.collection("dailyLog").add(dailyLog)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        addLogToTouch(documentReference.getId());
                        finish();
                    }
                });
    }

    private void addLogToTouch(String logId){
        DocumentReference docRef = firebaseFirestore.collection("touchs").document(touchId);
        docRef.update("logId", logId);
    }

}