package com.collage.app.model;


import com.collage.audit.BaseAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@EntityListeners(EntityListeners.class)
@Entity
@Table(name = "subject")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Subject extends BaseAudit {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher")
    @ToString.Exclude
    private Teacher teacher;


}
