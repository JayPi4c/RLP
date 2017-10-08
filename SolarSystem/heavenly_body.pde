class Star {

  Star parent;

  float radius;
  float distance;
  float x, y;

  float offset = 0;
  
  float speed;

  public Star(Star parent, float r, float dis) {
    this.parent = parent;
    this.radius = r;
    this.distance = dis;
    x = this.parent.x + sin(offset) * (this.distance + this.radius);
    y = this.parent.y + cos(offset) * (this.distance + this.radius);
    this.speed = random(-0.05, 0.05);
  }
  
  public Star(Star parent, float r, float dis, float speed) {
    this.parent = parent;
    this.radius = r;
    this.distance = dis;
    x = this.parent.x + sin(offset) * (this.distance + this.radius);
    y = this.parent.y + cos(offset) * (this.distance + this.radius);
    this.speed = speed;
  }
  
  public Star(float x, float y, float r){
    this.x = x;
    this.y = y;
    this.radius = r;
  }
  
  

  void update() {
    if (this.parent == null) {
      return;
    }
    x = this.parent.x + sin(offset) * (this.distance + this.radius);
    y = this.parent.y + cos(offset) * (this.distance + this.radius);
    offset += speed;
  }

  void show() {
    fill(255);
    noStroke();
    ellipse(this.x, this.y, this.radius, this.radius);
  }
}