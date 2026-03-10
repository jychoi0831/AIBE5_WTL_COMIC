package app;

public class Rq {
	
    private String cmd;
    private String action;
    
    public Rq(String cmd) {
    	
    	this.cmd = cmd.trim();
    	
    	String[] cmdBits = this.cmd.split(" ");
    	
    	this.action = cmdBits[0];
    	}
    
    public String getAction() {
    	return action;
    	}
    
}
