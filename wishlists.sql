-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:3306
-- 產生時間： 2022-10-17 18:52:10
-- 伺服器版本： 5.7.24
-- PHP 版本： 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫: `test0928`
--

-- --------------------------------------------------------

--
-- 資料表結構 `wishlists`
--

CREATE TABLE `wishlists` (
  `wishlist_id` int(20) NOT NULL,
  `member_id` int(20) DEFAULT NULL,
  `item_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `item_id` int(20) DEFAULT NULL,
  `category_id` int(20) DEFAULT NULL,
  `item_description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `expect_price` int(20) DEFAULT NULL,
  `item_photo_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `item_likes` int(20) DEFAULT '0',
  `item_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `addwish_date` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `wishlists`
--

INSERT INTO `wishlists` (`wishlist_id`, `member_id`, `item_name`, `item_id`, `category_id`, `item_description`, `expect_price`, `item_photo_url`, `item_likes`, `item_comment`, `addwish_date`) VALUES
(1, 1, '魔法少年賈修第一集', NULL, 10301, '到處都找不到，跪求大大割愛', 5, 'https://i.imgur.com/8mAdIoV.jpg', 21, NULL, '2022-09-13'),
(2, 2, '倚天屠龍記全套', NULL, 20505, '該複習惹', 100, 'https://i.imgur.com/bLx8IGn.jpg', 9, NULL, '2022-09-14'),
(3, 3, 'Wii U主機', NULL, 40001, '', 800, 'https://i.imgur.com/tSJ0YEj.png', 1, NULL, '2022-09-14'),
(4, 1, '山中小屋', NULL, 30206, '有次在桌遊店跟朋友玩到~想再玩一次', 100, 'https://i.imgur.com/3HVOAsl.jpg', 6, NULL, '2022-09-15'),
(5, 3, '火鳳燎原1~10集', NULL, 10208, '好想進入八奇的領域', 50, 'https://i.imgur.com/yzRk4wN.png', 5, NULL, '2022-09-15'),
(6, 8, '黃金神威全套', NULL, 10302, 'ヒンナ~ヒンナ~', 500, 'https://i.imgur.com/cPO8Ywz.jpg', 37, NULL, '2022-09-17'),
(7, 2, '冰與火之歌第四集', NULL, 20601, '', 50, 'https://i.imgur.com/eyfjnwK.jpg', 7, NULL, '2022-09-17'),
(8, 3, '怪醫黑傑克第25集', NULL, 10317, '', 30, 'https://i.imgur.com/9yqBbSa.jpg', 3, NULL, '2022-09-19'),
(9, 1, '怪物全套', NULL, 10310, '', 300, 'https://i.imgur.com/7lVNLeq.jpg', 4, NULL, '2022-09-20'),
(10, 9, '卡卡頌', NULL, 30203, '', 100, 'https://i.imgur.com/PnhTEFT.jpg', 12, NULL, '2022-09-23'),
(11, 3, 'N3DS', NULL, 40002, '想複習一下神奇寶貝舊作', 500, 'https://i.imgur.com/8F6r0f2.png', 0, NULL, '2022-09-25'),
(12, 7, 'Sega Saturn', NULL, 40001, '', 500, 'https://i.imgur.com/Z9D0wjv.jpg', 0, NULL, '2022-09-26'),
(13, 9, '三國殺', NULL, 30104, '高中很常玩', 100, 'https://i.imgur.com/Le6Bt4l.jpg', 2, NULL, '2022-09-28'),
(14, 3, '鋼之煉金術師完全版全套', NULL, 10302, '', 500, 'https://i.imgur.com/7SKprsk.png', 15, NULL, '2022-09-30'),
(15, 2, 'GameBoy Color', NULL, 40002, '童年的回憶QQ有沒有大大願意割愛', 1200, 'https://i.imgur.com/CEm2xVu.jpg', 0, NULL, '2022-09-30'),
(16, 8, '龍紋身的女孩', NULL, 20707, '電影好看', 150, 'https://i.imgur.com/n5xCsRG.png', 10, NULL, '2022-10-02'),
(17, 3, '火影忍者博人傳第一集', NULL, 10301, '想看一下有多糞，感謝大家', 20, 'https://i.imgur.com/Clcv386.png', 0, NULL, '2022-10-02'),
(18, 7, '進擊的巨人全套', NULL, 10301, '阿爾敏我婆', 600, 'https://i.imgur.com/s0i88yl.png', 0, NULL, '2022-10-02'),
(19, 8, '沒有色彩的多崎作和他的巡禮之年', NULL, 20304, '', 50, 'https://i.imgur.com/PgLyx0e.jpg', 10, NULL, '2022-10-03'),
(20, 9, '風聲', NULL, 30104, '', 100, 'https://i.imgur.com/o19JOGR.jpg', 6, NULL, '2022-10-05'),
(21, 9, '哈利波特全套', NULL, 20701, '速速前！', 300, 'https://i.imgur.com/0JWB0Cc.jpg', 0, NULL, '2022-10-07'),
(22, 7, '魔戒三部曲', NULL, 10703, NULL, 80, 'https://i.imgur.com/Ao4h1oL.jpg', 0, NULL, '2022-10-07'),
(23, 3, '灌籃高手全套', NULL, 10306, '教練，我想看漫畫', 200, 'https://i.imgur.com/2axVO3A.jpg', 0, NULL, '2022-10-09'),
(24, 7, '幽遊白書全套', NULL, 10302, NULL, 250, 'https://i.imgur.com/JVwOge3.jpg', 0, NULL, '2022-10-11'),
(25, 1, '東京喰種1~10集', NULL, 10302, NULL, 50, 'https://i.imgur.com/ChdQt5e.jpg', 0, NULL, '2022-10-12'),
(26, 2, '遊戲王全套', NULL, 10305, '高橋老師QQ', 500, 'https://i.imgur.com/418RI1L.jpg', 0, NULL, '2022-10-14'),
(27, 2, '火影忍者疾風傳全套', NULL, 10301, '我不要博人傳QQ', 450, 'https://i.imgur.com/Vay06wh.jpg', 0, NULL, '2022-10-17'),
(28, 8, '最終進化少年全套', NULL, 10302, NULL, 210, 'https://i.imgur.com/J8HCHgM.jpg', 0, NULL, '2022-10-18'),
(29, 1, '結界師全套', NULL, 10301, NULL, 150, 'https://i.imgur.com/BIWgP7o.png', 0, NULL, '2022-10-18'),
(30, 8, '窮神全套', NULL, 10318, NULL, 60, 'https://i.imgur.com/5wE6qv3.png', 0, NULL, '2022-10-18'),
(31, 8, '流浪神差全套', NULL, NULL, NULL, 65, 'https://i.imgur.com/chB9fzi.png', 0, NULL, '2022-10-19'),
(32, 8, '獵人全套', NULL, 10302, '要復刊了趕快複習', 200, 'https://i.imgur.com/Y2LsHnU.png', 0, NULL, '2022-10-19');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `wishlists`
--
ALTER TABLE `wishlists`
  ADD PRIMARY KEY (`wishlist_id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `member_id` (`member_id`),
  ADD KEY `item_id` (`item_id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `wishlists`
--
ALTER TABLE `wishlists`
  MODIFY `wishlist_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `wishlists`
--
ALTER TABLE `wishlists`
  ADD CONSTRAINT `wishlists_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `item_categories` (`category_id`),
  ADD CONSTRAINT `wishlists_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  ADD CONSTRAINT `wishlists_ibfk_3` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
