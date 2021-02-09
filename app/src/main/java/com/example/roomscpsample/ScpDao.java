package com.example.roomscpsample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ScpDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Scp scp);

    @Query("DELETE FROM scp_table")
    void deleteAll();

    @Query("SELECT * from scp_table ORDER BY scp ASC")
    LiveData<List<Scp>> getAllScps();

    @Query("SELECT * from scp_table LIMIT 1")
    Scp[] getAnyScp();
    @Delete
    void deleteScp(Scp scp);
}
