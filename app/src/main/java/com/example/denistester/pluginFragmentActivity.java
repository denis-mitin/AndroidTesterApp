package com.example.denistester;

import android.app.Activity;

import com.gigya.socialize.GSObject;
import com.gigya.socialize.android.GSAPI;
import com.gigya.socialize.android.GSPluginFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class pluginFragmentActivity extends FragmentActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activiy_fragmentplugin);
	    // TODO Auto-generated method stub
	    
	    GSObject param = new GSObject();
	    param.put("screenSet", "Android-RegistrationLogin");
		//param.put("sessionExpiration", "30");


		param.put("facebookLoginBehavior", "NATIVE_ONLY");
	    GSPluginFragment fragment = GSPluginFragment.newInstance("accounts.screenSet", param);

	    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	    ft.replace(R.id.plugin_placeholder, fragment);
	    ft.commit();

		GSObject param2 = new GSObject();
	    param2.put("categoryID", "ios");
	    param2.put("version", "v2");
	    GSPluginFragment fragment2 = GSPluginFragment.newInstance("comments.commentsUI", param2);
	    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
	    ft2.replace(R.id.plugin_placeholder2, fragment2);
	    ft2.commit();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		if (GSAPI.getInstance().handleAndroidPermissionsResult(requestCode, permissions, grantResults))
			return;

		// handle other permissions result here
	}

}
