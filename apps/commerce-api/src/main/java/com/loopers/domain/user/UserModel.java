package com.loopers.domain.user;

import com.loopers.domain.BaseEntity;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
public class UserModel extends BaseEntity {

  private String userId;

  private String email;

  private LocalDate birthday;

  public UserModel() {
  }

  public UserModel(String userId) {

    if(userId == null || userId.trim().isEmpty()) {
      throw new CoreException(ErrorType.BAD_REQUEST, "아이디는 공백으로 생성할 수 없습니다.");
    }

    if(!userId.matches("^[A-Za-z0-9]+$")) {
      throw new CoreException(ErrorType.BAD_REQUEST, "아이디는 숫자 혹은 알파벳으로만 만들수 있습니다.");
    }

    if (userId.length() > 10) {
      throw new CoreException(ErrorType.BAD_REQUEST, "아이디는 10자를 초과할 수 없습니다.");
    }

    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }
}
