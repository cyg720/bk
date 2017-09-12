package com.skill.framewook.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 
 * @Description: 字符串工具类
 * @author 王道兵
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class StringUtil {

	public static String removeCrlf(String str) {
		if (str == null)
			return null;
		return StringUtil.join(StringUtil.tokenizeToStringArray(str, "\t\n\r\f"), " ");
	}

	/**
	 * 从一个字符串中移除之定义的前缀字符
	 * 
	 * @param str
	 * @param prefix
	 * @return
	 */
	public static String removePrefix(String str, String prefix) {
		if (str == null)
			return null;
		if (str.startsWith(prefix)) {
			return str.substring(prefix.length());
		}
		return str;
	}

	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 获取文件扩展名称
	 * 
	 * @param str
	 * @return
	 */
	public static String getExtension(String str) {
		if (str == null)
			return null;
		int i = str.lastIndexOf('.');
		if (i >= 0) {
			return str.substring(i + 1);
		}
		return null;
	}

	/**
	 * 一个字符串中是否包含制定的子串
	 * 
	 * @param str
	 * @param keywords
	 * @return
	 */
	public static boolean contains(String str, String... keywords) {
		if (str == null)
			return false;
		if (keywords == null)
			throw new IllegalArgumentException("'keywords' must be not null");

		for (String keyword : keywords) {
			if (str.contains(keyword.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回对象默认值。如果对象为空返回“”，反之为对象的toString();
	 * 
	 * @param value
	 * @return
	 */
	public static String defaultString(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	/**
	 * 判断对象是否为空，如果为空返回默认值，反之返回对象的toString();
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static String defaultIfEmpty(Object value, String defaultValue) {
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		return value.toString();
	}

	/**
	 * 按照指定的字符将原始字符串分割为字符数组。 将字符数组中的每个字符串首字符大写并连接。 user_name >UserName
	 * 
	 * @param sqlName
	 * @return
	 */
	public static String removeSeperatorFirstLetterUpperCase(String str, String seperator) {
		String[] strs = str.toLowerCase().split(seperator);
		// String[] strs = tokenizeToStringArray(str, seperator);
		String result = "";
		String preStr = "";
		for (int i = 0; i < strs.length; i++) {
			if (preStr.length() == 1) {
				result += strs[i];
			} else {
				result += capitalize(strs[i]);
			}
			preStr = strs[i];
		}
		return result;
	}

	/**
	 * 将字符串中指定的字符替换为新字符
	 * 
	 * @param inString
	 *            原字符串
	 * @param oldPattern
	 *            要替换的字符
	 * @param newPattern
	 *            新字符
	 * @return
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		int pos = 0;
		int index = inString.indexOf(oldPattern);
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		return sbuf.toString();
	}

	/**
	 * 转将字符串第一个字符转大写字符
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * 转将字符串第一个字符转小写字符
	 * 
	 * @param str
	 * @return
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	/**
	 * 第一个字符大小写转换
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param capitalize
	 *            为true时，小转大写。false时，大写转小写
	 * @return
	 */
	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	private static final Random RANDOM = new Random();

	/**
	 * 生产指定位数的随机数字符串
	 * 
	 * @param count
	 * @return
	 */
	public static String randomNumeric(int count) {
		return random(count, false, true);
	}

	/**
	 * 生产指定位数的随机字符串(不包含数字字符)
	 * 
	 * @param count
	 * @return
	 */
	public static String randomString(int count) {
		return random(count, true, false);
	}

	/**
	 * 生产指定位数的随机字符串
	 * 
	 * @param count
	 * @return
	 */
	public static String random(int count) {
		return random(count, true, false);
	}

	/**
	 * 产生指定位数的随机字符
	 * 
	 * @param count
	 *            位数
	 * @param letters
	 *            是否允许字符
	 * @param numbers
	 *            是否出现数字字符
	 * @return
	 */
	public static String random(int count, boolean letters, boolean numbers) {
		return random(count, 0, 0, letters, numbers);
	}

	private static String random(int count, int start, int end, boolean letters, boolean numbers) {
		return random(count, start, end, letters, numbers, null, RANDOM);
	}

	/**
	 * 从spring 拷贝
	 * 
	 * @param count
	 * @param start
	 * @param end
	 * @param letters
	 * @param numbers
	 * @param chars
	 * @param random
	 * @return
	 */
	private static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars,
			Random random) {
		if (count == 0) {
			return "";
		} else if (count < 0) {
			throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
		}
		if ((start == 0) && (end == 0)) {
			end = 'z' + 1;
			start = ' ';
			if (!letters && !numbers) {
				start = 0;
				end = Integer.MAX_VALUE;
			}
		}

		char[] buffer = new char[count];
		int gap = end - start;

		while (count-- != 0) {
			char ch;
			if (chars == null) {
				ch = (char) (random.nextInt(gap) + start);
			} else {
				ch = chars[random.nextInt(gap) + start];
			}
			if ((letters && Character.isLetter(ch)) || (numbers && Character.isDigit(ch)) || (!letters && !numbers)) {
				if (ch >= 56320 && ch <= 57343) {
					if (count == 0) {
						count++;
					} else {
						// low surrogate, insert high surrogate after putting it
						// in
						buffer[count] = ch;
						count--;
						buffer[count] = (char) (55296 + random.nextInt(128));
					}
				} else if (ch >= 55296 && ch <= 56191) {
					if (count == 0) {
						count++;
					} else {
						// high surrogate, insert low surrogate before putting
						// it in
						buffer[count] = (char) (56320 + random.nextInt(128));
						count--;
						buffer[count] = ch;
					}
				} else if (ch >= 56192 && ch <= 56319) {
					// private high surrogate, no effing clue, so skip it
					count++;
				} else {
					buffer[count] = ch;
				}
			} else {
				count++;
			}
		}
		return new String(buffer);
	}

	/**
	 * 将驼峰法命名的字符串变更为下划线分割的小写字符串。
	 * 
	 * @param name
	 * @return
	 */
	public static String toUnderscoreName(String name) {
		return addSeperatorFirstLetterUpperCase(name, "_");
	}

	/**
	 * 将驼峰法命名的字符串按指定的分割符拆分单词，并将单词首字符小写
	 * 
	 * @param name
	 * @param seperator
	 * @return
	 */
	public static String addSeperatorFirstLetterUpperCase(String name, String seperator) {
		if (name == null)
			return null;
		String filteredName = name;
		if (name.equals(name.toUpperCase())) {
			filteredName = filteredName.toUpperCase();
		}

		StringBuffer result = new StringBuffer();
		if (filteredName != null && filteredName.length() > 0) {
			result.append(filteredName.substring(0, 1).toLowerCase());
			for (int i = 1; i < filteredName.length(); i++) {
				String preChart = filteredName.substring(i - 1, i);
				String c = filteredName.substring(i, i + 1);
				if (c.equals(seperator)) {
					result.append(seperator);
					continue;
				}
				if (preChart.equals(seperator)) {
					result.append(c.toLowerCase());
					continue;
				}
				if (c.matches("\\d")) {
					result.append(c);
				} else if (c.equals(c.toUpperCase())) {
					result.append(seperator);
					result.append(c.toLowerCase());
				} else {
					result.append(c);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 从一个字符末尾移除指定的子串
	 * 
	 * @param inputString
	 * @param endWiths
	 * @return
	 */
	public static String removeEndWiths(String inputString, String... endWiths) {
		for (String endWith : endWiths) {
			if (inputString.endsWith(endWith)) {
				return inputString.substring(0, inputString.length() - endWith.length());
			}
		}
		return inputString;
	}

	/**
	 * 判断一个字符中从指定的位置开始是否包含指定的子串
	 * 
	 * @param str
	 * @param index
	 *            起始位置。默认从0代表第一个字符以此类推。
	 * @param substring
	 * @return
	 */
	public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = index + j;
			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将一个字符串按自定的分割符分割为字符数组
	 * 
	 * @param str
	 * @param seperators
	 * @return
	 */
	public static String[] tokenizeToStringArray(String str, String seperators) {
		if (str == null)
			return new String[0];
		StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
		List result = new ArrayList();

		while (tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}
		return (String[]) result.toArray(new String[result.size()]);
	}

	/**
	 * 使用指定的分割符连接字符串
	 * 
	 * @param array
	 *            字符串集合
	 * @param seperator
	 *            分割符
	 * @return
	 */

	public static String join(List list, String seperator) {
		return join(list.toArray(new Object[0]), seperator);
	}

	/**
	 * 使用指定的分割符连接字符串
	 * 
	 * @param array
	 *            字符串数组
	 * @param seperator
	 *            分割符
	 * @return
	 */
	public static String join(Object[] array, String seperator) {
		if (array == null)
			return null;
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			result.append(array[i]);
			if (i != array.length - 1) {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	/**
	 * 一个字符串中包含子串的个数，没有包含返回0
	 * 
	 * @param string
	 * @param keyword
	 * @return
	 */
	public static int containsCount(String str, String sub) {
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 将字符串按指定的位数分开
	 * 
	 * @param str
	 *            要分隔的字符串
	 * @param split
	 *            分隔符
	 * @param length
	 *            分隔长度
	 * @return
	 */
	public static String splitString(String str, String split, int length) {
		int len = str.length();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if (i % length == 0 && i > 0) {
				temp.append(split);
			}
			temp.append(str.charAt(i));
		}
		String[] attrs = temp.toString().split(split);
		StringBuilder finalMachineCode = new StringBuilder();
		for (String attr : attrs) {
			if (attr.length() == length) {
				finalMachineCode.append(attr).append(split);
			}
		}
		String result = finalMachineCode.toString().substring(0, finalMachineCode.toString().length() - 1);
		return result;
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//]<>/?~！@#￥%……&*（）——|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 写sql模糊查询时转义特俗字符
	 * 
	 * @param raw
	 * @return
	 */
	public static String escape(String raw) {
		return raw.replace("'", "''").replace("%", "\\%").replace("_", "\\_");
	}

	/**
	 * 判断对象是否为空，如果为空返回默认值，反之返回对象的toString();
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static String nullIfEmpty(String value) {
		if (value == "null" || "".equals(value)) {
			return null;
		}
		return value;
	}

}
