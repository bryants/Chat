import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;

public class InterruptInput {   
    static BufferedReader in = new BufferedReader(
            new InputStreamReader(
            Channels.newInputStream(
            (new FileInputStream(FileDescriptor.in)).getChannel())));
    
    public static void Interrupt() {
        try {
            //System.out.println("Enter lines of input (user ctrl+Z Enter to terminate):");
            //System.out.println("(Input thread will be interrupted in 10 sec.)");
            //interrupt input in 10 sec
            (new TimeOut()).start();
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println("Read line:'"+line+"'");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString()); // printStackTrace();
        }
    }
    
    public static class TimeOut extends Thread {
        int sleepTime = 5000;
        Thread threadToInterrupt = null;    
        public TimeOut() {
            // interrupt thread that creates this TimeOut.
            threadToInterrupt = Thread.currentThread();
            setDaemon(true);
        }
        
        public void run() {
            try {
                sleep(5000); // wait 5 sec
            } catch(InterruptedException ex) {/*ignore*/}
            threadToInterrupt.interrupt();
        }
    }
}
