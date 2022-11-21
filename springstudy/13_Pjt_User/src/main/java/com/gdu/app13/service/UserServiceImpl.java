package com.gdu.app13.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;

@PropertySource(value = {"classpath:email.properties"})
@Service
public class UserServiceImpl implements UserService {

	// 이메일을 보내는 사용자 정보
	@Value(value = "${mail.username}")
	private String username;  // 본인 지메일 주소
	
	@Value(value="${mail.password}")
	private String password;  // 발급 받은 앱 비밀번호
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Override
	public Map<String, Object> isReduceId(String id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		
		Map<String, Object> result = new HashMap<String, Object>();
	
		result.put("isUser", userMapper.selectUserByMap(map) != null);    // select 결과가 null이 아니면(조회 되었으면) true
		
		result.put("isRetireUser", userMapper.selectRetireUserById(id) != null);
      	return result;
	}
	
	@Override
	public Map<String, Object> isReduceEmail(String email) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserByMap(map) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {
		
		// 인증코드 만들기
		String authCode = securityUtil.getAuthCode(6);	// String authCode = securityUtil.generateRandomString(6);
		System.out.println("발송된 인증코드 : " + authCode);
		
		// 이메일 전송을 위한 필수 속성을 Properties 객체로 생성
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");		// 구글 메일로 보냄(보내는 메일은 구글 메일만 가능)
		properties.put("mail.smtp.port", "587");				// 구글 메일로 보내는 포트 번호
		properties.put("mail.smtp.auth", "true");				// 인증된 메일
		properties.put("mail.smtp.starttls.enable", "true");	// TLS 허용
		
		/*
			이메일 보내기 API 사용을 위한 사전 작업
			
			1. 구글 로그인
			2. [Google 계정] - [보안]
			    1) [2단계 인증]  - [사용]
			    2) [앱 비밀번호]
			        (1) 앱 선택   : 기타
			        (2) 기기 선택 : Windows 컴퓨터
			        (3) 생성 버튼 : 16자리 앱 비밀번호를 생성해 줌(이 비밀번호를 이메일 보낼 때 사용)
		*/
		
		// 사용자 정보를 javax.mail.Session에 저장
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		// 이메일 작성 및 전송
		try {
			
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(username, "인증코드관리자"));			// 보내는 사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));	// 받는 사람
			message.setSubject("[Application] 인증 요청 메일입니다.");					// 제목
			message.setContent("인증번호는 <strong>" + authCode + "</strong>입니다.", "text/html; charset=UTF-8");	// 내용
			
			Transport.send(message);	// 이메일 전송
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// join.jsp로 반환할 데이터
		// 생성한 인증코드를 보내줘야 함
		// 그래야 사용자가 입력한 인증코드와 비교를 할 수 있다.
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("authCode", authCode);
		return result;
	}
	
