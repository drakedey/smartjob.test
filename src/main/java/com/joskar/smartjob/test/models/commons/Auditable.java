package com.joskar.smartjob.test.models.commons;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class Auditable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @ColumnDefault("random_uuid()")
    private UUID id;

    @Column(name = "is_active")
    private Boolean active = true;

    @Column(name = "created")
    private Date created = new Date();

    @Column(name = "updated")
    private Date updated = new Date();
}
