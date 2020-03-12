// 3D Earthquake Data Visualization
// The Coding Train / Daniel Shiffman
// https://thecodingtrain.com/CodingChallenges/058-earthquakeviz3d.html
// https://youtu.be/dbs4IYGfAXc
// https://editor.p5js.org/codingtrain/sketches/tttPKxZi

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;

import peasy.PeasyCam;

float angle;

Table table;
float r = 200;

PImage earth;
PShape globe;

Calendar current;
Calendar begin, end;

SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");

float xAngle;

PeasyCam cam;

void setup() {
  size(600, 600, P3D);
  earth = loadImage("earth.jpg");
  // https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Deaths.csv // Deaths
  table = loadTable("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv", "header"); // Confirmed Cases
  // https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Recovered.csv // recovered


  cam = new PeasyCam(this, 400);

  noStroke();
  globe = createShape(SPHERE, r);
  globe.setTexture(earth);

  xAngle =  -QUARTER_PI/2;


  // init timing
  String beginDate = table.getColumnTitle(4);
  String endDate = table.getColumnTitle(table.getColumnCount()-1);
  // println(beginDate + " " + endDate);

  current = Calendar.getInstance();
  begin = Calendar.getInstance();
  end = Calendar.getInstance();
  try {
    current.setTime(sdf.parse(beginDate));
    begin.setTime(sdf.parse(beginDate));
    end.setTime(sdf.parse(endDate));
    end.add(Calendar.DAY_OF_MONTH, 1);
  }
  catch(ParseException e) {
    println("error");
  }
}

void draw() {
  background(51);

  if (frameCount % 60 == 0)
    current.add(Calendar.DAY_OF_MONTH, 1);
  if (current.get(Calendar.DAY_OF_YEAR) == end.get(Calendar.DAY_OF_YEAR) && current.get(Calendar.YEAR) == end.get(Calendar.YEAR))
    current.setTime(begin.getTime());

  String date = sdf.format(current.getTime());



  //translate(width*0.5, height*0.5);
  //  rotateX(xAngle);
  // rotateY(angle);
  //  angle += 0.01;

  /*
stroke(255, 0, 0);
   line(0, 0, -500, 0, 0, 500);
   stroke(0, 255, 0);
   line(500, 0, 0, -500, 0, 0);
   stroke(0, 0, 255);
   line(0, -500, 0, 0, 500, 0);
   */

  lights();
  fill(200);
  noStroke();
  //sphere(r);
  shape(globe);

  for (TableRow row : table.rows()) {
    float lat = row.getFloat("Lat");
    float lon = row.getFloat("Long");

    float count = row.getFloat(date);
    // original version
    // float theta = radians(lat) + PI/2;

    // fix: no + PI/2 needed, since latitude is between -180 and 180 deg
    float theta = radians(lat);

    float phi = radians(lon) + PI;

    // original version
    // float x = r * sin(theta) * cos(phi);
    // float y = -r * sin(theta) * sin(phi);
    // float z = r * cos(theta);

    // fix: in OpenGL, y & z axes are flipped from math notation of spherical coordinates
    float x = r * cos(theta) * cos(phi);
    float y = -r * sin(theta);
    float z = -r * cos(theta) * sin(phi);

    PVector pos = new PVector(x, y, z);

    //float h = pow(10, count);
    //float maxh = pow(10, 7);
    float h = count * 0.01;//map(h, 0, maxh, 10, 100);
    PVector xaxis = new PVector(1, 0, 0);
    float angleb = PVector.angleBetween(xaxis, pos);
    PVector raxis = xaxis.cross(pos);


    if (count > 0) {
      pushMatrix();
      translate(x, y, z);
      rotate(angleb, raxis.x, raxis.y, raxis.z);
      fill(255);
      box(h, 5, 5);
      popMatrix();
    }
  }
  cam.beginHUD();
  fill(0, 128);
  rect(0, 0, 70, 30);
  fill(255);
  text("" + date, 10, 18);
  cam.endHUD();
}
