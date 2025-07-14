package com.loopers.infrastructure.user;

import com.loopers.domain.user.UserModel;
import com.loopers.domain.user.UserRepository;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
  private final UserJpaRepository repository;

  @Override
  public UserModel save(UserModel userModel) {
    return repository.save(userModel);
  }

  @Override
  public void duplicateUserId(String userId) {
    List<UserModel> exitsUser = repository.findByUserId(userId);
    if(!exitsUser.isEmpty()){
      throw new CoreException(ErrorType.BAD_REQUEST, "해당 아이디는 이미 가입이 되어있습니다.");
    }
  }
}
