package com.example.alligatorstours.cca2client.types;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class DialogSuggestionValue {

    private MessageInput input;
}
