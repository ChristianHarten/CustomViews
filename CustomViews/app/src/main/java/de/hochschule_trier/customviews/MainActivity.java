package de.hochschule_trier.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ToggleButton;

import de.hochschule_trier.customviewmodule.MasterToggleButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // master buttons
        MasterToggleButton master = (MasterToggleButton) findViewById(R.id.all_btn);
        MasterToggleButton front = (MasterToggleButton) findViewById(R.id.front_btn);
        MasterToggleButton rear = (MasterToggleButton) findViewById(R.id.rear_btn);
        // normal toggle buttons
        ToggleButton center = (ToggleButton) findViewById(R.id.center_btn);
        ToggleButton subwoofer = (ToggleButton) findViewById(R.id.subwoofer_btn);
        ToggleButton front_left = (ToggleButton) findViewById(R.id.left_front_btn);
        ToggleButton front_right = (ToggleButton) findViewById(R.id.right_front_btn);
        ToggleButton rear_left = (ToggleButton) findViewById(R.id.left_rear_btn);
        ToggleButton rear_right = (ToggleButton) findViewById(R.id.right_rear_btn);
        // create depencies
        master.addSlaves(new ToggleButton[]{center, subwoofer, front, rear});
        front.addSlaves(new ToggleButton[]{front_left, front_right});
        rear.addSlaves(new ToggleButton[]{rear_left, rear_right});
    }
}
