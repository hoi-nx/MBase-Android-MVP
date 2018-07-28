package com.mybase.mvp.base.adapter;

import android.view.View;

import com.mybase.mvp.interfaces.IBaseList;


/**
 * Created by Heart Of Dead on 14/01/2018.
 */

public abstract class MBaseListViewListViewAdapter<T extends IBaseList,O>extends MListViewAdapter<T,MViewHolder,O> {
    public MBaseListViewListViewAdapter(T mList) {
        super(mList);
    }

    @Override
    public MViewHolder buildHolder(View convertView, O t, int position) {
        return new MViewHolder(convertView);
    }
}
