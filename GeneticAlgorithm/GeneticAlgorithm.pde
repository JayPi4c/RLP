Population p;
Obstacle obstacles[];
Target t;
int count = 0;

int lifespan = 500;

int generation = 1;

void setup() {
  size(800, 600, P2D);
  p = new Population();
  //obs = new Obstacle(50, height/2, width-100, 20);
  obstacles = new Obstacle[2];//new Obstacle[3];
  obstacles[0] = new Obstacle(0, height/2, 400, 20);
  obstacles[1] = new Obstacle(width-400, height/2-100, 400, 20);
  //obstacles[2] = new Obstacle(0, height/2-200, 400, 20);
  // obs = new Obstacle(100, height/2, width-200, 20);
  t = new Target(width/2, 30, 15, 15);
}


void draw() {
  background(51);
  for (Obstacle o : obstacles) 
    o.show();

  t.show();
  count = ++count % lifespan;
  if (count == 0 || p.isDead()) {
    count = 0;
    p.evaluate();
    p.selection();
    generation++;
  }
  p.run();
  textSize(32);
  fill(0, 50);
  stroke(0);
  text("Generation: " + generation, 10, 40);
  text("lifespan:   " + count, 10, 80);
}