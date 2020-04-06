// Flocking
// Daniel Shiffman
// https://thecodingtrain.com/CodingChallenges/124-flocking-boids.html
// https://youtu.be/mhjuuHl6qHM
// https://editor.p5js.org/codingtrain/sketches/ry4XZ8OkN

import peasy.PeasyCam;


PeasyCam cam;

Boid[] flock;

float alignValue = .5;
float cohesionValue = 0.85;
float seperationValue = 1;

int space = 200;
int half_space = (int)(space*0.5);

  int n = 20;
void setup() {
  size(600, 600, P3D);

  cam = new PeasyCam(this, 400);




  flock = new Boid[n];
  for (int i = 0; i < n; i++) {
    flock[i] = new Boid();
  }
 }
float theta = 0;
void draw() {
   rotateX(-QUARTER_PI);
  rotateY(theta);
 
  theta+= 0.01;
  background(0);
  for (Boid boid : flock) {
    boid.edges();
    boid.flock(flock);
    boid.update();
    //boid.show();
  }
int skip = 25;
  for (int x = -half_space; x < half_space; x+=skip) {
    for (int y = -half_space; y < half_space; y+=skip) {
      for (int z =-half_space; z < half_space; z+=skip) {
       float distances[] = new float[n];
       for(int i = 0; i < n; i++){
         PVector v = flock[i].position;
         distances[i] = dist(x,y,z, v.x,v.y,v.z);
       }
       
       distances = sort(distances);
       
       float d0 = distances[0];
       float alpha = map(d0, 0, space/8, 255, 0);
       push();
       noFill();
       stroke(255, alpha);
       translate(x,y,z);
       box(skip);
       pop();
      }
    }
  }

  //noFill();
  //stroke(255);
  //strokeWeight(1);
  //box(space);
  
  
 // if(theta > 2*TAU)
  //  System.exit(0);
  
 // saveFrame("frames/img####.png");
}
