package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBVEntityConstraintColumnDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBVEntityConstraintColumn#DBVEntityConstraintColumn(DBVEntityConstraint, String)}
   *   <li>{@link DBVEntityConstraintColumn#getAttributeName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityConstraintColumn.<init>(DBVEntityConstraint, String)",
    "String DBVEntityConstraintColumn.getAttributeName()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act and Assert
    assertEquals(
        "Attribute Name",
        new DBVEntityConstraintColumn(constraint, "Attribute Name").getAttributeName());
  }

  /**
   * Test {@link DBVEntityConstraintColumn#DBVEntityConstraintColumn(DBVEntityConstraint,
   * DBVEntityConstraintColumn)}.
   *
   * <ul>
   *   <li>Then return {@code Attribute Name}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBVEntityConstraintColumn#DBVEntityConstraintColumn(DBVEntityConstraint,
   * DBVEntityConstraintColumn)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityConstraintColumn.<init>(DBVEntityConstraint, DBVEntityConstraintColumn)"
  })
  public void testNewDBVEntityConstraintColumn_thenReturnAttributeName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy2 =
        new DBVEntityConstraint(mock(DBVEntity.class), DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint2 = new DBVEntityConstraint(entity3, copy2);

    // Act
    DBVEntityConstraintColumn actualDbvEntityConstraintColumn =
        new DBVEntityConstraintColumn(
            constraint, new DBVEntityConstraintColumn(constraint2, "Attribute Name"));

    // Assert
    assertEquals("Attribute Name", actualDbvEntityConstraintColumn.getAttributeName());
    assertNull(actualDbvEntityConstraintColumn.getAttribute());
  }

  /**
   * Test {@link DBVEntityConstraintColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityConstraintColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityConstraintColumn.getAttribute()"})
  public void testGetAttribute() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent3, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(mock(DBVEntity.class), DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    DBSEntityAttribute actualAttribute =
        new DBVEntityConstraintColumn(constraint, "Attribute Name").getAttribute();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityConstraintColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityConstraintColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityConstraintColumn.getAttribute()"})
  public void testGetAttribute2() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer parent4 = new DBVContainer(parent3, "org.jkiss.dbeaver.model");
    DBVContainer container = new DBVContainer(parent4, "org.jkiss.dbeaver.model", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(mock(DBVEntity.class), DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    DBSEntityAttribute actualAttribute =
        new DBVEntityConstraintColumn(constraint, "Attribute Name").getAttribute();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityConstraintColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityConstraintColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityConstraintColumn.getAttribute()"})
  public void testGetAttribute3() {
    // Arrange
    DBVContainer container = new DBVContainer(null, "org.jkiss.dbeaver.model", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(mock(DBVEntity.class), DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act and Assert
    assertNull(new DBVEntityConstraintColumn(constraint, "Attribute Name").getAttribute());
  }

  /**
   * Test {@link DBVEntityConstraintColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityConstraintColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityConstraintColumn.getAttribute()"})
  public void testGetAttribute4() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(mock(DBVEntity.class), DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    DBSEntityAttribute actualAttribute =
        new DBVEntityConstraintColumn(constraint, "Attribute Name").getAttribute();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityConstraintColumn#getAttribute()}.
   *
   * <p>Method under test: {@link DBVEntityConstraintColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityConstraintColumn.getAttribute()"})
  public void testGetAttribute5() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(mock(DBVEntity.class), DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    DBSEntityAttribute actualAttribute =
        new DBVEntityConstraintColumn(constraint, "Attribute Name").getAttribute();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityConstraintColumn#getAttribute()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityConstraintColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityConstraintColumn.getAttribute()"})
  public void testGetAttribute_givenDBVContainerGetRealContainerReturnNull() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(mock(DBVEntity.class), DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    DBSEntityAttribute actualAttribute =
        new DBVEntityConstraintColumn(constraint, "Attribute Name").getAttribute();

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntityConstraintColumn#getAttribute()}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityConstraintColumn#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntityConstraintColumn.getAttribute()"})
  public void testGetAttribute_givenDBVModelWithIdIs42AndMapIsHashMap_thenReturnNull() {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(mock(DBVEntity.class), DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act and Assert
    assertNull(new DBVEntityConstraintColumn(constraint, "Attribute Name").getAttribute());
  }
}
