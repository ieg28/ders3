package com.ieg.ders3;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataBaseController {

    public enum DBError {SUCCESS, UNKNOW, DB_NULL, MODEL_NULL, OUT_INDEX}

    public static String DBFileName = "SMSDB.ser";
    //public static String AppFolderName = "SMS_READER";

    //private static String folderPath = Environment.getExternalStorageDirectory() + "/" + AppFolderName;
    //private static String filePath = folderPath + "/" + DBFileName;

    private ArrayList<SMSModel> smsDB;

    private Context context;

    public DataBaseController(Context context) throws Exception {
        this.context = context;

        if (importDB() != DBError.SUCCESS) {
            if (exportDB() != DBError.SUCCESS)
                throw new Exception("DB not create!!!");
        }
    }

    public DBError exportDB() {
        try {
            FileOutputStream fos = context.openFileOutput(DBFileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject((smsDB == null) ? new ArrayList<SMSModel>() : smsDB);
            os.close();
            fos.close();

            return DBError.SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return DBError.UNKNOW;
        }
    }

    public DBError importDB() {
        try {
            FileInputStream fis = context.openFileInput(DBFileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            smsDB = (ArrayList<SMSModel>) is.readObject();
            is.close();
            fis.close();

            return DBError.SUCCESS;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return DBError.UNKNOW;
        }
    }

    public DBError addSmsDB(SMSModel smsModel) {
        if (smsDB == null)
            return DBError.DB_NULL;

        if (smsModel == null)
            return DBError.MODEL_NULL;

        smsDB.add(smsModel);
        exportDB();

        return DBError.SUCCESS;
    }

    public DBError removeSmsDB(int index) {
        if (smsDB == null)
            return DBError.DB_NULL;

        if (index > smsDB.size() || index < 0)
            return DBError.OUT_INDEX;

        smsDB.remove(index);
        exportDB();

        return DBError.SUCCESS;
    }

    public ArrayList<SMSModel> getSmsDB() {
        return smsDB;
    }

    public DBError setSmsDB(ArrayList<SMSModel> smsDB) {
        this.smsDB = smsDB;
        exportDB();

        return DBError.SUCCESS;
    }
}
