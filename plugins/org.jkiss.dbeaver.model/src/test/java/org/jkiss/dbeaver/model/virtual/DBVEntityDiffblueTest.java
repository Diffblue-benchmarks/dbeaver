package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvaluationContext;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.data.DBDLabelValuePair;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.jkiss.dbeaver.model.impl.preferences.BundlePreferenceStore;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceStore;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSContextBoundAttribute;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.struct.DBSEntityType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBVEntityDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBVEntity#DBVEntity(DBVContainer, String, String)}
   *   <li>{@link DBVEntity#setColorOverrides(List)}
   *   <li>{@link DBVEntity#setDescription(String)}
   *   <li>{@link DBVEntity#setDescriptionColumnNames(String)}
   *   <li>{@link DBVEntity#getContainer()}
   *   <li>{@link DBVEntity#getDescription()}
   *   <li>{@link DBVEntity#getDescriptionColumnNames()}
   *   <li>{@link DBVEntity#getEntityAttributes()}
   *   <li>{@link DBVEntity#getEntityType()}
   *   <li>{@link DBVEntity#getName()}
   *   <li>{@link DBVEntity#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntity.<init>(DBVContainer, String, String)",
    "DBVContainer DBVEntity.getContainer()",
    "String DBVEntity.getDescription()",
    "String DBVEntity.getDescriptionColumnNames()",
    "List DBVEntity.getEntityAttributes()",
    "DBSEntityType DBVEntity.getEntityType()",
    "String DBVEntity.getName()",
    "void DBVEntity.setColorOverrides(List)",
    "void DBVEntity.setDescription(String)",
    "void DBVEntity.setDescriptionColumnNames(String)",
    "String DBVEntity.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent, "Name");

    // Act
    DBVEntity actualDbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    actualDbvEntity.setColorOverrides(new ArrayList<>());
    actualDbvEntity.setDescription("The characteristics of someone or something");
    actualDbvEntity.setDescriptionColumnNames("Description Column Names");
    DBVContainer actualContainer = actualDbvEntity.getContainer();
    String actualDescription = actualDbvEntity.getDescription();
    String actualDescriptionColumnNames = actualDbvEntity.getDescriptionColumnNames();
    List<DBVEntityAttribute> actualEntityAttributes = actualDbvEntity.getEntityAttributes();
    DBSEntityType actualEntityType = actualDbvEntity.getEntityType();
    String actualName = actualDbvEntity.getName();

    // Assert
    assertEquals("Description Column Names", actualDescriptionColumnNames);
    assertEquals("Name", actualName);
    assertEquals("Name", actualDbvEntity.toString());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertNull(actualEntityAttributes);
    assertNull(actualDbvEntity.getTransformSettings());
    assertSame(container, actualContainer);
    assertSame(container, actualDbvEntity.getParentObject());
    assertSame(DBSEntityType.VIRTUAL_ENTITY, actualEntityType);
  }

  /**
   * Test {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.<init>(DBVContainer, DBVEntity, DBVModel)"})
  public void testNewDBVEntity_givenArrayList() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity copy =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    copy.setColorOverrides(new ArrayList<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy2 =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy2);
    copy.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntity actualDbvEntity = new DBVEntity(container, copy, targetModel);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getDataSource();
    DBVEntityConstraint bestIdentifier = actualDbvEntity.getBestIdentifier();
    DBSEntityConstraintType constraintType = bestIdentifier.getConstraintType();
    assertEquals("Association", constraintType.getLocalizedName());
    assertEquals("Association", constraintType.getName());
    assertEquals("Name", bestIdentifier.getName());
    assertEquals("association", constraintType.getId());
    assertFalse(constraintType.isLogical());
    assertFalse(constraintType.isUnique());
    assertFalse(bestIdentifier.hasAttributes());
    List<DBVColorOverride> colorOverrides = actualDbvEntity.getColorOverrides();
    assertTrue(colorOverrides.isEmpty());
    assertTrue(bestIdentifier.getAttributes().isEmpty());
    Map<String, Object> properties = actualDbvEntity.getProperties();
    assertTrue(properties.isEmpty());
    assertTrue(constraintType.isAssociation());
    assertSame(colorOverrides, actualDbvEntity.getCustomAttributes());
    assertSame(colorOverrides, actualDbvEntity.getForeignKeys());
    DBVContainer container3 = actualDbvEntity.getContainer();
    assertSame(properties, container3.getParentObject().getProperties());
    assertSame(properties, container3.getProperties());
  }

  /**
   * Test {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then return Properties size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.<init>(DBVContainer, DBVEntity, DBVModel)"})
  public void testNewDBVEntity_givenName_thenReturnPropertiesSizeIsOne() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity copy =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    copy.setProperty("Name", DBPEvent.RENAME);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy2 =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy2);
    copy.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntity actualDbvEntity = new DBVEntity(container, copy, targetModel);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getDataSource();
    Map<String, Object> properties = actualDbvEntity.getProperties();
    assertEquals(1, properties.size());
    assertTrue(properties.containsKey("Name"));
    DBVContainer container3 = actualDbvEntity.getContainer();
    Map<String, Object> properties2 = container3.getProperties();
    assertTrue(properties2.isEmpty());
    assertSame(properties2, container3.getParentObject().getProperties());
  }

  /**
   * Test {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}.
   *
   * <ul>
   *   <li>Then return BestIdentifier Attributes size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.<init>(DBVContainer, DBVEntity, DBVModel)"})
  public void testNewDBVEntity_thenReturnBestIdentifierAttributesSizeIsOne() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVContainer container2 = new DBVContainer(parent4, "Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("Name");

    DBVEntity copy2 = new DBVEntity(container2, "Name", "Description Column Names");
    copy2.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntity actualDbvEntity = new DBVEntity(container, copy2, targetModel);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getDataSource();
    DBVEntityConstraint bestIdentifier = actualDbvEntity.getBestIdentifier();
    List<DBVEntityConstraintColumn> attributes = bestIdentifier.getAttributes();
    assertEquals(1, attributes.size());
    DBVEntityConstraintColumn getResult = attributes.get(0);
    assertEquals("Name", getResult.getAttributeName());
    assertNull(getResult.getAttribute());
    assertTrue(bestIdentifier.hasAttributes());
  }

  /**
   * Test {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}.
   *
   * <ul>
   *   <li>Then return BestIdentifier ConstraintType Name is {@code VIRTUAL PRIMARY KEY}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.<init>(DBVContainer, DBVEntity, DBVModel)"})
  public void testNewDBVEntity_thenReturnBestIdentifierConstraintTypeNameIsVirtualPrimaryKey() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity copy =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntity actualDbvEntity = new DBVEntity(container, copy, targetModel);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    DBVEntityConstraint bestIdentifier = actualDbvEntity.getBestIdentifier();
    DBSEntityConstraintType constraintType = bestIdentifier.getConstraintType();
    assertEquals("VIRTUAL PRIMARY KEY", constraintType.getName());
    assertEquals("VIRTUAL_PK", bestIdentifier.getName());
    assertEquals("Virtual Unique Key", constraintType.getLocalizedName());
    assertEquals("vpk", constraintType.getId());
    assertFalse(constraintType.isAssociation());
    assertTrue(constraintType.isLogical());
    assertTrue(constraintType.isUnique());
  }

  /**
   * Test {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}.
   *
   * <ul>
   *   <li>Then return ColorOverrides size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.<init>(DBVContainer, DBVEntity, DBVModel)"})
  public void testNewDBVEntity_thenReturnColorOverridesSizeIsOne() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity copy =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    copy.addColorOverride(color);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy2 =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy2);
    copy.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntity actualDbvEntity = new DBVEntity(container, copy, targetModel);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getDataSource();
    List<DBVColorOverride> colorOverrides = actualDbvEntity.getColorOverrides();
    assertEquals(1, colorOverrides.size());
    DBVColorOverride getResult = colorOverrides.get(0);
    assertEquals("Attribute Name", getResult.getAttributeName());
    assertEquals("Color Background", getResult.getColorBackground());
    assertEquals("Color Foreground", getResult.getColorForeground());
    assertNull(getResult.getColorBackground2());
    assertNull(getResult.getColorForeground2());
    assertEquals(1, getResult.getAttributeValues().length);
    assertEquals(DBCLogicalOperator.EQUALS, getResult.getOperator());
    assertFalse(getResult.isRange());
    assertFalse(getResult.isSingleColumn());
    List<DBVEntityAttribute> customAttributes = actualDbvEntity.getCustomAttributes();
    assertTrue(customAttributes.isEmpty());
    assertSame(customAttributes, actualDbvEntity.getForeignKeys());
  }

  /**
   * Test {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}.
   *
   * <ul>
   *   <li>Then return ForeignKeys Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.<init>(DBVContainer, DBVEntity, DBVModel)"})
  public void testNewDBVEntity_thenReturnForeignKeysEmpty() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity copy =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    copy.addForeignKey(new DBVEntityForeignKey(entity));
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy2 =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy2);
    copy.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntity actualDbvEntity = new DBVEntity(container, copy, targetModel);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3, atLeast(1)).getDataSource();
    DBVEntityConstraint bestIdentifier = actualDbvEntity.getBestIdentifier();
    DBSEntityConstraintType constraintType = bestIdentifier.getConstraintType();
    assertEquals("Association", constraintType.getLocalizedName());
    assertEquals("Association", constraintType.getName());
    assertEquals("Name", bestIdentifier.getName());
    assertEquals("association", constraintType.getId());
    assertFalse(constraintType.isLogical());
    assertFalse(constraintType.isUnique());
    assertFalse(bestIdentifier.hasAttributes());
    List<DBVColorOverride> colorOverrides = actualDbvEntity.getColorOverrides();
    assertTrue(colorOverrides.isEmpty());
    assertTrue(actualDbvEntity.getForeignKeys().isEmpty());
    assertTrue(bestIdentifier.getAttributes().isEmpty());
    Map<String, Object> properties = actualDbvEntity.getProperties();
    assertTrue(properties.isEmpty());
    assertTrue(constraintType.isAssociation());
    assertSame(colorOverrides, actualDbvEntity.getCustomAttributes());
    DBVContainer container4 = actualDbvEntity.getContainer();
    assertSame(properties, container4.getParentObject().getProperties());
    assertSame(properties, container4.getProperties());
  }

  /**
   * Test {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}.
   *
   * <ul>
   *   <li>Then return ForeignKeys is ColorOverrides.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#DBVEntity(DBVContainer, DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.<init>(DBVContainer, DBVEntity, DBVModel)"})
  public void testNewDBVEntity_thenReturnForeignKeysIsColorOverrides() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity copy =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy2 =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy2);
    copy.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntity actualDbvEntity = new DBVEntity(container, copy, targetModel);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getDataSource();
    DBVEntityConstraint bestIdentifier = actualDbvEntity.getBestIdentifier();
    DBSEntityConstraintType constraintType = bestIdentifier.getConstraintType();
    assertEquals("Association", constraintType.getLocalizedName());
    assertEquals("Association", constraintType.getName());
    assertEquals("Name", bestIdentifier.getName());
    assertEquals("association", constraintType.getId());
    assertFalse(constraintType.isLogical());
    assertFalse(constraintType.isUnique());
    assertFalse(bestIdentifier.hasAttributes());
    List<DBVColorOverride> colorOverrides = actualDbvEntity.getColorOverrides();
    assertTrue(colorOverrides.isEmpty());
    assertTrue(bestIdentifier.getAttributes().isEmpty());
    Map<String, Object> properties = actualDbvEntity.getProperties();
    assertTrue(properties.isEmpty());
    assertTrue(constraintType.isAssociation());
    assertSame(colorOverrides, actualDbvEntity.getCustomAttributes());
    assertSame(colorOverrides, actualDbvEntity.getForeignKeys());
    DBVContainer container3 = actualDbvEntity.getContainer();
    assertSame(properties, container3.getParentObject().getProperties());
    assertSame(properties, container3.getProperties());
  }

  /**
   * Test {@link DBVEntity#getDataSource()}.
   *
   * <p>Method under test: {@link DBVEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntity.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    dbvEntity.getDataSource();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVEntity#getDataSource()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getDataSource()} return {@link
   *       DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntity.getDataSource()"})
  public void testGetDataSource_givenDBVContainerGetDataSourceReturnDBPDataSource() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act
    dbvEntity.getDataSource();

    // Assert
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntity#getDataSource()}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntity.getDataSource()"})
  public void testGetDataSource_givenDBVModelWithIdIs42AndMapIsHashMap_thenReturnNull() {
    // Arrange
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertNull(dbvEntity.getDataSource());
  }

  /**
   * Test {@link DBVEntity#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntity.getDataSource()"})
  public void testGetDataSource_thenCallsGetDataSource() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    dbvEntity.getDataSource();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVEntity#getDataSource()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntity.getDataSource()"})
  public void testGetDataSource_thenThrowIllegalStateException() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalStateException());
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dbvEntity.getDataSource());
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntity#getCustomAttributes()}.
   *
   * <p>Method under test: {@link DBVEntity#getCustomAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getCustomAttributes()"})
  public void testGetCustomAttributes() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertTrue(dbvEntity.getCustomAttributes().isEmpty());
  }

  /**
   * Test {@link DBVEntity#getAttributes(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DBVEntity#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    // Act
    List<? extends DBSEntityAttribute> actualAttributes =
        dbvEntity.getAttributes(new LoggingProgressMonitor());

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualAttributes.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getAttributes(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DBVEntity#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes2() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    List<? extends DBSEntityAttribute> actualAttributes =
        dbvEntity.getAttributes(new LoggingProgressMonitor());

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualAttributes.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getAttributes(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVModel#DBVModel(DBPDataSourceContainer)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes_givenDBVContainerWithParentIsDBVModelAndName() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    List<? extends DBSEntityAttribute> actualAttributes =
        dbvEntity.getAttributes(new LoggingProgressMonitor());

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualAttributes.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getAttributes(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@code null}
   *       and {@code Name}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes_givenDBVContainerWithParentIsNullAndName_thenReturnEmpty()
      throws DBException {
    // Arrange
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertTrue(dbvEntity.getAttributes(new LoggingProgressMonitor()).isEmpty());
  }

  /**
   * Test {@link DBVEntity#getAttributes(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes_givenDBVModelWithIdIs42AndMapIsHashMap_thenReturnEmpty()
      throws DBException {
    // Arrange
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertTrue(dbvEntity.getAttributes(new LoggingProgressMonitor()).isEmpty());
  }

  /**
   * Test {@link DBVEntity#getAttributes(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes_thenThrowDBException() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertThrows(DBException.class, () -> dbvEntity.getAttributes(new LoggingProgressMonitor()));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntity.getAttribute(DBRProgressMonitor, String)"})
  public void testGetAttribute() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act
    DBSEntityAttribute actualAttribute =
        dbvEntity.getAttribute(new LoggingProgressMonitor(), "Attribute Name");

    // Assert
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntity.getAttribute(DBRProgressMonitor, String)"})
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
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    // Act
    DBSEntityAttribute actualAttribute =
        dbvEntity.getAttribute(new LoggingProgressMonitor(), "Attribute Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntity.getAttribute(DBRProgressMonitor, String)"})
  public void testGetAttribute3() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBSEntityAttribute actualAttribute =
        dbvEntity.getAttribute(new LoggingProgressMonitor(), "Attribute Name");

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVModel#DBVModel(DBPDataSourceContainer)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntity.getAttribute(DBRProgressMonitor, String)"})
  public void testGetAttribute_givenDBVContainerWithParentIsDBVModelAndName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBSEntityAttribute actualAttribute =
        dbvEntity.getAttribute(new LoggingProgressMonitor(), "Attribute Name");

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualAttribute);
  }

  /**
   * Test {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@code null}
   *       and {@code Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntity.getAttribute(DBRProgressMonitor, String)"})
  public void testGetAttribute_givenDBVContainerWithParentIsNullAndName_thenReturnNull() {
    // Arrange
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertNull(dbvEntity.getAttribute(new LoggingProgressMonitor(), "Attribute Name"));
  }

  /**
   * Test {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAttribute(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DBVEntity.getAttribute(DBRProgressMonitor, String)"})
  public void testGetAttribute_givenDBVModelWithIdIs42AndMapIsHashMap_thenReturnNull() {
    // Arrange
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertNull(dbvEntity.getAttribute(new LoggingProgressMonitor(), "Attribute Name"));
  }

  /**
   * Test {@link DBVEntity#getConstraints(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link DBVEntity#getConstraints(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getConstraints(DBRProgressMonitor)"})
  public void testGetConstraintsWithDBRProgressMonitor() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertNull(dbvEntity.getConstraints(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DBVEntity#getConstraints()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getConstraints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getConstraints()"})
  public void testGetConstraints_thenReturnEmpty() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertTrue(dbvEntity.getConstraints().isEmpty());
  }

  /**
   * Test {@link DBVEntity#getConstraints()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getConstraints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getConstraints()"})
  public void testGetConstraints_thenReturnSizeIsOne() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    dbvEntity.addConstraint(constraint);

    // Act
    List<DBVEntityConstraint> actualConstraints = dbvEntity.getConstraints();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertEquals(1, actualConstraints.size());
    assertSame(constraint, actualConstraints.get(0));
  }

  /**
   * Test {@link DBVEntity#getBestIdentifier()}.
   *
   * <p>Method under test: {@link DBVEntity#getBestIdentifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntityConstraint DBVEntity.getBestIdentifier()"})
  public void testGetBestIdentifier() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "VIRTUAL_PK");
    DBVEntity entity = new DBVEntity(container, "VIRTUAL_PK", "VIRTUAL_PK");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "VIRTUAL_PK", "VIRTUAL_PK");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "VIRTUAL_PK");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    dbvEntity.addConstraint(constraint);

    // Act
    DBVEntityConstraint actualBestIdentifier = dbvEntity.getBestIdentifier();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertSame(constraint, actualBestIdentifier);
  }

  /**
   * Test {@link DBVEntity#getBestIdentifier()}.
   *
   * <p>Method under test: {@link DBVEntity#getBestIdentifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntityConstraint DBVEntity.getBestIdentifier()"})
  public void testGetBestIdentifier2() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "VIRTUAL_PK");
    DBVEntity entity = new DBVEntity(container, "VIRTUAL_PK", "VIRTUAL_PK");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "VIRTUAL_PK", "VIRTUAL_PK");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.PRIMARY_KEY, "VIRTUAL_PK");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    dbvEntity.addConstraint(constraint);

    // Act
    DBVEntityConstraint actualBestIdentifier = dbvEntity.getBestIdentifier();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertSame(constraint, actualBestIdentifier);
  }

  /**
   * Test {@link DBVEntity#getBestIdentifier()}.
   *
   * <ul>
   *   <li>Then return ConstraintType Name is {@code VIRTUAL PRIMARY KEY}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getBestIdentifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntityConstraint DBVEntity.getBestIdentifier()"})
  public void testGetBestIdentifier_thenReturnConstraintTypeNameIsVirtualPrimaryKey() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act
    DBVEntityConstraint actualBestIdentifier = dbvEntity.getBestIdentifier();

    // Assert
    DBSEntityConstraintType constraintType = actualBestIdentifier.getConstraintType();
    assertEquals("VIRTUAL PRIMARY KEY", constraintType.getName());
    assertEquals("Virtual Unique Key", constraintType.getLocalizedName());
    assertEquals("vpk", constraintType.getId());
    assertTrue(constraintType.isLogical());
    assertSame(dbvEntity, actualBestIdentifier.getEntity());
    assertSame(dbvEntity, actualBestIdentifier.getParentObject());
  }

  /**
   * Test {@link DBVEntity#dispose()}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)} RefEntityId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.dispose()"})
  public void testDispose_givenDBVEntityForeignKeyWithEntityIsDBVEntityRefEntityIdIs42() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("42");

    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(foreignKey);

    // Act
    dbvEntity.dispose();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertTrue(dbvEntity.getForeignKeys().isEmpty());
  }

  /**
   * Test {@link DBVEntity#dispose()}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)} RefEntityId is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.dispose()"})
  public void testDispose_givenDBVEntityForeignKeyWithEntityIsDBVEntityRefEntityIdIsNull() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId(null);

    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(foreignKey);

    // Act
    dbvEntity.dispose();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertTrue(dbvEntity.getForeignKeys().isEmpty());
  }

  /**
   * Test {@link DBVEntity#copyFrom(DBVEntity, DBVModel)} with {@code src}, {@code targetModel}.
   *
   * <p>Method under test: {@link DBVEntity#copyFrom(DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.copyFrom(DBVEntity, DBVModel)"})
  public void testCopyFromWithSrcTargetModel() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity src =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvEntity.copyFrom(src, new DBVModel(dataSourceContainer));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVEntity#copyFrom(DBVEntity, DBVModel)} with {@code src}, {@code targetModel}.
   *
   * <p>Method under test: {@link DBVEntity#copyFrom(DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.copyFrom(DBVEntity, DBVModel)"})
  public void testCopyFromWithSrcTargetModel2() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(new DBVEntityForeignKey(entity));
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity src =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvEntity.copyFrom(src, new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntity#copyFrom(DBVEntity, DBVModel)} with {@code src}, {@code targetModel}.
   *
   * <p>Method under test: {@link DBVEntity#copyFrom(DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.copyFrom(DBVEntity, DBVModel)"})
  public void testCopyFromWithSrcTargetModel3() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(new DBVEntityForeignKey(entity));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity src =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    src.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvEntity.copyFrom(src, new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent3).getDataSource();
  }

  /**
   * Test {@link DBVEntity#copyFrom(DBVEntity, DBVModel)} with {@code src}, {@code targetModel}.
   *
   * <p>Method under test: {@link DBVEntity#copyFrom(DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.copyFrom(DBVEntity, DBVModel)"})
  public void testCopyFromWithSrcTargetModel4() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(new DBVEntityForeignKey(entity));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity src =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    src.addColorOverride(color);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    src.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvEntity.copyFrom(src, new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent3).getDataSource();
  }

  /**
   * Test {@link DBVEntity#copyFrom(DBVEntity, DBVModel)} with {@code src}, {@code targetModel}.
   *
   * <p>Method under test: {@link DBVEntity#copyFrom(DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.copyFrom(DBVEntity, DBVModel)"})
  public void testCopyFromWithSrcTargetModel5() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(new DBVEntityForeignKey(entity));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVContainer container2 = new DBVContainer(parent4, "Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    constraint.addAttribute("Name");

    DBVEntity src = new DBVEntity(container2, "Name", "Description Column Names");
    src.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvEntity.copyFrom(src, new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent3).getDataSource();
  }

  /**
   * Test {@link DBVEntity#copyFrom(DBVEntity, DBVModel)} with {@code src}, {@code targetModel}.
   *
   * <p>Method under test: {@link DBVEntity#copyFrom(DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.copyFrom(DBVEntity, DBVModel)"})
  public void testCopyFromWithSrcTargetModel6() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("42");

    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(foreignKey);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity src =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    src.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvEntity.copyFrom(src, new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent3).getDataSource();
  }

  /**
   * Test {@link DBVEntity#copyFrom(DBVEntity, DBVModel)} with {@code src}, {@code targetModel}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#copyFrom(DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.copyFrom(DBVEntity, DBVModel)"})
  public void testCopyFromWithSrcTargetModel_givenArrayList() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(new DBVEntityForeignKey(entity));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity src =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    src.setColorOverrides(new ArrayList<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    src.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvEntity.copyFrom(src, new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent3).getDataSource();
  }

  /**
   * Test {@link DBVEntity#copyFrom(DBVEntity, DBVModel)} with {@code src}, {@code targetModel}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#copyFrom(DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.copyFrom(DBVEntity, DBVModel)"})
  public void testCopyFromWithSrcTargetModel_givenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(new DBVEntityForeignKey(entity));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity src =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    src.addForeignKey(new DBVEntityForeignKey(entity2));
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    src.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvEntity.copyFrom(src, new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent3, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link DBVEntity#copyFrom(DBVEntity, DBVModel)} with {@code src}, {@code targetModel}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#copyFrom(DBVEntity, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.copyFrom(DBVEntity, DBVModel)"})
  public void testCopyFromWithSrcTargetModel_givenName() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(new DBVEntityForeignKey(entity));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer2).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());

    DBVEntity src =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    src.setProperty("Name", DBPEvent.RENAME);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    src.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvEntity.copyFrom(src, new DBVModel(dataSourceContainer));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource2).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dbpDataSourceContainer2).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent3).getDataSource();
  }

  /**
   * Test {@link DBVEntity#addConstraint(DBVEntityConstraint, boolean)} with {@code constraint},
   * {@code reflect}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addConstraint(DBVEntityConstraint, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntity.addConstraint(DBVEntityConstraint, boolean)"})
  public void testAddConstraintWithConstraintReflect_givenDBPDataSourceGetContainerReturnNull() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    boolean actualAddConstraintResult = dbvEntity.addConstraint(constraint, true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
    List<DBVEntityConstraint> constraints = dbvEntity.getConstraints();
    assertEquals(1, constraints.size());
    assertTrue(actualAddConstraintResult);
    assertSame(constraint, constraints.get(0));
    assertSame(constraint, dbvEntity.getBestIdentifier());
  }

  /**
   * Test {@link DBVEntity#addConstraint(DBVEntityConstraint, boolean)} with {@code constraint},
   * {@code reflect}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addConstraint(DBVEntityConstraint, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntity.addConstraint(DBVEntityConstraint, boolean)"})
  public void testAddConstraintWithConstraintReflect_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    boolean actualAddConstraintResult = dbvEntity.addConstraint(constraint, true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    List<DBVEntityConstraint> constraints = dbvEntity.getConstraints();
    assertEquals(1, constraints.size());
    assertTrue(actualAddConstraintResult);
    assertSame(constraint, constraints.get(0));
    assertSame(constraint, dbvEntity.getBestIdentifier());
  }

  /**
   * Test {@link DBVEntity#addConstraint(DBVEntityConstraint, boolean)} with {@code constraint},
   * {@code reflect}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addConstraint(DBVEntityConstraint, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntity.addConstraint(DBVEntityConstraint, boolean)"})
  public void testAddConstraintWithConstraintReflect_thenThrowIllegalStateException() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalStateException());
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dbvEntity.addConstraint(constraint, true));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntity#addConstraint(DBVEntityConstraint, boolean)} with {@code constraint},
   * {@code reflect}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addConstraint(DBVEntityConstraint, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntity.addConstraint(DBVEntityConstraint, boolean)"})
  public void testAddConstraintWithConstraintReflect_whenFalse() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    boolean actualAddConstraintResult = dbvEntity.addConstraint(constraint, false);

    // Assert
    List<DBVEntityConstraint> constraints = dbvEntity.getConstraints();
    assertEquals(1, constraints.size());
    assertTrue(actualAddConstraintResult);
    assertSame(constraint, constraints.get(0));
    assertSame(constraint, dbvEntity.getBestIdentifier());
  }

  /**
   * Test {@link DBVEntity#addConstraint(DBVEntityConstraint)} with {@code constraint}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addConstraint(DBVEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntity.addConstraint(DBVEntityConstraint)"})
  public void testAddConstraintWithConstraint_givenDBPDataSourceGetContainerReturnNull() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    boolean actualAddConstraintResult = dbvEntity.addConstraint(constraint);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
    List<DBVEntityConstraint> constraints = dbvEntity.getConstraints();
    assertEquals(1, constraints.size());
    assertTrue(actualAddConstraintResult);
    assertSame(constraint, constraints.get(0));
    assertSame(constraint, dbvEntity.getBestIdentifier());
  }

  /**
   * Test {@link DBVEntity#addConstraint(DBVEntityConstraint)} with {@code constraint}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addConstraint(DBVEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntity.addConstraint(DBVEntityConstraint)"})
  public void testAddConstraintWithConstraint_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act
    boolean actualAddConstraintResult = dbvEntity.addConstraint(constraint);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    List<DBVEntityConstraint> constraints = dbvEntity.getConstraints();
    assertEquals(1, constraints.size());
    assertTrue(actualAddConstraintResult);
    assertSame(constraint, constraints.get(0));
    assertSame(constraint, dbvEntity.getBestIdentifier());
  }

  /**
   * Test {@link DBVEntity#addConstraint(DBVEntityConstraint)} with {@code constraint}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addConstraint(DBVEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVEntity.addConstraint(DBVEntityConstraint)"})
  public void testAddConstraintWithConstraint_thenThrowIllegalStateException() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalStateException());
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dbvEntity.addConstraint(constraint));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntity#getAssociations(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAssociations(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getAssociations(DBRProgressMonitor)"})
  public void testGetAssociations_thenReturnNull() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertNull(dbvEntity.getAssociations(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DBVEntity#getAssociations(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAssociations(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getAssociations(DBRProgressMonitor)"})
  public void testGetAssociations_thenReturnSizeIsOne() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId(null);

    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(foreignKey);

    // Act
    List<DBVEntityForeignKey> actualAssociations = dbvEntity.getAssociations(null);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertEquals(1, actualAssociations.size());
    assertSame(foreignKey, actualAssociations.get(0));
  }

  /**
   * Test {@link DBVEntity#getAssociations(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getAssociations(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getAssociations(DBRProgressMonitor)"})
  public void testGetAssociations_thenThrowIllegalStateException() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenThrow(new IllegalStateException());
    DBVContainer parent4 = new DBVContainer(parent3, "%2F", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(
            new DBVContainer(new DBVContainer(parent4, "%2F"), "Name"),
            "Name",
            "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("foo");

    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(foreignKey);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> dbvEntity.getAssociations(new LoggingProgressMonitor()));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    verify(parent3).getDataSource();
  }

  /**
   * Test {@link DBVEntity#getForeignKeys()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getForeignKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getForeignKeys()"})
  public void testGetForeignKeys_thenReturnEmpty() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertTrue(dbvEntity.getForeignKeys().isEmpty());
  }

  /**
   * Test {@link DBVEntity#getForeignKeys()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getForeignKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getForeignKeys()"})
  public void testGetForeignKeys_thenReturnSizeIsOne() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    dbvEntity.addForeignKey(foreignKey);

    // Act
    List<DBVEntityForeignKey> actualForeignKeys = dbvEntity.getForeignKeys();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    assertEquals(1, actualForeignKeys.size());
    assertSame(foreignKey, actualForeignKeys.get(0));
  }

  /**
   * Test {@link DBVEntity#addForeignKey(DBVEntityForeignKey)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addForeignKey(DBVEntityForeignKey)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.addForeignKey(DBVEntityForeignKey)"})
  public void testAddForeignKey_givenDBPDataSourceGetContainerReturnNull() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);

    // Act
    dbvEntity.addForeignKey(foreignKey);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
    List<DBVEntityForeignKey> foreignKeys = dbvEntity.getForeignKeys();
    assertEquals(1, foreignKeys.size());
    assertSame(foreignKey, foreignKeys.get(0));
  }

  /**
   * Test {@link DBVEntity#addForeignKey(DBVEntityForeignKey)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addForeignKey(DBVEntityForeignKey)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.addForeignKey(DBVEntityForeignKey)"})
  public void testAddForeignKey_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);

    // Act
    dbvEntity.addForeignKey(foreignKey);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    List<DBVEntityForeignKey> foreignKeys = dbvEntity.getForeignKeys();
    assertEquals(1, foreignKeys.size());
    assertSame(foreignKey, foreignKeys.get(0));
  }

  /**
   * Test {@link DBVEntity#addForeignKey(DBVEntityForeignKey)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#addForeignKey(DBVEntityForeignKey)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.addForeignKey(DBVEntityForeignKey)"})
  public void testAddForeignKey_thenThrowIllegalStateException() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalStateException());
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbvEntity.addForeignKey(new DBVEntityForeignKey(entity)));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection)} with {@code attributes}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection)"})
  public void testGetDescriptionColumnsWithAttributes() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");

    // Act
    Collection<DBSAttributeBase> actualDescriptionColumns = dbvEntity.getDescriptionColumns(null);

    // Assert
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection)} with {@code attributes}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection)"})
  public void testGetDescriptionColumnsWithAttributes2() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", (String) null);

    // Act
    Collection<DBSAttributeBase> actualDescriptionColumns = dbvEntity.getDescriptionColumns(null);

    // Assert
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection)} with {@code attributes}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection)"})
  public void testGetDescriptionColumnsWithAttributes3() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act
    Collection<DBSAttributeBase> actualDescriptionColumns = dbvEntity.getDescriptionColumns(null);

    // Assert
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection, String)} with {@code attributes},
   * {@code descColumns}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection, String)"})
  public void testGetDescriptionColumnsWithAttributesDescColumns() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalStateException());
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, parent2, copy);

    ArrayList<DBSAttributeBase> attributes = new ArrayList<>();
    attributes.add(dbvEntityAttribute);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> DBVEntity.getDescriptionColumns(attributes, "Desc Columns"));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection, String)} with {@code attributes},
   * {@code descColumns}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection, String)"})
  public void testGetDescriptionColumnsWithAttributesDescColumns2() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalStateException());
    DBVContainer parent2 = new DBVContainer(parent, ",");
    DBVContainer container = new DBVContainer(parent2, ",", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent3 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, parent3, copy);

    ArrayList<DBSAttributeBase> attributes = new ArrayList<>();
    attributes.add(dbvEntityAttribute);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> DBVEntity.getDescriptionColumns(attributes, "Desc Columns"));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection, String)} with {@code attributes},
   * {@code descColumns}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getSQLDialect()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection, String)"})
  public void testGetDescriptionColumnsWithAttributesDescColumns_thenCallsGetSQLDialect() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, parent2, copy);

    ArrayList<DBSAttributeBase> attributes = new ArrayList<>();
    attributes.add(dbvEntityAttribute);

    // Act
    Collection<DBSAttributeBase> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(attributes, "Desc Columns");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection, String)} with {@code attributes},
   * {@code descColumns}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getSQLDialect()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection, String)"})
  public void testGetDescriptionColumnsWithAttributesDescColumns_thenCallsGetSQLDialect2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, ",");
    DBVContainer container = new DBVContainer(parent2, ",", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent3 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, parent3, copy);

    ArrayList<DBSAttributeBase> attributes = new ArrayList<>();
    attributes.add(dbvEntityAttribute);

    // Act
    Collection<DBSAttributeBase> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(attributes, "Desc Columns");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection, String)} with {@code attributes},
   * {@code descColumns}.
   *
   * <ul>
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection, String)"})
  public void testGetDescriptionColumnsWithAttributesDescColumns_thenReturnArrayList() {
    // Arrange
    ArrayList<DBSAttributeBase> attributes = new ArrayList<>();

    // Act
    Collection<DBSAttributeBase> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(attributes, "Desc Columns");

    // Assert
    assertEquals(attributes, actualDescriptionColumns);
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection, String)} with {@code attributes},
   * {@code descColumns}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection, String)"})
  public void testGetDescriptionColumnsWithAttributesDescColumns_whenEmptyString() {
    // Arrange and Act
    Collection<DBSAttributeBase> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(null, "");

    // Assert
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection, String)} with {@code attributes},
   * {@code descColumns}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection, String)"})
  public void testGetDescriptionColumnsWithAttributesDescColumns_whenNull_thenReturnList() {
    // Arrange and Act
    Collection<DBSAttributeBase> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(null, "Desc Columns");

    // Assert
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(Collection)} with {@code attributes}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(Collection)"})
  public void testGetDescriptionColumnsWithAttributes_whenArrayList_thenReturnArrayList() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    ArrayList<DBSAttributeBase> attributes = new ArrayList<>();

    // Act
    Collection<DBSAttributeBase> actualDescriptionColumns =
        dbvEntity.getDescriptionColumns(attributes);

    // Assert
    assertEquals(attributes, actualDescriptionColumns);
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)} with {@code
   * monitor}, {@code entity}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity)"})
  public void testGetDescriptionColumnsWithMonitorEntity() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "org.jkiss.dbeaver.model", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent4, "org.jkiss.dbeaver.model"));

    DBVContainer parent5 = mock(DBVContainer.class);
    when(parent5.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer parent6 = new DBVContainer(parent5, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent6, "Name"), "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        dbvEntity.getDescriptionColumns(monitor, entity);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent5).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)} with {@code
   * monitor}, {@code entity}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity)"})
  public void testGetDescriptionColumnsWithMonitorEntity2() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        dbvEntity.getDescriptionColumns(monitor, entity);

    // Assert
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)} with {@code
   * monitor}, {@code entity}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity)"})
  public void testGetDescriptionColumnsWithMonitorEntity3() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent3 = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent3, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        dbvEntity.getDescriptionColumns(monitor, entity);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)} with {@code
   * monitor}, {@code entity}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity)"})
  public void testGetDescriptionColumnsWithMonitorEntity4() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        dbvEntity.getDescriptionColumns(monitor, entity);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)} with {@code
   * monitor}, {@code entity}, {@code descColumns}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)"
  })
  public void testGetDescriptionColumnsWithMonitorEntityDescColumns() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(monitor, entity, "Desc Columns");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)} with {@code
   * monitor}, {@code entity}, {@code descColumns}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)"
  })
  public void testGetDescriptionColumnsWithMonitorEntityDescColumns2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(monitor, entity, "Desc Columns");

    // Assert
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)} with {@code
   * monitor}, {@code entity}, {@code descColumns}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)"
  })
  public void testGetDescriptionColumnsWithMonitorEntityDescColumns3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(monitor, entity, "Desc Columns");

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)} with {@code
   * monitor}, {@code entity}, {@code descColumns}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)"
  })
  public void testGetDescriptionColumnsWithMonitorEntityDescColumns4() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(monitor, entity, "Desc Columns");

    // Assert
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)} with {@code
   * monitor}, {@code entity}, {@code descColumns}.
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)"
  })
  public void testGetDescriptionColumnsWithMonitorEntityDescColumns5() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        DBVEntity.getDescriptionColumns(monitor, entity, "Desc Columns");

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)} with {@code
   * monitor}, {@code entity}, {@code descColumns}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity, String)"
  })
  public void testGetDescriptionColumnsWithMonitorEntityDescColumns_thenThrowDBException()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertThrows(
        DBException.class, () -> DBVEntity.getDescriptionColumns(monitor, entity, "Desc Columns"));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)} with {@code
   * monitor}, {@code entity}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity)"})
  public void testGetDescriptionColumnsWithMonitorEntity_thenThrowDBException() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertThrows(DBException.class, () -> dbvEntity.getDescriptionColumns(monitor, entity));
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)} with {@code
   * monitor}, {@code entity}.
   *
   * <ul>
   *   <li>When {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumns(DBRProgressMonitor, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBVEntity.getDescriptionColumns(DBRProgressMonitor, DBSEntity)"})
  public void testGetDescriptionColumnsWithMonitorEntity_whenDBVModelWithIdIs42AndMapIsHashMap()
      throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVModel parent3 = new DBVModel("42", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent3, "Name"), "Name", "Description Column Names");

    // Act
    Collection<DBSEntityAttribute> actualDescriptionColumns =
        dbvEntity.getDescriptionColumns(monitor, entity);

    // Assert
    assertTrue(actualDescriptionColumns instanceof List);
    assertTrue(actualDescriptionColumns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)}.
   *
   * <p>Method under test: {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBVEntity.getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)"
  })
  public void testGetDefaultDescriptionColumn() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    DBSContextBoundAttribute keyColumn = mock(DBSContextBoundAttribute.class);
    when(keyColumn.getParentObject()).thenReturn(dbvEntity);

    // Act
    String actualDefaultDescriptionColumn =
        DBVEntity.getDefaultDescriptionColumn(monitor, keyColumn);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(keyColumn).getParentObject();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertNull(actualDefaultDescriptionColumn);
  }

  /**
   * Test {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)}.
   *
   * <p>Method under test: {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBVEntity.getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)"
  })
  public void testGetDefaultDescriptionColumn2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    DBSContextBoundAttribute keyColumn = mock(DBSContextBoundAttribute.class);
    when(keyColumn.getParentObject()).thenReturn(dbvEntity);

    // Act
    String actualDefaultDescriptionColumn =
        DBVEntity.getDefaultDescriptionColumn(monitor, keyColumn);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(keyColumn).getParentObject();
    assertNull(actualDefaultDescriptionColumn);
  }

  /**
   * Test {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVModel#DBVModel(DBPDataSourceContainer)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBVEntity.getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)"
  })
  public void testGetDefaultDescriptionColumn_givenDBVContainerWithParentIsDBVModelAndName()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    DBSContextBoundAttribute keyColumn = mock(DBSContextBoundAttribute.class);
    when(keyColumn.getParentObject()).thenReturn(dbvEntity);

    // Act
    String actualDefaultDescriptionColumn =
        DBVEntity.getDefaultDescriptionColumn(monitor, keyColumn);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(keyColumn).getParentObject();
    assertNull(actualDefaultDescriptionColumn);
  }

  /**
   * Test {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@code null}
   *       and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBVEntity.getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)"
  })
  public void testGetDefaultDescriptionColumn_givenDBVContainerWithParentIsNullAndName()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSContextBoundAttribute keyColumn = mock(DBSContextBoundAttribute.class);
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    when(keyColumn.getParentObject()).thenReturn(dbvEntity);

    // Act
    String actualDefaultDescriptionColumn =
        DBVEntity.getDefaultDescriptionColumn(monitor, keyColumn);

    // Assert
    verify(keyColumn).getParentObject();
    assertNull(actualDefaultDescriptionColumn);
  }

  /**
   * Test {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBVEntity.getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)"
  })
  public void testGetDefaultDescriptionColumn_givenDBVModelWithIdIs42AndMapIsHashMap()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSContextBoundAttribute keyColumn = mock(DBSContextBoundAttribute.class);
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");
    when(keyColumn.getParentObject()).thenReturn(dbvEntity);

    // Act
    String actualDefaultDescriptionColumn =
        DBVEntity.getDefaultDescriptionColumn(monitor, keyColumn);

    // Assert
    verify(keyColumn).getParentObject();
    assertNull(actualDefaultDescriptionColumn);
  }

  /**
   * Test {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBVEntity.getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)"
  })
  public void testGetDefaultDescriptionColumn_thenThrowDBException() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBSContextBoundAttribute keyColumn = mock(DBSContextBoundAttribute.class);
    when(keyColumn.getParentObject()).thenReturn(dbvEntity);

    // Act and Assert
    assertThrows(
        DBException.class, () -> DBVEntity.getDefaultDescriptionColumn(monitor, keyColumn));
    verify(keyColumn).getParentObject();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDefaultDescriptionColumn(DBRProgressMonitor,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBVEntity.getDefaultDescriptionColumn(DBRProgressMonitor, DBSEntityAttribute)"
  })
  public void testGetDefaultDescriptionColumn_thenThrowIllegalStateException() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSContextBoundAttribute keyColumn = mock(DBSContextBoundAttribute.class);
    when(keyColumn.getParentObject()).thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> DBVEntity.getDefaultDescriptionColumn(monitor, keyColumn));
    verify(keyColumn).getParentObject();
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumnPatterns(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumnPatterns(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getDescriptionColumnPatterns(DBPPreferenceStore)"})
  public void testGetDescriptionColumnPatterns_givenEmptyString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");

    // Act
    List<String> actualDescriptionColumnPatterns = DBVEntity.getDescriptionColumnPatterns(store);

    // Assert
    verify(store).getString("resultset.reference.value.description.column.patterns");
    assertTrue(actualDescriptionColumnPatterns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumnPatterns(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then calls {@link DBPPreferenceStore#getString(String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumnPatterns(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getDescriptionColumnPatterns(DBPPreferenceStore)"})
  public void testGetDescriptionColumnPatterns_givenNull_thenCallsGetString() {
    // Arrange
    DBPPreferenceStore store = mock(DBPPreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn(null);

    // Act
    List<String> actualDescriptionColumnPatterns = DBVEntity.getDescriptionColumnPatterns(store);

    // Assert
    verify(store).getString("resultset.reference.value.description.column.patterns");
    assertTrue(actualDescriptionColumnPatterns.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDescriptionColumnPatterns(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDescriptionColumnPatterns(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getDescriptionColumnPatterns(DBPPreferenceStore)"})
  public void testGetDescriptionColumnPatterns_givenString_thenReturnSizeIsOne() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");

    // Act
    List<String> actualDescriptionColumnPatterns = DBVEntity.getDescriptionColumnPatterns(store);

    // Assert
    verify(store).getString("resultset.reference.value.description.column.patterns");
    assertEquals(1, actualDescriptionColumnPatterns.size());
    assertEquals("String", actualDescriptionColumnPatterns.get(0));
  }

  /**
   * Test {@link DBVEntity#getColorOverrides()}.
   *
   * <p>Method under test: {@link DBVEntity#getColorOverrides()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getColorOverrides()"})
  public void testGetColorOverrides() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertTrue(dbvEntity.getColorOverrides().isEmpty());
  }

  /**
   * Test {@link DBVEntity#getColorOverrides()}.
   *
   * <p>Method under test: {@link DBVEntity#getColorOverrides()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getColorOverrides()"})
  public void testGetColorOverrides2() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvEntity.setColorOverrides(new ArrayList<>());

    // Act and Assert
    assertTrue(dbvEntity.getColorOverrides().isEmpty());
  }

  /**
   * Test {@link DBVEntity#getColorOverrides(String)} with {@code String}.
   *
   * <p>Method under test: {@link DBVEntity#getColorOverrides(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getColorOverrides(String)"})
  public void testGetColorOverridesWithString() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertTrue(dbvEntity.getColorOverrides("Attr Name").isEmpty());
  }

  /**
   * Test {@link DBVEntity#getColorOverrides(String)} with {@code String}.
   *
   * <p>Method under test: {@link DBVEntity#getColorOverrides(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getColorOverrides(String)"})
  public void testGetColorOverridesWithString2() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride source =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    DBVColorOverride dbvColorOverride = new DBVColorOverride(source);
    dbvColorOverride.setAttributeName(null);

    ArrayList<DBVColorOverride> colorOverrides = new ArrayList<>();
    colorOverrides.add(dbvColorOverride);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvEntity.setColorOverrides(colorOverrides);

    // Act and Assert
    assertTrue(dbvEntity.getColorOverrides("Attr Name").isEmpty());
  }

  /**
   * Test {@link DBVEntity#getColorOverrides(String)} with {@code String}.
   *
   * <p>Method under test: {@link DBVEntity#getColorOverrides(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getColorOverrides(String)"})
  public void testGetColorOverridesWithString3() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride source =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    DBVColorOverride dbvColorOverride = new DBVColorOverride(source);
    dbvColorOverride.setAttributeName("foo");

    ArrayList<DBVColorOverride> colorOverrides = new ArrayList<>();
    colorOverrides.add(dbvColorOverride);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvEntity.setColorOverrides(colorOverrides);

    // Act and Assert
    assertTrue(dbvEntity.getColorOverrides(null).isEmpty());
  }

  /**
   * Test {@link DBVEntity#getColorOverrides(String)} with {@code String}.
   *
   * <p>Method under test: {@link DBVEntity#getColorOverrides(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getColorOverrides(String)"})
  public void testGetColorOverridesWithString4() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride source =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    DBVColorOverride dbvColorOverride = new DBVColorOverride(source);
    dbvColorOverride.setAttributeName("foo");

    ArrayList<DBVColorOverride> colorOverrides = new ArrayList<>();
    colorOverrides.add(dbvColorOverride);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvEntity.setColorOverrides(colorOverrides);

    // Act and Assert
    assertTrue(dbvEntity.getColorOverrides("Attr Name").isEmpty());
  }

  /**
   * Test {@link DBVEntity#getColorOverrides(String)} with {@code String}.
   *
   * <ul>
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getColorOverrides(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getColorOverrides(String)"})
  public void testGetColorOverridesWithString_thenReturnArrayList() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride source =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    DBVColorOverride dbvColorOverride = new DBVColorOverride(source);
    dbvColorOverride.setAttributeName(null);

    ArrayList<DBVColorOverride> colorOverrides = new ArrayList<>();
    colorOverrides.add(dbvColorOverride);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvEntity.setColorOverrides(colorOverrides);

    // Act and Assert
    assertEquals(colorOverrides, dbvEntity.getColorOverrides(null));
  }

  /**
   * Test {@link DBVEntity#getColorOverrides(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getColorOverrides(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntity.getColorOverrides(String)"})
  public void testGetColorOverridesWithString_whenNull_thenReturnEmpty() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvEntity.setColorOverrides(new ArrayList<>());

    // Act and Assert
    assertTrue(dbvEntity.getColorOverrides(null).isEmpty());
  }

  /**
   * Test {@link DBVEntity#addColorOverride(DBVColorOverride)}.
   *
   * <p>Method under test: {@link DBVEntity#addColorOverride(DBVColorOverride)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.addColorOverride(DBVColorOverride)"})
  public void testAddColorOverride() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    // Act
    dbvEntity.addColorOverride(color);

    // Assert
    List<DBVColorOverride> colorOverrides = dbvEntity.getColorOverrides();
    assertEquals(1, colorOverrides.size());
    List<DBVEntityAttribute> customAttributes = dbvEntity.getCustomAttributes();
    assertTrue(customAttributes.isEmpty());
    assertSame(color, colorOverrides.get(0));
    assertSame(customAttributes, dbvEntity.getForeignKeys());
  }

  /**
   * Test {@link DBVEntity#addColorOverride(DBVColorOverride)}.
   *
   * <p>Method under test: {@link DBVEntity#addColorOverride(DBVColorOverride)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.addColorOverride(DBVColorOverride)"})
  public void testAddColorOverride2() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    ArrayList<DBVColorOverride> colorOverrides = new ArrayList<>();
    dbvEntity.setColorOverrides(colorOverrides);
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    // Act
    dbvEntity.addColorOverride(color);

    // Assert
    List<DBVColorOverride> colorOverrides2 = dbvEntity.getColorOverrides();
    assertEquals(1, colorOverrides2.size());
    List<DBVEntityAttribute> customAttributes = dbvEntity.getCustomAttributes();
    assertTrue(customAttributes.isEmpty());
    assertSame(colorOverrides, colorOverrides2);
    assertSame(color, colorOverrides2.get(0));
    assertSame(customAttributes, dbvEntity.getForeignKeys());
  }

  /**
   * Test {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <p>Method under test: {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBVEntity.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    String actualFullyQualifiedName = dbvEntity.getFullyQualifiedName(DBPEvaluationContext.UI);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("\"Name\"", actualFullyQualifiedName);
  }

  /**
   * Test {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVModel#DBVModel(DBPDataSourceContainer)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBVEntity.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_givenDBVContainerWithParentIsDBVModelAndName() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    String actualFullyQualifiedName = dbvEntity.getFullyQualifiedName(DBPEvaluationContext.UI);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("\"Name\".\"Name\"", actualFullyQualifiedName);
  }

  /**
   * Test {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Then return {@code 42A42}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBVEntity.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_thenReturn42a42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act
    String actualFullyQualifiedName = dbvEntity.getFullyQualifiedName(DBPEvaluationContext.UI);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getStructSeparator();
    verify(parent).getDataSource();
    assertEquals("42A42", actualFullyQualifiedName);
  }

  /**
   * Test {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Then return {@code "Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBVEntity.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_thenReturnName() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    String actualFullyQualifiedName = dbvEntity.getFullyQualifiedName(DBPEvaluationContext.UI);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("\"Name\"", actualFullyQualifiedName);
  }

  /**
   * Test {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Then return {@code "Name"."Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBVEntity.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_thenReturnNameName() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act
    String actualFullyQualifiedName = dbvEntity.getFullyQualifiedName(DBPEvaluationContext.UI);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"Name\".\"Name\"", actualFullyQualifiedName);
  }

  /**
   * Test {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Then return {@code Name.Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBVEntity.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_thenReturnNameName2() {
    // Arrange
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertEquals("Name.Name", dbvEntity.getFullyQualifiedName(DBPEvaluationContext.UI));
  }

  /**
   * Test {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBVEntity.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_thenThrowIllegalStateException() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new IllegalStateException());
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbvEntity.getFullyQualifiedName(DBPEvaluationContext.UI));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntity#bindEntity(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#bindEntity(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.bindEntity(DBRProgressMonitor)"})
  public void testBindEntity_thenDoesNotThrow() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act and Assert
    dbvEntity.bindEntity(new LoggingProgressMonitor());
  }

  /**
   * Test {@link DBVEntity#bindEntity(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#bindEntity(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntity.bindEntity(DBRProgressMonitor)"})
  public void testBindEntity_thenThrowIllegalStateException() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getDataSource()).thenThrow(new IllegalStateException());
    DBVContainer parent4 = new DBVContainer(parent3, "%2F", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(
            new DBVContainer(new DBVContainer(parent4, "%2F"), "Name"),
            "Name",
            "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("foo");

    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    dbvEntity.addForeignKey(foreignKey);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> dbvEntity.bindEntity(new LoggingProgressMonitor()));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(parent).getDataSource();
    verify(parent3).getDataSource();
  }

  /**
   * Test {@link DBVEntity#getAdapter(Class)}.
   *
   * <p>Method under test: {@link DBVEntity#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBVEntity.getAdapter(Class)"})
  public void testGetAdapter() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    Class<Object> adapter = Object.class;

    // Act and Assert
    assertNull(dbvEntity.getAdapter(adapter));
  }

  /**
   * Test {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List,
   * boolean, boolean)}.
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor,
   * DBSEntityAttribute, List, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDictionaryAccessor DBVEntity.getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List, boolean, boolean)"
  })
  public void testGetDictionaryAccessor() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent5 = mock(DBVEntityAttribute.class);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute parent6 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent6, copy);

    DBVEntityAttribute parent7 = new DBVEntityAttribute(entity2, parent5, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent7, "Name");

    // Act
    dbvEntity.getDictionaryAccessor(monitor, keyColumn, new ArrayList<>(), true, true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List,
   * boolean, boolean)}.
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor,
   * DBSEntityAttribute, List, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDictionaryAccessor DBVEntity.getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List, boolean, boolean)"
  })
  public void testGetDictionaryAccessor2() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent = mock(DBVEntityAttribute.class);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent2, copy);

    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity2, parent, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent3, "Name");

    // Act
    dbvEntity.getDictionaryAccessor(monitor, keyColumn, new ArrayList<>(), true, true);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List,
   * boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVModel#DBVModel(DBPDataSourceContainer)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor,
   * DBSEntityAttribute, List, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDictionaryAccessor DBVEntity.getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List, boolean, boolean)"
  })
  public void testGetDictionaryAccessor_givenDBVContainerWithParentIsDBVModelAndName()
      throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent2 = mock(DBVEntityAttribute.class);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute parent3 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent3, copy);

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity2, parent2, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent4, "Name");

    // Act
    dbvEntity.getDictionaryAccessor(monitor, keyColumn, new ArrayList<>(), true, true);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List,
   * boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@code null}
   *       and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor,
   * DBSEntityAttribute, List, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDictionaryAccessor DBVEntity.getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List, boolean, boolean)"
  })
  public void testGetDictionaryAccessor_givenDBVContainerWithParentIsNullAndName()
      throws DBException {
    // Arrange
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent = mock(DBVEntityAttribute.class);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent2, copy);

    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity2, parent, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent3, "Name");

    // Act
    dbvEntity.getDictionaryAccessor(monitor, keyColumn, new ArrayList<>(), true, true);

    // Assert
  }

  /**
   * Test {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List,
   * boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor,
   * DBSEntityAttribute, List, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDictionaryAccessor DBVEntity.getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List, boolean, boolean)"
  })
  public void testGetDictionaryAccessor_givenDBVModelWithIdIs42AndMapIsHashMap()
      throws DBException {
    // Arrange
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent2 = mock(DBVEntityAttribute.class);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute parent3 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent3, copy);

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity2, parent2, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent4, "Name");

    // Act
    dbvEntity.getDictionaryAccessor(monitor, keyColumn, new ArrayList<>(), true, true);

    // Assert
  }

  /**
   * Test {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List,
   * boolean, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryAccessor(DBRProgressMonitor,
   * DBSEntityAttribute, List, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSDictionaryAccessor DBVEntity.getDictionaryAccessor(DBRProgressMonitor, DBSEntityAttribute, List, boolean, boolean)"
  })
  public void testGetDictionaryAccessor_thenThrowDBException() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent3 = mock(DBVEntityAttribute.class);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute parent4 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent4, copy);

    DBVEntityAttribute parent5 = new DBVEntityAttribute(entity2, parent3, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent5, "Name");

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dbvEntity.getDictionaryAccessor(monitor, keyColumn, new ArrayList<>(), true, true));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object,
   * String, List, boolean, boolean, boolean, int, int)}.
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor,
   * DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)"
  })
  public void testGetDictionaryEnumeration() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent5 = mock(DBVEntityAttribute.class);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute parent6 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent6, copy);

    DBVEntityAttribute parent7 = new DBVEntityAttribute(entity2, parent5, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent7, "Name");

    // Act
    List<DBDLabelValuePair> actualDictionaryEnumeration =
        dbvEntity.getDictionaryEnumeration(
            monitor,
            keyColumn,
            DBPEvent.RENAME,
            "Search Text",
            new ArrayList<>(),
            true,
            true,
            true,
            2,
            3);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualDictionaryEnumeration.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object,
   * String, List, boolean, boolean, boolean, int, int)}.
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor,
   * DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)"
  })
  public void testGetDictionaryEnumeration2() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent = mock(DBVEntityAttribute.class);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent2, copy);

    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity2, parent, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent3, "Name");

    // Act
    List<DBDLabelValuePair> actualDictionaryEnumeration =
        dbvEntity.getDictionaryEnumeration(
            monitor,
            keyColumn,
            DBPEvent.RENAME,
            "Search Text",
            new ArrayList<>(),
            true,
            true,
            true,
            2,
            3);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualDictionaryEnumeration.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object,
   * String, List, boolean, boolean, boolean, int, int)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVModel#DBVModel(DBPDataSourceContainer)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor,
   * DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)"
  })
  public void testGetDictionaryEnumeration_givenDBVContainerWithParentIsDBVModelAndName()
      throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent2 = mock(DBVEntityAttribute.class);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute parent3 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent3, copy);

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity2, parent2, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent4, "Name");

    // Act
    List<DBDLabelValuePair> actualDictionaryEnumeration =
        dbvEntity.getDictionaryEnumeration(
            monitor,
            keyColumn,
            DBPEvent.RENAME,
            "Search Text",
            new ArrayList<>(),
            true,
            true,
            true,
            2,
            3);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualDictionaryEnumeration.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object,
   * String, List, boolean, boolean, boolean, int, int)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@code null}
   *       and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor,
   * DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)"
  })
  public void testGetDictionaryEnumeration_givenDBVContainerWithParentIsNullAndName()
      throws DBException {
    // Arrange
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent = mock(DBVEntityAttribute.class);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent2, copy);

    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity2, parent, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent3, "Name");

    // Act and Assert
    assertTrue(
        dbvEntity
            .getDictionaryEnumeration(
                monitor,
                keyColumn,
                DBPEvent.RENAME,
                "Search Text",
                new ArrayList<>(),
                true,
                true,
                true,
                2,
                3)
            .isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object,
   * String, List, boolean, boolean, boolean, int, int)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor,
   * DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)"
  })
  public void testGetDictionaryEnumeration_givenDBVModelWithIdIs42AndMapIsHashMap()
      throws DBException {
    // Arrange
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent2 = mock(DBVEntityAttribute.class);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute parent3 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent3, copy);

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity2, parent2, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent4, "Name");

    // Act and Assert
    assertTrue(
        dbvEntity
            .getDictionaryEnumeration(
                monitor,
                keyColumn,
                DBPEvent.RENAME,
                "Search Text",
                new ArrayList<>(),
                true,
                true,
                true,
                2,
                3)
            .isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object,
   * String, List, boolean, boolean, boolean, int, int)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryEnumeration(DBRProgressMonitor,
   * DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryEnumeration(DBRProgressMonitor, DBSEntityAttribute, Object, String, List, boolean, boolean, boolean, int, int)"
  })
  public void testGetDictionaryEnumeration_thenThrowDBException() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityAttribute parent3 = mock(DBVEntityAttribute.class);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute parent4 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute copy =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity3, parent4, copy);

    DBVEntityAttribute parent5 = new DBVEntityAttribute(entity2, parent3, copy2);

    DBVEntityAttribute keyColumn = new DBVEntityAttribute(entity, parent5, "Name");

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            dbvEntity.getDictionaryEnumeration(
                monitor,
                keyColumn,
                DBPEvent.RENAME,
                "Search Text",
                new ArrayList<>(),
                true,
                true,
                true,
                2,
                3));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List, List, boolean,
   * boolean, boolean)}.
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List,
   * List, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryValues(DBRProgressMonitor, List, List, List, boolean, boolean, boolean)"
  })
  public void testGetDictionaryValues() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel dbvModel = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "org.jkiss.dbeaver.model", new HashMap<>());
    dbvModel.addContainer(new DBVContainer(parent2, "org.jkiss.dbeaver.model"));

    DBVContainer parent3 = mock(DBVContainer.class);
    when(parent3.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ArrayList<DBSEntityAttribute> keyColumns = new ArrayList<>();
    ArrayList<Object[]> keyValues = new ArrayList<>();

    // Act
    List<DBDLabelValuePair> actualDictionaryValues =
        dbvEntity.getDictionaryValues(
            monitor, keyColumns, keyValues, new ArrayList<>(), true, true, true);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent3).getRealContainer(isA(DBRProgressMonitor.class));
    assertTrue(actualDictionaryValues.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List, List, boolean,
   * boolean, boolean)}.
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List,
   * List, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryValues(DBRProgressMonitor, List, List, List, boolean, boolean, boolean)"
  })
  public void testGetDictionaryValues2() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ArrayList<DBSEntityAttribute> keyColumns = new ArrayList<>();
    ArrayList<Object[]> keyValues = new ArrayList<>();

    // Act
    List<DBDLabelValuePair> actualDictionaryValues =
        dbvEntity.getDictionaryValues(
            monitor, keyColumns, keyValues, new ArrayList<>(), true, true, true);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualDictionaryValues.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List, List, boolean,
   * boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVModel#DBVModel(DBPDataSourceContainer)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List,
   * List, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryValues(DBRProgressMonitor, List, List, List, boolean, boolean, boolean)"
  })
  public void testGetDictionaryValues_givenDBVContainerWithParentIsDBVModelAndName()
      throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ArrayList<DBSEntityAttribute> keyColumns = new ArrayList<>();
    ArrayList<Object[]> keyValues = new ArrayList<>();

    // Act
    List<DBDLabelValuePair> actualDictionaryValues =
        dbvEntity.getDictionaryValues(
            monitor, keyColumns, keyValues, new ArrayList<>(), true, true, true);

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualDictionaryValues.isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List, List, boolean,
   * boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@code null}
   *       and {@code Name}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List,
   * List, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryValues(DBRProgressMonitor, List, List, List, boolean, boolean, boolean)"
  })
  public void testGetDictionaryValues_givenDBVContainerWithParentIsNullAndName_thenReturnEmpty()
      throws DBException {
    // Arrange
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ArrayList<DBSEntityAttribute> keyColumns = new ArrayList<>();
    ArrayList<Object[]> keyValues = new ArrayList<>();

    // Act and Assert
    assertTrue(
        dbvEntity
            .getDictionaryValues(
                monitor, keyColumns, keyValues, new ArrayList<>(), true, true, true)
            .isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List, List, boolean,
   * boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List,
   * List, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryValues(DBRProgressMonitor, List, List, List, boolean, boolean, boolean)"
  })
  public void testGetDictionaryValues_givenDBVModelWithIdIs42AndMapIsHashMap_thenReturnEmpty()
      throws DBException {
    // Arrange
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ArrayList<DBSEntityAttribute> keyColumns = new ArrayList<>();
    ArrayList<Object[]> keyValues = new ArrayList<>();

    // Act and Assert
    assertTrue(
        dbvEntity
            .getDictionaryValues(
                monitor, keyColumns, keyValues, new ArrayList<>(), true, true, true)
            .isEmpty());
  }

  /**
   * Test {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List, List, boolean,
   * boolean, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntity#getDictionaryValues(DBRProgressMonitor, List, List,
   * List, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DBVEntity.getDictionaryValues(DBRProgressMonitor, List, List, List, boolean, boolean, boolean)"
  })
  public void testGetDictionaryValues_thenThrowDBException() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ArrayList<DBSEntityAttribute> keyColumns = new ArrayList<>();
    ArrayList<Object[]> keyValues = new ArrayList<>();

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            dbvEntity.getDictionaryValues(
                monitor, keyColumns, keyValues, new ArrayList<>(), true, true, true));
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
  }
}
