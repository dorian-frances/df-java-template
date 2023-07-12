package io.df.java.template.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    UUID id;
    String username;
}
