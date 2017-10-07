#include "Calculator.h"

String calculate(String i) { //calculate the CollatzConjecture

  int number = (i.charAt(i.length() - 1) - '0');
  if (number % 2 == 1) {
    i = stringAddition(stringMultiplication(i, 3), "1");
    return i;
  }
  else if (number % 2 == 0) {
    i = stringDivision(i, 2);
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
  String t;
  int h;
  int minutes;
  int sec =  (currentMillis - startMillis) / 1000;
  t = convertNumber(sec);
  if (sec > 60) {
    minutes = sec / 60;
    sec = sec % (minutes * 60);
    t = convertNumber(minutes) + ";" + convertNumber(sec);
    if (minutes > 60) {
      h = minutes / 60;
      minutes = minutes % (h * 60);
      sec = sec % (minutes * 60);
      t = convertNumber(h) + ":" + convertNumber(minutes) + ";" + convertNumber(sec);
    }
  }
  return t;
}

String convertNumber(int n) {
  if (String(n).length() == 1) {
    String s = "0";
    s = s + String(n);
    return s;
  }
  return String(n);
}

void clearLCDLine(LiquidCrystal_I2C lcd, int Line) {
  lcd.setCursor(0, Line);
  lcd.print("                    ");
  lcd.setCursor(0, Line);
}

String stringDivision(String number, int divisor) {
  int uebertrag = 0;
  int n = 0;

  String temp = "";

  for (int i = 0; i < number.length(); i++) {
    int num = number.charAt(i) - '0';
    n = num / divisor;
    temp += (n + uebertrag);

    uebertrag = (int)((((double)num / divisor) - n) * 10);
  }

  if (uebertrag != 0) {
    temp += ".";
    temp += uebertrag;
  }

  return temp;
}

String stringAddition(String a, String b) {
  String temp = "";

  if (a.length() > b.length()) {
    for (int i = a.length() - b.length(); i > 0; i--) {
      b = "0" + b;
    }
  } else if (a.length() < b.length()) {
    for (int i = b.length() - a.length(); i > 0; i--) {
      a = "0" + a;
    }
  }

  int uebertrag = 0;
  int number = 0;
  for (int i = a.length() - 1; i >= 0; i--) {
    int altUebertrag = uebertrag;
    int numberA = a.charAt(i) - '0';
    int numberB = b.charAt(i) - '0';
    uebertrag = (int)((numberA + numberB + altUebertrag) / 10);
    number = (numberA + numberB + altUebertrag) - (uebertrag * 10);
    temp = number + temp;
  }
  if (uebertrag != 0) {
    temp = uebertrag + temp;
  }

 
  return temp;
}

String stringMultiplication(String number, int factor) {
  int uebertrag = 0;
  int n = 0;

  String temp = "";

  for (int i = number.length() - 1; i >= 0; i--) {
    int num = number.charAt(i) - '0';
    n = ((num * factor) + uebertrag);
    uebertrag = (int)(n / 10);
    n = n - (uebertrag * 10);
    temp = n + temp;
  }
  if (uebertrag != 0) {
    temp = uebertrag + temp;
  }

  return temp;

}

String updateNumber(String num) {
  String tmp = "";
  int i;
  for (i = 0;i < num.length(); i++) {
    if (num.charAt(i) != '0') {
      break;
    }
  }

  for (i = i; i < num.length(); i++) {
    tmp += num.charAt(i);
  }
  return tmp;
}
