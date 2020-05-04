# Sprint 3

## Summary Data

| Summary Data          |            |
| --------------------- | ---------- |
| Team number           | 16         |
| Sprint technical lead | Philip     |
| Sprint start date     | 03-03-2020 |
| Sprint end date       | 17-03-2020 |

## key contributions

Individual key contributions

| Person   | Area                                                                                |
| :------- | ----------------------------------------------------------------------------------- |
| Lee      | re-sizable board, style sheet, multiple Token, new Game box, Property info          |
| Philip   | Game model basic game                                                               |
| Roksanna | Classes of Property. Street, Station, Utility and make corner square, token figures |
| Tom      | Game model basic game, integrate game and controller                                |

## User stories

The game will have six players. Each turn player gets to throw dices and move there token.
After a player has made a complete around the board, the player can buy a property.
If layer chose to purchase, cash would be drawn. After the player has moved, the player can sell there their properties, mortgage or remove mortgage of a property.
When player mortgage, they will receive half of the price of the property in cash.

If player land on the property that is, owned by another player and is not mortgaged, will pay rent to the owner.
These three types of property, street, station and Utility.
If a player owns all street of the same colour, they can buy a house.
At any point can a street have more than two houses that street with the smallest amount of houses, in the same colour group.
Street rent depends on the number of houses and a hotel on the street. Exact rent for a number of the house is defined inside a file.
Stations and Utilities rent depend on a number other station/Utilatis the owner owns.

If a player needs to pay and don't have cash, then the player is forced to sell property or mortgage their property.
If can't reach the cash need to pay, then the player is removed from the game.
When a player is removed from the game, all houses will be removed and there property will be ownerless.

The game will end when only one player left. The player left is the winner.

## Requirements analysis

Function requirements

1. Player land on an ownerless property, the player, get an option to buy the property.
2. Player land on not mortgage property owned by the other player, they will pay rent to the owner.
3. After player hade option of buying or didn't land on a property, the player gets the option to sell or mortgage properties.
4. After a player is given options to sell and mortgage property, the player turn ends.
5. Station: $rent = baseRent * 2^{owned - 1}$ where $owned$ is number of station owned by the station owner.
6. Utilisation $rent = factor \cdot dices$ if owner owners one Utilisation then $factor = 4$, if owner more then one $factor = 10$. dices are the value of the last throw
7. When player mortgage thy will receive half the buy value.
8. To remove Mordagaed status, the player needs to pay half the property buy price to the bank.
9. When a player can't play will need to sell and mortgage property to able to pay.
10. If a player can't receive cash to pay, then the player shall be removed from the game.
11. Property owned by a removed player shall be turned to ownerless.
12. The game ends when there is one player left.

Non-functionle

1. Street rent is defined in a JSON file.
2. Easy to see player cash.
3. Easy to see what player owned.

## Design

Se figure for the class

There should be a particular class for each type of property: street, Station Utilisation.
They will inherit from an abstract class property, with implement standard methods
and leaves rent as an abstract method. The property class inherit from SqaureImp to get board travels.

Problem with circular link list is easily making an infinite loop if tries to find a  square that doesn't exist.
The square will implement the Iterable interface to make more accessible to traverse the board,
There will need to get all station, all Utilation or get all street of the same colour.
To solve that, we apply special Iterator that returns square we want.

The game model needs in the future to enforce that only some player action is able at some state.
The solution is to have everything need to carry out a player action is stored in command object, action.
Then the command is sent to a game action handler. The action handlers act as a state machine for the game.
A command will only run if the action handler expects it.

The class Game master will handle witch player turn it is, and how next player.

## Test

Game model and IO part were using a unit test to make sure it works.
The main thing in the Game model that wasn't a unit test is the class Game.
Specify the state machine was not test.

## Summary of sprint

Six tokens were implanted and working correctly.
Player was apple to buy property, and the rent was taken from the squares.

PopWindow as implented on gui side, but was not intergted with modle.

Not implemented:

The action was never implanted to save time. Instead, there were only methods for the game witch GUI
could call, which leads to the state machine. Each state is a class.

Double dice roll was not implemented as then go jail need to be implemented.
Cards were never implemented, both in GUI and Game model. 

Refactor:

The task to move square is moved from Player to SquareImp as the player class shouldn't know how the board is structured.
