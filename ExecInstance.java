import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;

public class ExecInstance implements Runnable {
    public String host;
    public String username;
    public String password;
    public int port;
    public String command;

    public void setParams(String host, String username, String password, int port, String command){
        this.host = host;
        this.password = password;
        this.username = username;
        this.port = port;
        this.command = command;
    }

    @Override
    public void run() {
        Session session = null;
        ChannelExec channel = null;
        String response = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.setErrStream(errorStream);
            channel.connect();
            Thread.sleep(100);
            response = new String(responseStream.toByteArray());
            String errorResponse = new String(errorStream.toByteArray());
            if(!errorResponse.isEmpty()) {
                throw new Exception(errorResponse);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
