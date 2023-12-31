package custom_views_and_styles;

/**
 * Created by Srinu on 07-03-2018.
 */

import android.support.annotation.ColorInt;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Created by danylo.volokh on 12/22/2015.
 * This class is a combination of {@link android.text.style.ForegroundColorSpan}
 * and {@link ClickableSpan}.
 *
 * You can set a color of this span plus set a click listener
 */
public class ClickableForegroundColorSpan extends ClickableSpan {

    private OnHashTagClickListener mOnHashTagClickListener;

    public interface OnHashTagClickListener {
        void onHashTagClicked(String hashTag,char sign);
    }

    private final int mColor;

    public ClickableForegroundColorSpan(@ColorInt int color, OnHashTagClickListener listener) {
        mColor = color;
        mOnHashTagClickListener = listener;

        if (mOnHashTagClickListener == null) {
            throw new RuntimeException("constructor, click listener not specified. Are you sure you need to use this class?");
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(mColor);
    }

    @Override
    public void onClick(View widget) {
        CharSequence text = ((TextView) widget).getText();

        Spanned s = (Spanned) text;
        int start = s.getSpanStart(this);
        int end = s.getSpanEnd(this);

        mOnHashTagClickListener.onHashTagClicked(text.subSequence(start + 1/*skip "#" sign*/, end).toString(),text.charAt(start));
    }
}