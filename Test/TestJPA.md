***
## Câu 1

Sự khác biệt giữa thuộc tính name trong annotation Entity và annotation trong table 

```Java
@Entity(name = "name") => Đây là tên của entity
@Table(name = "name")  => Đây là tên của table trong Database
```

Ở trong trường hợp đầu tiên thì Entity (Thực thể) và Table (Bảng dưới DB) sẽ có cùng tên. điều này cho phép chúng ta truy cập vào bảng dưới DB có cùng tên với entity khi viết các câu lệnh

Còn ở trường hợp thứ hai khi viết các câu lệnh query chúng ta phải dùng tên của entity và tên của table sẽ được sử dụng để đặt tên cho bảng dưới DB 

Việc dùng annotation @table cùng thuộc tính name thường được dùng để cấu hình lại tên bảng dưới DB

---
## Câu 2

Ở trong file application.properties, Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi , ta có câu lệnh

```java
spring.jpa.show-sql=true
```

và để câu truy vấn xuất ra màn hình có định dạng rõ ràng và dễ nhìn hơn chúng ta thêm

```java
spring.jpa.properties.hibernate.format_sql=true
```

ngoài ra chúng ta còn cách ghi lại loggers
```java
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

Dòng đầu tiên ghi các truy vấn SQL và câu lệnh thứ hai ghi các tham số câu lệnh đã chuẩn bị.

---
## Câu 3
ta truy cập vào link  locohost:8080/h2-console

---

## Câu 4

Thuộc tính chúng ta không muốn lưu xuống CSDL thì cần  đánh dấu bằng annotation @Transient và @Formula

@Transient đánh dấu thuộc tính sẽ không lưu xuống CSDL, thực thi ở Java Run Time


@Formula sinh lệnh SQL, thực thi ở database .

---
## Câu 5

Annotation @Column dùng để bổ sung tính chất cho cột ứng với một thuộc tính.
Tham số name trong @Column sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính
Tham số unique chỉ định yêu cầu duy nhất không được trùng lặp dữ liệu
Tham số nullable buộc trường không được null

---

## Câu 6

Trước khi lưu một entity mới – @PrePersist

Trước khi cập nhật một entity – @PreUpdate

## Câu 7

Ta có thể sử dụng 2 annotation @Embeddable và @Embedded 

@Embeddable để khai báo rằng một lớp sẽ được nhúng bởi các thực thể khác. Hãy xác định một lớp để tóm tắt các chi tiết của người liên hệ:

@Embedded được sử dụng để khai báo nhúng một kiểu  vào một thực thể khác.

---
## Câu 8


JpaRepository kế thừa từ interface PagingAndSortingRepository và QueryByExampleExecutor.

PagingAndSortingRepository thì lại kế thừa từ CrudRepository


--- 

## Câu 9

```java
@Repository
public interface PostRepository extends JpaRepository<Post,String> 

```

trong đó String có thể sửa lại là Int, Long ... tùy thuộc vào kiểu dữ liệu của key

---

## Câu 10

khi có annotation @Id thì không cần thêm cột column cùng thuộc tính unique nữa vì @Id đã được đánh dấu là khóa chính

---

## Câu 11

@Id là khóa chính cần giữ nguyên không được thay đổi còn @NaturalId tạo unique constrain lên một trường không phải primary key. Có thể được phép thay đổi, tuy nhiên vẫn phải đảm bảo duy nhất

---
## Câu 12

Ta có thể sử dụng annotation @index 

1.ví dụ sử dụng annotation đó với index cho 1 column

Chỉ mục của chúng ta phải có tên. Nếu chúng ta không chỉ định thì đó là giá trị tự tạo. Khi muốn cấu hình tùy chỉnh, chúng ta chỉ cần thêm thuộc tính name:

```java
@Index(name = "index1", columnList = "name")
```
ta sẽ tạo được chỉ mục với tên do người dùng xác định

```java
[main] DEBUG org.hibernate.SQL -
  create index index1 on Client (name)
```

2.ví dụ với index cho tổ hợp nhiều column

Chúng ta có thể chỉ định nhiều cột cho một chỉ mục.
Chúng ta làm điều đó bằng cách phân tách các tên bằng dấu phẩy
```java
@Index(name = "mulitIndex1", columnList = "firstName, lastName")

@Index(name = "mulitIndex2", columnList = "lastName, firstName")
```

tương tự như trên 
```java
[main] DEBUG org.hibernate.SQL -
  create index mulitIndex1 on Client (firstName, lastName)
   
[main] DEBUG org.hibernate.SQL -
  create index mulitIndex2 on Client (lastName, firstName)
```

---
## Câu 13
Có thể sử dụng CustomID hoặc sử dụng UUID để tạo ra chuỗi duy nhất 

ví dụ

```java
public class Client {
  @Id
  private String id;  
  private String name;

  public Story(String name) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
  }
}


```

## Câu 14
https://github.com/Thaix2phomai/springboots/tree/main/demo
---
## Câu 15
@NamedQuery nếu ta không sử dụng repository interface mà chỉ dùng EntityManager để thao tác dữ liệu. Query đó sử dụng ở nhiều nơi khác nhau thì có thể dùng @NamedQuery.
```java
@Entity(name ="oto") 
@Table(name = "car")  
@Data 
@NamedQuery(name = "Car.findById", query = "SELECT c FROM oto c WHERE c.id=:id")
public class Car {
@Id private long id; 
private String model;
private String maker;
private int year; 
}
```

@Query với annotation @Query ta có thể khai báo câu query cho các method trong repository.

Việc khai báo câu query với @Query giúp ta tối ưu câu sql, và xử lý trong những trường hợp mà các method do Spring Data không thể đáp ứng:

Việc sử dụng các method có sẵn khi extends interface JPARepository, CrudRepository  không đáp ứng được yêu cầu.
Việc đặt tên method theo chuẩn Query Creation quá dài hoặc tối nghĩa. (Ví dụ bạn muốn truy vấn theo 5 điều kiện thì tên method của bạn sẽ gồm 5 điều kiện đó => quá dài)

```java
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  @Query("SELECT e FROM Customer e WHERE e.name = :name AND e.address = :address")
  List<Customer> findByNameAndAddress(@Param("name") String name, @Param("address") String address);
}
```













