package com.lmc.utils.export;

import com.lmc.service.PdfExportService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PdfExport extends AbstractPdfView {

    private PdfExportService pdfExportService = null;

    public PdfExport(PdfExportService pdfExportService){
        this.pdfExportService = pdfExportService;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter pdfWriter, HttpServletRequest request, HttpServletResponse response) throws Exception {
        pdfExportService.make(model,document,pdfWriter,request,response);
    }
}
