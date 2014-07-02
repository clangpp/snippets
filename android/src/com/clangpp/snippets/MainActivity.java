package com.clangpp.snippets;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {
  private ListView snippetListView;
  private SnippetAdapter snippetAdapter;
  private SnippetService snippetService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    snippetListView = (ListView) findViewById(R.id.snippet_list);
    snippetAdapter = new SnippetAdapter(this);
    snippetListView.setAdapter(snippetAdapter);
    snippetService = SnippetServiceFactory.getSnippetService();
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
    snippetListView.setEnabled(false);
    new SnippetTopicRetriever(snippetService, "")
        .retrieve(new SnippetTopicRetriever.RetrieveCompleteCallback() {

          @Override
          public void onRetrieveComplete(List<String> snippetIdList) {
            snippetAdapter.setSnippetIdList(snippetIdList);
            snippetListView.setEnabled(true);
            snippetAdapter.notifyDataSetChanged();
          }
        });
  }

}
