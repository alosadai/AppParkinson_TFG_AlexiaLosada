package cat.tecnocampus.tfg.alexia.losada.appparkinson.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Touch {

    @PrimaryKey
    private int id;
    private int logId;
    private float axisX;
    private float axisY;

    public Touch(){}

    public Touch(int id, int logId, float axisX, float axisY) {
        this.id = id;
        this.logId = logId;
        this.axisX = axisX;
        this.axisY = axisY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public float getAxisX() {
        return axisX;
    }

    public void setAxisX(float axisX) {
        this.axisX = axisX;
    }

    public float getAxisY() {
        return axisY;
    }

    public void setAxisY(float axisY) {
        this.axisY = axisY;
    }
}
