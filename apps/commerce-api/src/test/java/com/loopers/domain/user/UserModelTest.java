package com.loopers.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class UserModelTest {


  @DisplayName("회원가입을 했을때, ")
  @Nested
  class Create {

    @DisplayName("ID가 영문 10자 이내, 정상적으로 생성된다.")
    @Test
    void createUser_alphabeticIdUnderMaxLength_createsUserSuccessfully() {
      // arrange
      String userId = "useruserId";
      // act
      UserModel userModel = new UserModel(userId);
      // assert
      assertThat(userModel.getUserId().length()).isLessThanOrEqualTo(10);
    }
    @DisplayName("ID가 숫자 10자 이내가 주어지면, 정상적으로 생성된다.")
    @Test
    void createUser_numberIdUnderMaxLength_createsUserSuccessfully() {
      // arrange
      String userId = "0123456789";
      // act
      UserModel userModel = new UserModel(userId);
      // assert
      assertThat(userModel.getUserId().length()).isLessThanOrEqualTo(10);
    }

    @DisplayName("ID가 숫자와알파벳 혼합 10자 이내가 주어지면, 정상적으로 생성된다.")
    @Test
    void createUser_mixedIdUnderMaxLength_createsUserSuccessfully() {
      // arrange
      String userId = "userI12345";
      // act
      UserModel userModel = new UserModel(userId);
      // assert
      assertThat(userModel.getUserId().length()).isLessThanOrEqualTo(10);
    }
  }
}
