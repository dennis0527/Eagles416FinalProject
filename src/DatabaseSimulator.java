import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.Iterator;
import java.util.Set;

public class DatabaseSimulator {

    public static String getStateJson() {
        JSONParser parser = new JSONParser();

        try {
            Reader reader = new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/us-states.json");
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
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
            return e.toString();
        }
    }

    public static String getPrecinctJson(String stateName) {
        String fileName = "";
        if (stateName.equals("Maryland")) {
            fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/MD_data_raw.json";
        } else if (stateName.equals("Florida")) {
            fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/FL_demographics.json";
        } else if (stateName.equals("FloridaElection")) {
            fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/FL_precinct.json";
        } else if (stateName.equals("New York")) {
            fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/NY_demographics.json";
        } else if(stateName.equals("New York Election")) {
            fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/NY_precinct_2.json";
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
            Reader reader = new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/congressional_json.json");
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
            BufferedReader reader = null;
            if (stateName.equals("Maryland")) {
                reader = new BufferedReader(new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/MD_neighbors.txt"));
            }
            else if (stateName.equals("Florida")) {
                reader = new BufferedReader(new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/FL_neighbors.json"));
            }
            else if (stateName.equals("New York")) {
                reader = new BufferedReader(new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/NY_neighbors.json"));
            }
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
                Reader reader = new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/MD_anomalous.json");
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
        BufferedReader reader = new BufferedReader(new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/FL_national_park.json"));
        JSONObject object = (JSONObject) parser.parse(reader);
        return object.toString();


    } catch (Exception e) {
        e.printStackTrace();
        return e.toString();
    }

    }

    public static String getEnclosedPrecinctErrors(String stateName) {
        try {
            BufferedReader reader = null;
            if (stateName.equals("Maryland")) {
                reader = new BufferedReader(new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/MD_enclosed.json"));
            }
            else if (stateName.equals("Florida")) {
                reader = new BufferedReader(new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/FL_neighbors.json"));
            }
            else if (stateName.equals("New York")) {
                reader = new BufferedReader(new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/NY_neighbors.json"));
            }
            String line = reader.readLine();
            String errorsString = "";
            while (line != null) {
                String precinct = line.substring(0, line.indexOf(":"));
                errorsString +=  line.replaceAll("\'", "\"").trim().substring(line.indexOf(":") + 1);
                line = reader.readLine();
            }
            return errorsString;
        } catch (Exception e) {
            System.out.println("\n\n\n\n" + e.toString());
            return e.toString();
        }
    }
}