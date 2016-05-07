package com.isatimur;

import lombok.*;

/**
 * Created by tisachenko on 07.05.16.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Quize {
    private Long id;
    private Location loc;
    private QuizeType type;
    private QuizeQuest quest;

    public Quize() {
    }
}
