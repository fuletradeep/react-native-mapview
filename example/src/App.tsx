import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import PIMap from 'react-native-mapview';

export default function App() {
  var region = {
    latitude: 37.78825,
    longitude: -122.4324,
    latitudeDelta: 0.1,
    longitudeDelta: 0.1,
    radius: 10000,
  };
  return (
    <View style={styles.container}>
      <PIMap region={region} style={{ height: '100%', width: '100%' }} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
