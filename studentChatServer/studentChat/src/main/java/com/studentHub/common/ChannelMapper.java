package com.studentHub.common;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelMapper {


    //mentorId - channel
    private static Map<String, Channel> map = new ConcurrentHashMap<String, Channel>();

    public static void add(String stuId, Channel channel){
        Channel cn = map.get(stuId);
        if(cn==null){
            map.put(stuId,channel);
        }else{
            //one user can only log in one at same time
            //should not have this case.

            //click connect twice.
            map.remove(stuId);
            map.put(stuId,channel);
        }
        System.out.println(stuId);
        System.out.println(channel.remoteAddress());
    }

    public static String findStu(Channel channel){
        for (String entry : map.keySet()) {
            if (map.get(entry) == channel) {
                return entry;
            }
        }
        return null;
    }



    public static Channel get(String stuId) {
        return map.get(stuId);
    }

    //stu exit him/her-self
    public static void remove(Channel channel){
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == channel) {
                map.remove(entry.getKey());
            }
        }
        System.out.println("remove"+ channel.remoteAddress());
    }

    //when student exit the cov, call this method
    public static void remove(String stuId) {
        map.remove(stuId);
        System.out.println(stuId);
    }










}
