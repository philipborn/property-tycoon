# Sprint 4

## Summary Data

| Summary Data          |            |
| --------------------- | ---------- |
| Team number           | 16         |
| Sprint technical lead | Philip     |
| Sprint start date     | 31-03-2020 |
| Sprint end date       | 14-04-2020 |


## key contributions

Individual key contributions

| Person   | Area                                                  |
| :------- | ----------------------------------------------------- |
| Lee      | GUI: start game, player interface, other improvements |
| Philip   | Model: Card, game state                               |
| Roksanna | House figures                                         |
| Tom      | property pop window                                   |

## User stories


Before the game, the player selects the number of player for the game. Their select number of
player for game, token, if Ai, time to end the game and write their name. When the game show what player have selected.

Player land on card square, a card is drawn, and the Action from the card will be cared out.
If a player's token land on the free park it will all cash that accumulated in free parking.

After moving and made the Action, the player will get the option to buy or sell a house, and sell and mortgage property.

After this sprint, we should have the game's GUI show a the bord, dices and a token.
The squares in the bord will show their names,
which can be modified by changing inside a JSON file.
The change will affect when restarted the app.
A player can throw dices and token will move around the board.

## Requirements analysis

Function requirements

1. Player land on Free parking: player shall get all cash collect in the free parking
2. Player land on cards: draw a card from a deck and show the card. The card action will be carried out.
3. If a player goes to jail, the player may get the option to pay 50 pounds or lose two turns. The player turn end after decide.
4. Player shale doesn't pay rent if the owner of the property is in jail.
5. At the start of the game: shale apple to select the number of players.
6. At the beginning of the game: shale apple to select tokens to a player.
7. At the start of the game: shale apple to select ai of a player.
8. At the start of the game: shale apple to write player names.
9. After the player has moved and done Action, the player shall have the option to buy and sell houses, and sell and mortgage on a property.
10. Action on a property can only be done by the player that owned the property.
11. If the player makes forbidden actinon, nothing happens

Non-functionle

1. The card file is using JSON format.
2. Popups windows show extra information that not showed on mainboard view. Like cards.

## Design

Start of the game, a window comes up. When the number of players is selected, a row for each player will appear.
In the row there figure showing the token, a field for the player's name and pick ai for this player.
On this window, the option for a time limit can be selected.

A state machine that that player action should not always be apple only can be made at witch state, still use the state machine is added after the game interface.

Make command to lower the number of methods needed that the game need to pass to the states.
In short, state machines handle all player requests.
The policy for forbidden Action is nothing happens. There will be no exceptions or error message.
This is to make the controller simpler as only need translate player action to method calls.
This takes application logic out of the controllers. Reason for no error messages is player should not need more information,
the information should already be there.

To solve the problem of having card action read from a file, we will be using the interpreter pattern and using the command pattern.
The interpreter would give a flexible interface for the customs to use.
The pattern is there is a class for every gramma rule of the domain languish.  Witch is the same structure for a top-down parser.
The output of interpreter is an action object. The object Card has object Action. An action holds all it needs.
All card will be stored in decks. A Square can hold a deck reference. When a player land on the square, the top card will be picket. A message will be sent to GUI with the card description and the Action that card hold on will be activated.

## Test plan and evidence of testing

GUI and Game state machine will be tested manually. There will be a need for test protocol testing all Action and information should be an apple for the player.

Unit tests will test the rest of the game.

## Summary of sprint

GUI has a start window, as design.
Yes and no at bottoms of the board.
Have added the tokens figures
Have adding sound for dices hand player get cash.

Hade short the development time so need to simplify the card design.
For making parsing the card easer, every type of Action has a name.
Every card has an action name that defined the Action. Then have key and value pairs to parameterise the Action.
As every type of Action is defined as key-value parsing is easy.

When making changes to the parameter to the squares constructor,
then a large number of files need changing. This show that many tests was dependent on the square constructed.
To lose the coupling, inheritance was removed and made square hold a Square type object. The square type is reached by the visitor pattern.
The change leads to the test of different types of square don't need to see the linking part of the squares.

There was not to do the test of the new functionalty. So bugs are expected for next sprint.
