package com.joskar.smartjob.test.models;

import com.joskar.smartjob.test.models.commons.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone extends Auditable {
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
