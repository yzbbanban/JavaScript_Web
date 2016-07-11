package com.wangban.javascript_web;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
	private Button btnClick;
	private WebView webView;

	public void callJs(View view) {
		webView.loadUrl("javascript:setUsername()");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnClick = (Button) findViewById(R.id.ibtn_click);
		webView = (WebView) findViewById(R.id.webView);
		String url = "file:///android_asset/index.html";
		webView.loadUrl(url);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		MyPhone myPhone = new MyPhone();
		webView.addJavascriptInterface(myPhone, "abc");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class MyPhone {
		// 不加某些手机会报错
		@JavascriptInterface
		public void call(String mobile) {
			Uri uri = Uri.parse("tel:" + mobile);
			Intent intent = new Intent(Intent.ACTION_CALL, uri);
			startActivity(intent);
		}
	}

}
