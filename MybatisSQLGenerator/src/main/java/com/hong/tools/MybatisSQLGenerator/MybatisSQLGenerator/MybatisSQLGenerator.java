package com.hong.tools.MybatisSQLGenerator.MybatisSQLGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MybatisSQLGenerator {

	public static void main(String[] args) {
		generator();

	}

	private static void generator() {
		try {
			MybatisSQLGenerator mybatisSQLGenerator = new MybatisSQLGenerator();
			List<File> javaFiles = MybatisSQLGenerator.readFiles();

			mybatisSQLGenerator.generateXmls(javaFiles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<File> readFiles() {
		// 读取java的bean文件，生成mapper文件
		File file = new File("documents");
		if (!file.exists()) {
			System.out.println("directionary of '" + file + "' in projectroot is not exists, please check!");
			// file.mkdir();
			return null;
		}

		File[] files = file.listFiles();
		List<File> fileList = new ArrayList<>(Arrays.asList(files));
//		fileList.stream().forEach(fileoo -> {
//			System.out.println(fileoo.getPath());// getAbsolutePath()
//		});
		fileList = fileList.stream().filter(javaFile -> javaFile.getName().endsWith(".java"))
				.collect(Collectors.toList());
		fileList.stream().forEach(fileoo -> {
			System.out.println(fileoo.getPath());// getAbsolutePath()
		});
		return fileList;
	}

	private void generateXmls(List<File> javaFiles) throws Exception {
		for (File javaFile : javaFiles) {
			List<String> lines = readlines1(javaFile);
			writeXml(javaFile, lines);
		}

	}

	private List<String> readlines1(File javaFile) throws Exception {
		List<String> lines = new ArrayList<>();
		FileReader fr = new FileReader(javaFile.getPath());
		BufferedReader bf = new BufferedReader(fr);

		while (true) {
			String nextline = bf.readLine();
			if (nextline == null) {
				break;
			}
			nextline = nextline.trim();
			// System.out.println(nextline);
			// TODO 暂时无法处理内部类
			if (nextline.startsWith("public class") || nextline.startsWith("class") 
					|| nextline.startsWith("private") || nextline.startsWith("package")) {
				lines.add(nextline);
			}

		}

		bf.close();
		return lines;
	}

	private List<String> readlines2(File javaFile) throws Exception {

		return null;
	}

	private void writeXml(File javaFile, List<String> lines) throws Exception {
		String javaFilename = javaFile.getName();
		String xmlFileName = javaFilename.substring(0, javaFilename.length() - 5) + "Mapper.xml";
		File xmlFile = new File(javaFile.getParent() + "/" + xmlFileName);
		xmlFile.createNewFile();
		String separator = System.getProperty("line.separator");
		FileWriter fw = new FileWriter(xmlFile);
//		lines.stream().forEach(line -> {
//			try {
//				fw.write(line);
//				fw.write(separator);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});

		// bean类名
		String className = "";
		// 完整的包名
		String fullpackageName = "";
		// 属性列表，结果以 type,attrName" 的形式出现
		//List<Map<String, String>> attrList = new ArrayList<>();// Long id
		// type-attrName
		List<String> attrList = new ArrayList<>();// Long id
		for (String line : lines) {
			//System.out.println(line);
			// 防止属性后面有"// xxx"这样的注释：private BigDecimal lackgoodsmoney;//缺货金额
			line = line.split(";")[0];
			String[] str = line.split(" ");
			if (line.startsWith("public class") || line.startsWith("class")) {
				for (int i = 0; i < str.length; i++) {
					if ("class".equals(str[i])) {
						className = str[i + 1];
						break;
					}
				}
				// System.out.println("className = "+className);
			}else if(line.startsWith("package")) {
				// package com.suneee.scn.sale.model.dbo;
				fullpackageName = str[1].replace(";", "");
			}else if(!line.contains("serialVersionUID")) { // 排除 serialVersionUID
				//private String tid;  结果以 "type-attrName" 的形式出现
//				Map<String, String> attrMap = new HashMap<>();
//				attrMap.put(str[1], str[2]);
				if(str.length > 3) {
					attrList.add(str[1] + "-" + str + "?? unexpected column");
				}
				attrList.add(str[1] + "-" + str[2]);
			}
		}
		// 开始写入文件
		generateFileHead(separator, fw);
		// BaseResultMap
		generateBaseResultMap(separator, fw, className, fullpackageName, attrList);
		
		// Base_Column_List
		generateBaseColumnList(separator, fw, attrList);
		// insert
		generateInsert(separator, fw, className, fullpackageName, attrList);
			
		// update
		generateUpdate(separator, fw, className, fullpackageName, attrList);

		fw.write("</mapper>");
		fw.close();
	}

	private void generateFileHead(String separator, FileWriter fw) throws IOException {
		fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		fw.write(separator);
		fw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
		fw.write(separator);
		fw.write("<mapper namespace=\"{{DAOPACKAGENAME.dao.XXXXXDAO}}\" >");
		fw.write(separator);
		fw.write(separator);
		fw.write("<!-- 请务必检查集合类型、数组类型以及将其它类作为属性的字段，按需修改 -->");
		fw.write(separator);
		fw.write("<!-- 另外请务必请关注{{}}和\\\"??\\\"的部分 -->");
		fw.write(separator);
		fw.write(separator);
	}

	private void generateInsert(String separator, FileWriter fw, String className, String fullpackageName,
			List<String> attrList) throws IOException {
		fw.write("  <insert id=\"insert" + className + "\"" + " parameterType=\"" + fullpackageName + "."
				+ className + "\" >");
		fw.write(separator);
		fw.write("    INSERT INTO {{?? Target table name}}");
		fw.write(separator);
		fw.write("    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
		fw.write(separator);
		for(String attr : attrList) {//"type-attrName"
			String attrStr = attr.split("-")[1];
			fw.write("      <if test=\"" + attrStr + " != null\" >");
			fw.write(separator);
			fw.write("        "+attrStr.toLowerCase() + ",");
			fw.write(separator);
			fw.write("      </if>");
			fw.write(separator);
		}
		fw.write("    </trim>");
		fw.write(separator);
		fw.write("    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
		fw.write(separator);
		for(String attr : attrList) {//"type-attrName"
			String type = attr.split("-")[0];
			String attrStr = attr.split("-")[1];
			fw.write("      <if test=\"" + attrStr + " != null\" >");
			fw.write(separator);
			fw.write("        #{"+attrStr + ", jdbcType=");
			if(isNumeric(type)) {
				fw.write("NUMERIC},");
			}else if(type.equals("String")) {
				fw.write("VARCHAR},");
			}else if(type.equals("Date")) {
				fw.write("TIMESTAMP},");
			}else {
				fw.write("{{?? unknow type}} },");
			}
			fw.write(separator);
			fw.write("      </if>");
			fw.write(separator);
		}
		fw.write("    </trim>");
		fw.write(separator);
		fw.write("  </insert>");
		fw.write(separator);
		fw.write(separator);
	}

	private void generateUpdate(String separator, FileWriter fw, String className, String fullpackageName,
			List<String> attrList) throws IOException {
		fw.write("  <update id=\"update" + className + "\"" + " parameterType=\"" + fullpackageName + "."
				+ className + "\" >");
		fw.write(separator);
		fw.write("  <!-- parameterType=\"java.util.Map\" -->");
		fw.write(separator);
		fw.write("    UPDATE {{?? Target table name}}");
		fw.write(separator);
		fw.write("    <set>");
		fw.write(separator);
		for(String attr : attrList) {//"type-attrName"
			String type = attr.split("-")[0];
			String attrStr = attr.split("-")[1];
			fw.write("      <if test=\"" + attrStr + " != null\" >");
			fw.write(separator);
			fw.write("        " + attrStr + " = #{" + attrStr + ", jdbcType=");
			if(isNumeric(type)) {
				fw.write("NUMERIC},");
			}else if(type.equals("String")) {
				fw.write("VARCHAR},");
			}else if(type.equals("Date")) {
				fw.write("TIMESTAMP},");
			}else {
				fw.write("{{?? unknow type}} },");
			}
			fw.write(separator);
			fw.write("      </if>");
			fw.write(separator);
		}
		fw.write("    <set>");
		fw.write(separator);
		fw.write("    WHERE 1=1");
		fw.write(separator);
		for(String attr : attrList) {//"type-attrName"
			String type = attr.split("-")[0];
			String attrStr = attr.split("-")[1];
			fw.write("      <if test=\"" + attrStr + " != null\" >");
			fw.write(separator);
			fw.write("        AND " + attrStr + " = #{" + attrStr + ", jdbcType=");
			if(isNumeric(type)) {
				fw.write("NUMERIC},");
			}else if(type.equals("String")) {
				fw.write("VARCHAR},");
			}else if(type.equals("Date")) {
				fw.write("TIMESTAMP},");
			}else {
				fw.write("{{?? unknow type}} },");
			}
			fw.write(separator);
			fw.write("      </if>");
			fw.write(separator);
		}
		fw.write("  </update>");
		fw.write(separator);
		fw.write(separator);
	}

	private void generateBaseColumnList(String separator, FileWriter fw, List<String> attrList) throws IOException {
		fw.write(separator);
		fw.write("  <sql id=\"Base_Column_List\" >");
		fw.write(separator);
		{// a block
			int i = 0;
			int totle = 0;
			fw.write("    ");
			for (String attr : attrList) {
				String attrStr = attr.split("-")[1].toLowerCase();
				totle++;
				if(totle == attrList.size()) {
					fw.write(attrStr);
				}else {
					fw.write(attrStr+", ");
				}
				if(i == 4) {
					i = 0;
					fw.write(separator);
					fw.write("    ");
				}
				i++;
			}
		}
		fw.write(separator);
		fw.write("  </sql>");
		fw.write(separator);
		fw.write(separator);
	}

	private void generateBaseResultMap(String separator, FileWriter fw, String className, String fullpackageName,
			List<String> attrList) throws IOException {
		fw.write("  <resultMap id=\"BaseResultMap\" type=\""+fullpackageName+"."+className+"\">");
		fw.write(separator);
		fw.write("<!-- <id column=\"tid\" property=\"tid\" jdbcType=\"VARCHAR\" /> -->");
		fw.write(separator);
		for (String attr : attrList) {
			String type = attr.split("-")[0];
			String attrStr = attr.split("-")[1];
			fw.write("    <result column=\"");
			fw.write(attrStr.toLowerCase());
			fw.write("\" property=\"");
			fw.write(attrStr);
			if(isNumeric(type)) {
			fw.write("\" jdbcType=\"NUMERIC\" />");
			}else if(type.equals("String")) {
				fw.write("\" jdbcType=\"VARCHAR\" />");
			}else if(type.equals("Date")) {
				fw.write("\" jdbcType=\"DATE\" />");
			}else {
				fw.write("\" jdbcType=\"{{jdbcType ?? }}\" />");
			}
			fw.write(separator);
		}
		fw.write("  </resultMap>");
		fw.write(separator);
		fw.write(separator);
	}

	/**
	 * 判断该属性类型是不是varchar类型的：Long/long,Integer/int,Short/short,BigDecimal
	 * @param type
	 * @return
	 */
	private boolean isNumeric(String type) {
		if("LonglongIntegerintShortshortBigDecimal".contains(type)) {
			return true;
		}
		return false;
	}

}
