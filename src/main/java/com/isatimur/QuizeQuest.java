package com.isatimur;

import lombok.*;

/**
 * Created by tisachenko on 07.05.16.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class QuizeQuest {

    String question;
    String[] choices;
    String[] answers;
    int score;
    long timer;

    public QuizeQuest(String question, int score, int timer) {
        this(question, null, null, score, timer);
    }

    public QuizeQuest() {
    }
}
