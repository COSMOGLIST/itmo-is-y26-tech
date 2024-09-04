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
public class CreateFriendshipMessage {
    private int id1;
    private int id2;
}