-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 17, 2019 lúc 03:13 PM
-- Phiên bản máy phục vụ: 10.4.6-MariaDB
-- Phiên bản PHP: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `pthttt`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `author`
--

CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `birthday` date DEFAULT NULL,
  `description` text COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `author`
--

INSERT INTO `author` (`id`, `birthday`, `description`, `name`, `status`) VALUES
(1, '1970-11-18', 'Thạch Lam tên thật là Nguyễn Tường Vinh sinh ngày 7/7/1910 tại Hà Nội, nhưng nguyên quán ở làng Cẩm Phô, tỉnh Quảng Nam ( nay thuộc thị xã Hội An Tỉnh Quảng Nam).\r\n\r\nThạch Lam là người con thứ 6 trong gia đình có 7 người con. Cha của Thạch Lam mất sớm, bà Phán Nhu – Mẹ ông – phải một mình buôn bán tần tảo nuôi một Mẹ chồng và bảy người con.\r\n\r\nMuốn sớm đỡ đần cho Mẹ, Thạch Lam đã nhờ đổi tên khai tăng tuổi để học ban Thành chung. Sau khi đỗ vào Cao đẳng Canh Nông ở Hà Nội, nhưng chỉ học một thời gian rồi lại chuyển sang trường Trung học Albert Saraut để học thi Tú tài.\r\n\r\nKhi đã đỗ tú tài phần thứ nhất, Thạch Lam bỏ học để làm báo với hai anh. Do cuộc sống lao lực vì miếng cơm manh áo, Thạch Lam đã sớm mắc căn bệnh lao phổi, một căn bệnh nan y thời bấy giờ. Ông mất tại “ nhà cây liễu” vào ngày 27-6-1942, lúc mới 32 tuổi, khi đang còn trong độ tuổi rực rỡ trên văn đàn.', 'Thạch Lam', 'active'),
(2, '1920-11-05', 'Tố Hữu, tên thật là Nguyễn Kim Thành, quê gốc ở làng Phù Lai, nay thuộc xã Quảng Thọ, huyện Quảng Điền, tỉnh Thừa Thiên – Huế. Ông là một nhà thơ tiêu biểu của thơ cách mạng Việt Nam, đồng thời ông còn là một chính khách, một cán bộ cách mạng lão thành.', 'Tố Hữu', 'active');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `description` text COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `pages` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `path_images` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `price_buy` int(11) DEFAULT NULL,
  `price_sell` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `size` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `book`
--

