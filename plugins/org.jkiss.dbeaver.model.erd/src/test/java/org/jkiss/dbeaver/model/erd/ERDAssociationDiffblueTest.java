package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.navigator.DBNModel;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSEntityAssociation;
import org.jkiss.dbeaver.model.struct.DBSEntityReferrer;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ERDAssociationDiffblueTest {
  /**
   * Test {@link ERDAssociation#ERDAssociation(DBSEntityAssociation, ERDEntity, ERDEntity,
   * boolean)}.
   *
   * <ul>
   *   <li>Then SourceEntity return {@link ERDEntity}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#ERDAssociation(DBSEntityAssociation, ERDEntity,
   * ERDEntity, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDAssociation.<init>(DBSEntityAssociation, ERDEntity, ERDEntity, boolean)"
  })
  public void testNewERDAssociation_thenSourceEntityReturnERDEntity() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    // Act
    ERDAssociation actualErdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Assert
    ERDElement<?> sourceEntity2 = actualErdAssociation.getSourceEntity();
    assertTrue(sourceEntity2 instanceof ERDEntity);
    ERDElement<?> targetEntity2 = actualErdAssociation.getTargetEntity();
    assertTrue(targetEntity2 instanceof ERDEntity);
    DBSEntityAssociation object = actualErdAssociation.getObject();
    assertTrue(object instanceof ERDLogicalAssociation);
    assertEquals("Name", actualErdAssociation.getName());
    assertNull(actualErdAssociation.getUserData());
    assertNull(actualErdAssociation.getInitBends());
    List<ERDEntityAttribute> sourceAttributes = actualErdAssociation.getSourceAttributes();
    assertTrue(sourceAttributes.isEmpty());
    assertTrue(actualErdAssociation.isLogical());
    assertEquals(entity, sourceEntity2);
    assertEquals(entity, targetEntity2);
    assertSame(sourceEntity, sourceEntity2);
    assertSame(targetEntity, targetEntity2);
    assertSame(association, object);
    assertSame(sourceAttributes, actualErdAssociation.getTargetAttributes());
  }

  /**
   * Test {@link ERDAssociation#resolveAttributes()}.
   *
   * <p>Method under test: {@link ERDAssociation#resolveAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDAssociation.resolveAttributes()"})
  public void testResolveAttributes() throws DBException {
    // Arrange
    DBVEntityForeignKey association = mock(DBVEntityForeignKey.class);
    when(association.getAttributeReferences(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    erdAssociation.resolveAttributes();

    // Assert
    verify(association).getAttributeReferences(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDAssociation#resolveAttributes()}.
   *
   * <p>Method under test: {@link ERDAssociation#resolveAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDAssociation.resolveAttributes()"})
  public void testResolveAttributes2() throws DBException {
    // Arrange
    DBVEntityForeignKey association = mock(DBVEntityForeignKey.class);
    when(association.getAttributeReferences(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("Error resolving ERD association attributes"));
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    erdAssociation.resolveAttributes();

    // Assert
    verify(association).getAttributeReferences(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDAssociation#resolveAttributes()}.
   *
   * <p>Method under test: {@link ERDAssociation#resolveAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDAssociation.resolveAttributes()"})
  public void testResolveAttributes3() throws DBException {
    // Arrange
    DBVEntityForeignKey association = mock(DBVEntityForeignKey.class);
    when(association.getAttributeReferences(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException(""));
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    erdAssociation.resolveAttributes();

    // Assert
    verify(association).getAttributeReferences(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDAssociation#resolveAttributes(DBSEntityReferrer, ERDEntity, ERDEntity)} with
   * {@code DBSEntityReferrer}, {@code ERDEntity}, {@code ERDEntity}.
   *
   * <p>Method under test: {@link ERDAssociation#resolveAttributes(DBSEntityReferrer, ERDEntity,
   * ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDAssociation.resolveAttributes(DBSEntityReferrer, ERDEntity, ERDEntity)"
  })
  public void testResolveAttributesWithDBSEntityReferrerERDEntityERDEntity() throws DBException {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    when(entity3.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityAttribute);
    DBSDocumentConstraint association2 = new DBSDocumentConstraint(entity3);
    ERDEntity sourceEntity2 = new ERDEntity(mock(DBPDataSource.class));

    // Act
    erdAssociation.resolveAttributes(
        association2, sourceEntity2, new ERDEntity(mock(DBPDataSource.class)));

    // Assert
    verify(entity3).getDocumentAttribute(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDAssociation#resolveAttributes(DBSEntityReferrer, ERDEntity, ERDEntity)} with
   * {@code DBSEntityReferrer}, {@code ERDEntity}, {@code ERDEntity}.
   *
   * <p>Method under test: {@link ERDAssociation#resolveAttributes(DBSEntityReferrer, ERDEntity,
   * ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDAssociation.resolveAttributes(DBSEntityReferrer, ERDEntity, ERDEntity)"
  })
  public void testResolveAttributesWithDBSEntityReferrerERDEntityERDEntity2() throws DBException {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBSDocumentConstraint association2 = new DBSDocumentConstraint(entity3);
    ERDEntity sourceEntity2 = new ERDEntity(mock(DBPDataSource.class));

    // Act
    erdAssociation.resolveAttributes(
        association2, sourceEntity2, new ERDEntity(mock(DBPDataSource.class)));

    // Assert
    verify(entity3).getDocumentAttribute(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDAssociation#resolveAttributes(DBSEntityReferrer, ERDEntity, ERDEntity)} with
   * {@code DBSEntityReferrer}, {@code ERDEntity}, {@code ERDEntity}.
   *
   * <p>Method under test: {@link ERDAssociation#resolveAttributes(DBSEntityReferrer, ERDEntity,
   * ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDAssociation.resolveAttributes(DBSEntityReferrer, ERDEntity, ERDEntity)"
  })
  public void testResolveAttributesWithDBSEntityReferrerERDEntityERDEntity3() throws DBException {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("Error resolving ERD association attributes"));
    DBSDocumentConstraint association2 = new DBSDocumentConstraint(entity3);
    ERDEntity sourceEntity2 = new ERDEntity(mock(DBPDataSource.class));

    // Act
    erdAssociation.resolveAttributes(
        association2, sourceEntity2, new ERDEntity(mock(DBPDataSource.class)));

    // Assert
    verify(entity3).getDocumentAttribute(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDAssociation#resolveAttributes(DBSEntityReferrer, ERDEntity, ERDEntity)} with
   * {@code DBSEntityReferrer}, {@code ERDEntity}, {@code ERDEntity}.
   *
   * <p>Method under test: {@link ERDAssociation#resolveAttributes(DBSEntityReferrer, ERDEntity,
   * ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDAssociation.resolveAttributes(DBSEntityReferrer, ERDEntity, ERDEntity)"
  })
  public void testResolveAttributesWithDBSEntityReferrerERDEntityERDEntity4() throws DBException {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException(""));
    DBSDocumentConstraint association2 = new DBSDocumentConstraint(entity3);
    ERDEntity sourceEntity2 = new ERDEntity(mock(DBPDataSource.class));

    // Act
    erdAssociation.resolveAttributes(
        association2, sourceEntity2, new ERDEntity(mock(DBPDataSource.class)));

    // Assert
    verify(entity3).getDocumentAttribute(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link ERDAssociation#isLogical()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#isLogical()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDAssociation.isLogical()"})
  public void testIsLogical_thenReturnFalse() {
    // Arrange
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation = new ERDAssociation(null, sourceEntity, targetEntity, true);

    // Act and Assert
    assertFalse(erdAssociation.isLogical());
  }

  /**
   * Test {@link ERDAssociation#isLogical()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#isLogical()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDAssociation.isLogical()"})
  public void testIsLogical_thenReturnTrue() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act and Assert
    assertTrue(erdAssociation.isLogical());
  }

  /**
   * Test {@link ERDAssociation#getSourceAttributes()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#getSourceAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDAssociation.getSourceAttributes()"})
  public void testGetSourceAttributes_thenReturnEmpty() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act and Assert
    assertTrue(erdAssociation.getSourceAttributes().isEmpty());
  }

  /**
   * Test {@link ERDAssociation#getSourceAttributes()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#getSourceAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDAssociation.getSourceAttributes()"})
  public void testGetSourceAttributes_thenReturnSizeIsOne() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute sourceAttribute = new ERDEntityAttribute(attribute, true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    erdAssociation.addCondition(sourceAttribute, new ERDEntityAttribute(attribute2, true));

    // Act
    List<ERDEntityAttribute> actualSourceAttributes = erdAssociation.getSourceAttributes();

    // Assert
    assertEquals(1, actualSourceAttributes.size());
    assertSame(sourceAttribute, actualSourceAttributes.get(0));
  }

  /**
   * Test {@link ERDAssociation#getTargetAttributes()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#getTargetAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDAssociation.getTargetAttributes()"})
  public void testGetTargetAttributes_thenReturnEmpty() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act and Assert
    assertTrue(erdAssociation.getTargetAttributes().isEmpty());
  }

  /**
   * Test {@link ERDAssociation#getTargetAttributes()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#getTargetAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDAssociation.getTargetAttributes()"})
  public void testGetTargetAttributes_thenReturnSizeIsOne() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute sourceAttribute = new ERDEntityAttribute(attribute, true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute targetAttribute = new ERDEntityAttribute(attribute2, true);

    erdAssociation.addCondition(sourceAttribute, targetAttribute);

    // Act
    List<ERDEntityAttribute> actualTargetAttributes = erdAssociation.getTargetAttributes();

    // Assert
    assertEquals(1, actualTargetAttributes.size());
    assertSame(targetAttribute, actualTargetAttributes.get(0));
  }

  /**
   * Test {@link ERDAssociation#addCondition(ERDEntityAttribute, ERDEntityAttribute)}.
   *
   * <p>Method under test: {@link ERDAssociation#addCondition(ERDEntityAttribute,
   * ERDEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDAssociation.addCondition(ERDEntityAttribute, ERDEntityAttribute)"})
  public void testAddCondition() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute sourceAttribute = new ERDEntityAttribute(attribute, true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute targetAttribute = new ERDEntityAttribute(attribute2, true);

    // Act
    erdAssociation.addCondition(sourceAttribute, targetAttribute);

    // Assert
    List<ERDEntityAttribute> sourceAttributes = erdAssociation.getSourceAttributes();
    assertEquals(1, sourceAttributes.size());
    List<ERDEntityAttribute> targetAttributes = erdAssociation.getTargetAttributes();
    assertEquals(1, targetAttributes.size());
    assertSame(sourceAttribute, sourceAttributes.get(0));
    assertSame(targetAttribute, targetAttributes.get(0));
  }

  /**
   * Test {@link ERDAssociation#addCondition(ERDEntityAttribute, ERDEntityAttribute)}.
   *
   * <p>Method under test: {@link ERDAssociation#addCondition(ERDEntityAttribute,
   * ERDEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDAssociation.addCondition(ERDEntityAttribute, ERDEntityAttribute)"})
  public void testAddCondition2() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute sourceAttribute = new ERDEntityAttribute(attribute, true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    erdAssociation.addCondition(sourceAttribute, new ERDEntityAttribute(attribute2, true));
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity5 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute3 =
        new DBVEntityAttribute(entity5, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute sourceAttribute2 = new ERDEntityAttribute(attribute3, true);
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity6 = new DBVEntity(container4, "Name", "Description Column Names");
    DBVEntityAttribute attribute4 =
        new DBVEntityAttribute(entity6, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute targetAttribute = new ERDEntityAttribute(attribute4, true);

    // Act
    erdAssociation.addCondition(sourceAttribute2, targetAttribute);

    // Assert
    List<ERDEntityAttribute> sourceAttributes = erdAssociation.getSourceAttributes();
    assertEquals(2, sourceAttributes.size());
    List<ERDEntityAttribute> targetAttributes = erdAssociation.getTargetAttributes();
    assertEquals(2, targetAttributes.size());
    assertSame(sourceAttribute2, sourceAttributes.get(1));
    assertSame(targetAttribute, targetAttributes.get(1));
  }

  /**
   * Test {@link ERDAssociation#addCondition(ERDEntityAttribute, ERDEntityAttribute)}.
   *
   * <p>Method under test: {@link ERDAssociation#addCondition(ERDEntityAttribute,
   * ERDEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDAssociation.addCondition(ERDEntityAttribute, ERDEntityAttribute)"})
  public void testAddCondition3() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act
    erdAssociation.addCondition(null, null);

    // Assert that nothing has changed
    List<ERDEntityAttribute> sourceAttributes = erdAssociation.getSourceAttributes();
    assertTrue(sourceAttributes.isEmpty());
    assertSame(sourceAttributes, erdAssociation.getTargetAttributes());
  }

  /**
   * Test {@link ERDAssociation#getName()}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   *   <li>Then return {@code vfk_Name_?}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDAssociation.getName()"})
  public void testGetName_givenDBVEntityForeignKeyWithEntityIsDBVEntity_thenReturnVfkName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act and Assert
    assertEquals("vfk_Name_?", erdAssociation.getName());
  }

  /**
   * Test {@link ERDAssociation#getName()}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDAssociation.getName()"})
  public void testGetName_thenReturnName() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act and Assert
    assertEquals("Name", erdAssociation.getName());
  }

  /**
   * Test {@link ERDAssociation#getName()}.
   *
   * <ul>
   *   <li>Then return {@code vfk_Name_42}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDAssociation.getName()"})
  public void testGetName_thenReturnVfkName42() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityForeignKey association = new DBVEntityForeignKey(entity);
    association.setRefEntityId("42");
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);

    // Act and Assert
    assertEquals("vfk_Name_42", erdAssociation.getName());
  }

  /**
   * Test {@link ERDAssociation#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link ERDPersistedState#ATTR_NAME} is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDAssociation.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnAttr_nameIsName() {
    // Arrange
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    sourceEntity.setObject(dbvEntity);
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdAssociation.toMap(context, true);

    // Assert
    assertEquals(2, actualToMapResult.size());
    assertEquals("Name", actualToMapResult.get(ERDPersistedState.ATTR_NAME));
    assertEquals("erdkey", actualToMapResult.get(ERDPersistedState.ATTR_TYPE));
  }

  /**
   * Test {@link ERDAssociation#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link ERDPersistedState#ATTR_TYPE} is {@code vfk}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDAssociation.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnAttr_typeIsVfk() {
    // Arrange
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    sourceEntity.setObject(dbvEntity);
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), ERDPersistedState.ATTR_NAME);
    DBVEntity entity =
        new DBVEntity(container2, ERDPersistedState.ATTR_NAME, ERDPersistedState.ATTR_NAME);
    DBVEntityForeignKey association = new DBVEntityForeignKey(entity);
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdAssociation.toMap(context, true);

    // Assert
    assertEquals(2, actualToMapResult.size());
    assertEquals("vfk", actualToMapResult.get(ERDPersistedState.ATTR_TYPE));
    assertEquals("vfk_name_?", actualToMapResult.get(ERDPersistedState.ATTR_NAME));
  }

  /**
   * Test {@link ERDAssociation#toMap(ERDContext, boolean)}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link ERDAssociation#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDAssociation.toMap(ERDContext, boolean)"})
  public void testToMap_thenReturnSizeIsFour() {
    // Arrange
    ERDEntity sourceEntity = new ERDEntity(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");
    sourceEntity.setObject(dbvEntity);
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDEntity entity2 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(entity2, "Name", "The characteristics of someone or something");

    ERDLogicalAssociation association =
        new ERDLogicalAssociation(
            entity, "Name", "The characteristics of someone or something", pk);
    ERDEntity targetEntity = new ERDEntity(mock(DBPDataSource.class));

    ERDAssociation erdAssociation =
        new ERDAssociation(association, sourceEntity, targetEntity, true);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity3, mock(DBVEntityAttribute.class), "Name");
    ERDEntityAttribute sourceAttribute = new ERDEntityAttribute(attribute, true);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity4, mock(DBVEntityAttribute.class), "Name");
    erdAssociation.addCondition(sourceAttribute, new ERDEntityAttribute(attribute2, true));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdAssociation.toMap(context, true);

    // Assert
    assertEquals(4, actualToMapResult.size());
    Object getResult = actualToMapResult.get("primary-attributes");
    assertTrue(getResult instanceof List);
    assertEquals(1, ((List<String>) getResult).size());
    assertEquals("Name", ((List<String>) getResult).get(0));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_NAME));
    assertTrue(actualToMapResult.containsKey(ERDPersistedState.ATTR_TYPE));
    assertEquals(getResult, actualToMapResult.get("foreign-attributes"));
  }
}
