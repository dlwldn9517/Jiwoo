-- 1. 다음 설명을 읽고 적절한 테이블을 생성하되, 기본키/외래키는 별도로 설정하지 마시오.

-- 1) BOOK 테이블
--    (1) BOOK_ID : 책 아이디, 숫자 (최대 11자리), 필수
--    (2) BOOK_NAME : 책 이름, 가변 길이 문자 (최대 100 BYTE)
--    (3) PUBLISHER : 출판사, 가변 길이 문자 (최대 50 BYTE)
--    (4) PRICE : 가격, 숫자 (최대 6자리)

CREATE TABLE BOOK (
    BOOK_ID     NUMBER(11)          NOT NULL,
    BOOK_NAME   VARCHAR2(100 BYTE)  NULL,
    PUBLISHER   VARCHAR2(50 BYTE)   NULL,
    PRICE       NUMBER(6)           NULL
);


-- 2) CUSTOMER 테이블
--    (1) CUSTOMER_ID : 고객 아이디, 숫자 (최대 11자리), 필수
--    (2) CUSTOMER_NAME : 고객 이름, 가변 길이 문자 (최대 20 BYTE)
--    (3) ADDRESS : 고객 주소, 가변 길이 문자 (최대 50 BYTE)
--    (4) PHONE : 고객 전화, 가변 길이 문자 (최대 20 BYTE)

CREATE TABLE CUSTOMER (
    CUSTOMER_ID     NUMBER(11)  NOT     NULL,
    CUSTOMER_NAME   VARCHAR2(20 BYTE)   NULL,
    ADDRESS         VARCHAR2(50 BYTE)   NULL,
    PHONE           VARCHAR2(20 BYTE)   NULL
);


-- 3) ORDERS 테이블
--    (1) ORDER_ID : 주문 아이디, 숫자 (최대 11자리), 필수
--    (2) CUSTOMER_ID : 고객 아이디, 숫자 (최대 11자리)
--    (3) BOOK_ID : 책 아이디, 숫자 (최대 11자리)
--    (4) AMOUNT : 판매수량, 숫자 (최대 2자리)
--    (5) ORDER_DATE : 주문일, 날짜

CREATE TABLE ORDERS (
    ORDER_ID        NUMBER(11)  NOT NULL,
    CUSTOMER_ID     NUMBER(11)  NULL,
    BOOK_ID         NUMBER(11)  NULL,
    AMOUNT          NUMBER(2)   NULL,
    ORDER_DATE      DATE        NULL
);


-- 4) 아래 INSERT 문은 변경 없이 그대로 사용한다. (오라클 INSERT 방식)
INSERT ALL
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (1, '축구의역사', '굿스포츠', 7000)
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (2, '축구아는 여자', '나무수', 13000)
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (3, '축구의 이해', '대한미디어', 22000)
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (4, '골프 바이블', '대한미디어', 35000)
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (5, '피겨 교본', '굿스포츠', 6000)
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (6, '역도 단계별 기술', '굿스포츠', 6000)
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (7, '야구의 추억', '이상미디어', 20000)
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (8, '야구를 부탁해', '이상미디어', 13000)
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (9, '올림픽 이야기', '삼성당', 7500)
    INTO BOOK(BOOK_ID, BOOK_NAME, PUBLISHER, PRICE) VALUES (10,'올림픽 챔피언', '피어슨', 13000)
SELECT * FROM DUAL;

INSERT ALL
    INTO CUSTOMER(CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, PHONE) VALUES (1, '박지성', '영국 맨체스타', '000-5000-0001')
    INTO CUSTOMER(CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, PHONE) VALUES (2, '김연아', '대한민국 서울', '000-6000-0001')
    INTO CUSTOMER(CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, PHONE) VALUES (3, '장미란', '대한민국 강원도', '000-7000-0001')
    INTO CUSTOMER(CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, PHONE) VALUES (4, '추신수', '미국 텍사스', '000-8000-0001')
    INTO CUSTOMER(CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, PHONE) VALUES (5, '박세리', '대한민국 대전', NULL)
