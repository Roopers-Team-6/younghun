package com.loopers.interfaces.api.user;

import static com.loopers.interfaces.api.ApiResponse.Metadata.Result.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.loopers.infrastructure.user.UserJpaRepository;
import com.loopers.interfaces.api.ApiResponse;
import com.loopers.interfaces.api.user.UserV1Dto.UserRequest;
import com.loopers.interfaces.api.user.UserV1Dto.UserResponse;
import com.loopers.utils.DatabaseCleanUp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserV1ApiE2ETest {


  private final TestRestTemplate testRestTemplate;
  private final UserJpaRepository userJpaRepository;
  private final DatabaseCleanUp databaseCleanUp;

  @Autowired
  public UserV1ApiE2ETest(
      TestRestTemplate testRestTemplate,
      UserJpaRepository userJpaRepository,
      DatabaseCleanUp databaseCleanUp
  ) {
    this.testRestTemplate = testRestTemplate;
    this.userJpaRepository = userJpaRepository;
    this.databaseCleanUp = databaseCleanUp;
  }

  @AfterEach
  void tearDown() {
    databaseCleanUp.truncateAllTables();
  }


  /**
   * - [X]  회원 가입이 성공할 경우, 생성된 유저 정보를 응답으로 반환한다.
   * - [ ]  회원 가입 시에 성별이 없을 경우, `400 Bad Request` 응답을 반환한다.
   */

  @DisplayName("POST /api/v1/users")
  @Nested
  class Create {
    private static final String ENDPOINT_JOIN = "/api/v1/users";

    @DisplayName("회원 가입이 성공할 경우, 생성된 유저 정보를 응답으로 반환한다.")
    @Test
    void returnsUserInfo_whenJoinedUserResponse() {
      //arrange
      String userId = "userId";
      String email = "test@test.com";
      String birth = "2010-01-01";

      UserRequest userRequest = new UserRequest(userId, email, birth);
      // act
      ParameterizedTypeReference<ApiResponse<UserResponse>> responseType = new ParameterizedTypeReference<>() {
      };
      ResponseEntity<ApiResponse<UserResponse>> response =
          testRestTemplate.exchange(ENDPOINT_JOIN, HttpMethod.POST, new HttpEntity<>(userRequest, null), responseType);
      // assert
      assertAll(
          () -> assertThat(response.getStatusCode().is2xxSuccessful()).isTrue(),
          () -> assertThat(response.getBody().meta().result()).isEqualTo(SUCCESS),
          () -> assertThat(response.getBody().data().userId()).isEqualTo(userId),
          () -> assertThat(response.getBody().data().email()).isEqualTo(email),
          () -> assertThat(response.getBody().data().birthday()).isEqualTo(birth)

      );

    }

  }
}
