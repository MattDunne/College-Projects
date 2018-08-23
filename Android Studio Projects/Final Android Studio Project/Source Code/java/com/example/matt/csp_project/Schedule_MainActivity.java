package com.example.matt.csp_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import com.viewpagerindicator.TabPageIndicator;
 
public class Schedule_MainActivity extends Activity{
    //these are the titles that will appear on the "tabs"
    final String[] page_titles = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri"};

    final String[] hours = new String[]{"9", "10", "11", "noon", "1", "2", "3", "4", "5", "6"};
 
    private String[][] days;
    private int pos;
    
    public static ArrayList<String> schedule = new ArrayList<String>();
            
    List <Map<String, String>> [] list = new List[5]; // Mon - Fri
    Map<String, String> map;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        if(!isNetworkAvailable())
        		new AlertDialog.Builder(Schedule_MainActivity.this).
        		setTitle("Error").setMessage("No Network Connection").
        		setNeutralButton("Close", null).show();
		else {
			new GetXML().execute("");	
		}   
    }
    
	public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) 
          getSystemService(Context.CONNECTIVITY_SERVICE);
        
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
 
        if (networkInfo != null && networkInfo.isConnected()) 
        		return true;
        else
        		return false;
    }
 
    private class Adapter extends PagerAdapter {
 
        Context context;
 
        public Adapter(Context c){
            this.context = c;
        }
 
        //This is the number of pages -- 5
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return page_titles.length;
        }
 
        @Override
        public boolean isViewFromObject(View v, Object o) {
            // TODO Auto-generated method stub
            return v.equals(o);
        }
 
        //This is the title of the page that will apppear on the "tab"
        public CharSequence getPageTitle(int position) {
            return page_titles[position];
        }
 
        //This is where all the magic happen
        public Object instantiateItem(View pager, int position) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.page, null, false);
 
            ListView timeListView = (ListView)v.findViewById(R.id.timetable);
            switch(position) {
            		case 0 : timeListView.setAdapter(new TimeTableAdapter(Schedule_MainActivity.this, list[0], 0)); break;
            		case 1 : timeListView.setAdapter(new TimeTableAdapter(Schedule_MainActivity.this, list[1], 1)); break;
            		case 2 : timeListView.setAdapter(new TimeTableAdapter(Schedule_MainActivity.this, list[2], 2)); break;
           			case 3 : timeListView.setAdapter(new TimeTableAdapter(Schedule_MainActivity.this, list[3], 3)); break;
            		case 4 : timeListView.setAdapter(new TimeTableAdapter(Schedule_MainActivity.this, list[4], 4)); break;
            		default : break;
            }
            timeListView.setTextFilterEnabled(true);
  
            ( (ViewPager) pager ).addView( v, 0 );
             
            return v;
        }
 
        @Override
        public void destroyItem(View pager, int position, Object view) {
            ((ViewPager) pager).removeView((View) view);
        }
 
        @Override
        public void finishUpdate(View view) {
        }
 
        @Override
        public void restoreState(Parcelable p, ClassLoader c) {
        }
 
        @Override
        public Parcelable saveState() {
            return null;
        }
 
        @Override
        public void startUpdate(View view) {
        } 
    }
    
    private class GetXML extends AsyncTask<String, Void, String> {
		String src = null;
  		
		@Override
		protected String doInBackground(String... params) {
       		try {       			
       			URL url = new URL("http://branko-cirovic.appspot.com/etcapp/timetables/android/timetable_cs7.xml");
       			HttpURLConnection con = (HttpURLConnection) url.openConnection();
       			src = readStream(con.getInputStream());       		
       			
       		} catch (Exception e) {
       			e.printStackTrace();
       		}

       		return src;            
       }      
       
       @Override
       protected void onPostExecute(String result) {        		
    	   		if(src == null)
    	   			new AlertDialog.Builder(Schedule_MainActivity.this).
            		setTitle("Error").setMessage("No Schedule Found").
            		setNeutralButton("Close", null).show();
    	   		else {    	   			
    	   			parseXML(src); 
    	   		}
    	   		
    	   		setContentView(R.layout.schedule_activity_main);
    	   		
    	   		days = new String[5][10];
    	   		for(int i = 0; i < 5; i++) {
    				for(int j = 0; j < 10; j++) {
    					int k = i * 10 + j;				
    					days[i][j] = schedule.get(k);
    				}
    			}
    	   		
    	        for(int i = 0; i < 5; i++)
            		list[i] = new ArrayList<Map<String, String>>();
            
            int count = hours.length;
            
            for(int j = 0; j < 5; j++) {
            		for(int i = 0; i < count; i++) {
            			map = new HashMap<String, String>();
            			map.put("time", hours[i]);
            			map.put("description", days[j][i]);
            			list[j].add(map);
            		}
            }    
            
            Adapter a = new Adapter(Schedule_MainActivity.this);
            
            Calendar cal = Calendar.getInstance();
            int today = cal.get(Calendar.DAY_OF_WEEK) - 2;
    		
    		pos = 0;
    		if(today >= 0 && today <= 4)
    			pos = today;
    						            
            ViewPager mPager = (ViewPager)findViewById(R.id.pager);
            mPager.setAdapter(a);
            mPager.setCurrentItem(pos);
     
            TabPageIndicator mIndicator = (TabPageIndicator)findViewById(R.id.indicator);
            mIndicator.setViewPager(mPager, pos);
       }

       @Override
       protected void onPreExecute() {
       }

       @Override
       protected void onProgressUpdate(Void... values) {
       }
   }  
	
	public void parseXML(String src) {		
		try {
			StringReader sr = new StringReader(src);
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(sr);
	                                
			int token = xpp.getEventType();
			while (token != XmlPullParser.END_DOCUMENT) {
				if(token == XmlPullParser.START_TAG) {
					if(xpp.getName().equals("cid")) {
						token = xpp.nextToken();
						
						if(xpp.getText() == null)
							schedule.add("");
						
						if(token == XmlPullParser.TEXT) {
								schedule.add(xpp.getText());
						}
					}
				}
    	  	
				token = xpp.nextToken();                	
			}    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
   
	private String readStream(InputStream in) {
   		BufferedReader reader = null;
   		String line = null;
   		StringBuffer sb = new StringBuffer();
   		try {
   			reader = new BufferedReader(new InputStreamReader(in));
   			while ((line = reader.readLine()) != null) {
   				sb.append(line);
   	    		}    	   
   		} catch (IOException e) {
   			e.printStackTrace();
   		} finally {
   			if (reader != null) {
   				try {
   					reader.close();
   				} catch (IOException e) {
   					e.printStackTrace();
   				}
   			}
   		}
   		return sb.toString();
	}
}