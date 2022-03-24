package cat.tecnocampus.tfg.alexia.losada.appparkinson.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.DailyLog;

@Dao
public interface DailyLogDao {

    @Query("SELECT * FROM dailylog")
    LiveData<List<DailyLog>> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser (DailyLog element);
}