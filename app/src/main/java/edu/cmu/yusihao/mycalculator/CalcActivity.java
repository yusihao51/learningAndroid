package edu.cmu.yusihao.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.util.Log;

import edu.cmu.yusihao.mycalculator.databinding.ActivityMainBinding;

public class CalcActivity extends AppCompatActivity {
    private static final String TAG = "CalcActivity";
    private static final String KEY_INDEX = "index";
    private CalcViewModel viewModel;
//TODO save modulus
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = new CalcViewModel(this);
        binding.setViewModel(viewModel);
        Log.d("MainActivity","initiated");
//TODO load modulus
    }

}
