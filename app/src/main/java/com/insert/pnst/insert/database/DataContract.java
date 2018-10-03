package com.insert.pnst.insert.database;
import android.provider.BaseColumns;

public final class DataContract {
    public DataContract(){}
    public static final class EmployEntry implements BaseColumns{
        public static final String TABLE_NAME="employee";
        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_EMPLOY_NAME="name";
        public static final String COLUMN_EMPLOY_GENDER="gender";
        public static final String COLUMN_EMPLOY_ROLL="roll";
        public static final String GENDER_male="Male";
        public static final String GENDER_female="Female";
    }
}
