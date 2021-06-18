package ai.ecma.api_service2.entity;

import ai.ecma.api_service2.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Credit extends AbsEntity {
    @Column(nullable = false)
    private Double percent;

    @Column(nullable = false)
    private Double lifeTime;

    private Integer minSum;

    private Integer maxSum;

    private Boolean active;
}
