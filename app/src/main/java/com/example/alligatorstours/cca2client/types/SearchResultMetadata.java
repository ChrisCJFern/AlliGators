package com.example.alligatorstours.cca2client.types;

import lombok.*;

@Data
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultMetadata {

    private Double confidence;
    private Double score;
}
