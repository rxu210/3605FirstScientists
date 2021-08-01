package com.example.a3605firstscientists;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a3605firstscientists.activities.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import com.mapbox.geojson.FeatureCollection;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;


import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

public class MapActivity extends AppCompatActivity {

    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String ICON_ID = "ICON_ID";
    private static final String LAYER_ID = "LAYER_ID";


    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_map);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                FeatureCollection featureCollection = FeatureCollection.fromJson(HomeActivity.listFires);

                mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/rxu210/ckr4g009y0f2b18mo7y4y4fic")
                        // Add the SymbolLayer icon image to the map style
                        .withImage(ICON_ID, BitmapFactory.decodeResource(
                                MapActivity.this.getResources(), R.drawable.mapbox_marker_icon_default))
                        // Adding a GeoJson source for the SymbolLayer icons.
                        .withSource(new GeoJsonSource(SOURCE_ID,
                                featureCollection))
                        .withLayer(new SymbolLayer(LAYER_ID, SOURCE_ID)
                                .withProperties(
                                        iconImage(ICON_ID),
                                        iconAllowOverlap(true),
                                        iconIgnorePlacement(true)
                                )
                        ), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                        CameraPosition position = new CameraPosition.Builder()
                                .target(new LatLng(HomeActivity.latitude, HomeActivity.longitude))
                                .zoom(10)
                                .bearing(0)
                                .tilt(0)
                                .build();

                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 7000);

                        mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(HomeActivity.latitude, HomeActivity.longitude))
                        .title("Your location"));
                    }
                });

            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_bar);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    /*case R.id.ic_home:
                        Intent intent1 = new Intent(MapActivity.this, HomeActivity.class);;
                        startActivity(intent1);
                        break;*/

                    case R.id.ic_learn:
                        Intent intent2 = new Intent(MapActivity.this, UpdatedLearnActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_map:
                        break;

                    case R.id.ic_blog:
                        Intent intent3 = new Intent(MapActivity.this, Post.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}