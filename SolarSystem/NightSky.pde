//made by JayPi4c
//08.10.2017
//This program creates a little solar system
//in default it creates one sun and randomly its plantes and moons
//there is also the possibility to change the code the create a solar system with
//multiple suns and their planets.


SolarSystem SS;


void setup() {
  size(640, 480);

  SS = new SolarSystem();

  //                suns, planets, moons
  //SS = new SolarSystem(1, 3, 2);
}

void draw() {
  background(0);
  SS.update();
  SS.show();
}