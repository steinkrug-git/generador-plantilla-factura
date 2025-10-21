package com.store.mcs.document.generator.controller;

import com.store.mcs.document.generator.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("digits", "0080144");
            data.put("issue_date", LocalDate.now());
            data.put("cash_payment", "X"); //este va a cargar con un if si es crédito
            data.put("cash_credit", "X");
            data.put("social_reason", "Aldo González Colmán");
            data.put("ruc_ci", "123456");
            data.put("address", "Lapacho casi Padre Casanelo Nº 123");
            data.put("locality", "Ñemby");
            data.put("telephone", "+595 991123456");
            data.put("remission_note", ""); //hacer dinámico luego
            data.put("remission_date", "");
            data.put("remission_expiration_date", "");
            data.put("parcial_value", 1020000);
            data.put("parcial_exempt", "");
            data.put("parcial_iva5", "");
            data.put("total_price_letters", "Un millón ventemil");
            data.put("total_price", 1020000);
            data.put("iva_liquidation_5", "");
            data.put("iva_liquidation_10", 70321);
            data.put("total_iva", 70321);

            data.put("items", List.of(
                    Map.of("code", "6e698896-71f5-4c10-a7b7-22ba7f8a4eb4", "description", "Vestido floreado M Amarillo",
                    "quantity", 1, "unitPrice", 120000, "exempt", "", "iva5", "", "iva10", 120000),
                    Map.of("code", "4e698896-81f5-4c10-a7b7-12ba7f8a4eb8", "description", "Pantalón ejecutivo S Negro",
                            "quantity", 1, "unitPrice", 180000, "exempt", "", "iva5", "", "iva10", 180000),
                    Map.of("code", "4e698896-81f5-4c10-a7b7-12ba7f8a4eb8", "description", "Pantalón ejecutivo S Negro",
                            "quantity", 1, "unitPrice", 180000, "exempt", "", "iva5", "", "iva10", 180000),
                    Map.of("code", "4e698896-81f5-4c10-a7b7-12ba7f8a4eb8", "description", "Pantalón ejecutivo S Negro",
                            "quantity", 1, "unitPrice", 180000, "exempt", "", "iva5", "", "iva10", 180000),
                    Map.of("code", "4e698896-81f5-4c10-a7b7-12ba7f8a4eb8", "description", "Pantalón ejecutivo S Negro",
                            "quantity", 1, "unitPrice", 180000, "exempt", "", "iva5", "", "iva10", 180000),
                    Map.of("code", "4e698896-81f5-4c10-a7b7-12ba7f8a4eb8", "description", "Pantalón ejecutivo S Negro",
                            "quantity", 1, "unitPrice", 180000, "exempt", "", "iva5", "", "iva10", 180000),
                    Map.of("code", "4e698896-81f5-4c10-a7b7-12ba7f8a4eb8", "description", "Pantalón ejecutivo S Negro",
                            "quantity", 1, "unitPrice", 180000, "exempt", "", "iva5", "", "iva10", 180000),
                    Map.of("code", "4e698896-81f5-4c10-a7b7-12ba7f8a4eb8", "description", "Pantalón ejecutivo S Negro",
                            "quantity", 1, "unitPrice", 180000, "exempt", "", "iva5", "", "iva10", 180000),
                    Map.of("code", "4e698896-81f5-4c10-a7b7-12ba7f8a4eb8", "description", "Pantalón ejecutivo S Negro",
                            "quantity", 1, "unitPrice", 180000, "exempt", "", "iva5", "", "iva10", 180000)
            ));

            pdfService.generatePdf("invoice", data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(null);
                    //.body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}