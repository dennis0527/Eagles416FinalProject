package com.eagles.ElectionDataQuality.Handler;

import com.eagles.ElectionDataQuality.PersistenceLayer.PersistenceLayer;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.BufferedReader;
import java.io.*;
import java.util.Iterator;
import java.util.Set;

public class DatabaseSimulator {

    public static String getPrecinctJson(String stateName) {
        InputStream fileName = null;
        if (stateName.equals("Maryland")) {
            //fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/MD_data_raw.json";
            fileName = DatabaseSimulator.class.getClassLoader().getResourceAsStream("MD_data_raw.json");
        } else if (stateName.equals("Florida")) {
            //fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/FL_demographics.json";
            fileName = DatabaseSimulator.class.getClassLoader().getResourceAsStream("FL_demographics.json");
        } else if (stateName.equals("FloridaElection")) {
            //fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/FL_precinct.json";
            fileName = DatabaseSimulator.class.getClassLoader().getResourceAsStream("FL_precinct.json");
        } else if (stateName.equals("New York")) {
            //fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/NY_demographics.json";
            fileName = DatabaseSimulator.class.getClassLoader().getResourceAsStream("NY_demographics.json");
        } else if(stateName.equals("New York Election")) {
            //fileName = "/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/";
            fileName = DatabaseSimulator.class.getClassLoader().getResourceAsStream("NY_precinct_2.json");
        }


        try {
            JSONParser parser = new JSONParser();
            Reader reader = new InputStreamReader(fileName);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String result = "";
            result = jsonObject.toString();
            return result;
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
            return e.toString();
        }

    }

    public static String getCongressionalDistricts(String stateName) {
        JSONParser parser = new JSONParser();

        try {
            //Reader reader = new FileReader("/Users/dennisheerlein/IdeaProjects/election_data_quality_eagles/src/congressional_json.json");
            Reader reader = new InputStreamReader(DatabaseSimulator.class.getClassLoader().getResourceAsStream("congressional_json.json"));
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            return jsonObject.toString();


        } catch (Exception e) {
            System.out.println("\n\n\n\n\n" + e.toString());
            return e.toString();
        }

    }


    public static String getOverlappingPrecinctErrors(String stateName) {
        JSONParser parser = new JSONParser();
        try {
            System.out.println("get overlapping precinct errors");
            Reader reader = null;
            if (stateName.equals("Maryland")) {

            }
            else if (stateName.equals("Florida")) {
                reader = new FileReader("");
            }
            else if (stateName.equals("New York")) {
                reader = new FileReader("");
            }
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            return jsonObject.toString();
        } catch (Exception e) {
            System.out.println("\n\n\n\n" + e.toString());
            return e.toString();
        }
    }


}
