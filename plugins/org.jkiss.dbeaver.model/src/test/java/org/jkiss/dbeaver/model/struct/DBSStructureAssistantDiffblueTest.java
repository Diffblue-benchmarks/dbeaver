package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.struct.RelationalObjectType;
import org.jkiss.dbeaver.model.struct.DBSStructureAssistant.ObjectsSearchParams;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSStructureAssistantDiffblueTest {
  /**
   * Test ObjectsSearchParams getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ObjectsSearchParams#ObjectsSearchParams(DBSObjectType[], String)}
   *   <li>{@link ObjectsSearchParams#setCaseSensitive(boolean)}
   *   <li>{@link ObjectsSearchParams#setGlobalSearch(boolean)}
   *   <li>{@link ObjectsSearchParams#setLikeCondition(boolean)}
   *   <li>{@link ObjectsSearchParams#setMask(String)}
   *   <li>{@link ObjectsSearchParams#setMaxResults(int)}
   *   <li>{@link ObjectsSearchParams#setParentObject(DBSObject)}
   *   <li>{@link ObjectsSearchParams#setSearchInComments(boolean)}
   *   <li>{@link ObjectsSearchParams#setSearchInDefinitions(boolean)}
   *   <li>{@link ObjectsSearchParams#getMask()}
   *   <li>{@link ObjectsSearchParams#getMaxResults()}
   *   <li>{@link ObjectsSearchParams#getObjectTypes()}
   *   <li>{@link ObjectsSearchParams#getParentObject()}
   *   <li>{@link ObjectsSearchParams#isCaseSensitive()}
   *   <li>{@link ObjectsSearchParams#isGlobalSearch()}
   *   <li>{@link ObjectsSearchParams#isLikeCondition()}
   *   <li>{@link ObjectsSearchParams#isSearchInComments()}
   *   <li>{@link ObjectsSearchParams#isSearchInDefinitions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ObjectsSearchParams.<init>(DBSObjectType[], String)",
    "String ObjectsSearchParams.getMask()",
    "int ObjectsSearchParams.getMaxResults()",
    "DBSObjectType[] ObjectsSearchParams.getObjectTypes()",
    "DBSObject ObjectsSearchParams.getParentObject()",
    "boolean ObjectsSearchParams.isCaseSensitive()",
    "boolean ObjectsSearchParams.isGlobalSearch()",
    "boolean ObjectsSearchParams.isLikeCondition()",
    "boolean ObjectsSearchParams.isSearchInComments()",
    "boolean ObjectsSearchParams.isSearchInDefinitions()",
    "void ObjectsSearchParams.setCaseSensitive(boolean)",
    "void ObjectsSearchParams.setGlobalSearch(boolean)",
    "void ObjectsSearchParams.setLikeCondition(boolean)",
    "void ObjectsSearchParams.setMask(String)",
    "void ObjectsSearchParams.setMaxResults(int)",
    "void ObjectsSearchParams.setParentObject(DBSObject)",
    "void ObjectsSearchParams.setSearchInComments(boolean)",
    "void ObjectsSearchParams.setSearchInDefinitions(boolean)"
  })
  public void testObjectsSearchParamsGettersAndSetters() {
    // Arrange
    DBSObjectType[] objectTypes = new DBSObjectType[] {RelationalObjectType.TYPE_CATALOG};

    // Act
    ObjectsSearchParams actualObjectsSearchParams = new ObjectsSearchParams(objectTypes, "Mask");
    actualObjectsSearchParams.setCaseSensitive(true);
    actualObjectsSearchParams.setGlobalSearch(true);
    actualObjectsSearchParams.setLikeCondition(true);
    actualObjectsSearchParams.setMask("Mask");
    actualObjectsSearchParams.setMaxResults(3);
    DBSDocumentConstraint parentObject =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    actualObjectsSearchParams.setParentObject(parentObject);
    actualObjectsSearchParams.setSearchInComments(true);
    actualObjectsSearchParams.setSearchInDefinitions(true);
    String actualMask = actualObjectsSearchParams.getMask();
    int actualMaxResults = actualObjectsSearchParams.getMaxResults();
    DBSObjectType[] actualObjectTypes = actualObjectsSearchParams.getObjectTypes();
    DBSObject actualParentObject = actualObjectsSearchParams.getParentObject();
    boolean actualIsCaseSensitiveResult = actualObjectsSearchParams.isCaseSensitive();
    boolean actualIsGlobalSearchResult = actualObjectsSearchParams.isGlobalSearch();
    boolean actualIsLikeConditionResult = actualObjectsSearchParams.isLikeCondition();
    boolean actualIsSearchInCommentsResult = actualObjectsSearchParams.isSearchInComments();

    // Assert
    assertEquals("Mask", actualMask);
    assertEquals(3, actualMaxResults);
    assertTrue(actualIsCaseSensitiveResult);
    assertTrue(actualIsGlobalSearchResult);
    assertTrue(actualIsLikeConditionResult);
    assertTrue(actualIsSearchInCommentsResult);
    assertTrue(actualObjectsSearchParams.isSearchInDefinitions());
    assertSame(parentObject, actualParentObject);
    assertSame(objectTypes, actualObjectTypes);
  }
}
