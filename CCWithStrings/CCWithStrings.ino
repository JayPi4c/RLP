#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include "Vars.h"
#include "Calculator.h"

LiquidCrystal_I2C lcd(0x27 , 20 , 4);

unsigned long Steps = 0, maxSteps = 0;
String xStart = "1", xMax;
String i;
unsigned long StartTime, CurrentTime;
String currentTime, maxTime;
byte TimeCounter;

void setup() {
  Serial.begin(9600);

  lcd.init();
  lcd.setBacklight(true);
  lcd.setCursor(0, 0);
  lcd.print("CollatzConj. v1.4");
  lcd.setCursor(0, 1);
  lcd.print("    Starting up");
  lcd.setCursor(0, 3);
  lcd.print("     Made by JayPi4c");

  pinMode(LED, OUTPUT);

  delay(2500);
  lcd.clear();
  lcd.setCursor(0, 1);
  lcd.print("Most Steps @ Number:");
  StartTime = millis();
  maxTime = "0";
}

void loop() {
  i = xStart;
  Steps = 0;
  clearLCDLine(lcd, 0);
  lcd.print(xStart);

  digitalWrite(LED, HIGH);                                       //turn LED on while Calculating

  while (i.length()  > 1 || i.charAt(i.length() - 1) - '0' > 1) {
    i = calculate(i);                     //DO THE COLLATZCONJECTURE Calculation
    i = updateNumber(i);

    Steps++;
    lcd.setCursor(20 - getDigits(Steps), 0);
    lcd.print(Steps);

    CurrentTime = millis();
    currentTime = Time(StartTime, CurrentTime);
    if (TimeCounter < currentTime.length() || TimeCounter > currentTime.length()) {  // pretty looking on display; check the length of the time var and clear collom if needed
      clearLCDLine(lcd, 3);
      TimeCounter = currentTime.length();
      lcd.setCursor(0, 3);
      lcd.print(maxTime);
    }
    lcd.setCursor(20 - currentTime.length(), 3);                       //print Time as much as possible in the down right corner
    lcd.print(currentTime);
  }
  digitalWrite(LED, LOW);                                        //Turn LED off after calculating


  if (maxSteps < Steps) {                                        // Check the result of the steps with the older maxSteps, if result >, then set new highest level
    maxSteps = Steps;
    xMax = xStart;
    maxTime = currentTime;
    lcd.setCursor(0, 3);
    lcd.print(maxTime);
    clearLCDLine(lcd, 2);
    lcd.print(xMax);
    lcd.setCursor(20 - getDigits(maxSteps), 2);
    lcd.print(maxSteps);
  }
  xStart = stringAddition(xStart, "1");                                                      //create a new number to check by adding 1 to the older startnumber
}
