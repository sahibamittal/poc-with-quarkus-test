package org.acme;

import java.util.ArrayList;

public class EventData {
    public static class LocalData {
        private ArrayList<String> components;
        private String project;

        public void setComponents(ArrayList<String> components){
            this.components = components;
        }
        public ArrayList<String> getComponents(){
            return this.components;
        }
        public void setProject(String project){
            this.project = project;
        }
        public String getProject(){
            return this.project;
        }
    }
    public int eventType;



}