package xiaofei.com.slideview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by xiaofei on 2015/12/26.
 */
public class ListViewCompat extends ListView {
    private SlideView mFocusedItemView;

    public ListViewCompat(Context context) {
        super(context);
    }

    public ListViewCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                int position = pointToPosition(x, y);
                Log.e("listview", "position=" + position);
                if(position != INVALID_POSITION){
                    MainActivity.MessageItem data = (MainActivity.MessageItem) getItemAtPosition(position);
                    mFocusedItemView = data.slideView;
                    Log.e("listview", "FocusedItemView=" + mFocusedItemView.getTag().toString());
                }
            default:
                break;
        }
        if(mFocusedItemView != null){
            mFocusedItemView.onRequireTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }
}
