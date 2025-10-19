package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RMProjectTypeDiffblueTest {
  /**
   * Test {@link RMProjectType#getPrefix()}.
   *
   * <p>Method under test: {@link RMProjectType#getPrefix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMProjectType.getPrefix()"})
  public void testGetPrefix() {
    // Arrange, Act and Assert
    assertEquals("g", RMProjectType.valueOf("GLOBAL").getPrefix());
  }

  /**
   * Test {@link RMProjectType#getByPrefix(String)}.
   *
   * <ul>
   *   <li>When {@code g}.
   *   <li>Then return {@code GLOBAL}.
   * </ul>
   *
   * <p>Method under test: {@link RMProjectType#getByPrefix(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RMProjectType RMProjectType.getByPrefix(String)"})
  public void testGetByPrefix_whenG_thenReturnGlobal() {
    // Arrange, Act and Assert
    assertEquals(RMProjectType.GLOBAL, RMProjectType.getByPrefix("g"));
  }

  /**
   * Test {@link RMProjectType#getByPrefix(String)}.
   *
   * <ul>
   *   <li>When {@code Prefix}.
   *   <li>Then return {@code USER}.
   * </ul>
   *
   * <p>Method under test: {@link RMProjectType#getByPrefix(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RMProjectType RMProjectType.getByPrefix(String)"})
  public void testGetByPrefix_whenPrefix_thenReturnUser() {
    // Arrange, Act and Assert
    assertEquals(RMProjectType.USER, RMProjectType.getByPrefix("Prefix"));
  }

  /**
   * Test {@link RMProjectType#getPlainProjectId(DBPProject)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link DBPProject} {@link DBPProject#getId()} return {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link RMProjectType#getPlainProjectId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMProjectType.getPlainProjectId(DBPProject)"})
  public void testGetPlainProjectId_given42_whenDBPProjectGetIdReturn42_thenReturn42() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    // Act
    String actualPlainProjectId = RMProjectType.getPlainProjectId(project);

    // Assert
    verify(project).getId();
    assertEquals("42", actualPlainProjectId);
  }

  /**
   * Test {@link RMProjectType#getPlainProjectId(DBPProject)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link DBPProject} {@link DBPProject#getId()} return {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link RMProjectType#getPlainProjectId(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMProjectType.getPlainProjectId(DBPProject)"})
  public void testGetPlainProjectId_givenFoo_whenDBPProjectGetIdReturnFoo_thenReturnFoo() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("foo");

    // Act
    String actualPlainProjectId = RMProjectType.getPlainProjectId(project);

    // Assert
    verify(project).getId();
    assertEquals("foo", actualPlainProjectId);
  }
}
