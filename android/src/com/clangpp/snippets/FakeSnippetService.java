package com.clangpp.snippets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FakeSnippetService implements SnippetService {
  private final int NUM_SNIPPETS = 20;
  private final List<String> snippetIds = new ArrayList<String>();
  private final Map<String, Snippet> snippets = new HashMap<String, Snippet>();
  private final Set<String> knownSnippetIds = new HashSet<String>();

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
  public Snippet getSnippet(String snippetId) {
    return snippets.get(snippetId);
  }

  @Override
  public Snippet tryGetSnippet(String snippetId) {
    if (!knownSnippetIds.contains(snippetId)) {
      if (snippets.containsKey(snippetId)) {
        knownSnippetIds.add(snippetId);
      }
      return null;
    }
    return snippets.get(snippetId);
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
