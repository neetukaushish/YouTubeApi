package com.addvalsolutions.youtubeapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Video;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends Activity {


	ImageView img;
	EditText edit;
	Button btn;
	TextView tv;
	String title="cars";
	String player_default="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit=(EditText)findViewById(R.id.edit_search);
		btn=(Button)findViewById(R.id.btn_search);
		
		
		final ArrayList<HashMap<String, String>> items=new ArrayList<HashMap<String,String>>();
		
		try {
			JSONParser obj=new JSONParser();
			JSONArray jsonarray=obj.getVideos(title);
			for(int i=0;i<jsonarray.length();i++){	
				String title=jsonarray.getJSONObject(i).getString("title");
				JSONObject thumb=jsonarray.getJSONObject(i).getJSONObject("thumbnail");
				String sqDefault=thumb.getString("sqDefault");
				JSONObject player=jsonarray.getJSONObject(i).getJSONObject("player");
				player_default=player.getString("default");
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
						new String[] { "sqDefault","title" }, new int[] {
								R.id.img , R.id.tv_search});

				final ListView lv=(ListView)findViewById(R.id.list);
				lv.setAdapter(adapter);

         lv.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		// TODO Auto-generated method stub
		/*Video chosen=(Video)arg0.getItemAtPosition(position);
		int i=Integer.parseInt(chosen.toString());
		lv.SelectedItems[i].SubItems[1].Text;*/
		Map<String, String> map=(Map<String,String>)((ListView)lv).getItemAtPosition(position);
		String val=map.get("player");
		
		Bundle b=new Bundle();
		b.putString("video", val);
		Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
		intent.putExtras(b);
		startActivity(intent);
		//startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(val)));
	}
});
				btn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=urHuO7Zbhhw&feature=youtube_gdata_player")));
						
					}
				});
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
