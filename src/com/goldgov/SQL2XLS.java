package com.goldgov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class SQL2XLS {
	private File sqlFile=null;
	private BufferedReader bf=null;
	private FileReader fr =null;
	// Map<表名,<列明,列详细>>
	private Map<String, LinkedHashMap<String, Column>> tables=new LinkedHashMap<String, LinkedHashMap<String,Column>>();
	@SuppressWarnings("unused")
	private SQL2XLS(){}
	
	public SQL2XLS(String sqlPath){
		try {
			sqlFile=new File(sqlPath);
			fr = new FileReader(sqlFile);
			bf = new BufferedReader(fr);
			read();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Map<String, LinkedHashMap<String, Column>>getTables(){
		return tables;
	}
	
	private void read() throws Exception{
		String line=null;
		String tableName=null;
		while ((line=readLine())!=null) {
			if(line.startsWith("CREATE TABLE")){
				//新的表
				tableName=line.replaceAll("CREATE TABLE", "").replaceAll("\\(", "").replaceAll("/s","");
				LinkedHashMap<String,Column> colMap=new LinkedHashMap<>();
				aa: 
				while ((line=readLine())!=null) {
					if(line.indexOf("PRIMARY KEY")!=-1){
						//主键
						String pkName=line.substring(line.indexOf("(")+1, line.lastIndexOf(")"));
						colMap.get(pkName).setSole(1);//主键是唯一
						continue aa;
					}else if(line.equals(")ENGINE=INNODB") || line.equals(")ENGINE=MYISAM") || line.equals(")")){
						//表结束
						break aa; 
					}
					Column col=new Column();
					//去除多余空格 仅留一个空格
					while(line.indexOf("  ")!=-1){
						line=line.trim().replaceAll("  ", " ");
					}
					line=line.replace(",", "");
					String[] strs=line.split(" ");
					col.setColumnName(strs[0]);
					col.setColumnType(strs[1]);
					if(strs.length==2){
						col.setNotNull(1);
					}
					colMap.put(col.getColumnName(),col);
				}
				tables.put(tableName, colMap);
			}else if(line.startsWith("COMMENT ON COLUMN")){
				//标注释
//				System.out.println(line);
				String zs[]=line.substring(line.indexOf(".")+1).replaceAll("'","").split(" IS ");
				tables.get(tableName).get(zs[0]).setColumnAnnotation(zs[1]);;//主键是唯一
			}
		}
	}
	
	/**获取一行内容 若为空则接着读下一条*/
	private String readLine() throws Exception{
		String line=bf.readLine();
		while(line!=null&&"".equals(line)){
			line=bf.readLine();
		}
		return line;
	}
	private void close() throws Exception{
		if(bf!=null)bf.close();
		if(fr!=null)fr.close();
	}
	
}
