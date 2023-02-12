package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
@Setter
public class Question {
    String question;
    Set<Answer> answers;
}

