DROP TABLE ACCESS_LOG;
DROP TABLE USERS;
DROP TABLE RETIRE_USERS;
DROP TABLE SLEEP_USERS;

-- 회원 테이블
CREATE TABLE USERS
(
    USER_NO NUMBER NOT NULL,
    ID VARCHAR2(45 BYTE) NOT NULL UNIQUE,
    PW VARCHAR2(64 BYTE) NOT NULL,  -- 암호화된 비번 최대 64바이트.
    NAME VARCHAR2(50 BYTE) NOT NULL,
    GENDER VARCHAR2(2 BYTE) NOT NULL,  -- M, F, NO
    EMAIL VARCHAR2(50 BYTE) NOT NULL UNIQUE,  -- 이메일 인증 때문에 UNIQUE
    MOBILE VARCHAR2(11 BYTE) NOT NULL,  -- 휴대전화번호(하이픈 제외 최대 11자리)
    BIRTHYEAR VARCHAR2(4 BYTE) NOT NULL,  -- 출생년도(YYYY)
    BIRTHDAY VARCHAR2(4 BYTE) NOT NULL,  -- 생일(MMDD)
    POSTCODE VARCHAR2(5 BYTE),  -- 우편번호
    ROAD_ADDRESS VARCHAR2(100 BYTE),  -- 도로명주소
    JIBUN_ADDRESS VARCHAR2(100 BYTE),  -- 지번주소
    DETAIL_ADDRESS VARCHAR2(100 BYTE),  -- 상세주소
    EXTRA_ADDRESS VARCHAR2(100 BYTE),  -- 참고항목
    AGREE_CODE NUMBER NOT NULL,  -- 동의여부(0:필수, 1:필수+위치, 2:필수+프로모션, 3:필수+위치+프로모션)
    SNS_TYPE VARCHAR2(10 BYTE),  -- 간편가입종류(사이트가입:null, 네아로:naver)
    JOIN_DATE DATE NOT NULL,  -- 가입일
    PW_MODIFY_DATE DATE,    -- 비번 수정일
    INFO_MODIFY_DATE DATE,  -- 회원정보 수정일
    SESSION_ID VARCHAR2(32 BYTE),  -- 세션 아이디
    SESSION_LIMIT_DATE DATE  -- 세션 만료일
);

-- 회원접속기록 (최근 접속 기록 1개만 유지)
CREATE TABLE ACCESS_LOG
(
    ACCESS_LOG_NO NUMBER NOT NULL,
    ID VARCHAR2(45 BYTE) NOT NULL UNIQUE,
    LAST_LOGIN_DATE DATE NOT NULL  -- 마지막 로그인일
);

-- USERS 기본키
ALTER TABLE USERS
    ADD CONSTRAINT USERS_PK
        PRIMARY KEY(USER_NO);
-- ACCESS_LOG 기본키
ALTER TABLE ACCESS_LOG
    ADD CONSTRAINT ACCESS_LOG_PK
        PRIMARY KEY(ACCESS_LOG_NO);
-- ACCESS_LOG 외래키
ALTER TABLE ACCESS_LOG
    ADD CONSTRAINT ACCESS_LOG_USERS_FK
        FOREIGN KEY(ID) REFERENCES USERS(ID)
            ON DELETE CASCADE;
-- 시퀀스
DROP SEQUENCE USERS_SEQ;
DROP SEQUENCE ACCESS_LOG_SEQ;
CREATE SEQUENCE USERS_SEQ NOCACHE;
CREATE SEQUENCE ACCESS_LOG_SEQ NOCACHE;

-- 탈퇴 테이블(※ 삭제된 아이디로 재가입하거나 탈퇴한 아이디를 복구하는 것은 불가능)
CREATE TABLE RETIRE_USERS
(
    USER_NO NUMBER NOT NULL,
    ID VARCHAR2(45 BYTE) NOT NULL UNIQUE,
    JOIN_DATE DATE,  -- 가입일
    RETIRE_DATE DATE  -- 탈퇴일
);

