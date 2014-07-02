package com.clangpp.snippets;

public class SnippetServiceFactory {
  public SnippetService getSnippetService() {
    return new FakeSnippetService();
  }

}
