package com.example.denistester;

/*import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;*/

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.net.MalformedURLException;











import com.gigya.socialize.GSObject;
import com.gigya.socialize.GSResponse;
import com.gigya.socialize.GSResponseListener;
import com.gigya.socialize.SigUtils;
import com.gigya.socialize.android.GSAPI;
import com.gigya.socialize.android.event.GSEventListener;
import com.gigya.socialize.android.event.GSLoginUIListener;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewFragment;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.Instrumentation;

public class AutoTests implements GSLoginUIListener, GSResponseListener {
	public MainActivity activity;
	public AutoTests(MainActivity activity) {
		this.activity = activity;
	}
	
	public void ds_store_with_big_object(){
	  	GSAPI.OPTION_TRACE=false;
		String json = null;
		GSObject par = null;
		      try {

		          	InputStream is = this.activity.getAssets().open("set.json");
		            int size = is.available();
		            byte[] buffer = new byte[size];
		            is.read(buffer);
		            is.close();
		            json = new String(buffer, "UTF-8");
		            try {
						par = new GSObject();
						par.put("userSaveGameAndroid", json); 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		        } 
		        catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    Log.d("denis","before ds.store call");
		    GSAPI.getInstance().sendRequest("ds.store", par, this.activity, null);
		    // MainActivity.api.sendRequest("ds.store", par, this.activity, null);
		}
	
		public boolean notifyLogin(String secret){
			long unixTime = System.currentTimeMillis();
			String uid = String.valueOf( unixTime);
			GSObject par = null;
			try {
				par = new GSObject("{\"siteUID\":\"" + uid + "\"}");
				//par.put("sessionExpiration","33");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			String signature = "";
			SigUtils sig = new SigUtils();
			
			try {
				signature = sig.getOAuth1Signature(uid +"_" + uid, secret);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			par.put("UIDSig", signature);
			par.put("UIDTimestamp", uid);
			//MainActivity.GSAPI.getInstance().sendRequest("socialize.notifyLogin", par, this.activity, null);
			
			Log.d("denis","notify login call");
		    GSAPI.getInstance().sendRequest("socialize.notifyLogin", par, this.activity, null);
		    
			return true;
		}

		public void silent_login_to_gplus()
		{
			GSObject par=null;
			try {
				par = new GSObject("{\"provider\":\"googleplus\",\"googleExtraPermissions\":\"https://www.googleapis.com/auth/gan\"}");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				//GSAPI.getInstance().login(par, this.activity, true, null);
				GSAPI.getInstance().login(this.activity,par,null,true,null);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		public void setIdofLoginUI(){
			
			new Thread(new Runnable() { 
	             public void run(){

	            	 
	            	 FragmentManager fragmentManager = null;
	            	 Fragment fragment = null;
	            	 
	     	    	while (fragment == null) {
	     	    		
	     	    		try {
	     					Thread.sleep(1000);
	     				} catch (InterruptedException e) {
	     					// TODO Auto-generated catch block
	     					e.printStackTrace();
	     				}
	     	    		fragment = (Fragment)fragmentManager.findFragmentByTag("GigyaWebViewFragment");
	     	    		uiid = fragment.getId();
	     	    		Log.d("denis", "From thread uiid = " + Integer.toString(uiid) );
	     	    	}
	     	    	

	             }
	    	}).start();
		}
		
		public int uiid;
		
		public void loginToProvider(String provider) {
			//new ActivityMonitor
			
			/*GSObject params = new GSObject();
	    	params.put("provider",provider); //for SSO
	    	try {
				GSAPI.getInstance().login(params, this, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			Log.d("denis", "Start loginUI");
			
	    	GSObject params = new GSObject();  	
	    	GSAPI.getInstance().showLoginUI(params, this, null);
	    	
	    	/*new Thread(new Runnable() { 
	             public void run(){

	            	 
	            	 FragmentManager fragmentManager = null;
	            	 Fragment fragment = null;
	            	 
	     	    	while (fragment == null) {
	     	    		
	     	    		try {
	     					Thread.sleep(1000);
	     				} catch (InterruptedException e) {
	     					// TODO Auto-generated catch block
	     					e.printStackTrace();
	     				}
	     	    		fragment = (Fragment)fragmentManager.findFragmentByTag("GigyaWebViewFragment");
	     	    		Log.d("denis", "FragmentID = " + Integer.toString(fragment.getId()) );
	     	    	}
	     	    	

	             }
	    	}).start();*/
	        
			/*WebView webView = (WebView) findViewById(R.id.webView);

		    WebSettings webSettings = webView.getSettings();
		    webSettings.setJavaScriptEnabled(true);

		    webView.setWebViewClient(new CustomWebViewClient());*/
			
	    	
	    	/*new Thread(new Runnable() { 
	             public void run(){

	            	 WebViewFragment wbf = null;
	     	    	
	     	    	while (wbf == null) {
	     	    		
	     	    		try {
	     					Thread.sleep(1000);
	     				} catch (InterruptedException e) {
	     					// TODO Auto-generated catch block
	     					e.printStackTrace();
	     				}
	     	    		wbf = (WebViewFragment)activity.getFragmentManager().findFragmentByTag("GigyaWebViewFragment");
	     	    	}

	             }
	    	}).start();*/
	    	
	    
	    	/*WebDriver driver = null;
	    			
	    	SelendroidCapabilities caps = new SelendroidCapabilities("io.selendroid.testapp:0.10.0");
	    	//driver = new SelendroidDriver(caps);
	    	try {
				driver = new SelendroidDriver(new SelendroidCapabilities("io.selendroid.testapp:0.9.0"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	 WebElement nameInput = driver.findElement(By.xpath("//EditText[@id='inputName']"));
			*/	
		}
		
		
			
		

		@Override
		public void onLogin(String arg0, GSObject arg1, Object arg2) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void onClose(boolean arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onError(GSResponse arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onLoad(Object arg0) {
			// TODO Auto-generated method stub
			Log.d("denis", "Load event");
			 
		}

		@Override
		public void onGSResponse(String arg0, GSResponse arg1, Object arg2) {
			// TODO Auto-generated method stub
			Log.d("denis", "response recieved");
		}

}
