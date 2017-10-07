public class CircleSetup {

  Circle circles[];



public CircleSetup(int number, float xStart, float speedMin, float speedMax) {
    circles = new Circle[number];
    float radius = height/number;
    for (int i = 0; i < number; i++) {
      float y = -height/2 + radius + i*((height-radius)/number);
      float speed = map(i, 0, circles.length-1, speedMin, speedMax);
      circles[i] = new Circle(xStart, y, speed, radius);
    }
  }



  public CircleSetup(int number, float xStart, float radius, float speedMin, float speedMax) {
    circles = new Circle[number];
    for (int i = 0; i < number; i++) {
      float y = -height/2 + radius + i*((height-radius)/number);
      float speed = map(i, 0, circles.length-1, speedMin, speedMax);
      circles[i] = new Circle(xStart, y, speed, radius);
    }
  }

  public void update() {
    for (Circle c : circles)
      c.update();
  }

  public void show() {
    for (Circle c : circles)
      c.show();
  }
}