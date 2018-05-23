# kata-tennis-rep
L’énoncé du kata :

 

Le but est de modéliser un match de tennis entre 2 joueurs.

 

Le programme doit permettre de faire gagner les points par un des 2 joueurs

et de déterminer le score, le statut du jeu en cours (0-0, 15-0, …. Deuce, advantage) 

ainsi que status de la partie (in Progress, player 1 wins, player 2 wins) selon la format si dessous :

 

 

Exemple 1

Player  1 :  nom du joueur1

Player  2 : nom du joueur2

Score : (6 -1) (7-5) (1-0)

Current game status : 15-30

Match Status : in progress

 

Exemple 2

Player  1 :  nom du joueur1

Player  2 : nom du joueur2

Score : (6 -1) (7-5) (0-0)

Current game status : deuce

Match Status : in progress

 

Exemple 3

Player  1 :  nom du joueur1

Player  2 : nom du joueur2

Score : (6 -1) (7-5) (0-0)

Current game status : advantage

Match Status : in progress

 

Exemple 4

Player 1 :  nom du joueur1

Player 2 : nom du joueur2

Score : (6 -1) (7-5) (6-0)

Match Status : Player1 wins

 

Exemple 5

Player 1 :  nom du joueur1

Player 2 : nom du joueur2

Score : (6 -1) (7-5) (2-6) (6-7) (4-6)

Match Status : Player 2 wins

 

 

Tennis rules
Win a game
In a standard game (as opposed to a tie-break), each point increases the score of the player to the next value of the following sequence : 0, 15, 30, 40.

If a player has 40 and scores, then he wins the game.

Deuce state
If both players have 40, then it's deuce. The next player to score a point will have the advantage (yes, it is not the winning point !). If the opponent then scores, the advantage is lost and they are back to deuce (40-40).

To win in deuce, a player must :

1.       gain the advantage

2.       score while he has the advantage

Tie-break
In a tie-break game, points are counted as integers and not using the 0-15-30-40 sequence.

To win a tie-break game, a player must score :

·         at least 7 points

·         2 more points than the opponent (Rules explained here)

Win a set
To win a game, a player must win :

·         at least 6 games

·         2 more games than the opponent

Tie-break
If both players have 6 games, then the next game is a tie-break.

The winner of the tie-breaker game wins the set.

Win the match
To win the match, a player must win 3 sets.
