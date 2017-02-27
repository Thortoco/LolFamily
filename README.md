# LolFamily

### Project
Personnal Android project of Corentin EVANNO using RiotGames API.
This application use Retrofit and Picasso from http://square.github.io/
You need at least Android Kitkat (4.4) to run the application.
### Features
The goal of LolFamily is to allow users to browse summoners profile from different region, their last 10 matches and their best champions in ranked.

![alt tag](https://cldup.com/dTxpPi9lDf.thumb.png)
On the main page you can select the server where you play from the dropdown and then input your summoner name.
When it's done just click on the find button.

![alt tag](https://cldup.com/dTxpPi9lDf.thumb.png)
After finding your summoner, the application will display the profil page of your summoner with his ranking achievement (if he's not an ARAM one trick poney). You can switch between each page using the tabs or you can go back to the main page with the arrow on the top left of the screen.

![alt tag](https://cldup.com/dTxpPi9lDf.thumb.png)
The history page allows you to browse your last 10 matchs on League of legends. You can view details such as your score or your KDA. If you want to display your opponents or the build you used you must click on the blue arrow at the bottom of the match's card.

![alt tag](https://cldup.com/dTxpPi9lDf.thumb.png)
The best champions page is there to show you your 10 most played champions in ranked. You can scroll down to see all of your heroes !
### For devs
If you want to use the code to build the application in your IDE such as Android Studio you must add your own API KEY in the AndroidManifest.xml file at the line 16.
```xml
<meta-data
            android:name="corentin.lol.api.API_KEY"
            android:value="REPLACE_WITH_YOUR_API_KEY" />
```
### Thanks
I would like to thanks Guillaume, Maxence and Redouane for their feedbacks during the beta process.
### Disclaimer
LolFamily isn't endorsed by Riot Games and doesn't reflect the views or opinions of Riot Games or anyone officially involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.
