package vocabulary;

public class Word {
	String english; // 영단어를 의미합니다.
	String korean; // 한글 해석을 의미합니다.
	// 각각의 객체 처리 메소드를 정의합니다.
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
	// 영단어 생성자를 정의합니다.
	public Word(String english, String korean) {
		this.english = english;
		this.korean = korean;
	}
}