package vocabulary;
 
public class Word {
	String english;		// 영단어
	String korean;		// 한글뜻
	
	public Word() {
		
	}
	
	// 영단어 생성자 정의
	public Word(String english, String korean) {
		this.english = english;
		this.korean = korean;
	}
	
	// 객체 메소드
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getKorean() {
		return korean;
	}
	public void setKorean(String korean) {
		this.korean = korean;
	}
}