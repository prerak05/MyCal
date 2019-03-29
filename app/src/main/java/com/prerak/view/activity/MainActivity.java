package com.prerak.view.activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.prerak.R;
import com.prerak.database.dao.CURDOperation;
import com.prerak.database.db.DB;
import com.prerak.database.table.UserData;
import com.prerak.databinding.ActivityMainBinding;
import com.prerak.util.AppUtil;
import com.prerak.util.BaseDate;
import com.prerak.util.BaseTime;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String strType, strAmount, strDate, strInterest;
    private DB appDatabase;
    private CURDOperation curdOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        appDatabase = Room.databaseBuilder(this, DB.class, DB.DB_NAME)
                .allowMainThreadQueries().build();
        curdOperation = new CURDOperation(this);
        init();
    }

    private void init() {
        binding.etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.getInstance(MainActivity.this).chooseDateWithoutPreviousRestriction(binding.etDate, new BaseDate() {
                    @Override
                    public void onDateSelect(final String date, EditText editText) {
                        editText.setText(date);
                    }
                });
            }
        });

        binding.spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strType = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAmount = binding.etAmount.getText().toString();
                strDate = binding.etDate.getText().toString();
                strInterest = binding.etInterest.getText().toString();

                if (strDate == null || strDate.equals("")) {
                    Toast.makeText(MainActivity.this, "Choose your date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (strType == null || strType.equals("Select your type")) {
                    Toast.makeText(MainActivity.this, "Select your type", Toast.LENGTH_SHORT).show();
                    return;
                } else if (strInterest == null || strInterest.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter Interest", Toast.LENGTH_SHORT).show();
                    return;
                } else if (strAmount == null || strAmount.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (strAmount != null && strAmount.length() <= 2) {
                    Toast.makeText(MainActivity.this, "Enter 3 Digit Amount", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    curdOperation.insertData(new UserData(binding.etDate.getText().toString(), strType, strAmount, strInterest));

/*
                    if (strType != null && strType.equals("Saving")){
                        curdOperation.insertData(new UserData(binding.etDate.getText().toString(), strType, strAmount,"10"));
                    }else if (strType != null && strType.equals("Loan")){
                        curdOperation.insertData(new UserData(binding.etDate.getText().toString(), strType, strAmount,"12"));
                    }
*/
                    binding.etDate.setText("");
                    binding.etAmount.setText("");
                    binding.spnType.setSelection(0);
                    binding.etInterest.setText("");
                    Toast.makeText(MainActivity.this, "Record inserted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.tvAllRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAllRecordActivity.class);
                startActivity(intent);
            }
        });
    }
}
