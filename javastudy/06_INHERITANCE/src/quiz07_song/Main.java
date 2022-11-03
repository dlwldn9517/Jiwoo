package quiz07_song;

public class Main {

	public static void main(String[] args) {

		Producer producer = new Producer();
		
		Singer singer = new Singer("가수", 2);	// 가수, 노래가 2개
		
		Song song1 = new Song("노래1", 3.5);
		Song song2 = new Song("노래2", 4.5);
		
		producer.produce(singer, song1);
		producer.produce(singer, song2);
		
		singer.info();
	}

}

