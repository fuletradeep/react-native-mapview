import {
  requireNativeComponent,
  UIManager,
  Platform,
  ViewStyle,
} from 'react-native';
import RNMap from './RNMap'



var region = {
  latitude: 37.78825,
  longitude: -122.4324,
  latitudeDelta: 0.1,
  longitudeDelta: 0.1,
  radius: 300,
};

const ComponentName = 'MapviewView';

export const MapviewView = () => {
       return(
         <RNMap region={region} />
       )
      };
