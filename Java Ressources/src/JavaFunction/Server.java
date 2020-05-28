import java.io.IOException;  
import java.util.concurrent.CopyOnWriteArraySet;  
     
import javax.websocket.OnClose;  
import javax.websocket.OnError;  
import javax.websocket.OnMessage;  
import javax.websocket.OnOpen;  
import javax.websocket.Session;  
import javax.websocket.server.ServerEndpoint;  
     
/*Cette annotation est utilisée pour spécifier un URI via lequel le client peut se connecter à WebSocket.
Pas besoin de configurer dans web.xml. */
@ServerEndpoint("/websocket")  
public class Server {
 
  private static int onlineCount = 0;  
      
  private static CopyOnWriteArraySet<Server> webSocketSet = new CopyOnWriteArraySet<Server>();  
     
  // Session de connexion avec un client, besoin d'envoyer des données au client par son intermédiaire 
  private Session session;  
  
  @OnOpen  
  public void onOpen(Session session) {  
    this.session = session;  
    webSocketSet.add(this); 
  }  
     
  /** 
   * la fonction pour fermer la connection 
   */  
  @OnClose  
  public void onClose() {  
    webSocketSet.remove(this); // remove form set  
    subOnlineCount(); // online user number-1
  }  
     
  @OnMessage  
  public void onMessage(String message, Session session) {    
    // send message 
    for (Server item : webSocketSet) {  
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
    System.out.println("error");  
    error.printStackTrace();  
    
  }  
  
  public void sendMessage(String message) throws IOException {  
    this.session.getBasicRemote().sendText(message);  
  
  }        
  public static synchronized int getOnlineCount() {  
    return onlineCount;  
  }         
  public static synchronized void addOnlineCount() {  
    Server.onlineCount++;  
  }      
  public static synchronized void subOnlineCount() {  
    Server.onlineCount--;  
  }  
}
