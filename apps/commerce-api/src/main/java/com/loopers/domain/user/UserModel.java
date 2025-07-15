package com.loopers.domain.user;

import com.loopers.domain.BaseEntity;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import lombok.Builder;

@Entity
@Table(name = "user")
public class UserModel extends BaseEntity {

  private String userId;

  private String email;

  private LocalDate birthday;

  private String gender;

  public UserModel() {
  }

  @Builder
  public UserModel(String userId, String email, String birthday, String gender) {

    if (userId == null || userId.trim().isEmpty()) {
      throw new CoreException(ErrorType.BAD_REQUEST, "아이디는 공백으로 생성할 수 없습니다.");
    }

    if (!userId.matches("^[A-Za-z0-9]+$")) {
      throw new CoreException(ErrorType.BAD_REQUEST, "아이디는 숫자 혹은 알파벳으로만 만들수 있습니다.");
    }

    if (userId.length() > 10) {
      throw new CoreException(ErrorType.BAD_REQUEST, "아이디는 10자를 초과할 수 없습니다.");
    }

    if (!email.matches("^[a-z]+@[a-z]+\\.[a-z]{2,}$")) {
      throw new CoreException(ErrorType.BAD_REQUEST, "현재 등록된 이메일 패턴과 다릅니다.");
    }

    if (!isValidBirth(birthday)) {
      throw new CoreException(ErrorType.BAD_REQUEST, "생년월일 형식은 yyyy-MM-dd 이어야 하며, 유효한 날짜여야 합니다.");
    }

    this.userId = userId;
    this.email = email;
    this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    this.gender = gender;
  }


  private boolean isValidBirth(String birthday) {
    DateTimeFormatter BIRTH_FMT = DateTimeFormatter.ofPattern("uuuu-MM-dd")
        .withResolverStyle(ResolverStyle.STRICT);

    if (birthday == null) {
      return false;
    }
    try {
      LocalDate.parse(birthday, BIRTH_FMT);
      return true;
    } catch (DateTimeParseException ex) {
      return false;
    }
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
