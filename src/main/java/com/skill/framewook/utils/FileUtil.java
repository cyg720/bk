package com.skill.framewook.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

/**
 * @Description: 文件操作工具类
 * @author 王道兵
 */
public class FileUtil {

	public static final Logger	ERROR_LOG	= LoggerFactory.getLogger("his-error");

	private FileUtil() {
	};

	private static String	basePath	= System.getProperty("user.dir") + File.separator;

	/**
	 * 追加写入文件
	 * 
	 * @param path
	 *            文件绝对路径
	 * @param text
	 *            要追加的文件内容
	 * @return
	 */
	public static boolean appendText(String path, String text) {
		BufferedWriter writer = null;
		try {
			File file = new File(path);
			writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(text);
			return true;
		} catch (Exception e) {
			ERROR_LOG.error("写文件出错", e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 系统的默认目录为当前用户目录，可通过此函数重新设置
	 * 
	 * @param basePath
	 */
	public static void setBasePath(String basePath) {
		Assert.notNull(basePath);
		if (!basePath.trim().endsWith(File.separator)) {
			basePath += File.separator;
		}
		FileUtil.basePath = basePath;
	}

	/**
	 * 获取web根目录下面的资源的绝对路径
	 * 
	 * @param path
	 *            相对应WEB根目录的路径
	 * @return 绝对路径
	 */
	public static String getAbsolutePath(String path) {
		Assert.notNull(path);
		// 在windows下，如果路径包含：,为绝对路径，则不进行转换
		if (path.contains(":")) {
			return path;
		}
		if (path != null && path.trim().length() == 1) {
			return basePath;
		}
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		path = basePath + path.replace("/", File.separator);
		return path;
	}

	/**
	 * 拷贝文件
	 * @param inFile 源文件
	 * @param outFile 新文件
	 */
	public static void copyFile(File inFile, File outFile) {
		try {
			copyFile(new FileInputStream(inFile), outFile);
		} catch (FileNotFoundException ex) {
			ERROR_LOG.error("文件不存在", ex);
		}
	}

	/** 拷贝文件
	 * @param in 文件输出流
	 * @param outFile 文件输入流
	 */
	public static void copyFile(InputStream in, File outFile) {
		OutputStream out = null;
		try {
			byte[] data = readAll(in);
			out = new FileOutputStream(outFile);
			out.write(data, 0, data.length);
			out.close();
		} catch (Exception ex) {
			ERROR_LOG.error("文件操作失败", ex);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ERROR_LOG.error("文件操作失败", ex);
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				ERROR_LOG.error("文件操作失败", ex);
			}
		}
	}

	/**
	 * 读取文件内容并比byte数组的格式返回
	 * @param file
	 * @return
	 */
	public static byte[] readAll(File file) {
		try {
			return readAll(new FileInputStream(file));
		} catch (Exception ex) {
			ERROR_LOG.error("读取文件失败", ex);
		}
		return null;
	}

	/** 
	 * 读取输出流的内容，并以byte数组的格式返回
	 * @param in
	 * @return
	 */
	public static byte[] readAll(InputStream in) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			for (int n; (n = in.read(buffer)) > 0;) {
				out.write(buffer, 0, n);
			}
		} catch (IOException ex) {
			ERROR_LOG.error("读取文件失败", ex);
		} finally {
			try {
				out.close();
			} catch (IOException ex) {
				ERROR_LOG.error("文件操作失败", ex);
			}
		}
		return out.toByteArray();
	}

	/**
	 * 获取一个资源路径的输出流。
	 * @param path 资源路径
	 * @return 资源路径未找到时返回null。
	 */
	public static FileInputStream getInputStream(String path) {
		try {
			return new FileInputStream(getAbsolutePath(path));
		} catch (FileNotFoundException ex) {
			ERROR_LOG.error("文件没有找到", ex);
		}
		return null;
	}

	/**
	 * 判断一个路径的资源是否存在。
	 * @param path 路径
	 * @return 存在返回true，反之为false。
	 */
	public static boolean existsFile(String path) {
		try {
			File file = new File(getAbsolutePath(path));
			if (file.exists()) {
				return true;
			}
		} catch (Exception ex) {
			ERROR_LOG.error("文件操作失败", ex);
		}
		return false;
	}

