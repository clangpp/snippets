package com.clangpp.snippets;

public interface CachedSnippetService extends SnippetService {

  /**
   * Tries to retrieve snippet by given snippet ID, should return immediately.
   * 
   * @param snippetId
   *          The ID of a snippet.
   * @return The snippet. null if can't find immediately.
   */
  Snippet tryGetSnippet(String snippetId);

}
