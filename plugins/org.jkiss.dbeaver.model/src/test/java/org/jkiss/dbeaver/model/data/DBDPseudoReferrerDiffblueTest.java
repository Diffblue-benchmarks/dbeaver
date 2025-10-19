package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
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
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSEntityAttributeRef;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDPseudoReferrerDiffblueTest {
  /**
   * Test {@link DBDPseudoReferrer#getAttributeReferences(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDPseudoReferrer#getAttributeReferences(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBDPseudoReferrer.getAttributeReferences(DBRProgressMonitor)"})
  public void testGetAttributeReferences_thenReturnSizeIsOne() throws DBException {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBDPseudoReferrer dbdPseudoReferrer = new DBDPseudoReferrer(entity, null);

    // Act
    List<? extends DBSEntityAttributeRef> actualAttributeReferences =
        dbdPseudoReferrer.getAttributeReferences(new LoggingProgressMonitor());

    // Assert
    assertEquals(1, actualAttributeReferences.size());
    assertSame(dbdPseudoReferrer, actualAttributeReferences.get(0));
  }

  /**
   * Test {@link DBDPseudoReferrer#getDescription()}.
   *
   * <p>Method under test: {@link DBDPseudoReferrer#getDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDPseudoReferrer.getDescription()"})
  public void testGetDescription() {
    // Arrange
    DBDAttributeBindingCustom binding = mock(DBDAttributeBindingCustom.class);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, null, "Name");
    when(binding.getEntityAttribute()).thenReturn(dbvEntityAttribute);
    DBVEntity entity2 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    DBDPseudoReferrer dbdPseudoReferrer = new DBDPseudoReferrer(entity2, binding);

    // Act
    String actualDescription = dbdPseudoReferrer.getDescription();

    // Assert
    verify(binding).getEntityAttribute();
    assertNull(actualDescription);
  }

  /**
   * Test {@link DBDPseudoReferrer#getDescription()}.
   *
   * <ul>
   *   <li>Given {@link DBDAttributeBindingCustom} {@link
   *       DBDAttributeBindingCustom#getEntityAttribute()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDPseudoReferrer#getDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDPseudoReferrer.getDescription()"})
  public void testGetDescription_givenDBDAttributeBindingCustomGetEntityAttributeReturnNull() {
    // Arrange
    DBDAttributeBindingCustom binding = mock(DBDAttributeBindingCustom.class);
    when(binding.getEntityAttribute()).thenReturn(null);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    DBDPseudoReferrer dbdPseudoReferrer = new DBDPseudoReferrer(entity, binding);

    // Act
    String actualDescription = dbdPseudoReferrer.getDescription();

    // Assert
    verify(binding).getEntityAttribute();
    assertNull(actualDescription);
  }

  /**
   * Test {@link DBDPseudoReferrer#getParentObject()}.
   *
   * <p>Method under test: {@link DBDPseudoReferrer#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSEntity DBDPseudoReferrer.getParentObject()"
  })
  public void testGetParentObject() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBDPseudoReferrer dbdPseudoReferrer = new DBDPseudoReferrer(entity, null);

    // Act and Assert
    assertSame(entity, dbdPseudoReferrer.getParentObject());
  }

  /**
   * Test {@link DBDPseudoReferrer#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBDPseudoReferrer#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBDPseudoReferrer.getDataSource()"})
  public void testGetDataSource_thenCallsGetDataSource() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBDPseudoReferrer dbdPseudoReferrer = new DBDPseudoReferrer(entity, null);

    // Act
    dbdPseudoReferrer.getDataSource();

    // Assert
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBDPseudoReferrer#getName()}.
   *
   * <ul>
   *   <li>Then return {@code PSEUDO}.
   * </ul>
   *
   * <p>Method under test: {@link DBDPseudoReferrer#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDPseudoReferrer.getName()"})
  public void testGetName_thenReturnPseudo() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBDPseudoReferrer dbdPseudoReferrer = new DBDPseudoReferrer(entity, null);

    // Act and Assert
    assertEquals("PSEUDO", dbdPseudoReferrer.getName());
  }

  /**
   * Test {@link DBDPseudoReferrer#getAttribute()}.
   *
   * <p>Method under test: {@link DBDPseudoReferrer#getAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBDPseudoReferrer.getAttribute()"})
  public void testGetAttribute() {
    // Arrange
    DBDAttributeBindingCustom binding = mock(DBDAttributeBindingCustom.class);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, null, "Name");
    when(binding.getEntityAttribute()).thenReturn(dbvEntityAttribute);
    DBVEntity entity2 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    DBDPseudoReferrer dbdPseudoReferrer = new DBDPseudoReferrer(entity2, binding);

    // Act
    DBSEntityAttribute actualAttribute = dbdPseudoReferrer.getAttribute();

    // Assert
    verify(binding).getEntityAttribute();
    assertSame(dbvEntityAttribute, actualAttribute);
  }
}
