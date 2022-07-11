package com.collage.app.model;



import com.collage.audit.BaseAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@EntityListeners(EntityListeners.class)
@Entity
@Table(name ="teacher")
@Setter
@Getter
@ToString
@NoArgsConstructor

public class Teacher extends BaseAudit {

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

}
