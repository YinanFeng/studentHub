package com.studentHub.mentor.common;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChannelMapper {

    //mentorId - channel
    private static Map<String, Channel> map = new ConcurrentHashMap<String, Channel>();

    public static void add(String mentorId, Channel channel){
        Channel cn = map.get(mentorId);
        if(cn==null){
            map.put(mentorId,channel);
        }else{
            //one user can only log in one at same time
            //should not have this case.

            //click connect twice.
            map.remove(mentorId);
            map.put(mentorId,channel);
        }
        System.out.println(mentorId);
        System.out.println(channel.remoteAddress());
    }

    public static String findMentor(Channel channel){
        for (String entry : map.keySet()) {
            if (map.get(entry) == channel) {
                return entry;
            }
        }
        return null;
    }



    public static Channel get(String mentorId) {
        return map.get(mentorId);
    }

    //mentor exit him/her-self
    public static void remove(Channel channel){
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == channel) {
                map.remove(entry.getKey());
            }
        }
        System.out.println("remove"+ channel.remoteAddress());
    }

    //when student exit the cov, call this method
    public static void remove(String mentorId) {
        map.remove(mentorId);
        System.out.println(mentorId);
    }


}
