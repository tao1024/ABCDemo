package com.tt.abcdemo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ab.activity.AbActivity;
import com.ab.util.AbToastUtil;
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
		initData();
		initListener();
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.mListView);
		
	}

	private void initData() {
		mList = new ArrayList<Map<String,Object>>();
		mMap = new HashMap<String,Object>();
		
		mMap.put("itemsTitle", "AbActivity基本用法");
		mMap.put("itemsText", "AbActivity使用示例");
		mList.add(mMap);
		
		mMap.put("itemsTitle", "HttpActivity基本用法");
		mMap.put("itemsText", "HttpActivity使用示例");
		mList.add(mMap);
		
		mMap.put("itemsTitle", "ListViewActivity基本用法");
		mMap.put("itemsText", "ListViewActivity使用示例");
		mList.add(mMap);
		
		mListViewAdapter = new ListViewAdapter(MainActivity.this, mList, R.layout.item_list, new String[]{"itemsTitle","itemsText"}, new int[]{R.id.itemsTitle,R.id.itemsText});
		mListView.setAdapter(mListViewAdapter);
	}
	
	private void initListener() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent();
				switch(position){
				case 0:
					AbToastUtil.showToast(MainActivity.this, mList.get(position).get("itemsTitle").toString());
					AbToastUtil.showToast(MainActivity.this, mList.get(position).get("itemsText").toString());
					break;
				case 1:
					intent.setClass(MainActivity.this, HttpActivity.class);
					startActivity(intent);
					break;
//				case 2:
//					intent.setClass(MainActivity.this, ListViewActivity.class);
//					startActivity(intent);
//					break;
				}
				
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
