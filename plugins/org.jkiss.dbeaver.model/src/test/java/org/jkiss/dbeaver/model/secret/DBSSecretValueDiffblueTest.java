package org.jkiss.dbeaver.model.secret;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSSecretValueDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSSecretValue#DBSSecretValue()}
   *   <li>{@link DBSSecretValue#setDisplayName(String)}
   *   <li>{@link DBSSecretValue#setId(String)}
   *   <li>{@link DBSSecretValue#setSubjectId(String)}
   *   <li>{@link DBSSecretValue#setValue(String)}
   *   <li>{@link DBSSecretValue#getDisplayName()}
   *   <li>{@link DBSSecretValue#getId()}
   *   <li>{@link DBSSecretValue#getSubjectId()}
   *   <li>{@link DBSSecretValue#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSSecretValue.<init>()",
    "void DBSSecretValue.<init>(String, String, String)",
    "void DBSSecretValue.<init>(String, String, String, String)",
    "String DBSSecretValue.getDisplayName()",
    "String DBSSecretValue.getId()",
    "String DBSSecretValue.getSubjectId()",
    "String DBSSecretValue.getValue()",
    "void DBSSecretValue.setDisplayName(String)",
    "void DBSSecretValue.setId(String)",
    "void DBSSecretValue.setSubjectId(String)",
    "void DBSSecretValue.setValue(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSSecretValue actualDbsSecretValue = new DBSSecretValue();
    actualDbsSecretValue.setDisplayName("Display Name");
    actualDbsSecretValue.setId("42");
    actualDbsSecretValue.setSubjectId("42");
    actualDbsSecretValue.setValue("42");
    String actualDisplayName = actualDbsSecretValue.getDisplayName();
    String actualId = actualDbsSecretValue.getId();
    String actualSubjectId = actualDbsSecretValue.getSubjectId();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualSubjectId);
    assertEquals("42", actualDbsSecretValue.getValue());
    assertEquals("Display Name", actualDisplayName);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSSecretValue#DBSSecretValue(String, String, String)}
   *   <li>{@link DBSSecretValue#setDisplayName(String)}
   *   <li>{@link DBSSecretValue#setId(String)}
   *   <li>{@link DBSSecretValue#setSubjectId(String)}
   *   <li>{@link DBSSecretValue#setValue(String)}
   *   <li>{@link DBSSecretValue#getDisplayName()}
   *   <li>{@link DBSSecretValue#getId()}
   *   <li>{@link DBSSecretValue#getSubjectId()}
   *   <li>{@link DBSSecretValue#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSSecretValue.<init>()",
    "void DBSSecretValue.<init>(String, String, String)",
    "void DBSSecretValue.<init>(String, String, String, String)",
    "String DBSSecretValue.getDisplayName()",
    "String DBSSecretValue.getId()",
    "String DBSSecretValue.getSubjectId()",
    "String DBSSecretValue.getValue()",
    "void DBSSecretValue.setDisplayName(String)",
    "void DBSSecretValue.setId(String)",
    "void DBSSecretValue.setSubjectId(String)",
    "void DBSSecretValue.setValue(String)"
  })
  public void testGettersAndSetters_when42() {
    // Arrange and Act
    DBSSecretValue actualDbsSecretValue = new DBSSecretValue("42", "Display Name", "42");
    actualDbsSecretValue.setDisplayName("Display Name");
    actualDbsSecretValue.setId("42");
    actualDbsSecretValue.setSubjectId("42");
    actualDbsSecretValue.setValue("42");
    String actualDisplayName = actualDbsSecretValue.getDisplayName();
    String actualId = actualDbsSecretValue.getId();
    String actualSubjectId = actualDbsSecretValue.getSubjectId();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualSubjectId);
    assertEquals("42", actualDbsSecretValue.getValue());
    assertEquals("Display Name", actualDisplayName);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSSecretValue#DBSSecretValue(String, String, String, String)}
   *   <li>{@link DBSSecretValue#setDisplayName(String)}
   *   <li>{@link DBSSecretValue#setId(String)}
   *   <li>{@link DBSSecretValue#setSubjectId(String)}
   *   <li>{@link DBSSecretValue#setValue(String)}
   *   <li>{@link DBSSecretValue#getDisplayName()}
   *   <li>{@link DBSSecretValue#getId()}
   *   <li>{@link DBSSecretValue#getSubjectId()}
   *   <li>{@link DBSSecretValue#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSSecretValue.<init>()",
    "void DBSSecretValue.<init>(String, String, String)",
    "void DBSSecretValue.<init>(String, String, String, String)",
    "String DBSSecretValue.getDisplayName()",
    "String DBSSecretValue.getId()",
    "String DBSSecretValue.getSubjectId()",
    "String DBSSecretValue.getValue()",
    "void DBSSecretValue.setDisplayName(String)",
    "void DBSSecretValue.setId(String)",
    "void DBSSecretValue.setSubjectId(String)",
    "void DBSSecretValue.setValue(String)"
  })
  public void testGettersAndSetters_when422() {
    // Arrange and Act
    DBSSecretValue actualDbsSecretValue = new DBSSecretValue("42", "42", "Display Name", "42");
    actualDbsSecretValue.setDisplayName("Display Name");
    actualDbsSecretValue.setId("42");
    actualDbsSecretValue.setSubjectId("42");
    actualDbsSecretValue.setValue("42");
    String actualDisplayName = actualDbsSecretValue.getDisplayName();
    String actualId = actualDbsSecretValue.getId();
    String actualSubjectId = actualDbsSecretValue.getSubjectId();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualSubjectId);
    assertEquals("42", actualDbsSecretValue.getValue());
    assertEquals("Display Name", actualDisplayName);
  }

  /**
   * Test {@link DBSSecretValue#getUniqueId()}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretValue#getUniqueId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBSSecretValue.getUniqueId()"})
  public void testGetUniqueId_thenReturn42() {
    // Arrange
    DBSSecretValue dbsSecretValue = new DBSSecretValue("42", "Display Name", "42");

    // Act and Assert
    assertEquals("42", dbsSecretValue.getUniqueId());
  }

  /**
   * Test {@link DBSSecretValue#getUniqueId()}.
   *
   * <ul>
   *   <li>Then return {@code 42_foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretValue#getUniqueId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBSSecretValue.getUniqueId()"})
  public void testGetUniqueId_thenReturn42Foo() {
    // Arrange
    DBSSecretValue dbsSecretValue = new DBSSecretValue("42", "Display Name", "42");
    dbsSecretValue.setSubjectId("foo");

    // Act and Assert
    assertEquals("42_foo", dbsSecretValue.getUniqueId());
  }

  /**
   * Test {@link DBSSecretValue#equals(Object)}, and {@link DBSSecretValue#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSSecretValue#equals(Object)}
   *   <li>{@link DBSSecretValue#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSSecretValue.equals(Object)", "int DBSSecretValue.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBSSecretValue dbsSecretValue = new DBSSecretValue("42", "Display Name", "42");
    DBSSecretValue dbsSecretValue2 = new DBSSecretValue("42", "Display Name", "42");

    // Act and Assert
    assertEquals(dbsSecretValue, dbsSecretValue2);
    assertEquals(dbsSecretValue.hashCode(), dbsSecretValue2.hashCode());
  }

  /**
   * Test {@link DBSSecretValue#equals(Object)}, and {@link DBSSecretValue#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSSecretValue#equals(Object)}
   *   <li>{@link DBSSecretValue#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSSecretValue.equals(Object)", "int DBSSecretValue.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBSSecretValue dbsSecretValue = new DBSSecretValue("42", "Display Name", "42");

    // Act and Assert
    assertEquals(dbsSecretValue, dbsSecretValue);
    int expectedHashCodeResult = dbsSecretValue.hashCode();
    assertEquals(expectedHashCodeResult, dbsSecretValue.hashCode());
  }

  /**
   * Test {@link DBSSecretValue#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretValue#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSSecretValue.equals(Object)", "int DBSSecretValue.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DBSSecretValue dbsSecretValue = new DBSSecretValue("Id", "Display Name", "42");

    // Act and Assert
    assertNotEquals(dbsSecretValue, new DBSSecretValue("42", "Display Name", "42"));
  }

  /**
   * Test {@link DBSSecretValue#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretValue#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSSecretValue.equals(Object)", "int DBSSecretValue.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBSSecretValue dbsSecretValue = new DBSSecretValue("42", null, "42");

    // Act and Assert
    assertNotEquals(dbsSecretValue, new DBSSecretValue("42", "Display Name", "42"));
  }

  /**
   * Test {@link DBSSecretValue#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretValue#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSSecretValue.equals(Object)", "int DBSSecretValue.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DBSSecretValue dbsSecretValue = new DBSSecretValue("42", "Display Name", "Value");

    // Act and Assert
    assertNotEquals(dbsSecretValue, new DBSSecretValue("42", "Display Name", "42"));
  }

  /**
   * Test {@link DBSSecretValue#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretValue#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSSecretValue.equals(Object)", "int DBSSecretValue.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    DBSSecretValue dbsSecretValue = new DBSSecretValue("42", "42", "Display Name", "42");

    // Act and Assert
    assertNotEquals(dbsSecretValue, new DBSSecretValue("42", "Display Name", "42"));
  }

  /**
   * Test {@link DBSSecretValue#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretValue#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSSecretValue.equals(Object)", "int DBSSecretValue.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBSSecretValue("42", "Display Name", "42"), null);
  }

  /**
   * Test {@link DBSSecretValue#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretValue#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSSecretValue.equals(Object)", "int DBSSecretValue.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new DBSSecretValue("42", "Display Name", "42"), "Different type to DBSSecretValue");
  }
}
