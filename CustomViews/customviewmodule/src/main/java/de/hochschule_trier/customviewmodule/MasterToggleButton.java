package de.hochschule_trier.customviewmodule;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MasterToggleButton extends ToggleButton{
    private List<ToggleButton> slaves = new ArrayList<>();
    private boolean disableSlaves = false;
    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
    }
    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
    }
    public MasterToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
    }
    public MasterToggleButton(Context context) {
        super(context);
    }
    public void addSlaves(ToggleButton slave) {
        slave.setClickable(!this.disableSlaves);
        this.slaves.add(slave);
    }
    public void addSlaves(ToggleButton[] newSlaves) {
        for (ToggleButton slave : newSlaves) {
            this.addSlaves(slave);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!slaves.isEmpty()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                for (ToggleButton slave : slaves) {
                    handleEvent(slave);
                }
            }
        }
        return super.onTouchEvent(event);
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
        for (ToggleButton slave : slaves) {
            if (slave instanceof MasterToggleButton) {
                ((MasterToggleButton) slave).setDisableSlaves(this.disableSlaves);
            }
            slave.setClickable(!this.disableSlaves);
        }
    }
    private void parseAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MasterToggleButton);
        disableSlaves = a.getBoolean(R.styleable.MasterToggleButton_disableSlaves, disableSlaves);
        a.recycle();
    }
    private void handleEvent(ToggleButton _slave) {
        if (_slave instanceof MasterToggleButton && ((MasterToggleButton) _slave).hasSlaves()) {
            for (ToggleButton slave : ((MasterToggleButton) _slave).getSlaves()) {
                handleEvent(slave);
            }
        }
        _slave.setChecked(!this.isChecked());
    }
}