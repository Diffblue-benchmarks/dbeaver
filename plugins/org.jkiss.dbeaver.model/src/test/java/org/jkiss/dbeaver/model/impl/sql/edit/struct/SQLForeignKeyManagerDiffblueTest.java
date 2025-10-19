package org.jkiss.dbeaver.model.impl.sql.edit.struct;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraint;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLForeignKeyManagerDiffblueTest {
  /**
   * Test {@link SQLForeignKeyManager#generateConstraintName(DBSEntity, DBSEntityConstraint)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then return {@code Name_Name_FK}.
   * </ul>
   *
   * <p>Method under test: {@link SQLForeignKeyManager#generateConstraintName(DBSEntity,
   * DBSEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLForeignKeyManager.generateConstraintName(DBSEntity, DBSEntityConstraint)"
  })
  public void testGenerateConstraintName_givenName_thenReturnNameNameFk() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "Name", "Description Column Names");

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getName()).thenReturn("Name");

    // Act
    String actualGenerateConstraintNameResult =
        SQLForeignKeyManager.generateConstraintName(table, new DBSDocumentConstraint(entity));

    // Assert
    verify(entity).getName();
    assertEquals("Name_Name_FK", actualGenerateConstraintNameResult);
  }

  /**
   * Test {@link SQLForeignKeyManager#generateConstraintName(DBSEntity, DBSEntityConstraint)}.
   *
   * <ul>
   *   <li>Then return {@code java_lang_String_Name_FK}.
   * </ul>
   *
   * <p>Method under test: {@link SQLForeignKeyManager#generateConstraintName(DBSEntity,
   * DBSEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLForeignKeyManager.generateConstraintName(DBSEntity, DBSEntityConstraint)"
  })
  public void testGenerateConstraintName_thenReturnJavaLangStringNameFk() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "java.lang.String", "Description Column Names");

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getName()).thenReturn("Name");

    // Act
    String actualGenerateConstraintNameResult =
        SQLForeignKeyManager.generateConstraintName(table, new DBSDocumentConstraint(entity));

    // Assert
    verify(entity).getName();
    assertEquals("java_lang_String_Name_FK", actualGenerateConstraintNameResult);
  }

  /**
   * Test {@link SQLForeignKeyManager#generateConstraintName(DBSEntity, DBSEntityConstraint)}.
   *
   * <ul>
   *   <li>Then return {@code __Name_FK}.
   * </ul>
   *
   * <p>Method under test: {@link SQLForeignKeyManager#generateConstraintName(DBSEntity,
   * DBSEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLForeignKeyManager.generateConstraintName(DBSEntity, DBSEntityConstraint)"
  })
  public void testGenerateConstraintName_thenReturnNameFk() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, ",", "Description Column Names");

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getName()).thenReturn("Name");

    // Act
    String actualGenerateConstraintNameResult =
        SQLForeignKeyManager.generateConstraintName(table, new DBSDocumentConstraint(entity));

    // Assert
    verify(entity).getName();
    assertEquals("__Name_FK", actualGenerateConstraintNameResult);
  }

  /**
   * Test {@link SQLForeignKeyManager#generateConstraintName(DBSEntity, DBSEntityConstraint)}.
   *
   * <ul>
   *   <li>Then return {@code null_FK}.
   * </ul>
   *
   * <p>Method under test: {@link SQLForeignKeyManager#generateConstraintName(DBSEntity,
   * DBSEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLForeignKeyManager.generateConstraintName(DBSEntity, DBSEntityConstraint)"
  })
  public void testGenerateConstraintName_thenReturnNullFk() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, null, "Description Column Names");

    // Act and Assert
    assertEquals("null_FK", SQLForeignKeyManager.generateConstraintName(table, null));
  }

  /**
   * Test {@link SQLForeignKeyManager#generateConstraintName(DBSEntity, DBSEntityConstraint)}.
   *
   * <ul>
   *   <li>When {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLForeignKeyManager#generateConstraintName(DBSEntity,
   * DBSEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLForeignKeyManager.generateConstraintName(DBSEntity, DBSEntityConstraint)"
  })
  public void testGenerateConstraintName_whenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    String actualGenerateConstraintNameResult =
        SQLForeignKeyManager.generateConstraintName(table, new DBVEntityForeignKey(entity));

    // Assert
    assertEquals("Name_Name_FK", actualGenerateConstraintNameResult);
  }

  /**
   * Test {@link SQLForeignKeyManager#generateConstraintName(DBSEntity, DBSEntityConstraint)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code Name_FK}.
   * </ul>
   *
   * <p>Method under test: {@link SQLForeignKeyManager#generateConstraintName(DBSEntity,
   * DBSEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLForeignKeyManager.generateConstraintName(DBSEntity, DBSEntityConstraint)"
  })
  public void testGenerateConstraintName_whenNull_thenReturnNameFk() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertEquals("Name_FK", SQLForeignKeyManager.generateConstraintName(table, null));
  }
}
