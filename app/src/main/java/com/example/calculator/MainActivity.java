package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0;
    private Button btn_c,btn_percent,btn_divide,btn_multiply,btn_minus,btn_add,btn_equal,btn_dot;
    private Button btn_del,btn_plus_minus;
    private TextView txt_num,txt_math;
    private ListView lv_record_result;
    List<Item> mlist = new ArrayList<>();
    private MyBaseAdapter adapter;
    private String cur_num;
    private String TAG = "MainActivity";
    private int numCunt;
    private double num_1,num_2;
    private String num_math_sign;
    private String pre_num_math_sign;
    private int num_flag;
    private int math_flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        cur_num = "0";
        numCunt = 0;
        num_1 = 0;
        num_2 = 0;
        num_math_sign = "";
        num_flag = 0;
        math_flag = 1;
        pre_num_math_sign = "";

        txt_num = (TextView)findViewById(R.id.txt_num);
        txt_math = (TextView)findViewById(R.id.txt_math);
        btn_0 = (Button)findViewById(R.id.btn_0);
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_4 = (Button)findViewById(R.id.btn_4);
        btn_5 = (Button)findViewById(R.id.btn_5);
        btn_6 = (Button)findViewById(R.id.btn_6);
        btn_7 = (Button)findViewById(R.id.btn_7);
        btn_8 = (Button)findViewById(R.id.btn_8);
        btn_9 = (Button)findViewById(R.id.btn_9);

        btn_c = (Button)findViewById(R.id.btn_c);
        btn_percent = (Button)findViewById(R.id.btn_percent);
        btn_divide = (Button)findViewById(R.id.btn_divide);
        btn_multiply = (Button)findViewById(R.id.btn_multiply);
        btn_minus = (Button)findViewById(R.id.btn_minus);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_equal = (Button)findViewById(R.id.btn_equal);
        btn_dot = (Button)findViewById(R.id.btn_dot);
        btn_del = (Button)findViewById(R.id.btn_del);
        btn_plus_minus = (Button)findViewById(R.id.btn_plus_minus);

        lv_record_result = (ListView) findViewById(R.id.lv_record_result);
        lv_record_result.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        adapter = new MyBaseAdapter(this,mlist);
        lv_record_result.setAdapter(adapter);

        btn_0.setOnClickListener(onClick);
        btn_1.setOnClickListener(onClick);
        btn_2.setOnClickListener(onClick);
        btn_3.setOnClickListener(onClick);
        btn_4.setOnClickListener(onClick);
        btn_5.setOnClickListener(onClick);
        btn_6.setOnClickListener(onClick);
        btn_7.setOnClickListener(onClick);
        btn_8.setOnClickListener(onClick);
        btn_9.setOnClickListener(onClick);
        btn_dot.setOnClickListener(onClick);

        btn_c.setOnClickListener(onClickMath);
        btn_percent.setOnClickListener(onClickMath);
        btn_divide.setOnClickListener(onClickMath);
        btn_multiply.setOnClickListener(onClickMath);
        btn_minus.setOnClickListener(onClickMath);
        btn_add.setOnClickListener(onClickMath);
        btn_equal.setOnClickListener(onClickMath);
        btn_del.setOnClickListener(onClickMath);
        btn_plus_minus.setOnClickListener(onClickMath);



    }

    private View.OnClickListener onClickMath = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_c:
                    num_1 = 0;
                    num_2 = 0;
                    math_flag = 1;
                    cur_num = "0";
                    numCunt = 0;
                    num_math_sign = "";
                    txt_num.setText(cur_num);
                    txt_math.setText("");
                    break;
                case R.id.btn_percent:
                    Double now_percent_num = 0.0;
                    now_percent_num = Double.parseDouble(cur_num);
                    Log.d(TAG, "onClick: now_percent_num = "+now_percent_num);
                    now_percent_num = now_percent_num /100;
                    Log.d(TAG, "onClick: now_percent_num = "+now_percent_num);
                    cur_num = String.valueOf(now_percent_num);
                    txt_num.setText(cur_num);
                    numCunt = cur_num.length();
                    Log.d(TAG, "onClick: numCunt = " + numCunt);
                    break;
                case R.id.btn_divide:
                    num_math_sign = "/";
                    numCunt = numAccumulate(num_math_sign);
                    break;
                case R.id.btn_multiply:
                    num_math_sign = "x";
                    numCunt = numAccumulate(num_math_sign);
                    break;
                case R.id.btn_minus:
                    num_math_sign = "-";
                    numCunt = numAccumulate(num_math_sign);
                    break;
                case R.id.btn_add:
                    num_math_sign = "+";
                    numCunt = numAccumulate(num_math_sign);
                    break;
                case R.id.btn_equal:
                    txt_math.setText("");
                    num_2 = Double.parseDouble(cur_num);
                    txt_num.setText(String.valueOf(numMath(num_1,num_2,num_math_sign)));
                    num_flag = 2;
                    if(math_flag == 2){
                        show_record(num_1,num_2,num_math_sign);
                    }
                    break;
                case R.id.btn_del:
                    if (cur_num.length() > 1){
                        Log.d(TAG, "onClick: cur_num.length() > 0");
                        cur_num = cur_num.substring(0,cur_num.length()-1);
                        Log.d(TAG, "onClick: cur_num.length() = "+cur_num.length());
                        numCunt--;
                    }else {
                        Log.d(TAG, "onClick: cur_num.length() < 0");
                        numCunt = 0;
                        cur_num = "0";
                    }
                    txt_num.setText(cur_num);
                    break;
                case R.id.btn_plus_minus:
                    Double now_num = 0.0;
                    now_num = Double.parseDouble(cur_num);
                    if (now_num != 0){
                        now_num = now_num * -1;
                    }
                    cur_num = String.valueOf(now_num);
                    txt_num.setText(cur_num);
                    break;
            }

        }
    };

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (num_flag==2){
                cur_num = "0";
                txt_num.setText("0");
                num_flag = 0;
                math_flag = 1;
            }
            if (numCunt<11){
                switch (view.getId()) {
                    case R.id.btn_0:
                        cur_num = numChange(cur_num,"0");
                        break;
                    case R.id.btn_1:
                        cur_num = numChange(cur_num,"1");
                        break;
                    case R.id.btn_2:
                        cur_num = numChange(cur_num,"2");
                        break;
                    case R.id.btn_3:
                        cur_num = numChange(cur_num,"3");
                        break;
                    case R.id.btn_4:
                        cur_num = numChange(cur_num,"4");
                        break;
                    case R.id.btn_5:
                        cur_num = numChange(cur_num,"5");
                        break;
                    case R.id.btn_6:
                        cur_num = numChange(cur_num,"6");
                        break;
                    case R.id.btn_7:
                        cur_num = numChange(cur_num,"7");
                        break;
                    case R.id.btn_8:
                        cur_num = numChange(cur_num,"8");
                        break;
                    case R.id.btn_9:
                        cur_num = numChange(cur_num,"9");
                        break;
                    case R.id.btn_dot:
                        cur_num = numChange(cur_num,".");
                        break;
                }
                txt_num.setText(cur_num);
                numCunt++;
            }
        }
    };

    private String numChange(String cur_num, String num){
        Log.d(TAG, "numChange: cur_num = "+cur_num + "- num ="+num);
        if (cur_num.equals("0") && num.equals("0")){
            cur_num = "0";
            return cur_num;
        }
        if (cur_num.charAt(0)=='0'){
            //cur_num = cur_num + num;
            cur_num = num;
        }else {
            cur_num = cur_num + num;
        }
        Log.d(TAG, "numChange: result cur_num = "+cur_num);
        return cur_num;
    }

    private double numMath(double num_1,double num_2,String num_math_sign){
        double result;
        result = 0;
        if (num_math_sign.equals("+")){
            result = num_1 + num_2;
        }else if(num_math_sign.equals("-")){
            result = num_1 - num_2;
        }else if(num_math_sign.equals("x")){
            result = num_1 * num_2;
        }else if(num_math_sign.equals("/")){
            result = num_1 / num_2;
        }
        return result;
    }

    private int numAccumulate(String Ac_num_math_sign){
        num_flag = 0;
        Log.d(TAG, "numAccumulate: -----------------");
        Log.d(TAG, "numAccumulate: Before");
        Log.d(TAG, "numAccumulate: num_1 = "+num_1);
        Log.d(TAG, "numAccumulate: num_2 = "+num_2);
        Log.d(TAG, "numAccumulate: -----------------");
        if (math_flag == 1){
            Log.d(TAG, "onClick: math_flag = 1");
            txt_math.setText(cur_num+Ac_num_math_sign);
            num_1 = Double.parseDouble(cur_num);
            cur_num = "0";
            txt_num.setText(cur_num);
            math_flag = 2;
            pre_num_math_sign = Ac_num_math_sign;
        }else if (math_flag == 2 && pre_num_math_sign.equals(Ac_num_math_sign)){
            Log.d(TAG, "onClick: math_flag = 2");
            num_2 = Double.parseDouble(cur_num);
            Log.d(TAG, "numAccumulate: num_2 = "+num_2);
            txt_num.setText(String.valueOf(numMath(num_1,num_2,Ac_num_math_sign)));
            show_record(num_1,num_2,Ac_num_math_sign);
            num_1 = numMath(num_1,num_2,Ac_num_math_sign);
            txt_math.setText(num_1+Ac_num_math_sign);
            cur_num = "0";
            num_2 = 0;
        }else {
            Log.d(TAG, "onClick: math_flag = 3");
            num_2 = Double.parseDouble(cur_num);
            Log.d(TAG, "numAccumulate: num_2 = "+num_2);
            txt_num.setText(String.valueOf(numMath(num_1,num_2,pre_num_math_sign)));
            show_record(num_1,num_2,pre_num_math_sign);
            num_1 = numMath(num_1,num_2,pre_num_math_sign);
            txt_math.setText(num_1+Ac_num_math_sign);
            cur_num = "0";
            num_2 = 0;
            pre_num_math_sign = Ac_num_math_sign;
        }
        Log.d(TAG, "numAccumulate: -----------------");
        Log.d(TAG, "numAccumulate: After");
        Log.d(TAG, "numAccumulate: num_1 = "+num_1);
        Log.d(TAG, "numAccumulate: num_2 = "+num_2);
        Log.d(TAG, "numAccumulate: -----------------");
        numCunt = String.valueOf(numMath(num_1,num_2,Ac_num_math_sign)).length();
        return numCunt;
    }

    private void show_record(double num_1,double num_2,String num_math_sign){
        Item item = new Item("---------------",num_1 + num_math_sign +
                num_2 +"="+String.valueOf(numMath(num_1,num_2,num_math_sign)));
        mlist.add(item);
        adapter.notifyDataSetChanged();
    }
}