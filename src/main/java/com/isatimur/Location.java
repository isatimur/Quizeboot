package com.isatimur;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by tisachenko on 07.05.16.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Location {
    private double lat;
    private double lng;

    public Location() {
    }
}
