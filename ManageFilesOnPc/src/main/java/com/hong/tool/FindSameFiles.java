package com.hong.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 找到指定目录下的所有文件，记录hash值/md5值，按其进行排列，找到重复的文件<br/>
 * 计算MD5采用Google的guava工具计算，需使用多线程，采用多线程完成计算
 * 
 * @author hongw
 *
 */
public abstract class FindSameFiles {

	/**
	 * 递归找到指定目录下的所有文件
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Map<String,Map<String,String>> findFiles(String path) throws IOException {
		//找到所有的文件，路径存放在list中
		List<String> list = findFilesInPath(path);
		// 启用多线程，遍历计算文件的md5

		return null;
	}

	/**
	 * 找到所有的文件，路径存放在list中
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static List<String> findFilesInPath(String path) throws IOException {
		List<String> list = new ArrayList<>();
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (null != files) {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						System.out.println("文件夹:" + file2.getAbsolutePath());
						list.addAll(findFilesInPath(file2.getAbsolutePath()));
					} else {
						// Map<String, String> fileInfo = new HashMap<>();

						String absolutePath = file2.getAbsolutePath();
						list.add(absolutePath);
						System.out.println("文件:" + absolutePath);

						String md5Hashcode = MD5Util.getFileMD5(new File(absolutePath));
						System.out.println("mdd5 = " + md5Hashcode);

					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
		return list;
	}
}