SELECT * FROM DUAL;

INSERT ALL 
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (1, 1, 1, 1, '2014-07-01')
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (2, 1, 3, 2, '2014-07-03')
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (3, 2, 5, 1, '2014-07-03')
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (4, 3, 6, 2, '2014-07-04')
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (5, 4, 7, 3, '2014-07-05')
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (6, 1, 2, 5, '2014-07-07')
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (7, 4, 8, 2, '2014-07-07')
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (8, 3, 10, 2, '2014-07-08')
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (9, 2, 10, 1, '2014-07-09')
    INTO ORDERS(ORDER_ID, CUSTOMER_ID, BOOK_ID, AMOUNT, ORDER_DATE) VALUES (10, 3, 6, 4, '2014-07-10')
SELECT * FROM DUAL;

COMMIT;

-- 2. BOOK, CUSTOMER, ORDERS 테이블의 BOOK_ID, CUSTOMER_ID, ORDER_ID 칼럼에 기본키를 추가하시오.
-- 기본키 제약조건의 이름은 PK_BOOK, PK_CUSTOMER, PK_ORDERS으로 지정하시오.

ALTER TABLE BOOK
    ADD CONSTRAINT PK_BOOK PRIMARY KEY(BOOK_ID);
ALTER TABLE CUSTOMER
    ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY(CUSTOMER_ID);
ALTER TABLE ORDERS
    ADD CONSTRAINT PK_ORDERS PRIMARY KEY(ORDER_ID);


-- 3. ORDERS 테이블의 CUSTOMER_ID, BOOK_ID 칼럼에 각각 CUSTOMER 테이블과 BOOK 테이블을 참조할 외래키를 추가하시오.
-- 외래키 제약조건의 이름은 FK_ORDERS_CUSTOMER, FK_ORDERS_BOOK로 지정하시오.
-- CUSTOMER_ID나 BOOK_ID가 삭제되는 경우 이를 참조하는 ORDERS 테이블의 정보는 NULL로 처리하시오.

ALTER TABLE ORDERS
    ADD CONSTRAINT FK_ORDERS_CUSTOMER FOREIGN KEY(CUSTOMER_ID)
        REFERENCES CUSTOMER(CUSTOMER_ID)
            ON DELETE SET NULL;
ALTER TABLE BOOK
    ADD CONSTRAINT FK_ORDERS_BOOK FOREIGN KEY(BOOK_ID)
        REFERENCES BOOK(BOOK_ID)
            ON DELETE SET NULL;


-- 4. 2014년 7월 4일부터 7월 7일 사이에 주문 받은 도서를 제외하고 나머지 모든 주문 정보를 조회하시오.
--    조회할 데이터 : 주문번호, 주문자명, 책이름, 판매가격, 주문일자
-- 구매번호 구매자  책이름           판매가격 주문일자
-- 1        박지성  축구의 역사      7000     14/07/01
-- 2        박지성  축구의 이해      44000    14/07/03
-- 3        김연아  피겨 교본        6000     14/07/03
-- 10       장미란  역도 단계별 기술 24000    14/07/10
-- 9        김연아  올림픽 챔피언    13000    14/07/09
-- 8        장미란  올림픽 챔피언    26000    14/07/08

-- ANSI
SELECT O.ORDER_ID AS 구매번호
     , C.CUSTOMER_NAME AS 구매자
     , B.BOOK_NAME AS 책이름
     , B.PRICE * O.AMOUNT AS 판매가격
     , O.ORDER_DATE AS 주문일자
  FROM CUSTOMER C INNER JOIN ORDERS O
    ON C.CUSTOMER_ID = O.CUSTOMER_ID INNER JOIN BOOK B
    ON B.BOOK_ID = O.BOOK_ID
 WHERE O.ORDER_DATE NOT BETWEEN '14/07/04' AND '14/07/07';