INSERT INTO `book` (`id`, `description`, `name`, `pages`, `path_images`, `price_buy`, `price_sell`, `quantity`, `size`, `status`, `category_id`) VALUES
(1, 'Những câu chuyện nhỏ xảy ra ở một ngôi làng nhỏ: chuyện người, chuyện cóc, chuyện ma, chuyện công chúa và hoàng tử , rồi chuyện đói ăn, cháy nhà, lụt lội,... Bối cảnh là trường học, nhà trong xóm, bãi tha ma. Dẫn chuyện là cậu bé 15 tuổi tên Thiều. Thiều có chú ruột là chú Đàn, có bạn thân là cô bé Mận. Nhưng nhân vật đáng yêu nhất lại là Tường, em trai Thiều, một cậu bé học không giỏi. Thiều, Tường và những đứa trẻ sống trong cùng một làng, học cùng một trường, có biết bao chuyện chung. Chúng nô đùa, cãi cọ rồi yêu thương nhau, cùng lớn lên theo năm tháng, trải qua bao sự kiện biến cố của cuộc đời.\r\nTác giả vẫn giữ cách kể chuyện bằng chính giọng trong sáng hồn nhiên của trẻ con. 81 chương ngắn là 81 câu chuyện hấp dẫn với nhiều chi tiết thú vị, cảm động, có những tình tiết bất ngờ, từ đó lộ rõ tính cách người. Cuốn sách, vì thế, có sức ám ảnh.', 'Tôi Thấy Hoa Vàng Trên Cỏ Non', '160', '/images/toithayhoavang.jpg', 65000, 95000, 128, '14 x 16 cm', 'active', 1),
(2, 'Đảo Mộng Mơ là một lát cắt đời sống của những đứa trẻ lên 10 giàu trí tưởng tượng như tất cả mọi đứa trẻ. Chúng mơ mộng, tưởng tượng, và tự làm \"hiện thực hóa\" những khao khát của mình.\r\n\r\nCâu chuyện bắt đầu từ một đống cát, và được diễn ra theo nhân vật tôi - cu Tin. Có một hòn đảo hoang, trên đảo có Chúa đảo, phu nhân Chúa đảo, và một chàng Thứ... Bảy. Hàng ngày vợ chồng Chúa đảo và Thứ Bảy vẫn phải đi học, nhưng sau giờ học là một thế giới khác, của đảo, của biển có cá mập, và rừng có thú dữ. Hấp dẫn, đầy quyến rũ, có cãi vã, có cai trị, có yêu thương, có ẩu đả, và cả...những nụ hôn!\r\n\r\nTuổi thơ trong Đảo Mộng mơ như trong những tác phẩm khác của Nguyễn Nhật Ánh, trong veo và ngọt ngào. Những muốn bé lại bằng cu Tin để được cười, được khóc, được làm Chúa đảo thích đọc sách và biết đánh lại lưu manh, bắt giam kẻ cắp. Để được hiểu rằng, đối với trẻ con, nhu cầu được tôn trọng đôi khi lớn hơn gấp bội so với nhu cầu được yêu thương.', 'Đảo Mộng Mơ', '144', '/images/daomongmo.jpg', 55000, 75000, 62, '18 x 23 cm', 'active', 1),
(3, 'Nếu chỉ còn một ngày để sống” tên sách gốc Everything, Everything là cuốn tiểu thuyết bán chạy số 1 của New York Times – đồng thời  được chuyển thể thành phim điện ảnh với sự góp mặt của hai diễn viên nổi tiếng là Amandla Stenberg trong vai Maddy và Nick Robinson trong vai Olly.  Ngay từ khi công chiếu,  bộ phim đã gây bão tại các phòng vé trên toàn thế giới kéo theo cơn sốt tìm đọc cuốn sách đặc biệt này đến từ các fan yêu thích bộ phim.\r\n\r\nMột chuyện tình cảm động được kể dưới ngòi bút tràn đầy những xúc cảm khác biệt đem đến cho người đọc những rung cảm chân thật chạm đến từng ngóc ngách trong trái tim. Một cuốn sách đã khiến hàng triệu độc giả phải khóc phải cười qua từng trang giấy.', 'Nếu Chỉ Còn Một Ngày', '149', '/images/neuchiconmotngay.jpg', 45000, 65000, 54, '13 x 19 cm', 'unactive', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book_author`
--

CREATE TABLE `book_author` (
  `book_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `book_author`
--

INSERT INTO `book_author` (`book_id`, `author_id`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`id`) VALUES
(1),
(2),
(3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `img` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `description`, `img`, `status`, `title`) VALUES
(1, 'Bao gồm các cuốn sách thuộc thể văn học', '/images/category/sachvanhoc.png', 'active', 'Văn Học'),
(2, 'Bao gồm các cuốn sách thuộc thể kinh tế', '/images/category/sachkinhte.png', 'active', 'Kinh Tế');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `detail_cart`
--

CREATE TABLE `detail_cart` (
  `book_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `detail_invoice`
--

CREATE TABLE `detail_invoice` (
  `book_id` int(11) NOT NULL,
  `invoice_id` int(11) NOT NULL,
  `price_buy` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `detail_order`
--

CREATE TABLE `detail_order` (
  `book_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `detail_order`
--

INSERT INTO `detail_order` (`book_id`, `order_id`, `quantity`, `unit_price`) VALUES
(1, 4, 5, 95000),
(2, 3, 3, 75000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `date_modified` date DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `address_details` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `date_order` date DEFAULT NULL,
  `status_order` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `address_details`, `date_order`, `status_order`, `total_price`, `user_id`, `phone`) VALUES
(3, 'quan 12 TPHCM', '2019-11-15', 'unresolved', 225000, 3, '0964211495'),
(4, 'Nguyen Van Linh, Quan 12', '2019-11-16', 'resolved', 475000, 2, '0365668303');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `persistent_logins`
--

CREATE TABLE `persistent_logins` (
  `username` varchar(64) COLLATE utf8_vietnamese_ci NOT NULL,
  `series` varchar(64) COLLATE utf8_vietnamese_ci NOT NULL,
  `token` varchar(64) COLLATE utf8_vietnamese_ci NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `persistent_logins`
--

INSERT INTO `persistent_logins` (`username`, `series`, `token`, `last_used`) VALUES
('minhduc1998', 'WZi6STdIa16Mc1AH+Zw9HA==', 'om4+auBJ1e+Lj/BvtczPrw==', '2019-11-16 22:56:08');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `full_name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `full_name`, `password`, `username`, `phone`) VALUES
(1, 'paulducnguyen98@gmail.com', 'duc minh', '$2a$10$IOA/uH7WhbWvqzyEsIonv.9LikXher4Ddbim4G3IEjtHKJwLV3Sy2', 'admin', '0974382517'),
(2, 'minh_duc_0@yahoo.com.vn', 'duc Minh', '$2a$10$SAa7nEHvEeppwL6/.8qyBev4IN6A4Dsov6nbmZ.A8eMMOm1Yob8Im', 'minhduc1998', '0365668303'),
(3, 'huy@gmail.com', 'tran huy', '$2a$10$PwG3y8UEYFPl5LzwWfD7quEqjaOAaKNMyXcAJUuZ512j4auqPWzKS', 'tranhuy', '0964211495');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 1),
(3, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKam9riv8y6rjwkua1gapdfew4j` (`category_id`);

--
-- Chỉ mục cho bảng `book_author`
--
ALTER TABLE `book_author`
  ADD KEY `FKbjqhp85wjv8vpr0beygh6jsgo` (`author_id`),
  ADD KEY `FKhwgu59n9o80xv75plf9ggj7xn` (`book_id`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `detail_cart`
--
ALTER TABLE `detail_cart`
  ADD PRIMARY KEY (`book_id`,`cart_id`),
  ADD KEY `FK7htydudqi1f8p95axt7thuk9t` (`cart_id`);

--
-- Chỉ mục cho bảng `detail_invoice`
--
ALTER TABLE `detail_invoice`
  ADD PRIMARY KEY (`book_id`,`invoice_id`),
  ADD KEY `FKmt3tg7l0sp2hj0hyrglrmlbxr` (`invoice_id`);

--
-- Chỉ mục cho bảng `detail_order`
--
ALTER TABLE `detail_order`
  ADD PRIMARY KEY (`book_id`,`order_id`),
  ADD KEY `FKjdf2te6dqo3a74nru9wknvcp8` (`order_id`);

--
-- Chỉ mục cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjunvl5maki3unqdvljk31kns3` (`user_id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`);

--
-- Chỉ mục cho bảng `persistent_logins`
--
ALTER TABLE `persistent_logins`
  ADD PRIMARY KEY (`series`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `author`
--
ALTER TABLE `author`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FKam9riv8y6rjwkua1gapdfew4j` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `book_author`
--
ALTER TABLE `book_author`
  ADD CONSTRAINT `FKbjqhp85wjv8vpr0beygh6jsgo` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  ADD CONSTRAINT `FKhwgu59n9o80xv75plf9ggj7xn` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FKi5tbwrjiam9ubfubf13u9jqbg` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `detail_cart`
--
ALTER TABLE `detail_cart`
  ADD CONSTRAINT `FK12q15rf6yyla6uwauv5jf2adw` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `FK7htydudqi1f8p95axt7thuk9t` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`);

--
-- Các ràng buộc cho bảng `detail_invoice`
--
ALTER TABLE `detail_invoice`
  ADD CONSTRAINT `FKbaqvdpjigeap5b4yv4vd4i12x` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `FKmt3tg7l0sp2hj0hyrglrmlbxr` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`);

--
-- Các ràng buộc cho bảng `detail_order`
--
ALTER TABLE `detail_order`
  ADD CONSTRAINT `FK246w2ei60pjv2fsq9pke8qr1j` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `FKjdf2te6dqo3a74nru9wknvcp8` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FKjunvl5maki3unqdvljk31kns3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
