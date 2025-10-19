package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.jkiss.dbeaver.utils.GeneralUtils.VariableEntryInfo;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GeneralUtilsDiffblueTest {
  /**
   * Test {@link GeneralUtils#convertToString(byte[], int, int)}.
   *
   * <ul>
   *   <li>When array of {@code byte} with {@code A} and three.
   *   <li>Then return {@code A A}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertToString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GeneralUtils.convertToString(byte[], int, int)"})
  public void testConvertToString_whenArrayOfByteWithAAndThree_thenReturnAA() {
    // Arrange, Act and Assert
    assertEquals(
        "A A",
        GeneralUtils.convertToString(
            new byte[] {'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3}, 2, 3));
  }

  /**
   * Test {@link GeneralUtils#convertToString(byte[], int, int)}.
   *
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.
   *   <li>Then return {@code AXA}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertToString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GeneralUtils.convertToString(byte[], int, int)"})
  public void testConvertToString_whenAxaxaxaxBytesIsUtf8_thenReturnAxa()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("AXA", GeneralUtils.convertToString("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link GeneralUtils#convertToString(byte[], int, int)}.
   *
   * <ul>
   *   <li>When {@code X}.
   *   <li>Then return {@code XA}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertToString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GeneralUtils.convertToString(byte[], int, int)"})
  public void testConvertToString_whenX_thenReturnXa() {
    // Arrange, Act and Assert
    assertEquals(
        " XA",
        GeneralUtils.convertToString(
            new byte[] {'A', 'X', Byte.MAX_VALUE, 'X', 'A', 'X', 'A', 'X'}, 2, 3));
  }

  /**
   * Test {@link GeneralUtils#convertToString(byte[], int, int)}.
   *
   * <ul>
   *   <li>When {@code X}.
   *   <li>Then return {@code ÿXA}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertToString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GeneralUtils.convertToString(byte[], int, int)"})
  public void testConvertToString_whenX_thenReturnXa2() {
    // Arrange, Act and Assert
    assertEquals(
        "ÿXA",
        GeneralUtils.convertToString(new byte[] {'A', 'X', -1, 'X', 'A', 'X', 'A', 'X'}, 2, 3));
  }

  /**
   * Test {@link GeneralUtils#convertToString(byte[], int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertToString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GeneralUtils.convertToString(byte[], int, int)"})
  public void testConvertToString_whenZero_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals(
        "",
        GeneralUtils.convertToString(new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 2, 0));
  }

  /**
   * Test {@link GeneralUtils#convertToBytes(String)}.
   *
   * <p>Method under test: {@link GeneralUtils#convertToBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] GeneralUtils.convertToBytes(String)"})
  public void testConvertToBytes() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {'4', '2'}, GeneralUtils.convertToBytes("42"));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_when42_thenReturn42() {
    // Arrange
    Class<Object> valueType = Object.class;

    // Act and Assert
    assertEquals("42", GeneralUtils.convertString("42", valueType));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(GeneralUtils.convertString("", null));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenJavaLangObject_thenReturnNull() {
    // Arrange
    Class<Object> valueType = Object.class;

    // Act and Assert
    assertNull(GeneralUtils.convertString(null, valueType));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenNull_thenReturn1() {
    // Arrange, Act and Assert
    assertEquals("1", GeneralUtils.convertString("1", null));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@code org.jkiss.dbeaver.model}.
   *   <li>Then return {@code org.jkiss.dbeaver.model}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenOrgJkissDbeaverModel_thenReturnOrgJkissDbeaverModel() {
    // Arrange, Act and Assert
    assertEquals(
        "org.jkiss.dbeaver.model",
        GeneralUtils.convertString("org.jkiss.dbeaver.model", Long.TYPE));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Byte#TYPE}.
   *   <li>Then return byteValue is one.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnByteValueIsOne() {
    // Arrange, Act and Assert
    assertEquals((byte) 1, ((Byte) GeneralUtils.convertString("1", Byte.TYPE)).byteValue());
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Double#TYPE}.
   *   <li>Then return doubleValue is one.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnDoubleValueIsOne() {
    // Arrange, Act and Assert
    assertEquals(1.0d, ((Double) GeneralUtils.convertString("1", Double.TYPE)).doubleValue(), 0.0);
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Boolean#TYPE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((Boolean) GeneralUtils.convertString("1", Boolean.TYPE));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Float#TYPE}.
   *   <li>Then return floatValue is one.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnFloatValueIsOne() {
    // Arrange, Act and Assert
    assertEquals(1.0f, ((Float) GeneralUtils.convertString("1", Float.TYPE)).floatValue(), 0.0f);
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Integer#TYPE}.
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnIntValueIsOne() {
    // Arrange, Act and Assert
    assertEquals(1, ((Integer) GeneralUtils.convertString("1", Integer.TYPE)).intValue());
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Long#TYPE}.
   *   <li>Then return longValue is one.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnLongValueIsOne() {
    // Arrange, Act and Assert
    assertEquals(1L, ((Long) GeneralUtils.convertString("1", Long.TYPE)).longValue());
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Short#TYPE}.
   *   <li>Then return shortValue is one.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnShortValueIsOne() {
    // Arrange, Act and Assert
    assertEquals((short) 1, ((Short) GeneralUtils.convertString("1", Short.TYPE)).shortValue());
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Long#TYPE}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnValue() {
    // Arrange, Act and Assert
    assertEquals("Value", GeneralUtils.convertString("Value", Long.TYPE));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Integer#TYPE}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnValue2() {
    // Arrange, Act and Assert
    assertEquals("Value", GeneralUtils.convertString("Value", Integer.TYPE));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Short#TYPE}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnValue3() {
    // Arrange, Act and Assert
    assertEquals("Value", GeneralUtils.convertString("Value", Short.TYPE));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Byte#TYPE}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnValue4() {
    // Arrange, Act and Assert
    assertEquals("Value", GeneralUtils.convertString("Value", Byte.TYPE));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Double#TYPE}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnValue5() {
    // Arrange, Act and Assert
    assertEquals("Value", GeneralUtils.convertString("Value", Double.TYPE));
  }

  /**
   * Test {@link GeneralUtils#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Float#TYPE}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnValue6() {
    // Arrange, Act and Assert
    assertEquals("Value", GeneralUtils.convertString("Value", Float.TYPE));
  }

  /**
   * Test {@link GeneralUtils#extractVariableName(String)}.
   *
   * <ul>
   *   <li>When {@code ${U:U}${U:U}}.
   *   <li>Then return {@code U}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#extractVariableName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GeneralUtils.extractVariableName(String)"})
  public void testExtractVariableName_whenUUUU_thenReturnU() {
    // Arrange, Act and Assert
    assertEquals("U", GeneralUtils.extractVariableName("${U:U}${U:U}"));
  }

  /**
   * Test {@link GeneralUtils#extractVariableName(String)}.
   *
   * <ul>
   *   <li>When {@code ${U:U}}.
   *   <li>Then return {@code U}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#extractVariableName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GeneralUtils.extractVariableName(String)"})
  public void testExtractVariableName_whenUU_thenReturnU() {
    // Arrange, Act and Assert
    assertEquals("U", GeneralUtils.extractVariableName("${U:U}"));
  }

  /**
   * Test {@link GeneralUtils#extractVariableName(String)}.
   *
   * <ul>
   *   <li>When {@code Variable Pattern${U:U}}.
   *   <li>Then return {@code U}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#extractVariableName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GeneralUtils.extractVariableName(String)"})
  public void testExtractVariableName_whenVariablePatternUU_thenReturnU() {
    // Arrange, Act and Assert
    assertEquals("U", GeneralUtils.extractVariableName("Variable Pattern${U:U}"));
  }

  /**
   * Test {@link GeneralUtils#extractVariableName(String)}.
   *
   * <ul>
   *   <li>When {@code Variable Pattern}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#extractVariableName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GeneralUtils.extractVariableName(String)"})
  public void testExtractVariableName_whenVariablePattern_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(GeneralUtils.extractVariableName("Variable Pattern"));
  }

  /**
   * Test {@link GeneralUtils#findAllVariableEntries(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#findAllVariableEntries(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List GeneralUtils.findAllVariableEntries(String)"})
  public void testFindAllVariableEntries_whenEmptyString_thenReturnEmpty() {
    // Arrange and Act
    List<VariableEntryInfo> actualFindAllVariableEntriesResult =
        GeneralUtils.findAllVariableEntries("");

    // Assert
    assertTrue(actualFindAllVariableEntriesResult.isEmpty());
  }

  /**
   * Test {@link GeneralUtils#findAllVariableEntries(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#findAllVariableEntries(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List GeneralUtils.findAllVariableEntries(String)"})
  public void testFindAllVariableEntries_whenNull_thenReturnEmpty() {
    // Arrange and Act
    List<VariableEntryInfo> actualFindAllVariableEntriesResult =
        GeneralUtils.findAllVariableEntries(null);

    // Assert
    assertTrue(actualFindAllVariableEntriesResult.isEmpty());
  }

  /**
   * Test {@link GeneralUtils#findAllVariableEntries(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#findAllVariableEntries(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List GeneralUtils.findAllVariableEntries(String)"})
  public void testFindAllVariableEntries_whenString_thenReturnEmpty() {
    // Arrange and Act
    List<VariableEntryInfo> actualFindAllVariableEntriesResult =
        GeneralUtils.findAllVariableEntries("String");

    // Assert
    assertTrue(actualFindAllVariableEntriesResult.isEmpty());
  }

  /**
   * Test {@link GeneralUtils#findAllVariableEntries(String)}.
   *
   * <ul>
   *   <li>When {@code ${U:U}}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#findAllVariableEntries(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List GeneralUtils.findAllVariableEntries(String)"})
  public void testFindAllVariableEntries_whenUU_thenReturnSizeIsOne() {
    // Arrange and Act
    List<VariableEntryInfo> actualFindAllVariableEntriesResult =
        GeneralUtils.findAllVariableEntries("${U:U}");

    // Assert
    assertEquals(1, actualFindAllVariableEntriesResult.size());
    VariableEntryInfo getResult = actualFindAllVariableEntriesResult.get(0);
    assertEquals("U", getResult.name());
    assertEquals(0, getResult.start());
    assertEquals(6, getResult.end());
  }

  /**
   * Test {@link GeneralUtils#deserializeObject(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#deserializeObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.deserializeObject(String)"})
  public void testDeserializeObject_whenNull() {
    // Arrange, Act and Assert
    assertNull(GeneralUtils.deserializeObject(null));
  }

  /**
   * Test {@link GeneralUtils#deserializeObject(String)}.
   *
   * <ul>
   *   <li>When {@code org.jkiss.dbeaver.model}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#deserializeObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.deserializeObject(String)"})
  public void testDeserializeObject_whenOrgJkissDbeaverModel() {
    // Arrange, Act and Assert
    assertNull(GeneralUtils.deserializeObject("org.jkiss.dbeaver.model"));
  }

  /**
   * Test {@link GeneralUtils#deserializeObject(String)}.
   *
   * <ul>
   *   <li>When {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#deserializeObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.deserializeObject(String)"})
  public void testDeserializeObject_whenText() {
    // Arrange, Act and Assert
    assertNull(GeneralUtils.deserializeObject("Text"));
  }

  /**
   * Test {@link GeneralUtils#adapt(Object, Class, boolean)} with {@code sourceObject}, {@code
   * adapter}, {@code allowActivation}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#adapt(Object, Class, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.adapt(Object, Class, boolean)"})
  public void testAdaptWithSourceObjectAdapterAllowActivation_whenFalse_thenReturnNull() {
    // Arrange
    Class<Object> adapter = Object.class;

    // Act
    Object actualAdaptResult = GeneralUtils.adapt(null, adapter, false);

    // Assert
    assertNull(actualAdaptResult);
  }

  /**
   * Test {@link GeneralUtils#adapt(Object, Class)} with {@code sourceObject}, {@code adapter}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GeneralUtils#adapt(Object, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GeneralUtils.adapt(Object, Class)"})
  public void testAdaptWithSourceObjectAdapter_whenNull_thenReturnNull() {
    // Arrange
    Class<Object> adapter = Object.class;

    // Act and Assert
    assertNull(GeneralUtils.adapt(null, adapter));
  }
}
