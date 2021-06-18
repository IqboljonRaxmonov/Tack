package ai.ecma.api_service2.entity;

import ai.ecma.api_service2.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditInfo extends AbsEntity {

    @Column(nullable = false)
    private UUID userId;

    @ManyToOne(optional = false)
    private Credit credit;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private Double amount;

    private Date date;
}
