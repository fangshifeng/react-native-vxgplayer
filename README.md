# react-native-vxgplayer
# Install 安装
npm i react-native-vxgplayer@0.0.6 --save

### Import 导入
##### Android Studio
* setting.gradle 

```
include ':react-native-vxgplayer'
project(':react-native-vxgplayer').projectDir = new File(settingsDir, '../node_modules/react-native-vxgplayer/android') 
```

* build.gradle

`compile project(':react-native-vxgplayer')`

* MainApplication

`new VXGPlayerPackage()`

### Usage 使用方法

`import VXGPlayer from 'react-native-vxgplayer'`

