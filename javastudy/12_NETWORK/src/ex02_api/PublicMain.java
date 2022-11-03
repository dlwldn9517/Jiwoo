package ex02_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class PublicMain {
	
	public static void main(String[] args) {
		
		// 보건복지부_코로나 19 감염현황 조회 서비스
		
		// 인증키(Decoding)
		String serviceKey = "YGWZ4QeKRDz2kadVfxowJsKAEq3+Pu8sHt2L0UStVWScPr2R2QxBjTCyVEsy4E9CbP2ltlsuOJOkkQqjmXdv/g==";
		
		// API 주소
		String apiURL = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson";
		
		try {
			
			apiURL += "?resultCode" + URLEncoder.encode("00","UTF-8");
			apiURL += "&resultMsg" + URLEncoder.encode("OK","UTF-8");
			apiURL += "&numOfRows" + URLEncoder.encode("10","UTF-8");
			apiURL += "&pageNo" + URLEncoder.encode("1","UTF-8");
			apiURL += "&totalCount" + URLEncoder.encode("3","UTF-8");
			apiURL += "&SEQ" + URLEncoder.encode("74","UTF-8");
			apiURL += "&STATE_DT" + URLEncoder.encode("20200315","UTF-8");
			apiURL += "&STATE_TIME" + URLEncoder.encode("00:00","UTF-8");
			apiURL += "&DECIDE_CNT" + URLEncoder.encode("8162","UTF-8");
			apiURL += "&DEATH_CNT" + URLEncoder.encode("75","UTF-8");
			apiURL += "&ACC_EXAM_CNT" + URLEncoder.encode("268212","UTF-8");
			apiURL += "&ACC_DEF_RATE" + URLEncoder.encode("3.2396602365","UTF-8");
			apiURL += "&CREATE_DT" + URLEncoder.encode("2022-08-08 10:21:30.000","UTF-8");
			apiURL += "&UPDATE_DT" + URLEncoder.encode("null","UTF-8");
			
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}
		
		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");	// "GET"은 무조건 대문자
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
			
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println("API 주소 접속 실패");
		}
		
		// 입력 스트림(응답) 생성
		// 1. 응답 성공 시 정상 스트림, 실패 시 에러 스트림
		// 2. 응답 데이터 StringBuilder에 저장
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;	 // BufferedReader 써야 readLine()를 사용할 수 있다.
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			// 스트림 종료
			reader.close();
			
		} catch (IOException e) {
			System.out.println("API 응답 실패");
		}
		
		// API로부터 전달받은 xml 데이터
		String response = sb.toString();
		
		// File 생성
		File file = new File("C:\\storage", "api2.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
		
		
	}
	

}
