package cat.tecnocampus.tfg.alexia.losada.appparkinson.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

public class Touch {

    private String logId;
    private List<String> touchs;

    public Touch(){}

    public Touch(List<String> touchs){
        this.logId = "";
        this.touchs = touchs;
    }

    public Touch(String logId, List<String> touchs){
        this.logId = "";
        this.touchs = touchs;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public void setTouchs(List<String> touchs){
        this.touchs = touchs;
    }

    public List<String> getTouchs(){
        return touchs;
    }


}
