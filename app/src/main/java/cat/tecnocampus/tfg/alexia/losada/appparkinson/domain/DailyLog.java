package cat.tecnocampus.tfg.alexia.losada.appparkinson.domain;


import java.util.Date;

public class DailyLog {

    private String userId;
    private String question;
    private String answer;
    private Date date;

    public DailyLog(){
    }

    public DailyLog(String userId, String question, String answer, Date date) {
        this.userId = userId;
        this.question = question;
        this.answer = answer;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
