package com.loopers.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import java.time.LocalDate;
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
      String email = "email@email.com";
      // act
      UserModel userModel = new UserModel(userId, email);
      // assert
      assertThat(userModel.getUserId().length()).isLessThanOrEqualTo(10);
    }

    @DisplayName("ID가 숫자 10자 이내가 주어지면, 정상적으로 생성된다.")
    @Test
    void createUser_numberIdUnderMaxLength_createsUserSuccessfully() {
      // arrange
      String userId = "0123456789";
      String email = "email@email.com";
      // act
      UserModel userModel = new UserModel(userId, email);
      // assert
      assertThat(userModel.getUserId().length()).isLessThanOrEqualTo(10);
    }

    @DisplayName("ID가 숫자와알파벳 혼합 10자 이내가 주어지면, 정상적으로 생성된다.")
    @Test
    void createUser_mixedIdUnderMaxLength_createsUserSuccessfully() {
      // arrange
      String userId = "userI12345";
      String email = "email@email.com";
      // act
      UserModel userModel = new UserModel(userId, email);
      // assert
      assertThat(userModel.getUserId().length()).isLessThanOrEqualTo(10);
    }

    @DisplayName("ID가 10자 초과시, BAD_REQUEST 예외가 발생한다.")
    @Test
    void createUser_nonAlphanumericOrTooLongId_throwsIllegalArgumentException() {
      // arrange
      String userId = "userI123456";
      String email = "email@email.com";
      // act
      CoreException result = assertThrows(CoreException.class, () -> new UserModel(userId, email));
      // assert
      assertThat(result.getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
    }

    @DisplayName("ID는 공백으로 생성할 수 없습니다.")
    @Test
    void throwsBadRequestException_whenIdIsBlank() {
      // arrange
      String userId = "  ";
      String email = "email@email.com";
      // act
      CoreException result = assertThrows(CoreException.class, () -> new UserModel(userId, email));
      // assert
      assertThat(result.getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
    }

    @DisplayName("ID는 숫자,알파벳이외의 문자로 생성할 수 없습니다.")
    @Test
    void throwsBadRequestException_whenIdIsDifferentWord() {
      // arrange
      String userId = "#@!$]";
      String email = "email@email.com";
      // act
      CoreException result = assertThrows(CoreException.class, () -> new UserModel(userId, email));
      // assert
      assertThat(result.getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
    }

    @DisplayName("이메일형식이 틀리면, BAD_REQUEST 예외가 발생합니다.")
    @Test
    void throwsBadRequestException_whenEmailIsWrongPattern() {
      // arrange
      String userId = "userId1";
      String email = "email";
      // act
      CoreException result = assertThrows(CoreException.class, () -> new UserModel(userId, email));
      // assert
      assertThat(result.getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
    }

    @DisplayName("생년월일형식이 틀리면, BAD_REQUEST 예외가 발생합니다.")
    @Test
    void throwsBadRequestException_whenBirthIsWrongPattern() {
      // arrange
      String userId = "userId1";
      String email = "email@email.com";
      String birth = "wrong";

      // act
      CoreException result = assertThrows(CoreException.class, () -> new UserModel(userId, email, birth));
      // assert
      assertThat(result.getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
    }


  }
}
