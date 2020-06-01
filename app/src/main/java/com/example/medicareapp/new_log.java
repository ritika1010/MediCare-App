package com.example.medicareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicareapp.DatabaseHelper;
import com.example.medicareapp.Log_data;
import com.example.medicareapp.MainActivity;
import com.example.medicareapp.R;
import com.example.medicareapp.ui.log.LogFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class new_log extends AppCompatActivity {
    Log_data p;
    DatabaseHelper db;
    public List<Log_data> logDataList;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);
        db=new DatabaseHelper(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_newLog);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        logDataList=new ArrayList<>();

        db=new DatabaseHelper(this);
//db.delete_db();

        Log_data log_data[]=db.give_display("log_data");
        Log.e("length", String.valueOf(log_data.length));
        for(int i=0;i<log_data.length;i++)
        {
            if(log_data[i]==null)
            {
                break;
            }
            Log_data pr=log_data[i];
            Log.e("list",pr.getLog_name());
            logDataList.add(pr);
        }

        //creating recyclerview adapter
        final LogAdapter adapter = new LogAdapter(this, logDataList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new LogAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String temp=logDataList.get(position).ifClicked();
                int id=logDataList.get(position).getId();
                Log.e("id_newlog", String.valueOf(id));
                if(db.search(id))
                {
                    Log.e("entered", "p1");
                    func(true);
                }
                else
                {
                    Log.e("false", "p1");
                    func(false);

                }



            }
        });

    }

    public void func(boolean a)
    {
        if(a==true)
        {
            Toast.makeText(this,"Added successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Already Exists",Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(this, MainActivity.class);
       startActivity(i);
        finish();

    }
}

//    public void p_1(View view)
//    {
//        if(db.search(1)) {
//            Log.e("entered", "p1");
//        }else
//        {
//            Log.e("false", "p1");
//            Toast.makeText(this,"älready Exists",Toast.LENGTH_LONG).show();
//        }
//
//        Intent i = new Intent(this, MainActivity.class);
//        startActivity(i);
//        finish();
//
//    }
//    public void p_2(View view)
//    {
//       if(db.search(2)) {
//            Log.e("entered", "p2");
//        }else
//        {
//            Toast.makeText(this,"älready Exists",Toast.LENGTH_LONG).show();
//            Log.e("false", "p2");
//        }
//        Intent i = new Intent(this, MainActivity.class);
//        startActivity(i);
//        finish();
//    }
//    public void p_3(View view)
//    {
//        if(db.search(3)) {
//            Log.e("entered", "p3");
//        }else
//        {
//            Log.e("false", "p3");
//            Toast.makeText(this,"älready Exists",Toast.LENGTH_LONG).show();
//        }
//        Intent i = new Intent(this, MainActivity.class);
//        startActivity(i);
//        finish();
//    }


