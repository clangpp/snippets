package com.clangpp.snippets;

public class SnippetServiceFactory {
  private static CachedSnippetService cachedSnippetService;

  public static SnippetService getSnippetService() {
    return new FakeSnippetService();
  }

  public static CachedSnippetService getCachedSnippetService() {
    return new CachedServiceDecorator(getSnippetService());
  }

  public static CachedSnippetService getSingletonCachedSnippetService() {
    if (cachedSnippetService == null) {
      cachedSnippetService = new CachedServiceDecorator(getSnippetService());
    }
    return cachedSnippetService;
  }

}
