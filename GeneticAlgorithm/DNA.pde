class DNA {

  PVector genes [] = new PVector[lifespan];

  DNA() {
    for (int i = 0; i < genes.length; i++) {
      genes[i] = PVector.random2D();
      genes[i].setMag(0.1);
    }
  }

  DNA(PVector [] genes) {
    this.genes = genes;
  }


  //void mutation(int rate) {
  //  ArrayList<Integer> indeces = new ArrayList<Integer>();
  //  for (int i = 0; i < genes.length; i++) {
  //    indeces.add(i);
  //  }

  //  for (int i = 0; i < rate; i++) {
  //    int rand = floor(random(indeces.size()));
  //    int index = indeces.remove(rand);
  //    genes[index].add(new PVector(random(-1, 1), random(-1, 1)));
  //    genes[index].setMag(0.2);
  //  }
  //}

  PVector getGene(int i) {
    return genes[i];
  }




  DNA crossover(DNA partner) {
    PVector [] newgenes = new PVector[genes.length];
    // Picks random midpoint
    int mid = floor(random(genes.length));
    for (int i = 0; i < genes.length; i++) {
      // If i is greater than mid the new gene should come from this partner
      if (i > mid) {
        newgenes[i] = genes[i];
      }
      // If i < mid new gene should come from other partners gene's
      else {
        newgenes[i] = partner.getGene(i);
      }
    }
    // Gives DNA object an array
    return new DNA(newgenes);
  }

  // Adds random mutation to the genes to add variance.
  //void mutation() {
  void mutation(float rate) {
    for (int i = 0; i < genes.length; i++) {
      // if random number less than 0.01, new gene is then random vector
      //if(random(1) < 0.01)
      if (random(1) < map(rate, 0, 1, 0.015, 0.0001)) {
        genes[i] = PVector.random2D();
        genes[i].setMag(0.2);
      }
    }
  }
}