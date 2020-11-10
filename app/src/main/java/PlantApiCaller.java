import org.json.*;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.entity.mime.Header;

public class PlantApiCaller {
    public void searchPlant() throws JSONException{
        //input
        String input = "tomato";
        String relurl = "/search";
        RequestParams params = new RequestParams();
        params.put("q",input);

        PlantApi.get(relurl, params, new JsonHttpResponseHandler(){

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                System.out.println("got json object");
            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) throws JSONException {
                JSONObject resResult = (JSONObject) response.get(0);
                String plantName = resResult.getString("common_name");

                System.out.println(plantName);
            }

        });


    }
}
