package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.navigator.DBNModel;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ERDEntityAttributeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDEntityAttribute#ERDEntityAttribute(DBSEntityAttribute, boolean)}
   *   <li>{@link ERDEntityAttribute#setAlias(String)}
   *   <li>{@link ERDEntityAttribute#setChecked(boolean)}
   *   <li>{@link ERDEntityAttribute#setInForeignKey(boolean)}
   *   <li>{@link ERDEntityAttribute#setOrder(int)}
   *   <li>{@link ERDEntityAttribute#toString()}
   *   <li>{@link ERDEntityAttribute#getAlias()}
   *   <li>{@link ERDEntityAttribute#getOrder()}
   *   <li>{@link ERDEntityAttribute#isChecked()}
   *   <li>{@link ERDEntityAttribute#isInForeignKey()}
   *   <li>{@link ERDEntityAttribute#isInPrimaryKey()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDEntityAttribute.<init>(DBSEntityAttribute, boolean)",
    "String ERDEntityAttribute.getAlias()",
    "int ERDEntityAttribute.getOrder()",
    "boolean ERDEntityAttribute.isChecked()",
    "boolean ERDEntityAttribute.isInForeignKey()",
    "boolean ERDEntityAttribute.isInPrimaryKey()",
    "void ERDEntityAttribute.setAlias(String)",
    "void ERDEntityAttribute.setChecked(boolean)",
    "void ERDEntityAttribute.setInForeignKey(boolean)",
    "void ERDEntityAttribute.setOrder(int)",
    "String ERDEntityAttribute.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute attribute = new DBVEntityAttribute(entity, null, "Name");

    // Act
    ERDEntityAttribute actualErdEntityAttribute = new ERDEntityAttribute(attribute, true);
    actualErdEntityAttribute.setAlias("Alias");
    actualErdEntityAttribute.setChecked(true);
    actualErdEntityAttribute.setInForeignKey(true);
    actualErdEntityAttribute.setOrder(1);
    String actualToStringResult = actualErdEntityAttribute.toString();
    String actualAlias = actualErdEntityAttribute.getAlias();
    int actualOrder = actualErdEntityAttribute.getOrder();
    boolean actualIsCheckedResult = actualErdEntityAttribute.isChecked();
    boolean actualIsInForeignKeyResult = actualErdEntityAttribute.isInForeignKey();
    boolean actualIsInPrimaryKeyResult = actualErdEntityAttribute.isInPrimaryKey();

    // Assert
    assertEquals("Alias", actualAlias);
    assertEquals("Name", actualToStringResult);
    assertNull(actualErdEntityAttribute.getUserData());
    assertEquals(1, actualOrder);
    assertTrue(actualIsCheckedResult);
    assertTrue(actualIsInForeignKeyResult);
    assertTrue(actualIsInPrimaryKeyResult);
    assertSame(attribute, actualErdEntityAttribute.getObject());
  }

  /**
   * Test {@link ERDEntityAttribute#getLabelText()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#getLabelText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDEntityAttribute.getLabelText()"})
  public void testGetLabelText_givenDBVContainerWithParentIsDBVContainerAndName_thenReturnName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act and Assert
    assertEquals("Name", new ERDEntityAttribute(attribute, true).getLabelText());
  }

  /**
   * Test {@link ERDEntityAttribute#getLabelImage()}.
   *
   * <ul>
   *   <li>Then return Location is {@code types/unknown.svg}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#getLabelImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage ERDEntityAttribute.getLabelImage()"})
  public void testGetLabelImage_thenReturnLocationIsTypesUnknownSvg() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);

    // Act
    DBPImage actualLabelImage = erdEntityAttribute.getLabelImage();
    String actualLocation = actualLabelImage.getLocation();

    // Assert
    assertTrue(actualLabelImage instanceof DBIcon);
    DBSEntityAttribute object = erdEntityAttribute.getObject();
    assertTrue(object instanceof DBVEntityAttribute);
    assertEquals("types/unknown.svg", actualLabelImage.getLocation());
    assertEquals("types/unknown.svg", actualLocation);
    assertEquals("unknown", ((DBIcon) actualLabelImage).getToken());
    assertSame(
        ((DBIcon) actualLabelImage).TYPE_UNKNOWN, ((DBVEntityAttribute) object).getObjectImage());
  }

  /**
   * Test {@link ERDEntityAttribute#getLabelImage()}.
   *
   * <ul>
   *   <li>Then return Token is {@code boolean}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#getLabelImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage ERDEntityAttribute.getLabelImage()"})
  public void testGetLabelImage_thenReturnTokenIsBoolean() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDataKind(DBPDataKind.BOOLEAN);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);

    // Act
    DBPImage actualLabelImage = erdEntityAttribute.getLabelImage();
    String actualLocation = actualLabelImage.getLocation();

    // Assert
    assertTrue(actualLabelImage instanceof DBIcon);
    DBSEntityAttribute object = erdEntityAttribute.getObject();
    assertTrue(object instanceof DBVEntityAttribute);
    assertEquals("boolean", ((DBIcon) actualLabelImage).getToken());
    assertEquals("types/boolean.svg", actualLabelImage.getLocation());
    assertEquals("types/boolean.svg", actualLocation);
    assertSame(
        ((DBIcon) actualLabelImage).TYPE_BOOLEAN, ((DBVEntityAttribute) object).getObjectImage());
  }

  /**
   * Test {@link ERDEntityAttribute#getLabelImage()}.
   *
   * <ul>
   *   <li>Then return Token is {@code datetime}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#getLabelImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage ERDEntityAttribute.getLabelImage()"})
  public void testGetLabelImage_thenReturnTokenIsDatetime() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDataKind(DBPDataKind.DATETIME);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);

    // Act
    DBPImage actualLabelImage = erdEntityAttribute.getLabelImage();
    String actualLocation = actualLabelImage.getLocation();

    // Assert
    assertTrue(actualLabelImage instanceof DBIcon);
    DBSEntityAttribute object = erdEntityAttribute.getObject();
    assertTrue(object instanceof DBVEntityAttribute);
    assertEquals("datetime", ((DBIcon) actualLabelImage).getToken());
    assertEquals("types/datetime.svg", actualLabelImage.getLocation());
    assertEquals("types/datetime.svg", actualLocation);
    assertSame(
        ((DBIcon) actualLabelImage).TYPE_DATETIME, ((DBVEntityAttribute) object).getObjectImage());
  }

  /**
   * Test {@link ERDEntityAttribute#getLabelImage()}.
   *
   * <ul>
   *   <li>Then return Token is {@code number}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#getLabelImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage ERDEntityAttribute.getLabelImage()"})
  public void testGetLabelImage_thenReturnTokenIsNumber() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDataKind(DBPDataKind.NUMERIC);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);

    // Act
    DBPImage actualLabelImage = erdEntityAttribute.getLabelImage();
    String actualLocation = actualLabelImage.getLocation();

    // Assert
    assertTrue(actualLabelImage instanceof DBIcon);
    DBSEntityAttribute object = erdEntityAttribute.getObject();
    assertTrue(object instanceof DBVEntityAttribute);
    assertEquals("number", ((DBIcon) actualLabelImage).getToken());
    assertEquals("types/number.svg", actualLabelImage.getLocation());
    assertEquals("types/number.svg", actualLocation);
    assertSame(
        ((DBIcon) actualLabelImage).TYPE_NUMBER, ((DBVEntityAttribute) object).getObjectImage());
  }

  /**
   * Test {@link ERDEntityAttribute#getLabelImage()}.
   *
   * <ul>
   *   <li>Then return Token is {@code object}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#getLabelImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage ERDEntityAttribute.getLabelImage()"})
  public void testGetLabelImage_thenReturnTokenIsObject() {
    // Arrange and Act
    DBPImage actualLabelImage = new ERDEntityAttribute(null, true).getLabelImage();
    String actualLocation = actualLabelImage.getLocation();

    // Assert
    assertTrue(actualLabelImage instanceof DBIcon);
    assertEquals("object", ((DBIcon) actualLabelImage).getToken());
    assertEquals("types/object.svg", actualLabelImage.getLocation());
    assertEquals("types/object.svg", actualLocation);
  }

  /**
   * Test {@link ERDEntityAttribute#getLabelImage()}.
   *
   * <ul>
   *   <li>Then return Token is {@code string}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#getLabelImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage ERDEntityAttribute.getLabelImage()"})
  public void testGetLabelImage_thenReturnTokenIsString() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDataKind(DBPDataKind.STRING);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);

    // Act
    DBPImage actualLabelImage = erdEntityAttribute.getLabelImage();
    String actualLocation = actualLabelImage.getLocation();

    // Assert
    assertTrue(actualLabelImage instanceof DBIcon);
    DBSEntityAttribute object = erdEntityAttribute.getObject();
    assertTrue(object instanceof DBVEntityAttribute);
    assertEquals("string", ((DBIcon) actualLabelImage).getToken());
    assertEquals("types/string.svg", actualLabelImage.getLocation());
    assertEquals("types/string.svg", actualLocation);
    assertSame(
        ((DBIcon) actualLabelImage).TYPE_STRING, ((DBVEntityAttribute) object).getObjectImage());
  }

  /**
   * Test {@link ERDEntityAttribute#getName()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDEntityAttribute.getName()"})
  public void testGetName_givenDBVContainerWithParentIsDBVContainerAndName_thenReturnName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act and Assert
    assertEquals("Name", new ERDEntityAttribute(attribute, true).getName());
  }

  /**
   * Test {@link ERDEntityAttribute#fromMap(ERDContext, Map)}.
   *
   * <p>Method under test: {@link ERDEntityAttribute#fromMap(ERDContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntityAttribute.fromMap(ERDContext, Map)"})
  public void testFromMap() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    HashMap<String, Object> attrMap = new HashMap<>();
    attrMap.put(ERDPersistedState.ATTR_ALIAS, "Attr Map");
    attrMap.put("checked", null);
    attrMap.put("inPrimaryKey", null);
    attrMap.put("inForeignKey", null);

    // Act
    erdEntityAttribute.fromMap(context, attrMap);

    // Assert
    assertEquals("Attr Map", erdEntityAttribute.getAlias());
    assertFalse(erdEntityAttribute.isInPrimaryKey());
  }

  /**
   * Test {@link ERDEntityAttribute#fromMap(ERDContext, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#fromMap(ERDContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntityAttribute.fromMap(ERDContext, Map)"})
  public void testFromMap_whenHashMap() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    erdEntityAttribute.fromMap(context, new HashMap<>());

    // Assert
    assertNull(erdEntityAttribute.getAlias());
    assertFalse(erdEntityAttribute.isInPrimaryKey());
  }

  /**
   * Test {@link ERDEntityAttribute#fromMap(ERDContext, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@link ERDPersistedState#ATTR_ALIAS} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#fromMap(ERDContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDEntityAttribute.fromMap(ERDContext, Map)"})
  public void testFromMap_whenHashMapAttr_aliasIsNull() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    HashMap<String, Object> attrMap = new HashMap<>();
    attrMap.put(ERDPersistedState.ATTR_ALIAS, null);
    attrMap.put("checked", null);
    attrMap.put("inPrimaryKey", null);
    attrMap.put("inForeignKey", "Attr Map");

    // Act
    erdEntityAttribute.fromMap(context, attrMap);

    // Assert
    assertNull(erdEntityAttribute.getAlias());
    assertFalse(erdEntityAttribute.isInPrimaryKey());
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("UNKNOWN", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/unknown.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap2() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDefaultValue("");
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("UNKNOWN", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/unknown.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link ERDPersistedState#ATTR_ALIAS} is {@link ERDPersistedState#ATTR_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnAttr_aliasIsAttr_name() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    erdEntityAttribute.setAlias(ERDPersistedState.ATTR_NAME);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(8, actualToMapResult.size());
    assertEquals("UNKNOWN", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/unknown.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("inPrimaryKey"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertEquals(ERDPersistedState.ATTR_NAME, actualToMapResult.get(ERDPersistedState.ATTR_ALIAS));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code checked}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnChecked() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    erdEntityAttribute.setChecked(true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(8, actualToMapResult.size());
    assertEquals("UNKNOWN", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/unknown.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("inPrimaryKey"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("checked"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return containsKey {@code dataKind}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnContainsKeyDataKind() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setTypeName(ERDPersistedState.ATTR_NAME);
    attribute.setDataKind(DBPDataKind.CONTENT);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertTrue(actualToMapResult.containsKey("dataKind"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("inPrimaryKey"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertEquals(ERDPersistedState.ATTR_NAME, actualToMapResult.get("fullTypeName"));
    assertEquals(ERDPersistedState.ATTR_NAME, actualToMapResult.get("typeName"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code ANY}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsAny() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), ERDPersistedState.ATTR_NAME);
    DBVEntity entity2 =
        new DBVEntity(container2, ERDPersistedState.ATTR_NAME, ERDPersistedState.ATTR_NAME);
    DBVEntityAttribute child =
        new DBVEntityAttribute(
            entity2, mock(DBVEntityAttribute.class), ERDPersistedState.ATTR_NAME);
    attribute.addChild(child);
    attribute.setDataKind(DBPDataKind.ANY);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("ANY", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/any.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code ARRAY}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsArray() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), ERDPersistedState.ATTR_NAME);
    DBVEntity entity2 =
        new DBVEntity(container2, ERDPersistedState.ATTR_NAME, ERDPersistedState.ATTR_NAME);
    DBVEntityAttribute child =
        new DBVEntityAttribute(
            entity2, mock(DBVEntityAttribute.class), ERDPersistedState.ATTR_NAME);
    attribute.addChild(child);
    attribute.setDataKind(DBPDataKind.ARRAY);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("ARRAY", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/array.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code BINARY}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsBinary() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDataKind(DBPDataKind.BINARY);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("BINARY", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/binary.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsBoolean() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDataKind(DBPDataKind.BOOLEAN);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("BOOLEAN", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/boolean.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code CONTENT}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsContent() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), ERDPersistedState.ATTR_NAME);
    DBVEntity entity2 =
        new DBVEntity(container2, ERDPersistedState.ATTR_NAME, ERDPersistedState.ATTR_NAME);
    DBVEntityAttribute child =
        new DBVEntityAttribute(
            entity2, mock(DBVEntityAttribute.class), ERDPersistedState.ATTR_NAME);
    attribute.addChild(child);
    attribute.setDataKind(DBPDataKind.CONTENT);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("CONTENT", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/lob.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code DATETIME}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsDatetime() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDataKind(DBPDataKind.DATETIME);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("DATETIME", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/datetime.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code DOCUMENT}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsDocument() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), ERDPersistedState.ATTR_NAME);
    DBVEntity entity2 =
        new DBVEntity(container2, ERDPersistedState.ATTR_NAME, ERDPersistedState.ATTR_NAME);
    DBVEntityAttribute child =
        new DBVEntityAttribute(
            entity2, mock(DBVEntityAttribute.class), ERDPersistedState.ATTR_NAME);
    attribute.addChild(child);
    attribute.setDataKind(DBPDataKind.DOCUMENT);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("DOCUMENT", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/document.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsNumeric() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDataKind(DBPDataKind.NUMERIC);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("NUMERIC", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/number.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code OBJECT}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsObject() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), ERDPersistedState.ATTR_NAME);
    DBVEntity entity2 =
        new DBVEntity(container2, ERDPersistedState.ATTR_NAME, ERDPersistedState.ATTR_NAME);
    DBVEntityAttribute child =
        new DBVEntityAttribute(
            entity2, mock(DBVEntityAttribute.class), ERDPersistedState.ATTR_NAME);
    attribute.addChild(child);
    attribute.setDataKind(DBPDataKind.OBJECT);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("OBJECT", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/object.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code REFERENCE}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsReference() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), ERDPersistedState.ATTR_NAME);
    DBVEntity entity2 =
        new DBVEntity(container2, ERDPersistedState.ATTR_NAME, ERDPersistedState.ATTR_NAME);
    DBVEntityAttribute child =
        new DBVEntityAttribute(
            entity2, mock(DBVEntityAttribute.class), ERDPersistedState.ATTR_NAME);
    attribute.addChild(child);
    attribute.setDataKind(DBPDataKind.REFERENCE);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("REFERENCE", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/reference.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code ROWID}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsRowid() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), ERDPersistedState.ATTR_NAME);
    DBVEntity entity2 =
        new DBVEntity(container2, ERDPersistedState.ATTR_NAME, ERDPersistedState.ATTR_NAME);
    DBVEntityAttribute child =
        new DBVEntityAttribute(
            entity2, mock(DBVEntityAttribute.class), ERDPersistedState.ATTR_NAME);
    attribute.addChild(child);
    attribute.setDataKind(DBPDataKind.ROWID);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("ROWID", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/rowid.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsString() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDataKind(DBPDataKind.STRING);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("STRING", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/string.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code dataKind} is {@code STRUCT}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDataKindIsStruct() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), ERDPersistedState.ATTR_NAME);
    DBVEntity entity2 =
        new DBVEntity(container2, ERDPersistedState.ATTR_NAME, ERDPersistedState.ATTR_NAME);
    DBVEntityAttribute child =
        new DBVEntityAttribute(
            entity2, mock(DBVEntityAttribute.class), ERDPersistedState.ATTR_NAME);
    attribute.addChild(child);
    attribute.setDataKind(DBPDataKind.STRUCT);
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(7, actualToMapResult.size());
    assertEquals("STRUCT", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/struct.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inPrimaryKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code defaultValue} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDefaultValueIs42() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDefaultValue("42");
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(8, actualToMapResult.size());
    assertEquals("42", actualToMapResult.get("defaultValue"));
    assertEquals("UNKNOWN", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/unknown.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("inPrimaryKey"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code description} is {@code The characteristics of someone or something}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnDescriptionIsTheCharacteristicsOfSomeoneOrSomething() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setDescription("The characteristics of someone or something");
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(8, actualToMapResult.size());
    assertEquals(
        "The characteristics of someone or something", actualToMapResult.get("description"));
    assertEquals("UNKNOWN", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/unknown.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("inPrimaryKey"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code inForeignKey}.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnInForeignKey() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, true);
    erdEntityAttribute.setInForeignKey(true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(8, actualToMapResult.size());
    assertEquals("UNKNOWN", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/unknown.svg", icons.get(0));
    assertTrue(actualToMapResult.containsKey("fullTypeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("inPrimaryKey"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey("typeName"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue((Boolean) actualToMapResult.get("inForeignKey"));
  }

  /**
   * Test {@link ERDEntityAttribute#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return size is six.
   * </ul>
   *
   * <p>Method under test: {@link ERDEntityAttribute#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDEntityAttribute.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnSizeIsSix() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute erdEntityAttribute = new ERDEntityAttribute(attribute, false);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdEntityAttribute.toMap(context, true);

    // Assert
    assertEquals(6, actualToMapResult.size());
    assertEquals("UNKNOWN", actualToMapResult.get("dataKind"));
    List<String> icons = context.getIcons();
    assertEquals(1, icons.size());
    assertEquals("types/unknown.svg", icons.get(0));
    assertNull(actualToMapResult.get("fullTypeName"));
    assertNull(actualToMapResult.get("typeName"));
    assertTrue(actualToMapResult.containsKey("iconIndex"));
    assertTrue(actualToMapResult.containsKey("optional"));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
  }
}
