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

| Person   | Area                                                 |
| :------- | ---------------------------------------------------- |
| Lee      | GUI: start game, player interface , other improments |
| Philip   | Model: Card, gameState                               |
| Roksanna | House figers                                         |
| Tom      | property pop window                                  |

## User stories


Before game the Player select the number of player for the game. There there select number of
player for game, token, if Ai, time to end the game and write theres name. When game show the what player have selceted.

Player land on card sqaure will see the card and the action from the card will be cared out.
If a players token land on freepark it will all cash that cumlated in freeparking.

After moving and made the action, the player will get the option to buy or sell house ,and sell and mortige property.

After this sprint, we should have the game's GUI show a the bord, dices and a token.
The squares in the bord will show their names,
which can be modified by changing inside a JSON file.
The change will affect when restarted the app.
Player can throw dices and token will move around the bord.

## Requirements analysis

Function requriment

1. Player land on Free parking : player shall get all cash collet in free parking
2. Player land on cards: draw card from a deck and show the card. Thr card action will be carrid out.
3. If player go to jail, player may get option to pay 50 pound or lose 2 turns. player turn end after desiade.
4. Player skale don't pay rent if owner of property is in jail.
5. At start of game: shale apple to select number of player.
6. At start of game: shale apple to select tokens to player.
7. At start of game: shale apple to select ai of a player.
8. At start of game: shale apple to write player names.
9. After player have moved and done action, player shall have option to buy and sell houses, and sell and mortige on property.
10. Action on property can only done by player that owend the propterty.
11. If player make forbidden actinon, nothing happens

Non-functionle

1. Card file are using JSON format.
2. Extra inforamtion that not showed on main board view, are shoiwnd by popups windows. Like cards.

## Design

Start of the game, a window come up. When the number of players are sleckted, a row for each player will abear.
In the row there figure showing the token, field for the players name and pick ai for this player.
On this window, the option for a time limit can be selected.

A stathec machine that that player action should not allways be apple only can be made at witch state, still use the state machine is add beagined the game interface.

Make command to lowert maouny methods the game need to pass to the states.
In short state machines handle all player requsets.
The pliolcy for forbidden acion is nothing appens. No extions or error message.
This is to make the controller simplare as only need tranlate player action to method calls.
This take application logic out of the controlers. Reason for no error maesses is player should not need more information,
the inforamtion should aready be there

To solve be have a interpaterter pattern and using command pattern. Interpeter whould give a fleixble interafce for the cousterms to used.
The pattern is there is a class for every gramma rule of the domain languish.  Witch is same structer for top-down parser.
The out put of inpterter is a action objcet. The object Card have objcet Action. A action hold all it needs.
All card will be stored in decks. A Sqauare can hold a deck refens. When player land on the sqaure, the top card will be picket. Meassgae will be sent to gui with the card discriptoy and the action that card hold on will be actived.

## Test plan and evidence of testing

GUI and Game state maxchine will be tested manule. There will be a need for test protedcol testing all action and information should be apple for the player.

Rest of the game will be test by unit test.

## Summary of sprint

GUI have a start window, as dsigen.
yes and no at bottens of the borad.

 Have added tookens figures toekn figures

Have adding sound for dices hand player get cash.

Hade short the devople time so need to simplfie the card design.
For makeing parsing the card easer, every type of action has a name.
Every card have action name that defiend the action. Then have key and valus paire to parameterise the action.
As every typ of action is defiand as key-value parie, aprsing is verty easy.

When making changes to partermeter to the sqaures constuter, then large amount o files need change as the
