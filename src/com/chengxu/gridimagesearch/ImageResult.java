package com.chengxu.gridimagesearch;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Refer to google image api with java 
//https://developers.google.com/image-search/v1/jsondevguide?hl=ja
public class ImageResult {
	private String fullUrl;
	private String thumbUrl; 
	
	ImageResult(JSONObject json){
		try{
			this.fullUrl = json.getString("url"); 
			this.thumbUrl= json.getString("tbUrl");		
		}catch(JSONException e){
			this.fullUrl = null; 
			this.thumbUrl= null;		
		}
	}
	
	public String getfullUrl (){
		return fullUrl; 
	}
	
	public String getThumbUrl (){
		return thumbUrl; 
	}
	
	public String toString(){
		return this.thumbUrl;
	}
	
	//static method; access directly from ImageResult
	//go through JsonArray add each objects
	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray array) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for (int x=0; x<array.length();x++){
			try{
				results.add(new ImageResult(array.getJSONObject(x)));
			}catch (JSONException e){
				e.printStackTrace();
			}
		}
		
		return results;
	}
	
}
