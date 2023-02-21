package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private volatile boolean flag = false;
    private volatile boolean inform = true;
    private final int count = 7;
    private final double base = 120.0;
    private Context mContext;

    private EditText edit1, edit2, edit3, edit4, edit5, edit6, edit7, speedEdit;
    private TextView answerText;
    private Button calculate;

    private final double[] value = {24.2, 28.6, 39.6, 50.57, 70.38, 101.19, 0.0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        flag = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag) {
            edit1 = findViewById(R.id.edit1);
            edit2 = findViewById(R.id.edit2);
            edit3 = findViewById(R.id.edit3);
            edit4 = findViewById(R.id.edit4);
            edit5 = findViewById(R.id.edit5);
            edit6 = findViewById(R.id.edit6);
            edit7 = findViewById(R.id.edit7);
            speedEdit = findViewById(R.id.speedEdit);
            answerText = findViewById(R.id.answerText);
            calculate = findViewById(R.id.calculate);
            edit7.setEnabled(false);

            calculate.setOnClickListener(v -> {
                int[] nums = new int[count];
                try {
                    check(edit1);
                    check(edit2);
                    check(edit3);
                    check(edit4);
                    check(edit5);
                    check(edit6);
                    check(edit7);
                    check(speedEdit);
                    inform = true;
                    nums[0] = Integer.parseInt(edit1.getText().toString()) / 10;
                    nums[1] = Integer.parseInt(edit2.getText().toString()) / 10;
                    nums[2] = Integer.parseInt(edit3.getText().toString()) / 10;
                    nums[3] = Integer.parseInt(edit4.getText().toString()) / 10;
                    nums[4] = Integer.parseInt(edit5.getText().toString()) / 10;
                    nums[5] = Integer.parseInt(edit6.getText().toString()) / 10;
                    nums[6] = Integer.parseInt(edit7.getText().toString()) / 10;

                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
                double speed = Double.parseDouble(speedEdit.getText().toString());
                double ans = 0.0;
                for (int i = 0; i < count; i++) {
                    ans += nums[i] * value[i];
                }
                ans = ans * speed / base;
                answerText.setText("" + ans);
            });
            flag = false;
        }
    }
    private void check(EditText edit) {
        if (edit.getText().toString().length() == 0) {
            edit.setText("" + 0);
            if (inform) {
                Toast.makeText(mContext, "需要输入一个整数", Toast.LENGTH_SHORT).show();
                inform = false;
            }
        }
    }
}