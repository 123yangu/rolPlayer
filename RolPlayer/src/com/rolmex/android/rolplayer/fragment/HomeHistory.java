
package com.rolmex.android.rolplayer.fragment;

import com.rolmex.android.rolplayer.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeHistory extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.home_catory, null);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

}
