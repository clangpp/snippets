package com.clangpp.snippets;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SnippetAdapter extends BaseAdapter {
  private Context context;
  private List<String> snippetIdList = new ArrayList<String>();

  public SnippetAdapter(Context context) {
    this.context = context;
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
    textView.setText(snippetIdList.get(position));
    return textView;
  }

}
