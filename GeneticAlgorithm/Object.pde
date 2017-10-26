abstract class Object {

  float x, y;
  float w, h;

  Object(float x, float y, float w, float h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
  
  abstract void show();
  
  float getWidth() {
    return this.w;
  }

  float getHeight() {
    return this.h;
  }

  float getX() {
    return this.x;
  }

  float getY() {
    return this.y;
  }
}