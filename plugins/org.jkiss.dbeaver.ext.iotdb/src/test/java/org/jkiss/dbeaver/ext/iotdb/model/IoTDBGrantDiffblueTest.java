package org.jkiss.dbeaver.ext.iotdb.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IoTDBGrantDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link IoTDBGrant#IoTDBGrant(IoTDBAbstractUser, List, String, String, boolean)}
   *   <li>{@link IoTDBGrant#setGrantOption(Boolean)}
   *   <li>{@link IoTDBGrant#setRole(String)}
   *   <li>{@link IoTDBGrant#setScope(String)}
   *   <li>{@link IoTDBGrant#getDescription()}
   *   <li>{@link IoTDBGrant#getGrantOption()}
   *   <li>{@link IoTDBGrant#getParentObject()}
   *   <li>{@link IoTDBGrant#getRole()}
   *   <li>{@link IoTDBGrant#getScope()}
   *   <li>{@link IoTDBGrant#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void IoTDBGrant.<init>(IoTDBAbstractUser, List, String, String, boolean)",
    "String IoTDBGrant.getDescription()",
    "Boolean IoTDBGrant.getGrantOption()",
    "DBSObject IoTDBGrant.getParentObject()",
    "String IoTDBGrant.getRole()",
    "String IoTDBGrant.getScope()",
    "boolean IoTDBGrant.isPersisted()",
    "void IoTDBGrant.setGrantOption(Boolean)",
    "void IoTDBGrant.setRole(String)",
    "void IoTDBGrant.setScope(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    IoTDBGrant actualIoTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Scope", true);
    actualIoTDBGrant.setGrantOption(true);
    actualIoTDBGrant.setRole("Role");
    actualIoTDBGrant.setScope("Scope");
    String actualDescription = actualIoTDBGrant.getDescription();
    Boolean actualGrantOption = actualIoTDBGrant.getGrantOption();
    DBSObject actualParentObject = actualIoTDBGrant.getParentObject();
    String actualRole = actualIoTDBGrant.getRole();
    String actualScope = actualIoTDBGrant.getScope();

    // Assert
    assertEquals("Role", actualRole);
    assertEquals("Scope", actualScope);
    assertNull(actualDescription);
    assertNull(actualParentObject);
    assertTrue(actualGrantOption);
    assertTrue(actualIoTDBGrant.isPersisted());
  }

  /**
   * Test {@link IoTDBGrant#getSubject(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link IoTDBGrant#getSubject(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object IoTDBGrant.getSubject(DBRProgressMonitor)"})
  public void testGetSubject() throws DBException {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Scope", true);

    // Act and Assert
    assertNull(ioTDBGrant.getSubject(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link IoTDBGrant#getObject(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link IoTDBGrant#getObject(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object IoTDBGrant.getObject(DBRProgressMonitor)"})
  public void testGetObject() throws DBException {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Scope", true);

    // Act and Assert
    assertEquals("testObject", ioTDBGrant.getObject(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link IoTDBGrant#getPrivileges()}.
   *
   * <p>Method under test: {@link IoTDBGrant#getPrivileges()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.access.DBAPrivilege[] IoTDBGrant.getPrivileges()"})
  public void testGetPrivileges() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Scope", true);

    // Act and Assert
    assertEquals(0, ioTDBGrant.getPrivileges().length);
  }

  /**
   * Test {@link IoTDBGrant#isGranted()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link IoTDBGrant#isGranted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.isGranted()"})
  public void testIsGranted_thenReturnFalse() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Scope", false);

    // Act and Assert
    assertFalse(ioTDBGrant.isGranted());
  }

  /**
   * Test {@link IoTDBGrant#isGranted()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link IoTDBGrant#isGranted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.isGranted()"})
  public void testIsGranted_thenReturnTrue() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Scope", true);

    // Act and Assert
    assertTrue(ioTDBGrant.isGranted());
  }

  /**
   * Test {@link IoTDBGrant#matches(String, String)}.
   *
   * <p>Method under test: {@link IoTDBGrant#matches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.matches(String, String)"})
  public void testMatches() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "*.*", true);

    // Act and Assert
    assertTrue(ioTDBGrant.matches("Db", "Tb"));
  }

  /**
   * Test {@link IoTDBGrant#matches(String, String)}.
   *
   * <p>Method under test: {@link IoTDBGrant#matches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.matches(String, String)"})
  public void testMatches2() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Db.Tb", true);

    // Act and Assert
    assertTrue(ioTDBGrant.matches("Db", "Tb"));
  }

  /**
   * Test {@link IoTDBGrant#matches(String, String)}.
   *
   * <p>Method under test: {@link IoTDBGrant#matches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.matches(String, String)"})
  public void testMatches3() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Db.*", true);

    // Act and Assert
    assertTrue(ioTDBGrant.matches("Db", "Tb"));
  }

  /**
   * Test {@link IoTDBGrant#matches(String, String)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link IoTDBGrant#matches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.matches(String, String)"})
  public void testMatches_thenReturnFalse() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Scope", true);

    // Act and Assert
    assertFalse(ioTDBGrant.matches("Db", "Tb"));
  }

  /**
   * Test {@link IoTDBGrant#canHighlightDatabase(String)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link IoTDBGrant#canHighlightDatabase(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.canHighlightDatabase(String)"})
  public void testCanHighlightDatabase_thenReturnFalse() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Scope", true);

    // Act and Assert
    assertFalse(ioTDBGrant.canHighlightDatabase("Db"));
  }

  /**
   * Test {@link IoTDBGrant#canHighlightDatabase(String)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link IoTDBGrant#canHighlightDatabase(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.canHighlightDatabase(String)"})
  public void testCanHighlightDatabase_thenReturnTrue() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "*.", true);

    // Act and Assert
    assertTrue(ioTDBGrant.canHighlightDatabase("Db"));
  }

  /**
   * Test {@link IoTDBGrant#canHighlightTable(String, String)}.
   *
   * <p>Method under test: {@link IoTDBGrant#canHighlightTable(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.canHighlightTable(String, String)"})
  public void testCanHighlightTable() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "*.", true);

    // Act and Assert
    assertTrue(ioTDBGrant.canHighlightTable("Db", "Tb"));
  }

  /**
   * Test {@link IoTDBGrant#canHighlightTable(String, String)}.
   *
   * <p>Method under test: {@link IoTDBGrant#canHighlightTable(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.canHighlightTable(String, String)"})
  public void testCanHighlightTable2() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Db.*", true);

    // Act and Assert
    assertTrue(ioTDBGrant.canHighlightTable("Db", "Tb"));
  }

  /**
   * Test {@link IoTDBGrant#canHighlightTable(String, String)}.
   *
   * <p>Method under test: {@link IoTDBGrant#canHighlightTable(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.canHighlightTable(String, String)"})
  public void testCanHighlightTable3() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Db.Tb", true);

    // Act and Assert
    assertTrue(ioTDBGrant.canHighlightTable("Db", "Tb"));
  }

  /**
   * Test {@link IoTDBGrant#canHighlightTable(String, String)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link IoTDBGrant#canHighlightTable(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean IoTDBGrant.canHighlightTable(String, String)"})
  public void testCanHighlightTable_thenReturnFalse() {
    // Arrange
    IoTDBGrant ioTDBGrant = new IoTDBGrant(null, new ArrayList<>(), "Role", "Scope", true);

    // Act and Assert
    assertFalse(ioTDBGrant.canHighlightTable("Db", "Tb"));
  }
}
