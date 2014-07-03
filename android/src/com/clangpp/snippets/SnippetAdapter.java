package com.clangpp.snippets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SnippetAdapter extends BaseAdapter {
  class RetrieveSnippetAsyncTask extends AsyncTask<Void, Void, Snippet> {
    private final String snippetId;

    public RetrieveSnippetAsyncTask(String snippetId) {
      this.snippetId = snippetId;
    }

    @Override
    protected Snippet doInBackground(Void... params) {
      return snippetService.getSnippet(snippetId);
    }

    @Override
    protected void onPostExecute(Snippet result) {
      notifyDataSetChanged();
    }
  }

  private Context context;
  private SnippetService snippetService;
  private List<String> snippetIdList = new ArrayList<String>();

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
    Snippet snippet = snippetService.tryGetSnippet(snippetId);
    if (snippet != null) {
      textView.setText(snippet.getContent() + "\n--"
          + new Date(snippet.getTimestamp()));
    } else {
      new RetrieveSnippetAsyncTask(snippetId).execute();
    }
    return textView;
  }

}
