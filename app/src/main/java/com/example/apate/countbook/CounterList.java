package com.example.apate.countbook;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Akash on 2017-09-30.
 */

public class CounterList {
    public static ArrayList<Counter> counterList = new ArrayList<Counter>();
    private static final String FILENAME = "file.sav";

    public static void load(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type typeList = new TypeToken<ArrayList<Counter>>(){}.getType();
            counterList = gson.fromJson(in, typeList);
        } catch(FileNotFoundException e) {
            counterList = new ArrayList<Counter>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counterList, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
