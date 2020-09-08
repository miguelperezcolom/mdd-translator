package io.mateu.translator.model;

import io.mateu.mdd.util.Helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {

    public static void main(String[] args) {
        try {
            System.out.println(new Zipper().dumpAndZip(1));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static File dumpAndZip(long applicationId) throws Throwable {
        UUID uuid = UUID.randomUUID();
        File r = new File(System.getProperty("java.io.tmpdir") + "/" + uuid + ".zip");
        File d = new File(System.getProperty("java.io.tmpdir") + "/" + uuid);
        d.mkdirs();

        List<String> srcFiles = dump(applicationId, d);

        fillZip(r, srcFiles);

        return r;
    }

    private static void fillZip(File r, List<String> srcFiles) throws IOException {
        FileOutputStream fos = new FileOutputStream(r);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }

    public static List<String> dump(long applicationId, File d) throws Throwable {
        List<String> srcFiles = new ArrayList<>();
        for (String l : List.of("en", "es", "de", "fr", "it", "ru", "ar", "cz")) {
            Helper.notransact(em -> {
                File out = new File(d, "messages_" + l + ".properties");
                PrintWriter pw = new PrintWriter(out);
                Helper.getStreams().streamAll(em, Translation.class)
                        .where(t -> t.getApplication().getId() == applicationId)
                        .forEach(t -> pw.println(escape(t.getKey()) + " = " + putInOneLine(Optional.ofNullable(t.getLiteral().get(l)).orElse(""))));
                pw.close();
                srcFiles.add(out.getAbsolutePath());
            });
        }
        return srcFiles;
    }

    private static String putInOneLine(String s) {
        return s.replaceAll("(\\R)", "\\\n");
    }

    private static String escape(String s) {
        return s.replaceAll("([^a-zA-Z0-9\\.\\-\\_])", "\\$1");
    }

}
