package com.example.mohan.bmiapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class BmiHistory extends AppCompatActivity {
    String email;
    List<BMI> bmis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_history);

        email = getIntent().getExtras().getString("email");
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        bmis = helper.getAllBmiValues(email);

        ListView listView = findViewById(R.id.listView);
        CustomeAdapter adapter = new CustomeAdapter();
        listView.setAdapter(adapter);
    }
    class CustomeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return bmis.size();
        }

        @Override
        public Object getItem(int i) {
            BMI bmi = bmis.get(i);
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.bmi_layout, null);
            TextView heightView = view.findViewById(R.id.heightTextView);
            TextView weightView = view.findViewById(R.id.weightTextView);
            TextView bmiView = view.findViewById(R.id.bmiTextView);
            TextView dateView = view.findViewById(R.id.dateTextView);
            heightView.setText(bmis.get(i).height.toString());
            weightView.setText(bmis.get(i).weight.toString());
            bmiView.setText(bmis.get(i).bmi.toString());
            dateView.setText(bmis.get(i).date.toString());
            return view;
        }
    }
}
