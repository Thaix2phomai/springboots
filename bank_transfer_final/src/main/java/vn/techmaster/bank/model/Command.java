package vn.techmaster.bank.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Command {
  @Id

  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  private User requester; // Người yêu cầu hành động này

  public Command(User requester) {
    this.requester = requester;
  }

  private CommandStatus status;
  private LocalDateTime createdAt;


  @PrePersist // Khi lệnh bắt đầu chạy
  public void prePersist() {
    createdAt = LocalDateTime.now();
  }

}
