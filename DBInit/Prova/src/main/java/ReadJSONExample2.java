import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ReadJSONExample2
{

    private static final Random r = new Random();
    public static void main(String[] args)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("campania_cities.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray cityList = (JSONArray) obj;

            FileReader reader2 = new FileReader("coordinate.json");
            Object obj2 = jsonParser.parse(reader2);

            JSONArray coords = (JSONArray) obj2;


            //Iterate over employee array
           cityList.forEach( city -> editCity((JSONObject) city, coords));
            System.out.println(cityList);

            cityList.forEach( city -> {

                JSONObject cityObj = (JSONObject) city;

                if(cityObj.get("latitudine").equals("") || cityObj.get("longitudine").equals("")){
                    cityObj.put("latitudine", -1);
                    cityObj.put("longitudine", -1);
                }
            });

            FileWriter file = null;
            try {
                file = new FileWriter("city_campania-parsed.json");
                file.write(cityList.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }




    private static void editCity(JSONObject city, JSONArray coords) {

        coords.forEach(coord -> {
            JSONObject coords2 = (JSONObject)  coord;
            if(city.get("comune").equals(coords2.get("comune"))) {
                System.out.println(coords2);
                city.put("latitudine", Double.parseDouble((String) coords2.get("lat")));
                city.put("longitudine", Double.parseDouble((String) coords2.get("lng")));
            }
        });

    }
}