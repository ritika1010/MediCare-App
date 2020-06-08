package com.example.medicareapp.ui.log;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.medicareapp.DatabaseHelper;
import com.example.medicareapp.LogAdapter;
import com.example.medicareapp.LogModel;
import com.example.medicareapp.LogNewEntry;
import com.example.medicareapp.R;
import com.example.medicareapp.measurements.BloodGlucoseActivity;
import com.example.medicareapp.measurements.BloodGroupActivity;
import com.example.medicareapp.measurements.BloodPressureActivity;
import com.example.medicareapp.measurements.ExerciseActivity;
import com.example.medicareapp.measurements.FoodIntakeActivity;
import com.example.medicareapp.measurements.HeartRateActivity;
import com.example.medicareapp.measurements.HeightActivity;
import com.example.medicareapp.measurements.Spo2Activity;
import com.example.medicareapp.measurements.SymptomsActivity;
import com.example.medicareapp.measurements.Temperature2Activity;
import com.example.medicareapp.measurements.Thyroid2Activity;
import com.example.medicareapp.measurements.WeightActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LogFragment extends Fragment {
    public List<LogModel> logDataList;

    DatabaseHelper db;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_log, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        logDataList = new ArrayList<>();

        db = new DatabaseHelper(getActivity());

        LogModel logModels[] = db.give_display("current_log");
        Log.e("length", String.valueOf(logModels.length));
        for (int i = 0; i < logModels.length; i++) {
            if (logModels[i] == null) {
                break;
            }
            LogModel logModel = logModels[i];
            Log.e("products", logModel.getLog_name());
            logDataList.add(logModel);
        }

        //creating recyclerview adapter
        final LogAdapter adapter = new LogAdapter(getActivity(), logDataList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new LogAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String temp = logDataList.get(position).ifClicked();
                int id = logDataList.get(position).getId();
                db.recent_used(id);
                clicked(id);
                adapter.notifyItemChanged(position);
            }
        });
        ImageView plus = root.findViewById(R.id.add_log_button);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LogNewEntry.class);
                //i.putExtra("sampleObject", (Serializable) p);
                startActivity(i);
            }
        });
        return root;
    }


    public void clicked(int t) {

        Intent i;
        switch (t) {
            case 1:
                i = new Intent(getActivity(), HeightActivity.class);
                startActivity(i);
                break;

            case 2:
                i = new Intent(getActivity(), WeightActivity.class);
                startActivity(i);
                break;

            case 3:
                i = new Intent(getActivity(), BloodPressureActivity.class);
                startActivity(i);
                break;

            case 4:
                i = new Intent(getActivity(), HeartRateActivity.class);
                startActivity(i);
                break;

            case 5:
                i = new Intent(getActivity(), BloodGlucoseActivity.class);
                startActivity(i);
                break;

            case 6:
                i = new Intent(getActivity(), ExerciseActivity.class);
                startActivity(i);
                break;

            case 7:
                i = new Intent(getActivity(), FoodIntakeActivity.class);
                startActivity(i);
                break;

            case 8:
                i = new Intent(getActivity(), Temperature2Activity.class);
                startActivity(i);
                break;

            case 9:
                i = new Intent(getActivity(), SymptomsActivity.class);
                startActivity(i);
                break;

            case 10:
                i = new Intent(getActivity(), Spo2Activity.class);
                startActivity(i);
                break;

            case 11:
                i = new Intent(getActivity(), Thyroid2Activity.class);
                startActivity(i);
                break;

            case 12:
                i = new Intent(getActivity(), BloodGroupActivity.class);
                startActivity(i);
                break;


            default:
                Toast.makeText(getActivity(), "No activity present", Toast.LENGTH_LONG).show();

        }

    }

}
