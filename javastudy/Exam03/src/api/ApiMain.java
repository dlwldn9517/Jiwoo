package api;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ApiMain {

	public static void main(String[] args) {
		
		// 도로교통공단_사망교통사고정보서비스
		
		String apiURL = "https://www.data.go.kr/data/15059126/openapi.do";
		String serviceKey = "YGWZ4QeKRDz2kadVfxowJsKAEq3+Pu8sHt2L0UStVWScPr2R2QxBjTCyVEsy4E9CbP2ltlsuOJOkkQqjmXdv/g==";
		
		
		try {
			apiURL += "?pageNo=" + URLEncoder.encode("1","UTF-8");
			apiURL += "&numOfRows=" + URLEncoder.encode("10","UTF-8");
			apiURL += "&dataType=" + URLEncoder.encode("xml","UTF-8");
			apiURL += "&occrrncDt=" + URLEncoder.encode("2019011622","UTF-8");
			apiURL += "&occrrncDayCd=" + URLEncoder.encode("4","UTF-8");
			apiURL += "&dthDnvCnt=" + URLEncoder.encode("0","UTF-8");
			apiURL += "&injpsnCnt=" + URLEncoder.encode("1","UTF-8");
			apiURL += "&serviceKey=" + URLEncoder.encode(serviceKey,"UTF-8");

	    } catch (UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    }
		
		URL url = null;
		HttpURLConnection con = null;
		
	try {
		url = new URL(apiURL);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
        con.setRequestProperty("content-Type", "application/json; charset=UTF-8");
			
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println("API 접속 실패");	
		}
	
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			reader.close();
			
		} catch (IOException e) {
			System.out.println("API 응답 실패");
		}
		
		File file = new File("accident.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		con.disconnect();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			System.out.println(root.getNodeName());
			
			NodeList items = root.getElementsByTagName("item");
			
			for(int i = 0; i < items.getLength(); i++) {
				
				Element item = (Element)items.item(i);
				
				Node occrrnc_dt = item.getElementsByTagName("occrrnc_dt").item(0);
				Node occrrncDayCd = item.getElementsByTagName("occrrncDayCd").item(0);
				Node dthDnvCnt = item.getElementsByTagName("dthDnvCnt").item(0);
				Node injpsnCnt = item.getElementsByTagName("injpsnCnt").item(0);
				
				sb.append("발생일자 " + occrrnc_dt.getTextContent());
				sb.append(" " + occrrncDayCd.getTextContent());
				sb.append(", 사망자수" + dthDnvCnt.getTextContent() + "명, ");
				sb.append("부상자수 " + injpsnCnt.getTextContent() + "명 \n");
			}
			System.out.println(sb.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
}
