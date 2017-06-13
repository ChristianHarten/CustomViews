package de.hochschule_trier.customviewmodule;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MasterToggleButton extends ToggleButton{
    private List<ToggleButton> slaves;
    private boolean disableSlaves;
    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
        init();
    }
    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
        init();
    }
    public MasterToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
        init();
    }
    public MasterToggleButton(Context context) {
        super(context);
        init();
    }
    public void addSlaves(ToggleButton slave) {
        slave.setClickable(!this.disableSlaves);
        this.slaves.add(slave);
    }
    public void addSlaves(ToggleButton[] newSlaves) {
        for (ToggleButton slave : newSlaves) {
            slave.setClickable(!this.disableSlaves);
            this.slaves.add(slave);
        }
    }
    private void init() {
        this.slaves = new ArrayList<>();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (!slaves.isEmpty()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                for (ToggleButton slave : slaves) {
                    handleEvent(slave);
                }
            }
        }
        return true;
    }
    public boolean hasSlaves() {
        return !this.slaves.isEmpty();
    }
    public List<ToggleButton> getSlaves() {
        return this.slaves;
    }
    public boolean getDisableSlaves() {
        return this.disableSlaves;
    }
    public void setDisableSlaves(boolean value) {
        this.disableSlaves = value;
        refreshSlaveList();
    }
    private void refreshSlaveList() {
        if (!this.slaves.isEmpty()) {
            for (ToggleButton slave : slaves) {
                slave.setClickable(!this.disableSlaves);
            }
        }
    }
    private void parseAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MasterToggleButton);
        disableSlaves = a.getBoolean(R.styleable.MasterToggleButton_disableSlaves, disableSlaves);
        a.recycle();
    }
    private void handleEvent(ToggleButton slave) {
        if (slave instanceof MasterToggleButton && ((MasterToggleButton) slave).hasSlaves()) {
            for (ToggleButton _slave : ((MasterToggleButton) slave).getSlaves()) {
                handleEvent(_slave);
            }
        }
        slave.setChecked(!this.isChecked());
    }
}