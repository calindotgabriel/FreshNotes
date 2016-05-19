package io.freshnotes;

import android.app.Application;
import android.test.ApplicationTestCase;

import io.freshnotes.domain.persistence.DbContext;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getContext()).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }


    public void TestUpdateOk() {

        new DbContext.Notes.Update(1L)
                .title("new").content("content").commit();
    }
}