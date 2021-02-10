package brandaoti.sistema.excel;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import brandaoti.sistema.controller.SistemaController;
import brandaoti.sistema.dao.UsuarioDao;
import brandaoti.sistema.dao.PerfilDao;
import brandaoti.sistema.model.Usuario;


public class ProcessaExcel {
	
	private SistemaController sis = new SistemaController();
	
	public List<Tabela> uploadAlunos(@ModelAttribute MultipartFile file) throws Exception {
		List<Tabela> tabelas = new ArrayList<Tabela>();
		int linha = 1;
		int coluna = 0;
		try {
			InputStream in = file.getInputStream();
		    XSSFWorkbook workbook = new XSSFWorkbook(in);
		    XSSFSheet sheet = workbook.getSheet("Sheet1");
		    if(sheet == null) {
				sheet = workbook.getSheet("Plan1");
			}
		    if(sheet == null) {
				sheet = workbook.getSheet("Planilha1");
			}
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				coluna = 0;
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
						if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
							Tabela tabela = new Tabela();
							tabela.setLinha(linha);
							tabela.setConteudo(cell.getStringCellValue());
							tabela.setColuna(coluna);
							tabelas.add(tabela);
							System.out.println("Coluna: "+tabela.getConteudo());
						}
						else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							Tabela tabela = new Tabela();
							tabela.setLinha(linha);
							tabela.setConteudo(cell.getNumericCellValue() + "");
							tabela.setColuna(coluna);
							tabelas.add(tabela);
						}
					coluna++;
				}
				linha++;
			}
		    in.close();
		    workbook.close();
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
		
		return tabelas;
	}
	
	
	
	
}
