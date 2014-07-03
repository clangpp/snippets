package com.clangpp.snippets;

public class SnippetServiceFactory {
  private static SnippetService cachedSnippetService;

  public static SnippetService getSnippetService() {
    return new FakeSnippetService();
  }

  public static SnippetService getCachedSnippetService() {
    return new CachedServiceDecorator(getSnippetService());
  }

  public static SnippetService getSingletonCachedSnippetService() {
    if (cachedSnippetService == null) {
      cachedSnippetService = new CachedServiceDecorator(getSnippetService());
    }
    return cachedSnippetService;
  }

}
