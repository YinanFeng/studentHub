package com.studentHub.messageResolve;

import com.studentHub.remoteController.MatchCenterController;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class MessageResolve implements ApplicationContextAware {

    public static MessageResolve self;
    public static MatchCenterController matchCenterController;

    private static ApplicationContext applicationContext = null;

    @PostConstruct
    public void init(){
        self = this;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MessageResolve.applicationContext = applicationContext;
    }

    @Resource
    public void setMessageDispatcher(MatchCenterController matchCenterController) {
        MessageResolve.matchCenterController = matchCenterController;
    }


    public static void stuJoin(String stuNum, String mentorType){
        System.out.println(matchCenterController);

        matchCenterController.studentNewCov(stuNum, mentorType);
    }

    public static void stuMessage(String stuNum, String message){
        matchCenterController.studentNewMessage(stuNum,message);
    }

    public static void stuLeave(String stuId){
        matchCenterController.studentLeaveCov(stuId);
    }


}
