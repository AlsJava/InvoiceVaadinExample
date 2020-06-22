package org.alsjava.examples.invoices;

import com.vaadin.flow.component.UI;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Created by aluis on 6/21/20.
 */
public final class PrintTool {

    public static void printFromClient(UI ui, String html) {
        ui.getPage().executeJs("var printContents = '" + html + "';" +
                "       var w = window.open('', 'Invoice');" +
                "        w.document.write(printContents);" +
                "        w.document.close();" +
                "        w.focus();" +
                "        w.print();");
    }

    public static byte[] printFromServer(String html) {
        try {
            File invoice = File.createTempFile("invoice", ".html");
            FileWriter fileWriter = new FileWriter(invoice);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("<html lang=\"en\"><body>" + html + "</body></html>");
            printWriter.close();
//        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
            File pdfFile = new File(invoice.getParentFile(), "PDF.pdf");
            System.out.println(pdfFile.getAbsolutePath());
            System.out.println(invoice.getAbsolutePath());
            Process process;
//            if (isWindows) {
//            }
            process = Runtime.getRuntime().exec(String.format("google-chrome --headless --disable-gpu --print-to-pdf=%s --no-margins %s", pdfFile.getAbsolutePath(), invoice.getAbsolutePath()));
            StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            int exitCode = process.waitFor();
            assert exitCode == 0;
            byte[] bytes = Files.readAllBytes(pdfFile.toPath());
            invoice.delete();
            pdfFile.delete();
            return bytes;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static class StreamGobbler implements Runnable {
        private final InputStream inputStream;
        private final Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }
}
