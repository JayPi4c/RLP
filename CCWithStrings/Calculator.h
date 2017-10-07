#include "Arduino.h"
#include "Vars.h"
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

extern String calculate(String i);

extern int getDigits(int value);

extern String Time(unsigned long startMillis, unsigned long currentMillis);

extern String convertNumber(int n);

extern void clearLCDLine(LiquidCrystal_I2C lcd, int Line);

extern String stringDivision(String number, int divisor);

extern String stringAddition(String a, String b);

extern String stringMultiplication(String number, int factor);

extern String updateNumber(String num);
