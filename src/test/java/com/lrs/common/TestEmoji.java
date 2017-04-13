package com.lrs.common;

import java.util.Collection;

import org.junit.Test;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;

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
		// 冒号之间的为颜文字别名
		String str = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
		// 将颜文字转换为Unicode字符
		String result = EmojiParser.parseToUnicode(str);
		System.out.println(result);
		// Prints:
		// "An 😀awesome 😃string 😄with a few 😉emojis!"
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
			System.out.println(emoji2.getTags());
		}
		
	}
}
