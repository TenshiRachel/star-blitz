## Star Blitz
Star Blitz is a game inspired by the retro arcade game Space Invaders. 

This project aims to apply OOP concepts to implement a base Game Engine and build a game on top of it.

## Framework
* Java
* LibGDX

## Game Engine includes:
* Behaviour Manager

    * Player controls
    * AI Controls
    * Movement related collision resolvements (eg Recoil on collide)
* Entity Manager

    * Create, render, update entities
* Scene Manager

    * Implements LibGDX Scene interface
* Collision Manager

    * Handle game wise collision logic (eg Remove entity on collide)
* IO Manager

    * Codes to manage input from user devices go here (Keyboard, etc)
* Simulation Lifecycle Management
    
    * MyGame class creates the game and sets scenes

## How to run
1. Download the project
2. In Eclipse, run the project

## Useful docs
1. [LibGDX Scene 2D](https://libgdx.com/wiki/graphics/2d/scene2d/scene2d-ui)
2. [LibGDX Game class](https://libgdx.com/wiki/start/simple-game-extended)
