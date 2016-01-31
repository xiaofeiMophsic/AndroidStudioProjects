package xiaofei.com.navigationdrawer.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import xiaofei.com.navigationdrawer.R;

/**
 * Created by xiaofei on 2016/1/31.
 */
public class SimpleFragment extends Fragment {

    private static final String ARG_SELECTION_NUM = "arg_selection_num";
    private static final String ARG_SELECTION_CONTENT = "arg_selection_content";

    @Bind(R.id.main_tv_text)
    TextView mTvText;

    public SimpleFragment() {}

    public static SimpleFragment newInstance(int selectionNum, int content){

        SimpleFragment simpleFragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SELECTION_NUM, selectionNum);
        args.putInt(ARG_SELECTION_CONTENT, content);
        simpleFragment.setArguments(args);
        return simpleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvText.setText(getArguments().getInt(ARG_SELECTION_CONTENT));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
