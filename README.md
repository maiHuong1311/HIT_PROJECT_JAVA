# 🎓 UFES - Ứng dụng hỗ trợ ôn thi

Ứng dụng hỗ trợ ôn thi bằng những đề thi gồm các câu hỏi trắc nghiệm và tự luận của tất cả các môn học trên đại học, nhằm giúp học sinh/sinh viên hiểu sâu kiến thức và phản xạ nhanh hơn trong các kỳ thi.

Hệ thống giúp kiểm soát số lượng câu hỏi, đáp án và theo dõi những phần đã hoàn thành, luôn cập nhật những đề thi mới nhất đảm bảo sát với nội dung kỳ thi thực tế.

<p align="center">
  <img src="src/main/resources/images/uxnmh.jpg" width="400">
</p>

## ✨ Tính năng chính
- **Kho đề thi đa dạng:** Bao gồm trắc nghiệm và tự luận từ nhiều môn học đại cương và chuyên ngành.
- **Theo dõi tiến độ:** Kiểm soát số lượng câu hỏi, đáp án và ghi nhận những phần đã và đang hoàn thành.
- **Cập nhật liên tục:** Luôn cập nhật những đề thi mới nhất sát với chương trình học hiện hành.
- **Giao diện thân thiện:** Thiết kế hiện đại, trực quan, giúp người dùng tập trung vào việc học.

## 🛠 Công nghệ sử dụng
- **Ngôn ngữ:** Java (JavaFX)
- **Build Tool:** Maven.
- **Giao diện:** FXML & CSS Custom

## 🚀 Hướng dẫn cài đặt & Khởi chạy

### Yêu cầu hệ thống
- **Java Development Kit (JDK):** Phiên bản 17 hoặc mới hơn.
- **Build Tool:** Maven.
- **IDE:** IntelliJ IDEA (khuyên dùng) hoặc Eclipse.

### Các bước thực hiện
1. **Clone repository:**
   ```bash
   git clone https://github.com/maiHuong1311/HIT_PROJECT_JAVA.git
Mở dự án: Mở IntelliJ IDEA -> File -> Open -> Chọn thư mục dự án.

Cài đặt thư viện: Chờ Maven tự động tải các dependencies (JavaFX SDK).

Chạy ứng dụng: Tìm file src/main/java/com/example/hellowordjavafx/HelloApplication.java, chuột phải và chọn Run

```markdown

### 2. Kế hoạch phát triển tiếp theo (Roadmap)
Tích hợp AI dễ trao đổi và thắc mắc dễ giải đáp khi ôn thi

## 🗺️ Lộ trình phát triển (Roadmap)
Dựa trên các nhánh tính năng đang thực hiện:

- [x] **UI/UX Design:** Hoàn thiện giao diện Dashboard hiện đại (`feature/ux`).
- [x] **Authentication:** Xây dựng hệ thống đăng nhập/đăng ký (`feature/authentication`).
- [x] **Question Management:** Tính năng thêm câu hỏi trắc nghiệm/tự luận (`feature/create-fuction-to-add-question`).
- [x] **Search Engine:** Tìm kiếm môn học và tài liệu (`feature/create-function-to-find-subject`).
- [x] **Review System:** Hệ thống ôn tập và đánh giá kiến thức (`feature/create-function-to-review`).
- [x] **Random Display:** Hiển thị môn học/đề thi ngẫu nhiên (`feature/random-subjects-display`).