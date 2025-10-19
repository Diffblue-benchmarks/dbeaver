package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.navigator.DBNBrowseSettings;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVEntityConstraint;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ERDUtilsDiffblueTest {
  /**
   * Test {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    Collection<? extends DBSEntityAttribute> actualBestTableIdentifier =
        ERDUtils.getBestTableIdentifier(monitor, entity);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier instanceof List);
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

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

    // Act
    Collection<? extends DBSEntityAttribute> actualBestTableIdentifier =
        ERDUtils.getBestTableIdentifier(monitor, entity);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier instanceof List);
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity4, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity2.addConstraint(constraint);
    entity2.addVirtualAttribute(attribute);

    // Act
    Collection<? extends DBSEntityAttribute> actualBestTableIdentifier =
        ERDUtils.getBestTableIdentifier(monitor, entity2);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier instanceof List);
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier4() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container3 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity3, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    constraint.addAttribute("org.jkiss.dbeaver.model");

    DBVEntity entity4 = new DBVEntity(container, "Name", "Description Column Names");
    entity4.addConstraint(constraint);
    entity4.addVirtualAttribute(attribute);

    // Act
    Collection<? extends DBSEntityAttribute> actualBestTableIdentifier =
        ERDUtils.getBestTableIdentifier(monitor, entity4);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualBestTableIdentifier instanceof List);
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <p>Method under test: {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier5() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBPDataSourceContainer dataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer4.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer5 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer5.getId()).thenReturn("42");
    DBVModel source2 = new DBVModel(dataSourceContainer5);

    DBVModel container3 = new DBVModel(dataSourceContainer4, source2);
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, false, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity3, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    constraint.addAttribute("org.jkiss.dbeaver.model");

    DBVEntity entity4 = new DBVEntity(container, "Name", "Description Column Names");
    entity4.addConstraint(constraint);
    entity4.addVirtualAttribute(attribute);

    // Act
    Collection<? extends DBSEntityAttribute> actualBestTableIdentifier =
        ERDUtils.getBestTableIdentifier(monitor, entity4);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer4, atLeast(1)).getId();
    verify(dataSourceContainer5, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier instanceof List);
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier_givenNull_whenDBVContainerGetRealContainerReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    Collection<? extends DBSEntityAttribute> actualBestTableIdentifier =
        ERDUtils.getBestTableIdentifier(monitor, entity);

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualBestTableIdentifier instanceof List);
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier_thenCallsGetContainer() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container = new DBVModel(dataSourceContainer, source);
    container.setDataSourceContainer(dataSourceContainer3);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addVirtualAttribute(attribute);

    // Act
    Collection<? extends DBSEntityAttribute> actualBestTableIdentifier =
        ERDUtils.getBestTableIdentifier(monitor, entity2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertTrue(actualBestTableIdentifier instanceof List);
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier_thenThrowDBException() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(DBException.class, () -> ERDUtils.getBestTableIdentifier(monitor, entity));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}.
   *
   * <ul>
   *   <li>When {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#getBestTableIdentifier(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDUtils.getBestTableIdentifier(DBRProgressMonitor, DBSEntity)"})
  public void testGetBestTableIdentifier_whenDBVModelWithIdIs42AndMapIsHashMap_thenReturnList()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    Collection<? extends DBSEntityAttribute> actualBestTableIdentifier =
        ERDUtils.getBestTableIdentifier(monitor, entity);

    // Assert
    assertTrue(actualBestTableIdentifier instanceof List);
    assertTrue(actualBestTableIdentifier.isEmpty());
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act and Assert
    assertFalse(ERDUtils.isIdentifyingAssociation(association2));
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation2() {
    // Arrange
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association = new ERDAssociation(null, sourceEntity, targetEntity, true);

    // Act and Assert
    assertFalse(ERDUtils.isIdentifyingAssociation(association));
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation3() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act and Assert
    assertFalse(ERDUtils.isIdentifyingAssociation(association2));
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation4() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity entity2 = new DBVEntity(container2, "?", "?");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity3, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation5() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity entity2 = new DBVEntity(container2, "?", "?");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, false, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity3, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertFalse(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation6() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container2 = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container2, "?", "?");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity2, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("?");

    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    entity3.addConstraint(constraint);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity3);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation7() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource2);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity entity = new DBVEntity(container2, "?", "?");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity entity2 = new DBVEntity(container3, "?", "?");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "?");
    entity.addVirtualAttribute(attribute);
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity3, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("?");

    DBVEntity entity4 = new DBVEntity(container, "Name", "Description Column Names");
    entity4.addConstraint(constraint);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity4);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation8() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    when(parent2.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container2 = new DBVContainer(parent2, "?");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity copy = new DBVEntity(container3, "?", "?");

    DBVEntity entity = new DBVEntity(container2, copy, targetModel);
    entity.addVirtualAttribute(null);
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy2 = new DBVEntityConstraint(entity2, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy2);
    constraint.setUseAllColumns(true);
    constraint.addAttribute("?");

    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    entity3.addConstraint(constraint);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity3);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent2).getDataSource();
    verify(parent2, atLeast(1)).getRealContainer(Mockito.<DBRProgressMonitor>any());
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation_given42_whenDBVModelWithIdIs42AndMapIsHashMap()
      throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "?");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity copy = new DBVEntity(container2, "?", "?");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    entity.addVirtualAttribute(null);
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy2 = new DBVEntityConstraint(entity2, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy2);
    constraint.setUseAllColumns(true);
    constraint.addAttribute("?");
    DBVModel container3 = new DBVModel("42", new HashMap<>());

    DBVEntity entity3 = new DBVEntity(container3, "?", "?");
    entity3.addConstraint(constraint);

    DBVEntityForeignKey association = new DBVEntityForeignKey(entity3);
    association.setRefEntityId("42");
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent, atLeast(1)).getRealContainer(Mockito.<DBRProgressMonitor>any());
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation_givenDBVContainerGetRealContainerReturnNull()
      throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container2 = new DBVContainer(parent2, "?");
    DBVEntity entity = new DBVEntity(container2, "?", "?");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity2, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("?");

    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    entity3.addConstraint(constraint);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity3);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    verify(parent2).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation_givenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource2);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);
    container2.setDataSourceContainer(dataSourceContainer3);

    DBVEntity entity = new DBVEntity(container2, "?", "?");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity3, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.setUseAllColumns(true);
    constraint.addAttribute("?");

    DBVEntity entity4 = new DBVEntity(container, "Name", "Description Column Names");
    entity4.addConstraint(constraint);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity4);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation_givenDBVModelWithIdIs42AndMapIsHashMap() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVModel container2 = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container2, "?", "?");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity2, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("?");

    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    entity3.addConstraint(constraint);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity3);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getRealContainer(DBRProgressMonitor)}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation_thenCallsGetRealContainer() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container2 = new DBVContainer(parent2, "?");
    DBVEntity entity = new DBVEntity(container2, "?", "?");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity2, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("?");

    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    entity3.addConstraint(constraint);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity3);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    verify(parent2).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getRegistry()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation_thenCallsGetRegistry() throws DBException {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    when(parent2.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container2 = new DBVContainer(parent2, "?");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity copy = new DBVEntity(container3, "?", "?");

    DBVEntity entity = new DBVEntity(container2, copy, targetModel);
    entity.addVirtualAttribute(null);
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy2 = new DBVEntityConstraint(entity2, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy2);
    constraint.setUseAllColumns(true);
    constraint.addAttribute("?");

    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    entity3.addConstraint(constraint);

    DBVEntityForeignKey association = new DBVEntityForeignKey(entity3);
    association.setRefEntityId("42");
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent2).getDataSource();
    verify(parent, atLeast(1)).getDataSource();
    verify(parent2, atLeast(1)).getRealContainer(Mockito.<DBRProgressMonitor>any());
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getRegistry()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#isIdentifyingAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isIdentifyingAssociation(ERDAssociation)"})
  public void testIsIdentifyingAssociation_thenCallsGetRegistry2() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "?");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity copy = new DBVEntity(container2, "?", "?");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    entity.addVirtualAttribute(null);
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "?", "?");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "?", "?", true, true, true, true);

    DBVEntityConstraint copy2 = new DBVEntityConstraint(entity2, type, "?");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy2);
    constraint.setUseAllColumns(true);
    constraint.addAttribute("?");

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getRegistry()).thenReturn(dbpDataSourceRegistry);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    when(dataSourceContainer2.getDataSource()).thenReturn(dbpDataSource2);
    DBVModel container3 = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getId()).thenReturn("42");
    DBVModel targetModel2 = new DBVModel(dataSourceContainer3);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity copy3 = new DBVEntity(container4, "?", "?");

    DBVEntity entity3 = new DBVEntity(container3, copy3, targetModel2);
    entity3.addConstraint(constraint);

    DBVEntityForeignKey association = new DBVEntityForeignKey(entity3);
    association.setRefEntityId("42");
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    boolean actualIsIdentifyingAssociationResult = ERDUtils.isIdentifyingAssociation(association2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer2, atLeast(1)).getDataSource();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer3, atLeast(1)).getId();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
    verify(parent, atLeast(1)).getRealContainer(Mockito.<DBRProgressMonitor>any());
    assertTrue(actualIsIdentifyingAssociationResult);
  }

  /**
   * Test {@link ERDUtils#isOptionalAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isOptionalAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isOptionalAssociation(ERDAssociation)"})
  public void testIsOptionalAssociation() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association2 = new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act and Assert
    assertFalse(ERDUtils.isOptionalAssociation(association2));
  }

  /**
   * Test {@link ERDUtils#isOptionalAssociation(ERDAssociation)}.
   *
   * <p>Method under test: {@link ERDUtils#isOptionalAssociation(ERDAssociation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.isOptionalAssociation(ERDAssociation)"})
  public void testIsOptionalAssociation2() {
    // Arrange
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation association = new ERDAssociation(null, sourceEntity, targetEntity, true);

    // Act and Assert
    assertFalse(ERDUtils.isOptionalAssociation(association));
  }

  /**
   * Test {@link ERDUtils#getAttributeByModel(ERDEntity, DBSEntityAttribute)}.
   *
   * <p>Method under test: {@link ERDUtils#getAttributeByModel(ERDEntity, DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntityAttribute ERDUtils.getAttributeByModel(ERDEntity, DBSEntityAttribute)"
  })
  public void testGetAttributeByModel() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "42");
    entity.addAttribute(new ERDEntityAttribute(attribute, true), true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attr =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    // Act
    ERDEntityAttribute actualAttributeByModel = ERDUtils.getAttributeByModel(entity, attr);

    // Assert
    assertNull(actualAttributeByModel);
  }

  /**
   * Test {@link ERDUtils#getAttributeByModel(ERDEntity, DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Then LabelImage return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#getAttributeByModel(ERDEntity, DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntityAttribute ERDUtils.getAttributeByModel(ERDEntity, DBSEntityAttribute)"
  })
  public void testGetAttributeByModel_thenLabelImageReturnDBIcon() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addAttribute(new ERDEntityAttribute(attribute, true), true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attr =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    // Act
    ERDEntityAttribute actualAttributeByModel = ERDUtils.getAttributeByModel(entity, attr);

    // Assert
    assertTrue(actualAttributeByModel.getLabelImage() instanceof DBIcon);
    DBSEntityAttribute object = actualAttributeByModel.getObject();
    assertTrue(object instanceof DBVEntityAttribute);
    assertEquals("Name", actualAttributeByModel.getLabelText());
    assertEquals("Name", actualAttributeByModel.getName());
    assertNull(actualAttributeByModel.getUserData());
    assertNull(actualAttributeByModel.getAlias());
    assertEquals(-1, actualAttributeByModel.getOrder());
    assertFalse(actualAttributeByModel.isChecked());
    assertFalse(actualAttributeByModel.isInForeignKey());
    assertTrue(actualAttributeByModel.isInPrimaryKey());
    assertSame(attribute, object);
  }

  /**
   * Test {@link ERDUtils#getAttributeByModel(ERDEntity, DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>When {@link ERDEntity#ERDEntity(DBPDataSource)} with dataSource is {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#getAttributeByModel(ERDEntity, DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntityAttribute ERDUtils.getAttributeByModel(ERDEntity, DBSEntityAttribute)"
  })
  public void testGetAttributeByModel_whenERDEntityWithDataSourceIsDBPDataSource() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attr =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    // Act
    ERDEntityAttribute actualAttributeByModel = ERDUtils.getAttributeByModel(entity, attr);

    // Assert
    assertNull(actualAttributeByModel);
  }

  /**
   * Test {@link ERDUtils#getAttributeByModel(ERDEntity, DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#getAttributeByModel(ERDEntity, DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntityAttribute ERDUtils.getAttributeByModel(ERDEntity, DBSEntityAttribute)"
  })
  public void testGetAttributeByModel_whenNull_thenReturnNull() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attr =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    ERDEntityAttribute actualAttributeByModel = ERDUtils.getAttributeByModel(null, attr);

    // Assert
    assertNull(actualAttributeByModel);
  }

  /**
   * Test {@link ERDUtils#getObjectsFromERD(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#getObjectsFromERD(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDUtils.getObjectsFromERD(List)"})
  public void testGetObjectsFromERD_whenArrayList_thenReturnEmpty() {
    // Arrange and Act
    List<Object> actualObjectsFromERD = ERDUtils.getObjectsFromERD(new ArrayList<>());

    // Assert
    assertTrue(actualObjectsFromERD.isEmpty());
  }

  /**
   * Test {@link ERDUtils#getObjectsFromERD(List)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#getObjectsFromERD(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDUtils.getObjectsFromERD(List)"})
  public void testGetObjectsFromERD_whenNull_thenReturnNull() {
    // Arrange and Act
    List<Object> actualObjectsFromERD = ERDUtils.getObjectsFromERD(null);

    // Assert
    assertNull(actualObjectsFromERD);
  }

  /**
   * Test {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity,
   * Object)}.
   *
   * <p>Method under test: {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, DBSEntity, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntity ERDUtils.makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity, Object)"
  })
  public void testMakeEntityFromObject() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container2 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    ERDEntity actualMakeEntityFromObjectResult =
        ERDUtils.makeEntityFromObject(monitor, diagram, otherEntities, entity2, "User Data");

    // Assert
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
    assertSame(entity2, actualMakeEntityFromObjectResult.getObject());
  }

  /**
   * Test {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity,
   * Object)}.
   *
   * <p>Method under test: {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, DBSEntity, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntity ERDUtils.makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity, Object)"
  })
  public void testMakeEntityFromObject2() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container2 = new DBVModel(dataSourceContainer);
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    ERDEntity actualMakeEntityFromObjectResult =
        ERDUtils.makeEntityFromObject(monitor, diagram, otherEntities, entity2, "User Data");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertSame(entity2, actualMakeEntityFromObjectResult.getObject());
  }

  /**
   * Test {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity,
   * Object)}.
   *
   * <p>Method under test: {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, DBSEntity, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntity ERDUtils.makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity, Object)"
  })
  public void testMakeEntityFromObject3() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();
    DBVModel container2 = new DBVModel("42", new HashMap<>());
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    ERDEntity actualMakeEntityFromObjectResult =
        ERDUtils.makeEntityFromObject(monitor, diagram, otherEntities, entity2, "User Data");

    // Assert
    assertSame(entity2, actualMakeEntityFromObjectResult.getObject());
  }

  /**
   * Test {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity,
   * Object)}.
   *
   * <p>Method under test: {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, DBSEntity, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntity ERDUtils.makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity, Object)"
  })
  public void testMakeEntityFromObject4() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);
    container2.setDataSourceContainer(dataSourceContainer3);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container3 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity2);
    foreignKey.setRefEntityId("42");

    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    entity3.addForeignKey(foreignKey);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntity entity5 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity5, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity4, copy);
    entity3.addConstraint(constraint);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity6 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity6, mock(DBVEntityAttribute.class), "Name");
    entity3.addVirtualAttribute(attribute);

    // Act
    ERDEntity actualMakeEntityFromObjectResult =
        ERDUtils.makeEntityFromObject(monitor, diagram, otherEntities, entity3, "User Data");

    // Assert
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
    DBSEntity object = actualMakeEntityFromObjectResult.getObject();
    assertTrue(object instanceof DBVEntity);
    List<DBVEntityForeignKey> foreignKeys = ((DBVEntity) object).getForeignKeys();
    assertEquals(1, foreignKeys.size());
    assertSame(foreignKey, foreignKeys.get(0));
  }

  /**
   * Test {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity,
   * Object)}.
   *
   * <p>Method under test: {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, DBSEntity, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntity ERDUtils.makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity, Object)"
  })
  public void testMakeEntityFromObject5() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer3 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer3.getDataSource()).thenReturn(dbpDataSource);

    DBVModel container2 = new DBVModel(dataSourceContainer, source);
    container2.setDataSourceContainer(dataSourceContainer3);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBPDataSourceContainer dataSourceContainer4 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer4.getId()).thenReturn("42");
    when(dataSourceContainer4.getDataSource()).thenReturn(dbpDataSource2);
    DBVModel container3 = new DBVModel(dataSourceContainer4);

    DBPDataSourceContainer dataSourceContainer5 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer5.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer5);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container4, "Name", "Description Column Names");

    DBVEntity entity2 = new DBVEntity(container3, copy, targetModel);

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity2);
    foreignKey.setRefEntityId("42");

    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    entity3.addForeignKey(foreignKey);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntity entity5 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy2 = new DBVEntityConstraint(entity5, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity4, copy2);
    entity3.addConstraint(constraint);
    DBVContainer container6 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity6 = new DBVEntity(container6, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity6, mock(DBVEntityAttribute.class), "Name");
    entity3.addVirtualAttribute(attribute);

    // Act
    ERDEntity actualMakeEntityFromObjectResult =
        ERDUtils.makeEntityFromObject(monitor, diagram, otherEntities, entity3, "User Data");

    // Assert
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer4).getDataSource();
    verify(dataSourceContainer3, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dataSourceContainer4, atLeast(1)).getId();
    verify(dataSourceContainer5, atLeast(1)).getId();
    verify(dbpDataSourceContainer2).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    DBSEntity object = actualMakeEntityFromObjectResult.getObject();
    assertTrue(object instanceof DBVEntity);
    List<DBVEntityForeignKey> foreignKeys = ((DBVEntity) object).getForeignKeys();
    assertEquals(1, foreignKeys.size());
    assertSame(foreignKey, foreignKeys.get(0));
  }

  /**
   * Test {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity,
   * Object)}.
   *
   * <ul>
   *   <li>Then return Object Constraints size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, DBSEntity, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntity ERDUtils.makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity, Object)"
  })
  public void testMakeEntityFromObject_thenReturnObjectConstraintsSizeIsOne() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

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

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity4, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity2.addConstraint(constraint);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");
    entity2.addVirtualAttribute(attribute);

    // Act
    ERDEntity actualMakeEntityFromObjectResult =
        ERDUtils.makeEntityFromObject(monitor, diagram, otherEntities, entity2, "User Data");

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    DBSEntity object = actualMakeEntityFromObjectResult.getObject();
    assertTrue(object instanceof DBVEntity);
    List<DBVEntityConstraint> constraints = ((DBVEntity) object).getConstraints();
    assertEquals(1, constraints.size());
    assertSame(constraint, constraints.get(0));
    assertSame(constraint, ((DBVEntity) object).getBestIdentifier());
  }

  /**
   * Test {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity,
   * Object)}.
   *
   * <ul>
   *   <li>Then return Object EntityAttributes size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, DBSEntity, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntity ERDUtils.makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity, Object)"
  })
  public void testMakeEntityFromObject_thenReturnObjectEntityAttributesSizeIsOne() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

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

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");
    entity2.addVirtualAttribute(attribute);

    // Act
    ERDEntity actualMakeEntityFromObjectResult =
        ERDUtils.makeEntityFromObject(monitor, diagram, otherEntities, entity2, "User Data");

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    DBSEntity object = actualMakeEntityFromObjectResult.getObject();
    assertTrue(object instanceof DBVEntity);
    assertEquals(1, ((DBVEntity) object).getEntityAttributes().size());
    DBVEntityConstraint bestIdentifier = ((DBVEntity) object).getBestIdentifier();
    assertSame(entity2, bestIdentifier.getEntity());
    assertSame(entity2, bestIdentifier.getParentObject());
  }

  /**
   * Test {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity,
   * Object)}.
   *
   * <ul>
   *   <li>Then return Object ForeignKeys first Name is {@code vfk_Name_?}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, DBSEntity, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntity ERDUtils.makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity, Object)"
  })
  public void testMakeEntityFromObject_thenReturnObjectForeignKeysFirstNameIsVfkName() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

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

    DBVModel container2 = new DBVModel(dataSourceContainer, source);

    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    entity2.addForeignKey(new DBVEntityForeignKey(entity3));
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntity entity5 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBSEntityConstraintType type =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    DBVEntityConstraint copy = new DBVEntityConstraint(entity5, type, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity4, copy);
    entity2.addConstraint(constraint);
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity6 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity6, mock(DBVEntityAttribute.class), "Name");
    entity2.addVirtualAttribute(attribute);

    // Act
    ERDEntity actualMakeEntityFromObjectResult =
        ERDUtils.makeEntityFromObject(monitor, diagram, otherEntities, entity2, "User Data");

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    DBSEntity object = actualMakeEntityFromObjectResult.getObject();
    assertTrue(object instanceof DBVEntity);
    List<DBVEntityForeignKey> foreignKeys = ((DBVEntity) object).getForeignKeys();
    assertEquals(1, foreignKeys.size());
    DBVEntityForeignKey getResult = foreignKeys.get(0);
    assertEquals("vfk_Name_?", getResult.getName());
    assertNull(getResult.getRefEntityId());
    DBVEntity entity7 = getResult.getEntity();
    assertNull(entity7.getContainer().getDataSource());
    assertNull(entity7.getDataSource());
    assertNull(entity7.getBestIdentifier().getDataSource());
    assertNull(getResult.getDataSource());
  }

  /**
   * Test {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity,
   * Object)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#makeEntityFromObject(DBRProgressMonitor, ERDDiagram,
   * List, DBSEntity, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDEntity ERDUtils.makeEntityFromObject(DBRProgressMonitor, ERDDiagram, List, DBSEntity, Object)"
  })
  public void testMakeEntityFromObject_whenDBVContainerGetRealContainerReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ArrayList<ERDEntity> otherEntities = new ArrayList<>();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container2 = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    ERDEntity actualMakeEntityFromObjectResult =
        ERDUtils.makeEntityFromObject(monitor, diagram, otherEntities, entity2, "User Data");

    // Assert
    verify(parent, atLeast(1)).getRealContainer(isA(DBRProgressMonitor.class));
    assertSame(entity2, actualMakeEntityFromObjectResult.getObject());
  }

  /**
   * Test {@link ERDUtils#collectDatabaseTables(DBRProgressMonitor, DBSObject, ERDDiagram, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then return {@link Set}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#collectDatabaseTables(DBRProgressMonitor, DBSObject,
   * ERDDiagram, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection ERDUtils.collectDatabaseTables(DBRProgressMonitor, DBSObject, ERDDiagram, boolean, boolean)"
  })
  public void testCollectDatabaseTables_whenLoggingProgressMonitor_thenReturnSet()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey root =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act
    Collection<DBSEntity> actualCollectDatabaseTablesResult =
        ERDUtils.collectDatabaseTables(monitor, root, diagram, true, true);

    // Assert
    assertTrue(actualCollectDatabaseTablesResult instanceof Set);
    assertTrue(actualCollectDatabaseTablesResult.isEmpty());
  }

  /**
   * Test {@link ERDUtils#skipSystemEntity(DBSEntity)}.
   *
   * <ul>
   *   <li>Given {@link DBNBrowseSettings} {@link DBNBrowseSettings#isShowSystemObjects()} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#skipSystemEntity(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.skipSystemEntity(DBSEntity)"})
  public void testSkipSystemEntity_givenDBNBrowseSettingsIsShowSystemObjectsReturnTrue() {
    // Arrange
    DBNBrowseSettings dbnBrowseSettings = mock(DBNBrowseSettings.class);
    when(dbnBrowseSettings.isShowSystemObjects()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getNavigatorSettings()).thenReturn(dbnBrowseSettings);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    boolean actualSkipSystemEntityResult = ERDUtils.skipSystemEntity(entity);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getNavigatorSettings();
    verify(dbnBrowseSettings).isShowSystemObjects();
    verify(parent).getDataSource();
    assertFalse(actualSkipSystemEntityResult);
  }

  /**
   * Test {@link ERDUtils#skipSystemEntity(DBSEntity)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#skipSystemEntity(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.skipSystemEntity(DBSEntity)"})
  public void testSkipSystemEntity_thenCallsGetDataSource() {
    // Arrange
    DBNBrowseSettings dbnBrowseSettings = mock(DBNBrowseSettings.class);
    when(dbnBrowseSettings.isShowSystemObjects()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getNavigatorSettings()).thenReturn(dbnBrowseSettings);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    boolean actualSkipSystemEntityResult = ERDUtils.skipSystemEntity(entity);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getNavigatorSettings();
    verify(dbnBrowseSettings).isShowSystemObjects();
    verify(parent).getDataSource();
    assertFalse(actualSkipSystemEntityResult);
  }

  /**
   * Test {@link ERDUtils#skipSystemEntity(DBSEntity)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDUtils#skipSystemEntity(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDUtils.skipSystemEntity(DBSEntity)"})
  public void testSkipSystemEntity_thenCallsGetDataSource2() {
    // Arrange
    DBNBrowseSettings dbnBrowseSettings = mock(DBNBrowseSettings.class);
    when(dbnBrowseSettings.isShowSystemObjects()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getNavigatorSettings()).thenReturn(dbnBrowseSettings);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    boolean actualSkipSystemEntityResult = ERDUtils.skipSystemEntity(entity);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(dbpDataSourceContainer).getNavigatorSettings();
    verify(dbnBrowseSettings).isShowSystemObjects();
    assertFalse(actualSkipSystemEntityResult);
  }
}
