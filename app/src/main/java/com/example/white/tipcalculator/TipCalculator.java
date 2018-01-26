package com.example.white.tipcalculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class TipCalculator extends AppCompatActivity {
public Calc tipCalc;
public NumberFormat money = NumberFormat.getCurrencyInstance();
private EditText billEditText;
private EditText tipEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new Calc(0.17f, 100.0f);
        setContentView(R.layout.activity_tip_calculator);

        handler tch = new handler();
        billEditText = (EditText) findViewById(R.id.amount_bill);

        tipEditText = (EditText) findViewById(R.id.amount_tip_percent);


        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);

    }

    public void calculate(){

        EditText billEditText = (EditText) findViewById(R.id.amount_bill);
        EditText tipEditText = (EditText) findViewById(R.id.amount_tip_percent);
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();
        TextView tipTextView = (TextView) findViewById(R.id.amount_tip);
        TextView totalTextView = (TextView) findViewById(R.id.amount_tip);

        try {
            //convert billstring and tipstring to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);
            //update the model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);
            //ask model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();
            //update the view with formatted tip and total amounts
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));

        }catch (NumberFormatException nfe){

        }


    }
    private class handler implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int after) {

        }

        @Override
        public void afterTextChanged(Editable e) {
            calculate();
        }
    }
}
 class Calc{
    private float tip, bill;

    public Calc(float newTip, float newBill){
        setTip(newTip);
        setBill(newBill);

    }

     public float getTip() {
         return tip;
     }

     public float getBill() {
         return bill;
     }

     public void setTip(float newTip) {
         if( newTip > 0){
             tip = newTip;
         }
     }

     public void setBill(float newBill) {
         if (newBill > 0){
             bill = newBill;
         }
     }

     public float tipAmount() {
         return bill * tip;
     }

     public float totalAmount() {
         return bill + tipAmount();
     }
 }

