package com.example.roomscpsample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScpRepository {
    private ScpDao mScpDao;
    private LiveData<List<Scp>> mAllScps;
    ScpRepository(Application application){
        ScpRoomDatabase db = ScpRoomDatabase.getDatabase(application);
        mScpDao = db.scpDao();
        mAllScps = mScpDao.getAllScps();
    }
    LiveData<List<Scp>> getmAllScps(){
        return mAllScps;
    }
    public void insert(Scp scp){
        new insertAsyncTask(mScpDao).execute(scp);
    }

    private class insertAsyncTask extends AsyncTask<Scp, Void, Void> {
        private ScpDao mAsyncTaskDao;
        public insertAsyncTask(ScpDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Scp... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAllScpsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ScpDao mAsyncTaskDao;

        deleteAllScpsAsyncTask(ScpDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    public void deleteAll()  {
        new deleteAllScpsAsyncTask(mScpDao).execute();
    }

    private static class deleteScpAsyncTask extends AsyncTask< Scp, Void, Void> {
        private ScpDao mAsyncTaskDao;

        deleteScpAsyncTask(ScpDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Scp... params) {
            mAsyncTaskDao.deleteScp(params[0]);
            return null;
        }
    }
    public void deleteScp(Scp scp)  {
        new deleteScpAsyncTask(mScpDao).execute(scp);
    }
}
