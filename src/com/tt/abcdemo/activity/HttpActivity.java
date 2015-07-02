package com.tt.abcdemo.activity;

import java.io.File;

import com.ab.activity.AbActivity;
import com.ab.fragment.AbAlertDialogFragment.AbDialogOnClickListener;
import com.ab.http.AbBinaryHttpResponseListener;
import com.ab.http.AbFileHttpResponseListener;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbFileUtil;
import com.ab.util.AbImageUtil;
import com.ab.util.AbToastUtil;
import com.tt.abcdemo.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HttpActivity extends AbActivity implements OnClickListener{

	private Button getBtn, postBtn, byteBtn, fileDownBtn, fileUpBtn;
	private AbHttpUtil mAbHttpUtil;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_http);
		initView();
		initListener();
		initHttp();		
	}

	private void initView() {
		getBtn = (Button) findViewById(R.id.getBtn);
		postBtn = (Button) findViewById(R.id.postBtn);
		byteBtn = (Button) findViewById(R.id.byteBtn);
		fileDownBtn = (Button) findViewById(R.id.fileDownBtn);
		fileUpBtn = (Button) findViewById(R.id.fileUpBtn);
	}

	private void initListener() {
		getBtn.setOnClickListener(this);
		postBtn.setOnClickListener(this);
		byteBtn.setOnClickListener(this);
		fileDownBtn.setOnClickListener(this);
		fileUpBtn.setOnClickListener(this);
	}

	private void initHttp() {
		mAbHttpUtil = AbHttpUtil.getInstance(this);
        mAbHttpUtil.setTimeout(10000);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.getBtn:
			doGetHttp();
			break;
		case R.id.postBtn:
			doPostHttp();
			break;
		case R.id.byteBtn:
			doByteBtn();
			break;
		case R.id.fileDownBtn:
			doFileDownBtn();
			break;
		case R.id.fileUpBtn:
			doFileUpBtn();
			break;
		}
	}

	private void doGetHttp() {
		String url = "http://www.amsoft.cn/rss.php"; 
		mAbHttpUtil.get(url, new AbStringHttpResponseListener(){
			@Override
			public void onSuccess(int statusCode, String content) {
				AbDialogUtil.showAlertDialog(HttpActivity.this,"返回结果",content.trim(),new AbDialogOnClickListener(){
					@Override
					public void onPositiveClick() {
						AbToastUtil.showToast(HttpActivity.this,"确认");
					}
					@Override
					public void onNegativeClick() {
						AbToastUtil.showToast(HttpActivity.this,"取消");
					}
            	});
			}
			@Override
			public void onStart() {
				AbDialogUtil.showProgressDialog(HttpActivity.this,0,"正在查询...");
			}
			@Override
			public void onFinish() {
				AbDialogUtil.removeDialog(HttpActivity.this);
			}
			@Override
			public void onFailure(int statusCode, String content, Throwable error) {
				AbToastUtil.showToast(HttpActivity.this,error.getMessage());
			}
		});
	}

	private void doPostHttp() {
		String url = "http://www.amsoft.cn/sort/10";
        AbRequestParams params = new AbRequestParams(); 
        params.put("param1", "1");
        params.put("param2", "2");
        params.put("param3", "10");
        mAbHttpUtil.post(url,params, new AbStringHttpResponseListener() {
			@Override
			public void onSuccess(int statusCode, String content) {
				AbDialogUtil.showAlertDialog(HttpActivity.this,"返回结果",content.trim(),new AbDialogOnClickListener(){
					@Override
					public void onPositiveClick() {
						AbToastUtil.showToast(HttpActivity.this,"确认");
					}
					@Override
					public void onNegativeClick() {
						AbToastUtil.showToast(HttpActivity.this,"取消");
					}
            	});
			}
			@Override
			public void onStart() {
				AbDialogUtil.showProgressDialog(HttpActivity.this,0,"正在查询...");
			}
			@Override
			public void onFinish() {
				AbDialogUtil.removeDialog(HttpActivity.this);
			}
			@Override
			public void onFailure(int statusCode, String content, Throwable error) {
				AbToastUtil.showToast(HttpActivity.this,error.getMessage());
			}
		});
	}

	private void doByteBtn() {
		String url = "http://www.amsoft.cn/content/templates/amsoft/images/rand/8.jpg";
		mAbHttpUtil.get(url, new AbBinaryHttpResponseListener() {
			@Override
			public void onSuccess(int statusCode, byte[] content) {
				Bitmap bitmap = AbImageUtil.bytes2Bimap(content);
            	ImageView view = new ImageView(HttpActivity.this);
            	view.setImageBitmap(bitmap);
				AbDialogUtil.showAlertDialog("返回结果", view, new AbDialogOnClickListener(){
					@Override
					public void onPositiveClick() {
						AbToastUtil.showToast(HttpActivity.this,"确认");
					}
					@Override
					public void onNegativeClick() {
						AbToastUtil.showToast(HttpActivity.this,"取消");
					}
            	});
			}
			@Override
			public void onStart() {
				AbDialogUtil.showProgressDialog(HttpActivity.this,0,"正在查询...");
			}
			@Override
			public void onFinish() {
				AbDialogUtil.removeDialog(HttpActivity.this);
			}
			@Override
			public void onFailure(int statusCode, String content, Throwable error) {
				AbToastUtil.showToast(HttpActivity.this,error.getMessage());
			}
		});
	}

	private void doFileDownBtn() {
		String url = "http://www.amsoft.cn/content/uploadfile/201311/38ed1385599018.jpg";
		mAbHttpUtil.get(url, new AbFileHttpResponseListener(url) {
			@Override
			public void onSuccess(int statusCode, File file) {
				Bitmap bitmap = AbFileUtil.getBitmapFromSD(file);
            	ImageView view = new ImageView(HttpActivity.this);
            	view.setImageBitmap(bitmap);
				AbDialogUtil.showAlertDialog("返回结果", view, new AbDialogOnClickListener(){
					@Override
					public void onPositiveClick() {
						AbToastUtil.showToast(HttpActivity.this,"确认");
					}
					@Override
					public void onNegativeClick() {
						AbToastUtil.showToast(HttpActivity.this,"取消");
					}
            	});
			}
			@Override
			public void onStart() {
				AbDialogUtil.showProgressDialog(HttpActivity.this,0,"正在查询...");
			}
			@Override
			public void onFinish() {
				AbDialogUtil.removeDialog(HttpActivity.this);
			}
			@Override
			public void onFailure(int statusCode, String content, Throwable error) {
				AbToastUtil.showToast(HttpActivity.this,error.getMessage());
			}
		});
	}

	private void doFileUpBtn() {
		// TODO Auto-generated method stub
		
	}

}
