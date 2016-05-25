package io.freshnotes.domain.persistence;

import java.util.List;

import io.freshnotes.domain.model.Note;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by calin on 5/18/2016.
 */
public class DbContext {

    private static Realm realm = Realm.getDefaultInstance();

    public static class Notes {

        public static Note findById(Long id) {
            realm.beginTransaction();
            final Note note = realm.where(Note.class).equalTo(Note.ID, id).findFirst();
            realm.commitTransaction();
            return note;
        }

        public static List<Note> listAll() {
            realm.beginTransaction();
            final RealmResults<Note> results = realm.where(Note.class).findAll();
            realm.commitTransaction();
            return results.subList(0, results.size());

        }

        public static void save(final Note note) {
            final Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    long key = getNextKey(realm);
                    note.setId(key);
                    realm.copyToRealm(note);
                }
            });
        }

        private static long getNextKey(Realm realm) {
            long key;
            try {
                key = realm.where(Note.class).max(Note.ID).longValue() + 1;
            } catch(ArrayIndexOutOfBoundsException | NullPointerException ex) {
                key = 0;
            }
            return key;
        }

        public static void delete(final Note note) {
            final Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.where(Note.class).equalTo(Note.ID, note.getId()).findFirst().deleteFromRealm();
                }
            });
        }

        public static class Update {
            private final Realm realm;

            private final Long id;
            private String title;
            private String content;

            private int colorHex;

            public Update(Long id) {
                this.realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                this.id = id;
            }

            public Update title(String newTitle) {
                this.title = newTitle;
                return this;
            }

            public Update content(String newContent) {
                this.content = newContent;
                return this;
            }


            public Update colorHex(int selectedColor) {
                this.colorHex = selectedColor;
                return this;
            }

            public void commit() {
                final Note note = realm.where(Note.class).equalTo(Note.ID, id).findFirst();
                note.setTitle(title);
                note.setContent(content);
                note.setColorHex(colorHex);
                realm.commitTransaction();
            }
        }

    }
}
