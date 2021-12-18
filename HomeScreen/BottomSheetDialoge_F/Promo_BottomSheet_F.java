package com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dinosoftlabs.uber.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Promo_BottomSheet_F extends BottomSheetDialogFragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.promo_bts_f, container, false);

        return view;
    }
}
