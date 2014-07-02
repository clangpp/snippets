package com.clangpp.snippets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeSnippetService implements SnippetService {
  private final int NUM_SNIPPETS = 20;
  private final List<String> snippetIds = new ArrayList<String>();
  private final Map<String, Snippet> snippets = new HashMap<String, Snippet>();

  public FakeSnippetService() {
    for (int i = 0; i < NUM_SNIPPETS; ++i) {
      String fakeId = "fakeId$" + i;
      Snippet fakeSnippet =
          Snippet.newBuilder().setId(fakeId).setContent("fakeContent$" + i).build();
      snippetIds.add(fakeId);
      snippets.put(fakeId, fakeSnippet);
    }
  }

  @Override
  public Snippet getSnippet(String id) {
    return snippets.get(id);
  }

  @Override
  public List<String> listSnippet(String topic) {
    return snippetIds;
  }

  @Override
  public Snippet updateSnippet(Snippet snippet) {
    return snippet;
  }

}
