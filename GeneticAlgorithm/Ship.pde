class Ship {

  PVector pos;
  PVector vel = new PVector();
  PVector acc = new PVector();
  float fitness = 0;
  boolean completed = false;
  boolean crashed = false;
  int lifetime = 0;

  DNA dna;

  Ship() {
    pos = new PVector(width/2, height);
    dna = new DNA();
  }

  Ship(DNA dna) {
    pos = new PVector(width/2, height);
    this.dna = dna;
  }


  void applyForce(PVector force) {
    acc.add(force);
  }


  void update() {
    float d = dist(pos.x, pos.y, t.x, t.y);
    if (d < 10) {
      completed = true;
      pos = new PVector(t.x, t.y);
    }
    for (Obstacle obs : obstacles) {
      if (pos.x > obs.getX() && pos.x < obs.getX() + obs.getWidth() && pos.y > obs.getY() && pos.y < obs.getY() + obs.getHeight()) {
        crashed();
      }
    }
    if (pos.x > width || pos.x < 0 || pos.y > height || pos.y < 0) {
      crashed();
    }

    applyForce(dna.getGene(count));

    if (!completed && !crashed) {
      vel.add(acc);
      pos.add(vel);
      acc.mult(0);
      vel.limit(4);
    }
  }



  void calcFitness() {
    float d = dist(pos.x, pos.y, t.getX(), t.getY());
    fitness = map(d, 0, width, width, 0);
    if (completed)
      fitness *= 10;
    if (crashed) {
      fitness /= 10;
      fitness *= lifetime;
    } else 
    fitness *= lifespan;
  }


  void crashed() {
    lifetime = count;
    crashed = true;
  }


  void show() {
    pushMatrix();

    noStroke();
    fill(255, 35);
    translate(pos.x, pos.y);
    rotate(vel.heading());

    rectMode(CENTER);
    rect(0, 0, 25, 5);

    popMatrix();
  }


  //void calcFitness(Obstacle o){

  //}
}