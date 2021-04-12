package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import java.util.Date;

public class Utility {

    // function to get date and day from Unix EPOCH time
    public static String getTheDateAndDayFromEPOCHTime(int epochTime)
    {
        Date time=new Date((long)epochTime*1000);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        String formatedDate = formatter.format(time);
        return formatedDate;
    }

    // Function to get date on Thursday forecast
    public static String getDate(String response)
    {
        String date = "";
        JSONObject jsonObj = new JSONObject(response);
        JSONArray array = (JSONArray) jsonObj.get("daily");
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject newObj = (JSONObject) array.get(i);
            int epochDate = (int) newObj.get("dt");
            date = getTheDateAndDayFromEPOCHTime(epochDate);
            if(date.contains("Thu"))
            {
                return date;
            }
        }
        return date;
    }

    //Function to get minimum temperature for Thursday forecast
    public static double getMinimumTemperatureForThursday(String response)
    {
        double minTemp = -1.0;
        JSONObject jsonObj = new JSONObject(response);
        JSONArray array = (JSONArray) jsonObj.get("daily");
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject newObj = (JSONObject) array.get(i);
            int epochDate = (int) newObj.get("dt");
            String date = getTheDateAndDayFromEPOCHTime(epochDate);
            if(date.contains("Thu"))
            {
                JSONObject obj2 = (JSONObject) newObj.get("temp");
                BigDecimal Temp = (BigDecimal) obj2.get("min");
                minTemp = Temp.doubleValue();
                return minTemp;
            }
        }
        return minTemp;
    }

}
