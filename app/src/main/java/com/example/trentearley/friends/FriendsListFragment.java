package com.example.trentearley.friends;


import android.content.ContentResolver;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

/**
 * Created by te6933 on 2/16/2016.
 */
public class FriendsListFragment extends android.support.v4.app.ListFragment implements LoaderManager.LoaderCallbacks<List<Friend>>{

    private static final String LOG_TAG= FriendsListFragment.class.getSimpleName();
    private FriendsCustomAdapter mAdapter;
    private static final int LOADER_ID = 1;
    private ContentResolver mContentResolver;
    private List<Friend> mFriends;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mContentResolver = getActivity().getContentResolver();
        mAdapter = new FriendsCustomAdapter(getActivity(), getChildFragmentManager());
        setEmptyText("No Friends");
        setListAdapter(mAdapter);
        setListShown(false);

        getLoaderManager().initLoader(LOADER_ID, null,this);
    }

    @Override
    public Loader<List<Friend>> onCreateLoader(int id, Bundle args) {
        mContentResolver= getActivity().getContentResolver();
        return new FriendsListLoader(getActivity(), FriendsContract.URI_TABLE, mContentResolver);

    }

    @Override
    public void onLoadFinished(Loader<List<Friend>> loader, List<Friend> friends) {
        mAdapter.setData(friends);
        mFriends = friends;

    }

    @Override
    public void onLoaderReset(Loader<List<Friend>> loader) {
        mAdapter.setData(null);
    }
}
