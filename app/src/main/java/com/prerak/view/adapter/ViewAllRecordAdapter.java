package com.prerak.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.prerak.view.fragment.LoanFragment;
import com.prerak.view.fragment.SavingFragmentFragment;

/**
 * Created by prerak on 23/1/19.
 */

public class ViewAllRecordAdapter extends FragmentStatePagerAdapter {
    private int NUM_OF_TABS;

    public ViewAllRecordAdapter(FragmentManager fm, int NUM_OF_TABS) {
        super(fm);
        this.NUM_OF_TABS = NUM_OF_TABS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SavingFragmentFragment savingFragmentFragment = new SavingFragmentFragment();
                return savingFragmentFragment;

            case 1:
                LoanFragment loanFragment = new LoanFragment();
                return loanFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_OF_TABS;
    }
}
