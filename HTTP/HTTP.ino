/*
 WiFi Web Server LED Blink

 A simple web server that lets you blink an LED via the web.
 This sketch will print the IP address of your WiFi Shield (once connected)
 to the Serial monitor. From there, you can open that address in a web browser
 to turn on and off the LED on pin 5.

 If the IP address of your shield is yourAddress:
 http://yourAddress/H turns the LED on
 http://yourAddress/L turns it off

 This example is written for a network using WPA encryption. For
 WEP or WPA, change the Wifi.begin() call accordingly.

 Circuit:
 * WiFi shield attached
 * LED attached to pin 5

 created for arduino 25 Nov 2012
 by Tom Igoe

ported for sparkfun esp32 
31.01.2017 by Jan Hendrik Berlin
 
 */

#include <WiFi.h>
//#include <SoftwareSerial.h>
//SoftwareSerial HoverSerial(2,3);        // RX, TX
const char* ssid     = "BanGO";
const char* password = "88888888";

WiFiServer server(80);

unsigned char play[4]={0xAA,0x02,0x00,0xAC};
unsigned char Pauses[4]={0xAA, 0x03, 0x00,0xAD};
unsigned char stops[4]={0xAA, 0x04, 0x00, 0xAE};
unsigned char last[4]={0xAA, 0x05, 0x00, 0xAF}; 
unsigned char next[4]={0xAA, 0x06, 0x00, 0xB0};
unsigned char volue[5]={0xAA, 0x13, 0x01, 0x10, 0xCE};

unsigned char select_play[6]={0xAA, 0x07, 0x02, 0x00, 0x08, 0xBB}; 


unsigned char volue_set[32] = {
  0x00,0x01,0x02,0x03,0x04,0x05,0x06,0x07,
  0x08,0x09,0x0A,0x0B,0x0C,0x0D,0x0E,0x0F,
  0x10,0x11,0x12,0x13,0x14,0x15,0x16,0x17,
  0x18,0x19,0x1A,0x1B,0x1C,0x1D,0x1E,0x1F,
  
};
unsigned char SM[32] = {
  0xBE,0xBF,0xC0,0xC1,0xC2,0xC3,0xC4,0xC5,
  0xC6,0xC7,0xC8,0xC9,0xCA,0xCB,0xCC,0xCD,
  0xCE,0xCF,0xD0,0xD1,0xD2,0xD3,0xD4,0xD5,
  0xD6,0xD7,0xD8,0xD9,0xDA,0xDB,0xDC,0xDD

};
unsigned char play_set[48] = {
  0x00,0x01,0x02,0x03,0x04,0x05,0x06,0x07,
  0x08,0x09,0x0A,0x0B,0x0C,0x0D,0x0E,0x0F,
  0x10,0x11,0x12,0x13,0x14,0x15,0x16,0x17,
  0x18,0x19,0x1A,0x1B,0x1C,0x1D,0x1E,0x1F,
  0x20,0x21,0x22,0x23,0x24,0x25,0x26,0x27,
  0x28,0x29,0x2A,0x2B,0x2C,0x2D,0x2E,0x2F
};

unsigned char play_SM[48] = {
  0xB3,0xB4,0xB5,0xB6,0xB7,0xB8,0xB9,0xBA,
  0xBB,0xBC,0xBD,0xBE,0xBF,0xC0,0xC0,0xC1,
  0xC2,0xC3,0xC4,0xC5,0xC6,0xC7,0xC8,0xC9,
  0xCA,0xCB,0xCC,0xCD,0xCE,0xCF,0xD0,0xD1,
  0xD2,0xD3,0xD4,0xD5,0xD6,0xD7,0xD8,0xD9,
  0xDA,0xDB,0xDC,0xDD,0xDE,0xDF,0xe0,0xE1
};

typedef struct{
  
   int16_t  pwml;
   int16_t  pwmr;

} SerialCommand;
SerialCommand Command;

bool flag = false;

int i,j,k;

