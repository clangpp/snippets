package com.clangpp.snippets;

import java.util.List;

import android.util.LruCache;

public class CachedServiceDecorator
    extends SnippetServiceDecorator
    implements CachedSnippetService {
  public static final int DEFAULT_CACHE_SIZE_IN_BYTES = 4 * 1024 * 1024;  // 4MB

  private LruCache<String, Snippet> snippetCache;

  public CachedServiceDecorator(SnippetService snippetService,
      int cacheSizeInBytes) {
    super(snippetService);
    snippetCache = new LruCache<String, Snippet>(cacheSizeInBytes) {
      protected int sizeOf(String key, Snippet value) {
        return value.getContent().length();
      }
    };
  }

  public CachedServiceDecorator(SnippetService snippetService) {
    this(snippetService, DEFAULT_CACHE_SIZE_IN_BYTES);
  }

  @Override
  public Snippet getSnippet(String snippetId) {
    Snippet snippet = snippetCache.get(snippetId);
    if (snippet == null) {
      snippet = snippetService.getSnippet(snippetId);
      snippetCache.put(snippet.getId(), snippet);
    }
    return snippet;
  }

  @Override
  public Snippet tryGetSnippet(String snippetId) {
    return snippetCache.get(snippetId);
  }

  @Override
  public List<String> listSnippet(String topic) {
    return snippetService.listSnippet(topic);
  }

  @Override
  public Snippet updateSnippet(Snippet snippet) {
    Snippet updatedSnippet = snippetService.updateSnippet(snippet);
    snippetCache.put(updatedSnippet.getId(), updatedSnippet);
    return null;
  }

}
