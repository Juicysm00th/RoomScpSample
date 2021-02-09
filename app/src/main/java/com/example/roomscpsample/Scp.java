package com.example.roomscpsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "scp_table")
public class Scp {
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "scp")
        private String mScp;

        public Scp(@NonNull String scp){this.mScp = scp;}

        public String getScp(){return  this.mScp;}
}
