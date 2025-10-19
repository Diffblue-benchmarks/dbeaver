package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.navigator.DBNModel;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityAssociation;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ERDEntityDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDEntity#ERDEntity(DBSEntity)}
   *   <li>{@link ERDEntity#setAlias(String)}
   *   <li>{@link ERDEntity#setAttributeVisibility(ERDAttributeVisibility)}
   *   <li>{@link ERDEntity#setPrimary(boolean)}
   *   <li>{@link ERDEntity#getAlias()}
   *   <li>{@link ERDEntity#getAttributeVisibility()}
   *   <li>{@link ERDEntity#isPrimary()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDEntity.<init>(DBPDataSource)",
    "void ERDEntity.<init>(DBSEntity)",
    "String ERDEntity.getAlias()",
    "ERDAttributeVisibility ERDEntity.getAttributeVisibility()",
    "boolean ERDEntity.isPrimary()",
    "void ERDEntity.setAlias(String)",
    "void ERDEntity.setAttributeVisibility(ERDAttributeVisibility)",
    "void ERDEntity.setPrimary(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    // Act
    ERDEntity actualErdEntity = new ERDEntity(entity);
    actualErdEntity.setAlias("Alias");
    actualErdEntity.setAttributeVisibility(ERDAttributeVisibility.ALL);
    actualErdEntity.setPrimary(true);
    String actualAlias = actualErdEntity.getAlias();
    ERDAttributeVisibility actualAttributeVisibility = actualErdEntity.getAttributeVisibility();
    boolean actualIsPrimaryResult = actualErdEntity.isPrimary();

    // Assert
    assertEquals("Alias", actualAlias);
    assertNull(actualErdEntity.getUserData());
    assertEquals(ERDAttributeVisibility.ALL, actualAttributeVisibility);
    assertTrue(actualIsPrimaryResult);
    assertSame(entity, actualErdEntity.getObject());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link DBPDataSource}.
   *   <li>Then return Object is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDEntity#ERDEntity(DBPDataSource)}
   *   <li>{@link ERDEntity#setAlias(String)}
   *   <li>{@link ERDEntity#setAttributeVisibility(ERDAttributeVisibility)}
   *   <li>{@link ERDEntity#setPrimary(boolean)}
   *   <li>{@link ERDEntity#getAlias()}
   *   <li>{@link ERDEntity#getAttributeVisibility()}
   *   <li>{@link ERDEntity#isPrimary()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDEntity.<init>(DBPDataSource)",
    "void ERDEntity.<init>(DBSEntity)",
    "String ERDEntity.getAlias()",
    "ERDAttributeVisibility ERDEntity.getAttributeVisibility()",
    "boolean ERDEntity.isPrimary()",
    "void ERDEntity.setAlias(String)",
    "void ERDEntity.setAttributeVisibility(ERDAttributeVisibility)",
    "void ERDEntity.setPrimary(boolean)"
  })
  public void testGettersAndSetters_whenDBPDataSource_thenReturnObjectIsNull() {
    // Arrange and Act
    ERDEntity actualErdEntity = new ERDEntity(mock(DBPDataSource.class));
    actualErdEntity.setAlias("Alias");
    actualErdEntity.setAttributeVisibility(ERDAttributeVisibility.ALL);
    actualErdEntity.setPrimary(true);
    String actualAlias = actualErdEntity.getAlias();
    ERDAttributeVisibility actualAttributeVisibility = actualErdEntity.getAttributeVisibility();
    boolean actualIsPrimaryResult = actualErdEntity.isPrimary();

    // Assert
    assertEquals("Alias", actualAlias);
    assertNull(actualErdEntity.getUserData());
    assertNull(actualErdEntity.getObject());
    assertEquals(ERDAttributeVisibility.ALL, actualAttributeVisibility);
    assertTrue(actualIsPrimaryResult);
  }

  /**
   * Test {@link ERDEntity#getDataSource()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getDataSource()} return {@link
   *       DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource ERDEntity.getDataSource()"})
  public void testGetDataSource_givenDBVContainerGetDataSourceReturnDBPDataSource() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ERDEntity erdEntity = new ERDEntity((DBPDataSource) null);
    erdEntity.setObject(dbvEntity);

    // Act
    erdEntity.getDataSource();

    // Assert
    verify(parent).getDataSource();
  }

  /**
   * Test {@link ERDEntity#getDataSource()}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource ERDEntity.getDataSource()"})
  public void testGetDataSource_givenDBVModelWithIdIs42AndMapIsHashMap_thenReturnNull() {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertNull(new ERDEntity(entity).getDataSource());
  }

  /**
   * Test {@link ERDEntity#getDataSource()}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link
   *       DBPDataSource}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource ERDEntity.getDataSource()"})
  public void testGetDataSource_givenERDEntityWithDataSourceIsDBPDataSource_thenDoesNotThrow() {
    // Arrange and Act
    new ERDEntity(mock(DBPDataSource.class)).getDataSource();

    // Assert
  }

  /**
   * Test {@link ERDEntity#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource ERDEntity.getDataSource()"})
  public void testGetDataSource_thenCallsGetDataSource() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    ERDEntity erdEntity = new ERDEntity(entity);
    erdEntity.setObject(dbvEntity);

    // Act
    erdEntity.getDataSource();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDEntity#getDataSource()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource ERDEntity.getDataSource()"})
  public void testGetDataSource_thenThrowIllegalArgumentException() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalArgumentException());
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    ERDEntity erdEntity = new ERDEntity((DBPDataSource) null);
    erdEntity.setObject(dbvEntity);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> erdEntity.getDataSource());
    verify(parent).getDataSource();
  }

  /**
   * Test {@link ERDEntity#addAttribute(ERDEntityAttribute, boolean)}.
   *
   * <ul>
   *   <li>Then {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link DBPDataSource}
   *       Attributes size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#addAttribute(ERDEntityAttribute, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.addAttribute(ERDEntityAttribute, boolean)"})
  public void testAddAttribute_thenERDEntityWithDataSourceIsDBPDataSourceAttributesSizeIsOne() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute attribute2 = new ERDEntityAttribute(attribute, true);

    // Act
    erdEntity.addAttribute(attribute2, true);

    // Assert
    List<ERDEntityAttribute> attributes = erdEntity.getAttributes();
    assertEquals(1, attributes.size());
    assertSame(attribute2, attributes.get(0));
  }

  /**
   * Test {@link ERDEntity#addAttribute(ERDEntityAttribute, boolean)}.
   *
   * <ul>
   *   <li>Then {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link DBPDataSource}
   *       Attributes size is two.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#addAttribute(ERDEntityAttribute, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.addAttribute(ERDEntityAttribute, boolean)"})
  public void testAddAttribute_thenERDEntityWithDataSourceIsDBPDataSourceAttributesSizeIsTwo() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute attribute2 = new ERDEntityAttribute(attribute, true);
    erdEntity.addAttribute(attribute2, true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute3 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute attribute4 = new ERDEntityAttribute(attribute3, true);

    // Act
    erdEntity.addAttribute(attribute4, true);

    // Assert
    List<ERDEntityAttribute> attributes = erdEntity.getAttributes();
    assertEquals(2, attributes.size());
    assertSame(attribute2, attributes.get(0));
    assertSame(attribute4, attributes.get(1));
  }

  /**
   * Test {@link ERDEntity#addAttribute(ERDEntityAttribute, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#addAttribute(ERDEntityAttribute, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.addAttribute(ERDEntityAttribute, boolean)"})
  public void testAddAttribute_whenFalse() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute attribute2 = new ERDEntityAttribute(attribute, true);

    // Act
    erdEntity.addAttribute(attribute2, false);

    // Assert
    List<ERDEntityAttribute> attributes = erdEntity.getAttributes();
    assertEquals(1, attributes.size());
    assertSame(attribute2, attributes.get(0));
  }

  /**
   * Test {@link ERDEntity#switchAttribute(ERDEntityAttribute, int, boolean)}.
   *
   * <p>Method under test: {@link ERDEntity#switchAttribute(ERDEntityAttribute, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.switchAttribute(ERDEntityAttribute, int, boolean)"})
  public void testSwitchAttribute() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    erdEntity.addAttribute(new ERDEntityAttribute(attribute, true), true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute attribute3 = new ERDEntityAttribute(attribute2, true);

    // Act
    erdEntity.switchAttribute(attribute3, 1, true);

    // Assert
    List<ERDEntityAttribute> attributes = erdEntity.getAttributes();
    assertEquals(2, attributes.size());
    assertSame(attribute3, attributes.get(1));
  }

  /**
   * Test {@link ERDEntity#switchAttribute(ERDEntityAttribute, int, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link PropertyChangeListener#propertyChange(PropertyChangeEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#switchAttribute(ERDEntityAttribute, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.switchAttribute(ERDEntityAttribute, int, boolean)"})
  public void testSwitchAttribute_thenCallsPropertyChange() {
    // Arrange
    PropertyChangeListener l = mock(PropertyChangeListener.class);
    doNothing().when(l).propertyChange(Mockito.<PropertyChangeEvent>any());

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.addPropertyChangeListener(l);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    erdEntity.addAttribute(new ERDEntityAttribute(attribute, true), true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute attribute3 = new ERDEntityAttribute(attribute2, true);

    // Act
    erdEntity.switchAttribute(attribute3, 1, true);

    // Assert
    verify(l, atLeast(1)).propertyChange(Mockito.<PropertyChangeEvent>any());
    List<ERDEntityAttribute> attributes = erdEntity.getAttributes();
    assertEquals(2, attributes.size());
    assertSame(attribute3, attributes.get(1));
  }

  /**
   * Test {@link ERDEntity#switchAttribute(ERDEntityAttribute, int, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then calls {@link PropertyChangeListener#propertyChange(PropertyChangeEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#switchAttribute(ERDEntityAttribute, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.switchAttribute(ERDEntityAttribute, int, boolean)"})
  public void testSwitchAttribute_whenFalse_thenCallsPropertyChange() {
    // Arrange
    PropertyChangeListener l = mock(PropertyChangeListener.class);
    doNothing().when(l).propertyChange(Mockito.<PropertyChangeEvent>any());

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.addPropertyChangeListener(l);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    erdEntity.addAttribute(new ERDEntityAttribute(attribute, true), true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute attribute3 = new ERDEntityAttribute(attribute2, true);

    // Act
    erdEntity.switchAttribute(attribute3, 1, false);

    // Assert
    verify(l).propertyChange(isA(PropertyChangeEvent.class));
    List<ERDEntityAttribute> attributes = erdEntity.getAttributes();
    assertEquals(2, attributes.size());
    assertSame(attribute3, attributes.get(1));
  }

  /**
   * Test {@link ERDEntity#getAttributes()}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link
   *       DBPDataSource}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDEntity.getAttributes()"})
  public void testGetAttributes_givenERDEntityWithDataSourceIsDBPDataSource_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new ERDEntity(mock(DBPDataSource.class)).getAttributes().isEmpty());
  }

  /**
   * Test {@link ERDEntity#getAttributes()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDEntity.getAttributes()"})
  public void testGetAttributes_thenReturnSizeIsOne() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute attribute2 = new ERDEntityAttribute(attribute, true);
    erdEntity.addAttribute(attribute2, true);

    // Act
    List<ERDEntityAttribute> actualAttributes = erdEntity.getAttributes();

    // Assert
    assertEquals(1, actualAttributes.size());
    assertSame(attribute2, actualAttributes.get(0));
  }

  /**
   * Test {@link ERDEntity#getAttribute(DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getAttribute(DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ERDEntityAttribute ERDEntity.getAttribute(DBSEntityAttribute)"})
  public void testGetAttribute_givenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    erdEntity.addAttribute(new ERDEntityAttribute(attribute, true), true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    // Act
    ERDEntityAttribute actualAttribute = erdEntity.getAttribute(attribute2);

    // Assert
    assertNull(actualAttribute);
  }

  /**
   * Test {@link ERDEntity#getAttribute(DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link
   *       DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getAttribute(DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ERDEntityAttribute ERDEntity.getAttribute(DBSEntityAttribute)"})
  public void testGetAttribute_givenERDEntityWithDataSourceIsDBPDataSource() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    ERDEntityAttribute actualAttribute = erdEntity.getAttribute(attribute);

    // Assert
    assertNull(actualAttribute);
  }

  /**
   * Test {@link ERDEntity#getAttribute(DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link
   *       DBPDataSource}.
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getAttribute(DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ERDEntityAttribute ERDEntity.getAttribute(DBSEntityAttribute)"})
  public void testGetAttribute_givenERDEntityWithDataSourceIsDBPDataSource_whenNull() {
    // Arrange, Act and Assert
    assertNull(new ERDEntity(mock(DBPDataSource.class)).getAttribute(null));
  }

  /**
   * Test {@link ERDEntity#getAssociation(DBSEntityAssociation)}.
   *
   * <ul>
   *   <li>When {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getAssociation(DBSEntityAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ERDAssociation ERDEntity.getAssociation(DBSEntityAssociation)"})
  public void testGetAssociation_whenERDEntityWithDataSourceIsDBPDataSource() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);

    // Act
    ERDAssociation actualAssociation = erdEntity.getAssociation(association);

    // Assert
    assertNull(actualAssociation);
  }

  /**
   * Test {@link ERDEntity#getAssociation(DBSEntityAssociation)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getAssociation(DBSEntityAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ERDAssociation ERDEntity.getAssociation(DBSEntityAssociation)"})
  public void testGetAssociation_whenNull() {
    // Arrange, Act and Assert
    assertNull(new ERDEntity(mock(DBPDataSource.class)).getAssociation(null));
  }

  /**
   * Test {@link ERDEntity#getReferenceAssociation(DBSEntityAssociation)}.
   *
   * <ul>
   *   <li>When {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getReferenceAssociation(DBSEntityAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ERDAssociation ERDEntity.getReferenceAssociation(DBSEntityAssociation)"})
  public void testGetReferenceAssociation_whenERDEntityWithDataSourceIsDBPDataSource() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);

    // Act
    ERDAssociation actualReferenceAssociation = erdEntity.getReferenceAssociation(association);

    // Assert
    assertNull(actualReferenceAssociation);
  }

  /**
   * Test {@link ERDEntity#getReferenceAssociation(DBSEntityAssociation)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getReferenceAssociation(DBSEntityAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ERDAssociation ERDEntity.getReferenceAssociation(DBSEntityAssociation)"})
  public void testGetReferenceAssociation_whenNull() {
    // Arrange, Act and Assert
    assertNull(new ERDEntity(mock(DBPDataSource.class)).getReferenceAssociation(null));
  }

  /**
   * Test {@link ERDEntity#getCheckedAttributes()}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link
   *       DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getCheckedAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDEntity.getCheckedAttributes()"})
  public void testGetCheckedAttributes_givenERDEntityWithDataSourceIsDBPDataSource() {
    // Arrange, Act and Assert
    assertTrue(new ERDEntity(mock(DBPDataSource.class)).getCheckedAttributes().isEmpty());
  }

  /**
   * Test {@link ERDEntity#getCheckedAttributes()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getCheckedAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDEntity.getCheckedAttributes()"})
  public void testGetCheckedAttributes_thenReturnEmpty() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    erdEntity.addAttribute(new ERDEntityAttribute(attribute, true), true);

    // Act and Assert
    assertTrue(erdEntity.getCheckedAttributes().isEmpty());
  }

  /**
   * Test {@link ERDEntity#getCheckedAttributes()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getCheckedAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDEntity.getCheckedAttributes()"})
  public void testGetCheckedAttributes_thenReturnSizeIsOne() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    ERDEntityAttribute attribute2 = new ERDEntityAttribute(attribute, true);
    attribute2.setChecked(true);

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.addAttribute(attribute2, true);

    // Act
    List<ERDEntityAttribute> actualCheckedAttributes = erdEntity.getCheckedAttributes();

    // Assert
    assertEquals(1, actualCheckedAttributes.size());
    assertSame(attribute2, actualCheckedAttributes.get(0));
  }

  /**
   * Test {@link ERDEntity#reloadAttributes(ERDDiagram)}.
   *
   * <p>Method under test: {@link ERDEntity#reloadAttributes(ERDDiagram)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.reloadAttributes(ERDDiagram)"})
  public void testReloadAttributes() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBCException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity);
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container2 =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container2, "Name", new ERDContentProviderDefault());

    // Act
    erdEntity.reloadAttributes(diagram);

    // Assert
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDEntity#reloadAttributes(ERDDiagram)}.
   *
   * <p>Method under test: {@link ERDEntity#reloadAttributes(ERDDiagram)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.reloadAttributes(ERDDiagram)"})
  public void testReloadAttributes2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity);
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container2 =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container2, "Name", new ERDContentProviderDefault());

    // Act
    erdEntity.reloadAttributes(diagram);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDEntity#reloadAttributes(ERDDiagram)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#reloadAttributes(ERDDiagram)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.reloadAttributes(ERDDiagram)"})
  public void testReloadAttributes_givenDBVContainerGetRealContainerReturnNull()
      throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity);
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container2 =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container2, "Name", new ERDContentProviderDefault());

    // Act
    erdEntity.reloadAttributes(diagram);

    // Assert
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDEntity#reloadAttributes(ERDDiagram)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#reloadAttributes(ERDDiagram)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.reloadAttributes(ERDDiagram)"})
  public void testReloadAttributes_givenDBVModelWithIdIs42AndMapIsHashMap_thenDoesNotThrow() {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity);
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container2 =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container2, "Name", new ERDContentProviderDefault());

    // Act and Assert
    erdEntity.reloadAttributes(diagram);
  }

  /**
   * Test {@link ERDEntity#reloadAttributes(ERDDiagram)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#reloadAttributes(ERDDiagram)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.reloadAttributes(ERDDiagram)"})
  public void testReloadAttributes_thenCallsGetContainer() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container = new DBVModel(dataSourceContainer, source);

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    ERDEntity erdEntity = new ERDEntity(entity);
    ERDEntity entity3 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container3 =
        new ERDLogicalPrimaryKey(entity3, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container3, "Name", new ERDContentProviderDefault());

    // Act
    erdEntity.reloadAttributes(diagram);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link ERDEntity#reloadAttributes(ERDDiagram)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#reloadAttributes(ERDDiagram)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.reloadAttributes(ERDDiagram)"})
  public void testReloadAttributes_thenThrowIllegalArgumentException() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new IllegalArgumentException());
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity);
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container2 =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container2, "Name", new ERDContentProviderDefault());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> erdEntity.reloadAttributes(diagram));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDEntity#addModelRelations(DBRProgressMonitor, ERDContainer, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBSEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#addModelRelations(DBRProgressMonitor, ERDContainer,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDEntity.addModelRelations(DBRProgressMonitor, ERDContainer, boolean, boolean)"
  })
  public void testAddModelRelations_givenERDEntityWithEntityIsDBVEntity_thenDoesNotThrow()
      throws DBException {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container2 =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container2, "Name", new ERDContentProviderDefault());

    // Act and Assert
    erdEntity.addModelRelations(monitor, diagram, true, true);
  }

  /**
   * Test {@link ERDEntity#addModelRelations(DBRProgressMonitor, ERDContainer, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#addModelRelations(DBRProgressMonitor, ERDContainer,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDEntity.addModelRelations(DBRProgressMonitor, ERDContainer, boolean, boolean)"
  })
  public void testAddModelRelations_thenCallsGetId() throws DBException {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    container.setDataSourceContainer(mock(DBPDataSourceContainer.class));

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId(null);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container3 = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel2 = new DBVModel(dataSourceContainer2);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy2 = new DBVEntity(container4, "Name", "Description Column Names");

    DBVEntity entity2 = new DBVEntity(container3, copy2, targetModel2);
    entity2.addForeignKey(foreignKey);
    ERDEntity erdEntity = new ERDEntity(entity2);
    ERDEntity entity3 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container5 =
        new ERDLogicalPrimaryKey(entity3, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container5, "Name", new ERDContentProviderDefault());

    // Act
    erdEntity.addModelRelations(null, diagram, true, true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link ERDEntity#addModelRelations(DBRProgressMonitor, ERDContainer, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#addModelRelations(DBRProgressMonitor, ERDContainer,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDEntity.addModelRelations(DBRProgressMonitor, ERDContainer, boolean, boolean)"
  })
  public void testAddModelRelations_thenCallsGetId2() throws DBException {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    container.setDataSourceContainer(mock(DBPDataSourceContainer.class));

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId(null);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container3 = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel2 = new DBVModel(dataSourceContainer2);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy2 = new DBVEntity(container4, "Name", "Description Column Names");

    DBVEntity entity2 = new DBVEntity(container3, copy2, targetModel2);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container5, "Name", "Description Column Names");
    entity2.addForeignKey(new DBVEntityForeignKey(entity3));
    entity2.addForeignKey(foreignKey);
    ERDEntity erdEntity = new ERDEntity(entity2);
    ERDEntity entity4 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container6 =
        new ERDLogicalPrimaryKey(entity4, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container6, "Name", new ERDContentProviderDefault());

    // Act
    erdEntity.addModelRelations(null, diagram, true, true);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link ERDEntity#addModelRelations(DBRProgressMonitor, ERDContainer, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#addModelRelations(DBRProgressMonitor, ERDContainer,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDEntity.addModelRelations(DBRProgressMonitor, ERDContainer, boolean, boolean)"
  })
  public void testAddModelRelations_thenThrowIllegalArgumentException() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenThrow(new IllegalArgumentException());
    DBVContainer container2 = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("42");

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addForeignKey(foreignKey);
    ERDEntity erdEntity = new ERDEntity(entity2);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity3 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container3 =
        new ERDLogicalPrimaryKey(entity3, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container3, "Name", new ERDContentProviderDefault());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> erdEntity.addModelRelations(monitor, diagram, true, true));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    verify(parent2).getDataSource();
  }

  /**
   * Test {@link ERDEntity#getName()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDEntity.getName()"})
  public void testGetName_givenDBVContainerWithParentIsDBVContainerAndName_thenReturnName() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    erdEntity.setObject(dbvEntity);

    // Act and Assert
    assertEquals("Name", erdEntity.getName());
  }

  /**
   * Test {@link ERDEntity#fromMap(ERDContext, Map)}.
   *
   * <ul>
   *   <li>Then {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link DBPDataSource}
   *       Alias is {@code Map}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#fromMap(ERDContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.fromMap(ERDContext, Map)"})
  public void testFromMap_thenERDEntityWithDataSourceIsDBPDataSourceAliasIsMap() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put(ERDPersistedState.ATTR_NAME, null);
    objectObjectMap.put(ERDPersistedState.ATTR_ALIAS, null);

    LinkedHashSet<Object> objectSet = new LinkedHashSet<>();
    objectSet.add(objectObjectMap);

    HashMap<String, Object> map = new HashMap<>();
    map.put("attributes", objectSet);
    map.put(ERDPersistedState.ATTR_ALIAS, "Map");

    // Act
    erdEntity.fromMap(context, map);

    // Assert
    assertEquals("Map", erdEntity.getAlias());
  }

  /**
   * Test {@link ERDEntity#fromMap(ERDContext, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link DBPDataSource}
   *       Alias is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#fromMap(ERDContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntity.fromMap(ERDContext, Map)"})
  public void testFromMap_whenHashMap_thenERDEntityWithDataSourceIsDBPDataSourceAliasIsNull() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    erdEntity.fromMap(context, new HashMap<>());

    // Assert that nothing has changed
    assertNull(erdEntity.getAlias());
  }

  /**
   * Test {@link ERDEntity#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntity.toMap(ERDContext, boolean)"})
  public void testToMap_thenThrowIllegalArgumentException() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new IllegalArgumentException());
    DBVContainer container = new DBVContainer(parent, ERDPersistedState.ATTR_ID);
    DBVEntity dbvEntity =
        new DBVEntity(container, ERDPersistedState.ATTR_ID, ERDPersistedState.ATTR_ID);

    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setObject(dbvEntity);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> erdEntity.toMap(context, true));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDEntity#toString()}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link DBPDataSource}
   *       Alias is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDEntity.toString()"})
  public void testToString_givenERDEntityWithDataSourceIsDBPDataSourceAliasIsEmptyString() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setAlias("");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    erdEntity.setObject(dbvEntity);

    // Act and Assert
    assertEquals("Name", erdEntity.toString());
  }

  /**
   * Test {@link ERDEntity#toString()}.
   *
   * <ul>
   *   <li>Given {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link DBPDataSource}
   *       Alias is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDEntity.toString()"})
  public void testToString_givenERDEntityWithDataSourceIsDBPDataSourceAliasIsNull() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setAlias(null);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    erdEntity.setObject(dbvEntity);

    // Act and Assert
    assertEquals("Name", erdEntity.toString());
  }

  /**
   * Test {@link ERDEntity#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Name foo}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDEntity.toString()"})
  public void testToString_thenReturnNameFoo() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setAlias("foo");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    erdEntity.setObject(dbvEntity);

    // Act and Assert
    assertEquals("Name foo", erdEntity.toString());
  }

  /**
   * Test {@link ERDEntity#equals(Object)}, and {@link ERDEntity#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDEntity#equals(Object)}
   *   <li>{@link ERDEntity#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDEntity.equals(Object)", "int ERDEntity.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity erdEntity2 = new ERDEntity(mock(DBPDataSource.class));

    // Act and Assert
    assertEquals(erdEntity, erdEntity2);
    assertEquals(erdEntity.hashCode(), erdEntity2.hashCode());
  }

  /**
   * Test {@link ERDEntity#equals(Object)}, and {@link ERDEntity#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDEntity#equals(Object)}
   *   <li>{@link ERDEntity#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDEntity.equals(Object)", "int ERDEntity.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));

    // Act and Assert
    assertEquals(erdEntity, erdEntity);
    int expectedHashCodeResult = erdEntity.hashCode();
    assertEquals(expectedHashCodeResult, erdEntity.hashCode());
  }

  /**
   * Test {@link ERDEntity#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDEntity.equals(Object)", "int ERDEntity.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    ERDEntity erdEntity = new ERDEntity(entity);

    // Act and Assert
    assertNotEquals(erdEntity, new ERDEntity(mock(DBPDataSource.class)));
  }

  /**
   * Test {@link ERDEntity#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDEntity.equals(Object)", "int ERDEntity.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ERDEntity erdEntity = new ERDEntity(mock(DBPDataSource.class));
    erdEntity.setAlias("Alias");

    // Act and Assert
    assertNotEquals(erdEntity, new ERDEntity(mock(DBPDataSource.class)));
  }

  /**
   * Test {@link ERDEntity#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDEntity.equals(Object)", "int ERDEntity.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ERDEntity(mock(DBPDataSource.class)), null);
  }

  /**
   * Test {@link ERDEntity#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntity#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDEntity.equals(Object)", "int ERDEntity.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ERDEntity(mock(DBPDataSource.class)), "Different type to ERDEntity");
  }
}
