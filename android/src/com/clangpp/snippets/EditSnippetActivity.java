package com.clangpp.snippets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class EditSnippetActivity extends Activity {
  private EditText snippetContent;
  private TextView snippetTimestamp;
  private Snippet snippet;
  private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS ZZZZZ";
  private static final String TAG = EditSnippetActivity.class.getName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_snippet);

    snippetContent = (EditText) findViewById(R.id.snippet_content);
    snippetTimestamp = (TextView) findViewById(R.id.snippet_timestamp);

    snippet = Snippet.newBuilder().build();
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

    SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
    snippetTimestamp.setText(sdf.format(new Date(snippet.getTimestamp())));
  }

  void saveSnippet() {
    Snippet.Builder snippetBuilder = Snippet.newBuilder(snippet);
    
    snippetBuilder.setContent(snippetContent.getText().toString());

    SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
    Date date;
    try {
      date = sdf.parse(snippetTimestamp.getText().toString());
      snippetBuilder.setTimestamp(date.getTime());
    } catch (ParseException e) {
      Log.e(TAG, "Parse failed", e);
    }
    
    snippet = snippetBuilder.build();
  }
}
