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

import com.prerak.R;
import com.prerak.database.dao.CURDOperation;
import com.prerak.database.db.DB;
import com.prerak.database.table.TotalSum;
import com.prerak.database.table.UserData;
import com.prerak.databinding.FragmentSavingBinding;
import com.prerak.view.adapter.LoanAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoanFragment extends Fragment {
    private DB appDatabase;
    private CURDOperation curdOperation;
    private FragmentSavingBinding mBinding;
    private List<UserData> userDataList = new ArrayList<>();
    private UserData userData;
    private LoanAdapter mAdapter;
    private TotalSum totalSum;

    public LoanFragment() {
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

    private void setupRecyclerview() {
        mAdapter = new LoanAdapter(getActivity(), userDataList);
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

    private void init() {
        userDataList = curdOperation.getDataByPaymentType("Loan");
        if (userDataList != null && userDataList.size() > 0) {
            setupRecyclerview();
        } else {
            mBinding.tcNoRecord.setVisibility(View.VISIBLE);
        }
        totalSum = curdOperation.getTotalAmount("Loan");
        System.out.println("total Amount is ==>" + totalSum.getTotalInterestedAmount());
        mBinding.tvTotalInterest.setText("Total Saving amount with interest is " + "\u20B9" + totalSum.getTotalInterestedAmount());
    }
}
