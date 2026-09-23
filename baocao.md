# Bài tập 1: Vá lỗi bán giá không đồng nhất

## 1. Thông tin sinh viên

- Họ tên: Đoàn Trung Nguyên
- Mã sinh viên: B24DTCN435
- Lớp: HN-K24-CNTT4

## 2. Mô tả vấn đề

Trình bày hệ thống Flash Sale có 10 instance và hiện tượng người dùng nhìn thấy giá khác nhau.

## 3. Nguyên nhân kỹ thuật

Phân tích bộ nhớ riêng của mỗi instance và hạn chế của HashMap local cache.

## 4. Test case minh họa

Trình bày bảng T0, T1, T2, T3 của sản phẩm P001.

## 5. Giải pháp Redis Distributed Cache

Trình bày kiến trúc các instance dùng chung Redis.

## 6. Mã nguồn sau khi sửa

Chèn các file RedisCacheConfig, ProductPriceService và application.yml.

## 7. Xử lý Redis down

Trình bày CacheErrorHandler, fallback Database, timeout và TTL.

## 8. Xử lý dữ liệu đầu vào không hợp lệ

Trình bày validateProductId và condition trong @Cacheable/@CacheEvict.

## 9. Kết quả kiểm thử

Ghi kết quả test Postman, hai instance và trường hợp tắt Redis.

## 10. Kết luận

Redis giúp các instance dùng chung cache và giảm nguy cơ trả về giá khác nhau.