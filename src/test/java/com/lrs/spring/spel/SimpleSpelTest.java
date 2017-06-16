package com.lrs.spring.spel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.lrs.spring.spel.model.Inventor;

/**
 * Spring Expression Language(SpEL)
 * 
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/expressions.html
 * 
 * @author Swedish-li
 * @see org.springframework.expression.ExpressionParser
 */
public class SimpleSpelTest {
	// Instances are reusable and thread-safe.
	private final static ExpressionParser parser = new SpelExpressionParser();
	private final static EvaluationContext context;

	static {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(1856, 7, 9);
		// 使用根对象，设置解析上下文
		context = new StandardEvaluationContext(new Inventor("Nikola Tesla", "Serbian", calendar.getTime()));
	}

	// 1、字面量(Literal)表达式
	@Test
	public void shouldParserLiteral() {
		String exp = "'Hello World!'";

		assertEquals("Hello World!", parser.parseExpression(exp).getValue());

		double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
		assertEquals(6.0221415E+23, avogadrosNumber, 0.1);

		// evals to 2147483647
		int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();

		assertEquals(2147483647, maxValue);

		boolean trueValue = (Boolean) parser.parseExpression("true").getValue();

		assertEquals(true, trueValue);

		Object nullValue = parser.parseExpression("null").getValue();

		assertNull(nullValue);
	}

	// 对象方法执行
	@Test
	public void shouldInvocateMethod() {
		String exp = "'Hello '.concat('World!')";
		assertEquals("Hello World!", parser.parseExpression(exp).getValue());
	}

	// 执行java bean中的get方法
	@Test
	public void shouldCallGetBytes() {
		String exp = "'Hello World!'.bytes";
		byte[] bytes = parser.parseExpression(exp).getValue(byte[].class);

		assertArrayEquals("Hello World!".getBytes(), bytes);
	}

	// 获取嵌套属性
	@Test
	public void shouldCallNestedProperties() {
		String exp = "'Hello World!'.bytes.length";
		int len = parser.parseExpression(exp).getValue(int.class);
		assertEquals("Hello World!".getBytes().length, len);
	}

	// 通过构造器构造对象
	@Test
	public void shouldCallConstructor() {
		String exp = "new String('Hello World!').toUpperCase()";
		Object val = parser.parseExpression(exp).getValue();
		assertTrue(val instanceof String);
		assertEquals("HELLO WORLD!", val);
	}

	@Test
	public void shouldCallInstanceProperty() {
		String exp = "name";
		// 使用上下文缓存重用对象
		assertEquals("Nikola Tesla", parser.parseExpression(exp).getValue(context));
		// 使用root object
		assertEquals("Spring-spel", parser.parseExpression(exp).getValue(new Inventor("Spring-spel", "none",
				new Date())));
	}

	@Test
	public void shouldUseBooleanOperator() {
		String exp = "name == 'Nikola Tesla'";

		assertTrue(parser.parseExpression(exp).getValue(context, Boolean.class));
	}

	@Test
	public void shouldSetCorrectType() {
		Simple simple = new Simple();
		simple.booleanList.add(true);
		EvaluationContext ctx = new StandardEvaluationContext(simple);
		String exp = "booleanList[0]";
		parser.parseExpression(exp).setValue(ctx, "false");

		assertTrue(!simple.booleanList.get(0).booleanValue());

	}

	@Test
	public void shouldGetCorrectTypes() {
		Class<?> type = parser.parseExpression("T(java.util.Date)").getValueType();
		assertEquals(type, Date.class.getClass());
		Class<?> stringType = parser.parseExpression("T(String)").getValueType();
		assertEquals(stringType, String.class.getClass());

		boolean val = parser.parseExpression("T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR")
				.getValue(
						Boolean.class);
		assertTrue(val);
	}

	@Test
	public void shouldGetList() {
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) parser.parseExpression("{1,8,9,10}").getValue();
		assertArrayEquals(new Object[] { 1, 8, 9, 10 }, list.toArray());
	}

	@Test
	public void shouldGetMap() {
		@SuppressWarnings("unchecked")
		Map<String, Object> inventorInfo = (Map<String, Object>) parser.parseExpression(
				"{name:'Nikola',dob:'10-July-1856'}").getValue();
		assertEquals("Nikola", inventorInfo.get("name"));
		assertEquals("10-July-1856", inventorInfo.get("dob"));
	}

	@Test
	// supports the instanceof and regular expression based matches operator.
	public void relationOperator() {
		// instanceof
		assertFalse(parser.parseExpression("'xyz' instanceof T(Integer)").getValue(Boolean.class));
		// matches
		assertTrue(parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class));
		assertFalse(parser.parseExpression("'5.0068' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class));
	}

}

class Simple {
	public List<Boolean> booleanList = new ArrayList<>();
}
