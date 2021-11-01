package com.plusinfosys;

import android.graphics.Color;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ViewMap extends MapView implements
  OnMapReadyCallback {
  public GoogleMap mMap;
  private Boolean isMapLoaded = false;
  public Double latitude;
  public Double longitude;
  public Double radius;


  public ViewMap(ThemedReactContext reactContext, ReactApplicationContext appContext,
                    ViewMapManager manager,
                    GoogleMapOptions googleMapOptions) {
    super(appContext);
    super.onCreate(null);
    super.onResume();
    super.getMapAsync(this);
    final ViewMap view = this;
  }


  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    Log.d("+++", "setRegion: "+latitude);
    // Add a marker in Sydney and move the camera
    LatLng region = new LatLng(latitude, longitude);

    mMap.addMarker(new MarkerOptions().position(region));

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));

    Circle circle = mMap.addCircle(new CircleOptions().center(region).fillColor(Color.parseColor("#200000FF")).radius(radius));
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
      circle.getCenter(), getZoomLevel(circle)));
  }

  public float getZoomLevel(Circle circle) {
    int zoomLevel = 11;
    if (circle != null) {
      double radius = circle.getRadius() + circle.getRadius() / 2;
      double scale = radius / 500;
      zoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
    }
    return zoomLevel+.4f;
  }
  public void setRegion(ReadableMap region) {
    if (region == null) return;

    latitude = region.getDouble("latitude");
    longitude = region.getDouble("longitude");
    radius = region.getDouble("radius");



  }
}

