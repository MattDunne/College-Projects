package com.example.matt.csp_project;

/*import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.google.android.gms.maps.SupportMapFragment;
import android.support.v4.app.FragmentActivity;


//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MapActivity extends Activity {
	static final LatLng RidgeRoad = new LatLng(47.58698, -52.73468);
    private GoogleMap map;
          
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
        	
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#055b8c")));
        	
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(RidgeRoad, 15));
 		
		map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        
		map.addMarker(new MarkerOptions()
			.position(RidgeRoad)
			.title("Engineering Technology Centre")
			.snippet("Ridge Road, St. John\'s NL, A1C 6L8"));
	}
}*/

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;


		LatLng ridgeroad = new LatLng(47.58698, -52.73468);
		mMap.addMarker(new MarkerOptions().position(ridgeroad).title("Marker at RidgeRoad"));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(ridgeroad));
	}
}
