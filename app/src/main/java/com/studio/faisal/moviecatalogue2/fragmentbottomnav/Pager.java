package com.studio.faisal.moviecatalogue2.fragmentbottomnav;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.studio.faisal.moviecatalogue2.tabfavorite.TabMovie;
import com.studio.faisal.moviecatalogue2.tabfavorite.TabTV;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    public int tabCount;

    //Constructor to the class
    Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return new TabMovie();
            case 1:
                return new TabTV();
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