	/**
	 * 根据指定的路径创建一个新文件，并写人数据。
	 * @param path 路径
	 * @param data 要写入的数据
	 * @return 如果创建
	 */
	public static File createAndWriteFile(String path, byte[] data) {
		OutputStream out = null;
		try {
			File file = new File(getAbsolutePath(path));
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			out = new FileOutputStream(file);
			out.write(data, 0, data.length);
			return file;
		} catch (Exception ex) {
			ERROR_LOG.error("文件操作失败", ex);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
				ERROR_LOG.error("文件操作失败", ex);
			}
		}
		return null;
	}
	/**
	 * 根据指定的路径创建一个新文件，并写人数据。
	 * @param path 路径
	 * @param data 要写入的数据
	 * @return 如果创建
	 */
	public static File createAndWriteFile(String path, String text) {
		BufferedWriter writer = null;
		try {
			File file = new File(getAbsolutePath(path));
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			writer.write(text);
			return file;
		} catch (Exception ex) {
			ERROR_LOG.error("文件操作失败", ex);
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException ex) {
				ERROR_LOG.error("文件操作失败", ex);
			}
		}
		return null;
	}
	/**
	 * 根据指定的路径创建一个新文件，并写人数据。
	 * @param path 路径
	 * @param in 要写入的文件输入流
	 * @return 如果创建
	 */
	public static File createAndWriteFile(String path, InputStream in) {
		OutputStream out = null;
		try {
			File file = new File(getAbsolutePath(path));
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			out = new FileOutputStream(file);
			 byte buffer[]=new byte[1024];
			 int byteread = 0;
			 while((byteread=in.read(buffer))!=-1){
	               out.write(buffer,0,byteread);        
	         }
			return file;
		} catch (Exception ex) {
			ERROR_LOG.error("文件操作失败", ex);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
				ERROR_LOG.error("文件操作失败", ex);
			}
		}
		return null;
	}

	/**
	 * 创建文件写入数据
	 * @param path 路径
	 * @param in 要写入的文件输入流
	 * @return
	 */
	public static File createFile(String path, InputStream in){
		OutputStream out = null;
		try {
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			out = new FileOutputStream(file);
			byte buffer[]=new byte[1024];
			int byteread = 0;
			while((byteread=in.read(buffer))!=-1){
	              out.write(buffer,0,byteread);        
	        }
			return file;
		} catch (Exception ex) {
			ERROR_LOG.error("文件操作失败", ex);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
				ERROR_LOG.error("文件操作失败", ex);
			}
		}
		return null;
	}
	
	
	/**
	 * 删除文件。
	 * @param path 文件路径
	 * @return 成功返回true，失败返回false
	 */
	public static boolean removeFile(String path) {
		try {
			File file = new File(getAbsolutePath(path));
			if (file.exists()) {
				file.delete();
			}
			return true;
		} catch (Exception ex) {
			ERROR_LOG.error("文件操作失败", ex);
		}
		return false;
	}

	/**
	 * 获取文件的内容，
	 * @param path 文件路径
	 * @return Collection对象。 Collection中每条数据为一行。
	 */
	public static Collection<String> getTextFileContent(String path) {
		return getTextFileContent(getInputStream(path));
	}

	public static Collection<String> getTextFileContent(InputStream in) {
		Collection<String> result = new LinkedHashSet<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = reader.readLine();
			while (line != null) {
				// 忽略空行和以#号开始的注释行
				if (!"".equals(line.trim()) && !line.trim().startsWith("#")) {
					result.add(line);
				}
				line = reader.readLine();
			}
		} catch (UnsupportedEncodingException ex) {
			ERROR_LOG.error("不支持的编码", ex);
		} catch (IOException ex) {
			ERROR_LOG.error("文件操作失败", ex);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ex) {
				ERROR_LOG.error("文件操作失败", ex);
			}
		}
		return result;
	}

	/**
	 * 获取类路径下的文件内容
	 * @param path path 文件路径
	 * @return Collection对象。 Collection中每条数据为一行。
	 */
	public static Collection<String> getClassPathTextFileContent(String path) {
		try {
			ClassPathResource cr = new ClassPathResource(path);
			return getTextFileContent(cr.getInputStream());
		} catch (IOException ex) {
			ERROR_LOG.error("文件操作失败", ex);
		}
		return null;
	}
}