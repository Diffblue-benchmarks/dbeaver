package org.jkiss.dbeaver.model.secret;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSSecretDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSSecret#DBSSecret()}
   *   <li>{@link DBSSecret#setDescription(String)}
   *   <li>{@link DBSSecret#setId(String)}
   *   <li>{@link DBSSecret#setName(String)}
   *   <li>{@link DBSSecret#toString()}
   *   <li>{@link DBSSecret#getDescription()}
   *   <li>{@link DBSSecret#getId()}
   *   <li>{@link DBSSecret#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSSecret.<init>()",
    "void DBSSecret.<init>(String, String)",
    "String DBSSecret.getDescription()",
    "String DBSSecret.getId()",
    "String DBSSecret.getName()",
    "void DBSSecret.setDescription(String)",
    "void DBSSecret.setId(String)",
    "void DBSSecret.setName(String)",
    "String DBSSecret.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSSecret actualDbsSecret = new DBSSecret();
    actualDbsSecret.setDescription("The characteristics of someone or something");
    actualDbsSecret.setId("42");
    actualDbsSecret.setName("Name");
    String actualToStringResult = actualDbsSecret.toString();
    String actualDescription = actualDbsSecret.getDescription();
    String actualId = actualDbsSecret.getId();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Name", actualDbsSecret.getName());
    assertEquals("Name", actualToStringResult);
    assertEquals("The characteristics of someone or something", actualDescription);
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
   *   <li>{@link DBSSecret#DBSSecret(String, String)}
   *   <li>{@link DBSSecret#setDescription(String)}
   *   <li>{@link DBSSecret#setId(String)}
   *   <li>{@link DBSSecret#setName(String)}
   *   <li>{@link DBSSecret#toString()}
   *   <li>{@link DBSSecret#getDescription()}
   *   <li>{@link DBSSecret#getId()}
   *   <li>{@link DBSSecret#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSSecret.<init>()",
    "void DBSSecret.<init>(String, String)",
    "String DBSSecret.getDescription()",
    "String DBSSecret.getId()",
    "String DBSSecret.getName()",
    "void DBSSecret.setDescription(String)",
    "void DBSSecret.setId(String)",
    "void DBSSecret.setName(String)",
    "String DBSSecret.toString()"
  })
  public void testGettersAndSetters_when42() {
    // Arrange and Act
    DBSSecret actualDbsSecret = new DBSSecret("42", "Name");
    actualDbsSecret.setDescription("The characteristics of someone or something");
    actualDbsSecret.setId("42");
    actualDbsSecret.setName("Name");
    String actualToStringResult = actualDbsSecret.toString();
    String actualDescription = actualDbsSecret.getDescription();
    String actualId = actualDbsSecret.getId();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Name", actualDbsSecret.getName());
    assertEquals("Name", actualToStringResult);
    assertEquals("The characteristics of someone or something", actualDescription);
  }
}
