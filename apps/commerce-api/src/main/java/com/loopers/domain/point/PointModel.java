package com.loopers.domain.point;

import com.loopers.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "point")
public class PointModel extends BaseEntity {

  private String userId;
  private int point;

  public PointModel() {
  }

  public PointModel(String userId, int point) {
    this.userId = userId;
    this.point = point;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getPoint() {
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }
}
