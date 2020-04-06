// Flocking
// Daniel Shiffman
// https://thecodingtrain.com/CodingChallenges/124-flocking-boids.html
// https://youtu.be/mhjuuHl6qHM
// https://editor.p5js.org/codingtrain/sketches/ry4XZ8OkN

class Boid {
  PVector position;
  PVector velocity;
  PVector acceleration;
  int maxForce;
  int maxSpeed;
  float half_space = space*0.5;

  Boid() {  
    this.position = new PVector(random(-half_space, half_space), random(-half_space, half_space), random(-half_space, half_space));
    this.velocity = PVector.random3D();
    this.velocity.setMag(random(2, 4));
    this.acceleration = new PVector();
    this.maxForce = 1;
    this.maxSpeed = 4;
  }

  void edges() {
    if (this.position.x > half_space) {
      this.position.x = -half_space;
    }else if(this.position.x < -half_space){
      this.position.x = half_space;
    }
    if (this.position.y > half_space) {
      this.position.y = -half_space;
    }else if(this.position.y < -half_space){
      this.position.y = half_space;
    }
    if (this.position.z > half_space) {
      this.position.z = -half_space;
    }else if(this.position.z < -half_space){
      this.position.z = half_space;
    }
  }

  PVector align(Boid[] boids) {
    int perceptionRadius = 50;
    PVector steering = new PVector();
    int total = 0;
    for (Boid other : boids) {
      float d = dist(this.position.x, this.position.y, other.position.x, other.position.y);
      if (other != this && d < perceptionRadius) {
        steering.add(other.velocity);
        total++;
      }
    }
    if (total > 0) {
      steering.div(total);
      steering.setMag(this.maxSpeed);
      steering.sub(this.velocity);
      steering.limit(this.maxForce);
    }
    return steering;
  }

  PVector separation(Boid[] boids) {
    int perceptionRadius = 50;
    PVector steering = new PVector();
    int total = 0;
    for (Boid other : boids) {
      float d = dist(this.position.x, this.position.y, this.position.z, other.position.x, other.position.y, other.position.z);
      if (other != this && d < perceptionRadius) {
        PVector diff = PVector.sub(this.position, other.position);
        diff.div(d * d);
        steering.add(diff);
        total++;
      }
    }
    if (total > 0) {
      steering.div(total);
      steering.setMag(this.maxSpeed);
      steering.sub(this.velocity);
      steering.limit(this.maxForce);
    }
    return steering;
  }

  PVector cohesion(Boid[] boids) {
    int perceptionRadius = 100;
    PVector steering = new PVector();
    int total = 0;
    for (Boid other : boids) {
      float d = dist(this.position.x, this.position.y, this.position.z, other.position.x, other.position.y, other.position.z);
      if (other != this && d < perceptionRadius) {
        steering.add(other.position);
        total++;
      }
    }
    if (total > 0) {
      steering.div(total);
      steering.sub(this.position);
      steering.setMag(this.maxSpeed);
      steering.sub(this.velocity);
      steering.limit(this.maxForce);
    }
    return steering;
  }

  void flock(Boid[] boids) {
    PVector alignment = this.align(boids);
    PVector cohesion = this.cohesion(boids);
    PVector separation = this.separation(boids);

    alignment.mult(alignValue);
    cohesion.mult(cohesionValue);
    separation.mult(seperationValue);

    this.acceleration.add(alignment);
    this.acceleration.add(cohesion);
    this.acceleration.add(separation);
  }

  void update() {
    this.position.add(this.velocity);
    this.velocity.add(this.acceleration);
    this.velocity.limit(this.maxSpeed);
    this.acceleration.mult(0);
  }

  void show() {
    pushMatrix();
    translate(this.position.x, this.position.y, this.position.z);
    fill(255);
    noStroke();
    sphere(10);
    popMatrix();
  }
}
