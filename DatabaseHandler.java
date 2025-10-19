package testu11;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Class for handling operations related to the Excel database.
 */
public class DatabaseHandler {

    /**
     * Updates the Excel database with the results of the plagiarism analysis.
     * 
     * @param filePath        the path to the Excel database
     * @param originalProject the path to the original project
     * @param suspectProject  the path to the suspect project
     * @param rezultatGlobal  the plagiarism percentage result
     */
    public static void updateDatabase(String filePath, String originalProject, String suspectProject, String rezultatGlobal) {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(inputStream)) {
            
            /**Access the specific sheet within the workbook*/
            Sheet sheet = workbook.getSheetAt(0);

            int nextRow = sheet.getLastRowNum() + 2;
            Row newRow = sheet.createRow(nextRow);

            newRow.createCell(0).setCellValue(PlagiarismDetector.prelucrareNume(originalProject));
            newRow.createCell(2).setCellValue(PlagiarismDetector.prelucrareNume(suspectProject));
            newRow.createCell(4).setCellValue(rezultatGlobal);

            /**Add date and time cell*/
            ZoneId zoneId = ZoneId.of("Europe/Bucharest");
            LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            newRow.createCell(6).setCellValue(formattedDateTime);

            /**Set cell style based on plagiarism percentage*/
            double doubleValue = Double.parseDouble(rezultatGlobal.replace(",", "."));
            CellStyle cellStyle = workbook.createCellStyle();
            if (doubleValue >= 40.00) {
                cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            } else {
                cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            }
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Cell cell = newRow.createCell(9);
            cell.setCellStyle(cellStyle);

            /**Save the workbook back to the file*/
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
