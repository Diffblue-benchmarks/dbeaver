package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBCStatisticsDiffblueTest {
  @InjectMocks private DBCStatistics dBCStatistics;

  @Mock private List<String> list;

  @Mock private Map<String, Object> map;

  /**
   * Test new {@link DBCStatistics} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DBCStatistics}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.<init>()"})
  public void testNewDBCStatistics() {
    // Arrange and Act
    DBCStatistics actualDbcStatistics = new DBCStatistics();

    // Assert
    assertNull(actualDbcStatistics.getQueryText());
    assertNull(actualDbcStatistics.getError());
    assertNull(actualDbcStatistics.getMessages());
    assertNull(actualDbcStatistics.getWarnings());
    assertEquals(-1L, actualDbcStatistics.getRowsFetched());
    assertEquals(-1L, actualDbcStatistics.getRowsUpdated());
    assertEquals(0, actualDbcStatistics.getStatementsCount());
    assertEquals(0L, actualDbcStatistics.getExecuteTime());
    assertEquals(0L, actualDbcStatistics.getFetchTime());
    assertEquals(0L, actualDbcStatistics.getTotalTime());
    assertTrue(actualDbcStatistics.getInfo().isEmpty());
    assertTrue(actualDbcStatistics.isEmpty());
  }

  /**
   * Test {@link DBCStatistics#addRowsUpdated(long)}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor).
   *   <li>When one.
   *   <li>Then {@link DBCStatistics} (default constructor) RowsUpdated is one.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#addRowsUpdated(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.addRowsUpdated(long)"})
  public void testAddRowsUpdated_givenDBCStatistics_whenOne_thenDBCStatisticsRowsUpdatedIsOne() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();

    // Act
    dbcStatistics.addRowsUpdated(1L);

    // Assert
    assertEquals(1L, dbcStatistics.getRowsUpdated());
  }

  /**
   * Test {@link DBCStatistics#addRowsUpdated(long)}.
   *
   * <ul>
   *   <li>Then {@link DBCStatistics} (default constructor) RowsUpdated is minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#addRowsUpdated(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.addRowsUpdated(long)"})
  public void testAddRowsUpdated_thenDBCStatisticsRowsUpdatedIsMinusOne() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    dbcStatistics.setRowsUpdated(-1L);

    // Act
    dbcStatistics.addRowsUpdated(-1L);

    // Assert that nothing has changed
    assertEquals(-1L, dbcStatistics.getRowsUpdated());
  }

  /**
   * Test {@link DBCStatistics#addRowsUpdated(long)}.
   *
   * <ul>
   *   <li>Then {@link DBCStatistics} (default constructor) RowsUpdated is two.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#addRowsUpdated(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.addRowsUpdated(long)"})
  public void testAddRowsUpdated_thenDBCStatisticsRowsUpdatedIsTwo() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    dbcStatistics.addRowsUpdated(1L);

    // Act
    dbcStatistics.addRowsUpdated(1L);

    // Assert
    assertEquals(2L, dbcStatistics.getRowsUpdated());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBCStatistics#setError(Throwable)}
   *   <li>{@link DBCStatistics#setExecuteTime(long)}
   *   <li>{@link DBCStatistics#setFetchTime(long)}
   *   <li>{@link DBCStatistics#setQueryText(String)}
   *   <li>{@link DBCStatistics#setRowsFetched(long)}
   *   <li>{@link DBCStatistics#setRowsUpdated(long)}
   *   <li>{@link DBCStatistics#setStatementsCount(int)}
   *   <li>{@link DBCStatistics#addExecuteTime(long)}
   *   <li>{@link DBCStatistics#addFetchTime(long)}
   *   <li>{@link DBCStatistics#addStatementsCount()}
   *   <li>{@link DBCStatistics#getError()}
   *   <li>{@link DBCStatistics#getExecuteTime()}
   *   <li>{@link DBCStatistics#getFetchTime()}
   *   <li>{@link DBCStatistics#getMessages()}
   *   <li>{@link DBCStatistics#getQueryText()}
   *   <li>{@link DBCStatistics#getRowsFetched()}
   *   <li>{@link DBCStatistics#getRowsUpdated()}
   *   <li>{@link DBCStatistics#getStartTime()}
   *   <li>{@link DBCStatistics#getStatementsCount()}
   *   <li>{@link DBCStatistics#getWarnings()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBCStatistics.addExecuteTime(long)",
    "void DBCStatistics.addFetchTime(long)",
    "void DBCStatistics.addStatementsCount()",
    "Throwable DBCStatistics.getError()",
    "long DBCStatistics.getExecuteTime()",
    "long DBCStatistics.getFetchTime()",
    "List DBCStatistics.getMessages()",
    "String DBCStatistics.getQueryText()",
    "long DBCStatistics.getRowsFetched()",
    "long DBCStatistics.getRowsUpdated()",
    "long DBCStatistics.getStartTime()",
    "int DBCStatistics.getStatementsCount()",
    "List DBCStatistics.getWarnings()",
    "void DBCStatistics.setError(Throwable)",
    "void DBCStatistics.setExecuteTime(long)",
    "void DBCStatistics.setFetchTime(long)",
    "void DBCStatistics.setQueryText(String)",
    "void DBCStatistics.setRowsFetched(long)",
    "void DBCStatistics.setRowsUpdated(long)",
    "void DBCStatistics.setStatementsCount(int)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    Throwable error = new Throwable();

    // Act
    dbcStatistics.setError(error);
    dbcStatistics.setExecuteTime(1L);
    dbcStatistics.setFetchTime(1L);
    dbcStatistics.setQueryText("Query Text");
    dbcStatistics.setRowsFetched(1L);
    dbcStatistics.setRowsUpdated(1L);
    dbcStatistics.setStatementsCount(3);
    dbcStatistics.addExecuteTime(1L);
    dbcStatistics.addFetchTime(1L);
    dbcStatistics.addStatementsCount();
    Throwable actualError = dbcStatistics.getError();
    long actualExecuteTime = dbcStatistics.getExecuteTime();
    long actualFetchTime = dbcStatistics.getFetchTime();
    List<String> actualMessages = dbcStatistics.getMessages();
    String actualQueryText = dbcStatistics.getQueryText();
    long actualRowsFetched = dbcStatistics.getRowsFetched();
    long actualRowsUpdated = dbcStatistics.getRowsUpdated();
    dbcStatistics.getStartTime();
    int actualStatementsCount = dbcStatistics.getStatementsCount();

    // Assert
    assertEquals("Query Text", actualQueryText);
    assertNull(actualMessages);
    assertNull(dbcStatistics.getWarnings());
    assertEquals(1L, actualRowsFetched);
    assertEquals(1L, actualRowsUpdated);
    assertEquals(2L, actualExecuteTime);
    assertEquals(2L, actualFetchTime);
    assertEquals(4, actualStatementsCount);
    assertSame(error, actualError);
  }

  /**
   * Test {@link DBCStatistics#getTotalTime()}.
   *
   * <p>Method under test: {@link DBCStatistics#getTotalTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBCStatistics.getTotalTime()"})
  public void testGetTotalTime() {
    // Arrange, Act and Assert
    assertEquals(0L, new DBCStatistics().getTotalTime());
  }

  /**
   * Test {@link DBCStatistics#addMessage(String)}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor).
   *   <li>Then {@link DBCStatistics} (default constructor) Messages size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#addMessage(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.addMessage(String)"})
  public void testAddMessage_givenDBCStatistics_thenDBCStatisticsMessagesSizeIsOne() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();

    // Act
    dbcStatistics.addMessage("Not all who wander are lost");

    // Assert
    List<String> messages = dbcStatistics.getMessages();
    assertEquals(1, messages.size());
    assertEquals("Not all who wander are lost", messages.get(0));
  }

  /**
   * Test {@link DBCStatistics#addMessage(String)}.
   *
   * <ul>
   *   <li>Then {@link DBCStatistics} (default constructor) Messages size is two.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#addMessage(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.addMessage(String)"})
  public void testAddMessage_thenDBCStatisticsMessagesSizeIsTwo() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    dbcStatistics.addMessage("Not all who wander are lost");

    // Act
    dbcStatistics.addMessage("Not all who wander are lost");

    // Assert
    List<String> messages = dbcStatistics.getMessages();
    assertEquals(2, messages.size());
    assertEquals("Not all who wander are lost", messages.get(0));
    assertEquals("Not all who wander are lost", messages.get(1));
  }

  /**
   * Test {@link DBCStatistics#getInfo()}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor) addInfo {@code Name} and {@link
   *       DBPEvent#RENAME}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#getInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBCStatistics.getInfo()"})
  public void testGetInfo_givenDBCStatisticsAddInfoNameAndRename_thenReturnSizeIsOne() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    dbcStatistics.addInfo("Name", DBPEvent.RENAME);

    // Act
    Map<String, Object> actualInfo = dbcStatistics.getInfo();

    // Assert
    assertEquals(1, actualInfo.size());
    assertTrue(actualInfo.containsKey("Name"));
  }

  /**
   * Test {@link DBCStatistics#getInfo()}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#getInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBCStatistics.getInfo()"})
  public void testGetInfo_givenDBCStatistics_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new DBCStatistics().getInfo().isEmpty());
  }

  /**
   * Test {@link DBCStatistics#addInfo(String, Object)}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#addInfo(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.addInfo(String, Object)"})
  public void testAddInfo_givenDBCStatistics() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    Object object = DBPEvent.RENAME;

    // Act
    dbcStatistics.addInfo("Name", object);

    // Assert
    Map<String, Object> info = dbcStatistics.getInfo();
    assertEquals(1, info.size());
    assertSame(object, info.get("Name"));
  }

  /**
   * Test {@link DBCStatistics#addInfo(String, Object)}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor) addInfo {@code Name} and {@link
   *       DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#addInfo(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.addInfo(String, Object)"})
  public void testAddInfo_givenDBCStatisticsAddInfoNameAndRename() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    dbcStatistics.addInfo("Name", DBPEvent.RENAME);
    Object object = DBPEvent.RENAME;

    // Act
    dbcStatistics.addInfo("Name", object);

    // Assert that nothing has changed
    Map<String, Object> info = dbcStatistics.getInfo();
    assertEquals(1, info.size());
    assertSame(object, info.get("Name"));
  }

  /**
   * Test {@link DBCStatistics#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor) addStatementsCount.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBCStatistics.isEmpty()"})
  public void testIsEmpty_givenDBCStatisticsAddStatementsCount_thenReturnFalse() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    dbcStatistics.addStatementsCount();

    // Act and Assert
    assertFalse(dbcStatistics.isEmpty());
  }

  /**
   * Test {@link DBCStatistics#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor) ExecuteTime is one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBCStatistics.isEmpty()"})
  public void testIsEmpty_givenDBCStatisticsExecuteTimeIsOne_thenReturnFalse() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    dbcStatistics.setExecuteTime(1L);
    dbcStatistics.setFetchTime(0L);
    dbcStatistics.setStatementsCount(0);

    // Act and Assert
    assertFalse(dbcStatistics.isEmpty());
  }

  /**
   * Test {@link DBCStatistics#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor) ExecuteTime is zero.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBCStatistics.isEmpty()"})
  public void testIsEmpty_givenDBCStatisticsExecuteTimeIsZero_thenReturnFalse() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    dbcStatistics.setExecuteTime(0L);
    dbcStatistics.setFetchTime(1L);
    dbcStatistics.setStatementsCount(0);

    // Act and Assert
    assertFalse(dbcStatistics.isEmpty());
  }

  /**
   * Test {@link DBCStatistics#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBCStatistics.isEmpty()"})
  public void testIsEmpty_givenDBCStatistics_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBCStatistics().isEmpty());
  }

  /**
   * Test {@link DBCStatistics#accumulate(DBCStatistics)}.
   *
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@link SimpleEntry#SimpleEntry(Object, Object)} with
   *       {@code foo} and {@link DBPEvent#RENAME}.
   *   <li>Then calls {@link Map#put(Object, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#accumulate(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.accumulate(DBCStatistics)"})
  public void testAccumulate_givenHashSetAddSimpleEntryWithFooAndRename_thenCallsPut() {
    // Arrange
    HashSet<Entry<String, Object>> entrySet = new HashSet<>();
    entrySet.add(new SimpleEntry<>("foo", DBPEvent.RENAME));
    when(map.put(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(DBPEvent.RENAME);
    when(map.isEmpty()).thenReturn(false);
    when(map.entrySet()).thenReturn(entrySet);
    when(list.isEmpty()).thenReturn(true);

    // Act
    dBCStatistics.accumulate(dBCStatistics);

    // Assert
    verify(list).isEmpty();
    verify(map).entrySet();
    verify(map).isEmpty();
    verify(map).put(eq("foo"), isA(Object.class));
  }

  /**
   * Test {@link DBCStatistics#accumulate(DBCStatistics)}.
   *
   * <ul>
   *   <li>Given {@link List} {@link List#isEmpty()} return {@code false}.
   *   <li>When {@link DBCStatistics}.
   *   <li>Then calls {@link List#iterator()}.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#accumulate(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.accumulate(DBCStatistics)"})
  public void testAccumulate_givenListIsEmptyReturnFalse_whenDBCStatistics_thenCallsIterator() {
    // Arrange
    when(map.isEmpty()).thenReturn(true);
    when(list.isEmpty()).thenReturn(false);

    ArrayList<String> stringList = new ArrayList<>();
    when(list.iterator()).thenReturn(stringList.iterator());

    // Act
    dBCStatistics.accumulate(dBCStatistics);

    // Assert
    verify(list).isEmpty();
    verify(list).iterator();
    verify(map).isEmpty();
  }

  /**
   * Test {@link DBCStatistics#accumulate(DBCStatistics)}.
   *
   * <ul>
   *   <li>Given {@link Map} {@link Map#isEmpty()} return {@code false}.
   *   <li>When {@link DBCStatistics}.
   *   <li>Then calls {@link Map#entrySet()}.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#accumulate(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.accumulate(DBCStatistics)"})
  public void testAccumulate_givenMapIsEmptyReturnFalse_whenDBCStatistics_thenCallsEntrySet() {
    // Arrange
    when(map.isEmpty()).thenReturn(false);
    when(map.entrySet()).thenReturn(new HashSet<>());
    when(list.isEmpty()).thenReturn(true);

    // Act
    dBCStatistics.accumulate(dBCStatistics);

    // Assert
    verify(list).isEmpty();
    verify(map).entrySet();
    verify(map).isEmpty();
  }

  /**
   * Test {@link DBCStatistics#accumulate(DBCStatistics)}.
   *
   * <ul>
   *   <li>Given {@link Map} {@link Map#isEmpty()} return {@code true}.
   *   <li>When {@link DBCStatistics}.
   *   <li>Then calls {@link List#isEmpty()}.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#accumulate(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.accumulate(DBCStatistics)"})
  public void testAccumulate_givenMapIsEmptyReturnTrue_whenDBCStatistics_thenCallsIsEmpty() {
    // Arrange
    when(map.isEmpty()).thenReturn(true);
    when(list.isEmpty()).thenReturn(true);

    // Act
    dBCStatistics.accumulate(dBCStatistics);

    // Assert
    verify(list).isEmpty();
    verify(map).isEmpty();
  }

  /**
   * Test {@link DBCStatistics#reset()}.
   *
   * <p>Method under test: {@link DBCStatistics#reset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.reset()"})
  public void testReset() {
    // Arrange and Act
    dBCStatistics.reset();

    // Assert
    assertNull(dBCStatistics.getMessages());
    assertTrue(dBCStatistics.getInfo().isEmpty());
  }

  /**
   * Test {@link DBCStatistics#addWarning(Throwable)}.
   *
   * <ul>
   *   <li>Given {@link DBCStatistics} (default constructor).
   *   <li>Then {@link DBCStatistics} (default constructor) Warnings size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#addWarning(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.addWarning(Throwable)"})
  public void testAddWarning_givenDBCStatistics_thenDBCStatisticsWarningsSizeIsOne() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    Throwable warning = new Throwable();

    // Act
    dbcStatistics.addWarning(warning);

    // Assert
    List<Throwable> warnings = dbcStatistics.getWarnings();
    assertEquals(1, warnings.size());
    assertSame(warning, warnings.get(0));
  }

  /**
   * Test {@link DBCStatistics#addWarning(Throwable)}.
   *
   * <ul>
   *   <li>Then {@link DBCStatistics} (default constructor) Warnings size is two.
   * </ul>
   *
   * <p>Method under test: {@link DBCStatistics#addWarning(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCStatistics.addWarning(Throwable)"})
  public void testAddWarning_thenDBCStatisticsWarningsSizeIsTwo() {
    // Arrange
    DBCStatistics dbcStatistics = new DBCStatistics();
    Throwable warning = new Throwable();
    dbcStatistics.addWarning(warning);
    Throwable warning2 = new Throwable();

    // Act
    dbcStatistics.addWarning(warning2);

    // Assert
    List<Throwable> warnings = dbcStatistics.getWarnings();
    assertEquals(2, warnings.size());
    assertSame(warning, warnings.get(0));
    assertSame(warning2, warnings.get(1));
  }
}
