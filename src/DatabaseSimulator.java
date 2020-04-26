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
            result = jsonObject.toString();

            return result;
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
            return e.toString();
        }
    }

    public static String getPrecinctJson(String stateName) {
        String fileName = "";
        if (stateName.equals("Maryland")) {
            fileName = "/Users/sudippaul/CSE416/FinalProject/src/MD_data_raw.json";
        } else if (stateName.equals("Florida")) {
            fileName = "/Users/sudippaul/CSE416/FinalProject/src/FL_demographics.json";
        } else if (stateName.equals("FloridaElection")) {
            fileName = "/Users/sudippaul/CSE416/FinalProject/src/FL_precinct.json";
        } else if (stateName.equals("New York")) {
            fileName = "/Users/sudippaul/CSE416/FinalProject/src/NY_demographics.json";
        } else if(stateName.equals("New York Election")) {
            fileName = "/Users/sudippaul/CSE416/FinalProject/src/NY_precinct_lines.json";
        }


        try {
            JSONParser parser = new JSONParser();
            System.out.println(fileName);
            Reader reader = new FileReader(fileName);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String result = "";

            result = jsonObject.toString();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
            return e.toString();
        }

    }

    public static String getCongressionalDistricts(String stateName) {
        JSONParser parser = new JSONParser();

        try {
            Reader reader = new FileReader("/Users/sudippaul/CSE416/FinalProject/src/congressional_json.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            return jsonObject.toString();


        } catch (Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
            return e.toString();
        }

    }

    public static String getNeighbors(String stateName, String precinctName) {

        JSONParser parser = new JSONParser();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/sudippaul/CSE416/FinalProject/src/MD_neighbors.txt"));
            String line = reader.readLine();

            while (line != null) {

                String precinct = line.substring(0, line.indexOf(":"));
                if (precinct.equalsIgnoreCase(precinctName)) {

                    return line.replaceAll("\'", "\"").trim().substring(line.indexOf(":") + 1);

                }

                //read next line
                line = reader.readLine();

            }

            return null;

        } catch (Exception e) {
            System.out.println("\n\n\n\n" + e.toString());
            return e.toString();
        }
    }


    public static String getAnomalousErrors(String stateName) {
        JSONParser parser = new JSONParser();
        if (stateName.equals("Maryland")) {
            try {
                Reader reader = new FileReader("/Users/sudippaul/CSE416/FinalProject/src/MD_anomalous.json");
                JSONObject jsonObject = (JSONObject) parser.parse(reader);

                return jsonObject.toString();


            } catch (Exception e) {
                System.out.println("\n\n\n\n\n" + e.toString());
                return e.toString();
            }
        } else {
            return "";
        }
    }

    public static String getNationalParks(){

    JSONParser parser = new JSONParser();

    try {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/sudippaul/CSE416/FinalProject/src/FL_national_park.json"));
        JSONObject object = (JSONObject) parser.parse(reader);
        return object.toString();


    } catch (Exception e) {
        e.printStackTrace();
        return e.toString();
    }

    }
}