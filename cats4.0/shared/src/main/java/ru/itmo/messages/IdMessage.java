package ru.itmo.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Data
@ToString
@AllArgsConstructor
@Builder
@Jacksonized
public class IdMessage {
    private int id;
}