package org.jkiss.dbeaver.model.ai.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPObjectWithDescription;
import org.jkiss.dbeaver.model.logical.DBSLogicalDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseMetadataUtilsDiffblueTest {
  /**
   * Test {@link DatabaseMetadataUtils#generateObjectDescription(DBRProgressMonitor,
   * DBPObjectWithDescription)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then calls {@link DBPObjectWithDescription#getDescription()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DatabaseMetadataUtils#generateObjectDescription(DBRProgressMonitor, DBPObjectWithDescription)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseMetadataUtils.generateObjectDescription(DBRProgressMonitor, DBPObjectWithDescription)"
  })
  public void testGenerateObjectDescription_givenEmptyString_thenCallsGetDescription() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPObjectWithDescription object = mock(DBPObjectWithDescription.class);
    when(object.getDescription()).thenReturn("");

    // Act
    String actualGenerateObjectDescriptionResult =
        DatabaseMetadataUtils.generateObjectDescription(monitor, object);

    // Assert
    verify(object, atLeast(1)).getDescription();
    assertEquals("-- \n", actualGenerateObjectDescriptionResult);
  }

  /**
   * Test {@link DatabaseMetadataUtils#generateObjectDescription(DBRProgressMonitor,
   * DBPObjectWithDescription)}.
   *
   * <ul>
   *   <li>Given lf.
   * </ul>
   *
   * <p>Method under test: {@link
   * DatabaseMetadataUtils#generateObjectDescription(DBRProgressMonitor, DBPObjectWithDescription)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseMetadataUtils.generateObjectDescription(DBRProgressMonitor, DBPObjectWithDescription)"
  })
  public void testGenerateObjectDescription_givenLf() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSLogicalDataSource object = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));
    object.setDescription("\n");

    // Act
    String actualGenerateObjectDescriptionResult =
        DatabaseMetadataUtils.generateObjectDescription(monitor, object);

    // Assert
    assertEquals("-- \n\n", actualGenerateObjectDescriptionResult);
  }

  /**
   * Test {@link DatabaseMetadataUtils#generateObjectDescription(DBRProgressMonitor,
   * DBPObjectWithDescription)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DatabaseMetadataUtils#generateObjectDescription(DBRProgressMonitor, DBPObjectWithDescription)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseMetadataUtils.generateObjectDescription(DBRProgressMonitor, DBPObjectWithDescription)"
  })
  public void testGenerateObjectDescription_givenObject_thenReturnEmptyString() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSLogicalDataSource object = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));
    object.setDescription("Object");

    // Act
    String actualGenerateObjectDescriptionResult =
        DatabaseMetadataUtils.generateObjectDescription(monitor, object);

    // Assert
    assertEquals("", actualGenerateObjectDescriptionResult);
  }
}
