package com.eagles.ElectionDataQuality.PersistenceLayer;
import com.eagles.ElectionDataQuality.Entity.State;
import org.json.simple.parser.JSONParser;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;

public class PersistenceLayer {

    public static String getStatesJson(){
        EntityManager em = getEntityManagerInstance();
        State state = em.find(State.class, "state_MD");
        return state.getGeojson();
    }

    public static String getNeighbors(String stateName, String precinctName) {

        EntityManager em = getEntityManagerInstance();
        if(stateName.equalsIgnoreCase("Maryland")){
            State state = em.find(State.class, "state_MD");
            ArrayList<String> neighbors = new ArrayList<>(Arrays.asList(state.getNeighbors().split("\n")));

            for(String s : neighbors){
                String precinct = s.substring(0, s.indexOf(":"));
                if(precinct.equalsIgnoreCase(precinctName)){
                    return s.replaceAll("\'", "\"").trim().substring(s.indexOf(":") + 1);
                }
            }

            return null;
        }

        return "";

    }

    public static String getAnomalousErrors(String stateName){
        EntityManager em = getEntityManagerInstance();

        if(stateName.equalsIgnoreCase("Maryland")){
            State state = em.find(State.class, "state_MD");
            return state.getAnomalousErrors();
        }

        return "";
    }

    public static String getEnclosedPrecinctErrors(String stateName){
        EntityManager em = getEntityManagerInstance();

        if(stateName.equalsIgnoreCase("Maryland")){
            State s = em.find(State.class, "state_MD");
            return s.getEnclosedErrors();
        }

        return "";
    }


    public static EntityManager getEntityManagerInstance(){
        try{
            ServletContext context = PersistenceContextListener.getApplicationContext();
            EntityManager em =  (EntityManager) context.getAttribute("em");
            return em;
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
