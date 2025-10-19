package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleDDLFormatDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OracleDDLFormat#getTitle()}
   *   <li>{@link OracleDDLFormat#isShowSegments()}
   *   <li>{@link OracleDDLFormat#isShowStorage()}
   *   <li>{@link OracleDDLFormat#isShowTablespace()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String OracleDDLFormat.getTitle()",
    "boolean OracleDDLFormat.isShowSegments()",
    "boolean OracleDDLFormat.isShowStorage()",
    "boolean OracleDDLFormat.isShowTablespace()"
  })
  public void testGettersAndSetters() {
    // Arrange
    OracleDDLFormat valueOfResult = OracleDDLFormat.valueOf("FULL");

    // Act
    String actualTitle = valueOfResult.getTitle();
    boolean actualIsShowSegmentsResult = valueOfResult.isShowSegments();
    boolean actualIsShowStorageResult = valueOfResult.isShowStorage();

    // Assert
    assertEquals("Full DDL", actualTitle);
    assertTrue(actualIsShowSegmentsResult);
    assertTrue(actualIsShowStorageResult);
    assertTrue(valueOfResult.isShowTablespace());
  }

  /**
   * Test {@link OracleDDLFormat#getCurrentFormat(DBPDataSource)}.
   *
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDDLFormat#getCurrentFormat(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleDDLFormat OracleDDLFormat.getCurrentFormat(DBPDataSource)"})
  public void testGetCurrentFormat_givenIllegalArgumentException() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> OracleDDLFormat.getCurrentFormat(dataSource));
    verify(dataSource).getContainer();
  }

  /**
   * Test {@link OracleDDLFormat#getCurrentFormat(DBPDataSource)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getPreferenceStore()}.
   * </ul>
   *
   * <p>Method under test: {@link OracleDDLFormat#getCurrentFormat(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleDDLFormat OracleDDLFormat.getCurrentFormat(DBPDataSource)"})
  public void testGetCurrentFormat_thenCallsGetPreferenceStore() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new IllegalArgumentException());

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> OracleDDLFormat.getCurrentFormat(dataSource));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getPreferenceStore();
  }
}
