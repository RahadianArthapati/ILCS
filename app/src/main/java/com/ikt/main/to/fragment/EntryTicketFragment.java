package com.ikt.main.to.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.ikt.main.to.R;
import com.ikt.main.to.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 3/14/16.
 */
public class EntryTicketFragment extends Fragment {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tabs)
    NavigationTabStrip tabs;

    private ViewPagerAdapter adapter;

    private String titles[] = new String[]{"Assigned", "Unassigned"};


    public EntryTicketFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_entry_ticket_layout, container, false);
        ButterKnife.bind(this, rootView);

        adapter = new ViewPagerAdapter(getChildFragmentManager(), titles);
        viewpager.setAdapter(adapter);
        /*
        tabs.setTextColor(getResources().getColor(android.R.color.white));
        tabs.setIndicatorColor(getResources().getColor(android.R.color.holo_red_dark));
        tabs.setIndicatorHeight(5);
        tabs.setShouldExpand(true);*/

        viewpager.setOffscreenPageLimit(2);
        tabs.setViewPager(viewpager);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_refresh).setVisible(true);
        menu.findItem(R.id.action_search).setVisible(true);
        menu.findItem(R.id.action_my_account).setVisible(false);
        menu.findItem(R.id.action_notif).setVisible(false);
        menu.findItem(R.id.action_language).setVisible(false);
        menu.findItem(R.id.action_sign_out).setVisible(false);
    }
}
