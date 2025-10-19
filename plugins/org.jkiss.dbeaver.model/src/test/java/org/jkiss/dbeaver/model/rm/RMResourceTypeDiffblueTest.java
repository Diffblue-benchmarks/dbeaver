package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.app.DBPResourceTypeDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class RMResourceTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMResourceType#RMResourceType()}
   *   <li>{@link RMResourceType#setDisplayName(String)}
   *   <li>{@link RMResourceType#setFileExtensions(String[])}
   *   <li>{@link RMResourceType#setFolderIcon(String)}
   *   <li>{@link RMResourceType#setIcon(String)}
   *   <li>{@link RMResourceType#setId(String)}
   *   <li>{@link RMResourceType#setRootFolder(String)}
   *   <li>{@link RMResourceType#getDisplayName()}
   *   <li>{@link RMResourceType#getFileExtensions()}
   *   <li>{@link RMResourceType#getFolderIcon()}
   *   <li>{@link RMResourceType#getIcon()}
   *   <li>{@link RMResourceType#getId()}
   *   <li>{@link RMResourceType#getRootFolder()}
   *   <li>{@link RMResourceType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMResourceType.<init>()",
    "String RMResourceType.getDisplayName()",
    "String[] RMResourceType.getFileExtensions()",
    "String RMResourceType.getFolderIcon()",
    "String RMResourceType.getIcon()",
    "String RMResourceType.getId()",
    "String RMResourceType.getRootFolder()",
    "void RMResourceType.setDisplayName(String)",
    "void RMResourceType.setFileExtensions(String[])",
    "void RMResourceType.setFolderIcon(String)",
    "void RMResourceType.setIcon(String)",
    "void RMResourceType.setId(String)",
    "void RMResourceType.setRootFolder(String)",
    "String RMResourceType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    RMResourceType actualRmResourceType = new RMResourceType();
    actualRmResourceType.setDisplayName("Display Name");
    String[] fileExtensions = new String[] {"File Extensions"};
    actualRmResourceType.setFileExtensions(fileExtensions);
    actualRmResourceType.setFolderIcon("Folder Icon");
    actualRmResourceType.setIcon("Icon");
    actualRmResourceType.setId("42");
    actualRmResourceType.setRootFolder("Root Folder");
    String actualDisplayName = actualRmResourceType.getDisplayName();
    String[] actualFileExtensions = actualRmResourceType.getFileExtensions();
    String actualFolderIcon = actualRmResourceType.getFolderIcon();
    String actualIcon = actualRmResourceType.getIcon();
    String actualId = actualRmResourceType.getId();
    String actualRootFolder = actualRmResourceType.getRootFolder();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualRmResourceType.toString());
    assertEquals("Display Name", actualDisplayName);
    assertEquals("Folder Icon", actualFolderIcon);
    assertEquals("Icon", actualIcon);
    assertEquals("Root Folder", actualRootFolder);
    assertSame(fileExtensions, actualFileExtensions);
    assertArrayEquals(new String[] {"File Extensions"}, actualFileExtensions);
  }

  /**
   * Test {@link RMResourceType#RMResourceType(DBPResourceTypeDescriptor)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return FolderIcon is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RMResourceType#RMResourceType(DBPResourceTypeDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMResourceType.<init>(DBPResourceTypeDescriptor)"})
  public void testNewRMResourceType_givenNull_thenReturnFolderIconIsNull() {
    // Arrange
    DBPImage dbpImage = mock(DBPImage.class);
    when(dbpImage.getLocation()).thenReturn("Location");

    DBPResourceTypeDescriptor rtd = mock(DBPResourceTypeDescriptor.class);
    when(rtd.getFolderIcon()).thenReturn(null);
    when(rtd.getDefaultRoot(Mockito.<DBPProject>any())).thenReturn("Default Root");
    when(rtd.getFileExtensions()).thenReturn(new String[] {"File Extensions"});
    when(rtd.getId()).thenReturn("42");
    when(rtd.getName()).thenReturn("Name");
    when(rtd.getIcon()).thenReturn(dbpImage);

    // Act
    RMResourceType actualRmResourceType = new RMResourceType(rtd);

    // Assert
    verify(dbpImage).getLocation();
    verify(rtd).getDefaultRoot(isNull());
    verify(rtd).getFileExtensions();
    verify(rtd).getFolderIcon();
    verify(rtd).getIcon();
    verify(rtd).getId();
    verify(rtd).getName();
    assertEquals("42", actualRmResourceType.getId());
    assertEquals("42", actualRmResourceType.toString());
    assertEquals("Default Root", actualRmResourceType.getRootFolder());
    assertEquals("Location", actualRmResourceType.getIcon());
    assertEquals("Name", actualRmResourceType.getDisplayName());
    assertNull(actualRmResourceType.getFolderIcon());
    assertArrayEquals(new String[] {"File Extensions"}, actualRmResourceType.getFileExtensions());
  }

  /**
   * Test {@link RMResourceType#RMResourceType(DBPResourceTypeDescriptor)}.
   *
   * <ul>
   *   <li>Then return FolderIcon is {@code Location}.
   * </ul>
   *
   * <p>Method under test: {@link RMResourceType#RMResourceType(DBPResourceTypeDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMResourceType.<init>(DBPResourceTypeDescriptor)"})
  public void testNewRMResourceType_thenReturnFolderIconIsLocation() {
    // Arrange
    DBPImage dbpImage = mock(DBPImage.class);
    when(dbpImage.getLocation()).thenReturn("Location");

    DBPImage dbpImage2 = mock(DBPImage.class);
    when(dbpImage2.getLocation()).thenReturn("Location");

    DBPResourceTypeDescriptor rtd = mock(DBPResourceTypeDescriptor.class);
    when(rtd.getDefaultRoot(Mockito.<DBPProject>any())).thenReturn("Default Root");
    when(rtd.getFileExtensions()).thenReturn(new String[] {"File Extensions"});
    when(rtd.getFolderIcon()).thenReturn(dbpImage2);
    when(rtd.getId()).thenReturn("42");
    when(rtd.getName()).thenReturn("Name");
    when(rtd.getIcon()).thenReturn(dbpImage);

    // Act
    RMResourceType actualRmResourceType = new RMResourceType(rtd);

    // Assert
    verify(dbpImage2).getLocation();
    verify(dbpImage).getLocation();
    verify(rtd).getDefaultRoot(isNull());
    verify(rtd).getFileExtensions();
    verify(rtd, atLeast(1)).getFolderIcon();
    verify(rtd).getIcon();
    verify(rtd).getId();
    verify(rtd).getName();
    assertEquals("42", actualRmResourceType.getId());
    assertEquals("42", actualRmResourceType.toString());
    assertEquals("Default Root", actualRmResourceType.getRootFolder());
    assertEquals("Location", actualRmResourceType.getFolderIcon());
    assertEquals("Location", actualRmResourceType.getIcon());
    assertEquals("Name", actualRmResourceType.getDisplayName());
    assertArrayEquals(new String[] {"File Extensions"}, actualRmResourceType.getFileExtensions());
  }
}
