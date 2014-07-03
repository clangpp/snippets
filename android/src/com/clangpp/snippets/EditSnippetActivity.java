package com.clangpp.snippets;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class EditSnippetActivity extends Activity {
  class RetrieveSnippetAsyncTask extends AsyncTask<String, Void, Snippet> {
    SnippetService snippetService;

    public RetrieveSnippetAsyncTask(SnippetService snippetService) {
      this.snippetService = snippetService;
    }

    @Override
    protected Snippet doInBackground(String... params) {
      return snippetService.getSnippet(params[0]);
    }

    @Override
    protected void onPostExecute(Snippet result) {
      snippet = result;
      showSnippet();
    }
  }

  private EditText snippetContent;
  private TextView snippetTimestamp;
  private Snippet snippet;
  private SnippetService snippetService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_snippet);

    snippetContent = (EditText) findViewById(R.id.snippet_content);
    snippetTimestamp = (TextView) findViewById(R.id.snippet_timestamp);

    // TODO(clangpp): Add option to read snippet ID from intent.
    snippet = Snippet.newBuilder().build();

    snippetService = SnippetServiceFactory.getSingletonCachedSnippetService();
    if (!snippet.getId().equals(Snippet.Builder.EMPTY_ID)) {
      new RetrieveSnippetAsyncTask(snippetService).execute(snippet.getId());
    }
    showSnippet();
  }

  @Override
  protected void onResume() {
    super.onResume();
    showSnippet();
  }

  @Override
  protected void onPause() {
    super.onPause();
    saveSnippet();
  }

  void showSnippet() {
    snippetContent.setText(snippet.getContent());
    snippetTimestamp.setText(
        Snippet.newTimestampFormat().format(snippet.getTimestamp()));
  }

  void saveSnippet() {
    Snippet.Builder snippetBuilder = Snippet.newBuilder(snippet);
    snippetBuilder.setContent(snippetContent.getText().toString());
    snippetBuilder.setTimestamp(
        Snippet.newTimestampFormat().parse(snippetTimestamp.getText().toString()));
    snippet = snippetBuilder.build();
  }
}
