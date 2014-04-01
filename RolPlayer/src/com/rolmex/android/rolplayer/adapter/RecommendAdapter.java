
package com.rolmex.android.rolplayer.adapter;

import com.rolmex.android.rolplayer.R;
import com.rolmex.android.rolplayer.adapter.ItemAdapter.ViewHolder;
import com.rolmex.android.rolplayer.comment.BitmapManager;
import com.rolmex.android.rolplayer.model.CommendItem;
import com.rolmex.android.rolplayer.model.HotItemBean;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecommendAdapter extends BaseAdapter {
    private List<CommendItem> list;

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private int width;

    private BitmapManager mBitmapManager;

    static class ViewHolder {
        ImageView item_image;

        TextView item_name;
    }

    public RecommendAdapter(Context context, List<CommendItem> list) {
        this.mContext = context;
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels / 3 - 30;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.list = list;
        mBitmapManager = new BitmapManager();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private int getHeight(int width) {
        return width * 13 / 25;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_item, null);
            viewHolder = new ViewHolder();
            viewHolder.item_image = (ImageView)convertView.findViewById(R.id.item_imag);
            viewHolder.item_name = (TextView)convertView.findViewById(R.id.item_name);

            viewHolder.item_image.setLayoutParams(new LinearLayout.LayoutParams(width,
                    getHeight(width)));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        CommendItem bean = list.get(position);
        mBitmapManager.loadBitmap(bean.pic, viewHolder.item_image);
        viewHolder.item_name.setText(bean.title);
        return convertView;
    }

}
