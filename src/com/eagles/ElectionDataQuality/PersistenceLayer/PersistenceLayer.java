package com.eagles.ElectionDataQuality.PersistenceLayer;

import com.eagles.ElectionDataQuality.Entity.NationalPark;
import com.eagles.ElectionDataQuality.Entity.State;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PersistenceLayer {
    private static Properties props = new Properties();
    private static String propFileName = "config.properties";

    public static String getStatesJson() {
        try{
            InputStream is = PersistenceLayer.class.getClassLoader().getResourceAsStream(propFileName);
            props.load(is);
            EntityManager em = getEntityManagerInstance();
            Query query = em.createQuery("Select s from State s order by s.canonicalName asc");
            List<State> states = (List<State>)query.getResultList();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(props.getProperty("skeleton"));
            JSONArray features = (JSONArray) json.get("features");
            for(State s : states){
                features.add(parser.parse(s.getGeojson()));
            }
            return json.toJSONString();
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static String getNeighbors(String stateName, String precinctName) {
        EntityManager em = getEntityManagerInstance();
        Query query = em.createQuery("Select s from State s where s.fullName = " + "\"" + stateName + "\"");
        State state = (State)query.getSingleResult();
        ArrayList<String> neighbors = new ArrayList<>(Arrays.asList(state.getNeighbors().split("\n")));
        for (String s : neighbors) {
            String precinct = s.substring(0, s.indexOf(":"));
            if (precinct.equalsIgnoreCase(precinctName)) {
                return s.replaceAll("\'", "\"").trim().substring(s.indexOf(":") + 1);
            }
        }
        return "none";
    }

    public static String getAnomalousErrors(String stateName) {
        EntityManager em = getEntityManagerInstance();
        if (stateName.equalsIgnoreCase("Maryland")) {
            State state = em.find(State.class, "state_MD");
            return state.getAnomalousErrors();
        }
        return "";
    }

    public static String getEnclosedPrecinctErrors(String stateName) {
        EntityManager em = getEntityManagerInstance();
        if (stateName.equalsIgnoreCase("Maryland")) {
            State s = em.find(State.class, "state_MD");
            return s.getEnclosedErrors();
        }
        return "";
    }

    public static String getNationalParks() {
        try {
            InputStream is = PersistenceLayer.class.getClassLoader().getResourceAsStream(propFileName);
            props.load(is);
            EntityManager em = getEntityManagerInstance();
            Query query = em.createQuery("Select p from NationalPark p order by p.canonicalName asc");
            List<NationalPark> nationalParks = (List<NationalPark>)query.getResultList();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(props.getProperty("skeleton"));
            JSONArray features = (JSONArray) json.get("features");
            for(NationalPark park : nationalParks){
                features.add(parser.parse(park.getGeojson()));
            }
            return json.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPrecinctData(String stateName){

        try {
            InputStream is = PersistenceLayer.class.getClassLoader().getResourceAsStream(propFileName);
            props.load(is);
            EntityManager entityManager = getEntityManagerInstance();

            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static EntityManager getEntityManagerInstance() {
        try {
            ServletContext context = PersistenceContextListener.getApplicationContext();
            EntityManager em = (EntityManager) context.getAttribute("em");
            return em;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
