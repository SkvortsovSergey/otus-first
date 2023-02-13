package com.example.service.iml;

import com.example.model.Question;
import com.example.service.StudentCheckService;
import org.springframework.stereotype.Service;

@Service
public class StudentCheckServiceImpl implements StudentCheckService {

    @Override
    public boolean checkStudent(Question question, String answer) {
        return question.getAnswers().stream().anyMatch(item -> answer.equals(item.getAnswer()));
    }
}
