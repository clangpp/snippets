package com.clangpp.snippets;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ListSnippetActivity extends Activity {
  private ListView snippetList;
  private SnippetAdapter snippetAdapter;
  private SnippetService snippetService;
  private String topic = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_snippet);

    // TODO(ytzhang): Read topic from intent if provided.

    snippetList = (ListView) findViewById(R.id.snippet_list);
    snippetService = SnippetServiceFactory.getSnippetService();
    snippetAdapter = new SnippetAdapter(this, snippetService);
    snippetList.setAdapter(snippetAdapter);
    listSnippetAsync();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void listSnippetAsync() {
    snippetList.setEnabled(false);
    new SnippetTopicRetriever(snippetService, topic)
        .retrieve(new SnippetTopicRetriever.RetrieveCompleteCallback() {

          @Override
          public void onRetrieveComplete(List<String> snippetIdList) {
            snippetAdapter.setSnippetIdList(snippetIdList);
            snippetList.setEnabled(true);
            snippetAdapter.notifyDataSetChanged();
          }
        });
  }

}
