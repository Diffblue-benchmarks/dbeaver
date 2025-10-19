package org.jkiss.dbeaver.model.impl.sql.edit.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.edit.DBEPersistAction.ActionType;
import org.jkiss.dbeaver.model.impl.edit.DBECommandAbstract;
import org.jkiss.dbeaver.model.impl.edit.SQLDatabasePersistAction;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.edit.struct.SQLTableColumnManager.BaseDefaultModifier;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSDataType;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLTableColumnManagerDiffblueTest {
  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#appendDefaultValue(StringBuilder, String,
   * boolean)}.
   *
   * <p>Method under test: {@link BaseDefaultModifier#appendDefaultValue(StringBuilder, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BaseDefaultModifier.appendDefaultValue(StringBuilder, String, boolean)"})
  public void testBaseDefaultModifierAppendDefaultValue() {
    // Arrange
    BaseDefaultModifier baseDefaultModifier =
        mock(SQLTableColumnManager.class).new BaseDefaultModifier();
    StringBuilder sql = new StringBuilder("foo");

    // Act
    baseDefaultModifier.appendDefaultValue(sql, "42", true);

    // Assert
    assertEquals("foo'42'", sql.toString());
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#appendDefaultValue(StringBuilder, String,
   * boolean)}.
   *
   * <p>Method under test: {@link BaseDefaultModifier#appendDefaultValue(StringBuilder, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BaseDefaultModifier.appendDefaultValue(StringBuilder, String, boolean)"})
  public void testBaseDefaultModifierAppendDefaultValue2() {
    // Arrange
    BaseDefaultModifier baseDefaultModifier =
        mock(SQLTableColumnManager.class).new BaseDefaultModifier();
    StringBuilder sql = new StringBuilder("foo");

    // Act
    baseDefaultModifier.appendDefaultValue(sql, "42", false);

    // Assert
    assertEquals("foo42", sql.toString());
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)} with {@code DBRProgressMonitor}, {@code
   * DBSEntityAttribute}, {@code StringBuilder}, {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BaseDefaultModifier.appendModifier(DBRProgressMonitor, DBSEntityAttribute, StringBuilder, DBECommandAbstract)"
  })
  public void
      testBaseDefaultModifierAppendModifierWithDBRProgressMonitorDBSEntityAttributeStringBuilderDBECommandAbstract() {
    // Arrange
    BaseDefaultModifier baseDefaultModifier =
        mock(SQLTableColumnManager.class).new BaseDefaultModifier();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    // Act
    baseDefaultModifier.appendModifier(
        monitor, dbvEntityAttribute, sql, new DBECommandAbstract<>(dbvEntityAttribute2, "Dr"));

    // Assert that nothing has changed
    assertEquals("foo", sql.toString());
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)} with {@code DBRProgressMonitor}, {@code
   * DBSEntityAttribute}, {@code StringBuilder}, {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BaseDefaultModifier.appendModifier(DBRProgressMonitor, DBSEntityAttribute, StringBuilder, DBECommandAbstract)"
  })
  public void
      testBaseDefaultModifierAppendModifierWithDBRProgressMonitorDBSEntityAttributeStringBuilderDBECommandAbstract2() {
    // Arrange
    BaseDefaultModifier baseDefaultModifier =
        mock(SQLTableColumnManager.class).new BaseDefaultModifier();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, parent, "Name");
    dbvEntityAttribute.setDefaultValue("");
    dbvEntityAttribute.setDataKind(DBPDataKind.STRING);
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute2 =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    // Act
    baseDefaultModifier.appendModifier(
        monitor, dbvEntityAttribute, sql, new DBECommandAbstract<>(dbvEntityAttribute2, "Dr"));

    // Assert that nothing has changed
    assertEquals("foo", sql.toString());
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)} with {@code DBRProgressMonitor}, {@code
   * DBSEntityAttribute}, {@code StringBuilder}, {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BaseDefaultModifier.appendModifier(DBRProgressMonitor, DBSEntityAttribute, StringBuilder, DBECommandAbstract)"
  })
  public void
      testBaseDefaultModifierAppendModifierWithDBRProgressMonitorDBSEntityAttributeStringBuilderDBECommandAbstract3() {
    // Arrange
    BaseDefaultModifier baseDefaultModifier =
        mock(SQLTableColumnManager.class).new BaseDefaultModifier();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, parent, "Name");
    dbvEntityAttribute.setDefaultValue(SQLTableColumnManager.QUOTE);
    dbvEntityAttribute.setDataKind(DBPDataKind.STRING);
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute2 =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    // Act
    baseDefaultModifier.appendModifier(
        monitor, dbvEntityAttribute, sql, new DBECommandAbstract<>(dbvEntityAttribute2, "Dr"));

    // Assert
    assertEquals("foo DEFAULT '", sql.toString());
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)} with {@code DBRProgressMonitor}, {@code
   * DBSEntityAttribute}, {@code StringBuilder}, {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BaseDefaultModifier.appendModifier(DBRProgressMonitor, DBSEntityAttribute, StringBuilder, DBECommandAbstract)"
  })
  public void
      testBaseDefaultModifierAppendModifierWithDBRProgressMonitorDBSEntityAttributeStringBuilderDBECommandAbstract4() {
    // Arrange
    BaseDefaultModifier baseDefaultModifier =
        mock(SQLTableColumnManager.class).new BaseDefaultModifier();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, parent, "Name");
    dbvEntityAttribute.setDefaultValue("Column");
    dbvEntityAttribute.setDataKind(DBPDataKind.STRING);
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute2 =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    // Act
    baseDefaultModifier.appendModifier(
        monitor, dbvEntityAttribute, sql, new DBECommandAbstract<>(dbvEntityAttribute2, "Dr"));

    // Assert
    assertEquals("foo DEFAULT Column", sql.toString());
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)} with {@code DBRProgressMonitor}, {@code
   * DBSEntityAttribute}, {@code StringBuilder}, {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BaseDefaultModifier.appendModifier(DBRProgressMonitor, DBSEntityAttribute, StringBuilder, DBECommandAbstract)"
  })
  public void
      testBaseDefaultModifierAppendModifierWithDBRProgressMonitorDBSEntityAttributeStringBuilderDBECommandAbstract5() {
    // Arrange
    BaseDefaultModifier baseDefaultModifier =
        mock(SQLTableColumnManager.class).new BaseDefaultModifier();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, parent, "Name");
    dbvEntityAttribute.setDefaultValue("Column");
    dbvEntityAttribute.setDataKind(DBPDataKind.DATETIME);
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute2 =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    // Act
    baseDefaultModifier.appendModifier(
        monitor, dbvEntityAttribute, sql, new DBECommandAbstract<>(dbvEntityAttribute2, "Dr"));

    // Assert
    assertEquals("foo DEFAULT Column", sql.toString());
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)} with {@code DBRProgressMonitor}, {@code
   * DBSEntityAttribute}, {@code StringBuilder}, {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BaseDefaultModifier.appendModifier(DBRProgressMonitor, DBSEntityAttribute, StringBuilder, DBECommandAbstract)"
  })
  public void
      testBaseDefaultModifierAppendModifierWithDBRProgressMonitorDBSEntityAttributeStringBuilderDBECommandAbstract6() {
    // Arrange
    BaseDefaultModifier baseDefaultModifier =
        mock(SQLTableColumnManager.class).new BaseDefaultModifier();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    dbvEntityAttribute.setDefaultValue("42");
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    // Act
    baseDefaultModifier.appendModifier(
        monitor, dbvEntityAttribute, sql, new DBECommandAbstract<>(dbvEntityAttribute2, "Dr"));

    // Assert
    assertEquals("foo DEFAULT 42", sql.toString());
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)} with {@code DBRProgressMonitor}, {@code
   * DBSEntityAttribute}, {@code StringBuilder}, {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link BaseDefaultModifier#appendModifier(DBRProgressMonitor,
   * DBSEntityAttribute, StringBuilder, DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BaseDefaultModifier.appendModifier(DBRProgressMonitor, DBSEntityAttribute, StringBuilder, DBECommandAbstract)"
  })
  public void
      testBaseDefaultModifierAppendModifierWithDBRProgressMonitorDBSEntityAttributeStringBuilderDBECommandAbstract7() {
    // Arrange
    BaseDefaultModifier baseDefaultModifier =
        mock(SQLTableColumnManager.class).new BaseDefaultModifier();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, parent, "Name");
    dbvEntityAttribute.setDefaultValue("42");
    dbvEntityAttribute.setDataKind(DBPDataKind.STRING);
    StringBuilder sql = new StringBuilder("foo");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute2 =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    // Act
    baseDefaultModifier.appendModifier(
        monitor, dbvEntityAttribute, sql, new DBECommandAbstract<>(dbvEntityAttribute2, "Dr"));

    // Assert
    assertEquals("foo DEFAULT '42'", sql.toString());
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BaseDefaultModifier.isUsesQuotes(String, DBPDataKind)"})
  public void testBaseDefaultModifierIsUsesQuotes_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        mock(SQLTableColumnManager.class).new BaseDefaultModifier()
            .isUsesQuotes("42", DBPDataKind.STRING));
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@code BOOLEAN}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BaseDefaultModifier.isUsesQuotes(String, DBPDataKind)"})
  public void testBaseDefaultModifierIsUsesQuotes_whenBoolean_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        mock(SQLTableColumnManager.class).new BaseDefaultModifier()
            .isUsesQuotes("42", DBPDataKind.BOOLEAN));
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@code DATETIME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BaseDefaultModifier.isUsesQuotes(String, DBPDataKind)"})
  public void testBaseDefaultModifierIsUsesQuotes_whenDatetime_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        mock(SQLTableColumnManager.class).new BaseDefaultModifier()
            .isUsesQuotes("42", DBPDataKind.DATETIME));
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@code Default Value}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BaseDefaultModifier.isUsesQuotes(String, DBPDataKind)"})
  public void testBaseDefaultModifierIsUsesQuotes_whenDefaultValue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        mock(SQLTableColumnManager.class).new BaseDefaultModifier()
            .isUsesQuotes("Default Value", DBPDataKind.STRING));
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BaseDefaultModifier.isUsesQuotes(String, DBPDataKind)"})
  public void testBaseDefaultModifierIsUsesQuotes_whenEmptyString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        mock(SQLTableColumnManager.class).new BaseDefaultModifier()
            .isUsesQuotes("", DBPDataKind.STRING));
  }

  /**
   * Test BaseDefaultModifier {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@link SQLTableColumnManager#QUOTE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link BaseDefaultModifier#isUsesQuotes(String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BaseDefaultModifier.isUsesQuotes(String, DBPDataKind)"})
  public void testBaseDefaultModifierIsUsesQuotes_whenQuote_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        mock(SQLTableColumnManager.class).new BaseDefaultModifier()
            .isUsesQuotes(SQLTableColumnManager.QUOTE, DBPDataKind.STRING));
  }

  /**
   * Test {@link SQLTableColumnManager#findBestDataType(DBSObject, String[])}.
   *
   * <p>Method under test: {@link SQLTableColumnManager#findBestDataType(DBSObject, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataType SQLTableColumnManager.findBestDataType(DBSObject, String[])"})
  public void testFindBestDataType() {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getParentObject()).thenReturn(null);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getParentObject()).thenReturn(new DBSDocumentConstraint(entity));

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getParentObject()).thenReturn(new DBSDocumentConstraint(entity2));

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getParentObject()).thenReturn(new DBSDocumentConstraint(entity3));

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getParentObject()).thenReturn(new DBSDocumentConstraint(entity4));

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getParentObject()).thenReturn(new DBSDocumentConstraint(entity5));

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getParentObject()).thenReturn(new DBSDocumentConstraint(entity6));

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getParentObject()).thenReturn(new DBSDocumentConstraint(entity7));

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getParentObject()).thenReturn(new DBSDocumentConstraint(entity8));

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getParentObject()).thenReturn(new DBSDocumentConstraint(entity9));

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getParentObject()).thenReturn(new DBSDocumentConstraint(entity10));

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getParentObject()).thenReturn(new DBSDocumentConstraint(entity11));

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getParentObject()).thenReturn(new DBSDocumentConstraint(entity12));

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getParentObject()).thenReturn(new DBSDocumentConstraint(entity13));

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getParentObject()).thenReturn(new DBSDocumentConstraint(entity14));

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getParentObject()).thenReturn(new DBSDocumentConstraint(entity15));

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getParentObject()).thenReturn(new DBSDocumentConstraint(entity16));

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getParentObject()).thenReturn(new DBSDocumentConstraint(entity17));

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getParentObject()).thenReturn(new DBSDocumentConstraint(entity18));

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getParentObject()).thenReturn(new DBSDocumentConstraint(entity19));

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getParentObject()).thenReturn(new DBSDocumentConstraint(entity20));

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getParentObject()).thenReturn(new DBSDocumentConstraint(entity21));

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getParentObject()).thenReturn(new DBSDocumentConstraint(entity22));

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getParentObject()).thenReturn(new DBSDocumentConstraint(entity23));

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getParentObject()).thenReturn(new DBSDocumentConstraint(entity24));

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getParentObject()).thenReturn(new DBSDocumentConstraint(entity25));

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getParentObject()).thenReturn(new DBSDocumentConstraint(entity26));

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getParentObject()).thenReturn(new DBSDocumentConstraint(entity27));

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getParentObject()).thenReturn(new DBSDocumentConstraint(entity28));

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getParentObject()).thenReturn(new DBSDocumentConstraint(entity29));

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getParentObject()).thenReturn(new DBSDocumentConstraint(entity30));

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getParentObject()).thenReturn(new DBSDocumentConstraint(entity31));

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getParentObject()).thenReturn(new DBSDocumentConstraint(entity32));

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getParentObject()).thenReturn(new DBSDocumentConstraint(entity33));

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getParentObject()).thenReturn(new DBSDocumentConstraint(entity34));

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getParentObject()).thenReturn(new DBSDocumentConstraint(entity35));

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getParentObject()).thenReturn(new DBSDocumentConstraint(entity36));

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getParentObject()).thenReturn(new DBSDocumentConstraint(entity37));

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getParentObject()).thenReturn(new DBSDocumentConstraint(entity38));

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getParentObject()).thenReturn(new DBSDocumentConstraint(entity39));

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getParentObject()).thenReturn(new DBSDocumentConstraint(entity40));

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getParentObject()).thenReturn(new DBSDocumentConstraint(entity41));

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getParentObject()).thenReturn(new DBSDocumentConstraint(entity42));

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getParentObject()).thenReturn(new DBSDocumentConstraint(entity43));

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getParentObject()).thenReturn(new DBSDocumentConstraint(entity44));

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getParentObject()).thenReturn(new DBSDocumentConstraint(entity45));

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getParentObject()).thenReturn(new DBSDocumentConstraint(entity46));

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getParentObject()).thenReturn(new DBSDocumentConstraint(entity47));

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getParentObject()).thenReturn(new DBSDocumentConstraint(entity48));

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getParentObject()).thenReturn(new DBSDocumentConstraint(entity49));

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getParentObject()).thenReturn(new DBSDocumentConstraint(entity50));

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getParentObject()).thenReturn(new DBSDocumentConstraint(entity51));

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getParentObject()).thenReturn(new DBSDocumentConstraint(entity52));

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getParentObject()).thenReturn(new DBSDocumentConstraint(entity53));

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getParentObject()).thenReturn(new DBSDocumentConstraint(entity54));

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getParentObject()).thenReturn(new DBSDocumentConstraint(entity55));

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getParentObject()).thenReturn(new DBSDocumentConstraint(entity56));

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getParentObject()).thenReturn(new DBSDocumentConstraint(entity57));

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getParentObject()).thenReturn(new DBSDocumentConstraint(entity58));

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getParentObject()).thenReturn(new DBSDocumentConstraint(entity59));

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getParentObject()).thenReturn(new DBSDocumentConstraint(entity60));

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getParentObject()).thenReturn(new DBSDocumentConstraint(entity61));

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getParentObject()).thenReturn(new DBSDocumentConstraint(entity62));

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getParentObject()).thenReturn(new DBSDocumentConstraint(entity63));

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getParentObject()).thenReturn(new DBSDocumentConstraint(entity64));

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getParentObject()).thenReturn(new DBSDocumentConstraint(entity65));

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getParentObject()).thenReturn(new DBSDocumentConstraint(entity66));

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getParentObject()).thenReturn(new DBSDocumentConstraint(entity67));

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getParentObject()).thenReturn(new DBSDocumentConstraint(entity68));

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getParentObject()).thenReturn(new DBSDocumentConstraint(entity69));

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getParentObject()).thenReturn(new DBSDocumentConstraint(entity70));

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getParentObject()).thenReturn(new DBSDocumentConstraint(entity71));

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getParentObject()).thenReturn(new DBSDocumentConstraint(entity72));

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getParentObject()).thenReturn(new DBSDocumentConstraint(entity73));

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getParentObject()).thenReturn(new DBSDocumentConstraint(entity74));

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getParentObject()).thenReturn(new DBSDocumentConstraint(entity75));

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getParentObject()).thenReturn(new DBSDocumentConstraint(entity76));

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getParentObject()).thenReturn(new DBSDocumentConstraint(entity77));

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getParentObject()).thenReturn(new DBSDocumentConstraint(entity78));

    DBSDocumentContainer entity80 = mock(DBSDocumentContainer.class);
    when(entity80.getParentObject()).thenReturn(new DBSDocumentConstraint(entity79));

    DBSDocumentContainer entity81 = mock(DBSDocumentContainer.class);
    when(entity81.getParentObject()).thenReturn(new DBSDocumentConstraint(entity80));

    DBSDocumentContainer entity82 = mock(DBSDocumentContainer.class);
    when(entity82.getParentObject()).thenReturn(new DBSDocumentConstraint(entity81));

    DBSDocumentContainer entity83 = mock(DBSDocumentContainer.class);
    when(entity83.getParentObject()).thenReturn(new DBSDocumentConstraint(entity82));

    DBSDocumentContainer entity84 = mock(DBSDocumentContainer.class);
    when(entity84.getParentObject()).thenReturn(new DBSDocumentConstraint(entity83));

    DBSDocumentContainer entity85 = mock(DBSDocumentContainer.class);
    when(entity85.getParentObject()).thenReturn(new DBSDocumentConstraint(entity84));

    DBSDocumentContainer entity86 = mock(DBSDocumentContainer.class);
    when(entity86.getParentObject()).thenReturn(new DBSDocumentConstraint(entity85));

    DBSDocumentContainer entity87 = mock(DBSDocumentContainer.class);
    when(entity87.getParentObject()).thenReturn(new DBSDocumentConstraint(entity86));

    DBSDocumentContainer entity88 = mock(DBSDocumentContainer.class);
    when(entity88.getParentObject()).thenReturn(new DBSDocumentConstraint(entity87));

    DBSDocumentContainer entity89 = mock(DBSDocumentContainer.class);
    when(entity89.getParentObject()).thenReturn(new DBSDocumentConstraint(entity88));

    DBSDocumentContainer entity90 = mock(DBSDocumentContainer.class);
    when(entity90.getParentObject()).thenReturn(new DBSDocumentConstraint(entity89));

    DBSDocumentContainer entity91 = mock(DBSDocumentContainer.class);
    when(entity91.getParentObject()).thenReturn(new DBSDocumentConstraint(entity90));

    DBSDocumentContainer entity92 = mock(DBSDocumentContainer.class);
    when(entity92.getParentObject()).thenReturn(new DBSDocumentConstraint(entity91));

    DBSDocumentContainer entity93 = mock(DBSDocumentContainer.class);
    when(entity93.getParentObject()).thenReturn(new DBSDocumentConstraint(entity92));

    DBSDocumentContainer entity94 = mock(DBSDocumentContainer.class);
    when(entity94.getParentObject()).thenReturn(new DBSDocumentConstraint(entity93));

    DBSDocumentContainer entity95 = mock(DBSDocumentContainer.class);
    when(entity95.getParentObject()).thenReturn(new DBSDocumentConstraint(entity94));

    DBSDocumentContainer entity96 = mock(DBSDocumentContainer.class);
    when(entity96.getParentObject()).thenReturn(new DBSDocumentConstraint(entity95));

    DBSDocumentContainer entity97 = mock(DBSDocumentContainer.class);
    when(entity97.getParentObject()).thenReturn(new DBSDocumentConstraint(entity96));

    DBSDocumentContainer entity98 = mock(DBSDocumentContainer.class);
    when(entity98.getParentObject()).thenReturn(new DBSDocumentConstraint(entity97));

    DBSDocumentContainer entity99 = mock(DBSDocumentContainer.class);
    when(entity99.getParentObject()).thenReturn(new DBSDocumentConstraint(entity98));

    DBSDocumentContainer entity100 = mock(DBSDocumentContainer.class);
    when(entity100.getParentObject()).thenReturn(new DBSDocumentConstraint(entity99));

    DBSDocumentContainer entity101 = mock(DBSDocumentContainer.class);
    when(entity101.getParentObject()).thenReturn(new DBSDocumentConstraint(entity100));

    DBSDocumentContainer entity102 = mock(DBSDocumentContainer.class);
    when(entity102.getParentObject()).thenReturn(new DBSDocumentConstraint(entity101));

    DBSDocumentContainer entity103 = mock(DBSDocumentContainer.class);
    when(entity103.getParentObject()).thenReturn(new DBSDocumentConstraint(entity102));

    DBSDocumentContainer entity104 = mock(DBSDocumentContainer.class);
    when(entity104.getParentObject()).thenReturn(new DBSDocumentConstraint(entity103));

    DBSDocumentContainer entity105 = mock(DBSDocumentContainer.class);
    when(entity105.getParentObject()).thenReturn(new DBSDocumentConstraint(entity104));

    DBSDocumentContainer entity106 = mock(DBSDocumentContainer.class);
    when(entity106.getParentObject()).thenReturn(new DBSDocumentConstraint(entity105));

    DBSDocumentContainer entity107 = mock(DBSDocumentContainer.class);
    when(entity107.getParentObject()).thenReturn(new DBSDocumentConstraint(entity106));

    DBSDocumentContainer entity108 = mock(DBSDocumentContainer.class);
    when(entity108.getParentObject()).thenReturn(new DBSDocumentConstraint(entity107));

    DBSDocumentContainer entity109 = mock(DBSDocumentContainer.class);
    when(entity109.getParentObject()).thenReturn(new DBSDocumentConstraint(entity108));

    DBSDocumentContainer entity110 = mock(DBSDocumentContainer.class);
    when(entity110.getParentObject()).thenReturn(new DBSDocumentConstraint(entity109));

    DBSDocumentContainer entity111 = mock(DBSDocumentContainer.class);
    when(entity111.getParentObject()).thenReturn(new DBSDocumentConstraint(entity110));

    DBSDocumentContainer entity112 = mock(DBSDocumentContainer.class);
    when(entity112.getParentObject()).thenReturn(new DBSDocumentConstraint(entity111));

    DBSDocumentContainer entity113 = mock(DBSDocumentContainer.class);
    when(entity113.getParentObject()).thenReturn(new DBSDocumentConstraint(entity112));

    DBSDocumentContainer entity114 = mock(DBSDocumentContainer.class);
    when(entity114.getParentObject()).thenReturn(new DBSDocumentConstraint(entity113));

    DBSDocumentContainer entity115 = mock(DBSDocumentContainer.class);
    when(entity115.getParentObject()).thenReturn(new DBSDocumentConstraint(entity114));

    DBSDocumentContainer entity116 = mock(DBSDocumentContainer.class);
    when(entity116.getParentObject()).thenReturn(new DBSDocumentConstraint(entity115));

    DBSDocumentContainer entity117 = mock(DBSDocumentContainer.class);
    when(entity117.getParentObject()).thenReturn(new DBSDocumentConstraint(entity116));

    DBSDocumentContainer entity118 = mock(DBSDocumentContainer.class);
    when(entity118.getParentObject()).thenReturn(new DBSDocumentConstraint(entity117));

    DBSDocumentContainer entity119 = mock(DBSDocumentContainer.class);
    when(entity119.getParentObject()).thenReturn(new DBSDocumentConstraint(entity118));

    DBSDocumentContainer entity120 = mock(DBSDocumentContainer.class);
    when(entity120.getParentObject()).thenReturn(new DBSDocumentConstraint(entity119));

    DBSDocumentContainer entity121 = mock(DBSDocumentContainer.class);
    when(entity121.getParentObject()).thenReturn(new DBSDocumentConstraint(entity120));

    DBSDocumentContainer entity122 = mock(DBSDocumentContainer.class);
    when(entity122.getParentObject()).thenReturn(new DBSDocumentConstraint(entity121));

    DBSDocumentContainer entity123 = mock(DBSDocumentContainer.class);
    when(entity123.getParentObject()).thenReturn(new DBSDocumentConstraint(entity122));

    DBSDocumentContainer entity124 = mock(DBSDocumentContainer.class);
    when(entity124.getParentObject()).thenReturn(new DBSDocumentConstraint(entity123));

    DBSDocumentContainer entity125 = mock(DBSDocumentContainer.class);
    when(entity125.getParentObject()).thenReturn(new DBSDocumentConstraint(entity124));

    DBSDocumentContainer entity126 = mock(DBSDocumentContainer.class);
    when(entity126.getParentObject()).thenReturn(new DBSDocumentConstraint(entity125));

    DBSDocumentContainer entity127 = mock(DBSDocumentContainer.class);
    when(entity127.getParentObject()).thenReturn(new DBSDocumentConstraint(entity126));

    DBSDocumentContainer entity128 = mock(DBSDocumentContainer.class);
    when(entity128.getParentObject()).thenReturn(new DBSDocumentConstraint(entity127));

    DBSDocumentContainer entity129 = mock(DBSDocumentContainer.class);
    when(entity129.getParentObject()).thenReturn(new DBSDocumentConstraint(entity128));

    DBSDocumentContainer entity130 = mock(DBSDocumentContainer.class);
    when(entity130.getParentObject()).thenReturn(new DBSDocumentConstraint(entity129));

    DBSDocumentContainer entity131 = mock(DBSDocumentContainer.class);
    when(entity131.getParentObject()).thenReturn(new DBSDocumentConstraint(entity130));

    // Act
    DBSDataType actualFindBestDataTypeResult =
        SQLTableColumnManager.findBestDataType(new DBSDocumentConstraint(entity131), "Type Names");

    // Assert
    verify(entity131).getParentObject();
    verify(entity130).getParentObject();
    verify(entity129).getParentObject();
    verify(entity128).getParentObject();
    verify(entity127).getParentObject();
    verify(entity126).getParentObject();
    verify(entity125).getParentObject();
    verify(entity124).getParentObject();
    verify(entity123).getParentObject();
    verify(entity122).getParentObject();
    verify(entity121).getParentObject();
    verify(entity120).getParentObject();
    verify(entity119).getParentObject();
    verify(entity118).getParentObject();
    verify(entity117).getParentObject();
    verify(entity116).getParentObject();
    verify(entity115).getParentObject();
    verify(entity114).getParentObject();
    verify(entity113).getParentObject();
    verify(entity112).getParentObject();
    verify(entity111).getParentObject();
    verify(entity110).getParentObject();
    verify(entity109).getParentObject();
    verify(entity108).getParentObject();
    verify(entity107).getParentObject();
    verify(entity106).getParentObject();
    verify(entity105).getParentObject();
    verify(entity104).getParentObject();
    verify(entity103).getParentObject();
    verify(entity102).getParentObject();
    verify(entity101).getParentObject();
    verify(entity100).getParentObject();
    verify(entity99).getParentObject();
    verify(entity98).getParentObject();
    verify(entity97).getParentObject();
    verify(entity96).getParentObject();
    verify(entity95).getParentObject();
    verify(entity94).getParentObject();
    verify(entity93).getParentObject();
    verify(entity92).getParentObject();
    verify(entity91).getParentObject();
    verify(entity90).getParentObject();
    verify(entity89).getParentObject();
    verify(entity88).getParentObject();
    verify(entity87).getParentObject();
    verify(entity86).getParentObject();
    verify(entity85).getParentObject();
    verify(entity84).getParentObject();
    verify(entity83).getParentObject();
    verify(entity82).getParentObject();
    verify(entity81).getParentObject();
    verify(entity80).getParentObject();
    verify(entity79).getParentObject();
    verify(entity78).getParentObject();
    verify(entity77).getParentObject();
    verify(entity76).getParentObject();
    verify(entity75).getParentObject();
    verify(entity74).getParentObject();
    verify(entity73).getParentObject();
    verify(entity72).getParentObject();
    verify(entity71).getParentObject();
    verify(entity70).getParentObject();
    verify(entity69).getParentObject();
    verify(entity68).getParentObject();
    verify(entity67).getParentObject();
    verify(entity66).getParentObject();
    verify(entity65).getParentObject();
    verify(entity64).getParentObject();
    verify(entity63).getParentObject();
    verify(entity62).getParentObject();
    verify(entity61).getParentObject();
    verify(entity60).getParentObject();
    verify(entity59).getParentObject();
    verify(entity58).getParentObject();
    verify(entity57).getParentObject();
    verify(entity56).getParentObject();
    verify(entity55).getParentObject();
    verify(entity54).getParentObject();
    verify(entity53).getParentObject();
    verify(entity52).getParentObject();
    verify(entity51).getParentObject();
    verify(entity50).getParentObject();
    verify(entity49).getParentObject();
    verify(entity48).getParentObject();
    verify(entity47).getParentObject();
    verify(entity46).getParentObject();
    verify(entity45).getParentObject();
    verify(entity44).getParentObject();
    verify(entity43).getParentObject();
    verify(entity42).getParentObject();
    verify(entity41).getParentObject();
    verify(entity40).getParentObject();
    verify(entity39).getParentObject();
    verify(entity38).getParentObject();
    verify(entity37).getParentObject();
    verify(entity36).getParentObject();
    verify(entity35).getParentObject();
    verify(entity34).getParentObject();
    verify(entity33).getParentObject();
    verify(entity32).getParentObject();
    verify(entity31).getParentObject();
    verify(entity30).getParentObject();
    verify(entity29).getParentObject();
    verify(entity28).getParentObject();
    verify(entity27).getParentObject();
    verify(entity26).getParentObject();
    verify(entity25).getParentObject();
    verify(entity24).getParentObject();
    verify(entity23).getParentObject();
    verify(entity22).getParentObject();
    verify(entity21).getParentObject();
    verify(entity20).getParentObject();
    verify(entity19).getParentObject();
    verify(entity18).getParentObject();
    verify(entity17).getParentObject();
    verify(entity16).getParentObject();
    verify(entity15).getParentObject();
    verify(entity14).getParentObject();
    verify(entity13).getParentObject();
    verify(entity12).getParentObject();
    verify(entity11).getParentObject();
    verify(entity10).getParentObject();
    verify(entity9).getParentObject();
    verify(entity8).getParentObject();
    verify(entity7).getParentObject();
    verify(entity6).getParentObject();
    verify(entity5).getParentObject();
    verify(entity4).getParentObject();
    verify(entity3).getParentObject();
    verify(entity2).getParentObject();
    verify(entity).getParentObject();
    assertNull(actualFindBestDataTypeResult);
  }

  /**
   * Test {@link SQLTableColumnManager#addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)}.
   *
   * <p>Method under test: {@link SQLTableColumnManager#addColumnCommentAction(List,
   * DBSEntityAttribute, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLTableColumnManager.addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)"
  })
  public void testAddColumnCommentAction() {
    // Arrange
    ArrayList<DBEPersistAction> actionList = new ArrayList<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute column =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container2 = new DBVContainer(parent2, "Name");
    DBVEntity table = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    SQLTableColumnManager.addColumnCommentAction(actionList, column, table);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dbpDataSource2, atLeast(1)).getSQLDialect();
    verify(parent2).getDataSource();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals(1, actionList.size());
    DBEPersistAction getResult = actionList.get(0);
    assertTrue(getResult instanceof SQLDatabasePersistAction);
    assertEquals("COMMENT ON COLUMN \"Name\".\"Name\".\"Name\" IS ''", getResult.getScript());
    assertEquals("Comment column", getResult.getTitle());
    assertEquals(ActionType.NORMAL, getResult.getType());
    assertFalse(getResult.isComplex());
  }

  /**
   * Test {@link SQLTableColumnManager#addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)}.
   *
   * <p>Method under test: {@link SQLTableColumnManager#addColumnCommentAction(List,
   * DBSEntityAttribute, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLTableColumnManager.addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)"
  })
  public void testAddColumnCommentAction2() {
    // Arrange
    ArrayList<DBEPersistAction> actionList = new ArrayList<>();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");
    when(sqlDialect.getQuotedString(Mockito.<String>any())).thenReturn("Quoted String");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute column =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container2 = new DBVContainer(parent2, "Name");
    DBVEntity table = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    SQLTableColumnManager.addColumnCommentAction(actionList, column, table);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dbpDataSource2, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getQuotedString("");
    verify(parent2).getDataSource();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals(1, actionList.size());
    DBEPersistAction getResult = actionList.get(0);
    assertTrue(getResult instanceof SQLDatabasePersistAction);
    assertEquals("COMMENT ON COLUMN \"Name\".\"Name\".42 IS Quoted String", getResult.getScript());
    assertEquals("Comment column", getResult.getTitle());
    assertEquals(ActionType.NORMAL, getResult.getType());
    assertFalse(getResult.isComplex());
  }

  /**
   * Test {@link SQLTableColumnManager#addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)}.
   *
   * <p>Method under test: {@link SQLTableColumnManager#addColumnCommentAction(List,
   * DBSEntityAttribute, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLTableColumnManager.addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)"
  })
  public void testAddColumnCommentAction3() {
    // Arrange
    ArrayList<DBEPersistAction> actionList = new ArrayList<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Comment column");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute column =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container2 = new DBVContainer(parent2, "");
    DBVEntity table = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    SQLTableColumnManager.addColumnCommentAction(actionList, column, table);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dbpDataSource2, atLeast(1)).getSQLDialect();
    verify(parent2).getDataSource();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals(1, actionList.size());
    DBEPersistAction getResult = actionList.get(0);
    assertTrue(getResult instanceof SQLDatabasePersistAction);
    assertEquals("COMMENT ON COLUMN \"Name\".\"Name\" IS ''", getResult.getScript());
    assertEquals("Comment column", getResult.getTitle());
    assertEquals(ActionType.NORMAL, getResult.getType());
    assertFalse(getResult.isComplex());
  }

  /**
   * Test {@link SQLTableColumnManager#addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)}.
   *
   * <p>Method under test: {@link SQLTableColumnManager#addColumnCommentAction(List,
   * DBSEntityAttribute, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLTableColumnManager.addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)"
  })
  public void testAddColumnCommentAction4() {
    // Arrange
    ArrayList<DBEPersistAction> actionList = new ArrayList<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute column =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    column.setDescription("The characteristics of someone or something");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container2 = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntity table = new DBVEntity(container2, copy, targetModel);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    table.addVirtualAttribute(attribute);

    // Act
    SQLTableColumnManager.addColumnCommentAction(actionList, column, table);

    // Assert
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dbpDataSource2, atLeast(1)).getSQLDialect();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals(1, actionList.size());
    DBEPersistAction getResult = actionList.get(0);
    assertTrue(getResult instanceof SQLDatabasePersistAction);
    assertEquals(
        "COMMENT ON COLUMN \"Name\".\"Name\".\"Name\" IS 'The characteristics of someone or something'",
        getResult.getScript());
    assertEquals("Comment column", getResult.getTitle());
    assertEquals(ActionType.NORMAL, getResult.getType());
    assertFalse(getResult.isComplex());
  }

  /**
   * Test {@link SQLTableColumnManager#addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)}.
   *
   * <ul>
   *   <li>Then {@link ArrayList#ArrayList()} first Script is {@code COMMENT ON COLUMN "Name"."Name"
   *       IS ''}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableColumnManager#addColumnCommentAction(List,
   * DBSEntityAttribute, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLTableColumnManager.addColumnCommentAction(List, DBSEntityAttribute, DBSEntity)"
  })
  public void testAddColumnCommentAction_thenArrayListFirstScriptIsCommentOnColumnNameNameIs() {
    // Arrange
    ArrayList<DBEPersistAction> actionList = new ArrayList<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Comment column");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute column =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource2);
    DBVModel container2 = new DBVModel(dataSourceContainer);
    DBVEntity table = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    SQLTableColumnManager.addColumnCommentAction(actionList, column, table);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dbpDataSource2, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals(1, actionList.size());
    DBEPersistAction getResult = actionList.get(0);
    assertTrue(getResult instanceof SQLDatabasePersistAction);
    assertEquals("COMMENT ON COLUMN \"Name\".\"Name\" IS ''", getResult.getScript());
    assertEquals("Comment column", getResult.getTitle());
    assertEquals(ActionType.NORMAL, getResult.getType());
    assertFalse(getResult.isComplex());
  }
}
