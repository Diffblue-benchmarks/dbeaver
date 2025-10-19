package org.jkiss.dbeaver.model.sql.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLGeneratorBaseDiffblueTest {
  /**
   * Test {@link SQLGeneratorBase#getValueAttributes(DBRProgressMonitor, Object, Collection)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorBase#getValueAttributes(DBRProgressMonitor, Object,
   * Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection SQLGeneratorBase.getValueAttributes(DBRProgressMonitor, Object, Collection)"
  })
  public void testGetValueAttributes_thenCallsGetContainer() throws DBException {
    // Arrange
    SQLGeneratorDelete sqlGeneratorDelete = new SQLGeneratorDelete();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "org.jkiss.dbeaver.model");
    DBVEntity entity =
        new DBVEntity(container2, "org.jkiss.dbeaver.model", "org.jkiss.dbeaver.model");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "org.jkiss.dbeaver.model");
    dbvEntity.addVirtualAttribute(attribute);

    // Act
    Collection<? extends DBSAttributeBase> actualValueAttributes =
        sqlGeneratorDelete.getValueAttributes(monitor, dbvEntity, new ArrayList<>());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualValueAttributes instanceof List);
    assertTrue(actualValueAttributes.isEmpty());
  }

  /**
   * Test {@link SQLGeneratorBase#getValueAttributes(DBRProgressMonitor, Object, Collection)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorBase#getValueAttributes(DBRProgressMonitor, Object,
   * Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection SQLGeneratorBase.getValueAttributes(DBRProgressMonitor, Object, Collection)"
  })
  public void testGetValueAttributes_thenThrowDBException() throws DBException {
    // Arrange
    SQLGeneratorDelete sqlGeneratorDelete = new SQLGeneratorDelete();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> sqlGeneratorDelete.getValueAttributes(monitor, dbvEntity, new ArrayList<>()));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link SQLGeneratorBase#appendDefaultValue(StringBuilder, DBSAttributeBase)}.
   *
   * <ul>
   *   <li>Given {@code Attr}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       fooAttr}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorBase#appendDefaultValue(StringBuilder,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorBase.appendDefaultValue(StringBuilder, DBSAttributeBase)"})
  public void testAppendDefaultValue_givenAttr_thenStringBuilderWithFooToStringIsFooAttr() {
    // Arrange
    SQLGeneratorDelete sqlGeneratorDelete = new SQLGeneratorDelete();
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute attr = new DBVEntityAttribute(entity, parent, "Name");
    attr.setDefaultValue("Attr");

    // Act
    sqlGeneratorDelete.appendDefaultValue(sql, attr);

    // Assert
    assertEquals("fooAttr", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorBase#appendDefaultValue(StringBuilder, DBSAttributeBase)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorBase#appendDefaultValue(StringBuilder,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorBase.appendDefaultValue(StringBuilder, DBSAttributeBase)"})
  public void testAppendDefaultValue_givenEmptyString() {
    // Arrange
    SQLGeneratorDelete sqlGeneratorDelete = new SQLGeneratorDelete();
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute attr = new DBVEntityAttribute(entity, parent, "Name");
    attr.setDefaultValue("");

    // Act
    sqlGeneratorDelete.appendDefaultValue(sql, attr);

    // Assert
    assertEquals("foo?", sql.toString());
  }

  /**
   * Test {@link SQLGeneratorBase#appendDefaultValue(StringBuilder, DBSAttributeBase)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGeneratorBase#appendDefaultValue(StringBuilder,
   * DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGeneratorBase.appendDefaultValue(StringBuilder, DBSAttributeBase)"})
  public void testAppendDefaultValue_givenNull() {
    // Arrange
    SQLGeneratorDelete sqlGeneratorDelete = new SQLGeneratorDelete();
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute attr = new DBVEntityAttribute(entity, parent, "Name");
    attr.setDefaultValue(null);

    // Act
    sqlGeneratorDelete.appendDefaultValue(sql, attr);

    // Assert
    assertEquals("foo?", sql.toString());
  }
}
