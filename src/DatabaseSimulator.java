import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.Iterator;

public class DatabaseSimulator {

    static String stateName = "test";

    public DatabaseSimulator() {
        JSONParser parser = new JSONParser();

        try {
            Reader reader = new FileReader("/Users/lacey/IdeaProjects/Eagles416FinalProject2/src/us-states.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            //JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream("us-states.json")));
            String name = jsonObject.toString();
            System.out.println(name);
            stateName = name;
        }catch(Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
        }
    }

}
