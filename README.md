# Color-Switch-Game

A **JavaFX applicatio**n for the game - **Color Switch**. The objective of the game is to collect
as many stars as possible by manoeuvring the obstacles. If you hit any obstacle, you can continue
with your earned stars or the game ends and you have to start from the beginning. This is a high
score game. We will be developing only the endless (infinity) game mode of the classic
gameplay. It is important that you play the game (available on both Android and iOS) and make
yourself well-familiar with it before reading this document any further. A youtube video of the
game can be found at this link.

**Some of the rules for the game play are mentioned below:**

1) There are many modes and levels in this game. We are only concerned with “Endless”
game mode denoted with an infinity symbol in the actual game's homescreen in which
you are required to design the “Classic” gameplay alone.
2) The player has to use a single input to keep the ball afloat as well as move upward. A
constant amount of distance is travelled each time the input is given.
3) The ball will fall if no input is given, as if it is moving against gravity.
4) The player has to collect the “Stars” placed along the gameplay. This will be counted
towards the score, hence it should be provided at frequent intervals.
5) A minimum of 4 colors has to be used to create obstacles and color the game ball.
6) Make use of Color switches to change the ball color and make the game interesting. They
need to be placed appropriately so that the game always progresses and there is no dead
end.
7) As the game progresses, the game needs to become difficult. You could achieve this by
creating different kinds of obstacles.
8) The Stars collected can be used to resurrect the ball in case the ball hits an obstacle.
Choose an appropriate number of stars to use to let the player resume if she/he feels so or
let them start afresh.
9) To save the state of game at any point in time, the game must store the following objects:
a) Store the stars collected
b) Store the exact position of the ball
c) Store the position of the obstacles and their orientation
10) A player must be able to save as well as load any saved game.
11) Game must allow multiple save/load games.


**The UML Diagram shows various class relationships**
