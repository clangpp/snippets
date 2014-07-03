package com.clangpp.snippets;

public class SnippetServiceFactory {

  public static SnippetService getSnippetService() {
    return new FakeSnippetService();
  }

  public static SnippetService getCachedSnippetService() {
    return new CachedServiceDecorator(getSnippetService());
  }

}
