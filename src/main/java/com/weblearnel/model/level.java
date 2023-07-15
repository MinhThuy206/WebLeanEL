package com.weblearnel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "level")
public class level {
    @Id
    @Column(name = "lv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lv_id;
    @Column(name = "lv_name")
    private String lv_name;
    @Column(name = "lv_description")
    private String lv_description;
}
