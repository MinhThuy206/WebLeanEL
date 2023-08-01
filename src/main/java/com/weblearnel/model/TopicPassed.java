package com.weblearnel.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "topic_passed")
public class TopicPassed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_passed_id")
    private long topicPassedId;

    @Column(name = "topic_passed_name")
    private String topicPassedName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_topic_id", referencedColumnName = "tp_id")
    private Topic topic;

    public void assignTopic(Topic topicToAssign) {
        this.topic = topicToAssign;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_topic_passed", joinColumns = @JoinColumn(name = "topic_passed_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
    
}
