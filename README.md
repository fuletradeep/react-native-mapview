//Plusinfosys

#Android
##Create a Native UI Map Component

###Generate the api key
 https://console.cloud.google.com/

###Add the following
 In build.gradle file of the project

>classpath "com.android.tools.build:gradle:7.0.3"
>classpath "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.0"

In **`build.gradle`** file of your app

At the top of the file

>apply plugin 'com.google.android.libraries.mapsplatform.secrets-gradle-plugin'

Add the dependency
>implementation 'com.google.android.gms:play-services-maps:17.0.1'

In **`Manifest`** file

Add before the `activity` tag

    <meta-data
                	android:name="com.google.android.geo.API_KEY"
                	android:value=“api_key_generated” />

Add these permissons


    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>

In the gradle.wrapper.properties 
>change the gradle version to 7.0.2

####Make sure you have java 11 or greater in your app






