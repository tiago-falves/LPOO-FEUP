# Unbinding of Isaac


The Unbinding of Isaac is a dungeon crawler video game where the main hero is Isaac, a boy whose objective is to escape his mother's basement. On his way, he will find bizarre treasures that change his form giving him super human abillities and enabling him to fight mysterious creatures, discover secrets and find his way to safety.


This project was developed by Miguel Pinto and Tiago Alves.

To Run the project use ./gradlew run

## Implemented features


In this part of the project the main focus was to have a strong architecture foundation so it would be easy to implement new features in the future. 

**Movable Isaac** - The main character, Isaac, will move inside the screen when one of the four arrows is pressed.

**Wall** - The wall is a static element without any behaviour or interaction that can not be destroyed.

**Enemy Variety** - There were implemented 10 kinds of monsters, each one having different movement strategies, actions, health and power. Some of their abilities are to follow the hero or, for example, shoot four granades at once!

**Collisions** - All of the important collisions are correctly detected, acting in different ways depending on the type of collision.

**Projectiles** - Isaac is able to shoot infinite bullets in order to kill monsters by pressing SPACE and moving in the direction he wants to shoot. Some monsters also have this ability. There are many types of projectiles in the game, each one having its own representation and power.

**Multi-character Elements** - Each element of the game has it's own representation, being able to have many characters, in both axis, resulting in pleasent representation of the game.

**Health** - Isaac has health associated to him, being decremented each time he collides with a monster. The monsters also have health that's reduced when Isaac's bullets hit them.

**Element Update** - The game features a cycle with non-blocking input, allowing the elements to update continuously while receiving input to move Isaac.

**Lose by Death** - If Isaac is hit by monsters too many times, then he'll die and lose the game.

**Quit Game** - When ESC is pressed, the game ends.

**Main menu** - The first thing that appears in the game, a welcoming page than enables the user to choose various options.

**Score** - It was decided to create a score that is based on the cumulative time the player needs to complete the different levels. 


**Random room generator** - This feature makes the game more interesting, being different every time someone plays it. A random grid of rooms is randomly generated, each one with different types of enemies, keys and treasures.


**Treasures** - As said before, during his journey Isaac can find many treasures, each one giving him more health or super human abilities, like for example, different types of powerful projectiles.

**Traps** - If the user gets close to one, or collides with it, harms him in some waay. There could also be holes in the ground where the player falls to his inevitable death.

**Information Bar** - A bar at the top of the screen with different information:
 * **Time**: a counter representing the elapsed time, in seconds, since the beginning of the game;
 * **Score**: a number representing the current score that the player has already accumulated;
 * **Health**: A number that represents Isaac's health. 


