import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class FiltreLog {
    public static void main(String[] args) {
        // Nivell 3: Argument per defecte ERROR o el primer argument (args[0])
        String paraulaClau = (args.length > 0) 
            ? args[0] 
            : "ERROR";
        int comptador = 0;
        boolean teDades = false;

        // Nivell 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                
                if (!linia.trim().isEmpty()) {
                    teDades = true;
                }
                
                if (linia.contains(paraulaClau)) {
                    comptador++;
                }
            }

            // Nivell 2
            if (!teDades) {
                System.err.println("Error: Text buit");
                System.exit(1);
            }

            // S'envia el resultat
            System.out.println(comptador);
            System.exit(0);

        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
            System.exit(1);
        }
    }
}