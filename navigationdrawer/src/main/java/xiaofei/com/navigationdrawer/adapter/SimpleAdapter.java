package xiaofei.com.navigationdrawer.adapter;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xiaofei.com.navigationdrawer.R;
import xiaofei.com.navigationdrawer.fragment.SimpleFragment;

/**
 * Created by xiaofei on 2016/1/31.
 */
public class SimpleAdapter extends FragmentPagerAdapter {
    private static final String[] TITLE = {"Tiffany", "Taeyeon", "Yoona"};
    private static final Section[] SECTIONS = {
            new Section("Tiffany", R.drawable.tiffany),
            new Section("Taeyeon", R.drawable.taeyeon),
            new Section("Yoona", R.drawable.yoona)
    };
    public SimpleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SimpleFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position >= 0 && position < TITLE.length ? TITLE[position] : null;
    }

    @DrawableRes
    public int getDrawable(int position){
        return position >= 0 && position < SECTIONS.length ? SECTIONS[position].getDrawable() : -1;
    }

    private static final class Section {
        private final String mTitle; // 标题
        @DrawableRes private final int mDrawable; // 图片

        public Section(String title, int drawable) {
            mTitle = title;
            mDrawable = drawable;
        }

        public String getTitle() {
            return mTitle;
        }

        public int getDrawable() {
            return mDrawable;
        }
    }
}
