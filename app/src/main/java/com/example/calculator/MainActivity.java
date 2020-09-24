package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView num1;
    private TextView num2;
    private TextView dot1;
    private TextView dot2;
    private TextView res;
    private TextView symbol;
    private long back_press_time;

    float number1 = 0;
    float number2 = 0;
    float Result = 0;
    boolean dotinnum1 = false;
    boolean dotinnum2 = false;
    boolean isnum1 = true;
    float divideby = 10;
    char operation = ' ';

    public void clearall(View view){
        number1 = 0;
        number2 = 0;
        Result = 0;
        operation = ' ';
        dotinnum1 = false;
        dotinnum2 = false;
        isnum1 = true;
        divideby = 10;
        num1.setText("");
        num2.setText("");
        res.setText("");
        symbol.setText("");
        dot1.setText("");
        dot2.setText("");
    }

    public void symbol(View view){
        isnum1 = false;
        int id = view.getId();
        Button btn = findViewById(id);
        symbol.setText(btn.getText().toString());
        operation = btn.getText().toString().charAt(0);
    }

    public void give_answer(View view){
        if(operation == ' '){
            Toast.makeText(this, "Their is no operation", Toast.LENGTH_SHORT).show();
        }
        else{
            System.out.println(operation + " This is the operation");
            if(operation == '+'){
                Result = number1 + number2;
            }
            if(operation == '-'){
                Result = number1 - number2;
            }
            if(operation == '*'){
                Result = number1 * number2;
            }
            if(operation == '/'){
                Result = number1 / number2;
            }

            res.setText(Float.toString(Result));
        }
    }

    public void dot_is_coming(View view){
        if(isnum1){
            int id = view.getId();
            Button btn = findViewById(id);
            dot1.setText(".");
            dotinnum1 = true;
        }
        else{
            int id = view.getId();
            Button btn = findViewById(id);
            dot2.setText(".");
            dotinnum2 = true;
        }
    }

    public void back_button(View view){
        if(isnum1){
            if(number1 == 0 && dotinnum1 == false){}
            else{
                if(dotinnum1 == false){
                    number1 /= 10;
                    number1 = (int)number1;
                }
                else{
                    String temp = Float.toString(number1);
                    temp = temp.substring(0, temp.length() - 1);
                    divideby /= 10;
                    float y = Float.parseFloat(temp);
                    int x = (int)y;
                    if(y-x == 0.0){
                        dotinnum1 = false;
                        dot1.setText("");
                        number1 = x;
                    }
                    else{
                        number1 = y;
                    }
                }
                num1.setText(Float.toString(number1));
            }
        }
        else{
            if(number2 == 0 && dotinnum2 == false){}
            else{
                if(dotinnum2 == false){
                    number2 /= 10;
                    number2 = (int)number2;
                }
                else{
                    String temp = Float.toString(number2);
                    temp = temp.substring(0, temp.length() - 1);
                    divideby /= 10;
                    float y = Float.parseFloat(temp);
                    int x = (int)y;
                    if(y-x == 0.0){
                        dot2.setText("");
                        dotinnum2 = false;
                        number2 = x;
                    }
                    else{
                        number2 = y;
                    }
                }
                num2.setText(Float.toString(number2));
            }
        }
    }

    public void button(View view){
        if((isnum1 && num1.length() > 15) || (num2.length() > 15)){
            Toast.makeText(this, "Max length of a number can be 12", Toast.LENGTH_SHORT).show();
        }
        else {
            int id = view.getId();
            Button btn = findViewById(id);
            float temp;
            if (isnum1) {
                if (number1 == 0 && dotinnum1 == false) {
                    temp = 0;
                    divideby = 10;
                } else {
                    temp = number1;
                }
                if (dotinnum1) {
                    float toadd = Integer.parseInt(btn.getText().toString()) / divideby;
                    divideby *= 10;
                    temp += toadd;
                } else {
                    temp = temp * 10 + Integer.parseInt(btn.getText().toString());
                }
                number1 = temp;
                num1.setText(Float.toString(number1));

            } else {
                if (number2 == 0 && dotinnum2 == false) {
                    temp = 0;
                    divideby = 10;
                } else {
                    temp = number2;
                }
                if (dotinnum2) {
                    float toadd = Integer.parseInt(btn.getText().toString()) / divideby;
                    temp += toadd;
                    divideby *= 10;
                } else {
                    temp = temp * 10 + Integer.parseInt(btn.getText().toString());
                }
                number2 = temp;
                num2.setText(Float.toString(number2));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectButton();
    }

    @Override
    public void onBackPressed() {

        if(back_press_time + 1000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else{
            Toast.makeText(this, "Press Again to exit", Toast.LENGTH_SHORT).show();
        }

        back_press_time = System.currentTimeMillis();
    }

    private void connectButton(){
        num1 = (TextView)findViewById(R.id.num1);
        num2 = (TextView)findViewById(R.id.num2);
        res = (TextView)findViewById(R.id.result);
        symbol = (TextView)findViewById(R.id.symbol);
        dot1 = (TextView)findViewById(R.id.dot1);
        dot2 = (TextView)findViewById(R.id.dot2);
    }
}