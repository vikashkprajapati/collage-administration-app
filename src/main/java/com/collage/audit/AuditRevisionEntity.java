package com.collage.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.*;

@Entity
@Table(name = "revision_info")
@RevisionEntity(com.collage.audit.AuditRevisionListener.class)
@AttributeOverrides({
        @AttributeOverride(name = "timestamp", column = @Column(name = "rev_timestamp")),
        @AttributeOverride(name = "id", column = @Column(name = "revision_id"))
})
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AuditRevisionEntity extends DefaultRevisionEntity {

    @Column(name = "_user")
    private String user;
}