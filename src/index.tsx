import {
  requireNativeComponent,
  UIManager,
  Platform,
  ViewStyle,
} from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-mapview' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

type MapviewProps = {
  color: string;
  style: ViewStyle;
};

const ComponentName = 'MapviewView';

export const MapviewView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<MapviewProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };
