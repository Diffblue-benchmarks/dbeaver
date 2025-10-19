package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBVEntityAttributeDiffblueTest {
  /**
   * Test {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity, DBVEntityAttribute,
   * DBVEntityAttribute)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then return Properties size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity,
   * DBVEntityAttribute, DBVEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityAttribute.<init>(DBVEntity, DBVEntityAttribute, DBVEntityAttribute)"
  })
  public void testNewDBVEntityAttribute_givenName_thenReturnPropertiesSizeIsOne() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = mock(DBVEntity.class);
    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity3, null, "Name", new HashMap<>());

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity2, parent3, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity5 = mock(DBVEntity.class);
    DBVEntityAttribute parent5 = new DBVEntityAttribute(entity5, null, "Name", new HashMap<>());

    DBVEntityAttribute copy = new DBVEntityAttribute(entity4, parent5, "Name");
    copy.setProperty("Name", DBPEvent.RENAME);

    // Act
    DBVEntityAttribute actualDbvEntityAttribute = new DBVEntityAttribute(entity, parent4, copy);

    // Assert
    Map<String, Object> properties = actualDbvEntityAttribute.getProperties();
    assertEquals(1, properties.size());
    assertTrue(properties.containsKey("Name"));
    DBVEntity entity6 = actualDbvEntityAttribute.getEntity();
    Map<String, Object> properties2 = entity6.getProperties();
    assertTrue(properties2.isEmpty());
    assertTrue(actualDbvEntityAttribute.hasValuableData());
    DBVEntityAttribute parent6 = actualDbvEntityAttribute.getParent();
    assertSame(properties2, parent6.getProperties());
    assertSame(properties2, parent6.getParent().getProperties());
    DBVContainer container3 = entity6.getContainer();
    assertSame(properties2, container3.getParentObject().getProperties());
    assertSame(properties2, container3.getProperties());
    DBVEntity entity7 = parent6.getEntity();
    assertSame(properties2, entity7.getContainer().getProperties());
    assertSame(properties2, entity7.getProperties());
  }

  /**
   * Test {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity, DBVEntityAttribute,
   * DBVEntityAttribute)}.
   *
   * <ul>
   *   <li>Then return Children size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity,
   * DBVEntityAttribute, DBVEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityAttribute.<init>(DBVEntity, DBVEntityAttribute, DBVEntityAttribute)"
  })
  public void testNewDBVEntityAttribute_thenReturnChildrenSizeIsOne() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = mock(DBVEntity.class);
    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity3, null, "Name", new HashMap<>());

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity2, parent3, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity5 = mock(DBVEntity.class);
    DBVEntityAttribute parent5 = new DBVEntityAttribute(entity5, null, "Name", new HashMap<>());

    DBVEntityAttribute copy = new DBVEntityAttribute(entity4, parent5, "Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity6 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntity entity7 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity8 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVContainer container5 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity9 = new DBVEntity(container5, "Name", "Description Column Names");
    DBVEntityAttribute parent6 = new DBVEntityAttribute(entity9, null, "Name");
    DBVContainer container6 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity10 = new DBVEntity(container6, "Name", "Description Column Names");
    DBVEntityAttribute copy2 = new DBVEntityAttribute(entity10, null, "Name");

    DBVEntityAttribute copy3 = new DBVEntityAttribute(entity8, parent6, copy2);

    DBVEntityAttribute parent7 = new DBVEntityAttribute(entity7, null, copy3);

    DBVEntityAttribute child = new DBVEntityAttribute(entity6, parent7, "Name");
    copy.addChild(child);

    // Act
    DBVEntityAttribute actualDbvEntityAttribute = new DBVEntityAttribute(entity, parent4, copy);

    // Assert
    assertEquals(1, actualDbvEntityAttribute.getChildren().size());
  }

  /**
   * Test {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity, DBVEntityAttribute,
   * DBVEntityAttribute)}.
   *
   * <ul>
   *   <li>Then return TransformSettings is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity,
   * DBVEntityAttribute, DBVEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityAttribute.<init>(DBVEntity, DBVEntityAttribute, DBVEntityAttribute)"
  })
  public void testNewDBVEntityAttribute_thenReturnTransformSettingsIsNull() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = mock(DBVEntity.class);
    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity3, null, "Name", new HashMap<>());

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity2, parent3, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity5 = mock(DBVEntity.class);
    DBVEntityAttribute parent5 = new DBVEntityAttribute(entity5, null, "Name", new HashMap<>());

    DBVEntityAttribute copy = new DBVEntityAttribute(entity4, parent5, "Name");

    // Act
    DBVEntityAttribute actualDbvEntityAttribute = new DBVEntityAttribute(entity, parent4, copy);

    // Assert
    assertNull(actualDbvEntityAttribute.getTransformSettings());
    assertFalse(actualDbvEntityAttribute.hasValuableData());
    assertTrue(actualDbvEntityAttribute.getChildren().isEmpty());
    Map<String, Object> properties = actualDbvEntityAttribute.getProperties();
    assertTrue(properties.isEmpty());
    DBVEntityAttribute parent6 = actualDbvEntityAttribute.getParent();
    assertSame(properties, parent6.getProperties());
    assertSame(properties, parent6.getParent().getProperties());
    DBVEntity entity6 = actualDbvEntityAttribute.getEntity();
    DBVContainer container3 = entity6.getContainer();
    assertSame(properties, container3.getParentObject().getProperties());
    assertSame(properties, container3.getProperties());
    DBVEntity entity7 = parent6.getEntity();
    assertSame(properties, entity7.getContainer().getProperties());
    assertSame(properties, entity6.getProperties());
    assertSame(properties, entity7.getProperties());
  }

  /**
   * Test {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity, DBVEntityAttribute,
   * DBVEntityAttribute)}.
   *
   * <ul>
   *   <li>Then return TransformSettings TransformOptions is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity,
   * DBVEntityAttribute, DBVEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityAttribute.<init>(DBVEntity, DBVEntityAttribute, DBVEntityAttribute)"
  })
  public void testNewDBVEntityAttribute_thenReturnTransformSettingsTransformOptionsIsNull() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = mock(DBVEntity.class);
    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity3, null, "Name", new HashMap<>());

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity2, parent3, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity5 = mock(DBVEntity.class);
    DBVEntityAttribute parent5 = new DBVEntityAttribute(entity5, null, "Name", new HashMap<>());

    DBVEntityAttribute copy = new DBVEntityAttribute(entity4, parent5, "Name");
    copy.setTransformSettings(new DBVTransformSettings());

    // Act
    DBVEntityAttribute actualDbvEntityAttribute = new DBVEntityAttribute(entity, parent4, copy);

    // Assert
    DBVTransformSettings transformSettings = actualDbvEntityAttribute.getTransformSettings();
    assertNull(transformSettings.getCustomTransformer());
    assertNull(transformSettings.getTransformOptions());
    assertNull(transformSettings.getExcludedTransformers());
    assertNull(transformSettings.getIncludedTransformers());
    assertFalse(transformSettings.hasTransformOptions());
    assertFalse(transformSettings.hasValuableData());
  }

  /**
   * Test {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity, DBVEntityAttribute,
   * DBVEntityAttribute)}.
   *
   * <ul>
   *   <li>Then return TransformSettings TransformOptions size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityAttribute#DBVEntityAttribute(DBVEntity,
   * DBVEntityAttribute, DBVEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityAttribute.<init>(DBVEntity, DBVEntityAttribute, DBVEntityAttribute)"
  })
  public void testNewDBVEntityAttribute_thenReturnTransformSettingsTransformOptionsSizeIsOne() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = mock(DBVEntity.class);
    DBVEntityAttribute parent3 = new DBVEntityAttribute(entity3, null, "Name", new HashMap<>());

    DBVEntityAttribute parent4 = new DBVEntityAttribute(entity2, parent3, "Name");

    DBVTransformSettings transformSettings = new DBVTransformSettings();
    transformSettings.setTransformOption("Name", DBPEvent.RENAME);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity5 = mock(DBVEntity.class);
    DBVEntityAttribute parent5 = new DBVEntityAttribute(entity5, null, "Name", new HashMap<>());

    DBVEntityAttribute copy = new DBVEntityAttribute(entity4, parent5, "Name");
    copy.setTransformSettings(transformSettings);

    // Act
    DBVEntityAttribute actualDbvEntityAttribute = new DBVEntityAttribute(entity, parent4, copy);

    // Assert
    DBVTransformSettings transformSettings2 = actualDbvEntityAttribute.getTransformSettings();
    assertNull(transformSettings2.getCustomTransformer());
    assertNull(transformSettings2.getExcludedTransformers());
    assertNull(transformSettings2.getIncludedTransformers());
    Map<String, Object> transformOptions = transformSettings2.getTransformOptions();
    assertEquals(1, transformOptions.size());
    assertFalse(transformSettings2.hasValuableData());
    assertTrue(transformOptions.containsKey("Name"));
    assertTrue(transformSettings2.hasTransformOptions());
  }
}
