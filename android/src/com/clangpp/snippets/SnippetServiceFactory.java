package com.clangpp.snippets;

public class SnippetServiceFactory {
  public static SnippetService getSnippetService() {
    return new FakeSnippetService();
  }

}
