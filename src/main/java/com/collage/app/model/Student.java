package com.collage.app.model;



import com.collage.audit.BaseAudit;
import lombok.*;

import javax.persistence.*;

@EntityListeners(EntityListeners.class)
@Entity
@Table(name = "student")
@Setter
@Getter
@ToString
@NoArgsConstructor

public class Student extends BaseAudit {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age", nullable = false)
    private Long age;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "alt_mobile_number")
    private String altMobileNumber;

    @Column(name = "usn")
    private String usn;


}