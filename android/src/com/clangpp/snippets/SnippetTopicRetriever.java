package com.clangpp.snippets;

import java.util.List;

public class SnippetTopicRetriever {
  public interface RetrieveCompleteCallback {
    void onRetrieveComplete(List<String> snippetIdList);
  }
  
  private final SnippetService snippetService;
  private final String topic;
  
  public SnippetTopicRetriever(SnippetService snippetService, String topic) {
    this.snippetService = snippetService;
    this.topic = topic;
  }
  
  public void retrieve(final RetrieveCompleteCallback retrieveCompleteCallback) {
    List<String> snippetIdList = snippetService.listSnippet(topic);
    retrieveCompleteCallback.onRetrieveComplete(snippetIdList);
  }

}
