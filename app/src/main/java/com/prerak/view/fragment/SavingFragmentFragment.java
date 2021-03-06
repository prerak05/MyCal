package com.prerak.view.fragment;


import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.prerak.R;
import com.prerak.database.dao.CURDOperation;
import com.prerak.database.db.DB;
import com.prerak.database.table.TotalSum;
import com.prerak.database.table.UserData;
import com.prerak.databinding.FragmentSavingBinding;
import com.prerak.view.activity.ViewAllRecordActivity;
import com.prerak.view.adapter.SavingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavingFragmentFragment extends Fragment {
    private DB appDatabase;
    private CURDOperation curdOperation;
    private FragmentSavingBinding mBinding;
    private List<UserData> userDataList = new ArrayList<>();
    private UserData userData;
    private SavingAdapter mAdapter;
    private TotalSum totalSum;
    private String strMonth, strMonthNo = "01";


    public SavingFragmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_saving, container, false);
        appDatabase = Room.databaseBuilder(getContext(), DB.class, DB.DB_NAME)
                .allowMainThreadQueries().build();
        curdOperation = new CURDOperation(getActivity());

        init();
        setupSwipeToRefresh();
        return mBinding.getRoot();

    }

    private void init() {
        ViewAllRecordActivity.spMonth.setVisibility(View.VISIBLE);
        ViewAllRecordActivity.spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strMonth = parent.getSelectedItem().toString();
                    if (strMonth.equals("January")) {
                        strMonthNo = "01";
                    } else if (strMonth.equals("February")) {
                        strMonthNo = "02";
                    } else if (strMonth.equals("March")) {
                        strMonthNo = "03";
                    } else if (strMonth.equals("April")) {
                        strMonthNo = "04";
                    } else if (strMonth.equals("May")) {
                        strMonthNo = "05";
                    } else if (strMonth.equals("June")) {
                        strMonthNo = "06";
                    } else if (strMonth.equals("July")) {
                        strMonthNo = "07";
                    } else if (strMonth.equals("August")) {
                        strMonthNo = "08";
                    } else if (strMonth.equals("September")) {
                        strMonthNo = "09";
                    } else if (strMonth.equals("October")) {
                        strMonthNo = "10";
                    } else if (strMonth.equals("November")) {
                        strMonthNo = "11";
                    } else if (strMonth.equals("December")) {
                        strMonthNo = "12";
                    }

                if (strMonthNo != null && !strMonthNo.equals("")) {
                    userDataList = curdOperation.getDataByPaymentType("Saving", strMonthNo);
                    if (userDataList != null && userDataList.size() > 0) {
                        mBinding.tcNoRecord.setVisibility(View.GONE);
                        setupRecyclerview();
                    } else {
                        mBinding.tcNoRecord.setVisibility(View.VISIBLE);
                    }
                    totalSum = curdOperation.getTotalAmount("Saving", strMonthNo);
                    System.out.println("total Amount is ==>" + totalSum.getTotalInterestedAmount());
                    mBinding.tvTotalInterest.setText("Total Saving amount with interest is " + "\u20B9" + totalSum.getTotalInterestedAmount());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

     /*   if (strMonthNo != null && !strMonthNo.equals("")) {
            userDataList = curdOperation.getDataByPaymentType("Saving", strMonthNo);
            if (userDataList != null && userDataList.size() > 0) {
                mBinding.tcNoRecord.setVisibility(View.GONE);
                setupRecyclerview();
            } else {
                mBinding.tcNoRecord.setVisibility(View.VISIBLE);
            }
            totalSum = curdOperation.getTotalAmount("Saving", strMonthNo);
            System.out.println("total Amount is ==>" + totalSum.getTotalInterestedAmount());
            mBinding.tvTotalInterest.setText("Total Saving amount with interest is " + "\u20B9" + totalSum.getTotalInterestedAmount());
        }*/
    }

    private void setupRecyclerview() {
        mAdapter = new SavingAdapter(getActivity(), userDataList);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    public void setupSwipeToRefresh() {
        mBinding.swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.swipeToRefresh.setRefreshing(false);
                userDataList.clear();
                init();
            }
        });
    }
}
