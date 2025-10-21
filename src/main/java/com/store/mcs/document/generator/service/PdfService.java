package com.store.mcs.document.generator.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.util.Map;

@Service
public class PdfService {

    private final SpringTemplateEngine templateEngine;

    public PdfService(@Qualifier("thymeleaf-template") SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void generatePdf(String templateName, Map<String, Object> data) throws Exception {

        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);

        try (FileOutputStream outputStream = new FileOutputStream("invoice.pdf")) {

            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            PageSize pageSize = PageSize.LEGAL.rotate();
            pdf.setDefaultPageSize(pageSize);

            HtmlConverter.convertToPdf(new ByteArrayInputStream(htmlContent.getBytes()),
                    pdf);

        };


    }
}