void Send(int16_t uSteer, int16_t uSpeed)
{
  // Create command
 
  Command.pwml    = (int16_t)uSteer;
  Command.pwmr    = (int16_t)uSpeed;
 
  // Write to Serial
  Serial.write((uint8_t *) &Command, sizeof(Command)); 
}
void setup()
{
    Serial.begin(115200);
    pinMode(2, OUTPUT);      // set the LED pin mode

    delay(10);

    // We start by connecting to a WiFi network

    Serial.println();
    Serial.println();
    Serial.print("Connecting to ");
    Serial.println(ssid);

    WiFi.begin(ssid, password);

    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }

    Serial.println("");
    Serial.println("WiFi connected.");
    Serial.println("IP address: ");
    Serial.println(WiFi.localIP());
    Serial2.begin(9600);
  
  Serial.println("Hoverboard Serial v1.0");
  pinMode(4,OUTPUT);
  pinMode(25,OUTPUT);
  pinMode(26,OUTPUT);
  pinMode(0,INPUT);
  Send(0, 0);
  dacWrite(25,123);
  dacWrite(26,123);
//  select_play[4] = play_set[1]; 
//    
//  select_play[5] = play_SM[1];
//      
  Serial2.write(select_play,6);
  Serial2.write(play,4);
    server.begin();

}

int value = 0;

void loop(){
 WiFiClient client = server.available();   // listen for incoming clients

  if (client) {                             // if you get a client,
    //Serial.println("New Client.");           // print a message out the serial port
    String currentLine = "";                // make a String to hold incoming data from the client
    while (client.connected()) {            // loop while the client's connected
      if (client.available()) {             // if there's bytes to read from the client,
        char c = client.read();             // read a byte, then
       // Serial.write(c);                    // print it out the serial monitor
        if (c == '\n') {                    // if the byte is a newline character

          // if the current line is blank, you got two newline characters in a row.
          // that's the end of the client HTTP request, so send a response:
          if (currentLine.length() == 0) {
            // HTTP headers always start with a response code (e.g. HTTP/1.1 200 OK)
            // and a content-type so the client knows what's coming, then a blank line:
            client.println("HTTP/1.1 200 OK");
            client.println("Content-type:text/html");
            client.println();

            // the content of the HTTP response follows the header:
            client.print("Click <a href=\"/H\">here</a> to turn the LED on pin 5 on.<br>");
            client.print("Click <a href=\"/L\">here</a> to turn the LED on pin 5 off.<br>");

            // The HTTP response ends with another blank line:
            client.println();
            // break out of the while loop:
            break;
          } else {    // if you got a newline, then clear currentLine:
            currentLine = "";
          }
        } else if (c != '\r') {  // if you got anything else but a carriage return character,
          currentLine += c;      // add it to the end of the currentLine
        }

       
        if (currentLine.endsWith("GET /go")) {
          digitalWrite(2, HIGH);
          dacWrite(26,200);    
     
        }
        if (currentLine.endsWith("GET /stop")) {
          digitalWrite(2, LOW);   
           dacWrite(25,123);
           dacWrite(26,123); 
        }           
        if (currentLine.endsWith("GET /back")) {
          digitalWrite(2, HIGH);
          dacWrite(26,55); 
          select_play[4] = play_set[3]; 
    
          select_play[5] = play_SM[3];
      
          Serial2.write(select_play,6); 
                  
        }
        if (currentLine.endsWith("GET /left")) {
          digitalWrite(2, HIGH);   
          dacWrite(25,55);
                   
        }
        if (currentLine.endsWith("GET /right")) {
          digitalWrite(2, HIGH); 
           dacWrite(25,200);
 
        }
         if (currentLine.endsWith("GET /light")) {
          digitalWrite(2, HIGH); 
           dacWrite(25,200);
          
          select_play[4] = play_set[2]; 
    
          select_play[5] = play_SM[2];
      
          Serial2.write(select_play,6); 
           
        }
   
      }
    }
    // close the connection:
    client.stop();
    //Serial.println("Client Disconnected.");
  }
      if(digitalRead(0)==0){
    delay(10);
    if(digitalRead(0)==0){
      dacWrite(25,123);
      dacWrite(25,123);
      delay(1000);
      dacWrite(25,0);
      delay(1000);
      dacWrite(25,123);
      delay(1000);
      dacWrite(25,255);
      delay(1000);
      dacWrite(25,123);
      delay(1000);
      dacWrite(26,0);
      delay(1000);
      dacWrite(26,123);
      delay(1000);
      dacWrite(26,255);
      delay(1000);
      dacWrite(26,123);
      Send(1, 0);
      
    }
  }
      
    
}