	@Transactional	// INSERT, UPDATE, DELETE 중 2개 이상이 호출되는 서비스에서 필요함
	@Override
	public void join(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthdate = request.getParameter("birthdate");
		String postcode = request.getParameter("postcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		String promotion = request.getParameter("promotion");
		
		
		// 일부 파라미터는 DB에 넣을 수 있도록 암호화로 가공
		pw = securityUtil.sha256(pw);
		name = securityUtil.preventXSS(name);	// <script>처럼 < >.. ".. ' 등 공격 방지
		String birthday = birthmonth + birthdate;
		detailAddress = securityUtil.preventXSS(detailAddress);
		
		int agreeCode = 0;	// 필수 동의
		
		if(!location.isEmpty() && promotion.isEmpty()) {
			agreeCode = 1;	// 필수 + 위치
		
		} else if(location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 2;	// 필수 + 프로모션
		
		} else if(!location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 3;	// 필수 + 위치 + 프로모션
		}	
		// location, promotion 둘 다 name으로 전달되서 null이 아니라 isEmpty를 사용
		
		
		// DB로 보낼 UserDTO 만들기
		UserDTO user = UserDTO.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.gender(gender)
				.email(email)
				.mobile(mobile)
				.birthyear(birthyear)
				.birthday(birthday)
				.postcode(postcode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.detailAddress(detailAddress)
				.extraAddress(extraAddress)
				.agreeCode(agreeCode)
				.build();
		
		// 회원가입처리
		int result = userMapper.insertUser(user);
		
		// 응답
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				
				// 로그인한다 = 로그인한 사용자의 정보를 session에 올려둔다.
				// 수많은 페이지들 위에 session이 있어서 모든 페이지에서 언제든지 session 꺼내쓸 수 있다. 
				// 로그인 처리를 위해서 session에 로그인 된 사용자 정보를 올려둠
				request.getSession().setAttribute("loginUser", userMapper.selectUserByMap(map));
				
				// 로그인 기록 남기기
				int updateResult = userMapper.updateAccessLog(id);
				if(updateResult == 0) {
					userMapper.insertAccessLog(id);
				}
				
				out.println("<script>");
				out.println("alert('회원 가입되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 가입에 실패했습니다.');");
				out.println("history.go(-2);");	// 뒤로 2칸
				out.println("</script>");
			}
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional	// INSERT, UPDATE, DELETE 중 2개 이상이 호출되는 서비스에서 필요함
	@Override
	public void retire(HttpServletRequest request, HttpServletResponse response) {

		// 탈퇴할 회원의 userNo, id, joinDate는 session의 loginUser에 저장되어 있다.
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		// 탈퇴할 회원 RetireUserDTO 생성
		RetireUserDTO retireUser = RetireUserDTO.builder()
				.userNo(loginUser.getUserNo())
				.id(loginUser.getId())
				.joinDate(loginUser.getJoinDate())
				.build();
		
		// 탈퇴처리
		int deleteResult = userMapper.deleteUser(loginUser.getUserNo());
		int insertResult = userMapper.insertRetireUser(retireUser);
		
		// 응답
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(deleteResult > 0 && insertResult > 0) {
				
				// session 초기화 (로그인 사용자 loginUser 삭제를 위해서)
				session.invalidate();
				
				out.println("<script>");
				out.println("alert('회원 탈퇴되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 탈퇴에 실패했습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) {

		// 파라미터
		String url = request.getParameter("url");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// pw는 DB에 저장된 데이터와 동일한 형태로 가공
		pw = securityUtil.sha256(pw);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		// id. pw가 일치하는 회원을 DB에서 조회하기
		UserDTO loginUser = userMapper.selectUserByMap(map);
		
		// id. pw가 일치하는 회원이 있다 : 로그인 기록 남기기 + session에 loginUser 저장
		if(loginUser != null) {
			
			// 로그인 기록 남기기
			int updateResult = userMapper.updateAccessLog(id);
			if(updateResult == 0) {
				userMapper.insertAccessLog(id);
			}
			
			// 로그인 유지 처리는 keepLogin 메소드가 따로 처리함
			keepLogin(request, response);
			
			// 로그인 처리를 위해서 session에 로그인 된 사용자 정보를 올려둠
			request.getSession().setAttribute("loginUser", loginUser);
			
			// 이동 (로그인페이지 이전 페이지로 돌아가기)
			try {
				response.sendRedirect(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// id. pw가 일치하는 회원이 없다 : 로그인 페이지로 돌려 보내기
		else {
			
			// 응답
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('일치하는 회원 정보가 없습니다.');");
				// 로그인 실패시 웰컴페이지로 감
				out.println("location.href='" + request.getContextPath() + "';");
				out.println("</script>");
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void keepLogin(HttpServletRequest request, HttpServletResponse response) {

		/*
			로그인 유지를 체크한 경우
			
			1. session_id를 쿠키에 저장해둔다.
				(쿠키명 : keepLogin)
			2. session_id를 DB에 저장해둔다.
				(SESSION_ID 칼럼에 session_id를 저장하고, SESSION_LIMIT_DATE 칼럼에 15일 후 날짜를 저장한다.)
			
	
			로그인 유지를 체크하지 않은 경우
			
			1. 쿠키 또는 DB에 저장된 정보를 삭제한다.
				편의상 쿠키명 keepLogin을 제거하는 것으로 처리한다.
		*/
		
		// 파라미터
		String id = request.getParameter("id");
		String keepLogin = request.getParameter("keepLogin");
		
		// 로그인 유지를 체크한 경우
		if(keepLogin != null) {
			
			// session_id
			String sessionId = request.getSession().getId();
			
			// session_id를 쿠키에 저장하기
			Cookie cookie = new Cookie("keepLogin", request.getSession().getId());
			cookie.setMaxAge(60 * 60 * 24 * 15);	// 15일( 60분 * 60초 * 24시간 * 15일)
			cookie.setPath(request.getContextPath());	// 쿠키 저장 장소를 getContextPath로 함
			response.addCookie(cookie);
			
			// session_id를 DB에 저장하기
			UserDTO user = UserDTO.builder()
					.id(id)
					.sessionId(sessionId)
					.sessionLimitDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))
					.build();
			
			userMapper.updateSessionInfo(user);
			
		}
		
		// 로그인 유지를 체크하지 않은 경우
		else {
			
			// session_id를 쿠키에 저장하기
			Cookie cookie = new Cookie("keepLogin", "");
			cookie.setMaxAge(0);	// 쿠키 유지 시간이 0이면 삭제를 의미
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
			
		}
		
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
	
		// 로그아웃 처리
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			session.invalidate();	// 세션 초기화
		}
		
		// 로그인 유지 풀기
		Cookie cookie = new Cookie("keepLogin", "");
		cookie.setMaxAge(0);	// 쿠키 유지 시간이 0이면 삭제를 의미
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		
	}
	
	@Override
	public UserDTO getUserBySessionId(Map<String, Object> map) {
		return userMapper.selectUserByMap(map);
	}
	
	@Override
	public Map<String, Object> confirmPassword(HttpServletRequest request) {
		
		// 파라미터 pw + SHA-256 처리
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		// id
		HttpSession session = request.getSession();
		String id = ((UserDTO)session.getAttribute("loginUser")).getId();
		
		// 조회 조건으로 사용할 Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		// id, pw가 일치하는 회원 조회
		UserDTO user = userMapper.selectUserByMap(map);
		
		// 결과 반환
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", user != null);		// 조회된 결과가 null이 아니면 isUser(사이트 회원이란 의미)
		return result;
	}
	
	@Override
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response) {

		// 현재 로그인 된 사용자
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");

		// 파라미터
		String pw = securityUtil.sha256(request.getParameter("pw"));

		// 동일한 비밀번호로 변경 금지
		if(pw.equals(loginUser.getPw())) {
			// 응답
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		// 사용자 번호
		int userNo = loginUser.getUserNo();
		
		// DB로 보낼 UserDTO
		UserDTO user = UserDTO.builder()
				.userNo(userNo)
				.pw(pw)
				.build();
		
		// 비밀번호 수정
		int result = userMapper.updateUserPassword(user);
		
		// 응답
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				
				// session에 저장된 loginUser 업데이트
				loginUser.setPw(pw);
				
				out.println("<script>");
				out.println("alert('비밀번호가 수정되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "';");
				out.println("</script>");
				
			} else {
				out.println("<script>");
				out.println("alert('비밀번호가 수정되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional	// 휴면처리 : insert와 delete 동시 진행되기 때문에 트랜잭션 처리 필요
	@Override
	public void sleepUserHandle() {
		
		int insertCount = userMapper.insertSleepUser();	  // inserCount로 반환되는게 3인거.
		if(insertCount > 0) {
			userMapper.deleteUserForSleep();
		}
	}
	
	@Override
	public SleepUserDTO getSleepUserById(String id) {
		return userMapper.selectSleepUserById(id);
	}
	
	@Transactional	// 휴면처리 : insert와 delete 동시 진행되기 때문에 트랜잭션 처리 필요
	@Override
	public void restoreUser(HttpServletRequest request, HttpServletResponse response) {		// 비밀번호가 request에 저장
		
		// 계정 복원을 원하는 사용자의 아이디
		HttpSession session = request.getSession();
		SleepUserDTO sleepUser = (SleepUserDTO)session.getAttribute("sleepUser");
		String id = sleepUser.getId();

		// 계정 복구 진행
		int insertCount = userMapper.insertRestoreUser(id);
		int deleteCount = 0;
		if(insertCount > 0) {
			deleteCount = userMapper.deleteSleepUser(id);
		}
		
		// 응답
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(insertCount > 0 && deleteCount > 0) {
				out.println("<script>");
				out.println("alert('휴면 계정이 복구되었습니다. 휴면 계정 활성화를 위해 곧바로 로그인을 해주세요.');");
				out.println("location.href='" + request.getContextPath() + "/user/login/form';");
				out.println("</script>");
			
			} else {
				out.println("<script>");
				out.println("alert('휴면 계정이 복구되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getNaverLoginApiURL(HttpServletRequest request) {
		
		String apiURL = null;
		
		try {
			
			String clientId = "r8AzKZxCTRl5CxIreAMx";	//애플리케이션 클라이언트 아이디값"; 
			String redirectURI = URLEncoder.encode("http://localhost:9090/" + request.getContextPath() + "/user/naver/login", "UTF-8");	 // 네이버 로그인 Callback URL에 작성한 주소 입력
			SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString();
		    apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		    apiURL += "&client_id=" + clientId;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&state=" + state;
		    HttpSession session = request.getSession();
		    session.setAttribute("state", state);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return apiURL;
	    
	    /*
	    	로그인 화면에서 네이버 로그인 클릭
			(reponse_type, client_id, redirect_uri, state 전송)
			->
			
			네이버로그인 동의화면(제공 정보 선택)
			이전 화면에서 보낸 redirect_uri 주소로 code, state값을 전송
			redirect_uri를 /user/naver/login이므로
			관련 매핑을 컨트롤러에 만들고, code, state 처리하는 서비스 구현
			
			callback url
	    */
	    
	}
	
	@Override
	public UserDTO getNaverLoginTokenNProfile(HttpServletRequest request) {
		
		// access_token을 이용해서 profile 받기
		String clientId = "r8AzKZxCTRl5CxIreAMx";	//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "PxjZVSOTBc";	//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    
	    String redirectURI = null;
	    try {
	    	redirectURI = URLEncoder.encode("http://localhost:9090" + request.getContextPath(), "UTF-8");
	    } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    
	    String access_token = "";
	    String refresh_token = "";
	    
	    StringBuffer res = new StringBuffer();	// StringBuffer는 StringBuilder와 동일한 역할 수행
	    
	    try {
	    	
	    	String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
	    	
	    	URL url = new URL(apiURL);
	    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
	    	con.setRequestMethod("GET");
	    	int responseCode = con.getResponseCode();
	    	BufferedReader br;

	    	if(responseCode == 200) { // 정상 호출
	    		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    	} else {  // 에러 발생
	    		br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	    	}
	    	String inputLine;
	    	while ((inputLine = br.readLine()) != null) {
	    		res.append(inputLine);
	    	}
	    	br.close();
	    	con.disconnect();
	    	
	    	/*
	    		{
		    		"access_token":"AAAAOpCedduWeWpQh3BN26tr4g6MvsZCBexFCI9gzXPMAbW2_4fGfS-8RJplo7O3JyuU8LpebTFf9B_FV-d1vIn52Po",
		    		"refresh_token":"bpQUGvEUABu4yjD2R287YWcRUsxUFsTb2p31YajAmvT38qLRd5NANPhFJYMJkRX2wzEsptwEcmGjCagZ5I1kAzWx0DiikI3IcaWHJmc2rN4IoVyhFmnipCVHipfPMtl1aRw",
		    		"token_type":"bearer",
		    		"expires_in":"3600"   -- 갱신주기
		    	}
	    	*/
	    	
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    
    	JSONObject obj = new JSONObject(res.toString());
    	access_token = obj.getString("access_token");
    	refresh_token = obj.getString("refresh_token");
    	
    	// access_token을 이용해서 profile 받기
    	String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
    	
    	StringBuffer sb = new StringBuffer();
    	
    	try {
    		
    		String apiURL = "https://openapi.naver.com/v1/nid/me";
    		URL url = new URL(apiURL);
	    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
	    	con.setRequestMethod("GET");
	    	con.setRequestProperty("Authorization", header);
	    	
	    	int responseCode = con.getResponseCode();
	    	BufferedReader br;

	    	if(responseCode == 200) { // 정상 호출
	    		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    	} else {  // 에러 발생
	    		br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	    	}
	    	String inputLine;
	    	while ((inputLine = br.readLine()) != null) {
	    		sb.append(inputLine);
	    	}
	    	br.close();
	    	con.disconnect();
	    	
	    	// 사용자의 프로필 정보
	    	/*
	    		{
		    		"resultcode":"00",
		    		"message":"success",
		    		"response":
		    					{
					    		"id":"EHDKccaO0aTLLhHW53HU6_N1XfQ_0w2E31Jl6KbCwFg",
					    		"gender":"F",
					    		"email":"admin@naver.com",
					    		"mobile":"010-0000-0000",
					    		"mobile_e164":"+821000000000",
					    		"name":"\uc774\uc9c0\uc6b0",
					    		"birthday":"11-17",
					    		"birthyear":"2022"
					    		}
		    	}
	    	*/
	    	
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	// 받아온 profile을 UserDTO로 만들어서 반환
    	
		return null;
	}
		
}
