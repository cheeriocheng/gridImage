package com.chengxu.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	EditText etQuery;
	GridView gvResults; 
	Button btnSearch;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		Log.v("system", "onCreate was just called!");
		
		setupViews();
	}
	
	protected void onResume() {
	       super.onResume();
	       Log.v("system", "onResume was just called!");
	    }

	    protected void onPause() {
	       super.onPause();
	       Log.v("system", "onPause was just called!");
	    }
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
		
	}
	
	public void onImageSearch(View v){
		String query = etQuery.getText().toString();
		Toast.makeText(this, "Searching for "+query, Toast.LENGTH_LONG).show();
		
		//load async http client
		//see example http://loopj.com/android-async-http/
		AsyncHttpClient client = new AsyncHttpClient();
		//https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=QUERY_STRING"
		
//		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"+"start="+0+"&v=1.0&q="+Uri.encode(query),
		client.get(Uri.encode("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz=8&q=android"),
			new JsonHttpResponseHandler() {
		    @Override
		    public void onSuccess(JSONObject response) {
		    	JSONArray imageJsonResutls = null; 
		    	Log.d("DEBUG","in http handler");
		    	
		    	try{
		    		imageJsonResutls=response.getJSONObject(
		    				"responseData").getJSONArray("resutls");
		    		
//		    		imageResults.clear();
//		    		imageResults.addAll(ImageResult.fromJSONArray(imageJsonResutls));
		    		Log.d("DEBUG",imageResults.toString());
//		    		
		    	}catch(JSONException e){
		    		Log.d("DEBUG","http parse fail");
		    		e.printStackTrace();
		    	}
		    }
		});

	}
	
	public void setupViews(){
		etQuery = (EditText)findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);
	}

}
