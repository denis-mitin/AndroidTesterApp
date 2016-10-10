package com.example.denistester;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gigya.socialize.GSObject;
import com.gigya.socialize.android.GSPermissionResultHandler;
import com.gigya.socialize.android.GSWebBridge;
import com.gigya.socialize.GSResponse;
import com.gigya.socialize.GSResponseListener;
import com.gigya.socialize.android.GSAPI;
import com.gigya.socialize.android.event.GSAccountsEventListener;
import com.gigya.socialize.android.event.GSConnectUIListener;
import com.gigya.socialize.android.event.GSDialogListener;
import com.gigya.socialize.android.event.GSEventListener;
import com.gigya.socialize.android.event.GSLoginUIListener;
import com.gigya.socialize.android.event.GSSocializeEventListener;
import com.gigya.socialize.android.event.GSPluginListener;
import com.gigya.socialize.android.GSPluginFragment;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements GSLoginUIListener, GSResponseListener, OnItemSelectedListener, 
GSConnectUIListener,GSPermissionResultHandler,GSEventListener 
{

	// public static GSAPI api;
	public EditText myText;
	public EditText method;
	public EditText paramsText;
	public Spinner mSpin;
	public int errorCode = 0;
	public String regToken;
	public String secretKey = "WyULW7D5Kw77hZlD7d/pVBA97YwL1LL7DVk4/jutnHs="; //sociallogin.org, prod
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		bindAll();

		if(savedInstanceState != null )
			return;

		GSAPI.__DEBUG_OPTION_ENABLE_TEST_NETWORKS = true;

       // GSAPI.getInstance().initialize(this, "3_xDBay1MzaIhCR9fwEW-m73sNdF8sSPzTqeGIXKjRcAqKVE4kMHy0cARbeTwCsEfK","eu1.gigya.com"); // eu site
        
       GSAPI.getInstance().initialize(this, "3_wJo2JBfQO09OzjVvvt6F-0oEd8igVPLLRRXXqgNGvOkQyVUP9wWZxg_kIEMnbE9n", "us1.gigya.com"); //sociallogin.org, prod FBapp: 176663209146906
		//GSAPI.getInstance().initialize(this, "3_PrJU94R8nIylcnsY0nyf1fQgq62FHZ7bMOI-73sUZHw_aM_uvj9IS-tODCSfHPw9", "us1.gigya.com"); //sociallogin.org, il1a FBapp: 176663209146906
		 //GSAPI.getInstance().initialize(this, "3_3kIXdIsXW8dHcP3Gq90nmwFLjjUUbei_sUc6UFnCd8b81ELFW_-aZppsoC2idcoL", "us1.gigya.com"); //rba.com
		//GSAPI.getInstance().initialize(this, "2_NGWPcIQU-iNWg06QwYu3Y9wDseMse_9AeQMBhfaLzjhTYIjUaFDO1vwri0v8Ga3R", "us1.gigya.com"); //qagroup.com v2 app, prod FBapp:771616246230009
       
      //GSAPI.getInstance().initialize(this, "3_hnzloUppwx6FLJ5FvRgSPKzjqqv93nGYta4DiToY-eWxusL017zgDjoW_1kWV3Ve", "us1.gigya.com"); //sociallogin.org, Local ST
       //GSAPI.getInstance().initialize(this, "3_rnylxaXmqRFLbILkPIV1cQJhIvlMOwjcFSmKOQiuWxPmd5KBKOP_LUdfS6z_luEh", "us1.gigya.com"); // suntimes
        //GSAPI.getInstance().initialize(this, "2_EWWT3Zd9zqdy7qjHXV91o6mYy8TLdfd_kCQydHG2to70MPdklSd4P7wAd8n2B4yP", "us1.gigya.com"); // demo.gigya.com
        //GSAPI.getInstance().initialize(this, "2_pd9mRZHRYIukd6DpOMReSfqTpuF0K4eHN_cz3hYmMGx8zmPE3ObXwGQCSKZ4FS1O", "us1.gigya.com"); //nhl.com
        //GSAPI.getInstance().initialize(this, "2_NGWPcIQU-iNWg06QwYu3Y9wDseMse_9AeQMBhfaLzjhTYIjUaFDO1vwri0v8Ga3R", "us1.gigya.com"); //qagroup.org,
       
        //GSAPI.getInstance().initialize(this, "3_CljQA1PeyX2OA3fMMznRVzCJUlyUl-PRc4twmGjZGhFQBIxNSxeRPQRlfheW0QzB", "us1.gigya.com"); //andrey.com ST2
		//GSAPI.getInstance().OPTION_TRACE=true;
       // GSAPI.getInstance().OPTION_SHOW_PROGRESS_ON_REQUEST=true;
       // bug GSAPI.getInstance().OPTION_SHOW_PROGRESS_ON_NAVIGATION=true;
        GSAPI.OPTION_CHECK_CONNECTIVITY = true;

		GSAPI.getInstance().setLoginBehavior(GSAPI.LoginBehavior.WEBVIEW_DIALOG);
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
		WebView.setWebContentsDebuggingEnabled(true);


        // before 3.0
        // api = new GSAPI("2_DJDxNTrvx_QC313MDs_6Byos-ua1lHs5S1-fH32d-MznZn-gLccOid6IYt1D2f26", this); // bsocialize.com
        // api = new GSAPI("2_NGWPcIQU-iNWg06QwYu3Y9wDseMse_9AeQMBhfaLzjhTYIjUaFDO1vwri0v8Ga3R", this); // qagroup.com, prod 210607919054593
        //api = new GSAPI("3_hnzloUppwx6FLJ5FvRgSPKzjqqv93nGYta4DiToY-eWxusL017zgDjoW_1kWV3Ve", this); // sociallogin.org, local
       // GSAPI.getInstance().setAPIDomain("us1.gigya.com");
        //GSAPI.getInstance().setEventListener(this);
        // GSAPI.getInstance().OPTION_IGNORE_SSL_ERROR=true;
          
		try {
		    PackageInfo info = getPackageManager().getPackageInfo("com.example.denistester", PackageManager.GET_SIGNATURES);
		    for (Signature signature : info.signatures) {
	           MessageDigest md = MessageDigest.getInstance("SHA");
	           md.update(signature.toByteArray());
	           Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
	        }
		} catch (NameNotFoundException e) {
			
		} catch (NoSuchAlgorithmException e) {
		}	    
		
		GSAPI.getInstance().setSocializeEventListener(new GSSocializeEventListener() {
			
			@Override
			public void onLogout(Object arg0) {
				logtext("\nlogout event from socialize\n");
			}
			
			@Override
			public void onLogin(String provider, GSObject user, Object context) {
				logtext(provider + " from socialize event handler response: " + user.toJsonString());
			}
			
			@Override
			public void onConnectionRemoved(String provider, Object context) {
				logtext( provider + " connection removed from socialize event handler");
				
			}
			
			@Override
			public void onConnectionAdded(String provider, GSObject user, Object context) {
				logtext( provider + " connection added from socialize event handler response: " + user.toJsonString());
				
			}


		});
		

		
		GSAPI.getInstance().setAccountsEventListener(new GSAccountsEventListener(){

			@Override
			public void onLogin(GSObject account, Object context) {
				logtext( "from accounts event handler response: " + account.toJsonString());
				
			}

			@Override
			 public void onLogout(Object arg0) {
				logtext("\nlogout event from accounts\n");

			}
			
			
			
		});
    }

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		if (GSAPI.getInstance().handleAndroidPermissionsResult(requestCode, permissions, grantResults))
			return;

		// handle other permissions result here
	}

    @Override
    protected void onResume() {
        super.onResume();
        // Send deep link
    	GSAPI.getInstance().reportDeepLink(getIntent());

    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		GSAPI.getInstance().reportDeepLink(intent);

	}
    public void logtext(String txt) {
    	myText.setText( txt);
		Log.d("denis", txt);
    }
    
    
    public void onItemSelected(AdapterView<?> main, View view, int position,
            long Id) {
 
        String item = main.getItemAtPosition(position).toString();
        method.setText(item);
		if(item == "accounts.screenSet")
		{
			// Array of choices
			String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};

// Selection of the spinner
			//Spinner spinner = (Spinner) findViewById(R.id.SpinnerScreenset);

// Application of the Array to the Spinner
		//	ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colors);
		//	spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		//	spinner.setAdapter(spinnerArrayAdapter);

		}

        
        String[] params = getResources().getStringArray(R.array.params_array);
        paramsText.setText(params[position]);
    }
    
    public void onNothingSelected(AdapterView<?> parent) {
    // Another interface callback
    }
    
    private void bindAll() {
        myText = (EditText) this.findViewById(R.id.output);
        method = (EditText) this.findViewById(R.id.methodName);
        paramsText = (EditText) this.findViewById(R.id.paramsJson);
        mSpin = (Spinner) findViewById(R.id.methodSel);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.methods_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpin.setAdapter(adapter);
        
        Log.d("denis","before set listener ");
        mSpin.setOnItemSelectedListener(this);
    }
    

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onShowLoginUIClick(View view) {
    	showLoginUI();
    }
    
    public void showLoginUI(){
    	
    	GSObject params = new GSObject();
    	//params.put("enabledProviders", "facebook,twitter,googleplus,google,linkedin");
    	params.put("facebookLoginBehavior", "NATIVE_ONLY");//NATIVE_ONLY,WEB_ONLY,NATIVE_WITH_FALLBACK
    	params.put("facebookExtraPermissions","rsvp_event,sms");
    	params.put("facebookReadPermissions","user_events"); //for SSO
    	params.put("googlePlusExtraPermissions","https://www.googleapis.com/auth/adsense");
		//params.put("pendingRegistration",true);
    	//params.put("forceAuthentication",true);
    	params.put("captionText","My Login");
    	params.put("regSource","login UI");
		params.put("enabledProviders","*,testnetwork3,testnetwork4");
		//params.put("sessionExpiration","45");
    	
    	logtext("calling loginUI");
    	GSAPI.getInstance().showLoginUI(params, this, null);
    	//GSAPI.getInstance().showLoginUI(null, null, null);
    }
    
    public void onShowConnectUIClick(View view) {
    	GSObject params = new GSObject();
    	//params.put("enabledProviders", "facebook,twitter,googleplus,google,linkedin");
    	params.put("facebookExtraPermissions","rsvp_event,sms");
    	params.put("facebookReadPermissions","user_events"); //for SSO
    	params.put("googlePlusExtraPermissions","https://www.googleapis.com/auth/adsense");
    	params.put("pendingRegistration",true);
    	params.put("forceAuthentication",true);
    	params.put("captionText", "My Connect юникод");
		//params.put("sessionExpiration","45");
    	
    	//GSAPI.getInstance().showAddConnectionsUI(params, this, null);
    	//GSAPI.getInstance().showAddConnectionsUI(params, null, null);
		GSAPI.getInstance().showAddConnectionsUI(params, this, null);
    }
    
    public void onStartAutoClick(View view) {
    	AutoTests a = new AutoTests(this);

    	//login from loginUI
    	//a.loginToProvider("twitter");
    	
    	callMethod("accounts.initRegistration", "");
    	
    	//1. notifyLogin 
    	a.notifyLogin(secretKey);
    	
    	//2.
    	//a.ds_store_with_big_object();
    	
    	//3. silent login to G+
    	a.silent_login_to_gplus();
    	
    	//4. par.put("noAuth", true);
    	callMethod("accounts.resetPassword", "{\"noAuth\":true,\"loginID\":\"cefrrqka@gmail.com\"}");
    	
    	//5. register a new user
    	try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	callMethod("accounts.register","{\"finalizeRegistration\":true,\"password\":\"Q123456w\" }");
    	
    }
    
    public void onCallMethodClick(View view) {
    	callMethod(method.getText().toString(),paramsText.getText().toString());
    }
    
    public void callMethod(String m, String p){
    	
    	/*if(m == "") {
			m = method.getText().toString();
		}
    	
    	if(p == "") {
    		p = paramsText.getText().toString();
		}*/
    	
    	Log.d("denis","Calling " + m + " params = " + p);
    	myText.setText( "Calling " + m + " params = " + p);
    	GSObject par = null;
		try {
			par = new GSObject(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if ( m.equals( "accounts.register"))
		{
    		long ut = System.currentTimeMillis();
    		//par.put("username", ut);
    		par.put("email", ut + "@mail.com");
    		par.put("regToken", regToken);
    		par.put("password", "Q123456w");
    		paramsText.setText(par.toString());
    		GSAPI.getInstance().sendRequest(m, par, true, this, null);
		}
		else if( m.equals( "accounts.login"))
		{
    		GSAPI.getInstance().sendRequest(m, par, true, this, null);
		}
		else if ( m.equals("socialize.login")) {
			try {
				//GSAPI.getInstance().login(par, this, null);
				GSAPI.getInstance().login(this, par, null, null);
				//GSAPI.getInstance().login(par, this, true, null);
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("denis",e.toString());
			}
		}
		else if ( m.equals("socialize.addConnection")) {
			try {
				//GSAPI.getInstance().addConnection(par, this, null);
				GSAPI.getInstance().addConnection(this,par, this, null);
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("denis",e.toString());
			}
		}
		else if ( m.equals("socialize.logout") || m.equals("accounts.logout")) {
			try {
				GSAPI.getInstance().logout();
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("denis",e.toString());
			}
		}
		else if ( m.equals("requestFacebookPublishPermissions")) {
			List<String> perms =Arrays.asList(par.getString("permissions", "").split(",")); 
			try {
				GSAPI.getInstance().requestNewFacebookPublishPermissions(perms, new GSPermissionResultHandler() {
				
					@Override
					public void onResult(boolean arg0, Exception arg1, List<String> arg2) {
						// logtext("Success = " + arg0 + " error = " + arg1 == null? "":arg1.toString() + " Not granted = " + arg2 == null? "":arg2.toString());
						String str = "Success = " + arg0;
						
						if(arg1 != null) { str += " Error = " + arg1.toString();}
						if(arg2 != null) { str += " Not granted = " + arg2.toString();}
						logtext(str);
							
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
				logtext(e.toString());
			}
		}
		else if ( m.equals("requestFacebookReadPermissions")) {
			List<String> perms =Arrays.asList(par.getString("permissions", "").split(",")); 
			try {
				GSAPI.getInstance().requestNewFacebookReadPermissions(perms, new GSPermissionResultHandler() {
				
					@Override
					public void onResult(boolean arg0, Exception arg1, List<String> arg2) {
						//logtext("Success = " + arg0  + " error = " + arg1 == null? "":arg1.toString() + " Not granted = " + arg2 == null? "":arg2.toString());
						
						String str = "Success = " + arg0;
						
						if(arg1 != null) { str += " Error = " + arg1.toString();}
						if(arg2 != null) { str += " Not granted = " + arg2.toString();}
						logtext(str);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
				logtext(e.toString());
			}
		} else if (m.equals("socialize.notifyLogin")) {
			AutoTests a = new AutoTests(this);
			a.notifyLogin(secretKey);
		}
		else if ( m.equals("FacebookDialog")) {
			try {

				onNewIntent(getIntent());
				//FBDialog fb = new FBDialog(this);
				//fb.publishFeedDialog();
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("denis",e.toString());
			}
		}
		else if ( m.equals("accounts.screenSet") || m.equals("gm.notifications") || m.contains("UI")) {
			try {
				// GSAPI.getInstance().logout();
				Log.d("denis","calling for plugin " + m);
				myText.setText("calling for plugin " + m);
				//GSAPI.getInstance().showPluginDialog(m, par, null,null);

				GSAPI.getInstance().showPluginDialog(m, par, new GSPluginListener() {
                    @Override
                    public void onLoad(GSPluginFragment pluginFragment, GSObject event) {
                        Log.d("GigyaTester", "Plugin dialog: plugin load");
                    }

                    @Override
                    public void onError(GSPluginFragment pluginFragment, GSObject event) {
                        Log.d("GigyaTester", "Plugin dialog: plugin error " + event.getInt("errorCode", 0));
                    }

                    @Override
                    public void onEvent(GSPluginFragment pluginFragment, GSObject event) {
                        Log.d("GigyaTester", "Plugin dialog: plugin event " + event.getString("eventName", ""));
                    }
                }, new GSDialogListener() {
                            @Override
                            public void onDismiss(boolean wasCanceled, GSObject event) {
                                Log.d("GigyaTester", "Plugin dialog dismissed" + (wasCanceled ? "(canceled)" : ""));
                            }
                        });
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("denis",e.toString());
			}
		}
		else if ( m.equals("webView")) {
			//
			Intent intent = new Intent(MainActivity.this, WebBridgeTestActivity.class);
		    startActivity(intent);
			
		}
		else if ( m.equals("fragment")) {
			Intent intent = new Intent(MainActivity.this, pluginFragmentActivity.class);
		    startActivity(intent);
		}
		else {
			Log.d("denis","calling " + m);
			myText.setText("calling for " + m);
			GSAPI.getInstance().sendRequest(m, par, this, null);
		}
    }
    
	public void onResult(boolean arg0, Exception arg1) {
		// TODO Auto-generated method stub
		if(arg1 != null ) {
			Log.d("denis", "Callback recieved");
			myText.setText ("Callback recieved");
		}
		else {	
			Log.d("denis", arg1.toString());
			myText.setText (arg1.toString());
		}
	}
	
	@Override
	public void onGSResponse(String arg0, GSResponse arg1, Object arg2) {
		if(! arg0.equals("ds.set")) {
			errorCode = arg1.getInt("errorCode", -1);
			logtext(arg0 + " " + arg1.getResponseText() + "\nErrorMessage = " + arg1.getErrorMessage() + " " +  errorCode);
		}
		else
			logtext("ds response arrived");
		
		if ( arg0.equals( "accounts.initRegistration") || arg0.equals( "accounts.register") )
			regToken = arg1.getString("regToken", null);
	}
    
	@Override
	public void onClose(boolean arg0, Object arg1) {
		// TODO Auto-generated method stub
		Log.d("denis","Closed");
		 myText.setText( "Closed");
	}


	@Override
	public void onError(GSResponse arg0, Object arg1) {
		// TODO Auto-generated method stub
		String text = "Error event \n" +  arg0.getErrorCode() + " " + arg0.getErrorMessage() + "\n" +
				arg0.toString();
		
		
		if(arg0.getErrorCode() == 206001) {
			text += " RegToken = " + arg0.getString("x_regToken","default");
		}
		// Setting the text:
		logtext(text);
	}


	@Override
	public void onLoad(Object arg0) {
		// TODO Auto-generated method stub
		// myText.setText( arg0.toString() );
		myText.setText( "Load");
	}


	//Deprecated events handlers 
	@Override
	public void onLogin(String methodName, GSObject user, Object context) {
		// TODO Auto-generated method stub
		logtext( "login event methodName = " + methodName + "\n response: " + user.toJsonString());
	}

	@Override
	public void onConnectionAdded(String provider, GSObject user, Object context) {
		// TODO Auto-generated method stub
		myText.setText( "addConnection event provider = " + provider + "\n" + user.toJsonString());
		Log.d("denis", "addConnection event provider = " + provider + "\n" + user.toJsonString() );
	}

	
	@Override
	public void onConnectionRemoved(String methodName, Object user) {
		// TODO Auto-generated method stub
		myText.setText( methodName + "\n response: " + user.toString());
		Log.d("denis", "login event methodName = " + methodName);
	}

	@Override
	public void onLogout(Object arg0) {
		// TODO Auto-generated method stub
		
		Log.d("denis", "logout event ");
		myText.setText("logout event ");
	}



	@Override
	public void onResult(boolean arg0, Exception arg1, List<String> arg2) {
		// TODO Auto-generated method stub
		
	}




}
