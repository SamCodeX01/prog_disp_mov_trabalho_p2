package com.example.projeto_2.classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfUtils {

    public static File gerarPdfSimples(Context context, String nomeArquivo, String conteudo) {
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setTextSize(12);

        int x = 10, y = 25;

        for (String linha : conteudo.split("\n")) {
            canvas.drawText(linha, x, y, paint);
            y += 20;
        }

        pdfDocument.finishPage(page);

        File pasta = new File(
                context.getExternalFilesDir(null),
                "PDFsGerados"
        );
        if (!pasta.exists()) pasta.mkdirs();

        File arquivoPdf = new File(pasta, nomeArquivo + ".pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(arquivoPdf));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfDocument.close();
        return arquivoPdf;
    }
}
