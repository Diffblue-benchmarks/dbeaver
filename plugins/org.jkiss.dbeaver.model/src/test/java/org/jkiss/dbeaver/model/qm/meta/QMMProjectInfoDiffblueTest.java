package org.jkiss.dbeaver.model.qm.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.UUID;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.qm.meta.QMMProjectInfo.Builder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMMProjectInfoDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#setAnonymous(boolean)}
   *   <li>{@link Builder#setId(String)}
   *   <li>{@link Builder#setName(String)}
   *   <li>{@link Builder#setPath(String)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>()",
    "QMMProjectInfo Builder.build()",
    "Builder Builder.setAnonymous(boolean)",
    "Builder Builder.setId(String)",
    "Builder Builder.setName(String)",
    "Builder Builder.setPath(String)"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    QMMProjectInfo actualQmmProjectInfo =
        QMMProjectInfo.builder()
            .setAnonymous(true)
            .setId("42")
            .setName("Name")
            .setPath("Path")
            .build();

    // Assert
    assertEquals("42", actualQmmProjectInfo.getId());
    assertEquals("Name", actualQmmProjectInfo.getName());
    assertEquals("Path", actualQmmProjectInfo.getPath());
    assertTrue(actualQmmProjectInfo.isAnonymous());
  }

  /**
   * Test {@link QMMProjectInfo#QMMProjectInfo(DBPProject)}.
   *
   * <ul>
   *   <li>Then return Id is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link QMMProjectInfo#QMMProjectInfo(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMProjectInfo.<init>(DBPProject)"})
  public void testNewQMMProjectInfo_thenReturnIdIs42() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getSessionContext())
        .thenReturn(new SessionContextImpl(mock(SMSessionContext.class)));
    when(project.getId()).thenReturn("42");
    when(project.getName()).thenReturn("Name");
    when(project.getAbsolutePath())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    UUID randomUUIDResult = UUID.randomUUID();
    when(project.getProjectID()).thenReturn(randomUUIDResult);

    // Act
    QMMProjectInfo actualQmmProjectInfo = new QMMProjectInfo(project);

    // Assert
    verify(project).getAbsolutePath();
    verify(project).getId();
    verify(project).getName();
    verify(project).getProjectID();
    verify(project).getSessionContext();
    assertEquals("42", actualQmmProjectInfo.getId());
    assertEquals("Name", actualQmmProjectInfo.getName());
    assertTrue(actualQmmProjectInfo.isAnonymous());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(),
        actualQmmProjectInfo.getPath());
    assertSame(randomUUIDResult, actualQmmProjectInfo.getUuid());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMMProjectInfo#getId()}
   *   <li>{@link QMMProjectInfo#getName()}
   *   <li>{@link QMMProjectInfo#getPath()}
   *   <li>{@link QMMProjectInfo#getUuid()}
   *   <li>{@link QMMProjectInfo#isAnonymous()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String QMMProjectInfo.getId()",
    "String QMMProjectInfo.getName()",
    "String QMMProjectInfo.getPath()",
    "UUID QMMProjectInfo.getUuid()",
    "boolean QMMProjectInfo.isAnonymous()"
  })
  public void testGettersAndSetters() {
    // Arrange
    QMMProjectInfo qmmProjectInfo =
        QMMProjectInfo.builder()
            .setId("42")
            .setAnonymous(true)
            .setName("Name")
            .setPath("Path")
            .build();

    // Act
    String actualId = qmmProjectInfo.getId();
    String actualName = qmmProjectInfo.getName();
    String actualPath = qmmProjectInfo.getPath();
    qmmProjectInfo.getUuid();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Name", actualName);
    assertEquals("Path", actualPath);
    assertTrue(qmmProjectInfo.isAnonymous());
  }
}
