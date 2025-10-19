package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamTransferUtilsDiffblueTest {
  /**
   * Test {@link StreamTransferUtils#getDelimiterString(Map, String)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferUtils#getDelimiterString(Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferUtils.getDelimiterString(Map, String)"})
  public void testGetDelimiterString_given42_whenHashMap42Is42_thenReturn42() {
    // Arrange
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("42", "42");

    // Act and Assert
    assertEquals("42", StreamTransferUtils.getDelimiterString(properties, "42"));
  }

  /**
   * Test {@link StreamTransferUtils#getDelimiterString(Map, String)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link HashMap#HashMap()} {@code 42} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferUtils#getDelimiterString(Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferUtils.getDelimiterString(Map, String)"})
  public void testGetDelimiterString_givenEmptyString_whenHashMap42IsEmptyString() {
    // Arrange
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("42", "");

    // Act and Assert
    assertEquals(",", StreamTransferUtils.getDelimiterString(properties, "42"));
  }

  /**
   * Test {@link StreamTransferUtils#getDelimiterString(Map, String)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashMap#HashMap()} {@code 42} is one.
   *   <li>Then return {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferUtils#getDelimiterString(Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferUtils.getDelimiterString(Map, String)"})
  public void testGetDelimiterString_givenOne_whenHashMap42IsOne_thenReturn1() {
    // Arrange
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("42", 1);

    // Act and Assert
    assertEquals("1", StreamTransferUtils.getDelimiterString(properties, "42"));
  }

  /**
   * Test {@link StreamTransferUtils#getDelimiterString(Map, String)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@code ,}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferUtils#getDelimiterString(Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferUtils.getDelimiterString(Map, String)"})
  public void testGetDelimiterString_whenHashMap_thenReturnComma() {
    // Arrange, Act and Assert
    assertEquals(",", StreamTransferUtils.getDelimiterString(new HashMap<>(), "Prop Name"));
  }
}
