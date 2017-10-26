class Target extends Object {

  Target(float x, float y, float w, float h) {
    super(x, y, w, h);
  }

  void show() {
    fill(100);
    noStroke();
    ellipse(x, y, w, h);
  }
}