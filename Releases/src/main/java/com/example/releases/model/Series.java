package com.example.releases.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Series {
    private Set<String> volumes = new HashSet<String>();
    private String seriesName;

    public Series(String seriesName){
        this.seriesName = seriesName;
    }
}
