import java.io.*;

public class AnalitzadorPrincipal {
    public static void main(String[] args) {

        // Nivell 3
        ProcessBuilder pbError = new ProcessBuilder("java", "-cp", ".", "FiltreLog", "ERROR");
        ProcessBuilder pbWarning = new ProcessBuilder("java", "-cp", ".", "FiltreLog", "WARNING");

        // Nivell 2
        File fitxerErrors = new File("errors_filtre.log");
        pbError.redirectError(ProcessBuilder.Redirect.appendTo(fitxerErrors));
        pbWarning.redirectError(ProcessBuilder.Redirect.appendTo(fitxerErrors));

        try {
            // Iniciem els processos
            Process procError = pbError.start();
            Process procWarning = pbWarning.start();

            // Text de prova
            String textProva = "INFO Iniciant\n" +
                               "ERROR Connexió fallada a la base de dades\n" +
                               "WARNING Ús de CPU al 85%\n" +
                               "ERROR Memòria insuficient\n" +
                               "INFO Tot correcte\n" +
                               "ERROR Timeout\n" +
                               "WARNING Espai en disc baix\n" +
                               "WARNING Intents fallits\n" +
                               "WARNING Certificat a punt de caducar\n" +
                               "WARNING Latència alta\n";

            // Nivell 1 i 3
            try (BufferedWriter outError = new BufferedWriter(new OutputStreamWriter(procError.getOutputStream()));
                 BufferedWriter outWarning = new BufferedWriter(new OutputStreamWriter(procWarning.getOutputStream()))) {
                
                outError.write(textProva);
                outError.flush();
                
                outWarning.write(textProva);
                outWarning.flush();
            }

            // Nivell 3
            String resultatErrorStr, resultatWarningStr;
            try (BufferedReader inError = new BufferedReader(new InputStreamReader(procError.getInputStream()));
                 BufferedReader inWarning = new BufferedReader(new InputStreamReader(procWarning.getInputStream()))) {
                
                resultatErrorStr = inError.readLine();
                resultatWarningStr = inWarning.readLine();
            }

            // Parseig a Integer dels resultats enviants con a Strings
            int errors = (resultatErrorStr != null && !resultatErrorStr.isEmpty()) 
                ? Integer.parseInt(resultatErrorStr) 
                : 0;
            int warnings = (resultatWarningStr != null && !resultatWarningStr.isEmpty()) 
                ? Integer.parseInt(resultatWarningStr) 
                : 0;

            // Nivell 2
            int exitError = procError.waitFor();
            int exitWarning = procWarning.waitFor();
            
            int exitCodeFinal = Math.max(exitError, exitWarning);

            // Nivell 2
            System.out.println("Estat de l'execució (procés ERROR): " + exitError);
            System.out.println("Estat de l'execució (procés WARNING): " + exitWarning);
            System.out.println("====");

            // Nivell 3
            System.out.println("RESULTAT: ERRORS=" + errors + " | WARNINGS=" + warnings + " | EXIT_CODE=" + exitCodeFinal);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}