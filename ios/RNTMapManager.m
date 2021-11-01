//
//  RNTMapManager.m
//  mapview
//
//  Created by plusinfosys on 06/10/21.
//

#import <React/RCTViewManager.h>
#import "RNTMapManager.h"
#import <GoogleMaps/GoogleMaps.h>

@implementation RNTMapManager {
  GMSMarker *marker;
  GMSCircle *circle;
  GMSCameraPosition *camera;
  GMSCameraPosition *lastCameraPosition;
  BOOL isIntialize;
  double latitude;
  double longitude;
  double radius;
  double isZoom;
  BOOL isScroll;
}

RCT_EXPORT_MODULE(RNTMap)

- (UIView *)view
{
  return [[GMSMapView alloc] initWithFrame:CGRectMake(0, 0, 320, 568)];
}



RCT_CUSTOM_VIEW_PROPERTY(region, MKCoordinateRegion, GMSMapView)
{
  int zoomLevel = 11;
  radius = [[json objectForKey:@"radius"] doubleValue];
  double raduismeter = radius * 1000;
  double radius = raduismeter+ raduismeter / 2;
  double scale = radius / 500;
  zoomLevel = (int) (16 - log(scale) / log(2));
  
  if(!isIntialize) {

    latitude = [[json objectForKey:@"latitude"] doubleValue];
    longitude = [[json objectForKey:@"longitude"] doubleValue];

    camera = [GMSCameraPosition cameraWithLatitude:latitude
              longitude:longitude
              zoom:zoomLevel];
    view.delegate = self;
    [view setCamera:camera];

    marker = [[GMSMarker alloc] init];
    marker.position = CLLocationCoordinate2DMake(latitude,longitude);
//    marker.title = @"Sydney";
//    marker.snippet = @"Australia";
    marker.map = view;

    CLLocationCoordinate2D circleCenter = CLLocationCoordinate2DMake(latitude,longitude);
    circle= [GMSCircle circleWithPosition:circleCenter
             radius:raduismeter];

    circle.fillColor = [UIColor colorWithRed:66.0/255.0 green:159.0/255.0 blue:223.0/255.0 alpha:0.08];
    circle.strokeColor = [UIColor colorWithRed:66.0/255.0 green:159.0/255.0 blue:223.0/255.0 alpha:1.0];

    circle.strokeWidth = 1;
    circle.map = view;

    [view animateToCameraPosition:camera];
    isIntialize = YES;
    isZoom = NO;
  }
  else
  {
    isZoom = YES;
    circle.radius = raduismeter;
    camera = [GMSCameraPosition cameraWithLatitude:latitude
              longitude:longitude zoom:zoomLevel];
    [view setCamera:camera];
  }
}


- (void)mapView:(GMSMapView *)pMapView didChangeCameraPosition:(GMSCameraPosition *)position {

  if(isZoom) {
    isZoom = NO;
    return;
  }
  
  if (marker) {
    if (lastCameraPosition == nil) lastCameraPosition = position;
    double lat = position.target.latitude - lastCameraPosition.target.latitude;
    double lng = position.target.longitude - lastCameraPosition.target.longitude;
    lastCameraPosition = position;
    latitude = marker.position.latitude+lat;
    longitude = marker.position.longitude+lng;
    CLLocationCoordinate2D newCoords = CLLocationCoordinate2DMake(marker.position.latitude+lat,
                                                                  marker.position.longitude+lng);
    [circle setPosition:newCoords];
    [marker setPosition:newCoords];
    isScroll= NO;
    return;
  }
}

@end