-- 휴면 테이블
CREATE TABLE SLEEP_USERS
(
    USER_NO NUMBER NOT NULL,
    ID VARCHAR2(45 BYTE) NOT NULL UNIQUE,
    PW VARCHAR2(64 BYTE) NOT NULL,  -- 암호화된 비번 최대 64바이트.
    NAME VARCHAR2(50 BYTE) NOT NULL,
    GENDER VARCHAR2(2 BYTE) NOT NULL,  -- M, F, NO
    EMAIL VARCHAR2(50 BYTE) NOT NULL UNIQUE,  -- 이메일 인증 때문에 UNIQUE
    MOBILE VARCHAR2(11 BYTE) NOT NULL,  -- 휴대전화번호(하이픈 제외 최대 11자리)
    BIRTHYEAR VARCHAR2(4 BYTE) NOT NULL,  -- 출생년도(YYYY)
    BIRTHDAY VARCHAR2(4 BYTE) NOT NULL,  -- 생일(MMDD)
    POSTCODE VARCHAR2(5 BYTE),  -- 우편번호
    ROAD_ADDRESS VARCHAR2(100 BYTE),  -- 도로명주소
    JIBUN_ADDRESS VARCHAR2(100 BYTE),  -- 지번주소
    DETAIL_ADDRESS VARCHAR2(100 BYTE),  -- 상세주소
    EXTRA_ADDRESS VARCHAR2(100 BYTE),  -- 참고항목
    AGREE_CODE NUMBER NOT NULL,  -- 동의여부(0:필수, 1:필수+위치, 2:필수+프로모션, 3:필수+위치+프로모션)
    SNS_TYPE VARCHAR2(10 BYTE),  -- 간편가입종류(사이트가입:null, 네아로:naver)
    JOIN_DATE DATE,  -- 가입일
    LAST_LOGIN_DATE DATE,  -- 마지막 로그인일
    SLEEP_DATE DATE  -- 휴면처리일
);

-- 아이디/비번(id01/1111, id02/2222, id03/3333, id04/4444, id05/5555)
INSERT INTO USERS VALUES(USERS_SEQ.NEXTVAL, 'id01', ' FFE1ABD1A 8215353C233D6E0 9613E95EEC4253832A761AF28FF37AC5A15 C', 'NAME1', 'M', 'id01@naver.com', '01011111111', '1999', '0101', '11111', 'ROAD1', 'JIBUN1', 'DETAIL1', 'EXTRA1', 0, NULL, TO_DATE('20201010', 'YYYYMMDD'), NULL, NULL, NULL, NULL);
INSERT INTO USERS VALUES(USERS_SEQ.NEXTVAL, 'id02', 'EDEE29F882543B956620B26D EE0E7E950399B1C4222F5DE 5E06425B4C995E9', 'NAME2', 'F', 'id02@naver.com', '01022222222', '2000', '0101', '22222', 'ROAD2', 'JIBUN2', 'DETAIL2', 'EXTRA2', 0, NULL, TO_DATE('20201111', 'YYYYMMDD'), NULL, NULL, NULL, NULL);
INSERT INTO USERS VALUES(USERS_SEQ.NEXTVAL, 'id03', '318AEE3FED8C9D 4 D35A7FC1FA776FB31303833AA2DE885354DDF3D44D8FB69', 'NAME3', 'M', 'id03@naver.com', '01033333333', '2001', '0101', '33333', 'ROAD3', 'JIBUN3', 'DETAIL3', 'EXTRA3', 0, NULL, TO_DATE('20201212', 'YYYYMMDD'), NULL, NULL, NULL, NULL);
INSERT INTO USERS VALUES(USERS_SEQ.NEXTVAL, 'id04', '79F06F8FDE333461739F22 090A23CB2A79F6D714BEE10 D E4B4AF249294619', 'NAME4', 'F', 'id04@naver.com', '01044444444', '2002', '0101', '44444', 'ROAD4', 'JIBUN4', 'DETAIL4', 'EXTRA4', 0, NULL, TO_DATE('20210101', 'YYYYMMDD'), NULL, NULL, NULL, NULL);
INSERT INTO USERS VALUES(USERS_SEQ.NEXTVAL, 'id05', 'C1F330D0AFF31C1C87403F1E4347BCC21AFF7C1799 8723535F2B31723702525', 'NAME5', 'M', 'id05@naver.com', '01055555555', '2003', '0101', '55555', 'ROAD5', 'JIBUN5', 'DETAIL5', 'EXTRA5', 0, NULL, TO_DATE('20210202', 'YYYYMMDD'), NULL, NULL, NULL, NULL);
INSERT INTO ACCESS_LOG VALUES(ACCESS_LOG_SEQ.NEXTVAL, 'id01', TO_DATE('20220620', 'YYYYMMDD'));  -- 정상
INSERT INTO ACCESS_LOG VALUES(ACCESS_LOG_SEQ.NEXTVAL, 'id02', TO_DATE('20220520', 'YYYYMMDD'));  -- 정상
INSERT INTO ACCESS_LOG VALUES(ACCESS_LOG_SEQ.NEXTVAL, 'id03', TO_DATE('20210101', 'YYYYMMDD'));  -- 휴면
INSERT INTO ACCESS_LOG VALUES(ACCESS_LOG_SEQ.NEXTVAL, 'id04', TO_DATE('20210101', 'YYYYMMDD'));  -- 휴면
INSERT INTO ACCESS_LOG VALUES(ACCESS_LOG_SEQ.NEXTVAL, 'id05', TO_DATE('20210202', 'YYYYMMDD'));  -- 휴면
COMMIT;
