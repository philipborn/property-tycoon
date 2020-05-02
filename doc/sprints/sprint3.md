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
| Lee      | re-sizable board, style sheet, multipal Token, new Game box, Propery info           |
| Philip   | Game model basic game                                                               |
| Roksanna | Calsses of Property. Street, Station, Utilaty and make corner sqaure, token figures |
| Tom      | Game model basic game, intercarate game and controller                              |

## User stories

Game will have 6 players. Each turn player get to throw dices and move there token.
After a player have made a complit arond the board, the player will beask to buy a propertry hey land on.
If player chose to buy, cash will drawn. After player have moved, player can sell there there propertys, morgade or remove morgade of a property.
When player morgade, they will reachive halfe of the pice of the property in cash.

If other player land on the property that is, owned by other player and is not mordagde, will pay rent to the owner.
There three typs of property, street, station and Utility.
If player owner all street of same couler they can buy house.
At any point can a street have more then two houses that street with samllest amount of houseses, in same color group..
A street rent depend number of houses and hotel on the street. Exat rent for number of house is definde in a file.
Stations and Utilitys rent depend on number other statiaon/Utilatis the owerner owens.

If player need to pay and don't have cash, then player is forc to sell property or morgade theres property.
If can't reach the cash need to pay, then player is reomoved from the game.
When a player is removed from game, all houses will be removed and there property will be ownerless.

The game will end when only one player left. The player left is the winner.

## Requirements analysis

Function requriment

1. Player land on a ownerlsee proptery, player get option to buy the property.
2. Player land on not morgade property ownend by a other player, they will pay rent to the onwer.
3. After player hade option of buying or did't land on porperty, the player get option to sell or mordage properties.
4. After player given optiona to sell and mortgade property, the player turn ends.
5. Station: $rent = baseRent * 2^{owned - 1}$ where $owned$ is number of station owned by the station owner.
6. Utilation $rent = factor \cdot dices$ if owner one utlation then $factor = 4$, if owner more then one $factor = 10$. dices is the value of last throw
7. When player mordgade thy will recived hafle the buy value.
8. To reomved Mordagaed status, player need to pay halfe the property buy prives to the bank.
9. When player can't play will need to sell and mordage property to aplee to pay.
10. If player can't recived cash to pay, then the player skalle be removed from game.
11. Property owned by removed palyer skalle be turn to ownerless.
12. Game end when there is one player left.

Non-functionle

1. Street rent is defiande in a JSON file.
2. Easy to see player cash.
3. Easy to see what player owned.

## Design


## Summary of sprint
