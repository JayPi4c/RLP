class Population {
  Ship ships[];
  int completedShips = 0;

  //populationSize
  int popsize = 25;

  ArrayList<Ship> matingpool;

  Population() {
    ships = new Ship[popsize];
    for (int i = 0; i < ships.length; i++) {
      ships[i] = new Ship();
    }
  }


  void evaluate() {

    completedShips = 0;
    float maxfit = 0;
    for (int i = 0; i < popsize; i++) {
      if (ships[i].completed)
        completedShips++;
      ships[i].calcFitness();
      if (ships[i].fitness > maxfit) {
        maxfit = ships[i].fitness;
      }
    }

    //normalize
    for (Ship s : ships) {
      s.fitness /= maxfit;
    }

    matingpool = new ArrayList<Ship>();
    for (int i = 0; i < popsize; i++) {
      int n = (int)(ships[i].fitness*100);
      for (int j = 0; j < n; j++) {
        matingpool.add(ships[i]);
      }
    }
  }

  void selection() {
    Ship newShips [] = new Ship[popsize];
    for (int i = 0; i < ships.length; i++) {
      int indexA = floor(random(matingpool.size()));
      int indexB = floor(random(matingpool.size()));
      DNA parentA = matingpool.get(indexA).dna;
      DNA parentB = matingpool.get(indexB).dna;

      DNA child = parentA.crossover(parentB);
      child.mutation(completedShips / popsize);
      newShips[i] = new Ship(child);
    }
    ships = newShips;
  }

  void update() {
    for (Ship s : ships) {
      s.update();
    }
  }

  void show() {
    for (Ship s : ships) {
      s.show();
    }
  }

  void run() {
    update();
    show();
  }

  boolean isDead() {
    for (Ship s : ships) {
      if (!s.crashed && !s.completed)
        return false;
    }
    return true;
  }
}