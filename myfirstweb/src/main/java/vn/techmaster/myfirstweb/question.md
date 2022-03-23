**1.**
```sql
- GroupID là một thành phần XML trong tệp POM.XML của dự án Maven chỉ định id của nhóm dự án. GroupID sẽ xác định duy nhất dự án của bạn trên tất cả các dự án, vì vậy  cần thực thi một lược đồ đặt tên. Nó phải tuân theo các quy tắc về tên gói, nghĩa là ít nhất phải là tên miền mà bạn kiểm soát và bạn có thể tạo bao nhiêu nhóm con tùy thích. Ví dụ: org.apache.maven, org.apache.commons
- ArtifactID là một thành phần XML trong POM.XML của dự án Maven chỉ định id của dự án. ArtifactId là tên của jar không có phiên bản. Người tạo có thể chọn bất cứ tên nào mong muốn với các chữ cái viết thường và không có ký hiệu lạ. Nếu đó là bình của bên thứ ba thì phải lấy tên của jar khi nó được phân phối. ví dụ. maven, commons-math,...
```
***

**2.**
```sql
Lý do đảo ngược tên miền trong <groupId>vn.techmaster</groupId>? là giúp lập trình viên truy cập vào thư viện cùng tên của các công ty khác nhau 1 cách dễ dàng tránh sự nhầm lẫn.
```
***

**3.**
```sql
SpringBoot có 2 cơ chế để quản lý thư viện gồm

1. Mô hình ba lớp (three tier)
Đây là mô hình tổ chức source code rất phổ biến trong Spring Boot. Cụ thể, ứng dụng được chia làm 3 tầng (tier hoặc layer) như sau:
- Presentation layer: tầng này tương tác với người dùng, bằng View, Controller (trong MVC) hoặc API (nếu có).
- Business logic layer: Chứa toàn bộ logic của chương trình, các đa số code nằm ở đây
- Data access layer: Tương tác với database, trả về kết quả cho tầng business logic

2. Mô hình MVC 
Chia tầng presentation làm 3 phần:
- Model: các cấu trúc dữ liệu của toàn chương trình, có thể đại diện cho trạng thái của ứng dụng
- View: lớp giao diện, dùng để hiển thị dữ liệu ra cho user xem và tương tác
- Controller: kết nối giữa Model và View, điều khiển dòng dữ liệu
Dữ liệu từ Model qua Controller sau đó được gửi cho View hiển thị ra. Và ngược lại, khi có yêu cầu mới từ View, thì sẽ qua Controller thực hiện thay đổi dữ liệu của Model.

Tuy nhiên, MVC chỉ mô tả luồng đi của dữ liệu, nó không nói rõ như code đặt ở đâu (ở Model, View hay Controller), rồi lưu trữ Model vào database kiểu gì,... Do đó, đối với ứng dụng hoàn chỉnh như Spring Boot thì cần kết hợp cả mô hình MVC và 3-tier lại với nhau.
```
***

**4.**
```sql
File pom.xml là nơi khai báo tất cả những gì liên quan đến dự án được cấu hình qua maven, như khai báo các dependency, version của dự án, tên dự án, repossitory …
```
***

**5.**
```sql
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

Ý nghĩa của của các thẻ dependency:
Group Id : Tên tổ chức / công ty / cá nhân của dự án
Artifact Id : Tên của packge, dự án
```
***

**6.**
```sql
Ý nghĩa của @Controller là để chỉ định một lớp nhất định làm bộ điều khiển trong khuôn khổ Spring.
```
***

**7.**
```sql
@RequestMapping là một chú thích ánh xạ các yêu cầu HTTP tới các phương thức xử lý của bộ điều khiển MVC và REST.
Ngoài value, nó có những tham số params, method. Sử dụng phần tử params có thể có nhiều phương thức xử lý xử lý các yêu cầu đến cùng một URL, nhưng với các tham số khác nhau.
```
***

**8.**
```sql

```
***

**9.**
```sql
 @RequestParam và @PathVariable là hai annotation được sử dụng để truy cập dữ liệu từ các request. Chúng được sử dụng vào những mục đích trong những trường hợp khác nhau.
- @RequestParam được sử dụng để truy cập (lấy) giá trị của parameters trên URL(kiểu query string)
- @PathVariable được sử dụng để lấy giá trị trên URI theo template (còn gọi là URI template).

 Sử dụng @RequestParam và @PathVariable ở Controller để lấy giá trị người dùng nhập trên trình duyệt. @RequestParam dùng theo định dạng key và value như ?param1=10&param2=20. @PathVariable  sử dụng định dạng là /param/10.
```
***

**10.**
```sql
Thứ tự các thành phần đường dẫn @PathVariable không thể hoán đổi được
```
***

**11.**
```sql
- @PostMapping là phiên bản chuyên biệt của chú thích @RequestMapping hoạt động như một phím tắt cho @RequestMapping (method = RequestMethod.POST). Các phương thức chú thích @PostMapping trong các lớp được chú thích @Controller xử lý các yêu cầu HTTP POST khớp với biểu thức URI đã cho.

- @GetMapping là phiên bản chuyên biệt của chú thích @RequestMapping hoạt động như một phím tắt cho @RequestMapping (method = RequestMethod.GET). Các phương thức chú thích @GetMapping trong các lớp được chú thích @Controller xử lý các yêu cầu HTTP GET khớp với biểu thức URI đã cho.
```
***

**12.**
```sql

```
***

**13.**
```sql
Response Body: Chứa dữ liệu tài nguyên.
```
***

**14.**
```sql
1. Đổi port với Property file
Cách nhanh nhất và dễ nhất để tùy chỉnh Spring Boot là ghi đè các giá trị của các thuộc tính mặc định.
Đối với Port Server, thuộc tính chúng ta cần thay đổi là server.port.

Mặc định, server sẽ khởi chạy trên port 8080, chúng ta có thể thay đổi điều này bắt cách điều chỉnh giá trị server.port trong application.properties hay application.yml tương ứng.

application.properties
`server.port=8081`

application.yml
`server:
port : 8081`
Bây giờ, server sẽ chạy trên port 8081.

Cả hai tệp đều được Spring Boot tải tự động nếu được đặt trong thư mục src / main / resources của ứng dụng Maven.

2. Environment-Specific Ports
Nếu chúng ta có một ứng dụng được triển khai trong các môi trường khác nhau, chúng tôi có thể muốn nó chạy trên các cổng khác nhau trên mỗi hệ thống.

Chúng ta có thể dễ dàng đạt được điều này bằng cách sử dụng Spring Profile với mỗi môi trường có một file cấu hình riêng.

Ví dụ application-dev.properties 
`server.port=8081`

Còn application-qa.properties sử dụng trong môi trường của qa để kiểm thử chạy ở port 8082
`server.port=8082`

3. Sử dụng CMD
Khi đóng gói và chạy ứng dụng của chúng ta dưới dạng jar, chúng ta có thể đặt đối số server.port bằng lệnh java:
`java -jar spring-5.jar --server.port=8083`

Hoặc bằng cách sử dụng cú pháp tương đương:
`java -jar -Dserver.port=8083 spring-5.jar`
```
***