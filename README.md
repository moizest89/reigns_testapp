<img src="https://raw.githubusercontent.com/moizest89/reigns_testapp/develop/backgrounds/image-20211015031012429.png" alt="reign_design" style="zoom:65%;" />

This repository contains an Android Application with a challenge to demonstrate part of my knowledge like Android Developer.



#### Challenge.

Create a simple Android Application that should connect to [API](https://hn.algolia.com/api/v1/search_by_date?query=mobile) which shows recently posted articles about Android or iOS on Hacker News



#### Result.

| ![app_1](https://raw.githubusercontent.com/moizest89/reigns_testapp/develop/backgrounds/Screen%20Shot%202021-10-15%20at%202.42.38%20AM.png) | ![app_2](https://raw.githubusercontent.com/moizest89/reigns_testapp/develop/backgrounds/Screen%20Shot%202021-10-15%20at%202.42.46%20AM.png) | ![app_3](https://raw.githubusercontent.com/moizest89/reigns_testapp/develop/backgrounds/Screen%20Shot%202021-10-15%20at%202.42.53%20AM.png) | ![app_4](https://raw.githubusercontent.com/moizest89/reigns_testapp/develop/backgrounds/Screen%20Shot%202021-10-15%20at%202.43.02%20AM.png) | ![app_4](https://raw.githubusercontent.com/moizest89/reigns_testapp/develop/backgrounds/Screen%20Shot%202021-10-15%20at%202.43.07%20AM.png) |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |

This project contains:

1. Splash view with timer delay.
2. List items with data obtained from API service
3. Offline data saved in local database
4. Pull to refresh option to reload data from API service
5. Delete item from list (swipe option)
6. Error messages if application not has internet connection
7. Emty message if application not contains items to show
8. Details view to show news item details
9.  Share item option

The pallet color is based from [Reign Technology](https://www.reign.cl/en/) website




#### Run 'app'

1. Clone the repository
2. Open the project in Android Studio
3. Go to file named local.properties and create a variable called `API_URL` like the following example:
![Cornershop logo](https://i.imgur.com/UwG5fzG.png)
4. Set API_URL with the value:  https://hn.algolia.com/api/v1/search_by_date?query=mobile
5. Go to Menu option build -> Clean Project. This step creates `BuildConfig.API_URL` value to use in the application.
6. Run the project and start to use it.



#### Note: 

If you want to test the application without compile you have the possibility tho download an [APK FILE](https://github.com/moizest89/reigns_testapp/blob/develop/backgrounds/app-debug.apk?raw=true) directly 



#### Regards!





