/*
      CollatzConjecture v. 1.4
      This Sketch calculates integer  to get to one by using the Colloatz Conjecture "rules"
      if a number is odd: multiply it with 3 and  add 1; if a number is even: divide it by 2
      This Sketch will also make a list of all numbers which a new highscore
      by calculating a connected lcd will show the progress and after that it will show all numbers getting a
      new highscore by printing the number and his highscore of steps
      Made by JayPi4c  December 19, 2016
*/


#include <Wire.h>
#include <LiquidCrystal_I2C.h>              //includes the library of LiquidCrystal_I2C to print on the lcd

#include "Vars.h"                           //includes the tab Vars which is including the main var which controls the length of the array which saves the highscores, so it is
                                            //the only thing you need to change
#include "Calculator.h"                     //includes the calclator which includes also some other functions to make code easier to understand

LiquidCrystal_I2C lcd(0x27 , 20 , 4);       //creating an object of the lcd

int  List[MAXIMUM][2];                      //creating an 2D array which includes a number of saving highscores and at each place the highscorenumber with its steps
int  ListCounter = 0;                       //Listcounter to control if the list is full, obviously it is possilble with the array, but it is much easier to understand by another var

int  Steps = 0, maxSteps = 0;               //var to save the current steps and var to save the current highscoresteps
int  xStart = START;                        //setting a var to a const value of the Vars.h file; xStart is increasing by 1 after each calculation to one
unsigned long  i;                           //var which is being manipulated during the calculation

unsigned long  CurrentTime, StartTime;      //vars to save current time and time of the beginnig of the Sketch
String  cTime, mTime;                       //String to save the time which is printed on lcd
byte  TimeCounter = 0;                      //byte for pretty looking of the time on the lcd line




void setup() {
  Serial.begin(9600);

  lcd.init();
  lcd.setBacklight(true);
  lcd.setCursor(0, 0);
  lcd.print("CollatzConj. v1.4");
  lcd.setCursor(0, 1);                       //doing some starting up stuff to see on the arduino/lcd what is happening
  lcd.print("    Starting up");
  lcd.setCursor(0, 3);
  lcd.print("     Made by JayPi4c");

  pinMode(LED, OUTPUT);                      //setting the pinMode of LED to output

  delay(2500);
  lcd.clear();                               //clear the display to start up with the actually code
  lcd.setCursor(0, 1);
  lcd.print("Most Steps @ Number:");
  StartTime = millis();                      //set StartTime to current systemtime (millis() starts at the beginng of the sketch but we are doing some stuff before, so we need to get this after the delay)
  mTime = "0";                               // set the time when the current highscore was reached to 0
}

void loop() {
  while (ListCounter < MAXIMUM) {
    i = xStart;
    Steps = 0;
    ClearLCDLine(0);
    lcd.print(xStart);

    digitalWrite(LED, HIGH);
    while (i > 1) {
      i = calculate(i);
      Steps++;
      lcd.setCursor(20 - getDigits(Steps), 0);
      lcd.print(Steps);

      CurrentTime = millis();
      cTime = Time(StartTime, CurrentTime);
      if (TimeCounter != cTime.length()) {
        ClearLCDLine(3);
        TimeCounter = cTime.length();
        lcd.setCursor(0, 3);
        lcd.print(toString(ListCounter) + "/" + MAXIMUM);
      }
      lcd.setCursor(20 - cTime.length(), 3);                       //print Time as much as possible in the down right corner
      lcd.print(cTime);
    }
    digitalWrite(LED, LOW);

    if (maxSteps < Steps) {
      maxSteps = Steps;
      //mTime = cTime;
      List[ListCounter][0] = xStart;
      List[ListCounter][1] = Steps;
      lcd.setCursor(0, 3);
      lcd.print(toString(ListCounter) + "/" + MAXIMUM);
      ClearLCDLine(2);
      lcd.print(List[ListCounter][0]);
      lcd.setCursor(20 - getDigits(List[ListCounter][1]), 2);
      lcd.print(List[ListCounter][1]);
      ListCounter++;
    }
    xStart++;
  }
  cTime = Time(StartTime, CurrentTime);


  for (int i = 3; i < MAXIMUM; i++) {
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print(List[i - 3][0]);
    lcd.setCursor(20 - getDigits(List[i - 3][1]), 0);
    lcd.print(List[i - 3][1]);

    lcd.setCursor(0, 1);
    lcd.print(List[i - 2][0]);
    lcd.setCursor(20 - getDigits(List[i - 2][1]), 1);
    lcd.print(List[i - 2][1]);

    lcd.setCursor(0, 2);
    lcd.print(List[i - 1][0]);
    lcd.setCursor(20 - getDigits(List[i - 1][1]), 2);
    lcd.print(List[i - 1][1]);

    lcd.setCursor(0, 3);
    lcd.print(List[i][0]);
    lcd.setCursor(20 - getDigits(List[i][1]), 3);
    lcd.print(List[i][1]);

    delay(1000);
  }

  lcd.clear();
  lcd.setCursor(0, 1);
  lcd.print("runtime:");
  lcd.setCursor(0, 2);
  lcd.print(cTime);
  delay(1000);
}





void ClearLCDLine(int Line) {
  lcd.setCursor(0, Line);
  lcd.print("                    ");
  lcd.setCursor(0, Line);
}
