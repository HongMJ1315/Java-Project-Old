// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import com.jsyn.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operator = scanner.nextLine();
        if(operator.equals("Player")){
            System.out.println("Player");
            Player player = new Player(scanner);
            player.Start();
        }
        else if(operator.equals("Recorder")){
            System.out.println("Recorder");
            Recorder recorder = new Recorder(scanner);
            recorder.Start();
        }
        scanner.close();
    }
}
