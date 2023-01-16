import java.util.Scanner;

public class MainConnect {

        public static void main(String args[]) throws Exception {
            Scanner scnr = new Scanner(System.in);

            System.out.println("Enter Host IP");
            String host = scnr.nextLine();

            System.out.println("Enter Username");
            String username = scnr.next();

            System.out.println("Enter Password");
            String password = scnr.next();

            System.out.println("Enter Port Number");
            int port = scnr.nextInt();

            System.out.println("Enter a SSH command");
            scnr.next();

            String command = scnr.nextLine();


            // make this multithreaded
            for (int i = 0; i < 10; i++) {
                ExecInstance execInstance = new ExecInstance();
                execInstance.setParams(username, password, host, port, command);
                Thread execThread = new Thread(new ExecInstance());
                execThread.setName("Connection " + i);
                execThread.start();

            }
        }

    }

