package com.prerak.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prerak.R;
import com.prerak.database.table.UserData;
import com.prerak.databinding.SavingByMonthBinding;

import java.util.List;

/**
 * Created by prerak on 3/29/2019.
 */

public class SavingByMonthAdapter extends RecyclerView.Adapter<SavingByMonthAdapter.ViewHolder> {
    private Context mContext;
    private List<UserData> userDataList;

    public SavingByMonthAdapter(FragmentActivity activity, List<UserData> userDataList) {
        this.mContext = activity;
        this.userDataList = userDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.saving_by_month, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       if (userDataList != null && userDataList.size() > 0){
//           holder.mBinding.tvMonth.setText(userDataList.get(position));
       }
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SavingByMonthBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
