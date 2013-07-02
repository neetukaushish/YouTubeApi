package com.addvalsolutions.youtubeapi;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class JSONParser {

	String path;
	private JSONArray jsonArray;
	private JSONObject jsonObject;
	String title="",href="";
	public JSONParser(){
		jsonArray=new JSONArray();
		jsonObject=new JSONObject();
	}
	public JSONArray getVideos(String title){
		
		try{
			String apiKey="AIzaSyBAhKQ4VY3PYYn1p4qjX0N7n7Cl3NPbzww";
			path="https://gdata.youtube.com/feeds/api/videos?q="+title+"&alt=jsonc&v=2&key="+apiKey;
			DefaultHttpClient client=new DefaultHttpClient();
			HttpPost url;
			try{
				url=new HttpPost(path);
			}
			catch(Exception e){
				//Toast.makeText(, "Please Enter a valid title", Toast.LENGTH_SHORT).show();
			}
			HttpResponse response=client.execute(new HttpGet(path));
			jsonObject=new JSONObject(EntityUtils.toString(response.getEntity()));
			//String video_title=jsonObject.getJSONObject("data").toString();
			jsonArray=jsonObject.getJSONObject("data").getJSONArray("items");
			//System.out.println(jsonArray.length());
			
		}
		catch(Exception e){
			
		}
		return jsonArray;
	}
	
}
