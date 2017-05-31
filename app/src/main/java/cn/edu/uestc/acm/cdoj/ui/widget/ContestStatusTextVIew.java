package cn.edu.uestc.acm.cdoj.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import cn.edu.uestc.acm.cdoj.R;

/**
 * Created by QK on 2016/9/18.
 */
public class ContestStatusTextVIew extends AppCompatTextView {
    public ContestStatusTextVIew(Context context) {
        super(context);
    }

    public ContestStatusTextVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContestStatusTextVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (text.equals("Ended")) {
            setTextColor(ContextCompat.getColor(getContext(), R.color.contestStatus_Ended));
            return;
        }
        if (text.equals("Running"))
            setTextColor(ContextCompat.getColor(getContext(), R.color.contestStatus_Running));
    }
}
