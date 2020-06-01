package com.example.medicareapp.ui.log;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicareapp.DatabaseHelper;
import com.example.medicareapp.LogAdapter;
import com.example.medicareapp.Log_data;
import com.example.medicareapp.MainActivity;
import com.example.medicareapp.R;
import com.example.medicareapp.measurements.Blood_glucose;
import com.example.medicareapp.measurements.Blood_group;
import com.example.medicareapp.measurements.Blood_pressure;
import com.example.medicareapp.measurements.Exercise;
import com.example.medicareapp.measurements.Food_intake;
import com.example.medicareapp.measurements.Heart_rate;
import com.example.medicareapp.measurements.Height;
import com.example.medicareapp.measurements.Spo2;
import com.example.medicareapp.measurements.Symptoms;
import com.example.medicareapp.measurements.Temperature;
import com.example.medicareapp.measurements.Temperature_2;
import com.example.medicareapp.measurements.Thyroid;
import com.example.medicareapp.measurements.Thyroid_2;
import com.example.medicareapp.measurements.Weight;
import com.example.medicareapp.new_log;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LogFragment extends Fragment {
    public List<Log_data> logDataList;

    DatabaseHelper db;
    RecyclerView recyclerView;
    //private LogViewModel logViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      //  logViewModel =
                //ViewModelProviders.of(this).get(LogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_log, container, false);
       // final TextView textView = root.findViewById(R.id.heading);
//        logViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//              //  textView.setText(s);
//            }
//        });
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        logDataList=new ArrayList<>();

        db=new DatabaseHelper(getActivity());
//db.delete_db();

        Log_data log_data[]=db.give_display("current_log");
        Log.e("length", String.valueOf(log_data.length));
        for(int i=0;i<log_data.length;i++)
        {
            if(log_data[i]==null)
            {
                break;
            }
            Log_data pr=log_data[i];
            Log.e("products",pr.getLog_name());
            logDataList.add(pr);
        }

        //creating recyclerview adapter
        final LogAdapter adapter = new LogAdapter(getActivity(), logDataList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new LogAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String temp=logDataList.get(position).ifClicked();
                int id=logDataList.get(position).getId();
                db.recent_used(id);
                clicked(id);
                adapter.notifyItemChanged(position);
            }
        });
        ImageView plus=root.findViewById(R.id.add_log_button);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), new_log.class);
                //i.putExtra("sampleObject", (Serializable) p);
                startActivity(i);
            }
        });
        return root;
    }


    public void clicked(int t)
    {
//        Intent i = new Intent(this, ifClicked_page.class);
//        i.putExtra("string", t);
//        startActivity(i);
        Intent i;
        switch(t)
        {
            case 1:i = new Intent(getActivity(), Height.class);
            startActivity(i);
            break;

            case 2: i = new Intent(getActivity(), Weight.class);
            startActivity(i);
            break;

            case 3: i = new Intent(getActivity(), Blood_pressure.class);
            startActivity(i);
            break;

            case 4: i = new Intent(getActivity(), Heart_rate.class);
                startActivity(i);
                break;

            case 5: i = new Intent(getActivity(), Blood_glucose.class);
                startActivity(i);
                break;

            case 6: i = new Intent(getActivity(), Exercise.class);
                startActivity(i);
                break;

            case 7: i = new Intent(getActivity(), Food_intake.class);
                startActivity(i);
                break;

            case 8: i = new Intent(getActivity(), Temperature_2.class);
                startActivity(i);
                break;

            case 9: i = new Intent(getActivity(), Symptoms.class);
                startActivity(i);
                break;

            case 10: i = new Intent(getActivity(), Spo2.class);
                startActivity(i);
                break;

            case 11: i = new Intent(getActivity(), Thyroid_2.class);
                startActivity(i);
                break;

            case 12: i = new Intent(getActivity(), Blood_group.class);
                startActivity(i);
                break;


            default:
                Toast.makeText(getActivity(),"No activity present",Toast.LENGTH_LONG).show();

        }

    }

//    public void clear_db(View view)
//    {
//        Log.e("clear","done");
//        db.delete_db();
//        Intent i = new Intent(getActivity(), MainActivity.class);
//        //i.putExtra("sampleObject", (Serializable) p);
//        startActivity(i);
//
//        //finish();
//    }
}

