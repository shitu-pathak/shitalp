package com.example.currencyconverter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class currencyconvertor extends Activity {

    private String fromCurrency;
    private String toCurrency;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currencyconvertor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.class.getModifiers(), menu);
        return true;
    }



    protected void onResume(){
        super.onResume();
        setUpSpinnerData();

    }
    //This method will be invoked to setup data of the spinner views
    //to show lists of currency types for selection
    public void setUpSpinnerData(){
        Spinner spFrom=(Spinner)findViewById(R.id.fromcurrency_spin);
        Spinner spTo=(Spinner)findViewById(R.id.tocurrency_spin);
        String[] currencyList={"AUD","CAD","CHF","EUR","GBP","JPY","NZD","KHR","USD","CNY","THB","INR"};
        ArrayAdapter<String> afrom=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,currencyList);
        spFrom.setAdapter(afrom);
        spFrom.setOnItemSelectedListener(new ItemSelectedFrom());
        ArrayAdapter<String> ato=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,currencyList);
        spTo.setAdapter(ato);
        spTo.setOnItemSelectedListener(new ItemSelectedTo());

    }

    private class ItemSelectedFrom implements OnItemSelectedListener{
        public void onNothingSelected(AdapterView<?> av){

        }
        public void onItemSelected(AdapterView<?> av, View view, int position, long id){
            TextView sel=(TextView)view;
            String from=sel.getText().toString();
            fromCurrency=from; //capture the currency of the From side
            EditText txtfrom=(EditText)findViewById(R.id.txt_fromamount);
            txtfrom.setHint("Enter "+fromCurrency+" amount");

        }
    }

    private class ItemSelectedTo implements OnItemSelectedListener{
        public void onNothingSelected(AdapterView<?> av){

        }
        public void onItemSelected(AdapterView<?> av, View view, int position, long id){
            TextView sel=(TextView)view;
            String to=sel.getText().toString();
            toCurrency=to; //capture the currency of the To side


        }
    }


    public void showResult(View view){
        EditText txtRate=(EditText)findViewById(R.id.txt_rate);
        EditText txtAmount=(EditText)findViewById(R.id.txt_fromamount);
        if(txtRate.getText().toString().length()<=0 || txtAmount.getText().toString().length()<=0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please input value in text box.");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", new OnClickListener(){
                public void onClick(DialogInterface di,int which){
                    di.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else{
            //create intent, place data in it and start the ConversionTable activity
            Intent intent=new Intent(this,Intent.class);
            intent.putExtra("fromCurrency", fromCurrency);
            intent.putExtra("toCurrency", toCurrency);
            intent.putExtra("Rate", Double.valueOf(txtRate.getText().toString()));
            intent.putExtra("fromAmount", Double.valueOf(txtAmount.getText().toString()));
            startActivity(intent);
        }
    }}