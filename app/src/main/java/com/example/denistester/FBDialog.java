package com.example.denistester;

/*import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
*/
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.net.URI;

//import bolts.AppLinks;

public class FBDialog {
	public MainActivity activity;

	public FBDialog(MainActivity activity) {
		this.activity = activity;
	}
	
	public void publishFeedDialog() {
		Bundle params = new Bundle();
	    params.putString("name", "Facebook SDK for Android");
	    params.putString("caption", "Build great social apps and get more installs.");
	    params.putString("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
	    params.putString("link", "https://developers.facebook.com/android");
	    params.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

		//URI targetURL = AppLinks.getTargetUrlFromInboundIntent(this, );
	/*   WebDialog feedDialog = (
	      new WebDialog.FeedDialogBuilder(activity,
	            Session.getActiveSession(),
	            params)).setOnCompleteListener(new OnCompleteListener() {

				@Override
				public void onComplete(Bundle values, FacebookException error) {
	                if (error == null) {
	                    // When the story is posted, echo the success
	                    // and the post Id.
	                    final String postId = values.getString("post_id");
	                    if (postId != null) {
	                        Toast.makeText(activity,
	                            "Posted story, id: "+postId,
	                            Toast.LENGTH_SHORT).show();
	                    } else {
	                        // User clicked the Cancel button
	                        Toast.makeText(activity.getApplicationContext(), 
	                            "Publish cancelled", 
	                            Toast.LENGTH_SHORT).show();
	                    }
	                } else if (error instanceof FacebookOperationCanceledException) {
	                    // User clicked the "x" button
	                    Toast.makeText(activity.getApplicationContext(), 
	                        "Publish cancelled", 
	                        Toast.LENGTH_SHORT).show();
	                } else {
	                    // Generic, ex: network error
	                    Toast.makeText(activity.getApplicationContext(), 
	                        "Error posting story", 
	                        Toast.LENGTH_SHORT).show();
	                }
				}

	        })
	        .build();
	    feedDialog.show();
	    	*/	
	}
}
