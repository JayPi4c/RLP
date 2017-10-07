public class Circle {

  float x;
  float y;
  float speed;
  float angle = 0;
  float r;

  public Circle(float x, float y, float speed, float r) {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.r = r;
  }

  public void show() {
    noStroke();
    int red = (int)map(x, -width/2, width/2, -255, 255);
    fill(abs(red), 30, 30);
    ellipse(x, y, r, r);
  }

  public void update() {
    x = map(sin(angle), -1, 1, -width/2 + r/2, width/2 - r/2);
    angle += speed;
  }
}