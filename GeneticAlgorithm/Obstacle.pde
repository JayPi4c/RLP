class Obstacle extends Object {

  Obstacle(float x, float y, float w, float h) {
    super(x, y, w, h);
  }

  void show() {
    fill(200);
    stroke(0);
    rectMode(CORNER);
    rect(x, y, w, h);
  }
}