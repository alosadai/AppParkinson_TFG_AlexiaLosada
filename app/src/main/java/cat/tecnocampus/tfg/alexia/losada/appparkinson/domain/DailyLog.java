package cat.tecnocampus.tfg.alexia.losada.appparkinson.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class DailyLog {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String question;
    private String answer;
    private Date date;

    public DailyLog(){
    }

    public DailyLog(int userId, String question, String answer, Date date) {
        this.userId = userId;
        this.question = question;
        this.answer = answer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
