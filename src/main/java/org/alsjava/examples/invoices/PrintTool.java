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

    public static void printFromClient(UI ui, String invoiceName, String html) {
        String js = String.format("var printContents = '%s';" +
                        "var w = window.open();" +
                        "w.document.write(printContents);" +
                        "w.document.close();" +
                        "w.document.title = '%s';" +
                        "w.focus();" +
                        "w.print()",
                html, invoiceName);
        ui.getPage().executeJs(js);
    }

    public static byte[] printFromServer(String html) {
        try {
            File invoice = File.createTempFile("invoice", ".html");
            FileWriter fileWriter = new FileWriter(invoice);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("<html><body>" + html + "</body></html>");
            printWriter.close();
            File pdfFile = new File(invoice.getParentFile(), "PDF.pdf");
            System.out.println(pdfFile.getAbsolutePath());
            System.out.println(invoice.getAbsolutePath());
            String command = String.format("google-chrome --headless --disable-gpu --print-to-pdf=%s --no-margin %s", pdfFile.getAbsolutePath(), invoice.getAbsolutePath());
            Process process = Runtime.getRuntime().exec(command);
            Gobbler gobbler = new Gobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(gobbler);
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                byte[] bytes = Files.readAllBytes(pdfFile.toPath());
                if (invoice.delete()) {
                    System.out.println("Invoice Deleted");
                }
                if (pdfFile.delete()) {
                    System.out.println("PDF File Deleted");
                }
                return bytes;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static class Gobbler implements Runnable {

        private final InputStream inputStream;
        private final Consumer<String> consumer;

        private Gobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }
}
