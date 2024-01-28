## IMDB database
Homework for my POO class. An application similar to the IMDB site, where one can browse movies and actors. Please compile the program using the main method inside of the IMDB class.
## Features
 - the actors, movies, and users are read from the resources/input folder, using the Jackson library. The json files are only read at run time, and can't be updated from inside the application.
 - log in using one of the accounts provided inside the accounts.json file
 - search for a movie or an actor
 - add a new user, production or actor, depending on the level of privilege of each user
 - add an actor or production to a favorites list
 - add a review to a production
 - an experience system
 - a notification system
 - a request system

## Application flow
Once the application has been ran, the user can choose whether they want to access the database using the command line interface or the graphical interface.
<p align="center">
  <img src="https://i.imgur.com/oxH7gbR.png" />
</p>

## Command line interface
After the user logs in using one of the accounts inside the accounts.json file, they'll be welcomed by an interface with multiple options. The user can choose what they'd like to do by typing the number next to the option.
<p align="center">
  <img src="https://i.imgur.com/9SpTld0.png" />
</p>
<p align="center">
  <img src="https://i.imgur.com/U6veevs.png" />
</p>

## Graphical user interface
The GUI is made using the Swing toolking and the FormLayout manager from JGoodies. After the user logs in, they are shown the main page of the application. They can then choose to explore all the other features.
<p align="center">
  <img src="https://i.imgur.com/xxJHQds.png" />
</p>
<p align="center">
  <img src="https://i.imgur.com/eCora2k.png" />
</p>
<p align="center">
  <img src="https://i.imgur.com/kl6oN4d.png" />
</p>