-- WHERE TO_DATE(O.ORDER_DATE) NOT BETWEEN TO_DATE('14/07/04', 'YY/MM/DD') AND TO_DATE('14/07/07', 'YY/MM/DD')

-- ORACLE
SELECT O.ORDER_ID AS 구매번호
     , C.CUSTOMER_NAME AS 구매자
     , B.BOOK_NAME AS 책이름
     , B.PRICE * O.AMOUNT AS 판매가격
     , O.ORDER_DATE AS 주문일자
  FROM CUSTOMER C, ORDERS O, BOOK B
 WHERE C.CUSTOMER_ID = O.CUSTOMER_ID
   AND B.BOOK_ID = O.BOOK_ID
   AND O.ORDER_DATE NOT BETWEEN '14/07/04' AND '14/07/07';


-- 5. 모든 구매 고객의 이름과 총구매액(PRICE * AMOUNT)을 조회하시오.
-- 구매 이력이 있는 고객만 조회하시오. (주문내역에 있고, 고객에 있는 데이터 = 내부 조인)
-- 고객명  총구매액
-- 박지성  116000
-- 추신수  86000
-- 장미란  62000
-- 김연아  19000

SELECT C.CUSTOMER_NAME AS 고객명
     , SUM(B.PRICE * O.AMOUNT) AS 총구매액
  FROM CUSTOMER C INNER JOIN ORDERS O
    ON C.CUSTOMER_ID = O.CUSTOMER_ID INNER JOIN BOOK B
    ON B.BOOK_ID = O.BOOK_ID
 GROUP BY C.CUSTOMER_ID, C.CUSTOMER_NAME;

 
-- 6. 모든 구매 고객의 이름과 총구매액(PRICE * AMOUNT)과 구매횟수를 조회하시오.
-- 구매 이력이 없는 고객은 총구매액과 구매횟수를 0으로 조회하시오. (고객은 모두 조회, 주문내역은 있는 자료만 조회 = 왼쪽 외부 조인)
-- 고객번호순으로 오름차순 정렬하여 조회하시오.
-- 고객명  총구매액  구매횟수
-- 박지성  116000     3
-- 김연아  19000      2
-- 장미란  62000      3
-- 추신수  86000      2
-- 박세리  0          0

SELECT C.CUSTOMER_NAME AS 고객명
     , NVL(SUM(B.PRICE * O.AMOUNT), 0) AS 총구매액
     , COUNT(O.ORDER_ID) AS 구매횟수  -- COUNT에는 고객정보가 포함되면 안된다.
  FROM CUSTOMER C LEFT OUTER JOIN ORDERS O
    ON C.CUSTOMER_ID = O.CUSTOMER_ID LEFT OUTER JOIN BOOK B
    ON B.BOOK_ID = O.BOOK_ID
 GROUP BY C.CUSTOMER_ID, C.CUSTOMER_NAME
 ORDER BY C.CUSTOMER_ID ASC;


-- 7. '김연아'가 구매한 도서개수를 조회하시오.
-- 고객명  구매도서수
-- 김연아  2

SELECT C.CUSTOMER_NAME AS 고객명
     , COUNT(*) AS 구매도서수
  FROM CUSTOMER C INNER JOIN ORDERS O
    ON C.CUSTOMER_ID = O.CUSTOMER_ID
 WHERE C.CUSTOMER_NAME = '김연아'
 GROUP BY C.CUSTOMER_ID, C.CUSTOMER_NAME;


-- 8. '박지성'이 구매한 도서를 발간한 출판사(PUBLISHER) 개수를 조회하시오.
-- 고객명  출판사수
-- 박지성  3

