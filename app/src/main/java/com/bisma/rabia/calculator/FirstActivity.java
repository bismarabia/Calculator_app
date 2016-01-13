package com.bisma.rabia.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    ArrayList<String> arraylist = new ArrayList<String>();
    String string = "";
    String string1 = "";
    TextView display_operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        display_operation = (TextView) findViewById(R.id.display_operation);
    }


    public void onClick1 (View v) {

        Button button = (Button) v;
        string = button.getText().toString();

        if (!string.contains("+") && !string.contains("-") && !string.contains("*") && !string.contains("/")) {
            string1 = string1 + string;
            if (arraylist.size() > 0) {
                arraylist.remove(arraylist.size() - 1);
            }
            arraylist.add(string1);
        }
        else {
            arraylist.add(string);
            arraylist.add(string);
            string1="";
        }
        display_operation.setText(String.format("%s%s", display_operation.getText().toString(), string));
        //display_operation.setText(arraylist.toString());
    }

    public void onClick (View v){

        TextView display_result = (TextView)findViewById(R.id.display_result);

        float calc = 0;
        int c = arraylist.size(); // size of the array eg. (2,+,3,-,4,*,5,/,8)

        while (c!=1){
            if (c>3) {
                if (arraylist.get(3).contains("*") || arraylist.get(3).contains("/")) {
                    if(arraylist.get(3).contains("*")) { calc = Float.parseFloat(arraylist.get(2))* Float.parseFloat(arraylist.get(4));}
                    if(arraylist.get(3).contains("/")) { calc = Float.parseFloat(arraylist.get(2))/ Float.parseFloat(arraylist.get(4));}

                    for (int i=0; i<3; i++) {arraylist.remove(2);}
                    arraylist.add(2, Float.toString(calc));
                    c = arraylist.size(); // new size of the array
                }
                else {
                    calc = performe_calculation(1, 0);

                    for (int i=0; i<3; i++) {arraylist.remove(0);}
                    arraylist.add(0, Float.toString(calc));
                    c = arraylist.size(); // new size of the array
                }
            }
            else { // so the array has three elements eg. (5,-,2)
                calc = performe_calculation (1, 0);
                for (int i=0; i<3; i++) {arraylist.remove(0);}
                arraylist.add(0, Float.toString(calc));
                c = arraylist.size();
            }
        }
        display_result.setText(Float.toString(calc));
        Toast toast = Toast.makeText(getApplicationContext(), Float.toString(calc), Toast.LENGTH_SHORT);
        toast.show();
    }

    public float performe_calculation (int pos, int operand) {
        float ca = 0;
        if(arraylist.get(pos).contains("+")) {
            ca = Float.parseFloat(arraylist.get(operand)) + Float.parseFloat(arraylist.get(operand + 2));
        }
        if(arraylist.get(pos).contains("-")) {
            ca = Float.parseFloat(arraylist.get(operand)) - Float.parseFloat(arraylist.get(operand+2));
        }
        if(arraylist.get(pos).contains("*")) {
            ca = Float.parseFloat(arraylist.get(operand)) * Float.parseFloat(arraylist.get(operand+2));
        }
        if(arraylist.get(pos).contains("/")) {
            ca = Float.parseFloat(arraylist.get(operand)) / Float.parseFloat(arraylist.get(operand+2));
        }
        return ca;
    }

    public void clear (View v){
        TextView display_result = (TextView)findViewById(R.id.display_result);
        TextView display_operation = (TextView)findViewById(R.id.display_operation);

        string = "";
        string1 = "";
        display_operation.setText("");
        display_result.setText("0");
        arraylist.clear();

    }

    public void delete_last (View v) {
        if ((arraylist.get(arraylist.size() - 1)).length()== 1) {
            arraylist.remove(arraylist.size() - 1);
        }
        else {
            float y = Float.valueOf(arraylist.get(arraylist.size() - 1));
            arraylist.remove(arraylist.size() - 1);
            arraylist.add(arraylist.size() - 1, String.valueOf((int)(y/10)));
        }

        arraylist.remove(arraylist.size() - 1);
        display_operation.setText(String.format("%s%s", display_operation.getText().toString(), string));

        Toast toast = Toast.makeText(getApplicationContext(), arraylist.toString(), Toast.LENGTH_SHORT);
        toast.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
