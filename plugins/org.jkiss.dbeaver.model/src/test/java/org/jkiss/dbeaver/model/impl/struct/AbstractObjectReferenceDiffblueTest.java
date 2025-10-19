package org.jkiss.dbeaver.model.impl.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvaluationContext;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObjectType;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AbstractObjectReferenceDiffblueTest {
  /**
   * Test {@link AbstractObjectReference#getName()}.
   *
   * <p>Method under test: {@link AbstractObjectReference#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.getName()"})
  public void testGetName() {
    // Arrange
    DBSDocumentConstraint container = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act and Assert
    assertEquals("DocumentKey", directObjectReference.getName());
  }

  /**
   * Test {@link AbstractObjectReference#getContainer()}.
   *
   * <p>Method under test: {@link AbstractObjectReference#getContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSObject AbstractObjectReference.getContainer()"
  })
  public void testGetContainer() {
    // Arrange
    DBSDocumentConstraint container = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act and Assert
    assertSame(container, directObjectReference.getContainer());
  }

  /**
   * Test {@link AbstractObjectReference#getObjectClass()}.
   *
   * <p>Method under test: {@link AbstractObjectReference#getObjectClass()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractObjectReference.getObjectClass()"})
  public void testGetObjectClass() {
    // Arrange
    DBSDocumentConstraint container = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    Class<?> actualObjectClass = directObjectReference.getObjectClass();

    // Assert
    Class<DBSDocumentConstraint> expectedObjectClass = DBSDocumentConstraint.class;
    assertEquals(expectedObjectClass, actualObjectClass);
  }

  /**
   * Test {@link AbstractObjectReference#getObjectDescription()}.
   *
   * <p>Method under test: {@link AbstractObjectReference#getObjectDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.getObjectDescription()"})
  public void testGetObjectDescription() {
    // Arrange
    DBSDocumentConstraint container = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act and Assert
    assertNull(directObjectReference.getObjectDescription());
  }

  /**
   * Test {@link AbstractObjectReference#getObjectType()}.
   *
   * <p>Method under test: {@link AbstractObjectReference#getObjectType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectType AbstractObjectReference.getObjectType()"})
  public void testGetObjectType() {
    // Arrange
    DBSDocumentConstraint container = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    DBSObjectType actualObjectType = directObjectReference.getObjectType();

    // Assert
    assertSame(((RelationalObjectType) actualObjectType).TYPE_CATALOG, actualObjectType);
  }

  /**
   * Test {@link AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <p>Method under test: {@link
   * AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    DirectObjectReference directObjectReference =
        new DirectObjectReference(null, RelationalObjectType.TYPE_CATALOG, object);

    // Act and Assert
    assertEquals(
        "DocumentKey", directObjectReference.getFullyQualifiedName(DBPEvaluationContext.UI));
  }

  /**
   * Test {@link AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity copy = new DBVEntity(container2, "?", "?");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityForeignKey container3 = new DBVEntityForeignKey(entity);
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container3, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    String actualFullyQualifiedName =
        directObjectReference.getFullyQualifiedName(DBPEvaluationContext.UI);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("DocumentKey", actualFullyQualifiedName);
  }

  /**
   * Test {@link AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Then return {@code 42A42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_thenReturn42a42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint container = new DBSDocumentConstraint(entity);
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    String actualFullyQualifiedName =
        directObjectReference.getFullyQualifiedName(DBPEvaluationContext.UI);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("DocumentKey", true, false);
    verify(sqlDialect).getStructSeparator();
    verify(entity).getDataSource();
    assertEquals("42A42", actualFullyQualifiedName);
  }

  /**
   * Test {@link AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Then return {@code "DocumentKey"."DocumentKey"}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_thenReturnDocumentKeyDocumentKey() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint container = new DBSDocumentConstraint(entity);
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    String actualFullyQualifiedName =
        directObjectReference.getFullyQualifiedName(DBPEvaluationContext.UI);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(entity).getDataSource();
    assertEquals("\"DocumentKey\".\"DocumentKey\"", actualFullyQualifiedName);
  }

  /**
   * Test {@link AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <ul>
   *   <li>Then return {@code "vfk_DocumentKey_?"."DocumentKey"}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractObjectReference#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName_thenReturnVfkDocumentKeyDocumentKey() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "DocumentKey");
    DBVEntity entity = new DBVEntity(container, "DocumentKey", "DocumentKey");
    DBVEntityForeignKey container2 = new DBVEntityForeignKey(entity);
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container2, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    String actualFullyQualifiedName =
        directObjectReference.getFullyQualifiedName(DBPEvaluationContext.UI);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"vfk_DocumentKey_?\".\"DocumentKey\"", actualFullyQualifiedName);
  }

  /**
   * Test {@link AbstractObjectReference#toString()}.
   *
   * <p>Method under test: {@link AbstractObjectReference#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.toString()"})
  public void testToString() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    DirectObjectReference directObjectReference =
        new DirectObjectReference(null, RelationalObjectType.TYPE_CATALOG, object);

    // Act and Assert
    assertEquals("DocumentKey", directObjectReference.toString());
  }

  /**
   * Test {@link AbstractObjectReference#toString()}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectReference#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.toString()"})
  public void testToString_givenDBPDataSourceContainerGetIdReturn42_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "?");
    DBVEntity copy = new DBVEntity(container2, "?", "?");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityForeignKey container3 = new DBVEntityForeignKey(entity);
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container3, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    String actualToStringResult = directObjectReference.toString();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("DocumentKey", actualToStringResult);
  }

  /**
   * Test {@link AbstractObjectReference#toString()}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getStructSeparator()} return {@code A}.
   *   <li>Then return {@code 42A42}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectReference#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.toString()"})
  public void testToString_givenSQLDialectGetStructSeparatorReturnA_thenReturn42a42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint container = new DBSDocumentConstraint(entity);
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    String actualToStringResult = directObjectReference.toString();

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("DocumentKey", true, false);
    verify(sqlDialect).getStructSeparator();
    verify(entity).getDataSource();
    assertEquals("42A42", actualToStringResult);
  }

  /**
   * Test {@link AbstractObjectReference#toString()}.
   *
   * <ul>
   *   <li>Then return {@code "DocumentKey"."DocumentKey"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectReference#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.toString()"})
  public void testToString_thenReturnDocumentKeyDocumentKey() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint container = new DBSDocumentConstraint(entity);
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    String actualToStringResult = directObjectReference.toString();

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(entity).getDataSource();
    assertEquals("\"DocumentKey\".\"DocumentKey\"", actualToStringResult);
  }

  /**
   * Test {@link AbstractObjectReference#toString()}.
   *
   * <ul>
   *   <li>Then return {@code "vfk_DocumentKey_?"."DocumentKey"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectReference#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectReference.toString()"})
  public void testToString_thenReturnVfkDocumentKeyDocumentKey() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "DocumentKey");
    DBVEntity entity = new DBVEntity(container, "DocumentKey", "DocumentKey");
    DBVEntityForeignKey container2 = new DBVEntityForeignKey(entity);
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DirectObjectReference directObjectReference =
        new DirectObjectReference(container2, RelationalObjectType.TYPE_CATALOG, object);

    // Act
    String actualToStringResult = directObjectReference.toString();

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"vfk_DocumentKey_?\".\"DocumentKey\"", actualToStringResult);
  }
}
