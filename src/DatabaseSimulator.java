import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.Iterator;
import java.util.Set;

public class DatabaseSimulator {

    public static String getStateJson() {
        JSONParser parser = new JSONParser();

        try {
            Reader reader = new FileReader("/Users/sudippaul/CSE416/FinalProject/src/us-states.json");
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
            Reader reader = new FileReader("/Users/sudippaul/CSE416/FinalProject/src/MD_data_raw.json");
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

    public static String getCongressionalDistricts(String stateName){
        JSONParser parser = new JSONParser();

        try{
            Reader reader = new FileReader("/Users/sudippaul/CSE416/FinalProject/src/congressional_json.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            return jsonObject.toString();


        }catch(Exception e){
            System.out.println("\n\n\n\n\n" + e.toString());
            return e.toString();
        }

    }

    public static String getNeighbors(String stateName, String precinctName){

        JSONParser parser = new JSONParser();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("/Users/sudippaul/CSE416/FinalProject/src/MD_neighbors.txt"));
            String line = reader.readLine();

            while(line != null){

                String precinct = line.substring(0, line.indexOf(":"));
                if(precinct.equalsIgnoreCase(precinctName)){

                    return line.replaceAll("\'", "\"").trim().substring(line.indexOf(":") + 1);

                }

                //read next line
                line = reader.readLine();

            }

            return null;

        }catch(Exception e){
            System.out.println("\n\n\n\n" + e.toString());
            return e.toString();
        }
    }

}
