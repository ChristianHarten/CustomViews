package de.hochschule_trier.customviewmodule;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SimpleNumpad extends LinearLayout {
    // event interface
    public interface OnNumClickListener {
        void onNumClick(View v, char num);
    }
    private OnNumClickListener mOnNumClickListener = null;
    public void setOnNumClickListener(OnNumClickListener l) {
        this.mOnNumClickListener = l;
    }
    // constructors
    public SimpleNumpad(Context context) {
        super(context);
        setup();
    }
    public SimpleNumpad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }
    public SimpleNumpad(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }
    public SimpleNumpad(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }
    // inflates layout, adds listener to buttons
    private void setup() {
        inflate(getContext(), R.layout.view_numpad, this);
        Button[] buttons = new Button[] {
                (Button)findViewById(R.id.btn_comma),
                (Button)findViewById(R.id.btn_0),
                (Button)findViewById(R.id.btn_1),
                (Button)findViewById(R.id.btn_2),
                (Button)findViewById(R.id.btn_3),
                (Button)findViewById(R.id.btn_4),
                (Button)findViewById(R.id.btn_5),
                (Button)findViewById(R.id.btn_6),
                (Button)findViewById(R.id.btn_7),
                (Button)findViewById(R.id.btn_8),
                (Button)findViewById(R.id.btn_9)
        };
        for (Button button : buttons) {
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnNumClickListener != null) {
                        char num = ((Button) v).getText().toString().charAt(0);
                        mOnNumClickListener.onNumClick(v, num);
                    }
                }
            });
        }
    }
}
