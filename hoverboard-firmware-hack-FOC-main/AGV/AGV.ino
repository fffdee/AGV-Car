#include "WiFi.h"


const char * ssid = "BanGO";
const char * password = "88888888";

IPAddress local_IP(192, 168, 190, 200);
IPAddress gateway(192, 168, 190, 1);
IPAddress subnet(255, 255, 255, 0);



WiFiUDP Udp;//声明UDP对象
char* da="";

char incomingPacket[255];  //存储Udp客户端发过来的数据
  
unsigned char play[4]={0xAA,0x02,0x00,0xAC};
unsigned char Pauses[4]={0xAA, 0x03, 0x00,0xAD};
unsigned char stops[4]={0xAA, 0x04, 0x00, 0xAE};
unsigned char last[4]={0xAA, 0x05, 0x00, 0xAF}; 
unsigned char next[4]={0xAA, 0x06, 0x00, 0xB0};
unsigned char volue[5]={0xAA, 0x13, 0x01, 0x10, 0xCE};

unsigned char select_play[6]={0xAA, 0x07, 0x02, 0x00, 0x01, 0xB4}; 


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
    WiFi.config(local_IP, gateway, subnet);//设置静态IP
    WiFi.mode(WIFI_STA);
    WiFi.begin(ssid, password);
    if (WiFi.waitForConnectResult() != WL_CONNECTED) {
        Serial.println("WiFi Failed");
        while(1) {
            delay(1000);
        }
    }
  Serial2.begin(9600);
  
  Serial.println("Hoverboard Serial v1.0");
  pinMode(4,OUTPUT);
  pinMode(25,OUTPUT);
  pinMode(26,OUTPUT);
  pinMode(0,INPUT);
  Send(0, 0);
  select_play[4] = play_set[0]; 
    
  select_play[5] = play_SM[0];
      
  Serial2.write(select_play,6);
  Serial2.write(play,4);
   Udp.begin(1234);//启动UDP监听这个端口
   Udp.beginPacket("192,168,190,52", 1234);
   Udp.write(6);
   Udp.endPacket();
//    if(udp.connect(IPAddress(192,168,190,52), 1234)) {
//        Serial.println("UDP connected");
//        udp.onPacket([](AsyncUDPPacket packet) {
//            Serial.print("UDP Packet Type: ");
//            Serial.print(packet.isBroadcast()?"Broadcast":packet.isMulticast()?"Multicast":"Unicast");
//            Serial.print(", From: ");
//            Serial.print(packet.remoteIP());
//            Serial.print(":");
//            Serial.print(packet.remotePort());
//            Serial.print(", To: ");
//            Serial.print(packet.localIP());
//            Serial.print(":");
//            Serial.print(packet.localPort());
//            Serial.print(", Length: ");
//            Serial.print(packet.length());
//            Serial.print(", Data: ");
//            Serial.write(packet.data(), packet.length());
//            Serial.println();
//            da= (char*)(packet.data());
//            //reply to the client
//            
//            packet.printf("Got %u bytes of data", packet.length());
//            Serial.println(da);
//            if(da[0]=='R')
//            {
//                dacWrite(25,200);
//                 Serial.println("GO");
//               
//            }
//            if(da[0]=='B')
//            {
//                dacWrite(25,55);
//                 Serial.println("BACK");
//              
//            }
//            if(da[0]=='L')
//            {
//                dacWrite(26,200);
//                 Serial.println("left");
//                
//            }
//            if(da[0]=='G')
//            {
//                 Serial.println("right");
//                dacWrite(26,55);
//               
//            }
//            if(da[0]=='S')
//            {
//                dacWrite(25,123);
//                dacWrite(26,123);
//            
//            }
//
//            da ="";
//        });
//        //Send unicast
//        udp.print("Hello Server!");
//    }
//    udp.broadcastTo("h", 1234);
}

void loop(){
  int Data_length=Udp.parsePacket();  //获取接收的数据的长度
  if(Data_length)  //如果有数据那么Data_length不为0，无数据Data_length为0
  {
    int len = Udp.read(incomingPacket, 255);  //读取数据，将数据保存在数组incomingPacket中
    if (len > 0)  //为了避免获取的数据后面乱码做的判断
    {
      incomingPacket[len] = 0;
    }
    
    /*将接受到的数据发送回去*/
//    Udp.beginPacket(Udp.remoteIP(),Udp.remotePort());  //准备发送数据到目标IP和目标端口
//    Udp.print("receive data:");  //将数据receive data:放入发送的缓冲区
      Serial.println(incomingPacket);  //将接收到的数据放入发送的缓冲区
//    Udp.endPacket();  //向目标IP目标端口发送数据
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
      dacWrite(25,123);
      dacWrite(26,123);
      delay(1000);
}
