import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONExample
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("biblioteche.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray bibliotecheList = (JSONArray) obj;


            //Iterate over employee array
           bibliotecheList.forEach( bib -> editBib((JSONObject) bib));
            System.out.println(bibliotecheList);

            FileWriter file = null;
            try {
                file = new FileWriter("biblioteche-parsed.json");
                file.write(bibliotecheList.toJSONString());
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




    private static void editBib(JSONObject bib) {
        String longit = (String) bib.get("Longitudine");
        String lat = (String) bib.get("Latitudine");

        JSONArray coords = new JSONArray();
        coords.add(Double.parseDouble(longit.replace(',', '.')));
        coords.add(Double.parseDouble(lat.replace(',', '.')));



        JSONObject location = new JSONObject();
        location.put("type", "Point");
        location.put("coordinates", coords);

        bib.put("location", location);
        bib.remove("Latitudine");
        bib.remove("Longitudine");

        if(((String) bib.get("Telefono")).equals("")){
            bib.remove("Telefono");
            bib.put("Telefono", " Non Disponibile");

        }

        if(((String) bib.get("Fax")).equals("")){
            bib.remove("Fax");
            bib.put("Fax", " Non Disponibile");
        }

        if(((String) bib.get("Email")).equals("")){
            bib.remove("Email");
            bib.put("Email", " Non Disponibile");

        }
        if(((String) bib.get("Url")).equals("")){
            bib.remove("Url");
            bib.put("Url", " Non Disponibile");

        }

        bib.remove("Fid");
        bib.remove("CodiceIsil");
        bib.remove("Frazione");

    }
}