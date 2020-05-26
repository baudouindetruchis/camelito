package websocket;
import java.io.IOException;  
import java.util.concurrent.CopyOnWriteArraySet;  
     
import javax.websocket.OnClose;  
import javax.websocket.OnError;  
import javax.websocket.OnMessage;  
import javax.websocket.OnOpen;  
import javax.websocket.Session;  
import javax.websocket.server.ServerEndpoint;  
     

@ServerEndpoint("/websocket")  
public class MyWebSocket {  
  // Static variable, used to record the current number of online connections.  
  private static int onlineCount = 0;  
     
  // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识  
  private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();  
     
  // Connection session with a client
  private Session session;  
  @OnOpen  
  public void onOpen(Session session) {  
    this.session = session;  
    webSocketSet.add(this); // 加入set中  
    addOnlineCount(); // online users + 1  
    System.out.println("New connections have been added! The current number of people online is" + getOnlineCount());  
  }  
     
  @OnClose  
  public void onClose() {  
    webSocketSet.remove(this); // 从set中删除  
    subOnlineCount(); // online users -1  
    System.out.println("There is a connection closed! The current number of people online is" + getOnlineCount());  
  }  
     
  @OnMessage  
  public void onMessage(String message, Session session) {  
    System.out.println("Message from the client:" + message);  
     
    // 群发消息  
    for (MyWebSocket item : webSocketSet) {  
      try {  
        item.sendMessage(message);  
      } catch (IOException e) {  
        e.printStackTrace();  
        continue;  
      }  
    }  
  }  
  @OnError  
  public void onError(Session session, Throwable error) {  
    System.out.println("An error occurred");  
    error.printStackTrace();  
  }  
  public void sendMessage(String message) throws IOException {  
    this.session.getBasicRemote().sendText(message);  
    // this.session.getAsyncRemote().sendText(message);  
  }        
  public static synchronized int getOnlineCount() {  
    return onlineCount;  
  }         
  public static synchronized void addOnlineCount() {  
    MyWebSocket.onlineCount++;  
  }      
  public static synchronized void subOnlineCount() {  
    MyWebSocket.onlineCount--;  
  }  
}  