![](https://i.imgur.com/zPbdYCg.gif)


![](https://i.imgur.com/OixhbDV.png)


![](https://i.imgur.com/Lz6lyDB.png)


![](https://i.imgur.com/Y7DXPYf.png)


![](https://i.imgur.com/v5IyPBc.png)






## Design


### Interacting with Isaac

#### Problem in context

During the game the user is able to perform many operations with the hero, like using the arrows to move him or, in the future, to pick up weapons, shoot and much more.

Although they would all have different requests and interactions, they would all have to interact with hero, being needed a way to unify them all.


#### The Pattern

To solve this problem it was decided to use the Command pattern. This patterns has the abillity to encapsulate our requests and to parameterize different contextual requests inside the same class.

#### Implementation

This pattern was conceived by creating an abstract class Command that has a method execute, which is implemented by each concrete MovementCommand and is called to perform the defined operation. We defined 4 commands, MoveIsaacUp, MoveIsaacDown, MoveIsaacLeft and MoveIsaacRight. These are called by the View Controllers depending on the recieved input, without having to know how they are implemented and what they do.

![](https://i.imgur.com/2Fkfvoy.png)

These classes can be found in the following files:

* [GameController](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/GameController.java)
* [Command](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/Command.java)
* [MoveUpCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/MoveUpCommand.java)
* [MoveDownCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/MoveDownCommand.java)
* [MoveLeftCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/MoveLeftCommand.java)
* [MoveRightCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/MoveRightCommand.java)
* [NothingCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/NothingCommand.java)
* [ShootCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/ShootCommand.java)


#### Consequences

With the command pattern we effectively decouple the invoker from the objects that know how to perform them. In addition to this, it is very easy to add another command to the hero.




### Monster Movement

#### Problem in context

Hardcoding the movement of every monster in each Monster class had may problems. Firstly each monster element would have to know much about the logic of the game, which is a controller property, not a model one. This would also make modulation very difficult, creating a new Enemy that would only differ from the first one in one parameter, the movement logic, would require some extra logic which is uncalled for.


#### The Pattern

The best solution we found to solve this problem was the implementation of the Strategy Pattern. This pattern allows us to define a family of movement strategies, encapsulate each one and make them interchangeable, making the algorithm vary depending on the monster that is using it.


#### Implementation

![](https://i.imgur.com/ksXwvAT.png)

These classes can be found in the following files:

* [Monster](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/model/element/monsters/Monster.java)
* [Bugs](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/model/element/monsters/Bugs.java)
* [Zombie](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/model/element/monsters/Zombie.java)
* [MoveQuickRandomStrategy](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/element/movementstrategy/MoveQuickRandomStrategy.java)
* [MoveRandomStrategy](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/element/movementstrategy/MoveRandomStrategy.java)
* [MoveStrategy](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/element/movementstrategy/MoveStrategy.java)



#### Consequences

This pattern allowed to keep the Monster model from the logic of the game, not being needed to have many conditionals in the controller to give each monster the correct movement. Instead, now each monster has it's own movement strategy and is now possible to use polymorphism to activate the right movement. Using this pattern also separated the movement logic from the monster, making other elements being able to user this movement strategies.

### Action

#### Problem in context

During the implementation of the game, we realized that the monsters were somewhat limited because they could only vary in it's health, power and movement strategy. The problem was that the room controller did not need to know what type of action each monster should perform, and even Isaac should be able to use some of theese actions. Although the majoraty of monsters still does not have an "ability", we wanted a design that would be easy to add new ones.

#### The Pattern

To solve this problem we implemented the Command pattern, making each monster know it's own action and making the room controller simply ask for it on every iteration. Since some actions need to be called at different times, like shooting different types of projectiles, each actions knows when to be executed.

#### Implementation

The following pattern illustrates how the pattern was implemented.

![](https://i.imgur.com/Lkrz7dG.png)

These classes can be found in the following files:

* [MonsterController](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/element/MonsterController.java)
* [Action](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/action/Action.java)
* [Shoot](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/action/Action.java)
* [ShootDirections](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/action/Action.java)


#### Consequences

By using this pattern, the room controller stays completely unaware of what type of actions each monster has, simply calling them in each iteration. This makes it very easy to add new kind of abilities to the monsters or even modify the current ones. Using the powerups effect, even Isaac is able to use some of the monster actions, not making any repeated code.

### Button Command

#### Problem in Context

When implementing the Menu, we found ourselves in a situation where the MenuController had to interact with the buttons in the Model and somehow change the Game state. However, neither the Models nor the View Controllers should know how to handle Game states.

#### The Pattern

To solve this problem we implemented the Command pattern, encapsulating the Game state changes in the Buttons themselves.

#### Implementation

This pattern was concieved by creating the abstract class ButtonCommand, that recieves a GameController and operates on it. The class has the method execute that is implemented by each concrete ButtonCommand and is called to performe the defined operation. In our project we defined two types of buttons, StartGame and ExitGame.
![](https://i.imgur.com/QKo0FhH.png)

These classes can be found in the following files:
* [ButtonModel](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/model/menu/ButtonModel.java)
* [ButtonCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/button/ButtonCommand.java)
* [ExitGameCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/button/ExitGameCommand.java)
* [StartGameCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/button/StartGameCommand.java)

#### Consequences 

With this pattern it becomes very easy to add and modify buttons to the Menu, with the defined behaviour encapsulated inside them, without having to change anything in the Menu Controller.


### Isaac Interactions

#### Problem in Context

With the creation of powerups, each time Isaac collided with one treasure or trap, there were a lot of if statements to assert wether the player had collided with one of theese elements and if so, which type of element it was, so it could call another function in the Collision Controller class, making it more aware than it should about the effects of each powerup. This also made it extremelly difficult to add new PowerUps.

#### The Pattern

Ok we swear this is the last one... Despite of our clear attraction to Command Patterns, we decided that it would be the most appropriate Pattern to solve this problem. This pattern normally encapsulates one behavior into a stand alone object allowing to parametrize clients (the other PowerUps) with different interactions.


#### Implementation 

The following diagram illustrates how this pattern was implemented:
![](https://i.imgur.com/YstO2So.png)


### Placement Command



#### Problem in context

After generating the random map, whenever Isaac entered a new room, he went straight to the middle of it. When we added the feature that Isaac would be positioned in a logic position, we realized that the Room Controller was once again becoming a god class and that the logic of the game was not needed there, we needed to encapsulate the information.


#### The Pattern

For the last time... The Command pattern for the reasons mentioned above.

#### Implementation

The Implementation of this pattern can be seen in the following UML:
![](https://i.imgur.com/BHB0eW6.png)

The classes can be found in the following files:

* [MapController](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/map/MapController.java)
* [PlacementCommand](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/room/PlacementCommand.java)
* [PlaceBottom](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/room/PlaceBottom.java)
* [PlaceLeft](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/room/PlaceLeft.java)
* [PlaceRight](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/room/PlaceRight.java)
* [PlaceTop](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/commands/room/PlaceTop.java)




#### Consequences

### The View

#### Problem in Context

During the development of our project, after Refactoring most of the code there was still a section that we did not like, the view. We realized that with our implementation, it was very hard to adpat our code to a different view, and a new one would probably need much of the code that was already created for other views.
In addition, for Lanterna we noticed that it would make sense to divide the highest level Views in smaller more specific classes, such as MenuView and GameOverView while keeping the same behaviour through all of them.

#### The Pattern
To solve the first problem we implemented an Abstract Factory pattern which, depending on the target platform, creates Views specific for it.

The second problem was solved with a Composite Pattern, creating Views which are composed of other Views.


#### Implementation

To implement the Abstract Factory we created an interface, ViewFactory, that specifies what each concrete factory must be able to create.Then, one concrete factory that implements this interface, LanternaFactory, was created. When the application starts, based on the target platform specified, the correct concrete factory is instanciated and used throughout the rest of the program, not having to worry with the specific type in use.

The composite pattern was achieved by creating an abstract class View, with an abstract method draw. We then composed our Lanterna classes in a tree structure where both high level views, such as LanternaStateView, and more specific views, such as LanternaPlayView, extend View and specify how to draw themselves. These higher level views are then composed of other Views and, when asked to draw themselves, call draw in each of their parts.

The following diagram represents how the mentioned patterns were implemented:

![](https://i.imgur.com/4O8I6ws.png)

The classes can be found in the following files:
* [ViewFactory](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/ViewFactory.java)
* [View](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/View.java)
* [StateView](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/View.java)
* [LanternaFactory](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/lanterna/LanternaFactory.java)
* [LanternaStateView](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/lanterna/LanternaStateView.java)
* [LanternaHudView](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/lanterna/game/LanternaHUDView.java)
* [LanternaPlayView](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/lanterna/game/LanternaPlayView.java)
* [LanternaButtonView](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/lanterna/menu/LanternaButtonView.java)
* [LanternaGameOverView](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/lanterna/menu/LanternaGameOverView.java)
* [LanternaGameWonView](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/lanterna/menu/LanternaGameWonView.java)
* [LanternaMenuView](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/view/lanterna/menu/LanternaMenuView.java)



### Game States

When we added the Menu to the game, there was a sudden urge to use the same keys to perform different actions, depending on where we were in the game. We also needed an elegant way of switching between different stages, that would allow us enough flexibility to add or remove a new stage as the development of the game progressed, which was needed when we implemented the GameOver and the Winning state.

#### The pattern

The obvious solution to this problem was the State Pattern, a design pattern that allows an object to alter its behaviour when its internal state changes.


#### Implementation

The following diagram illustrates how this pattern was implemented
![](https://i.imgur.com/ZokaeM2.png)

The classes can be found in the following files

* [GameController](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/GameController.java)
* [State](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/state/State.java)
* [GameOverState](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/state/GameOverState.java)
* [GameWonState](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/state/GameWonState.java)
* [MenuState](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/state/MenuState.java)
* [PlayState](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/state/PlayState.java)



#### Consequences 

Like expected, this pattern allows to easily add more stages to a game very easly, or change them. It also removed some of the logic of the game controller and took care of many complex "ifs" in the process.

### Isaac's Health and Updating Time

#### Problem in context
One of the planned features was to have a Heads-up display (HUD), where the user could see, for example, Isaac's current health. As our project was designed at the time, the only way for the view to get this information would be to be constantly asking for it. That was very unnecessary, given the fact that Isaac spends long moments without losing any health (especially at the hands of skilled players). This was also applied to the timer of the HUD

#### The Pattern

Observer pattern is used when there is one-to-many relationship between objects such as if one object is modified, its depenedent objects are to be notified automatically. 

#### Implementation
The following diagram illustrates how this pattern was implemented
![](https://i.imgur.com/y2al1Fy.png)
The classes can be found in the following files

* [MapController](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/map/MapController.java)
* [IsaacHealthObserver](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/observer/IsaacHealthObserver.java)
* [Observable](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/controller/observer/Observable.java)
* [HUDModel](https://github.com/FEUP-LPOO/lpoo-2020-g24/blob/master/project/src/main/java/g24/model/hud/HUDModel.java)

#### Consequences

The main consequence of this solution is that it uncouples the classes previously strongly dependent which is precisely what we needed. It also facilitates the addition of a new element that has to know when Isaac loses health.


#### Similar Occurences

### Memento

#### Problem in context

After implementing the Dungeon, instead of making a completely random implementation, we wanted to memorize the previous rooms. For this it would be necessary to save all the enemies positions, powerups and etc.

#### The pattern

Memento is a behavioral design pattern that lets you save and restore the previous state of an object without revealing the details of its implementation. The pattern suggests storing the copy of the object’s state in a special object called memento. The contents of the memento aren’t accessible to any other object except the one that produced it. Other objects must communicate with mementos using a limited interface which may allow fetching the snapshot’s metadata (creation time, the name of the performed operation, etc.), but not the original object’s state contained in the snapshot.

#### Implementation

When changing room, if the room has already been visited, it loads it, mantaining the same state as when it was left. This is done through a LinkedHashMap that connects a position in the map grid to a RoomModel object.

#### Consequences

This pattern allowed us to save all the information without compromising the the code, providing a clean solution for this complex problem. 





## Code Smells and Refactoring Suggestions


### Data Class

In this project it was decided to use the MVC Architectural pattern. Although this Architectural pattern brings many advantages, it also brings a major contradiction: Should every class present in the Model only contain fields and crude methods for accessing them, making the controller the responsible for knowing how the data is transformed and modeled, or should these classes be "smarter" and have more than setters and getters? Either way, we decided to include it here as the classes in the Model are in fact, Data classes.


### Parallel Inheritance Hierarchies 

This smell code happens if by creating a class, the user is obliged to create a subclass for another class. This code smell can be found in our Powerups, where each time we create a treasure or trap, it is also needed to create a interaction command to verify what haapens when Isaac collides with it. This could be solved by transfering the logic of the game to each powerup, each one knowing what would happen when someone interacts with it. We decided to implement this solution so we hade more re-usable code and to separate the logic from the Model of the powerUps.


### Speculative Generality

This code smell is present in the View Factory. This class is not very necessary when we only have one kind of view, the Lanterna. On the other hand, our view was to static and very hard to change if needed, so we decide to implement this pattern since a text based GUI is not the most adequate one for this game.


## Additional Topics

### Architectural Pattern

In this project we decided to use the MVC Architectural Pattern, as suggested by professor André Restivo. The objective of this architecture is to separate internal representations of information from the ways information is presented to the user, and it brings many advantages with it. Firstly, it facilitates simultaneous development, as developers are able to work in parallel on different components without affecting or blocking on another. Secondly, because of the separation of responsibilies, future development and modifications become much easier, being possible to, for example, change the aspect of the game (that is, the View) completely without changing the Model or the Controller. Finally it has the advantage that each part can be tested independently.

### Branch organization

In order to have a good git flow, we decided to use branches with the following protocol:

| Prefix    | Description                                                                        |
| --------- | ---------------------------------------------------------------------------------- |
| feature/  | Adds a new feature, such as new types of monsters, automatic room generation, etc. |
| fix/      | Fixes a bug in the behaviour of a given feature                                    |
| design/   | Implements a certain design pattern                                                |
| refactor/ | Changes the code structure without altering its behaviour                          |
| test/     | Adds unit tests for a given feature                                                |


## Testing

![](https://i.imgur.com/1KKXKXX.png)

![](https://i.imgur.com/4Hjx5n3.png)

To view the testing results in more detail, [click here](http://lpoo-g24-2020-test.surge.sh/).
To view the mutation testing results in more detail, [click here](http://lpoo-g24-2020-pitest.surge.sh/).



## Self-evaluation
Each member of the group has put in time for the project in pretty much the same proportions:

* Miguel Pinto - 50%
* Tiago Alves - 50%
