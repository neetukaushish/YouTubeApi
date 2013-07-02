package com.addvalsolutions.youtubeapi;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

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
		tb=(TableLayout)findViewById(R.id.tableLayout1);
		//img=(ImageView)findViewById(R.id.img);
		//tv=(TextView)findViewById(R.id.tv_name1);
		
		try {
			JSONParser obj=new JSONParser();
			JSONArray jsonarray=obj.getVideos(title);
			for(int i=0;i<jsonarray.length();i++){
				
				View row=getLayoutInflater().inflate(R.layout.activity_main, null);
				((TextView)row.findViewById(R.id.tv_name1)).setText(jsonarray.getJSONObject(i).getString("title"));
				((ImageView)row.findViewById(R.id.img)).setVisibility(i);
				tb.addView(row);
				
			
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
