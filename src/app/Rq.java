package app;

public class Rq {
	
    private String cmd; // 입력받은 명령어
    private String action; // 명령어에 전달 
    
    // 입력한 명령어를 확인합니다.
    public Rq(String cmd) {
    	
    	// 앞 뒤 공백 제거
    	this.cmd = cmd.trim();
    	
    	// 공백 기준으로 명령어 나누기
    	String[] cmdBits = this.cmd.split(" ");
    	
    	this.action = cmdBits[0];
    	
    	}
    
    public String getAction() {
    	return action;
    	}
    
}
