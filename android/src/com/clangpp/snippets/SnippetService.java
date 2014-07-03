package com.clangpp.snippets;

import java.util.List;

public interface SnippetService {

  /**
   * Retrieves snippet by given snippet ID.
   * 
   * @param snippetId
   *          The ID of a snippet.
   * @return The snippet. null if not found.
   */
  Snippet getSnippet(String snippetId);

  /**
   * Tries to retrieve snippet by given snippet ID, should return immediately.
   * 
   * @param snippetId
   *          The ID of a snippet.
   * @return The snippet. null if not found immediately.
   */
  Snippet tryGetSnippet(String snippetId);

  /**
   * Retrieves all snippets with the same topic.
   * 
   * @param topic
   *          The topic which snippets may contain.
   * @return List of snippet ID.
   */
  List<String> listSnippet(String topic);

  /**
   * Updates snippet in data source (local storage or server).1. If given
   * snippet has no ID, data source will create a new snippet with provided
   * snippet. 2. If given snippet has an ID and is newer, data source will
   * update its snippet with provided snippet.
   * 
   * @param snippet
   *          The snippet which is new or modified.
   * @return The snippet from updated data source.
   */
  Snippet updateSnippet(Snippet snippet);

}
