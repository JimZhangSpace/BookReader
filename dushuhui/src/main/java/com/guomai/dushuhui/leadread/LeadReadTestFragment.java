package com.guomai.dushuhui.leadread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseMainFragment;

import butterknife.ButterKnife;

public class LeadReadTestFragment extends BaseMainFragment {
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.test_layout, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;

    }
}
