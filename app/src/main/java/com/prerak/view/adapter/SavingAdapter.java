package com.prerak.view.adapter;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prerak.R;
import com.prerak.database.dao.CURDOperation;
import com.prerak.database.db.DB;
import com.prerak.database.table.TotalSum;
import com.prerak.database.table.UserData;
import com.prerak.databinding.RawSavingBinding;

import java.util.List;

/**
 * Created by prerak on 24/1/19.
 */

public class SavingAdapter extends RecyclerView.Adapter<SavingAdapter.ViewHolder> {
    private Activity mContext;
    private List<UserData> userDataList;
    private TotalSum totalSum;
    private DB appDatabase;
    private CURDOperation curdOperation;

    public SavingAdapter(Activity savingFragmentFragment, List<UserData> userDataList) {
        this.mContext = savingFragmentFragment;
        appDatabase = Room.databaseBuilder(savingFragmentFragment, DB.class, DB.DB_NAME)
                .allowMainThreadQueries().build();
        curdOperation = new CURDOperation(mContext);
        this.userDataList = userDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_saving, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (userDataList != null && userDataList.size() > 0) {
            holder.mBinding.tvAmount.setText("\u20B9" + userDataList.get(position).getAmount());
            holder.mBinding.tvDate.setText(userDataList.get(position).getDateTime());
            holder.mBinding.tvDate.setText(userDataList.get(position).getMm());
            totalSum = curdOperation.getTotalSum(userDataList.get(position).getId());
            holder.mBinding.tvInterest.setText("Amount with interest is : " + "\u20B9" + totalSum.getTotalAmount());
        }

    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RawSavingBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
