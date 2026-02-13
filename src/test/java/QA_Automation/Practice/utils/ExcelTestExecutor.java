package QA_Automation.Practice.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.internal.runners.statements.Fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelTestExecutor {
    private static final String baseResourcePath = "src/test/resources/Excelsourcefile/";
    private final String fileName = "Userstory1.xlsx";
    public static String Status = "Fail";

    /**
     * Retrieves the data from the 'Input' column for a specific step.
     */
    public String getInput(String stepDescription) throws Exception {
        File f = new File(baseResourcePath + fileName);
        try (FileInputStream fis = new FileInputStream(f);
                XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Testcases");
            Map<String, Integer> colMap = getColumnMapper(sheet);

            int stepColIdx = colMap.get("Steps");
            int inputColIdx = colMap.get("Input");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;

                String excelStep = getCellString(row.getCell(stepColIdx));

                // Match the step name (case-insensitive and trimmed for better reliability)
                if (excelStep.trim().equalsIgnoreCase(stepDescription.trim())) {
                    return getCellString(row.getCell(inputColIdx));
                }
            }
        }
        return ""; // Return empty if step not found
    }

    /**
     * Updates the status and returns the input value for the current step
     */
    public void runTests(String step, String status) throws Exception {

        Status = status;
        System.out.println("enter runTests method");
        File f = new File(baseResourcePath + fileName);

        try (FileInputStream fis = new FileInputStream(f);
                XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Testcases");
            Map<String, Integer> colMap = getColumnMapper(sheet);
            int statusIdx = colMap.getOrDefault("Status", (int) sheet.getRow(0).getLastCellNum());
            int stepColIdx = colMap.get("Steps");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;

                String excelStep = getCellString(row.getCell(stepColIdx));

                if (excelStep.equalsIgnoreCase(step) || excelStep.contains(step)) {
                    updateStatus(row, statusIdx, status);
                }
            }

            try (FileOutputStream fos = new FileOutputStream(f)) {
                workbook.write(fos);
            }
        }
    }

    // --- Helper Methods ---

    private Map<String, Integer> getColumnMapper(Sheet sheet) {
        Row headerRow = sheet.getRow(0);
        Map<String, Integer> colMap = new HashMap<>();
        String[] requiredColumns = { "TC ID", "Test Scenario", "Steps", "Input", "Expected Result", "Status" };

        for (int c = 0; c < headerRow.getLastCellNum(); c++) {
            Cell cell = headerRow.getCell(c);
            if (cell != null) {
                String headerText = cell.getStringCellValue().trim();
                for (String req : requiredColumns) {
                    if (headerText.equalsIgnoreCase(req)) {
                        colMap.put(req, c);
                    }
                }
            }
        }
        return colMap;
    }

    private static String getCellString(Cell c) {
        if (c == null)
            return "";
        switch (c.getCellType()) {
            case STRING:
                return c.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) c.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(c.getBooleanCellValue());
            default:
                return "";
        }
    }

    private static void updateStatus(Row row, int colIndex, String status) {
        Cell cell = row.getCell(colIndex);
        if (cell == null)
            cell = row.createCell(colIndex);
        cell.setCellValue(status);
    }
}