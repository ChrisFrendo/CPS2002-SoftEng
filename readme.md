# Water Tiles V2.0

Water tiles is a console based game intended for 2 to 8 players. It is a tile game
where the player has to find the treasure while avoiding water tiles because these
will send him back to the starting position!!

## Installation

1. Download the jar file for the latest release
2. Execute jar file using the following command

```
java -jar watertiles.jar
```

NB: If you are using a terminal which allows ANSI colours (e.g. bash) you can add "colours" as a command line argument when running the jar file to enable ANSI colours for the terminal outputs.


## Game Flow

Upon starting the game some initial details have to be filled in, particularly 
the number of players and the size of the game board that you would like. After
this each player is prompted to enter his move on the console which can be any of the 
following:  
```
(u)p, (d)own, (l)eft, (r)ight
```
After all players have inputted their moves successfully a folder is generated called 'generatedHTML'.
This is folder is generated on the same level as the classpath. In the folder there will be 
an html file for each player. Players can open their file in a browser to view their current position
on the map as well as any tiles that they have previously visited.  

After these files are generated the game checks to see if there are any players that have
found the treasure. If it does indeed find winners, then this is indicated on the console and the game ends there. 

## Visuals

![Screenshot of html page](https://media.giphy.com/media/gHctpdkhp80L4qVGcy/giphy.gif)  
![Screenshot of command line input](https://imgur.com/nQqUCPY.png)

## Updates

Version 2.0 allows team play and also offers 2 different map types to choose from.

## Authors

Chris Frendo  
Manwel Bugeja