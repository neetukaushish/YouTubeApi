package com.addvalsolutions.youtubeapi;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends Activity {


	TableLayout tb;
	ImageView img;
	TableRow tr;
	TextView tv;
	String title="cars";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*edit=(EditText)findViewById(R.id.edit_search);
		btn=(Button)findViewById(R.id.btn_search);
		*/
		//tb=(TableLayout)findViewById(R.id.tableLayout1);
		//img=(ImageView)findViewById(R.id.img);
		//tv=(TextView)findViewById(R.id.tv_name1);
		
		ArrayList<HashMap<String, String>> items=new ArrayList<HashMap<String,String>>();
		
		try {
			JSONParser obj=new JSONParser();
			JSONArray jsonarray=obj.getVideos(title);
			for(int i=0;i<jsonarray.length();i++){	
				String title=jsonarray.getJSONObject(i).getString("title");
				JSONObject thumb=jsonarray.getJSONObject(i).getJSONObject("thumbnail");
				String sqDefault=thumb.getString("sqDefault");
				JSONObject player=jsonarray.getJSONObject(i).getJSONObject("player");
				String player_default=player.getString("default");
				//creating hashmap
			    HashMap<String, String> map=new HashMap<String, String>();
			    
			    //adding each child node to HashMap key and value
			    map.put("title", title);
			    map.put("sqDefault", sqDefault);
                map.put("player", player_default);
                
                //adding to arraylist
                items.add(map);
                
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Updating parse data into list view
		
				ListAdapter adapter = new SimpleAdapter(this, items,
						R.layout.list_items,
						new String[] { "sqDefault","player" }, new int[] {
								R.id.img, R.id.tv_search });

				ListView lv=(ListView)findViewById(R.id.list);
				lv.setAdapter(adapter);

				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
