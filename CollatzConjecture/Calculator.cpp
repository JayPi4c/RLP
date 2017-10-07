#include "Calculator.h"





unsigned long calculate(unsigned long i) {                                                                  //calculate the CollatzConjecture
  if (i % 2 == 1) {
    i = i * 3 + 1;
    return i;
  }
  if (i % 2 == 0) {
    i = i / 2;
    return i;
  }
}

int getDigits(int value) {                                                              //returns the digits of a givin Integer
  int counter = 1;
  for (int i = 10; value >= i;  i = i * 10) {
    counter++;
  } return counter;
}

String Time(unsigned long startMillis, unsigned long currentMillis) {                   //Calculate passed time by getting current millis and start millis
  String time;
  int h;
  int minutes;
  unsigned long sec =  (currentMillis - startMillis) / 1000;
  time = String(sec);
  if (sec > 60) {
    minutes = (int)sec / 60;
    sec = sec % (minutes * 60);
    time = String(minutes) + "," + String(sec);
    if (minutes > 60) {
      h = minutes / 60;
      minutes = minutes % (h * 60);
      sec = sec % (minutes * 60);
      time = String(h) + "," + String(minutes) + "," + String(sec);
    }
  }
  return time;
}

String toString(int input) {
  String output = (String) input;
  return output;
}
