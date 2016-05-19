package io.freshnotes;

import org.junit.Test;

import io.freshnotes.domain.persistence.DbContext;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {



    @Test
    public void UpdateClassIsOk() {

        new DbContext.Notes.Update(1L)
                .title("new").content("content").commit();
    }
}