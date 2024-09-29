package beyond.samdasoo.common.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false,updatable = false)
    private LocalDateTime createdAt; // column 생성 날짜

    @UpdateTimestamp
    @Column(name = "updatedAt" , nullable = false)
    private LocalDateTime updatedAt; // column 생성 날짜


}