SELECT C.CUSTOMER_NAME AS 고객명
     , COUNT(DISTINCT B.PUBLISHER) AS 출판사수  -- DISTINCT는 칼럼 앞에 들어간다.
  FROM CUSTOMER C INNER JOIN ORDERS O
    ON C.CUSTOMER_ID = O.CUSTOMER_ID INNER JOIN BOOK B
    ON B.BOOK_ID = O.BOOK_ID
 WHERE C.CUSTOMER_NAME = '박지성'
 GROUP BY C.CUSTOMER_ID, C.CUSTOMER_NAME;
 

-- 9. 주문한 이력이 없는 고객의 이름을 조회하시오.
-- 고객명
-- 박세리

SELECT C.CUSTOMER_NAME AS 고객명
  FROM CUSTOMER C LEFT OUTER JOIN ORDERS O
    ON C.CUSTOMER_ID = O.CUSTOMER_ID
 GROUP BY C.CUSTOMER_ID, C.CUSTOMER_NAME
HAVING COUNT(O.ORDER_ID) = 0;   -- 집계함수 조건절은 HAVING

SELECT C.CUSTOMER_NAME AS 고객명
  FROM CUSTOMER C LEFT OUTER JOIN ORDERS O
    ON C.CUSTOMER_ID = O.CUSTOMER_ID
 WHERE O.ORDER_ID IS NULL
 GROUP BY C.CUSTOMER_ID, C.CUSTOMER_NAME;
 

-- 10. 가장 최근에 구매한 고객의 이름과 구매내역(책이름, 주문일자)을 조회하시오.
-- 고객명  책이름            주문일자
-- 장미란  역도 단계별 기술  14/07/10

SELECT C.CUSTOMER_NAME AS 고객명
     , B.BOOK_NAME AS 책이름
     , O.ORDER_DATE AS 주문일자
  FROM CUSTOMER C INNER JOIN ORDERS O
    ON C.CUSTOMER_ID = O.CUSTOMER_ID INNER JOIN BOOK B
    ON B.BOOK_ID = O.BOOK_ID
 WHERE O.ORDER_DATE = (SELECT MAX(O.ORDER_DATE)
                        FROM ORDERS);


-- 11. 현존하는 모든 서적 중에서 가장 비싼 서적을 구매한 고객의 이름과 구매내역(책이름, 가격)을 조회하시오.
-- 구매한 고객이 없다면 고객 이름은 NULL로 처리하시오. (책 LEFT OUTER JOIN 주문 ...)
-- 고객명  책이름       책가격
-- NULL    골프 바이블  35000

SELECT C.CUSTOMER_NAME AS 고객명
     , B.BOOK_NAME AS 책이름
     , B.PRICE AS 책가격
  FROM BOOK B LEFT OUTER JOIN ORDERS O
    ON B.BOOK_ID = O.BOOK_ID LEFT OUTER JOIN CUSTOMER C
    ON C.CUSTOMER_ID = O.CUSTOMER_ID
 WHERE B.PRICE = (SELECT MAX(PRICE)
                    FROM BOOK);


-- 12. 총구매액이 2~3위인 고객의 이름와 총구매액을 조회하시오.
-- ROWNUM PSEUDO-COLUMN을 이용하시오.
-- 고객명  총구매액
-- 추신수  86000
-- 장미란  62000

SELECT B.고객명
     , B.총구매액
  FROM (SELECT ROWNUM AS ROW_NUM
             , A.고객명
             , A.총구매액
          FROM (SELECT C.CUSTOMER_NAME AS 고객명
                     , SUM(B.PRICE * O.AMOUNT) AS 총구매액
                  FROM CUSTOMER C INNER JOIN ORDERS O
                    ON C.CUSTOMER_ID = O.CUSTOMER_ID INNER JOIN BOOK B
                    ON B.BOOK_ID = O.BOOK_ID
                 GROUP BY C.CUSTOMER_ID, C.CUSTOMER_NAME) A) B
 WHERE B.ROW_NUM BETWEEN 2 AND 3;

