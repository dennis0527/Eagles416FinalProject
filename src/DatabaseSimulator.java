import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.Iterator;
import java.util.Set;

public class DatabaseSimulator {

    public static String getStateJson() {
        JSONParser parser = new JSONParser();

        try {
            Reader reader = new FileReader("/Users/lacey/IdeaProjects/Eagles416FinalProject2/src/us-states.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String result = "";

//            Set key = jsonObject.keySet();
//
//            JSONArray array = (JSONArray) jsonObject.get(key.iterator().next());
//            for(int i = 0; i < 3; i++) {            //searches for state by alphabetical id and returns associated JSON
//                JSONObject obj = (JSONObject)array.get(i);
//                if (Integer.parseInt(obj.get("id").toString()) == stateIndex) {
//                    result = obj.toString();
//                }
//            }
            result = jsonObject.toString();

            return result;
        }catch(Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
            return e.toString();
        }
    }

    public static String getPrecinctJson(String stateName) {
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("/Users/lacey/IdeaProjects/Eagles416FinalProject2/src/MD_data_raw.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String result = "";

            result = jsonObject.toString();

            return result;
        }

        catch (Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
            return e.toString();
        }

    }

}
