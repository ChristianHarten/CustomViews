package de.hochschule_trier.numpaddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.hochschule_trier.customviewmodule.SimpleNumpad;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView numpad_demo = (TextView) findViewById(R.id.numpad_demo);
        SimpleNumpad numpad = (SimpleNumpad) findViewById(R.id.numpad);
        numpad.setOnNumClickListener(new SimpleNumpad.OnNumClickListener() {
            @Override
            public void onNumClick(View v, char num) {
                numpad_demo.append(String.valueOf(num));
            }
        });
    }
}
