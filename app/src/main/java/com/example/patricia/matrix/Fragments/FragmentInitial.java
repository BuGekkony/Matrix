package com.example.patricia.matrix.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patricia.matrix.R;


public class FragmentInitial extends Fragment {

    private final String TAG = FragmentInitial.class.getSimpleName();

    /*
    Componentes Gráficos
     */
    private TextView textViewInitial;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MÉTODO ON CREATE.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "MÉTODO ON START.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MÉTODO ON DESTROY.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "MÉTODO ON CREATE VIEW.");
        View view = inflater.inflate(R.layout.fragment_initial, container, false);
        textViewInitial = (TextView) view.findViewById(R.id.textview_fragment_initial);
        return(view);
    }
}
