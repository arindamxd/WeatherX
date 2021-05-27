# WeatherX
A mobile app that helps with searching current weather for a given city.

[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://opensource.org/licenses/Apache-2.0)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)

<p align="left">
  <img width="25%" src="https://github.com/arindamxd/WeatherX/raw/main/screenshots/search_cached.png?raw=true">
  <img width="25%" src="https://github.com/arindamxd/WeatherX/raw/main/screenshots/search_result.png?raw=true">
  <img width="25%" src="https://github.com/arindamxd/WeatherX/raw/main/screenshots/manage_favourite.png?raw=true">
</p>

## Overview

WeatherX is a simple Android App, that allows user to search weather for a city of their choice
and manage a list of favourite destinations. It provides an elegant UI/UX surface that works
across most Android devices, with backward compatibility to Android 4.4 (API level 19).

- Ease of use
- Allow users to search weather for a city of their choice
- Allow users to create/manage a list of "favourite" destinations
- Network unavailability handled
- Offline cache
- Consistency across devices

Lastly, WeatherX makes heavy usage of reactive MVVM design throughout the app. It uses
RxAndroid, Retrofit, Room, LiveData, etc. Both the screens (Search and Favourite) have one ViewModel that consumes user interactions.

## Build

To build the app directly from the command line, run:
```sh
./gradlew assembleDebug
```

### Find this project useful ? :heart:
> Support it by clicking the :star:   button on the upper right of this page. :v:

### License

```
   Copyright (c) 2021 Arindam Karmakar, Android Open Source Project

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
