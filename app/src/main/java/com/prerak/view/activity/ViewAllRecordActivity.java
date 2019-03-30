package com.prerak.view.activity;

import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import com.prerak.R;
import com.prerak.database.dao.CURDOperation;
import com.prerak.database.db.DB;
import com.prerak.databinding.ActivityViewAllRecordBinding;
import com.prerak.view.adapter.ViewAllRecordAdapter;

public class ViewAllRecordActivity extends AppCompatActivity {
    private ActivityViewAllRecordBinding mBinding;
    private DB appDatabase;
    private CURDOperation curdOperation;
    private ViewAllRecordAdapter mAdapter;
    public static Spinner spMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_all_record);
        appDatabase = Room.databaseBuilder(this, DB.class, DB.DB_NAME)
                .allowMainThreadQueries().build();
        curdOperation = new CURDOperation(this);
        spMonth = findViewById(R.id.spMonth);
        if (mBinding.tab.getTabCount() != 2) {
            mBinding.tab.addTab(mBinding.tab.newTab().setText("Saving"));
            mBinding.tab.addTab(mBinding.tab.newTab().setText("Loan"));
            mBinding.tab.setTabGravity(TabLayout.GRAVITY_FILL);

            mAdapter = new ViewAllRecordAdapter(getSupportFragmentManager(), mBinding.tab.getTabCount());
            mBinding.viewPager.setAdapter(mAdapter);
            mBinding.viewPager.setOffscreenPageLimit(1);
            mBinding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mBinding.tab));
            mBinding.tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    mBinding.viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }
}
