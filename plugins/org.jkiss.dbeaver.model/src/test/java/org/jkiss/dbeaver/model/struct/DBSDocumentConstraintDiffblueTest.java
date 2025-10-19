package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBSDocumentConstraintDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSDocumentConstraint#DBSDocumentConstraint(DBSDocumentContainer)}
   *   <li>{@link DBSDocumentConstraint#getConstraintType()}
   *   <li>{@link DBSDocumentConstraint#getDescription()}
   *   <li>{@link DBSDocumentConstraint#getName()}
   *   <li>{@link DBSDocumentConstraint#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSDocumentConstraint.<init>(DBSDocumentContainer)",
    "DBSEntityConstraintType DBSDocumentConstraint.getConstraintType()",
    "String DBSDocumentConstraint.getDescription()",
    "String DBSDocumentConstraint.getName()",
    "boolean DBSDocumentConstraint.isPersisted()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);

    // Act
    DBSDocumentConstraint actualDbsDocumentConstraint = new DBSDocumentConstraint(entity);
    DBSEntityConstraintType actualConstraintType = actualDbsDocumentConstraint.getConstraintType();
    String actualDescription = actualDbsDocumentConstraint.getDescription();
    String actualName = actualDbsDocumentConstraint.getName();

    // Assert
    assertEquals("DocumentKey", actualName);
    assertNull(actualDescription);
    assertFalse(actualDbsDocumentConstraint.isPersisted());
    assertSame(DBSEntityConstraintType.PRIMARY_KEY, actualConstraintType);
    assertSame(entity, actualDbsDocumentConstraint.getParentObject());
  }

  /**
   * Test {@link DBSDocumentConstraint#getDataSource()}.
   *
   * <p>Method under test: {@link DBSDocumentConstraint#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBSDocumentConstraint.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    new DBSDocumentConstraint(entity).getDataSource();

    // Assert
    verify(entity).getDataSource();
  }

  /**
   * Test {@link DBSDocumentConstraint#getAttributeReferences(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBSDocumentConstraint#getAttributeReferences(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBSDocumentConstraint.getAttributeReferences(DBRProgressMonitor)"})
  public void testGetAttributeReferences_thenReturnSizeIsOne() throws DBException {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityAttribute);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    // Act
    List<? extends DBSEntityAttributeRef> actualAttributeReferences =
        dbsDocumentConstraint.getAttributeReferences(new LoggingProgressMonitor());

    // Assert
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertEquals(1, actualAttributeReferences.size());
  }

  /**
   * Test {@link DBSDocumentConstraint#getAttributeReferences(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBSDocumentConstraint#getAttributeReferences(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBSDocumentConstraint.getAttributeReferences(DBRProgressMonitor)"})
  public void testGetAttributeReferences_thenThrowDBException() throws DBException {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dbsDocumentConstraint.getAttributeReferences(new LoggingProgressMonitor()));
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
  }
}
