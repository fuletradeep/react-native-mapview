package com.plusinfosys;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.maps.GoogleMapOptions;

class ViewMapManager extends ViewGroupManager<ViewMap> {

  private static final String REACT_CLASS = "RNMap";


  private final ReactApplicationContext appContext;

  protected GoogleMapOptions googleMapOptions;

  public ViewMapManager(ReactApplicationContext context) {
    this.appContext = context;
    this.googleMapOptions = new GoogleMapOptions();
  }

  @ReactProp(name = "region")
  public void setRegion(ViewMap view, ReadableMap region) {
    view.setRegion(region);
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  protected ViewMap createViewInstance(ThemedReactContext context) {
    return new ViewMap(context, this.appContext, this, googleMapOptions);
  }
}
