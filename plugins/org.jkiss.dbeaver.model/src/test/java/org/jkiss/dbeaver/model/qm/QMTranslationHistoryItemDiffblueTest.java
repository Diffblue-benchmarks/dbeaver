package org.jkiss.dbeaver.model.qm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMTranslationHistoryItemDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMTranslationHistoryItem#QMTranslationHistoryItem()}
   *   <li>{@link QMTranslationHistoryItem#setCompletionText(String)}
   *   <li>{@link QMTranslationHistoryItem#setId(String)}
   *   <li>{@link QMTranslationHistoryItem#setNaturalText(String)}
   *   <li>{@link QMTranslationHistoryItem#setTime(Date)}
   *   <li>{@link QMTranslationHistoryItem#getCompletionText()}
   *   <li>{@link QMTranslationHistoryItem#getId()}
   *   <li>{@link QMTranslationHistoryItem#getNaturalText()}
   *   <li>{@link QMTranslationHistoryItem#getTime()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMTranslationHistoryItem.<init>()",
    "void QMTranslationHistoryItem.<init>(String, String)",
    "String QMTranslationHistoryItem.getCompletionText()",
    "String QMTranslationHistoryItem.getId()",
    "String QMTranslationHistoryItem.getNaturalText()",
    "Date QMTranslationHistoryItem.getTime()",
    "void QMTranslationHistoryItem.setCompletionText(String)",
    "void QMTranslationHistoryItem.setId(String)",
    "void QMTranslationHistoryItem.setNaturalText(String)",
    "void QMTranslationHistoryItem.setTime(Date)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    QMTranslationHistoryItem actualQmTranslationHistoryItem = new QMTranslationHistoryItem();
    actualQmTranslationHistoryItem.setCompletionText("Completion Text");
    actualQmTranslationHistoryItem.setId("42");
    actualQmTranslationHistoryItem.setNaturalText("Natural Text");
    Date time =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualQmTranslationHistoryItem.setTime(time);
    String actualCompletionText = actualQmTranslationHistoryItem.getCompletionText();
    String actualId = actualQmTranslationHistoryItem.getId();
    String actualNaturalText = actualQmTranslationHistoryItem.getNaturalText();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Completion Text", actualCompletionText);
    assertEquals("Natural Text", actualNaturalText);
    assertSame(time, actualQmTranslationHistoryItem.getTime());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Natural Text}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMTranslationHistoryItem#QMTranslationHistoryItem(String, String)}
   *   <li>{@link QMTranslationHistoryItem#setCompletionText(String)}
   *   <li>{@link QMTranslationHistoryItem#setId(String)}
   *   <li>{@link QMTranslationHistoryItem#setNaturalText(String)}
   *   <li>{@link QMTranslationHistoryItem#setTime(Date)}
   *   <li>{@link QMTranslationHistoryItem#getCompletionText()}
   *   <li>{@link QMTranslationHistoryItem#getId()}
   *   <li>{@link QMTranslationHistoryItem#getNaturalText()}
   *   <li>{@link QMTranslationHistoryItem#getTime()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMTranslationHistoryItem.<init>()",
    "void QMTranslationHistoryItem.<init>(String, String)",
    "String QMTranslationHistoryItem.getCompletionText()",
    "String QMTranslationHistoryItem.getId()",
    "String QMTranslationHistoryItem.getNaturalText()",
    "Date QMTranslationHistoryItem.getTime()",
    "void QMTranslationHistoryItem.setCompletionText(String)",
    "void QMTranslationHistoryItem.setId(String)",
    "void QMTranslationHistoryItem.setNaturalText(String)",
    "void QMTranslationHistoryItem.setTime(Date)"
  })
  public void testGettersAndSetters_whenNaturalText() {
    // Arrange and Act
    QMTranslationHistoryItem actualQmTranslationHistoryItem =
        new QMTranslationHistoryItem("Natural Text", "Completion Text");
    actualQmTranslationHistoryItem.setCompletionText("Completion Text");
    actualQmTranslationHistoryItem.setId("42");
    actualQmTranslationHistoryItem.setNaturalText("Natural Text");
    Date time =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualQmTranslationHistoryItem.setTime(time);
    String actualCompletionText = actualQmTranslationHistoryItem.getCompletionText();
    String actualId = actualQmTranslationHistoryItem.getId();
    String actualNaturalText = actualQmTranslationHistoryItem.getNaturalText();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Completion Text", actualCompletionText);
    assertEquals("Natural Text", actualNaturalText);
    assertSame(time, actualQmTranslationHistoryItem.getTime());
  }

  /**
   * Test {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashMap#HashMap()} {@code time} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMTranslationHistoryItem.<init>(Map)"})
  public void testNewQMTranslationHistoryItem_given42_whenHashMapTimeIs42() {
    // Arrange
    HashMap<String, Object> map = new HashMap<>();
    map.put("id", null);
    map.put("naturalText", null);
    map.put("completionText", null);
    map.put("time", "42");

    // Act
    QMTranslationHistoryItem actualQmTranslationHistoryItem = new QMTranslationHistoryItem(map);

    // Assert
    assertNull(actualQmTranslationHistoryItem.getCompletionText());
    assertNull(actualQmTranslationHistoryItem.getId());
    assertNull(actualQmTranslationHistoryItem.getNaturalText());
  }

  /**
   * Test {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}.
   *
   * <ul>
   *   <li>Given forty-two.
   *   <li>When {@link HashMap#HashMap()} {@code time} is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMTranslationHistoryItem.<init>(Map)"})
  public void testNewQMTranslationHistoryItem_givenFortyTwo_whenHashMapTimeIsFortyTwo() {
    // Arrange
    HashMap<String, Object> map = new HashMap<>();
    map.put("id", null);
    map.put("naturalText", null);
    map.put("completionText", null);
    map.put("time", 42);

    // Act
    QMTranslationHistoryItem actualQmTranslationHistoryItem = new QMTranslationHistoryItem(map);

    // Assert
    assertNull(actualQmTranslationHistoryItem.getCompletionText());
    assertNull(actualQmTranslationHistoryItem.getId());
    assertNull(actualQmTranslationHistoryItem.getNaturalText());
  }

  /**
   * Test {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>When {@link HashMap#HashMap()} {@code completionText} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMTranslationHistoryItem.<init>(Map)"})
  public void testNewQMTranslationHistoryItem_givenRename_whenHashMapCompletionTextIsRename() {
    // Arrange
    HashMap<String, Object> map = new HashMap<>();
    map.put("id", null);
    map.put("naturalText", null);
    map.put("completionText", DBPEvent.RENAME);
    map.put("time", null);

    // Act
    QMTranslationHistoryItem actualQmTranslationHistoryItem = new QMTranslationHistoryItem(map);

    // Assert
    assertNull(actualQmTranslationHistoryItem.getId());
    assertNull(actualQmTranslationHistoryItem.getNaturalText());
  }

  /**
   * Test {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>When {@link HashMap#HashMap()} {@code time} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMTranslationHistoryItem.<init>(Map)"})
  public void testNewQMTranslationHistoryItem_givenRename_whenHashMapTimeIsRename() {
    // Arrange
    HashMap<String, Object> map = new HashMap<>();
    map.put("id", null);
    map.put("naturalText", null);
    map.put("completionText", null);
    map.put("time", DBPEvent.RENAME);

    // Act
    QMTranslationHistoryItem actualQmTranslationHistoryItem = new QMTranslationHistoryItem(map);

    // Assert
    assertNull(actualQmTranslationHistoryItem.getCompletionText());
    assertNull(actualQmTranslationHistoryItem.getId());
    assertNull(actualQmTranslationHistoryItem.getNaturalText());
  }

  /**
   * Test {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code time} is {@code id}.
   * </ul>
   *
   * <p>Method under test: {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMTranslationHistoryItem.<init>(Map)"})
  public void testNewQMTranslationHistoryItem_whenHashMapTimeIsId() {
    // Arrange
    HashMap<String, Object> map = new HashMap<>();
    map.put("id", null);
    map.put("naturalText", null);
    map.put("completionText", null);
    map.put("time", "id");

    // Act
    QMTranslationHistoryItem actualQmTranslationHistoryItem = new QMTranslationHistoryItem(map);

    // Assert
    assertNull(actualQmTranslationHistoryItem.getCompletionText());
    assertNull(actualQmTranslationHistoryItem.getId());
    assertNull(actualQmTranslationHistoryItem.getNaturalText());
  }

  /**
   * Test {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code time} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMTranslationHistoryItem.<init>(Map)"})
  public void testNewQMTranslationHistoryItem_whenHashMapTimeIsNull() {
    // Arrange
    HashMap<String, Object> map = new HashMap<>();
    map.put("id", null);
    map.put("naturalText", null);
    map.put("completionText", null);
    map.put("time", null);

    // Act
    QMTranslationHistoryItem actualQmTranslationHistoryItem = new QMTranslationHistoryItem(map);

    // Assert
    assertNull(actualQmTranslationHistoryItem.getCompletionText());
    assertNull(actualQmTranslationHistoryItem.getId());
    assertNull(actualQmTranslationHistoryItem.getNaturalText());
  }

  /**
   * Test {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return CompletionText is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMTranslationHistoryItem#QMTranslationHistoryItem(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMTranslationHistoryItem.<init>(Map)"})
  public void testNewQMTranslationHistoryItem_whenHashMap_thenReturnCompletionTextIsNull() {
    // Arrange and Act
    QMTranslationHistoryItem actualQmTranslationHistoryItem =
        new QMTranslationHistoryItem(new HashMap<>());

    // Assert
    assertNull(actualQmTranslationHistoryItem.getCompletionText());
    assertNull(actualQmTranslationHistoryItem.getId());
    assertNull(actualQmTranslationHistoryItem.getNaturalText());
  }

  /**
   * Test {@link QMTranslationHistoryItem#toMap()}.
   *
   * <ul>
   *   <li>Then return {@code Completion Text}.
   * </ul>
   *
   * <p>Method under test: {@link QMTranslationHistoryItem#toMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map QMTranslationHistoryItem.toMap()"})
  public void testToMap_thenReturnCompletionText() {
    // Arrange and Act
    Map<String, Object> actualToMapResult =
        new QMTranslationHistoryItem("Natural Text", "Completion Text").toMap();

    // Assert
    assertEquals(4, actualToMapResult.size());
    assertEquals("Completion Text", actualToMapResult.get("completionText"));
    assertEquals("Natural Text", actualToMapResult.get("naturalText"));
    assertTrue(actualToMapResult.containsKey("id"));
    assertTrue(actualToMapResult.containsKey("time"));
  }

  /**
   * Test {@link QMTranslationHistoryItem#toMap()}.
   *
   * <ul>
   *   <li>Then return {@code completionText} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMTranslationHistoryItem#toMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map QMTranslationHistoryItem.toMap()"})
  public void testToMap_thenReturnCompletionTextIsNull() {
    // Arrange
    QMTranslationHistoryItem qmTranslationHistoryItem =
        new QMTranslationHistoryItem(new HashMap<>());
    qmTranslationHistoryItem.setTime(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Act
    Map<String, Object> actualToMapResult = qmTranslationHistoryItem.toMap();

    // Assert
    assertEquals(4, actualToMapResult.size());
    assertNull(actualToMapResult.get("completionText"));
    assertNull(actualToMapResult.get("id"));
    assertNull(actualToMapResult.get("naturalText"));
    assertEquals(0L, ((Long) actualToMapResult.get("time")).longValue());
  }
}
