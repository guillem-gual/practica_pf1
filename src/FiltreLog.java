import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class FiltreLog {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int comptadorError = 0;

        if (sc.hasNextLine()){
            String line = sc.nextLine().trim();

            if (!line.isEmpty()){
                String[] paraules = line.split("\\s+");

                for (String e : paraules){

                    if (e.toUpperCase() == "ERROR"){
                        comptadorError ++;
                    }

                }
            }
        }

        System.out.println(comptadorError);

    }
}
