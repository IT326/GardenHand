import com.loopj.android.http.*;

public class PlantApi {
   private static final String PLANTBASEAPI = "trefle.io/api/v1/plants";
   private static final String PLANTAPITOKEN = "A5x32nhndnuaaNfm5-avmytk5xEn_1X0o72WhP11Cq4";
   private static AsyncHttpClient client = new AsyncHttpClient();

   public PlantApi(String plantSearch){
       //set up request String to get from the api
       //method to find plant
        //get id of plant
       //method to find growth variables
       //other info needed for plant object?
   }

   public static void get(String url,RequestParams params, AsyncHttpResponseHandler responseHandler) {
      params.put("token",PLANTAPITOKEN);
      client.get(getAbsoluteUrl(url), params, responseHandler);
   }

   public static void post(String url,RequestParams params, AsyncHttpResponseHandler responseHandler) {
      params.put("token",PLANTAPITOKEN);
      client.post(getAbsoluteUrl(url), params, responseHandler);
   }

   private static String getAbsoluteUrl(String relativeUrl) {
      return PLANTBASEAPI + relativeUrl;
   }


}
