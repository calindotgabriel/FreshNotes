package io.freshnotes.domain.async;

import android.os.AsyncTask;

import java.util.List;

import de.greenrobot.event.EventBus;
import io.freshnotes.domain.event.OnNotesLoadedEvent;
import io.freshnotes.domain.model.Note;
import io.freshnotes.domain.persistence.DbContext;

/**
 * Created by motan on 02.11.2015.
 */
public class LoadNotesTask extends AsyncTask<Void, Void, List<Note>> {

    @Override
    protected List<Note> doInBackground(Void... params) {
        return DbContext.Notes.listAll();
    }

    @Override
    protected void onPostExecute(List<io.freshnotes.domain.model.Note> notes) {
        EventBus.getDefault().post(new OnNotesLoadedEvent(notes));
    }
}
