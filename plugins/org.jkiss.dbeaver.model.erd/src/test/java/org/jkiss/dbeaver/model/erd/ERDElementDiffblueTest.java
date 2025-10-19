package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ERDElementDiffblueTest {
  /**
   * Test {@link ERDElement#addAssociation(ERDAssociation, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#addAssociation(ERDAssociation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDElement.addAssociation(ERDAssociation, boolean)"})
  public void testAddAssociation_whenFalse() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");

    // Act
    erdNote.addAssociation(null, false);

    // Assert
    List<ERDAssociation> associations = erdNote.getAssociations();
    assertEquals(1, associations.size());
    assertNull(associations.get(0));
    assertTrue(erdNote.getReferences().isEmpty());
  }

  /**
   * Test {@link ERDElement#addAssociation(ERDAssociation, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#addAssociation(ERDAssociation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDElement.addAssociation(ERDAssociation, boolean)"})
  public void testAddAssociation_whenTrue() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");

    // Act
    erdNote.addAssociation(null, true);

    // Assert
    List<ERDAssociation> associations = erdNote.getAssociations();
    assertEquals(1, associations.size());
    assertNull(associations.get(0));
    assertTrue(erdNote.getReferences().isEmpty());
  }

  /**
   * Test {@link ERDElement#addReferenceAssociation(ERDAssociation, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#addReferenceAssociation(ERDAssociation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDElement.addReferenceAssociation(ERDAssociation, boolean)"})
  public void testAddReferenceAssociation_whenFalse() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");

    // Act
    erdNote.addReferenceAssociation(null, false);

    // Assert
    List<ERDAssociation> references = erdNote.getReferences();
    assertEquals(1, references.size());
    assertNull(references.get(0));
  }

  /**
   * Test {@link ERDElement#addReferenceAssociation(ERDAssociation, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#addReferenceAssociation(ERDAssociation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDElement.addReferenceAssociation(ERDAssociation, boolean)"})
  public void testAddReferenceAssociation_whenTrue() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");

    // Act
    erdNote.addReferenceAssociation(null, true);

    // Assert
    List<ERDAssociation> references = erdNote.getReferences();
    assertEquals(1, references.size());
    assertNull(references.get(0));
  }

  /**
   * Test {@link ERDElement#getAssociations()}.
   *
   * <ul>
   *   <li>Given {@link ERDNote#ERDNote(String)} with {@code Text}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#getAssociations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDElement.getAssociations()"})
  public void testGetAssociations_givenERDNoteWithText_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new ERDNote("Text").getAssociations().isEmpty());
  }

  /**
   * Test {@link ERDElement#getAssociations()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#getAssociations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDElement.getAssociations()"})
  public void testGetAssociations_thenReturnSizeIsOne() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");
    erdNote.addAssociation(null, true);

    // Act
    List<ERDAssociation> actualAssociations = erdNote.getAssociations();

    // Assert
    assertEquals(1, actualAssociations.size());
    assertNull(actualAssociations.get(0));
  }

  /**
   * Test {@link ERDElement#getReferences()}.
   *
   * <ul>
   *   <li>Given {@link ERDNote#ERDNote(String)} with {@code Text}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#getReferences()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDElement.getReferences()"})
  public void testGetReferences_givenERDNoteWithText_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new ERDNote("Text").getReferences().isEmpty());
  }

  /**
   * Test {@link ERDElement#getReferences()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#getReferences()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDElement.getReferences()"})
  public void testGetReferences_thenReturnSizeIsOne() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");
    erdNote.addReferenceAssociation(null, true);

    // Act
    List<ERDAssociation> actualReferences = erdNote.getReferences();

    // Assert
    assertEquals(1, actualReferences.size());
    assertNull(actualReferences.get(0));
  }

  /**
   * Test {@link ERDElement#hasSelfLinks()}.
   *
   * <ul>
   *   <li>Given {@link ERDNote#ERDNote(String)} with {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#hasSelfLinks()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDElement.hasSelfLinks()"})
  public void testHasSelfLinks_givenERDNoteWithText() {
    // Arrange, Act and Assert
    assertFalse(new ERDNote("Text").hasSelfLinks());
  }

  /**
   * Test {@link ERDElement#hasSelfLinks()}.
   *
   * <ul>
   *   <li>Then calls {@link PropertyChangeListener#propertyChange(PropertyChangeEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#hasSelfLinks()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDElement.hasSelfLinks()"})
  public void testHasSelfLinks_thenCallsPropertyChange() {
    // Arrange
    PropertyChangeListener l = mock(PropertyChangeListener.class);
    doNothing().when(l).propertyChange(Mockito.<PropertyChangeEvent>any());

    ERDNote erdNote = new ERDNote("Text");
    erdNote.addPropertyChangeListener(l);
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation rel = new ERDAssociation(association, sourceEntity, targetEntity, true);
    erdNote.addAssociation(rel, true);

    // Act
    boolean actualHasSelfLinksResult = erdNote.hasSelfLinks();

    // Assert
    verify(l).propertyChange(isA(PropertyChangeEvent.class));
    assertFalse(actualHasSelfLinksResult);
  }

  /**
   * Test {@link ERDElement#hasAssociationsWith(ERDElement)}.
   *
   * <ul>
   *   <li>Given {@link ERDNote#ERDNote(String)} with {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#hasAssociationsWith(ERDElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDElement.hasAssociationsWith(ERDElement)"})
  public void testHasAssociationsWith_givenERDNoteWithText() {
    // Arrange
    ERDNote erdNote = new ERDNote("Text");

    // Act
    boolean actualHasAssociationsWithResult = erdNote.hasAssociationsWith(new ERDNote("Text"));

    // Assert
    assertFalse(actualHasAssociationsWithResult);
  }

  /**
   * Test {@link ERDElement#hasAssociationsWith(ERDElement)}.
   *
   * <ul>
   *   <li>Then calls {@link PropertyChangeListener#propertyChange(PropertyChangeEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link ERDElement#hasAssociationsWith(ERDElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDElement.hasAssociationsWith(ERDElement)"})
  public void testHasAssociationsWith_thenCallsPropertyChange() {
    // Arrange
    PropertyChangeListener l = mock(PropertyChangeListener.class);
    doNothing().when(l).propertyChange(Mockito.<PropertyChangeEvent>any());

    ERDNote erdNote = new ERDNote("Text");
    erdNote.addPropertyChangeListener(l);
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation rel = new ERDAssociation(association, sourceEntity, targetEntity, true);
    erdNote.addAssociation(rel, true);

    // Act
    boolean actualHasAssociationsWithResult = erdNote.hasAssociationsWith(new ERDNote("Text"));

    // Assert
    verify(l).propertyChange(isA(PropertyChangeEvent.class));
    assertFalse(actualHasAssociationsWithResult);
  }
}
