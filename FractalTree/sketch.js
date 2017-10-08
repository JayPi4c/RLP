// made by JayPi4c
// 8.10.17
// inspired by Daniel Shiffmans the coding train.
// This program includes two funcitons to create a fractal tree
// The branch(len) function creates a conventional fractal tree
// The randomBranch(len) function creates a fractal tree with a random branches count between 1 and 4

function setup() {
  createCanvas(400, 400);
  angleMode(RADIANS);
}

function draw() {noLoop();
  background(51);

  translate(width * 0.5, height);
  stroke(255);
  randomBranch(100);



  // translate(width/2, height);
  // stroke(255);
  // branch(100);
}


function randomBranch(len){
  line(0, 0, 0, -len);
  translate(0, -len);
  if(len > 1){
    let num = floor(random(4)) + 1;
    for(let i = 1; i <= num; i++){
      push();
      rotate(map(i, 1, num, -PI*0.25, PI * 0.25));
      randomBranch(len * 0.67);
      pop();
    }
  }
}



function branch(len){
    line(0, 0, 0, -len);
    translate(0, -len);
    if(len > 1){
      push();
      rotate(-PI * 0.1);
      branch(len * 0.67);
      pop();
      push();
      rotate(PI * 0.25);
      branch(len * 0.67);
      pop();
    }
}
