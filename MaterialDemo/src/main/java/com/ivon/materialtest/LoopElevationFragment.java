package com.ivon.materialtest;

/**
 * Created by root on 27/06/14.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoopElevationFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    CardView mCardView;
    TextView mTextView;
    static boolean looping = false;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LoopElevationFragment newInstance() {
        LoopElevationFragment fragment = new LoopElevationFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);
        return fragment;
    }

    public LoopElevationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_cardelevation, container, false);

        mCardView = (CardView) rootView.findViewById(R.id.card_view);
        mTextView = (TextView) rootView.findViewById(R.id.text_view1);

        mCardView.setElevation(0);
        mTextView.setText("Elevation: 0.0");

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //((MainActivity) activity).onSectionAttached(
        //        getArguments().getInt(ARG_SECTION_NUMBER));
        ((MainActivity) activity).onSectionAttached(R.string.title_section1);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem mToggleItem = menu.findItem(R.id.action_toggle);
        if(mToggleItem != null)
            mToggleItem.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {
            if(!looping) {
                looping = true;
                Toast.makeText(getActivity(), "starting loop", Toast.LENGTH_SHORT).show();
                loopElevation();
            } else {
                looping = false;
                Toast.makeText(getActivity(), "stopping loop", Toast.LENGTH_SHORT).show();
                mCardView.setElevation(0);
                mTextView.setText("Elevation: 0.0");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void loopElevation() {
        MessageHandler mHandler = new MessageHandler(this);
        Message mMessage = mHandler.obtainMessage(1);
        mHandler.sendMessage(mMessage);
    }

    private static class MessageHandler extends Handler {

        private WeakReference<LoopElevationFragment> mWeakReference;
        boolean increasing = true;

        private final int MAX_ELEVATION = 100;
        private final int MIN_ELEVATION = 0;

        public MessageHandler(LoopElevationFragment fragment) {
            mWeakReference = new WeakReference<LoopElevationFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {

            if(!LoopElevationFragment.looping) {
                return;
            }

            LoopElevationFragment mFragment = mWeakReference.get();

            if(mFragment == null) {
                return;
            }

            CardView mCardView = mFragment.mCardView;
            TextView mTextView = mFragment.mTextView;

            float prevElevation = mCardView.getElevation();

            if(prevElevation == MAX_ELEVATION) increasing = false;
            if(prevElevation == MIN_ELEVATION) increasing = true;

            float newElevation;
            if(increasing) {
                newElevation = prevElevation+10;
            } else {
                newElevation = prevElevation-10;
            }

            mCardView.setElevation(newElevation);
            mTextView.setText("Elevation: " + newElevation);

            sendMessageDelayed(obtainMessage(1), 2000);
        }
    }
}
