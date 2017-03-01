package edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.io.FilterInputStream;  //Files connection to SharedPref
import java.io.File;   //Files connection to SharedPref
import android.util.Log;  //Files connection to SharedPref

/**
 * Created by m_alrajab on 2/22/17.
 */

public class Utils {

    public static final String SHARED_PREF_FILENAME="edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.SHAREDFILE1";
    public static final String KEY_TITLE="Title_";
    public static final String KEY_BODY="Body_";
    //We need to create the Util link Files to SharedPref

//The lines below will get files from sharedpref


//Filename arrays

    public static String[] getListFromFiles(Context context){
        ArrayList<String> lstOfFilesInMemeory = new ArrayList<>();
        try{
            File filesDir = context.getFilesDir();
            File[] files = filesDir.listFiles();
            for(int i=1;i<files.length;i++)
                lstOfFilesInMemeory.add(files[i].getName().toString());
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        String[] list = lstOfFilesInMemeory.toArray(new String[lstOfFilesInMemeory.size()]);
        return list;
    }

    //All the input texts will be pulled from FIle
    public static String getFileByName(Context context, String filename){
        String tempStr = "";
        try{
            FileInputStream inputtStream = context.openFileInput(filename.replace(" ", ""));
            int c;
            while((c=inputtStream.read())!=-1){
                tempStr+=Character.toString((char) c);
            }
            inputtStream.close();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        return tempStr;
    }


    public static String[] getListFromSP(Context context, String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_FILENAME,
                Context.MODE_PRIVATE);
        Map<String, ?> map=sharedPreferences.getAll();
        ArrayList<String> lst= new ArrayList<>();
        for(String str:map.keySet()){
            if(str.startsWith(key))
                lst.add((String)map.get(str));
        }
        return lst.toArray(new String[lst.size()]);
    }
}
// we are creating a list that we are getting from the shared preferences.
// key is pulled from string and linked to map we past the title from getPref to string key
//