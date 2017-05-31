package cn.edu.uestc.acm.cdoj.ui.home;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.uestc.acm.cdoj.R;
import cn.edu.uestc.acm.cdoj.resources.ResourceFactory;
import cn.edu.uestc.acm.cdoj.ui.home.data.ContestListData;
import cn.edu.uestc.acm.cdoj.ui.home.data.NoticeListData;
import cn.edu.uestc.acm.cdoj.ui.home.data.ProblemListData;
import cn.edu.uestc.acm.cdoj.ui.widget.GestureList.GestureListFragment;

/**
 * Created by leigu on 2017/4/19.
 */

public class HomePage {
    private static boolean hasInit;
    private static String TAG = "HomePage";

    private static Fragment instance;


    public static View initHomePage(Activity activity) {
        if (hasInit) {
            throw new IllegalStateException("home page only can create one");
        }
        View mainView;
        ViewPager mViewPager;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(R.layout.activity_home_page, (ViewGroup) activity.getWindow().getDecorView(), false);
        mViewPager = (ViewPager) mainView.findViewById(R.id.pager);

        Fragment[] lists = new Fragment[3];
        lists[0] = initNoticeList(activity);
        lists[1] = initProblemList(activity);
        lists[2] = initContestList(activity);
        ListPagerAdapter listPagerAdapter = new ListPagerAdapter(activity.getFragmentManager(), lists);
        mViewPager.setAdapter(listPagerAdapter);

        initViewPager(mViewPager, (TabLayout) mainView.findViewById(R.id.tab));
        hasInit = true;
        return mainView;
    }

    private static Fragment initNoticeList(Context context) {
        GestureListFragment listFragment = new GestureListFragment();
        new NoticeListData(context).setupList(listFragment);
        return listFragment;
    }

    private static Fragment initProblemList(Context context) {
        GestureListFragment listFragment = new GestureListFragment();
        new ProblemListData(context).setupList(listFragment);
        return listFragment;
    }

    private static Fragment initContestList(Context context) {
        GestureListFragment listFragment = new GestureListFragment();
        new ContestListData(context).setupList(listFragment);
        return listFragment;
    }

    private static void initViewPager(ViewPager viewPager, TabLayout tabLayout) {
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new PageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        initTabIcon(tabLayout);
    }

    private static void initTabIcon(TabLayout tabLayout) {
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (tab != null) {
            tab.setIcon(ResourceFactory.getIcon().getNoticeListIcon_selected());
        }
        tab = tabLayout.getTabAt(1);
        if (tab != null) {
            tab.setIcon(ResourceFactory.getIcon().getProblemListIcon_unselect());
        }
        tab = tabLayout.getTabAt(2);
        if (tab != null) {
            tab.setIcon(ResourceFactory.getIcon().getContestListIcon_unselect());
        }
    }

    private static class ListPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] fragments;

        ListPagerAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    private static class PageChangeListener implements ViewPager.OnPageChangeListener {

        private TabLayout tabLayout;

        PageChangeListener(TabLayout tabLayout) {
            this.tabLayout = tabLayout;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changeCurrentTabIcon(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        private void changeCurrentTabIcon(int position) {
            TabLayout.Tab tab = tabLayout.getTabAt(0);
            if (tab != null) {
                if (position == 0) {
                    tab.setIcon(ResourceFactory.getIcon().getNoticeListIcon_selected());
                } else {
                    tab.setIcon(ResourceFactory.getIcon().getNoticeListIcon_unselect());
                }
            }
            tab = tabLayout.getTabAt(1);
            if (tab != null) {
                if (position == 1) {
                    tab.setIcon(ResourceFactory.getIcon().getProblemListIcon_selected());
                }else {
                    tab.setIcon(ResourceFactory.getIcon().getProblemListIcon_unselect());
                }
            }
            tab = tabLayout.getTabAt(2);
            if (tab != null) {
                if (position == 2) {
                    tab.setIcon(ResourceFactory.getIcon().getContestListIcon_selected());
                }else {
                    tab.setIcon(ResourceFactory.getIcon().getContestListIcon_unselect());
                }
            }
        }
    }
}
