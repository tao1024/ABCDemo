package com.tt.abcdemo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.ab.activity.AbActivity;
import com.tt.abcdemo.R;
import com.tt.abcdemo.adapter.ListViewAdapter;

public class MainActivity extends AbActivity implements OnClickListener{

	private ListView mListView = null;
	private List<Map<String,Object>> mList = null;
	private Map<String ,Object> mMap = null;
	private ListViewAdapter mListViewAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_main);
		initView();
		initListener();
		initData();
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.mListView);
		
	}

	private void initListener() {
		// TODO Auto-generated method stub
		
	}

	private void initData() {
		mList = new ArrayList<Map<String,Object>>();
		mMap = new HashMap<String,Object>();
		
		mMap.put("itemsTitle", "AbActivity基本用法");
		mMap.put("itemsText", "AbActivity使用示例");
		mList.add(mMap);
		
//		mMap.clear();
		mMap.put("itemsTitle", "AbActivity基本用法2");
		mMap.put("itemsText", "AbActivity使用示例2");
		mList.add(mMap);
		
		mListViewAdapter = new ListViewAdapter(MainActivity.this, mList, R.layout.item_list, new String[]{"itemsTitle","itemsText"}, new int[]{R.id.itemsTitle,R.id.itemsText});
		mListView.setAdapter(mListViewAdapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
