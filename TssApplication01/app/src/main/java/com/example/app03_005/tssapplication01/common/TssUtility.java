package com.example.app03_005.tssapplication01.common;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;

/**
 * Created by APP03-005 on 2016/12/25.
 */

public class TssUtility {

    private final String TAG = this.getClass().getSimpleName();


    public static String getInternalStorageText(Context context){
        InputStream inputStream;
        String text = "";
        try {
            inputStream = context.openFileInput(TssConstant.INTERNAL_STORAGE_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = bufferedReader.readLine()) != null){
                text += line;
            }
        } catch (Exception e){
            Log.d("",e.toString());
        }
        return text;
    }


    public static void writeInternalStorage(Context context, String text){
        File file = new File(context.getFilesDir(), TssConstant.INTERNAL_STORAGE_FILE_NAME);
        if (!file.exists()) {
            Log.d("","file not found : " + TssConstant.INTERNAL_STORAGE_FILE_NAME);
        }

        FileOutputStream fileOutputStream;
        try{
            fileOutputStream = context.openFileOutput(TssConstant.INTERNAL_STORAGE_FILE_NAME, context.MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
        } catch (Exception e){
            Log.d("", e.toString());
        }

    }


}
