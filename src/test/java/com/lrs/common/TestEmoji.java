package com.lrs.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import com.vdurmont.emoji.EmojiParser.FitzpatrickAction;

/**
 * Emoji(絵文字)
 * 
 * 相关文章：http://www.ruanyifeng.com/blog/2017/04/emoji.html
 * 
 * Emoji in java : https://github.com/vdurmont/emoji-java
 * 
 * Emoji in Unicode: http://www.unicode.org/emoji/charts/full-emoji-list.html
 * 
 * @author Swedish-li
 *
 */
public class TestEmoji {
	@Test
	public void simple() {
		// 冒号之间的为Emoji别名
		String str = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
		// 将颜文字转换为Unicode字符
		String result = EmojiParser.parseToUnicode(str);
		println(result);
		// Prints:
		// "An 😀awesome 😃string 😄with a few 😉emojis!"
	}

	// 将Emoji的Unicodes形式替换为别名
	@Test
	public void replaceUnicode2Aliases() {
		String string = "An 😀awesome 😃string 😄with a few 😉emojis!";
		String rs = EmojiParser.parseToAliases(string);
		// An :grinning:awesome :smiley:string :smile:with a few :wink:emojis!
		println(rs);
	}

	@Test
	public void testFitzpatrick() {
		String str = "Here is a boy: \uD83D\uDC66\uD83C\uDFFF!";
		println(str);
		//parse to Alias
		println(EmojiParser.parseToAliases(str));
		println(EmojiParser.parseToAliases(str, FitzpatrickAction.PARSE));
		
		println(EmojiParser.parseToAliases(str, FitzpatrickAction.IGNORE));
		println(EmojiParser.parseToAliases(str, FitzpatrickAction.REMOVE));
		
		
	}
	// 将Emoji转换为Html的表示方法
	@Test
	public void test2Html() {
		String str = "An 😀awesome 😃string with a few 😉emojis!";
		// 转换为十进制表示
		println(EmojiParser.parseToHtmlDecimal(str));
		
		// 转换为十六进制表示
		println(EmojiParser.parseToHtmlHexadecimal(str));
		
	}
	
	// 将Emoji从字符串中 移除
	@Test
	public void testRemoveEmoji() {
		String str = "An 😀awesome 😃string with a few 😉emojis!";
		// 移除所有
		println(EmojiParser.removeAllEmojis(str));
		// 移除非集合中的Emoji
		List<Emoji> exceptList = new ArrayList<>();
		exceptList.add(EmojiManager.getForAlias("wink"));
		println(EmojiParser.removeAllEmojisExcept(str, exceptList));
		
		// 移除集合中的Emoji
		println(EmojiParser.removeEmojis(str, exceptList));
	}

	private void println(Object ob) {
		System.out.println(ob);
	}

	/**
	 * EmojiManager 查找 Emoji(絵文字)
	 */
	@Test
	public void testEmojiManager() {
		// 使用别名获取
		Emoji emoji = EmojiManager.getForAlias("coffee");
		System.out.println("coffee:" + emoji.getUnicode());

		// 根据标签获取emoji [letter, p]
		Collection<Emoji> emojis = EmojiManager.getForTag("p");
		for (Emoji emoji2 : emojis) {
			println(emoji2.getTags());
		}

	}
}
