package org.jkiss.dbeaver.runtime.properties;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourcePropertyFilterDiffblueTest {
  /**
   * Test {@link DataSourcePropertyFilter#isExpensivePropertiesReadEnabledFor(DBSObject)}.
   *
   * <p>Method under test: {@link
   * DataSourcePropertyFilter#isExpensivePropertiesReadEnabledFor(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DataSourcePropertyFilter.isExpensivePropertiesReadEnabledFor(DBSObject)"
  })
  public void testIsExpensivePropertiesReadEnabledFor() {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getName()).thenReturn("Name");
    when(entity.getParentObject()).thenReturn(null);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getName()).thenReturn("Name");
    when(entity2.getParentObject()).thenReturn(dbsDocumentConstraint);
    DBSDocumentConstraint dbsDocumentConstraint2 = new DBSDocumentConstraint(entity2);

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getName()).thenReturn("Name");
    when(entity3.getParentObject()).thenReturn(dbsDocumentConstraint2);
    DBSDocumentConstraint dbsDocumentConstraint3 = new DBSDocumentConstraint(entity3);

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getName()).thenReturn("Name");
    when(entity4.getParentObject()).thenReturn(dbsDocumentConstraint3);
    DBSDocumentConstraint dbsDocumentConstraint4 = new DBSDocumentConstraint(entity4);

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getName()).thenReturn("Name");
    when(entity5.getParentObject()).thenReturn(dbsDocumentConstraint4);
    DBSDocumentConstraint dbsDocumentConstraint5 = new DBSDocumentConstraint(entity5);

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getName()).thenReturn("Name");
    when(entity6.getParentObject()).thenReturn(dbsDocumentConstraint5);
    DBSDocumentConstraint dbsDocumentConstraint6 = new DBSDocumentConstraint(entity6);

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getName()).thenReturn("Name");
    when(entity7.getParentObject()).thenReturn(dbsDocumentConstraint6);
    DBSDocumentConstraint dbsDocumentConstraint7 = new DBSDocumentConstraint(entity7);

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getName()).thenReturn("Name");
    when(entity8.getParentObject()).thenReturn(dbsDocumentConstraint7);
    DBSDocumentConstraint dbsDocumentConstraint8 = new DBSDocumentConstraint(entity8);

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getName()).thenReturn("Name");
    when(entity9.getParentObject()).thenReturn(dbsDocumentConstraint8);
    DBSDocumentConstraint dbsDocumentConstraint9 = new DBSDocumentConstraint(entity9);

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getName()).thenReturn("Name");
    when(entity10.getParentObject()).thenReturn(dbsDocumentConstraint9);
    DBSDocumentConstraint dbsDocumentConstraint10 = new DBSDocumentConstraint(entity10);

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getName()).thenReturn("Name");
    when(entity11.getParentObject()).thenReturn(dbsDocumentConstraint10);
    DBSDocumentConstraint dbsDocumentConstraint11 = new DBSDocumentConstraint(entity11);

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getName()).thenReturn("Name");
    when(entity12.getParentObject()).thenReturn(dbsDocumentConstraint11);
    DBSDocumentConstraint dbsDocumentConstraint12 = new DBSDocumentConstraint(entity12);

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getName()).thenReturn("Name");
    when(entity13.getParentObject()).thenReturn(dbsDocumentConstraint12);
    DBSDocumentConstraint dbsDocumentConstraint13 = new DBSDocumentConstraint(entity13);

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getName()).thenReturn("Name");
    when(entity14.getParentObject()).thenReturn(dbsDocumentConstraint13);
    DBSDocumentConstraint dbsDocumentConstraint14 = new DBSDocumentConstraint(entity14);

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getName()).thenReturn("Name");
    when(entity15.getParentObject()).thenReturn(dbsDocumentConstraint14);
    DBSDocumentConstraint dbsDocumentConstraint15 = new DBSDocumentConstraint(entity15);

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getName()).thenReturn("Name");
    when(entity16.getParentObject()).thenReturn(dbsDocumentConstraint15);
    DBSDocumentConstraint dbsDocumentConstraint16 = new DBSDocumentConstraint(entity16);

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getName()).thenReturn("Name");
    when(entity17.getParentObject()).thenReturn(dbsDocumentConstraint16);
    DBSDocumentConstraint dbsDocumentConstraint17 = new DBSDocumentConstraint(entity17);

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getName()).thenReturn("Name");
    when(entity18.getParentObject()).thenReturn(dbsDocumentConstraint17);
    DBSDocumentConstraint dbsDocumentConstraint18 = new DBSDocumentConstraint(entity18);

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getName()).thenReturn("Name");
    when(entity19.getParentObject()).thenReturn(dbsDocumentConstraint18);
    DBSDocumentConstraint dbsDocumentConstraint19 = new DBSDocumentConstraint(entity19);

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getName()).thenReturn("Name");
    when(entity20.getParentObject()).thenReturn(dbsDocumentConstraint19);
    DBSDocumentConstraint dbsDocumentConstraint20 = new DBSDocumentConstraint(entity20);

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getName()).thenReturn("Name");
    when(entity21.getParentObject()).thenReturn(dbsDocumentConstraint20);
    DBSDocumentConstraint dbsDocumentConstraint21 = new DBSDocumentConstraint(entity21);

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getName()).thenReturn("Name");
    when(entity22.getParentObject()).thenReturn(dbsDocumentConstraint21);
    DBSDocumentConstraint dbsDocumentConstraint22 = new DBSDocumentConstraint(entity22);

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getName()).thenReturn("Name");
    when(entity23.getParentObject()).thenReturn(dbsDocumentConstraint22);
    DBSDocumentConstraint dbsDocumentConstraint23 = new DBSDocumentConstraint(entity23);

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getName()).thenReturn("Name");
    when(entity24.getParentObject()).thenReturn(dbsDocumentConstraint23);
    DBSDocumentConstraint dbsDocumentConstraint24 = new DBSDocumentConstraint(entity24);

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getName()).thenReturn("Name");
    when(entity25.getParentObject()).thenReturn(dbsDocumentConstraint24);
    DBSDocumentConstraint dbsDocumentConstraint25 = new DBSDocumentConstraint(entity25);

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getName()).thenReturn("Name");
    when(entity26.getParentObject()).thenReturn(dbsDocumentConstraint25);
    DBSDocumentConstraint dbsDocumentConstraint26 = new DBSDocumentConstraint(entity26);

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getName()).thenReturn("Name");
    when(entity27.getParentObject()).thenReturn(dbsDocumentConstraint26);
    DBSDocumentConstraint dbsDocumentConstraint27 = new DBSDocumentConstraint(entity27);

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getName()).thenReturn("Name");
    when(entity28.getParentObject()).thenReturn(dbsDocumentConstraint27);
    DBSDocumentConstraint dbsDocumentConstraint28 = new DBSDocumentConstraint(entity28);

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getName()).thenReturn("Name");
    when(entity29.getParentObject()).thenReturn(dbsDocumentConstraint28);
    DBSDocumentConstraint dbsDocumentConstraint29 = new DBSDocumentConstraint(entity29);

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getName()).thenReturn("Name");
    when(entity30.getParentObject()).thenReturn(dbsDocumentConstraint29);
    DBSDocumentConstraint dbsDocumentConstraint30 = new DBSDocumentConstraint(entity30);

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getName()).thenReturn("Name");
    when(entity31.getParentObject()).thenReturn(dbsDocumentConstraint30);
    DBSDocumentConstraint dbsDocumentConstraint31 = new DBSDocumentConstraint(entity31);

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getName()).thenReturn("Name");
    when(entity32.getParentObject()).thenReturn(dbsDocumentConstraint31);
    DBSDocumentConstraint dbsDocumentConstraint32 = new DBSDocumentConstraint(entity32);

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getName()).thenReturn("Name");
    when(entity33.getParentObject()).thenReturn(dbsDocumentConstraint32);
    DBSDocumentConstraint dbsDocumentConstraint33 = new DBSDocumentConstraint(entity33);

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getName()).thenReturn("Name");
    when(entity34.getParentObject()).thenReturn(dbsDocumentConstraint33);
    DBSDocumentConstraint dbsDocumentConstraint34 = new DBSDocumentConstraint(entity34);

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getName()).thenReturn("Name");
    when(entity35.getParentObject()).thenReturn(dbsDocumentConstraint34);
    DBSDocumentConstraint dbsDocumentConstraint35 = new DBSDocumentConstraint(entity35);

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getName()).thenReturn("Name");
    when(entity36.getParentObject()).thenReturn(dbsDocumentConstraint35);
    DBSDocumentConstraint dbsDocumentConstraint36 = new DBSDocumentConstraint(entity36);

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getName()).thenReturn("Name");
    when(entity37.getParentObject()).thenReturn(dbsDocumentConstraint36);
    DBSDocumentConstraint dbsDocumentConstraint37 = new DBSDocumentConstraint(entity37);

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getName()).thenReturn("Name");
    when(entity38.getParentObject()).thenReturn(dbsDocumentConstraint37);
    DBSDocumentConstraint dbsDocumentConstraint38 = new DBSDocumentConstraint(entity38);

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getName()).thenReturn("Name");
    when(entity39.getParentObject()).thenReturn(dbsDocumentConstraint38);
    DBSDocumentConstraint dbsDocumentConstraint39 = new DBSDocumentConstraint(entity39);

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getName()).thenReturn("Name");
    when(entity40.getParentObject()).thenReturn(dbsDocumentConstraint39);
    DBSDocumentConstraint dbsDocumentConstraint40 = new DBSDocumentConstraint(entity40);

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getName()).thenReturn("Name");
    when(entity41.getParentObject()).thenReturn(dbsDocumentConstraint40);
    DBSDocumentConstraint dbsDocumentConstraint41 = new DBSDocumentConstraint(entity41);

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getName()).thenReturn("Name");
    when(entity42.getParentObject()).thenReturn(dbsDocumentConstraint41);
    DBSDocumentConstraint dbsDocumentConstraint42 = new DBSDocumentConstraint(entity42);

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getName()).thenReturn("Name");
    when(entity43.getParentObject()).thenReturn(dbsDocumentConstraint42);
    DBSDocumentConstraint dbsDocumentConstraint43 = new DBSDocumentConstraint(entity43);

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getName()).thenReturn("Name");
    when(entity44.getParentObject()).thenReturn(dbsDocumentConstraint43);
    DBSDocumentConstraint dbsDocumentConstraint44 = new DBSDocumentConstraint(entity44);

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getName()).thenReturn("Name");
    when(entity45.getParentObject()).thenReturn(dbsDocumentConstraint44);
    DBSDocumentConstraint dbsDocumentConstraint45 = new DBSDocumentConstraint(entity45);

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getName()).thenReturn("Name");
    when(entity46.getParentObject()).thenReturn(dbsDocumentConstraint45);
    DBSDocumentConstraint dbsDocumentConstraint46 = new DBSDocumentConstraint(entity46);

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getName()).thenReturn("Name");
    when(entity47.getParentObject()).thenReturn(dbsDocumentConstraint46);
    DBSDocumentConstraint dbsDocumentConstraint47 = new DBSDocumentConstraint(entity47);

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getName()).thenReturn("Name");
    when(entity48.getParentObject()).thenReturn(dbsDocumentConstraint47);
    DBSDocumentConstraint dbsDocumentConstraint48 = new DBSDocumentConstraint(entity48);

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getName()).thenReturn("Name");
    when(entity49.getParentObject()).thenReturn(dbsDocumentConstraint48);
    DBSDocumentConstraint dbsDocumentConstraint49 = new DBSDocumentConstraint(entity49);

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getName()).thenReturn("Name");
    when(entity50.getParentObject()).thenReturn(dbsDocumentConstraint49);
    DBSDocumentConstraint dbsDocumentConstraint50 = new DBSDocumentConstraint(entity50);

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getName()).thenReturn("Name");
    when(entity51.getParentObject()).thenReturn(dbsDocumentConstraint50);
    DBSDocumentConstraint dbsDocumentConstraint51 = new DBSDocumentConstraint(entity51);

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getName()).thenReturn("Name");
    when(entity52.getParentObject()).thenReturn(dbsDocumentConstraint51);
    DBSDocumentConstraint dbsDocumentConstraint52 = new DBSDocumentConstraint(entity52);

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getName()).thenReturn("Name");
    when(entity53.getParentObject()).thenReturn(dbsDocumentConstraint52);
    DBSDocumentConstraint dbsDocumentConstraint53 = new DBSDocumentConstraint(entity53);

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getName()).thenReturn("Name");
    when(entity54.getParentObject()).thenReturn(dbsDocumentConstraint53);
    DBSDocumentConstraint dbsDocumentConstraint54 = new DBSDocumentConstraint(entity54);

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getName()).thenReturn("Name");
    when(entity55.getParentObject()).thenReturn(dbsDocumentConstraint54);
    DBSDocumentConstraint dbsDocumentConstraint55 = new DBSDocumentConstraint(entity55);

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getName()).thenReturn("Name");
    when(entity56.getParentObject()).thenReturn(dbsDocumentConstraint55);
    DBSDocumentConstraint dbsDocumentConstraint56 = new DBSDocumentConstraint(entity56);

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getName()).thenReturn("Name");
    when(entity57.getParentObject()).thenReturn(dbsDocumentConstraint56);
    DBSDocumentConstraint dbsDocumentConstraint57 = new DBSDocumentConstraint(entity57);

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getName()).thenReturn("Name");
    when(entity58.getParentObject()).thenReturn(dbsDocumentConstraint57);
    DBSDocumentConstraint dbsDocumentConstraint58 = new DBSDocumentConstraint(entity58);

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getName()).thenReturn("Name");
    when(entity59.getParentObject()).thenReturn(dbsDocumentConstraint58);
    DBSDocumentConstraint dbsDocumentConstraint59 = new DBSDocumentConstraint(entity59);

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getName()).thenReturn("Name");
    when(entity60.getParentObject()).thenReturn(dbsDocumentConstraint59);
    DBSDocumentConstraint dbsDocumentConstraint60 = new DBSDocumentConstraint(entity60);

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getName()).thenReturn("Name");
    when(entity61.getParentObject()).thenReturn(dbsDocumentConstraint60);
    DBSDocumentConstraint dbsDocumentConstraint61 = new DBSDocumentConstraint(entity61);

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getName()).thenReturn("Name");
    when(entity62.getParentObject()).thenReturn(dbsDocumentConstraint61);
    DBSDocumentConstraint dbsDocumentConstraint62 = new DBSDocumentConstraint(entity62);

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getName()).thenReturn("Name");
    when(entity63.getParentObject()).thenReturn(dbsDocumentConstraint62);
    DBSDocumentConstraint dbsDocumentConstraint63 = new DBSDocumentConstraint(entity63);

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getName()).thenReturn("Name");
    when(entity64.getParentObject()).thenReturn(dbsDocumentConstraint63);
    DBSDocumentConstraint dbsDocumentConstraint64 = new DBSDocumentConstraint(entity64);

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getName()).thenReturn("Name");
    when(entity65.getParentObject()).thenReturn(dbsDocumentConstraint64);
    DBSDocumentConstraint dbsDocumentConstraint65 = new DBSDocumentConstraint(entity65);

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getName()).thenReturn("Name");
    when(entity66.getParentObject()).thenReturn(dbsDocumentConstraint65);
    DBSDocumentConstraint dbsDocumentConstraint66 = new DBSDocumentConstraint(entity66);

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getName()).thenReturn("Name");
    when(entity67.getParentObject()).thenReturn(dbsDocumentConstraint66);
    DBSDocumentConstraint dbsDocumentConstraint67 = new DBSDocumentConstraint(entity67);

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getName()).thenReturn("Name");
    when(entity68.getParentObject()).thenReturn(dbsDocumentConstraint67);
    DBSDocumentConstraint dbsDocumentConstraint68 = new DBSDocumentConstraint(entity68);

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getName()).thenReturn("Name");
    when(entity69.getParentObject()).thenReturn(dbsDocumentConstraint68);
    DBSDocumentConstraint dbsDocumentConstraint69 = new DBSDocumentConstraint(entity69);

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getName()).thenReturn("Name");
    when(entity70.getParentObject()).thenReturn(dbsDocumentConstraint69);
    DBSDocumentConstraint dbsDocumentConstraint70 = new DBSDocumentConstraint(entity70);

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getName()).thenReturn("Name");
    when(entity71.getParentObject()).thenReturn(dbsDocumentConstraint70);
    DBSDocumentConstraint dbsDocumentConstraint71 = new DBSDocumentConstraint(entity71);

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getName()).thenReturn("Name");
    when(entity72.getParentObject()).thenReturn(dbsDocumentConstraint71);
    DBSDocumentConstraint dbsDocumentConstraint72 = new DBSDocumentConstraint(entity72);

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getName()).thenReturn("Name");
    when(entity73.getParentObject()).thenReturn(dbsDocumentConstraint72);
    DBSDocumentConstraint dbsDocumentConstraint73 = new DBSDocumentConstraint(entity73);

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getName()).thenReturn("Name");
    when(entity74.getParentObject()).thenReturn(dbsDocumentConstraint73);
    DBSDocumentConstraint dbsDocumentConstraint74 = new DBSDocumentConstraint(entity74);

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getName()).thenReturn("Name");
    when(entity75.getParentObject()).thenReturn(dbsDocumentConstraint74);
    DBSDocumentConstraint dbsDocumentConstraint75 = new DBSDocumentConstraint(entity75);

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getName()).thenReturn("Name");
    when(entity76.getParentObject()).thenReturn(dbsDocumentConstraint75);
    DBSDocumentConstraint dbsDocumentConstraint76 = new DBSDocumentConstraint(entity76);

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getName()).thenReturn("Name");
    when(entity77.getParentObject()).thenReturn(dbsDocumentConstraint76);
    DBSDocumentConstraint dbsDocumentConstraint77 = new DBSDocumentConstraint(entity77);

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getName()).thenReturn("Name");
    when(entity78.getParentObject()).thenReturn(dbsDocumentConstraint77);
    DBSDocumentConstraint dbsDocumentConstraint78 = new DBSDocumentConstraint(entity78);

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getName()).thenReturn("Name");
    when(entity79.getParentObject()).thenReturn(dbsDocumentConstraint78);

    // Act
    boolean actualIsExpensivePropertiesReadEnabledForResult =
        DataSourcePropertyFilter.isExpensivePropertiesReadEnabledFor(
            new DBSDocumentConstraint(entity79));

    // Assert
    verify(entity79).getName();
    verify(entity78).getName();
    verify(entity77).getName();
    verify(entity76).getName();
    verify(entity75).getName();
    verify(entity74).getName();
    verify(entity73).getName();
    verify(entity72).getName();
    verify(entity71).getName();
    verify(entity70).getName();
    verify(entity69).getName();
    verify(entity68).getName();
    verify(entity67).getName();
    verify(entity66).getName();
    verify(entity65).getName();
    verify(entity64).getName();
    verify(entity63).getName();
    verify(entity62).getName();
    verify(entity61).getName();
    verify(entity60).getName();
    verify(entity59).getName();
    verify(entity58).getName();
    verify(entity57).getName();
    verify(entity56).getName();
    verify(entity55).getName();
    verify(entity54).getName();
    verify(entity53).getName();
    verify(entity52).getName();
    verify(entity51).getName();
    verify(entity50).getName();
    verify(entity49).getName();
    verify(entity48).getName();
    verify(entity47).getName();
    verify(entity46).getName();
    verify(entity45).getName();
    verify(entity44).getName();
    verify(entity43).getName();
    verify(entity42).getName();
    verify(entity41).getName();
    verify(entity40).getName();
    verify(entity39).getName();
    verify(entity38).getName();
    verify(entity37).getName();
    verify(entity36).getName();
    verify(entity35).getName();
    verify(entity34).getName();
    verify(entity33).getName();
    verify(entity32).getName();
    verify(entity31).getName();
    verify(entity30).getName();
    verify(entity29).getName();
    verify(entity28).getName();
    verify(entity27).getName();
    verify(entity26).getName();
    verify(entity25).getName();
    verify(entity24).getName();
    verify(entity23).getName();
    verify(entity22).getName();
    verify(entity21).getName();
    verify(entity20).getName();
    verify(entity19).getName();
    verify(entity18).getName();
    verify(entity17).getName();
    verify(entity16).getName();
    verify(entity15).getName();
    verify(entity14).getName();
    verify(entity13).getName();
    verify(entity12).getName();
    verify(entity11).getName();
    verify(entity10).getName();
    verify(entity9).getName();
    verify(entity8).getName();
    verify(entity7).getName();
    verify(entity6).getName();
    verify(entity5).getName();
    verify(entity4).getName();
    verify(entity3).getName();
    verify(entity2).getName();
    verify(entity).getName();
    verify(entity79, atLeast(1)).getParentObject();
    verify(entity78, atLeast(1)).getParentObject();
    verify(entity77, atLeast(1)).getParentObject();
    verify(entity76, atLeast(1)).getParentObject();
    verify(entity75, atLeast(1)).getParentObject();
    verify(entity74, atLeast(1)).getParentObject();
    verify(entity73, atLeast(1)).getParentObject();
    verify(entity72, atLeast(1)).getParentObject();
    verify(entity71, atLeast(1)).getParentObject();
    verify(entity70, atLeast(1)).getParentObject();
    verify(entity69, atLeast(1)).getParentObject();
    verify(entity68, atLeast(1)).getParentObject();
    verify(entity67, atLeast(1)).getParentObject();
    verify(entity66, atLeast(1)).getParentObject();
    verify(entity65, atLeast(1)).getParentObject();
    verify(entity64, atLeast(1)).getParentObject();
    verify(entity63, atLeast(1)).getParentObject();
    verify(entity62, atLeast(1)).getParentObject();
    verify(entity61, atLeast(1)).getParentObject();
    verify(entity60, atLeast(1)).getParentObject();
    verify(entity59, atLeast(1)).getParentObject();
    verify(entity58, atLeast(1)).getParentObject();
    verify(entity57, atLeast(1)).getParentObject();
    verify(entity56, atLeast(1)).getParentObject();
    verify(entity55, atLeast(1)).getParentObject();
    verify(entity54, atLeast(1)).getParentObject();
    verify(entity53, atLeast(1)).getParentObject();
    verify(entity52, atLeast(1)).getParentObject();
    verify(entity51, atLeast(1)).getParentObject();
    verify(entity50, atLeast(1)).getParentObject();
    verify(entity49, atLeast(1)).getParentObject();
    verify(entity48, atLeast(1)).getParentObject();
    verify(entity47, atLeast(1)).getParentObject();
    verify(entity46, atLeast(1)).getParentObject();
    verify(entity45, atLeast(1)).getParentObject();
    verify(entity44, atLeast(1)).getParentObject();
    verify(entity43, atLeast(1)).getParentObject();
    verify(entity42, atLeast(1)).getParentObject();
    verify(entity41, atLeast(1)).getParentObject();
    verify(entity40, atLeast(1)).getParentObject();
    verify(entity39, atLeast(1)).getParentObject();
    verify(entity38, atLeast(1)).getParentObject();
    verify(entity37, atLeast(1)).getParentObject();
    verify(entity36, atLeast(1)).getParentObject();
    verify(entity35, atLeast(1)).getParentObject();
    verify(entity34, atLeast(1)).getParentObject();
    verify(entity33, atLeast(1)).getParentObject();
    verify(entity32, atLeast(1)).getParentObject();
    verify(entity31, atLeast(1)).getParentObject();
    verify(entity30, atLeast(1)).getParentObject();
    verify(entity29, atLeast(1)).getParentObject();
    verify(entity28, atLeast(1)).getParentObject();
    verify(entity27, atLeast(1)).getParentObject();
    verify(entity26, atLeast(1)).getParentObject();
    verify(entity25, atLeast(1)).getParentObject();
    verify(entity24, atLeast(1)).getParentObject();
    verify(entity23, atLeast(1)).getParentObject();
    verify(entity22, atLeast(1)).getParentObject();
    verify(entity21, atLeast(1)).getParentObject();
    verify(entity20, atLeast(1)).getParentObject();
    verify(entity19, atLeast(1)).getParentObject();
    verify(entity18, atLeast(1)).getParentObject();
    verify(entity17, atLeast(1)).getParentObject();
    verify(entity16, atLeast(1)).getParentObject();
    verify(entity15, atLeast(1)).getParentObject();
    verify(entity14, atLeast(1)).getParentObject();
    verify(entity13, atLeast(1)).getParentObject();
    verify(entity12, atLeast(1)).getParentObject();
    verify(entity11, atLeast(1)).getParentObject();
    verify(entity10, atLeast(1)).getParentObject();
    verify(entity9, atLeast(1)).getParentObject();
    verify(entity8, atLeast(1)).getParentObject();
    verify(entity7, atLeast(1)).getParentObject();
    verify(entity6, atLeast(1)).getParentObject();
    verify(entity5, atLeast(1)).getParentObject();
    verify(entity4, atLeast(1)).getParentObject();
    verify(entity3, atLeast(1)).getParentObject();
    verify(entity2, atLeast(1)).getParentObject();
    verify(entity, atLeast(1)).getParentObject();
    assertFalse(actualIsExpensivePropertiesReadEnabledForResult);
  }
}
