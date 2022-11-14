package com.cloud.analysis.repository;

import com.cloud.analysis.entity.Answer;
import com.cloud.analysis.entity.Question;
import com.cloud.analysis.entity.Survey;
import com.cloud.analysis.entity.User;
import com.cloud.analysis.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bouncycastle.pqc.crypto.newhope.NHSecretKeyProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@NoArgsConstructor
class AnswerRepositoryTest {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void read(){
        List<Answer> answerList = answerRepository.findAll();
        answerList.forEach(answer -> {
            Pattern pattern = Pattern.compile("([a-zA-Z]+)");
            Matcher matcher = pattern.matcher(answer.getContent());
            while(matcher.find()){
                System.out.println(matcher.group());
            }

            System.out.println("answer = " + answer);
        });
    }

    @Test
    public void insert(){
        Survey survey = surveyRepository.findById(1).orElse(null);
        Question question = questionRepository.findById(1).orElse(null);
        answerRepository.save(Answer.builder()
                .type(Type.Member)
                .user(userRepository.findById("yena").orElse(null))
                .regIp("0.0.0.0")
                .regDt(LocalDateTime.now())
                .modId("yena")
                .modDt(LocalDateTime.now())
                .survey(survey)
                .question(question)
                .content("[1]")
                .build());
        answerRepository.save(Answer.builder()
                .type(Type.Member)
                .user(userRepository.findById("yuri").orElse(null))
                .regIp("0.0.0.0")
                .regDt(LocalDateTime.now())
                .modId("yuri")
                .modDt(LocalDateTime.now())
                .survey(survey)
                .question(question)
                .content("[1]")
                .build());
        answerRepository.save(Answer.builder()
                .type(Type.Member)
                .user(userRepository.findById("youngjoo").orElse(null))
                .regIp("0.0.0.0")
                .regDt(LocalDateTime.now())
                .modId("youngjoo")
                .modDt(LocalDateTime.now())
                .survey(survey)
                .question(question)
                .content("[4]")
                .build());
    }

    @Test
    void readByUser() {
        List<User> userList = answerRepository.findAnswerBySurvey(1);
        System.out.println("userList = " + userList);
        userList.forEach(user -> {
            List<Answer> answerList = answerRepository.findAnswerByUser(user.getUserId());
            System.out.println("answerList = " + answerList);
        });


    }

//    @Test
//    void readByGender() {
//        List<Object> answerByGender = answerRepository.findAnswerByGender();
//        answerByGender.forEach(answer -> {
//            System.out.println(answer.toString());
//        });
//    }



}
