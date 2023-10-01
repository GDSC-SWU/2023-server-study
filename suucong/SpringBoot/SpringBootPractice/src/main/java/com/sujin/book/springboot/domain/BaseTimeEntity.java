package com.sujin.book.springboot.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // JPA 엔타티 클래스들이 이 클래스를 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)  // 이 클래스에 Auditing 기능을 포함시킨다. JPA Auditing 기능은 데이타베이스 엔티티의 변경 이력을 추적하고 기록하는 기능
public abstract class BaseTimeEntity {  // 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할

    @CreatedDate    // Entity가 생성되어 저장될 때 시간이 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate   // 조회한 Entity의 값을 변경할 때 시간이 자동 저장.
    private LocalDateTime modifiedDate;

}
