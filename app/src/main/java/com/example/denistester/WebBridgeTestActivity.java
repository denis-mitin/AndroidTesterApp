package com.example.denistester;

import com.gigya.socialize.android.GSWebBridge;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebBridgeTestActivity extends Activity {
	private boolean loaded = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_webbridge);
	    // TODO Auto-generated method stub
	}

	@Override
	protected void onResume () {
		super.onResume();

		if (!loaded) {
			loaded = true;
			final WebView webview = (WebView)findViewById(R.id.webView);
			webview.getSettings().setJavaScriptEnabled(true);
			try {
				//final WebView webview = new WebView(this);
				webview.setWebViewClient(new WebViewClient(){
					@Override
					public boolean shouldOverrideUrlLoading (WebView view, String url) {
						if (GSWebBridge.handleUrl(webview, url)) {
							return true;
						}

						return false;
					}
				});
				//webview.loadUrl("http://qa-app6.sociallogin.org/QA/site/denis/oauth2/");
				//webview.loadUrl("http://suntimes.com/");
				webview.loadUrl("http://sociallogin.org/QA/site/denis/oauth2/jssdk/");
				//webview.loadUrl("http://demo.gigya.com/");
				//webview.loadUrl("http://nhl.com/");
				//webview.loadUrl("http://tfa.com/QA/site/qagigya/env/Production/tfa/tfa.htm");
				//webview.loadUrl("http://sociallogin.org/QA/site/andrey/mobile/href.htm?apikey=3_wJo2JBfQO09OzjVvvt6F-0oEd8igVPLLRRXXqgNGvOkQyVUP9wWZxg_kIEMnbE9n");
				//webview.loadUrl("http://rba.com/qa/site/andrey/rba/saml/gigyaLogin.html?apikey=3_aemEgF84qcIewz8jXNdpxk85BWIURmn8d_Ma48m5u5iqj2yyValwKl7xab7YMBJV");
				//webview.loadUrl("http://wildfiresocial.com/qa/site/inbal/CommentsUI/CommentsUI.htm");
			//http://rba.com/qa/site/andrey/rba/saml/gigyaLogin.html?apikey=3_cUJw_2bcsy9uLQLlv2aj-6WJmlZZ6BPiALfmLYlIxQL7ru9sRjzvmtn7M6rPA4Ex#

				//GSWebBridge.attach(webview, null);
				GSWebBridge.attach(this,webview,null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("denis",e.toString());
			}
		}


	}
}
