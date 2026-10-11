
import java.util.Scanner;

public class FiltreLog {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contadorError = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (!line.isEmpty()) {
                String[] paraules = line.split("\\s+");

                for (String e : paraules) {
                    if (e.toUpperCase().equals("ERROR")) {
                        contadorError++;
                    }
                }
            }
        }

        System.out.println(contadorError);
    }
}
