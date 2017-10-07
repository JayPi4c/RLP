//made by JayPi4c
//23.09.2017
//inspired by theCodingTrain (Daniel Shiffman) https://www.youtube.com/watch?v=PQwfop4bewM
//for original idea see: http://www.memo.tv/simple-harmonic-motion/

CircleSetup cS;

void setup() {
  size(600, 600);
  cS = new CircleSetup(80, 0, 0.01, 0.03); 
  background(0);
}

void draw() {
  background(0);
  translate(width/2, height/2);
  cS.update();
  cS.show();
}