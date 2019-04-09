package ir.aamnapm.firebasespringboot.Util;

import com.google.gson.JsonObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.MalformedURLException;


public class FirebaseUtil {



  public void sendDataNotification(String notifyId, String title, String message) {
    try {


      HttpClient httpClient = HttpClientBuilder.create().build();
      HttpPost httpPost = new HttpPost("https://fcm.googleapis.com/fcm/send");

      httpPost.setHeader("Authorization", "key=AAAANt09JGs:APA91bF6GDxHQN5p1OlXM9IdglI1H40g3ostF2l2SZzPQzVWLylR3EkXgj-wYAMaJg4Lz0jVtlW7GWV9O_U4Qn12i1AoVJFRIrgC_IlzBpgmfJeQgzeQzN3pXO7ACC2kvx3XKe5AqlXw");
      httpPost.setHeader("Content-Type", "application/json");


      JsonObject jsonObject = new JsonObject();


      JsonObject objData = new JsonObject();
      objData.addProperty("title", title);
      objData.addProperty("message", message);

      jsonObject.add("data", objData);
      jsonObject.addProperty("to", notifyId);

      StringEntity stringEntity = new StringEntity(String.valueOf(jsonObject), ContentType.APPLICATION_JSON);

      httpPost.setEntity(stringEntity);

      HttpResponse response = httpClient.execute(httpPost);

      System.out.println(response.getStatusLine());

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }




  public void sendNotification(String notifyId, String title, String message) {

    try {

      HttpClient httpClient = HttpClientBuilder.create().build();

      HttpPost httpPost = new HttpPost("https://fcm.googleapis.com/fcm/send");


      httpPost.setHeader("Authorization", "key=AAAANt09JGs:APA91bF6GDxHQN5p1OlXM9IdglI1H40g3ostF2l2SZzPQzVWLylR3EkXgj-wYAMaJg4Lz0jVtlW7GWV9O_U4Qn12i1AoVJFRIrgC_IlzBpgmfJeQgzeQzN3pXO7ACC2kvx3XKe5AqlXw");
      httpPost.setHeader("Content-Type", "application/json");


      JsonObject jsonObject = new JsonObject();

      JsonObject objNotification = new JsonObject();
      objNotification.addProperty("title", title);
      objNotification.addProperty("body", message);
      objNotification.addProperty("priority", "high");
//            obj.addProperty("badge", "high");
//            obj.addProperty("color", "BLUE");
//            obj.addProperty("sound", "");
//            obj.addProperty("icon", "ic_launcher_foreground");

      jsonObject.add("notification", objNotification);
      jsonObject.addProperty("to", notifyId);


      StringEntity stringEntity = new StringEntity(String.valueOf(jsonObject), ContentType.APPLICATION_JSON);

      httpPost.setEntity(stringEntity);

      HttpResponse response = httpClient.execute(httpPost);

      System.out.println(response.getStatusLine());

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
