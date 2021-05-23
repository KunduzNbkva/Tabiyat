package kg.tabiyat.notification;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {


    public static void sendMessage(String topic, String title, String body, RequestQueue mRequestQue) {
        JSONObject json = new JSONObject();
        try {
            json.put("to", "/topics/" + topic);
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title", title);
            notificationObj.put("body", body);
            json.put("notification", notificationObj);

            String URL = "https://fcm.googleapis.com/fcm/send";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    response -> Log.d("---", "notification Response: " + response),
                    error -> Log.d("---", "notification Error: " + error.networkResponse)
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=AAAAb_Yx8Ks:APA91bEucwVb95YAG5Rp_JUyJ5G0NCT875Qc538tbNiQAANvKgnYRcVlu14ZLSpFr7bEHzKlegiTiDBpXn-Mg5NFlpT9uJKdVgUQkkbjIoIwwgDTZJL_CzXRuwOf5L9h2MdQPIvGKb7L");
                    return header;
                }
            };
            mRequestQue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
