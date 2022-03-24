package cat.tecnocampus.tfg.alexia.losada.appparkinson.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cat.tecnocampus.tfg.alexia.losada.appparkinson.domain.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser (User element);
}