package net.siesgst.led;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    boolean on = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText url =(EditText) findViewById(R.id.url);
        final NetworkCommunication communication = new NetworkCommunication();
        final Button sendData = (Button) findViewById(R.id.control);
        sendData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        if(!on){
                            Log.v("log","a");
                            communication.sendData(url.getText().toString(),"1");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v("log","b");
                                    sendData.setText("LED STATUS :ON");
                                }

                            });
                        on = true;
                        }
                        else
                        {
                            Log.v("log","c");
                            communication.sendData(url.getText().toString(),"0");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    sendData.setText("LED STATUS : OFF");
                                }
                            });
                            on = false;
                        }

                    }
                }).start();
            }
        });
    }
}

