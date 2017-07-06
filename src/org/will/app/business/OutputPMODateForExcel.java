package org.will.app.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.will.app.model.DownloadFile;
import org.will.app.model.Operator;
import org.will.app.model.PStory;



public class OutputPMODateForExcel
{
	private Operator operator;
	
	public OutputPMODateForExcel(Operator operator)
	{
		this.operator = operator;
	}
	
	public DownloadFile execute(List<PStory> pstories) throws IOException
	{
		
		String projectPath = operator.getProperties().get("projectPath");
		String tempFilePath = operator.getProperties().get("tempFilePath");
		//System.out.println(projectPath);
		//System.out.println(tempFilePath);
		
		String fileName = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		fileName = "Data"+sdf.format(new Date())+String.valueOf(Math.random()).substring(3,5);
		String path = projectPath+"/WEB-INF/classes/"+fileName+".xlsx";
		
		//ÐÂexcel
		File newfile = new File(path);
		//¾Éexcel
		File file = new File(tempFilePath);
			
		FileUtils.copyFile(file, newfile);
		
		DownloadFile dfile = new DownloadFile();
		
		
		XSSFWorkbook wb;
		try {
			
			wb = new XSSFWorkbook(newfile);
			XSSFSheet st = wb.getSheetAt(0); 			
			XSSFCellStyle cellStyle = wb.createCellStyle();
			XSSFDataFormat df = wb.createDataFormat();
			cellStyle.setDataFormat(df.getFormat("0.000"));
			
			int indexRow = 1;
			int indexColumn = 0;
			for (PStory pstory : pstories)
			{
			
				XSSFRow row = st.createRow(indexRow++);
				
				indexColumn = 0;
				row.createCell(indexColumn++).setCellValue(pstory.getDepartment());
				row.createCell(indexColumn++).setCellValue(pstory.getGroup());
				row.createCell(indexColumn++).setCellValue(pstory.getGroupLeader());
				row.createCell(indexColumn++).setCellValue(pstory.getProjectName());
				row.createCell(indexColumn++).setCellValue(pstory.getUserStorySource());
				row.createCell(indexColumn++).setCellValue(pstory.getUserStoryName());
				
				row.createCell(indexColumn++).setCellValue(pstory.getUserStoryType());
				row.createCell(indexColumn++).setCellValue(pstory.getUserStoryRank());
				
				row.createCell(indexColumn++).setCellValue(pstory.getStoryOwner());
							
				XSSFCell cell = row.createCell(indexColumn++);
				cell.setCellStyle(cellStyle);			
				cell.setCellValue(Double.parseDouble(pstory.getStoryWorkTime()));
				
				row.createCell(indexColumn++).setCellValue(pstory.getStoryStartTime());
				
				row.createCell(indexColumn++).setCellValue(pstory.getStoryRealFinishTime());
				
				row.createCell(indexColumn++).setCellValue(pstory.getStoryPlanFinishTime());
				
				row.createCell(indexColumn++).setCellValue(pstory.getUserStoryState());
				
				row.createCell(indexColumn++).setCellValue(pstory.getCurrentState());
				row.createCell(indexColumn++).setCellValue(pstory.getRemark());
				row.createCell(indexColumn++).setCellValue(pstory.getJiraId());
				row.createCell(indexColumn++).setCellValue(pstory.getStoryID());
				
			}
					
			fileName = "Data"+sdf.format(new Date())+String.valueOf(Math.random()).substring(3,8);
			String targetpath = projectPath+"/WEB-INF/classes/"+fileName+".xlsx";
			FileOutputStream fs = new FileOutputStream(targetpath);
			
			dfile.setFileName(fileName);
			dfile.setFileType("xlsx");
			dfile.setFileFullName(fileName+"."+"xlsx");
			dfile.setFileFullPath(targetpath);
		
			wb.write(fs);
		
			fs.close();
			wb.close();		
			newfile.delete();
						
		} catch (InvalidFormatException | IOException e) {			
			e.printStackTrace();
		}
		
		
		return dfile;
	}
}
