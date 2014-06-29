package com.ivon.materialtest;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by root on 27/06/14.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private DataObject[] mDataset;

    public static class DataObject {
        final String title;
        final String subtitle;

        public DataObject(String title, String subtitle) {
            this.title = title;
            this.subtitle = subtitle;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        TextView mTitleView;
        TextView mSubtitleView;

        public ViewHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.rc_card);
            mTitleView = (TextView) itemView.findViewById(R.id.rc_title);
            mSubtitleView = (TextView) itemView.findViewById(R.id.rc_subtitle);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(DataObject[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                               .inflate(R.layout.recycler_card, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        float elevation = 0;
        if(i%2 == 0) {
            elevation = 30;
        } else {
            elevation = 10;
        }
        ViewUtils.setElevation(viewHolder.mCardView, elevation);
        viewHolder.mTitleView.setText(mDataset[i].title);
        viewHolder.mSubtitleView.setText(mDataset[i].subtitle);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
