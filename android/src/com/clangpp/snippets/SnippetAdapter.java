package com.clangpp.snippets;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SnippetAdapter extends BaseAdapter {
  private Context context;

  public SnippetAdapter(Context c) {
    context = c;
  }

  @Override
  public int getCount() {
    return 50; // TODO(clangpp): Use real data.
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
    textView.setText("placeholder " + position); // TODO(clangpp): Use real data.
    return textView;
  }

}
