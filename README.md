# Student Hub


### What is Student Hub?
It is a platform for new university studnet to get information from mentors to prepare better for their university. They are able to choose the topic they are interested in and chat with available mentor who is familiar with the topic.

### Why we need Student Hub
During Oweek in university, the stall provided information about uni is always very busy which cause each student only have very few time to consult. 
Also, since many student and mentor live far away from uni which make them unable to attend Oweek or only attend for limited time.  
Furthermore, many stduents complained that Oweek is too late to get these information. 

***Student Hub*** can solve all of these problems. It provide remote match/chat platform for student and mentor which enable them talk at **anytime and anywhere**.

---

### Tech Stack
##### Backend
- Spring Boot
- Maven
- Spring Cloud
- Netty

##### Frontend
- React
- Webpack
- Storybook(UI management)

---

### About Project 
##### Backend
This is a spring-cloud project, and consists of 3 microservices and Eureka.

Start ***Eureka*** first, which works as a register-center.
Then start ***StduentChat, MentorChat, MatchCenter*** in any order. But you may need to wait for a few minutes enable the microservices connect and communicate with each other.

- Eureka: Used for register all microservice.
- StduentChat: This microservice provide chat service on student side. It use netty to connect with the frontend by WebSocket. Also, it applies feignClient to consume other service.
- MentorChat: It is similiar with studentChat but provide the service on mentor side
- MatchCenter: This microservice provide service to deal with logic.

##### Frontend
Frontend is a React project. It use storybook to do UI management and use webpack to bundle JavaScript files.
Also, the project support hot reload.

###### Scripts

```
cd <project-name>
cd frondend
npm install
npm start
```
Install all dependencies and start frontend application

```
npm run storybook
```
Show all UI components with storybook


