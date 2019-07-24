package com.studentHub.mentor.messageResolve;

import com.studentHub.mentor.common.ChannelMapper;
import com.studentHub.mentor.remoteController.MatchCenterController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class MessageResolve implements ApplicationContextAware {

    //    public static TextWebSocketFrameHandler textWebSocketFrameHandler;
//
//    @PostConstruct
//    public void init(){
//        textWebSocketFrameHandler=this;
//        textWebSocketFrameHandler.matchCenterController=this.matchCenterController;
//    }

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

    public static void mentorJoin(String mentorId, String type){
        System.out.println(matchCenterController);

        matchCenterController.mentorJoinCov(mentorId,type);
    }

    public static void mentorMessage(String mentorId, String message){
        matchCenterController.mentorNewMessage(mentorId,message);
    }

    public static void mentorLeave(String mentorId){
        matchCenterController.mentorLeaveCov(mentorId);
    }


}
