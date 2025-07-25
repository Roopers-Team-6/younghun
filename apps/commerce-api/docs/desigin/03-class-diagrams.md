# 클래스 다이어그램

```mermaid
classDiagram


%% 계정        

class User {
   - id: UserId
   - email: Email
   - birthDay: Birthday
   - gender: String
   - createdAt: LocalDateTime
   - updatedAt: LocalDateTime
   - deletedAt: LocalDateTime
}


%% 좋아요
class Like {
    - user: User
    - product: Product
    - createdAt: LocalDateTime
    - updatedAt: LocalDateTime
}


%% 브랜드
class Brand {
    - id: Long
    - name: BrandName
    - products: List<Product>
    - createdAt: LocalDateTime
    - updatedAt: LocalDateTime
    - deletedAt: LocalDateTime
  
    + add(productId: Long, name: ProductName): void 
}


%% 상품
class Product {
    - id: Long
    - name: ProductName
    - price: ProductPrice
    - description: String
    - createdAt: LocalDateTime
    - updatedAt: LocalDateTime
    - deletedAt: LocalDateTime
}


%% 재고
class Stock { 
 - id: Long
 - product: Product
 - stock: ProductStock
 - createdAt: LocalDateTime
 - updatedAt: LocalDateTime
 - deletedAt: LocalDateTime
 
 + decrease(prductId: Long, prductStock: int): int
}


%% 주문
class Order {
  - id: Long
  - orderNumber: String
  - address: String
  - status: OrderStatus
  - orderItem: List<OrderItem>
  - user: User
  - memo: String
  - totalPrice: BigInt
  - createdAt: LocalDateTime
  - updatedAt: LocalDateTime
  - deletedAt: LocalDateTime
  
}


%% 주문 내역
class OrderHistory { 
  - id: Long
  - orderNumber: String
  - address: String
  - productName: String
  - status: OrderStatus
  - orderItem: List<OrderItem>
  - totalPrice: BigInt
  - userId: String
  - memo: String
  
  - createdAt: LocalDateTime
  - updatedAt: LocalDateTime  
}


%% 주문 상품
class OrderItem {
  - id: Long
  - product: Product

  - quantity: int
  - unitPrice: BigInt
  - createdAt: LocalDateTime
  - updatedAt: LocalDateTime
  - deletedAt: LocalDateTime
}


%% 결제
class Payment { 
  - id: Long
  - user: User
  - orderNumber: String
  - paymentAmout: BigInt
  - description: String
  - createdAt: LocalDateTime
  - updatedAt: LocalDateTime
  - deletedAt: LocalDateTime
}


%% 포인트
class Point { 
  - id: Long
  - userId: String
  - amount: PointAmount
  - createdAt: LocalDateTime
  - updatedAt: LocalDateTime
  - deletedAt: LocalDateTime  
}

Brand "1" --> "N" Product : 소유
Like --> Product : 참조    
Like --> User : 참조

Order --> User : 참조
Order "1" --> "N" OrderItem : 소유

OrderHistory --> Order : 참조
OrderItem --> Product : 참조
Payment --> User : 참조
Point --> User : 참조

Product "1" --> "1" Stock : 소유

```
