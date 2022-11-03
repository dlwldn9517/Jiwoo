package prac03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// 일별 박스오피스 API 서비스

		String key = "2226b7bc92047543a3afd798f6033fa3";
		
		Scanner sc = new Scanner(System.in);
		System.out.print("날짜(yyyymmdd) >>> ");
		String targetDt = sc.next();
		
		try {
			key = URLEncoder.encode(key, "UTF-8");
			targetDt = URLEncoder.encode(targetDt, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 실패", e);
		}
		
		// 파라미터 붙이고 싶으면 '?' 붙여서 쓰기
		// "?key = " 또는 "?key= " 또는 "?key =" 이것들처럼 공백이 있으면 안돼
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=" + key + "&targetDt=" + targetDt;
		
		String response = getResponse(apiURL);
		createFile(response);
		
	}
	
	public static String getResponse(String apiURL) {
		
		HttpURLConnection con = getConnection(apiURL);
		
		try {
		
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());
			} else {
				return readBody(con.getErrorStream());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("API 요청 오류", e);
		}	
	}
	
	public static HttpURLConnection getConnection(String apiURL) {
		
		try {
			URL url = new URL(apiURL);
			return (HttpURLConnection)url.openConnection();
			
		} catch (MalformedURLException e) {
			throw new RuntimeException("API 주소 오류", e);
		} catch (IOException e) {
			throw new RuntimeException("API 연결 오류", e);
		} 
	}
	
	public static String readBody(InputStream in) {
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
			
		} catch (IOException e) {
			throw new RuntimeException("API 응답 오류", e);
		}
	}
	
	public static void createFile(String response) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\boxoffice.xml"))) {
			bw.write(response);
			bw.flush();	
			// 스트림 : 통로, flush : 수도꼭지 호수에 남아있는 물을 바람으로 후 불어주는것 (굳이 안해줘도 된다.)
			// 스트림에 남아 있는 모든 것을 보내주는 것 (빨대에 남아 있는걸 불어내는 것)
			
		} catch (IOException e) {
			throw new RuntimeException("파일 생성 오류", e);
		}
	}
	
}
