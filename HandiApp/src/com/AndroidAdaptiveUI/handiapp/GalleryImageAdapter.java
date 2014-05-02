package com.AndroidAdaptiveUI.handiapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryImageAdapter extends BaseAdapter {

	private Context mContext;

    private Integer[] Imgid = {
            R.drawable.wallpaper1,
            R.drawable.wallpaper2,
            R.drawable.wallpaper3,
            R.drawable.wallpaper4
    };
	
    public GalleryImageAdapter(Context context) 
    {
        mContext = context;
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Imgid.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 ImageView img = new ImageView(mContext);

	        img.setImageResource(Imgid[index]);
	        img.setLayoutParams(new Gallery.LayoutParams(200, 200));
	    
	        img.setScaleType(ImageView.ScaleType.FIT_XY);

	        return img;
	}
	
}
