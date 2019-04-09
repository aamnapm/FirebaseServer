package ir.aamnapm.firebasespringboot.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ir.aamnapm.firebasespringboot.Util.FirebaseUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ir.aamnapm.firebasespringboot.Module.Firebase;
import ir.aamnapm.firebasespringboot.Repository.FirebaseRepository;

@RestController
@RequestMapping("/firebase")
public class FirebaseController {

    @Autowired
    FirebaseRepository firebaseRepository;


    @RequestMapping(value = "/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addNew(@RequestBody String payload) {

        JsonObject response = new JsonObject();

        try {

            JSONObject jsonObject = new JSONObject(payload);

            System.out.println(jsonObject.toString());

            String title = jsonObject.getString("title");
            String message = jsonObject.getString("message");
            String notifyId = jsonObject.getString("notifyId");


            Firebase newFirebase = new Firebase();

            newFirebase.setTitle(title);
            newFirebase.setMessage(message);
            newFirebase.setNotifyId(notifyId);

            Firebase firebase = firebaseRepository.save(newFirebase);

            FirebaseUtil firebaseUtil1 = new FirebaseUtil();
            firebaseUtil1.sendDataNotification(notifyId, title, message);


            response.addProperty("status", true);
            response.addProperty("message", "پیام با موفقیت ارسال شد.");


        } catch (Exception e) {
            System.out.println(e.toString());
            response.addProperty("status", false);
            response.addProperty("message", "در ارسال پیام مشکلی به وجود آمده است.");

        }

        return new Gson().toJson(response);
    }
}
