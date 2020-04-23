# Water Tiles V1.0

Water tiles is a console based game intended for 2 to 8 players. It is a tile game
where the player has to find the treasure while avoiding water tiles because these
will send him back to the starting position!!

## Installation

1. Clone this repository or download the zip file
2. Open the project using your favorite ide
3. Find the Menu class in /src/main/java/menu
4. Run the main method in this Menu class from your ide.

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

## Authors

Chris Frendo  
Manwel Bugeja