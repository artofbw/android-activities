package com.example.exampleapp.ui.kcal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.exampleapp.databinding.FragmentKcalBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class KcalFragment extends Fragment {

    private FragmentKcalBinding binding;

    // result formatter objects
    private static final DecimalFormat resultFormat = new DecimalFormat("0.00");
    // height and weight formatter objects
    private static final NumberFormat parameterFormat = NumberFormat.getInstance();

    private double weight = 0.0; // weight entered by the user
    private double height = 0.0; // height entered by the user
    private int age = 0; // age entered by the user

    // called when the activity is first created
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentKcalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // set heightEditText's TextWatcher
        EditText heightEditText =
                (EditText) binding.heightEditNumber;
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        // set weightEditText's TextWatcher
        EditText weightEditText =
                (EditText) binding.weightEditNumber;
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        // set ageEditText's TextWatcher
        EditText ageEditText =
                (EditText) binding.ageEditNumber;
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        return root;
    }

    // calculate and display BMR
    private void calculate() {
//        BMR = 66.5 + (13.75 * weight in kg) + (5.003 * height in cm) - (6.75 * age)
        if (weight > 0 && height > 0 && age > 0) {
            // calculate the BMR
            double result = 66.5 + (13.75 * weight) + (5.003 * height) - (6.75 * age);

            // display result formatted as a number
            setText(resultFormat.format(result));
        } else {
            setText("");
        }
    }

    public void setText(String text) {
        TextView view = (TextView) binding.kcalTextView;
        view.setText(text);
    }

    // listener object for the EditText's text-changed events
    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try { // get weight and display kg formatted value
                weight = Double.parseDouble(s.toString());
                setText(parameterFormat.format(weight));
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                setText("");
            }

            calculate(); // update the result
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    // listener object for the EditText's text-changed events
    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try { // get height and display cm formatted value
                height = Double.parseDouble(s.toString());
                setText(parameterFormat.format(height));
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                setText("");
            }

            calculate(); // update the result
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    // listener object for the EditText's text-changed events
    private final TextWatcher ageEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try { // get age and display formatted value
                age = Integer.parseInt(s.toString());
                setText(parameterFormat.format(age));
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                setText("");
            }

            calculate(); // update the result
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}