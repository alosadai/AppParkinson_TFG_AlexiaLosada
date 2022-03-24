package cat.tecnocampus.tfg.alexia.losada.appparkinson.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.DailyLog;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.Touch;
import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.User;

@Database(entities={User.class, Touch.class, DailyLog.class},version=1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract TouchDao touchDao();
    public abstract DailyLogDao dailyLogDAO();

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "database-parkinson").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){

    }
}
