-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:3306
-- 產生時間： 
-- 伺服器版本： 5.7.24
-- PHP 版本： 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `test0928`
--

-- --------------------------------------------------------

--
-- 資料表結構 `cart`
--

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL,
  `member_id` int(11) DEFAULT NULL,
  `items_id` int(11) DEFAULT NULL,
  `owner_account` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `cart`
--

INSERT INTO `cart` (`cart_id`, `member_id`, `items_id`, `owner_account`) VALUES
(1, 2, 3, 'brad0101');

-- --------------------------------------------------------

--
-- 資料表結構 `chatrooms`
--

CREATE TABLE `chatrooms` (
  `chatroom_id` int(20) NOT NULL,
  `chatroom_member_id1` int(20) DEFAULT NULL,
  `chatroom_member_id2` int(20) DEFAULT NULL,
  `chatroom_create_time` varchar(50) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `chatrooms`
--

INSERT INTO `chatrooms` (`chatroom_id`, `chatroom_member_id1`, `chatroom_member_id2`, `chatroom_create_time`) VALUES
(1, 1, 3, '2022/09/20-08:10:56'),
(2, 1, 4, '2022/09/22-17:34:48');

-- --------------------------------------------------------

--
-- 資料表結構 `items`
--

CREATE TABLE `items` (
  `item_id` int(20) NOT NULL,
  `member_id` int(20) DEFAULT NULL,
  `item_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `item_description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `item_state` varchar(20) COLLATE utf8_bin DEFAULT '準備上架',
  `item_date` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `item_price` int(20) DEFAULT NULL,
  `item_times_rent_out` int(20) DEFAULT NULL,
  `category_id` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `items`
--

INSERT INTO `items` (`item_id`, `member_id`, `item_name`, `item_description`, `item_state`, `item_date`, `item_price`, `item_times_rent_out`, `category_id`) VALUES
(1, 2, '烏龍院：20年典藏紀念版', '★國立編譯館 91 年度優良連環圖畫獎佳作 ★\r\n★ 行政院新聞局 2002 年度小太陽獎：漫畫類 ★\r\n\r\n曾經風靡全國，在漫畫史上有著傲人成績的烏龍院，回來了！時報出版特別為漫畫迷們出版這本全新的烏龍院 20 年四格精選。不僅老烏龍院迷可重新回味他的魅力，新生代更可感受烏龍院的搞笑本領喔。不可錯過！', '準備上架', '2022-09-21', 9, 1, 10123),
(2, 3, '草莓百分百全套', '國中三年級的學生真中淳平，有一天在學校的屋頂上，偶然目擊了一名美少女的「草莓內褲」。於是真中便不斷的尋找那位美少女是誰。那個美少女的名字是東城綾，然而真中卻誤以為是學生偶像西野司，從此便開始了他們不可思議的關係。', '租借中', '2022-09-22', 80, 2, 10313),
(3, 1, '任天堂紅白機', '保存良好，附初代馬力歐、薩爾達傳說等遊戲片', '準備上架', '2022-09-30', 2000, 0, 40001),
(4, 3, '國王的請願', '作為皇室中的一員，玩家必須暗自充實自己的勢力，爭取王室成員的支持，並且將國王拉攏到支持自己的一邊。 在持續改變的宮廷中，玩家最有用的力量，就是骰子。每次用三顆骰子開始。每個回合，玩家擲骰，將至少一顆骰子移到旁邊，並且丟擲剩下的骰子直到他將所有骰子都移到旁邊。這些被移到旁邊的骰子，如果符合正確的組合，玩家就可以獲得一張角色卡，為玩家帶來另一顆骰子或者特殊的力量。第一個將七顆骰子擲出同樣數字的玩家就會獲得國王的支持，甚者，獲得勝利。國王(以及皇后)的支持會在最後擲骰時改變，以便決定勝利者。\r\n', '已下架', '2022-09-22', 50, 1, 30103),
(5, 1, '貧妻出頭天', '她頭一天進王府，他就命她去廚房偷饅頭，\r\n基于害怕，她不得不硬著頭皮上了，還把自己的那顆也給了他，\r\n此後他像是纏她逗她上了癮似的，大半夜溜進她房里硬要陪睡，\r\n時不時對她毛手毛腳，摟抱親吻從沒少過，總讓她又羞又惱，\r\n本以為他被派去戍邊，她能換得好一段平靜的日子，怎料才三年就回來了，\r\n不過經過這番磨練的他，更顯挺拔不凡，\r\n當然，欺負她的本領也更高超了，居然揚言要讓她屬于他？！\r\n其實她哪里不懂他的明示暗示，又何嘗不想追求真正的幸福，...', '已刪除', '2022-09-21', 10, 0, 20509),
(10, 2, 'ｐｅｋｏ', 'ｐｅｋｏｐｅｋｏ', NULL, '2022-10-20', 111, NULL, NULL),
(11, 2, 'ｐｅｋｏ', 'ｐｅｋｏｐｅｋｏ', '準備上架', '2022-10-20', 111, NULL, NULL),
(12, 2, '', '', '準備上架', '', NULL, NULL, NULL),
(13, 2, '', '', '準備上架', '', NULL, NULL, NULL),
(14, 2, '', '', '準備上架', '', NULL, NULL, NULL),
(15, 2, '12312312', '123123123', '準備上架', '2022-09-15', 123123, NULL, NULL),
(16, 2, '12312312', '123123123', '準備上架', '2022-09-15', 123123, NULL, 10201),
(17, 2, '12312312', '123123123', '準備上架', '2022-09-15', 123123, NULL, 10201);

-- --------------------------------------------------------

--
-- 資料表結構 `item_categories`
--

CREATE TABLE `item_categories` (
  `category_id` int(20) NOT NULL,
  `category_name` varchar(50) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `item_categories`
--

INSERT INTO `item_categories` (`category_id`, `category_name`) VALUES
(10101, '台灣熱血漫畫'),
(10102, '台灣冒險漫畫'),
(10103, '台灣奇幻漫畫'),
(10104, '台灣科幻漫畫'),
(10105, '台灣競技漫畫'),
(10106, '台灣運動漫畫'),
(10107, '台灣校園漫畫'),
(10108, '台灣歷史漫畫'),
(10109, '台灣推理漫畫'),
(10110, '台灣懸疑漫畫'),
(10111, '台灣恐怖漫畫'),
(10112, '台灣美食漫畫'),
(10113, '台灣戀愛漫畫'),
(10114, '台灣日常漫畫'),
(10115, '台灣音樂漫畫'),
(10116, '台灣治癒漫畫'),
(10117, '台灣社會漫畫'),
(10118, '台灣搞笑漫畫'),
(10119, '台灣萌系漫畫'),
(10120, '台灣後宮漫畫'),
(10121, '台灣BL漫畫'),
(10122, '台灣GL漫畫'),
(10123, '台灣四格漫畫'),
(10124, '台灣武俠漫畫'),
(10125, '台灣格鬥漫畫'),
(10126, '台灣機戰漫畫'),
(10127, '台灣同人漫畫'),
(10128, '台灣溫馨漫畫'),
(10129, '台灣獵奇漫畫'),
(10130, '台灣其他漫畫'),
(10201, '香港熱血漫畫'),
(10202, '香港冒險漫畫'),
(10203, '香港奇幻漫畫'),
(10204, '香港科幻漫畫'),
(10205, '香港競技漫畫'),
(10206, '香港運動漫畫'),
(10207, '香港校園漫畫'),
(10208, '香港歷史漫畫'),
(10209, '香港推理漫畫'),
(10210, '香港懸疑漫畫'),
(10211, '香港恐怖漫畫'),
(10212, '香港美食漫畫'),
(10213, '香港戀愛漫畫'),
(10214, '香港日常漫畫'),
(10215, '香港音樂漫畫'),
(10216, '香港治癒漫畫'),
(10217, '香港社會漫畫'),
(10218, '香港搞笑漫畫'),
(10219, '香港萌系漫畫'),
(10220, '香港後宮漫畫'),
(10221, '香港BL漫畫'),
(10222, '香港GL漫畫'),
(10223, '香港四格漫畫'),
(10224, '香港武俠漫畫'),
(10225, '香港格鬥漫畫'),
(10226, '香港機戰漫畫'),
(10227, '香港同人漫畫'),
(10228, '香港溫馨漫畫'),
(10229, '香港獵奇漫畫'),
(10230, '香港其他漫畫'),
(10301, '日本熱血漫畫'),
(10302, '日本冒險漫畫'),
(10303, '日本奇幻漫畫'),
(10304, '日本科幻漫畫'),
(10305, '日本競技漫畫'),
(10306, '日本運動漫畫'),
(10307, '日本校園漫畫'),
(10308, '日本歷史漫畫'),
(10309, '日本推理漫畫'),
(10310, '日本懸疑漫畫'),
(10311, '日本恐怖漫畫'),
(10312, '日本美食漫畫'),
(10313, '日本戀愛漫畫'),
(10314, '日本日常漫畫'),
(10315, '日本音樂漫畫'),
(10316, '日本治癒漫畫'),
(10317, '日本社會漫畫'),
(10318, '日本搞笑漫畫'),
(10319, '日本萌系漫畫'),
(10320, '日本後宮漫畫'),
(10321, '日本BL漫畫'),
(10322, '日本GL漫畫'),
(10323, '日本四格漫畫'),
(10324, '日本武俠漫畫'),
(10325, '日本格鬥漫畫'),
(10326, '日本機戰漫畫'),
(10327, '日本同人漫畫'),
(10328, '日本溫馨漫畫'),
(10329, '日本獵奇漫畫'),
(10330, '日本其他漫畫'),
(10401, '韓國熱血漫畫'),
(10402, '韓國冒險漫畫'),
(10403, '韓國奇幻漫畫'),
(10404, '韓國科幻漫畫'),
(10405, '韓國競技漫畫'),
(10406, '韓國運動漫畫'),
(10407, '韓國校園漫畫'),
(10408, '韓國歷史漫畫'),
(10409, '韓國推理漫畫'),
(10410, '韓國懸疑漫畫'),
(10411, '韓國恐怖漫畫'),
(10412, '韓國美食漫畫'),
(10413, '韓國戀愛漫畫'),
(10414, '韓國日常漫畫'),
(10415, '韓國音樂漫畫'),
(10416, '韓國治癒漫畫'),
(10417, '韓國社會漫畫'),
(10418, '韓國搞笑漫畫'),
(10419, '韓國萌系漫畫'),
(10420, '韓國後宮漫畫'),
(10421, '韓國BL漫畫'),
(10422, '韓國GL漫畫'),
(10423, '韓國四格漫畫'),
(10424, '韓國武俠漫畫'),
(10425, '韓國格鬥漫畫'),
(10426, '韓國機戰漫畫'),
(10427, '韓國同人漫畫'),
(10428, '韓國溫馨漫畫'),
(10429, '韓國獵奇漫畫'),
(10430, '韓國其他漫畫'),
(10501, '中國熱血漫畫'),
(10502, '中國冒險漫畫'),
(10503, '中國奇幻漫畫'),
(10504, '中國科幻漫畫'),
(10505, '中國競技漫畫'),
(10506, '中國運動漫畫'),
(10507, '中國校園漫畫'),
(10508, '中國歷史漫畫'),
(10509, '中國推理漫畫'),
(10510, '中國懸疑漫畫'),
(10511, '中國恐怖漫畫'),
(10512, '中國美食漫畫'),
(10513, '中國戀愛漫畫'),
(10514, '中國日常漫畫'),
(10515, '中國音樂漫畫'),
(10516, '中國治癒漫畫'),
(10517, '中國社會漫畫'),
(10518, '中國搞笑漫畫'),
(10519, '中國萌系漫畫'),
(10520, '中國後宮漫畫'),
(10521, '中國BL漫畫'),
(10522, '中國GL漫畫'),
(10523, '中國四格漫畫'),
(10524, '中國武俠漫畫'),
(10525, '中國格鬥漫畫'),
(10526, '中國機戰漫畫'),
(10527, '中國同人漫畫'),
(10528, '中國溫馨漫畫'),
(10529, '中國獵奇漫畫'),
(10530, '中國其他漫畫'),
(10601, '美國熱血漫畫'),
(10602, '美國冒險漫畫'),
(10603, '美國奇幻漫畫'),
(10604, '美國科幻漫畫'),
(10605, '美國競技漫畫'),
(10606, '美國運動漫畫'),
(10607, '美國校園漫畫'),
(10608, '美國歷史漫畫'),
(10609, '美國推理漫畫'),
(10610, '美國懸疑漫畫'),
(10611, '美國恐怖漫畫'),
(10612, '美國美食漫畫'),
(10613, '美國戀愛漫畫'),
(10614, '美國日常漫畫'),
(10615, '美國音樂漫畫'),
(10616, '美國治癒漫畫'),
(10617, '美國社會漫畫'),
(10618, '美國搞笑漫畫'),
(10619, '美國萌系漫畫'),
(10620, '美國後宮漫畫'),
(10621, '美國BL漫畫'),
(10622, '美國GL漫畫'),
(10623, '美國四格漫畫'),
(10624, '美國武俠漫畫'),
(10625, '美國格鬥漫畫'),
(10626, '美國機戰漫畫'),
(10627, '美國同人漫畫'),
(10628, '美國溫馨漫畫'),
(10629, '美國獵奇漫畫'),
(10630, '美國其他漫畫'),
(10701, '歐洲熱血漫畫'),
(10702, '歐洲冒險漫畫'),
(10703, '歐洲奇幻漫畫'),
(10704, '歐洲科幻漫畫'),
(10705, '歐洲競技漫畫'),
(10706, '歐洲運動漫畫'),
(10707, '歐洲校園漫畫'),
(10708, '歐洲歷史漫畫'),
(10709, '歐洲推理漫畫'),
(10710, '歐洲懸疑漫畫'),
(10711, '歐洲恐怖漫畫'),
(10712, '歐洲美食漫畫'),
(10713, '歐洲戀愛漫畫'),
(10714, '歐洲日常漫畫'),
(10715, '歐洲音樂漫畫'),
(10716, '歐洲治癒漫畫'),
(10717, '歐洲社會漫畫'),
(10718, '歐洲搞笑漫畫'),
(10719, '歐洲萌系漫畫'),
(10720, '歐洲後宮漫畫'),
(10721, '歐洲BL漫畫'),
(10722, '歐洲GL漫畫'),
(10723, '歐洲四格漫畫'),
(10724, '歐洲武俠漫畫'),
(10725, '歐洲格鬥漫畫'),
(10726, '歐洲機戰漫畫'),
(10727, '歐洲同人漫畫'),
(10728, '歐洲溫馨漫畫'),
(10729, '歐洲獵奇漫畫'),
(10730, '歐洲其他漫畫'),
(10801, '其他熱血漫畫'),
(10802, '其他冒險漫畫'),
(10803, '其他奇幻漫畫'),
(10804, '其他科幻漫畫'),
(10805, '其他競技漫畫'),
(10806, '其他運動漫畫'),
(10807, '其他校園漫畫'),
(10808, '其他歷史漫畫'),
(10809, '其他推理漫畫'),
(10810, '其他懸疑漫畫'),
(10811, '其他恐怖漫畫'),
(10812, '其他美食漫畫'),
(10813, '其他戀愛漫畫'),
(10814, '其他日常漫畫'),
(10815, '其他音樂漫畫'),
(10816, '其他治癒漫畫'),
(10817, '其他社會漫畫'),
(10818, '其他搞笑漫畫'),
(10819, '其他萌系漫畫'),
(10820, '其他後宮漫畫'),
(10821, '其他BL漫畫'),
(10822, '其他GL漫畫'),
(10823, '其他四格漫畫'),
(10824, '其他武俠漫畫'),
(10825, '其他格鬥漫畫'),
(10826, '其他機戰漫畫'),
(10827, '其他同人漫畫'),
(10828, '其他溫馨漫畫'),
(10829, '其他獵奇漫畫'),
(10830, '其他其他漫畫'),
(20101, '台灣奇幻小說'),
(20102, '台灣科幻小說'),
(20103, '台灣玄幻小說'),
(20104, '台灣歷史小說'),
(20105, '台灣武俠小說'),
(20106, '台灣推理小說'),
(20107, '台灣懸疑小說'),
(20108, '台灣恐怖小說'),
(20109, '台灣言情小說'),
(20110, '台灣穿越小說'),
(20111, '台灣古典小說'),
(20112, '台灣輕小說'),
(20113, '台灣同人小說'),
(20114, '台灣純文學'),
(20115, '台灣其他小說'),
(20201, '香港奇幻小說'),
(20202, '香港科幻小說'),
(20203, '香港玄幻小說'),
(20204, '香港歷史小說'),
(20205, '香港武俠小說'),
(20206, '香港推理小說'),
(20207, '香港懸疑小說'),
(20208, '香港恐怖小說'),
(20209, '香港言情小說'),
(20210, '香港穿越小說'),
(20211, '香港古典小說'),
(20212, '香港輕小說'),
(20213, '香港同人小說'),
(20214, '香港純文學'),
(20215, '香港其他小說'),
(20301, '日本奇幻小說'),
(20302, '日本科幻小說'),
(20303, '日本玄幻小說'),
(20304, '日本歷史小說'),
(20305, '日本武俠小說'),
(20306, '日本推理小說'),
(20307, '日本懸疑小說'),
(20308, '日本恐怖小說'),
(20309, '日本言情小說'),
(20310, '日本穿越小說'),
(20311, '日本古典小說'),
(20312, '日本輕小說'),
(20313, '日本同人小說'),
(20314, '日本純文學'),
(20315, '日本其他小說'),
(20401, '韓國奇幻小說'),
(20402, '韓國科幻小說'),
(20403, '韓國玄幻小說'),
(20404, '韓國歷史小說'),
(20405, '韓國武俠小說'),
(20406, '韓國推理小說'),
(20407, '韓國懸疑小說'),
(20408, '韓國恐怖小說'),
(20409, '韓國言情小說'),
(20410, '韓國穿越小說'),
(20411, '韓國古典小說'),
(20412, '韓國輕小說'),
(20413, '韓國同人小說'),
(20414, '韓國純文學'),
(20415, '韓國其他小說'),
(20501, '中國奇幻小說'),
(20502, '中國科幻小說'),
(20503, '中國玄幻小說'),
(20504, '中國歷史小說'),
(20505, '中國武俠小說'),
(20506, '中國推理小說'),
(20507, '中國懸疑小說'),
(20508, '中國恐怖小說'),
(20509, '中國言情小說'),
(20510, '中國穿越小說'),
(20511, '中國古典小說'),
(20512, '中國輕小說'),
(20513, '中國同人小說'),
(20514, '中國純文學'),
(20515, '中國其他小說'),
(20601, '美國奇幻小說'),
(20602, '美國科幻小說'),
(20603, '美國玄幻小說'),
(20604, '美國歷史小說'),
(20605, '美國武俠小說'),
(20606, '美國推理小說'),
(20607, '美國懸疑小說'),
(20608, '美國恐怖小說'),
(20609, '美國言情小說'),
(20610, '美國穿越小說'),
(20611, '美國古典小說'),
(20612, '美國輕小說'),
(20613, '美國同人小說'),
(20614, '美國純文學'),
(20615, '美國其他小說'),
(20701, '歐洲奇幻小說'),
(20702, '歐洲科幻小說'),
(20703, '歐洲玄幻小說'),
(20704, '歐洲歷史小說'),
(20705, '歐洲武俠小說'),
(20706, '歐洲推理小說'),
(20707, '歐洲懸疑小說'),
(20708, '歐洲恐怖小說'),
(20709, '歐洲言情小說'),
(20710, '歐洲穿越小說'),
(20711, '歐洲古典小說'),
(20712, '歐洲輕小說'),
(20713, '歐洲同人小說'),
(20714, '歐洲純文學'),
(20715, '歐洲其他小說'),
(20801, '其他奇幻小說'),
(20802, '其他科幻小說'),
(20803, '其他玄幻小說'),
(20804, '其他歷史小說'),
(20805, '其他武俠小說'),
(20806, '其他推理小說'),
(20807, '其他懸疑小說'),
(20808, '其他恐怖小說'),
(20809, '其他言情小說'),
(20810, '其他穿越小說'),
(20811, '其他古典小說'),
(20812, '其他輕小說'),
(20813, '其他同人小說'),
(20814, '其他純文學'),
(20815, '其他其他小說'),
(30101, '小型兒童桌遊'),
(30102, '小型家庭桌遊'),
(30103, '小型派對桌遊'),
(30104, '小型陣營桌遊'),
(30105, '小型策略桌遊'),
(30106, '小型主題桌遊'),
(30107, '小型抽象桌遊'),
(30108, '小型集換桌遊'),
(30201, '中型兒童桌遊'),
(30202, '中型家庭桌遊'),
(30203, '中型派對桌遊'),
(30204, '中型陣營桌遊'),
(30205, '中型策略桌遊'),
(30206, '中型主題桌遊'),
(30207, '中型抽象桌遊'),
(30208, '中型集換桌遊'),
(30301, '大型兒童桌遊'),
(30302, '大型家庭桌遊'),
(30303, '大型派對桌遊'),
(30304, '大型陣營桌遊'),
(30305, '大型策略桌遊'),
(30306, '大型主題桌遊'),
(30307, '大型抽象桌遊'),
(30308, '大型集換桌遊'),
(40001, '家用主機'),
(40002, '掌上主機'),
(40003, '大型機台');

-- --------------------------------------------------------

--
-- 資料表結構 `item_picture`
--

CREATE TABLE `item_picture` (
  `picture_id` int(20) NOT NULL,
  `item_id` int(20) DEFAULT NULL,
  `picture_url` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `item_picture`
--

INSERT INTO `item_picture` (`picture_id`, `item_id`, `picture_url`) VALUES
(1, 3, 'https://imgur.com/wDpizSM'),
(2, 4, 'https://imgur.com/UEZc3C3'),
(3, 5, 'https://imgur.com/wy01BcG'),
(4, 1, 'https://imgur.com/Kw5pewS'),
(5, 2, 'https://imgur.com/Kn6bG9D'),
(10, 10, '20221004222137_'),
(11, 11, '20221004222436_'),
(12, 12, '20221005213225_'),
(13, 13, '20221005213307_'),
(14, 14, '20221005213321_'),
(15, 15, '20221005215423_'),
(16, 16, '20221005215715_'),
(17, 17, '20221005215827_');

-- --------------------------------------------------------

--
-- 資料表結構 `item_tags`
--

CREATE TABLE `item_tags` (
  `tag_id` int(20) NOT NULL,
  `item_id` int(20) DEFAULT NULL,
  `item_tag` varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `item_tags`
--

INSERT INTO `item_tags` (`tag_id`, `item_id`, `item_tag`) VALUES
(1, 1, '搞笑'),
(2, 1, '武術'),
(3, 1, '古代'),
(4, 2, '校園'),
(5, 2, '青春'),
(6, 5, '古裝'),
(7, 4, '骰子'),
(8, 4, '策略'),
(9, 4, '多人'),
(10, 3, '舊型機種'),
(11, 3, '任天堂');

-- --------------------------------------------------------

--
-- 資料表結構 `members`
--

CREATE TABLE `members` (
  `member_id` int(255) NOT NULL,
  `member_account` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_phonenumber` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_birthday` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_region` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_rate` double DEFAULT NULL,
  `member_icon` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_nickname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_introduction` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_rank` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_ban_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_token` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_balance` int(255) DEFAULT NULL,
  `account_create_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_login_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `seven11_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ok_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `family_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `bank_account` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `members`
--

INSERT INTO `members` (`member_id`, `member_account`, `member_password`, `member_email`, `member_phonenumber`, `member_birthday`, `member_region`, `member_rate`, `member_icon`, `member_nickname`, `member_introduction`, `member_rank`, `member_ban_date`, `member_token`, `member_balance`, `account_create_time`, `last_login_time`, `seven11_address`, `ok_address`, `family_address`, `bank_account`) VALUES
(1, 'brad0101', '12345678', 'brad@brad.tw', '912345678', NULL, NULL, NULL, NULL, '布萊德', NULL, '一般', NULL, '1', NULL, '2022/09/22-22:35:04', '2022-10-09 02:39:25', '7-ELEVEN新墩門市', 'OK台中福星店', '全家台中樂活店', '0111234567891'),
(2, 'shitter', '98765432', 'GG@gmail.com', '965789456', '1988-09-04', NULL, 5, NULL, '雪特', '安安', '停權', NULL, '3', NULL, '2022/09/21-16:08:17', '2022/09/23-21:55:06', '7-ELEVEn 精明門市 便利商店', 'OK便利商店 台中華美店', '全家便利商店 台中大英店', '123123213'),
(3, 'wolfgang', 'passw0rd', 'ilovecosplay@outlook.com', '988756498', NULL, '台中', 4.7, NULL, '曼尼', NULL, '一般', NULL, '2', NULL, '2022/09/23-09:15:39', '2022/09/23-15:18:42', '7-ELEVEN隆安門市 ', 'OK台中太原店', '全家台中精誠店', '01347685210245175'),
(4, 'admin01', 'admin01', 'admin01@hotmail.com', NULL, NULL, NULL, NULL, NULL, '布萊', '014123456789123', '管理者', NULL, '1', NULL, '2022/09/15-00:00:00', '2022-10-09 04:04:59', '7-ELEVEn 精明門市 便利商店', 'OK便利商店 台中華美店', '全家便利商店 台中福揚店', ''),
(5, '123', '$2a$10$Dk7eZty8LuYwJodzXiCvmO9SAaGXGEsAXeKY9JtiKu6UERuQ8PFSa', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-10-01 15:19:27', NULL, NULL, NULL, NULL, NULL),
(6, '12345', '$2a$10$89yVbT.G6kWXr.AmY4Tfb.IX7W.RvRgmSQ2nL24URCPmktTM.4BIi', '12345', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-10-02 01:28:00', '2022-10-02 01:35:38', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `member_login_list`
--

CREATE TABLE `member_login_list` (
  `login_list_id` int(20) NOT NULL,
  `member_id` int(20) DEFAULT NULL,
  `member_login_time` varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `member_login_list`
--

INSERT INTO `member_login_list` (`login_list_id`, `member_id`, `member_login_time`) VALUES
(1, 4, '2022-09-23 08:19:08'),
(2, 4, '2022-09-23 17:19:15'),
(3, 1, '2022-09-22 10:53:08'),
(4, 2, '2022-09-21 23:32:43'),
(5, 3, '2022-09-21 01:07:14'),
(6, 2, '2022-09-23 15:35:14');

-- --------------------------------------------------------

--
-- 資料表結構 `member_payment_detail`
--

CREATE TABLE `member_payment_detail` (
  `payment_detail_id` int(20) NOT NULL,
  `member_id` int(20) DEFAULT NULL,
  `member_firstname` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `member_lastname` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `member_address` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `creditcard_number` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `creditcard_date` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `creditcard_security_code` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `member_payment_detail`
--

INSERT INTO `member_payment_detail` (`payment_detail_id`, `member_id`, `member_firstname`, `member_lastname`, `member_address`, `creditcard_number`, `creditcard_date`, `creditcard_security_code`) VALUES
(1, 1, 'brad', 'chao', '台中市北屯區松竹五路二段283號', '4561-7894-4561-4585', '02/27', 654),
(2, 2, 'ching', 'hsu', NULL, '5123-4561-7564-0135', '06/25', 123),
(3, 3, 'money', 'money', '台中市南屯區公益路二段51號', '3246-8456-1234-6543', '09/26', 347);

-- --------------------------------------------------------

--
-- 資料表結構 `messages`
--

CREATE TABLE `messages` (
  `message_id` int(20) NOT NULL,
  `chatroom_id` int(20) DEFAULT NULL,
  `member_id` int(20) DEFAULT NULL,
  `message_content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `message_sent_time` varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `messages`
--

INSERT INTO `messages` (`message_id`, `chatroom_id`, `member_id`, `message_content`, `message_sent_time`) VALUES
(1, 1, 3, '為什麼這班上課一直睡覺', '2022-09-20 08:10:56'),
(2, 1, 3, '你上的時候有這樣嗎?', '2022-09-20 08:11:12'),
(3, 2, 1, '為什麼我的商品被下架了', '2022-09-22 17:34:48'),
(4, 2, 1, '?????????', '2022-09-22 17:34:51');

-- --------------------------------------------------------

--
-- 資料表結構 `order_details`
--

CREATE TABLE `order_details` (
  `order_detail_id` int(20) NOT NULL,
  `order_id` int(255) DEFAULT NULL,
  `item_id` int(255) DEFAULT NULL,
  `item_receive_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `deliver_way` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `deliver_place` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `item_return_date` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `return_way` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `return_place` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `item_price` int(255) DEFAULT NULL,
  `is_violate` tinyint(1) DEFAULT NULL,
  `violate_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `order_detail_state` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '等待接受',
  `order_payment` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `order_details`
--

INSERT INTO `order_details` (`order_detail_id`, `order_id`, `item_id`, `item_receive_date`, `deliver_way`, `deliver_place`, `item_return_date`, `return_way`, `return_place`, `item_price`, `is_violate`, `violate_type`, `order_detail_state`, `order_payment`) VALUES
(1, 1, 1, '20220925', '全家店到店', '全家台中育德店', '20221001', '全家店到店', '全家台中大英店', 35, 2, NULL, '已接受', ''),
(2, 1, 2, '20220925', '全家店到店', '全家台中育德店', '20221001', '全家店到店', '全家台中精誠店', 560, 2, NULL, '已接受', ''),
(3, 2, 2, '20221004', '7-11店到店', '7-ELEVEN 鄉林門市', '20221011', '7-11店到店', '7-ELEVEN 隆安門市', 560, 2, NULL, '等待接受', ''),
(4, 2, 4, '20221004', '7-11店到店', '7-ELEVEN 鄉林門市', '20221011', '7-11店到店', '7-ELEVEN 隆安門市', 350, 2, NULL, '等待接受', ''),
(17, 16, 2, '2022-10-12', NULL, 'OK便利商店 台中華美店', '2022-10-18', NULL, '全家台中精誠店', 80, NULL, NULL, '等待接受', '帳戶01347685210245175'),
(18, 17, 2, '', NULL, '全家便利商店 台中福揚店', '2022-10-26', NULL, 'OK台中太原店', 80, NULL, NULL, '等待接受', '帳戶01347685210245175'),
(19, 18, 2, '', NULL, '全家便利商店 台中福揚店', '2022-10-26', NULL, 'OK台中太原店', 80, NULL, NULL, '等待接受', '帳戶01347685210245175'),
(20, 19, 2, '2022-10-18', NULL, 'OK便利商店 台中華美店', '2022-10-29', NULL, 'OK台中太原店', 80, NULL, NULL, '等待接受', '帳戶01347685210245175');

-- --------------------------------------------------------

--
-- 資料表結構 `order_list`
--

CREATE TABLE `order_list` (
  `order_id` int(20) NOT NULL,
  `member_id` int(20) DEFAULT NULL,
  `order_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `order_item_number` int(20) DEFAULT NULL,
  `order_delivery_fee` int(20) DEFAULT NULL,
  `order_total_price` int(20) DEFAULT NULL,
  `order_payment` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `order_state` varchar(20) COLLATE utf8_bin DEFAULT '待確認'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `order_list`
--

INSERT INTO `order_list` (`order_id`, `member_id`, `order_date`, `order_item_number`, `order_delivery_fee`, `order_total_price`, `order_payment`, `order_state`) VALUES
(1, 1, '2022-09-23', 2, 120, 715, '線上刷卡', '已完成'),
(2, 2, '2022-09-23', 2, 120, 1030, '第三方支付', '租借中'),
(16, 4, '2022-10-09', NULL, NULL, 80, NULL, NULL),
(17, 4, '2022-10-09', NULL, NULL, 80, NULL, NULL),
(18, 4, '2022-10-09', NULL, NULL, 80, NULL, NULL),
(19, 4, '2022-10-09', NULL, NULL, 80, NULL, NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `rate_list`
--

CREATE TABLE `rate_list` (
  `rate_id` int(20) NOT NULL,
  `order_id` int(20) DEFAULT NULL,
  `rated_member_id` int(20) DEFAULT NULL,
  `rated_item_id` int(20) DEFAULT NULL,
  `rate_member_id` int(20) DEFAULT NULL,
  `rate_description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `rate_grade` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `rate_list`
--

INSERT INTO `rate_list` (`rate_id`, `order_id`, `rated_member_id`, `rated_item_id`, `rate_member_id`, `rate_description`, `rate_grade`) VALUES
(1, 1, 2, 1, 1, NULL, 5),
(2, 1, 3, 2, 1, NULL, 5),
(3, 2, 3, 2, 2, NULL, 5),
(4, 2, 3, 4, 2, '骰子少了幾顆', 4);

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
  `item_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 傾印資料表的資料 `wishlists`
--

INSERT INTO `wishlists` (`wishlist_id`, `member_id`, `item_name`, `item_id`, `category_id`, `item_description`, `expect_price`, `item_photo_url`, `item_likes`, `item_comment`) VALUES
(1, 1, '魔法少年賈修第一集', NULL, 10301, '到處都找不到，跪求大大割愛', 30, NULL, 21, NULL),
(2, 2, '倚天屠龍記全套', NULL, 20505, '該複習惹', 400, NULL, 9, NULL),
(3, 3, 'Wii U主機', NULL, 40001, '', 800, NULL, 1, NULL),
(4, 1, '山中小屋', NULL, 30206, '', 200, NULL, 6, NULL),
(5, 3, '火鳳燎原1~10集', NULL, 10208, '', 300, NULL, 5, NULL),
(6, 2, '黃金神威全套', NULL, 10302, '', 1200, NULL, 37, NULL),
(7, 2, '冰與火之歌第四集', NULL, 20601, '', 50, NULL, 7, NULL),
(8, 3, '怪醫黑傑克第25集', NULL, 10317, '', 30, NULL, 3, NULL),
(9, 1, '怪物全套', NULL, 10310, '', 800, NULL, 4, NULL),
(10, 1, '卡卡頌', NULL, 30203, '', 200, NULL, 12, NULL),
(11, 3, 'N3DS', NULL, 40002, '', 500, NULL, 0, NULL),
(12, 1, 'Sega Saturn', NULL, 40001, '', 500, NULL, 0, NULL),
(13, 2, '三國殺', NULL, 30104, '', 100, NULL, 2, NULL),
(14, 3, '鋼之煉金術師完全版全套', NULL, 10302, '', 600, NULL, 15, NULL),
(15, 2, 'GameBoy Color', NULL, 40002, '', 1200, NULL, 0, NULL),
(16, 2, '龍紋身的女孩', NULL, 20707, '', 150, NULL, 10, NULL),
(17, 3, '火影忍者博人傳第一集', NULL, 10301, '想看一下有多糞，感謝大家', 20, NULL, 0, NULL),
(18, 2, '進擊的巨人全套', NULL, 10301, '阿爾敏我婆', NULL, NULL, 0, NULL),
(19, 2, '沒有色彩的多崎作和他的巡禮之年', NULL, 20304, '', NULL, NULL, 7, NULL),
(20, 1, '風聲', NULL, 30104, '', NULL, NULL, 0, NULL);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_id`);

--
-- 資料表索引 `chatrooms`
--
ALTER TABLE `chatrooms`
  ADD PRIMARY KEY (`chatroom_id`),
  ADD KEY `chatroom_member_id` (`chatroom_member_id1`),
  ADD KEY `chatroom_member_id2` (`chatroom_member_id2`);

--
-- 資料表索引 `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `rent_out_list_ibfk_1` (`category_id`),
  ADD KEY `memberID` (`member_id`),
  ADD KEY `item_state` (`item_state`);

--
-- 資料表索引 `item_categories`
--
ALTER TABLE `item_categories`
  ADD PRIMARY KEY (`category_id`);

--
-- 資料表索引 `item_picture`
--
ALTER TABLE `item_picture`
  ADD PRIMARY KEY (`picture_id`),
  ADD KEY `item_id` (`item_id`);

--
-- 資料表索引 `item_tags`
--
ALTER TABLE `item_tags`
  ADD PRIMARY KEY (`tag_id`),
  ADD KEY `item_id` (`item_id`);

--
-- 資料表索引 `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`member_id`),
  ADD UNIQUE KEY `member_account` (`member_account`),
  ADD UNIQUE KEY `member_email` (`member_email`),
  ADD UNIQUE KEY `member_password` (`member_password`),
  ADD KEY `member_token` (`member_token`),
  ADD KEY `member_rank` (`member_rank`);

--
-- 資料表索引 `member_login_list`
--
ALTER TABLE `member_login_list`
  ADD PRIMARY KEY (`login_list_id`),
  ADD KEY `member_id` (`member_id`);

--
-- 資料表索引 `member_payment_detail`
--
ALTER TABLE `member_payment_detail`
  ADD PRIMARY KEY (`payment_detail_id`),
  ADD KEY `member_id` (`member_id`);

--
-- 資料表索引 `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `chatroom_id` (`chatroom_id`),
  ADD KEY `member_id` (`member_id`);

--
-- 資料表索引 `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`order_detail_id`),
  ADD KEY `orderID` (`order_id`),
  ADD KEY `itemID` (`item_id`);

--
-- 資料表索引 `order_list`
--
ALTER TABLE `order_list`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `memberID` (`member_id`);

--
-- 資料表索引 `rate_list`
--
ALTER TABLE `rate_list`
  ADD PRIMARY KEY (`rate_id`),
  ADD KEY `rated_member_id` (`rated_member_id`),
  ADD KEY `rate_member_id` (`rate_member_id`),
  ADD KEY `rated_item_id` (`rated_item_id`),
  ADD KEY `order_id` (`order_id`);

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
-- 使用資料表自動遞增(AUTO_INCREMENT) `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `chatrooms`
--
ALTER TABLE `chatrooms`
  MODIFY `chatroom_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `items`
--
ALTER TABLE `items`
  MODIFY `item_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `item_picture`
--
ALTER TABLE `item_picture`
  MODIFY `picture_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `item_tags`
--
ALTER TABLE `item_tags`
  MODIFY `tag_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `members`
--
ALTER TABLE `members`
  MODIFY `member_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `member_login_list`
--
ALTER TABLE `member_login_list`
  MODIFY `login_list_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `member_payment_detail`
--
ALTER TABLE `member_payment_detail`
  MODIFY `payment_detail_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `messages`
--
ALTER TABLE `messages`
  MODIFY `message_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `order_details`
--
ALTER TABLE `order_details`
  MODIFY `order_detail_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `order_list`
--
ALTER TABLE `order_list`
  MODIFY `order_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `rate_list`
--
ALTER TABLE `rate_list`
  MODIFY `rate_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `wishlists`
--
ALTER TABLE `wishlists`
  MODIFY `wishlist_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `chatrooms`
--
ALTER TABLE `chatrooms`
  ADD CONSTRAINT `chatrooms_ibfk_1` FOREIGN KEY (`chatroom_member_id1`) REFERENCES `members` (`member_id`),
  ADD CONSTRAINT `chatrooms_ibfk_2` FOREIGN KEY (`chatroom_member_id2`) REFERENCES `members` (`member_id`);

--
-- 資料表的限制式 `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `items_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `item_categories` (`category_id`),
  ADD CONSTRAINT `items_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`);

--
-- 資料表的限制式 `item_picture`
--
ALTER TABLE `item_picture`
  ADD CONSTRAINT `item_picture_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`);

--
-- 資料表的限制式 `item_tags`
--
ALTER TABLE `item_tags`
  ADD CONSTRAINT `item_tags_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`);

--
-- 資料表的限制式 `member_login_list`
--
ALTER TABLE `member_login_list`
  ADD CONSTRAINT `member_login_list_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`);

--
-- 資料表的限制式 `member_payment_detail`
--
ALTER TABLE `member_payment_detail`
  ADD CONSTRAINT `member_payment_detail_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`);

--
-- 資料表的限制式 `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`chatroom_id`) REFERENCES `chatrooms` (`chatroom_id`),
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`);

--
-- 資料表的限制式 `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_list` (`order_id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`);

--
-- 資料表的限制式 `order_list`
--
ALTER TABLE `order_list`
  ADD CONSTRAINT `order_list_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`);

--
-- 資料表的限制式 `rate_list`
--
ALTER TABLE `rate_list`
  ADD CONSTRAINT `rate_list_ibfk_1` FOREIGN KEY (`rated_member_id`) REFERENCES `members` (`member_id`),
  ADD CONSTRAINT `rate_list_ibfk_2` FOREIGN KEY (`rate_member_id`) REFERENCES `members` (`member_id`),
  ADD CONSTRAINT `rate_list_ibfk_3` FOREIGN KEY (`rated_item_id`) REFERENCES `items` (`item_id`),
  ADD CONSTRAINT `rate_list_ibfk_4` FOREIGN KEY (`order_id`) REFERENCES `order_list` (`order_id`);

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
