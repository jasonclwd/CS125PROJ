package com.example.clwd2.clock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.os.Handler;
import android.os.Message;


public class DigitalClock extends AppCompatActivity {
    private Button button;
    private TextView minute;
    private TextView second;
    private Button start;
    private Button reset;
    private long timer;
    private boolean hasItStop = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (!hasItStop) {
                        updateView();
                        handler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
                case 0:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_clock2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencompass();
            }
        });
        initViews();
    }
    private void initViews() {
        minute = (TextView) findViewById(R.id.minute);
        second = (TextView) findViewById(R.id.second);
        reset = (Button) findViewById(R.id.reset);
        start = (Button) findViewById(R.id.start);
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                minute.setText("00");
                second.setText("00");
                start.setText("start");
                timer = 0;
                hasItStop = true;
            }
        });
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                handler.removeMessages(1);
                String aaa=start.getText().toString();
                if(aaa.equals("start")){
                    handler.sendEmptyMessage(1);
                    hasItStop = false;
                    start.setText("pause");
                }else {
                    handler.sendEmptyMessage(0);
                    hasItStop = true;
                    start.setText("start");
                }
            }
        });
    }
    private void updateView() {
        timer += 1;
        int min = (int) (timer / 60)%60;
        int sec = (int) (timer % 60);
        if (min < 10)
            minute.setText("0" + min);
        else
            minute.setText("" + min);
        if (sec < 10)
            second.setText("0" + sec);
        else
            second.setText("" + sec);
    }
    public void opencompass() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
