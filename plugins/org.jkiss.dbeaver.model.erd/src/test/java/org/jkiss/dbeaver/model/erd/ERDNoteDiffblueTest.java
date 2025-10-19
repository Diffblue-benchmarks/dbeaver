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
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.navigator.DBNModel;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ERDNoteDiffblueTest {
  /**
   * Test {@link ERDNote#ERDNote(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ERDNote#ERDNote(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDNote.<init>(String)"})
  public void testNewERDNote_whenNull_thenReturnNameIsEmptyString() {
    // Arrange and Act
    ERDNote actualErdNote = new ERDNote(null);

    // Assert
    assertEquals("", actualErdNote.getName());
    assertEquals("", actualErdNote.getObject());
    assertNull(actualErdNote.getUserData());
    assertFalse(actualErdNote.hasSelfLinks());
    List<ERDAssociation> associations = actualErdNote.getAssociations();
    assertTrue(associations.isEmpty());
    assertSame(associations, actualErdNote.getReferences());
  }

  /**
   * Test {@link ERDNote#ERDNote(String)}.
   *
   * <ul>
   *   <li>When {@code Text}.
   *   <li>Then return Name is {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link ERDNote#ERDNote(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDNote.<init>(String)"})
  public void testNewERDNote_whenText_thenReturnNameIsText() {
    // Arrange and Act
    ERDNote actualErdNote = new ERDNote("Text");

    // Assert
    assertEquals("Text", actualErdNote.getName());
    assertEquals("Text", actualErdNote.getObject());
    assertNull(actualErdNote.getUserData());
    assertFalse(actualErdNote.hasSelfLinks());
    List<ERDAssociation> associations = actualErdNote.getAssociations();
    assertTrue(associations.isEmpty());
    assertSame(associations, actualErdNote.getReferences());
  }

  /**
   * Test {@link ERDNote#getName()}.
   *
   * <p>Method under test: {@link ERDNote#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDNote.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("Text", new ERDNote("Text").getName());
  }

  /**
   * Test {@link ERDNote#setObject(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ERDNote#ERDNote(String)} with {@code Text} Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ERDNote#setObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDNote.setObject(String)"})
  public void testSetObjectWithString_whenNull_thenERDNoteWithTextNameIsEmptyString() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");

    // Act
    erdNote.setObject(null);

    // Assert
    assertEquals("", erdNote.getName());
    assertEquals("", erdNote.getObject());
  }

  /**
   * Test {@link ERDNote#setObject(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then {@link ERDNote#ERDNote(String)} with {@code Text} Name is {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link ERDNote#setObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDNote.setObject(String)"})
  public void testSetObjectWithString_whenObject_thenERDNoteWithTextNameIsObject() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");

    // Act
    erdNote.setObject("Object");

    // Assert
    assertEquals("Object", erdNote.getName());
    assertEquals("Object", erdNote.getObject());
  }

  /**
   * Test {@link ERDNote#fromMap(ERDContext, Map)}.
   *
   * <ul>
   *   <li>Given {@link ERDPersistedState#TAG_NOTE}.
   *   <li>When {@link HashMap#HashMap()} {@link ERDPersistedState#TAG_NOTE} is {@code Map}.
   *   <li>Then {@link ERDNote#ERDNote(String)} with {@code Text} Name is {@code Map}.
   * </ul>
   *
   * <p>Method under test: {@link ERDNote#fromMap(ERDContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDNote.fromMap(ERDContext, Map)"})
  public void testFromMap_givenTag_note_whenHashMapTag_noteIsMap_thenERDNoteWithTextNameIsMap() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    HashMap<String, Object> map = new HashMap<>();
    map.put(ERDPersistedState.TAG_NOTE, "Map");

    // Act
    erdNote.fromMap(context, map);

    // Assert
    assertEquals("Map", erdNote.getName());
    assertEquals("Map", erdNote.getObject());
  }

  /**
   * Test {@link ERDNote#fromMap(ERDContext, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then {@link ERDNote#ERDNote(String)} with {@code Text} Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ERDNote#fromMap(ERDContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDNote.fromMap(ERDContext, Map)"})
  public void testFromMap_whenHashMap_thenERDNoteWithTextNameIsEmptyString() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    erdNote.fromMap(context, new HashMap<>());

    // Assert
    assertEquals("", erdNote.getName());
    assertEquals("", erdNote.getObject());
  }

  /**
   * Test {@link ERDNote#toMap(ERDContext, boolean)}.
   *
   * <p>Method under test: {@link ERDNote#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDNote.toMap(ERDContext, boolean)"})
  public void testToMap() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdNote.toMap(context, true);

    // Assert
    assertEquals(1, actualToMapResult.size());
    assertEquals("Text", actualToMapResult.get(ERDPersistedState.TAG_NOTE));
  }
}
