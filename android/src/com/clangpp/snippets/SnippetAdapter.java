package com.clangpp.snippets;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SnippetAdapter extends BaseAdapter {
  class RetrieveSnippetAsyncTask extends AsyncTask<Void, Void, Void> {
    private final String snippetId;

    public RetrieveSnippetAsyncTask(String snippetId) {
      this.snippetId = snippetId;
    }

    @Override
    protected Void doInBackground(Void... params) {
      Snippet snippet = snippetService.getSnippet(snippetId);
      snippetCache.put(snippetId, snippet);
      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      retrievingSnippetIds.remove(snippetId);
      notifyDataSetChanged();
    }
  }

  private Context context;
  private SnippetService snippetService;
  private List<String> snippetIdList = new ArrayList<String>();
  private final LruCache<String, Snippet> snippetCache = new LruCache<String, Snippet>(100);
  private final Set<String> retrievingSnippetIds = new HashSet<String>();

  public SnippetAdapter(Context context, SnippetService snippetService) {
    this.context = context;
    this.snippetService = snippetService;
  }

  public void setSnippetIdList(List<String> snippetIdList) {
    this.snippetIdList = snippetIdList;
  }

  @Override
  public int getCount() {
    return snippetIdList.size();
  }

  @Override
  public Object getItem(int position) {
    return null; // TODO(clangpp): Use real data.
  }

  @Override
  public long getItemId(int position) {
    return 0; // TODO(clangpp): Use real data.
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    TextView textView;
    if (convertView == null) {
      textView = new TextView(context);
    } else {
      textView = (TextView) convertView;
      textView.setText("");
    }
    if (position >= snippetIdList.size()) {
      return textView;
    }

    String snippetId = snippetIdList.get(position);
    Snippet snippet = snippetCache.get(snippetId);
    if (snippet != null) {
      textView.setText(snippet.getContent() + "\n--"
          + new Date(snippet.getTimestamp()));
    } else {
      if (!retrievingSnippetIds.contains(snippetId)) {
        retrievingSnippetIds.add(snippetId);
        new RetrieveSnippetAsyncTask(snippetId).execute();
      }
    }
    return textView;
  }

}
