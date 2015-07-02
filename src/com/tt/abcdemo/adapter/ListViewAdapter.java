package com.tt.abcdemo.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<? extends Map<String,?>> mData;
	private int mResource;
	private String[] mFrom;
	private int[] mTo;
	
	public ListViewAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to){
		mContext = context;
		mData = data;
		mResource = resource;
		mFrom = from;
		mTo = to;
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final ViewHolder holder;
		
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
			holder = new ViewHolder();
			holder.itemTitle = (TextView) convertView.findViewById(mTo[0]);
			holder.itemText = (TextView) convertView.findViewById(mTo[1]);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		final Map<String,?> itemMap = mData.get(position);
		if(itemMap == null){
			return null;
		}
		final Object itemMapTitle = itemMap.get(mFrom[0]);
		final Object itemMapText = itemMap.get(mFrom[1]);
		
		holder.itemTitle.setText(String.valueOf(position + 1)+ "." +itemMapTitle.toString());
		holder.itemText.setText(itemMapText.toString());
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView itemTitle, itemText;
	}

}
