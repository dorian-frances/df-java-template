package io.df.java.template.infrastructure.postgres.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@Table(name = "user", schema = "exposition_storage")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "username", nullable = false)
    private String username;

}
