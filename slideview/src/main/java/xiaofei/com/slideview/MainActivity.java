package xiaofei.com.slideview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, SlideView.OnSlideListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ListViewCompat mListView;

    private List<MessageItem> messageItems = new ArrayList<MainActivity.MessageItem>();

    private SlideAdapter mSlideAdapter;

    private SlideView mLastSlideViewWithStatusOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mListView = (ListViewCompat) findViewById(R.id.list);

        for(int i = 0; i < 200; i++){
            MessageItem item = new MessageItem();
            if (i % 3 == 0){
                item.iconRes = android.R.drawable.ic_menu_view;
                item.title = "新闻" + i;
                item.msg = "青岛大虾";
                item.time = "晚上8点";
            } else {
                item.iconRes = android.R.drawable.ic_menu_view;
                item.title = "微信" + i;
                item.msg = "北京烤鸭";
                item.time = "晚上9点";
            }
            messageItems.add(item);
        }
        mSlideAdapter = new SlideAdapter();
        mListView.setAdapter(mSlideAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemClick position=" + position);
    }

    @Override
    public void onSlide(View view, int status) {
        if(mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view){
            mLastSlideViewWithStatusOn.shrink();
        }
        if(status == SLIDE_STATUS_ON){
            mLastSlideViewWithStatusOn = (SlideView)view;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.holder){
            int position = mListView.getPositionForView(v);
            if(position != ListView.INVALID_POSITION){
                messageItems.remove(position);
                mSlideAdapter.notifyDataSetChanged();
            }
            Log.e(TAG, "onClick v=" + v.getTag());
        }
    }

    public class MessageItem {
        public int iconRes;
        public String time;
        public String msg;
        public String title;
        public SlideView slideView;
    }

    private class SlideAdapter extends BaseAdapter{
        private LayoutInflater mInflater;
        SlideAdapter(){
            super();
            mInflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return messageItems.size();
        }

        @Override
        public Object getItem(int position) {
            return messageItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            SlideView slideView = (SlideView)convertView;
            if(slideView == null){
                View itemView = mInflater.inflate(R.layout.list_item, null);
                slideView = new SlideView(MainActivity.this);
                slideView.setContentView(itemView);

                holder = new ViewHolder(slideView);
                slideView.setOnSlideListener(MainActivity.this);
                slideView.setTag(holder);
            } else{
                holder = (ViewHolder)slideView.getTag();
            }

            MessageItem item = messageItems.get(position);
            item.slideView = slideView;
            item.slideView.shrink();

            holder.title.setText(item.title);
            holder.deleteHolder.setOnClickListener(MainActivity.this);

            return slideView;
        }
    }

    public static class ViewHolder {
        public TextView title;
        public ViewGroup deleteHolder;

        @Override
        public String toString() {
            return title.getText().toString();
        }

        ViewHolder(View view){
            title = (TextView)view.findViewById(R.id.title);
            deleteHolder = (ViewGroup)view.findViewById(R.id.holder);
        }
    }

}
