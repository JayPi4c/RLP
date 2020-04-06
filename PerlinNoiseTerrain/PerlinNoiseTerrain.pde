// Daniel Shiffman
// http://codingtra.in
// http://patreon.com/codingtrain
// Code for: https://youtu.be/IKB1hWWedMk

int cols, rows;
int scl = 20;
int w = 2000;
int h = 1600;

float flying = 0;

float[][] terrain;

float minHeight = -25;

void setup() {
  // fullScreen(P3D);
  size(600, 600, P3D);
  surface.setLocation(displayWidth/2-width/2, displayHeight/2-height/2);
  cols = w/scl;
  rows = h /scl;
  terrain = new float[cols][rows];
}



void draw() {
  PVector l = new PVector();
  com.jogamp.nativewindow.util.Point p = new com.jogamp.nativewindow.util.Point();
  ((com.jogamp.newt.opengl.GLWindow)surface.getNative()).getLocationOnScreen(p);
  l.x = p.getX();
  l.y = p.getY();


  flying -= 0.1;

  float yoff = flying;
  for (int y = 0; y < rows; y++) {
    float xoff = (l.x/100);
    for (int x = 0; x < cols; x++) {
      float val = map(noise(xoff, yoff), 0, 1, -100, 100);
      terrain[x][y] = val > minHeight ? val : minHeight;
      xoff+= 0.2;
    }
    yoff +=0.2;
  } 


  background(0);
  noFill();

  noStroke();
  translate(width/2, height /2 + 50);
  rotateX(PI/3);
  translate(-w/2, -h/2);

  for (int y = 0; y < rows-1; y++) {
    beginShape(TRIANGLE_STRIP);
    for (int x = 0; x < cols; x++) {
      stroke(minHeight == terrain[x][y] ? color(0, 0, 255) : 255);
      vertex(x*scl, y*scl, terrain[x][y]);
      stroke(minHeight == terrain[x][y+1] ? color(0, 0, 255) : 255);
      vertex(x*scl, (y+1)*scl, terrain[x][y+1]);
    }
    endShape();
  }
}

void mouseClicked() {
}
