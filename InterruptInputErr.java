import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;

public class InterruptInputErr {   
    static BufferedReader in = new BufferedReader(
            new InputStreamReader(
            Channels.newInputStream(
            (new FileInputStream(FileDescriptor.in)).getChannel())));
    
    public static void Interrupt() {
        try {
            (new TimeOut()).start();
            String line = null;
            //while ((line = in.readLine()) != null) {
            //    System.out.println("Read line:'"+line+"'");
            //}
        } catch (Exception ex) {
            System.out.println(ex.toString()); // printStackTrace();
        }
    }
    
    public static class TimeOut extends Thread {
        int sleepTime = 0;
        Thread threadToInterrupt = null;    
        public TimeOut() {
            // interrupt thread that creates this TimeOut.
            threadToInterrupt = Thread.currentThread();
            setDaemon(true);
        }
        
        public void run() {
            try {
                sleep(0); // don't sleep
            } catch(InterruptedException ex) {/*ignore*/}
            threadToInterrupt.interrupt();
        }
    }
}

