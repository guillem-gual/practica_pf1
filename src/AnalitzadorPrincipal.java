import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AnalitzadorPrincipal {

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "FiltreLog");

        try {
            Process p = pb.start();

            PrintWriter out = new PrintWriter(new OutputStreamWriter(p.getOutputStream()), true);

            out.println("INFO: Inici del proces.");
            out.println("ERROR: No s'ha pogut carregar el proces.");
            out.println("WARNING: El proces s'esta intentant recuperar.");
            out.println("ERROR: No s'ha pogut recuperar el proces.");
            out.println("INFO: El proces ha finalitzat.");

            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String respostaFill = in.readLine();

            in.close();

            System.out.println(respostaFill);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

    }
}
