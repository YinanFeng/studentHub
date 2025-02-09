package com.studentHub.matchCentre.common;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class Data {
    public static Map<String, String> stuMentorGroup = new HashMap<>();
    public static Map<String, String> mentorStuGroup = new HashMap<>();
    //type,[mentorId]
    public static Map<String, ArrayList<String>> avaMentor = new HashMap<>();

    public static void isNewStu(){

    }

    public static String findMentor(String stuId){
        System.out.println("mentorId matched" + stuMentorGroup.get(stuId));
            return stuMentorGroup.get(stuId);

    }

    public static String matchAvailableMentor(String stuId, String type){
        ArrayList<String> availableMentor = avaMentor.get(type);
        if(availableMentor == null || availableMentor.size() == 0){
            return null;
        }
        String mentorMatched = availableMentor.get(0);

        System.out.println("mentor matched" + mentorMatched);
        System.out.println("length" + availableMentor.size());

        avaMentor.get(type).remove(mentorMatched);
        stuMentorGroup.put(stuId,mentorMatched);
        mentorStuGroup.put(mentorMatched,stuId);
        return mentorMatched;
    }

    public static void addNewAvailbleMentor(String mentorId, String type){
        if(avaMentor.get(type) != null){
            avaMentor.get(type).add(mentorId);
        }else{
            avaMentor.put(type,new ArrayList<String>());
            avaMentor.get(type).add(mentorId);
        }
    }

    public static String findStudent(String mentorId){
        return mentorStuGroup.get(mentorId);
    }

    public static String studentLeave(String stuId){
        String mentorId = stuMentorGroup.get(stuId);
        if(mentorId != null){
            stuMentorGroup.remove(stuId);
            mentorStuGroup.remove(mentorId);
            return mentorId;
        }
        return null;
    }

    public static String mentorLeave(String mentorId){
        String stuId = mentorStuGroup.get(mentorId);
        if(stuId != null) {
            stuMentorGroup.remove(stuId);
            mentorStuGroup.remove(mentorId);
            return stuId;
        }else{
//
//            //unmatched mentor leave!
//            avaMentor.get(type).remove(mentorId);

            //do not need to inform student, so no return.
            Set<String> allKeys = avaMentor.keySet();
            for(String key : allKeys){
                if(avaMentor.get(key).contains(mentorId)){
                    avaMentor.get(key).remove(mentorId);
                }
            }
        }
        return null;
    }

    public static JSONObject currentMentor(){
        JSONObject jo = new JSONObject();

        for(String s : avaMentor.keySet()){
            jo.put(s, avaMentor.get(s).size());
        }

        System.out.println("here    "+jo);
        return jo;
    }

}
