package org.jkiss.dbeaver.ext.mysql.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MySQLSequenceDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Sequence Name}.
   *   <li>Then return IncrementBy is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MySQLSequence#MySQLSequence(MySQLCatalog, String)}
   *   <li>{@link MySQLSequence#setName(String)}
   *   <li>{@link MySQLSequence#setObjectDefinitionText(String)}
   *   <li>{@link MySQLSequence#getCatalog()}
   *   <li>{@link MySQLSequence#getDescription()}
   *   <li>{@link MySQLSequence#getIncrementBy()}
   *   <li>{@link MySQLSequence#getLastValue()}
   *   <li>{@link MySQLSequence#getMaxValue()}
   *   <li>{@link MySQLSequence#getMinValue()}
   *   <li>{@link MySQLSequence#getName()}
   *   <li>{@link MySQLSequence#getParentObject()}
   *   <li>{@link MySQLSequence#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MySQLSequence.<init>(MySQLCatalog, String)",
    "void MySQLSequence.<init>(MySQLCatalog, String, boolean)",
    "MySQLCatalog MySQLSequence.getCatalog()",
    "String MySQLSequence.getDescription()",
    "Number MySQLSequence.getIncrementBy()",
    "Number MySQLSequence.getLastValue()",
    "Number MySQLSequence.getMaxValue()",
    "Number MySQLSequence.getMinValue()",
    "String MySQLSequence.getName()",
    "DBSObject MySQLSequence.getParentObject()",
    "boolean MySQLSequence.isPersisted()",
    "void MySQLSequence.setName(String)",
    "void MySQLSequence.setObjectDefinitionText(String)"
  })
  public void testGettersAndSetters_whenSequenceName_thenReturnIncrementByIsNull()
      throws DBException {
    // Arrange and Act
    MySQLSequence actualMySQLSequence = new MySQLSequence(null, "Sequence Name");
    actualMySQLSequence.setName("Name");
    actualMySQLSequence.setObjectDefinitionText("Source Text");
    MySQLCatalog actualCatalog = actualMySQLSequence.getCatalog();
    String actualDescription = actualMySQLSequence.getDescription();
    Number actualIncrementBy = actualMySQLSequence.getIncrementBy();
    Number actualLastValue = actualMySQLSequence.getLastValue();
    Number actualMaxValue = actualMySQLSequence.getMaxValue();
    Number actualMinValue = actualMySQLSequence.getMinValue();
    String actualName = actualMySQLSequence.getName();
    DBSObject actualParentObject = actualMySQLSequence.getParentObject();

    // Assert
    assertEquals("Name", actualName);
    assertNull(actualIncrementBy);
    assertNull(actualLastValue);
    assertNull(actualMaxValue);
    assertNull(actualMinValue);
    assertNull(actualDescription);
    assertNull(actualCatalog);
    assertNull(actualParentObject);
    assertTrue(actualMySQLSequence.isPersisted());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return IncrementBy intValue is one.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MySQLSequence#MySQLSequence(MySQLCatalog, String, boolean)}
   *   <li>{@link MySQLSequence#setName(String)}
   *   <li>{@link MySQLSequence#setObjectDefinitionText(String)}
   *   <li>{@link MySQLSequence#getCatalog()}
   *   <li>{@link MySQLSequence#getDescription()}
   *   <li>{@link MySQLSequence#getIncrementBy()}
   *   <li>{@link MySQLSequence#getLastValue()}
   *   <li>{@link MySQLSequence#getMaxValue()}
   *   <li>{@link MySQLSequence#getMinValue()}
   *   <li>{@link MySQLSequence#getName()}
   *   <li>{@link MySQLSequence#getParentObject()}
   *   <li>{@link MySQLSequence#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MySQLSequence.<init>(MySQLCatalog, String)",
    "void MySQLSequence.<init>(MySQLCatalog, String, boolean)",
    "MySQLCatalog MySQLSequence.getCatalog()",
    "String MySQLSequence.getDescription()",
    "Number MySQLSequence.getIncrementBy()",
    "Number MySQLSequence.getLastValue()",
    "Number MySQLSequence.getMaxValue()",
    "Number MySQLSequence.getMinValue()",
    "String MySQLSequence.getName()",
    "DBSObject MySQLSequence.getParentObject()",
    "boolean MySQLSequence.isPersisted()",
    "void MySQLSequence.setName(String)",
    "void MySQLSequence.setObjectDefinitionText(String)"
  })
  public void testGettersAndSetters_whenTrue_thenReturnIncrementByIntValueIsOne()
      throws DBException {
    // Arrange and Act
    MySQLSequence actualMySQLSequence = new MySQLSequence(null, "Sequence Name", true);
    actualMySQLSequence.setName("Name");
    actualMySQLSequence.setObjectDefinitionText("Source Text");
    MySQLCatalog actualCatalog = actualMySQLSequence.getCatalog();
    String actualDescription = actualMySQLSequence.getDescription();
    Number actualIncrementBy = actualMySQLSequence.getIncrementBy();
    Number actualLastValue = actualMySQLSequence.getLastValue();
    Number actualMaxValue = actualMySQLSequence.getMaxValue();
    Number actualMinValue = actualMySQLSequence.getMinValue();
    String actualName = actualMySQLSequence.getName();
    DBSObject actualParentObject = actualMySQLSequence.getParentObject();
    boolean actualIsPersistedResult = actualMySQLSequence.isPersisted();

    // Assert
    assertEquals("Name", actualName);
    assertNull(actualLastValue);
    assertNull(actualDescription);
    assertNull(actualCatalog);
    assertNull(actualParentObject);
    assertEquals(1, actualIncrementBy.intValue());
    assertEquals(1, actualMinValue.intValue());
    assertEquals(9223372036854775806L, actualMaxValue.longValue());
    assertTrue(actualIsPersistedResult);
  }

  /**
   * Test {@link MySQLSequence#getIncrementBy(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link MySQLSequence#getIncrementBy(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getIncrementBy(DBRProgressMonitor)"})
  public void testGetIncrementByWithDBRProgressMonitor() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, null);

    // Act and Assert
    assertNull(mySQLSequence.getIncrementBy(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getIncrementBy(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link MySQLSequence#getIncrementBy(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getIncrementBy(DBRProgressMonitor)"})
  public void testGetIncrementByWithDBRProgressMonitor2() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "");

    // Act and Assert
    assertNull(mySQLSequence.getIncrementBy(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getIncrementBy(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link MySQLSequence#getIncrementBy(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getIncrementBy(DBRProgressMonitor)"})
  public void testGetIncrementByWithDBRProgressMonitor_thenReturnIntValueIsOne() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "Load sequence info", true);

    // Act and Assert
    assertEquals(1, mySQLSequence.getIncrementBy(new LoggingProgressMonitor()).intValue());
  }

  /**
   * Test {@link MySQLSequence#getMinValue(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link MySQLSequence#getMinValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getMinValue(DBRProgressMonitor)"})
  public void testGetMinValueWithDBRProgressMonitor() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, null);

    // Act and Assert
    assertNull(mySQLSequence.getMinValue(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getMinValue(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link MySQLSequence#getMinValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getMinValue(DBRProgressMonitor)"})
  public void testGetMinValueWithDBRProgressMonitor2() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "");

    // Act and Assert
    assertNull(mySQLSequence.getMinValue(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getMinValue(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link MySQLSequence#getMinValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getMinValue(DBRProgressMonitor)"})
  public void testGetMinValueWithDBRProgressMonitor_thenReturnIntValueIsOne() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "Load sequence info", true);

    // Act and Assert
    assertEquals(1, mySQLSequence.getMinValue(new LoggingProgressMonitor()).intValue());
  }

  /**
   * Test {@link MySQLSequence#getMaxValue(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link MySQLSequence#getMaxValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getMaxValue(DBRProgressMonitor)"})
  public void testGetMaxValueWithDBRProgressMonitor() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, null);

    // Act and Assert
    assertNull(mySQLSequence.getMaxValue(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getMaxValue(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link MySQLSequence#getMaxValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getMaxValue(DBRProgressMonitor)"})
  public void testGetMaxValueWithDBRProgressMonitor2() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "");

    // Act and Assert
    assertNull(mySQLSequence.getMaxValue(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getMaxValue(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return longValue is {@link Long#MAX_VALUE} less one.
   * </ul>
   *
   * <p>Method under test: {@link MySQLSequence#getMaxValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getMaxValue(DBRProgressMonitor)"})
  public void testGetMaxValueWithDBRProgressMonitor_thenReturnLongValueIsMax_valueLessOne() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "Load sequence info", true);

    // Act and Assert
    assertEquals(
        9223372036854775806L, mySQLSequence.getMaxValue(new LoggingProgressMonitor()).longValue());
  }

  /**
   * Test {@link MySQLSequence#getCache(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link MySQLSequence#getCache(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getCache(DBRProgressMonitor)"})
  public void testGetCache() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "");

    // Act and Assert
    assertNull(mySQLSequence.getCache(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getCache(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link MySQLSequence#MySQLSequence(MySQLCatalog, String)} with mySQLCatalog is
   *       {@code null} and sequenceName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLSequence#getCache(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getCache(DBRProgressMonitor)"})
  public void testGetCache_givenMySQLSequenceWithMySQLCatalogIsNullAndSequenceNameIsNull() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, null);

    // Act and Assert
    assertNull(mySQLSequence.getCache(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getCache(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return intValue is one thousand.
   * </ul>
   *
   * <p>Method under test: {@link MySQLSequence#getCache(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getCache(DBRProgressMonitor)"})
  public void testGetCache_thenReturnIntValueIsOneThousand() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "Load sequence info", true);

    // Act and Assert
    assertEquals(1000, mySQLSequence.getCache(new LoggingProgressMonitor()).intValue());
  }

  /**
   * Test {@link MySQLSequence#getStartValue(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link MySQLSequence#getStartValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getStartValue(DBRProgressMonitor)"})
  public void testGetStartValue() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "");

    // Act and Assert
    assertNull(mySQLSequence.getStartValue(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getStartValue(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link MySQLSequence#MySQLSequence(MySQLCatalog, String)} with mySQLCatalog is
   *       {@code null} and sequenceName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLSequence#getStartValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLSequence.getStartValue(DBRProgressMonitor)"})
  public void testGetStartValue_givenMySQLSequenceWithMySQLCatalogIsNullAndSequenceNameIsNull() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, null);

    // Act and Assert
    assertNull(mySQLSequence.getStartValue(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#isCycle(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link MySQLSequence#isCycle(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MySQLSequence.isCycle(DBRProgressMonitor)"})
  public void testIsCycle() {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "Sequence Name");

    // Act and Assert
    assertFalse(mySQLSequence.isCycle(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link MySQLSequence#getObjectDefinitionText(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Then return {@code Read sequence declaration}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLSequence#getObjectDefinitionText(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLSequence.getObjectDefinitionText(DBRProgressMonitor, Map)"})
  public void testGetObjectDefinitionText_thenReturnReadSequenceDeclaration() throws DBException {
    // Arrange
    MySQLSequence mySQLSequence = new MySQLSequence(null, "Sequence Name");
    mySQLSequence.setObjectDefinitionText("Read sequence declaration");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertEquals(
        "Read sequence declaration",
        mySQLSequence.getObjectDefinitionText(monitor, new HashMap<>()));
  }
}
