package prac03;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParseMain {

	public static void main(String[] args) {

		// 영화 한 편 : Movie 객체
		// 영화 전체  : List<Movie> 리스트
		
		File file = new File("C:\\storage", "boxoffice.xml");
		List<Movie> movies = new ArrayList<Movie>();
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			NodeList boxOfficeList = doc.getElementsByTagName("dailyBoxOffice");
			
			for(int i = 0; i < boxOfficeList.getLength(); i++) {
				
				Element boxOffice = (Element)boxOfficeList.item(i);
				/*
				NodeList movieCdList = boxOffice.getElementsByTagName("movieCd");
				Node movieCdNode = movieCdList.item(0);
				String movieCd = movieCdNode.getTextContent();
				아래 1줄과 동일하다
				*/
				String movieCd = boxOffice.getElementsByTagName("movieCd").item(0).getTextContent();
				String MovieNm = boxOffice.getElementsByTagName("movieNm").item(0).getTextContent();
				String openDt = boxOffice.getElementsByTagName("openDt").item(0).getTextContent();
				String salesAcc = boxOffice.getElementsByTagName("salesAcc").item(0).getTextContent();
				String audiAcc = boxOffice.getElementsByTagName("audiAcc").item(0).getTextContent();
				
				Movie movie = Movie.builder()
						.movieCd(movieCd)
						.MovieNm(MovieNm)
						.openDt(openDt)
						.salesAcc(salesAcc)
						.audiAcc(audiAcc)
						.build();	// 전부 넣고 마지막에 build() : 지어라	
									// 메소드 체이닝 : 변수에 여러가지 메소드가 체인 형식으로 연결되어 있는 것
					
				movies.add(movie);
				
			}	// for
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Movie movie : movies) {
			System.out.println(movie);
		}
		
	}

}
