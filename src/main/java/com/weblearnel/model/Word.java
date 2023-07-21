package com.weblearnel.model;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pronounce")
    private String pronounce;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "mean")
    private String mean;

    @Column(name = "attributes")
    private String attribute;

    @Column(name = "example")
    private String example;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tp_id", referencedColumnName = "tp_id")
    private Topic topic;

    @JsonIgnore
    @OneToMany(mappedBy = "word")
    private Set<Result> results;

}
