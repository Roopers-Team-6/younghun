package com.loopers.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.loopers.infrastructure.user.UserJpaRepository;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import com.loopers.utils.DatabaseCleanUp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

@SpringBootTest
public class UserServiceIntegrationTest {
  @MockitoSpyBean
  private UserService userService;
  @MockitoSpyBean
  private UserJpaRepository userJpaRepository;

  @Autowired
  private DatabaseCleanUp databaseCleanUp;


  @AfterEach
  void tearDown() {
    databaseCleanUp.truncateAllTables();
  }

  @DisplayName("회원가입을 했을때, ")
  @Nested
  class Create {

    @DisplayName("User 저장이 수행된다.")
    @Test
    void returnsExampleInfo_saveUser() {
      // arrange
      UserModel userModel = new UserModel("userId", "test@test.com", "2020-01-01");
      // act
      userService.createUser(userModel);
      // assert
      Mockito.verify(userJpaRepository, Mockito.times(1)).save(userModel);
    }


    @DisplayName("이미 가입된 ID 로 회원가입 시도 시, 실패한다.")
    @Test
    void throwsBadRequestException_saveUser_whenAlreadyId() {
      // arrange
      UserModel userModel = new UserModel("userId", "test@test.com", "2020-01-01");
      userService.createUser(userModel);
      // act
      CoreException result = assertThrows(CoreException.class, () -> userService.createUser(userModel));
      // assert
      assertThat(result.getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
    }

  }
}
