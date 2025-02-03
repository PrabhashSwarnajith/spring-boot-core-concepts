package lk.zerocode.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
