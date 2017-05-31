package cn.edu.uestc.acm.cdoj.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import cn.edu.uestc.acm.cdoj.R;

/**
 * Created by Great on 2016/9/22.
 */

public class ProblemSubmitResultTextView extends AppCompatTextView {
    public ProblemSubmitResultTextView(Context context) {
        super(context);
    }

    public ProblemSubmitResultTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProblemSubmitResultTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (text.equals("Accepted")){
            setTextColor(ContextCompat.getColor(getContext(), R.color.green));
            return;
        }
        setTextColor(ContextCompat.getColor(getContext(), R.color.red));
    }
}
