package Components;

import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public  class ExportTableToExcel {
    public static class ExportTableToExcelStatic {


        public static void exportToExcel(TableView<?> table, String name) throws IOException {

            Workbook workbook = new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet(name);

            Row row = spreadsheet.createRow(0);

            for (int j = 0; j < table.getColumns().get(0).getColumns().size(); j++) {
                row.createCell(j).setCellValue(table.getColumns().get(0).getColumns().get(j).getText());
            }

            for (int i = 0; i < table.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j < table.getColumns().get(0).getColumns().size(); j++) {
                    if (table.getColumns().get(0).getColumns().get(j).getCellData(i) != null) {
                        row.createCell(j).setCellValue(table.getColumns().get(0).getColumns().get(j).getCellData(i).toString());
                    } else {
                        row.createCell(j).setCellValue("");
                    }
                }
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Eksport do skoroszytu excel");

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Skoroszyt Excel XLS (*.xls)", "*.xls");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(name);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(table.getScene().getWindow());

            if (file != null) {
                FileOutputStream fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();
            }
        }
    }
}
