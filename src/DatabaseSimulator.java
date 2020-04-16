import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.Iterator;
import java.util.Set;

public class DatabaseSimulator {

    static String stateName = "test";

    public DatabaseSimulator() {
        JSONParser parser = new JSONParser();

        try {
            Reader reader = new FileReader("/Users/lacey/IdeaProjects/Eagles416FinalProject2/src/us-states.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            Set key = jsonObject.keySet();

            JSONArray array = (JSONArray) jsonObject.get(key.iterator().next());

            System.out.println("The 1st element of array");
            System.out.println(array.get(0));
            System.out.println();

            JSONObject obj = (JSONObject)array.get(0);
            JSONObject properties = (JSONObject) obj.get("properties");
            System.out.println(properties.get("name"));


            stateName = properties.get("name").toString();
        }catch(Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
        }
    }

}
