class SolarSystem {

  ArrayList<Star> stars;
  ArrayList<Star> planets;
  ArrayList<Star> moons;

  ArrayList<Star> heavenlyBody;

  public SolarSystem(int sun, int pla, int moo) {
    stars = new ArrayList<Star>();
    planets = new ArrayList<Star>();
    moons = new ArrayList<Star>();
    heavenlyBody = new ArrayList<Star>();


    

    for (int i = 0; i < sun; i++) {
      stars.add(new Star(random(width), random(height), random(50, 70)));
    }

    for (Star s : stars) {
      for (int i = pla; i >= 0; i--) {
        planets.add(new Star(s, random(15, 35), random(65, 200)));
      }
    }

    for (Star p : planets) {
      for (int i = moo; i >= 0; i--) {
        moons.add(new Star(p, random(6, 12), random(20, 50)));
      }
    }

    heavenlyBody.addAll(stars);
    heavenlyBody.addAll(planets);
    heavenlyBody.addAll(moons);
  }
  
  
  public SolarSystem() {
    stars = new ArrayList<Star>();
    planets = new ArrayList<Star>();
    moons = new ArrayList<Star>();
    heavenlyBody = new ArrayList<Star>();


    stars.add(new Star(width/2, height/2, 65));

    for (Star s : stars) {
      for (int i = floor(random(10) + 1); i >= 0; i--) {
        planets.add(new Star(s, random(15, 35), random(65, height / 2 - 40)));
      }
    }

    for (Star p : planets) {
      for (int i = floor(random(3)); i >= 0; i--) {
        moons.add(new Star(p, random(6, 12), random(20, 50)));
      }
    }

    heavenlyBody.addAll(stars);
    heavenlyBody.addAll(planets);
    heavenlyBody.addAll(moons);
  }
  
  
  

  void update() {
    for (Star s : heavenlyBody) {
      s.update();
    }
  }

  void show() {
    for (Star s : heavenlyBody) {
      s.show();
    }
  }
}