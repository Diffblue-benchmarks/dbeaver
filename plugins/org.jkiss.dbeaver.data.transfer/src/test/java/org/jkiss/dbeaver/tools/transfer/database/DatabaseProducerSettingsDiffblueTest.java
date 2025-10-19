package org.jkiss.dbeaver.tools.transfer.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.tools.transfer.DataTransferSettings;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseProducerSettings.ExtractType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseProducerSettingsDiffblueTest {
  /**
   * Test new {@link DatabaseProducerSettings} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DatabaseProducerSettings}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseProducerSettings.<init>()"})
  public void testNewDatabaseProducerSettings() {
    // Arrange and Act
    DatabaseProducerSettings actualDatabaseProducerSettings = new DatabaseProducerSettings();

    // Assert
    assertEquals(
        "\tOpen new connection(s): Yes\n"
            + "\tExtract type: SINGLE_QUERY\n"
            + "\tSelect row count: Yes\n"
            + "\tSelected rows only: No\n"
            + "\tSelected columns only: No\n",
        actualDatabaseProducerSettings.getSettingsSummary());
    assertEquals(10000, actualDatabaseProducerSettings.getFetchSize());
    assertEquals(100000, actualDatabaseProducerSettings.getSegmentSize());
    assertEquals(ExtractType.SINGLE_QUERY, actualDatabaseProducerSettings.getExtractType());
    assertFalse(actualDatabaseProducerSettings.isSelectedColumnsOnly());
    assertFalse(actualDatabaseProducerSettings.isSelectedRowsOnly());
    assertTrue(actualDatabaseProducerSettings.isOpenNewConnections());
    assertTrue(actualDatabaseProducerSettings.isQueryRowCount());
  }

  /**
   * Test {@link DatabaseProducerSettings#setSegmentSize(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then {@link DatabaseProducerSettings} (default constructor) SegmentSize is three.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseProducerSettings#setSegmentSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseProducerSettings.setSegmentSize(int)"})
  public void testSetSegmentSize_whenThree_thenDatabaseProducerSettingsSegmentSizeIsThree() {
    // Arrange
    DatabaseProducerSettings databaseProducerSettings = new DatabaseProducerSettings();

    // Act
    databaseProducerSettings.setSegmentSize(3);

    // Assert
    assertEquals(3, databaseProducerSettings.getSegmentSize());
  }

  /**
   * Test {@link DatabaseProducerSettings#setSegmentSize(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then {@link DatabaseProducerSettings} (default constructor) SegmentSize is {@code
   *       100000}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseProducerSettings#setSegmentSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseProducerSettings.setSegmentSize(int)"})
  public void testSetSegmentSize_whenZero_thenDatabaseProducerSettingsSegmentSizeIs100000() {
    // Arrange
    DatabaseProducerSettings databaseProducerSettings = new DatabaseProducerSettings();

    // Act
    databaseProducerSettings.setSegmentSize(0);

    // Assert that nothing has changed
    assertEquals(100000, databaseProducerSettings.getSegmentSize());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DatabaseProducerSettings#setExtractType(ExtractType)}
   *   <li>{@link DatabaseProducerSettings#setFetchSize(int)}
   *   <li>{@link DatabaseProducerSettings#setOpenNewConnections(boolean)}
   *   <li>{@link DatabaseProducerSettings#setQueryRowCount(boolean)}
   *   <li>{@link DatabaseProducerSettings#setSelectedColumnsOnly(boolean)}
   *   <li>{@link DatabaseProducerSettings#setSelectedRowsOnly(boolean)}
   *   <li>{@link DatabaseProducerSettings#getExtractType()}
   *   <li>{@link DatabaseProducerSettings#getFetchSize()}
   *   <li>{@link DatabaseProducerSettings#getSegmentSize()}
   *   <li>{@link DatabaseProducerSettings#isOpenNewConnections()}
   *   <li>{@link DatabaseProducerSettings#isQueryRowCount()}
   *   <li>{@link DatabaseProducerSettings#isSelectedColumnsOnly()}
   *   <li>{@link DatabaseProducerSettings#isSelectedRowsOnly()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ExtractType DatabaseProducerSettings.getExtractType()",
    "int DatabaseProducerSettings.getFetchSize()",
    "int DatabaseProducerSettings.getSegmentSize()",
    "boolean DatabaseProducerSettings.isOpenNewConnections()",
    "boolean DatabaseProducerSettings.isQueryRowCount()",
    "boolean DatabaseProducerSettings.isSelectedColumnsOnly()",
    "boolean DatabaseProducerSettings.isSelectedRowsOnly()",
    "void DatabaseProducerSettings.setExtractType(ExtractType)",
    "void DatabaseProducerSettings.setFetchSize(int)",
    "void DatabaseProducerSettings.setOpenNewConnections(boolean)",
    "void DatabaseProducerSettings.setQueryRowCount(boolean)",
    "void DatabaseProducerSettings.setSelectedColumnsOnly(boolean)",
    "void DatabaseProducerSettings.setSelectedRowsOnly(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DatabaseProducerSettings databaseProducerSettings = new DatabaseProducerSettings();

    // Act
    databaseProducerSettings.setExtractType(ExtractType.SINGLE_QUERY);
    databaseProducerSettings.setFetchSize(3);
    databaseProducerSettings.setOpenNewConnections(true);
    databaseProducerSettings.setQueryRowCount(true);
    databaseProducerSettings.setSelectedColumnsOnly(true);
    databaseProducerSettings.setSelectedRowsOnly(true);
    ExtractType actualExtractType = databaseProducerSettings.getExtractType();
    int actualFetchSize = databaseProducerSettings.getFetchSize();
    int actualSegmentSize = databaseProducerSettings.getSegmentSize();
    boolean actualIsOpenNewConnectionsResult = databaseProducerSettings.isOpenNewConnections();
    boolean actualIsQueryRowCountResult = databaseProducerSettings.isQueryRowCount();
    boolean actualIsSelectedColumnsOnlyResult = databaseProducerSettings.isSelectedColumnsOnly();

    // Assert
    assertEquals(100000, actualSegmentSize);
    assertEquals(3, actualFetchSize);
    assertEquals(ExtractType.SINGLE_QUERY, actualExtractType);
    assertTrue(actualIsOpenNewConnectionsResult);
    assertTrue(actualIsQueryRowCountResult);
    assertTrue(actualIsSelectedColumnsOnlyResult);
    assertTrue(databaseProducerSettings.isSelectedRowsOnly());
  }

  /**
   * Test {@link DatabaseProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then {@link DatabaseProducerSettings} (default constructor) SegmentSize is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_given42_thenDatabaseProducerSettingsSegmentSizeIsFortyTwo() {
    // Arrange
    DatabaseProducerSettings databaseProducerSettings = new DatabaseProducerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("segmentSize", "42");

    // Act
    databaseProducerSettings.loadSettings(runnableContext, null, settings);

    // Assert
    assertEquals(
        "\tOpen new connection(s): No\n"
            + "\tExtract type: SINGLE_QUERY\n"
            + "\tSelect row count: No\n"
            + "\tSelected rows only: No\n"
            + "\tSelected columns only: No\n",
        databaseProducerSettings.getSettingsSummary());
    assertEquals(42, databaseProducerSettings.getSegmentSize());
    assertFalse(databaseProducerSettings.isOpenNewConnections());
    assertFalse(databaseProducerSettings.isQueryRowCount());
  }

  /**
   * Test {@link DatabaseProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link DatabaseProducerSettings} (default constructor) SegmentSize is {@code
   *       100000}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_whenNull_thenDatabaseProducerSettingsSegmentSizeIs100000() {
    // Arrange
    DatabaseProducerSettings databaseProducerSettings = new DatabaseProducerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    // Act
    databaseProducerSettings.loadSettings(runnableContext, null, new HashMap<>());

    // Assert
    assertEquals(
        "\tOpen new connection(s): No\n"
            + "\tExtract type: SINGLE_QUERY\n"
            + "\tSelect row count: No\n"
            + "\tSelected rows only: No\n"
            + "\tSelected columns only: No\n",
        databaseProducerSettings.getSettingsSummary());
    assertEquals(100000, databaseProducerSettings.getSegmentSize());
    assertFalse(databaseProducerSettings.isOpenNewConnections());
    assertFalse(databaseProducerSettings.isQueryRowCount());
  }

  /**
   * Test {@link DatabaseProducerSettings#saveSettings(Map)}.
   *
   * <p>Method under test: {@link DatabaseProducerSettings#saveSettings(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseProducerSettings.saveSettings(Map)"})
  public void testSaveSettings() {
    // Arrange
    DatabaseProducerSettings databaseProducerSettings = new DatabaseProducerSettings();
    HashMap<String, Object> settings = new HashMap<>();

    // Act
    databaseProducerSettings.saveSettings(settings);

    // Assert
    assertEquals(7, settings.size());
    assertEquals("SINGLE_QUERY", settings.get("extractType"));
    assertEquals(10000, ((Integer) settings.get("fetchSize")).intValue());
    assertEquals(100000, ((Integer) settings.get("segmentSize")).intValue());
    assertFalse((Boolean) settings.get("selectedColumnsOnly"));
    assertFalse((Boolean) settings.get("selectedRowsOnly"));
    assertTrue((Boolean) settings.get("openNewConnections"));
    assertTrue((Boolean) settings.get("queryRowCount"));
  }

  /**
   * Test {@link DatabaseProducerSettings#getSettingsSummary()}.
   *
   * <p>Method under test: {@link DatabaseProducerSettings#getSettingsSummary()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseProducerSettings.getSettingsSummary()"})
  public void testGetSettingsSummary() {
    // Arrange, Act and Assert
    assertEquals(
        "\tOpen new connection(s): Yes\n"
            + "\tExtract type: SINGLE_QUERY\n"
            + "\tSelect row count: Yes\n"
            + "\tSelected rows only: No\n"
            + "\tSelected columns only: No\n",
        new DatabaseProducerSettings().getSettingsSummary());
  }
}
